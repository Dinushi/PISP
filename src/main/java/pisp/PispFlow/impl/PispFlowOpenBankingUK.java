/*
 *   Copyright (c) 2018, WSO2 Inc. (http://www.wso2.com). All Rights Reserved.
 *   This software is the property of WSO2 Inc. and its suppliers, if any.
 *   Dissemination of any information or reproduction of any material contained
 *   herein is strictly forbidden, unless permitted by WSO2 in accordance with
 *   the WSO2 Commercial License available at http://wso2.com/licenses. For specific
 *   language governing the permissions and limitations under this license,
 *   please see the license as well as any agreement you’ve entered into with
 *   WSO2 governing the purchase of this software and any associated services.
 */

package pisp.PispFlow.impl;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import pisp.PispFlow.PispFlow;
import pisp.dao.AccessTokenManagementDAO;
import pisp.exception.PispException;
import pisp.models.AccessToken;
import pisp.models.HTTPResponse;
import pisp.models.PaymentInitiationRequest;
import pisp.models.PispInternalResponse;
import pisp.utilities.Utilities;
import pisp.utilities.constants.Constants;
import pisp.utilities.constants.ErrorMessages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;


public class PispFlowOpenBankingUK implements PispFlow {

    private String clientID;
    private String redirectURL;
    private String xFAPIInteractionIID;
    private String xFAPIFinancialID;

    private String bank_uid;

    private String paymentInitiationsURL;
    private String tokenApiURL;
    private String authorizeApiURL;

    private String authorizationString;
    private String clientAssertionType;

    private char[] keyStorePassword;
    private String keyStoreDomainName;
    private String keyStorePath;

    private String audienceValue;

    private Certificate certificate = null;
    private JWSAlgorithm signatureAlgorithm = JWSAlgorithm.RS256;


    private AccessTokenManagementDAO accessTokenManagementDAO = new AccessTokenManagementDAO();

    private Log log = LogFactory.getLog(PispFlowOpenBankingUK.class);

    /**
     * Constructor to set bank attributes and load application attributes from configuration file.
     * Also generates application access token as part of constructor.
     *
     * @param bank_uid the bank to do authentication with.
     */
    public PispFlowOpenBankingUK(String bank_uid){
        this.bank_uid = bank_uid;
        this.loadConfigurations(bank_uid);

        PispInternalResponse applicationTokenResponse = accessTokenManagementDAO.getLastApplicationToken(bank_uid);

        if (applicationTokenResponse.isOperationSuccessful()) {
            AccessToken token = (AccessToken) applicationTokenResponse.getData();
            if (token.isExpired()) {
                log.info("Expired Application Token. Requesting new token....");
                retrieveAndSaveApplicationToken();
            } else {
                authorizationString = Constants.AUTHORIZATION_BEARER_HEADER + token.getPrimaryAccessToken();
            }

        } else {
            log.info("No Application Token found in DB. Getting new token now...");
            retrieveAndSaveApplicationToken();
        }

    }
     /*
    ==================================================================================
    Following methods are specific for invocation Payment Initiation resource of bank
    ==================================================================================
    */


    /**
     * Invoke the payment Initiation API resource of the bank
     * Request paymentInitiationID from bank APIs.
     *
     * @return The paymnet Initiation ID from bank.
     */
    @Override
    public String getPaymentInitiationId(PaymentInitiationRequest paymentdata) {
        HttpPost paymentInitiation = new HttpPost(paymentInitiationsURL);

        paymentInitiation.setHeader(Constants.CONTENT_TYPE_HEADER, Constants.CONTENT_TYPE);
        paymentInitiation.setHeader(Constants.AUTHORIZATION_HEADER, authorizationString);
        paymentInitiation.setHeader(Constants.X_FAPI_FINANCIAL_ID, xFAPIFinancialID);
        paymentInitiation.setHeader(Constants.X_IDEMPOTENCY_KEY, generatexidempotencykey());

        StringEntity bodyEntity = new StringEntity(getPaymentInitiationRequestBody(paymentdata), StandardCharsets.UTF_8);
        paymentInitiation.setEntity(bodyEntity);

        HTTPResponse response = Utilities.getHttpPostResponse(paymentInitiation, "Payment Initiation URL");

        String paymentInitiationPostResponse = response.getResponse();

        log.info("Returned for payment Initiation Request: " + paymentInitiationPostResponse);

        if (paymentInitiationPostResponse != null) {
            try {
                JSONObject paymentInitiationResponseJson = new JSONObject(paymentInitiationPostResponse);
                String id = (String) paymentInitiationResponseJson
                        .getJSONObject("Data").get("PaymentId");
                savePaymentInitiationID(id, username);
                return id;
            } catch (JSONException j) {
                log.error(ErrorMessages.ACCOUNT_INITIATION_FAILED, j);
                throw new PispException(ErrorMessages.ACCOUNT_INITIATION_FAILED);
            }
        } else {
            throw new PispException(ErrorMessages.ACCOUNT_INITIATION_FAILED);
        }
    }

    /**
     * generate a random variable as the xidempotencykey
     * Unique request identifier to support idempotency of a POST request
     * @return
     */
    public String generatexidempotencykey(){
        Random rand = new Random();

        int number = rand.nextInt(100000000) + 1;
        return String.valueOf(number);

    }

    /**
     * Generate the Claims Body required for payment-Initiation-Request call.
     * @return The claims body.
     * @throws PispException If generation fails.
     */

    //need to chnage this request body as per the relevant paymentInitiationRequest
    private String getPaymentInitiationRequestBody(PaymentInitiationRequest paymentdata) throws PispException {
        Path path = FileSystems.getDefault().getPath("banks/" + paymentdata.getCustomerBank().getIdentification() + "/account-request-body.json");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        Calendar c = Calendar.getInstance();

        try (InputStream input = PispFlowOpenBankingUK.class.getClassLoader()
                .getResourceAsStream(path.toString())) {

            String text = IOUtils.toString(input, StandardCharsets.UTF_8);
            JSONObject initiationRequestBody = new JSONObject(text);

            initiationRequestBody.getJSONObject("Data").put("TransactionToDateTime", df.format(c.getTime()));

            c.add(Calendar.MONTH, 6);
            initiationRequestBody.getJSONObject("Data").put("ExpirationDateTime", df.format(c.getTime()));

            c.add(Calendar.MONTH, -18);
            initiationRequestBody.getJSONObject("Data").put("TransactionFromDateTime", df.format(c.getTime()));

            return initiationRequestBody.toString();

        } catch (IOException e) {
            log.error("Error while reading request object", e);
            throw new PispException("Error while reading request object");
        }
    }

    /**
     * save payment initiation Id to the database
     *
     * @param accountInitiationId
     * @param username
     */
    @Override
    public void savePaymentInitiationID(String accountInitiationId, String username) {
        accessTokenManagementDAO.saveInitiationIds(username, bankID, accountInitiationId);
        log.info("Initiation ID saved to DB");
    }


    /**
     * Load the attributes needed for API invocations from properties file.
     *
     * @param bankUid the bankId to generate configurations.
     * @throws PispException If configuration reading errs.
     */
    @Override
    public void loadConfigurations(String bankUid) throws PispException {
        Properties prop = new Properties();
        //read the file from the default file system of the machine,I suppose
        Path fileDirectory = FileSystems.getDefault().getPath("banks/" + bankUid + "/" + bankUid + ".properties");
        try (InputStream input = this.getClass().getClassLoader()
                .getResourceAsStream(fileDirectory.toString())) {

            prop.load(input);

            clientID = prop.getProperty("clientID");
            redirectURL = prop.getProperty("redirect_uri");
            xFAPIInteractionIID = prop.getProperty("xFapiInteractionIid");
            xFAPIFinancialID = prop.getProperty("xFapiFinancialId");

            paymentInitiationsURL = prop.getProperty("accountInitiationsURL");
            tokenApiURL = prop.getProperty("tokenAPIURL");
            authorizeApiURL = prop.getProperty("authorizeAPIURL");

            keyStorePassword = prop.getProperty("keyStorePassword").toCharArray();
            keyStoreDomainName = prop.getProperty("keyStoreDomainName");
            keyStorePath = FileSystems.getDefault().getPath("banks/" + bankUid + "/" +
                    prop.getProperty("keyStoreName")).toString();

            if (prop.containsKey("audience")) {
                audienceValue = prop.getProperty("audience");
            } else {
                log.warn("Dedicated Audience value not found. Using Token URL instead. " +
                        "Please add an audience value to properties file");
                audienceValue = prop.getProperty("tokenAPIURL");
            }

            clientAssertionType = prop.getProperty("clientAssertionType");


        } catch (IOException | NullPointerException ex) {
            log.error(ErrorMessages.PROPERTIES_FILE_ERROR, ex);
            throw new PispException(ErrorMessages.PROPERTIES_FILE_ERROR);
        }
    }




    /*
    =======================================================================================
    Rest of the methods are specific for OpenBanking UK and Assertion Authentication
    =======================================================================================
    */

    /**
     * Get the Application Token of the user.
     */
    private void retrieveAndSaveApplicationToken() throws PispException {
        HttpPost tokenApiPostReq = new HttpPost(tokenApiURL);

        String assertionKey = getKey();

        String body2 = "grant_type=" + Constants.GRANT_TYPE_CLIENT + "&" +
                "scope=" + Constants.ACCOUNTS_SCOPE + "&" +
                "redirect_uri=" + redirectURL + "&" +
                "client_id=" + clientID + "&" +
                "client_assertion_type=" + clientAssertionType + "&" +
                "client_assertion=" + assertionKey;

        StringEntity bodyEntity = new StringEntity(body2, StandardCharsets.UTF_8);
        tokenApiPostReq.setHeader(Constants.CONTENT_TYPE_HEADER, Constants.TOKEN_API_CONTENT_TYPE);

        tokenApiPostReq.setEntity(bodyEntity);

        HTTPResponse response = Utilities.getHttpPostResponse(tokenApiPostReq, "Application Token");
        String tokenApiResponse = response.getResponse();

        if (response.getStatusCode() != 200) {
            if (response.getStatusCode() == 401) {
                throw new PispException(ErrorMessages.APPLICATION_TOKEN_EXPIRED);
            }
        }

        log.info("Returned for Application Token: " + tokenApiResponse);

        if (tokenApiResponse != null) {
            JSONObject tokenApiResponseJson = new JSONObject(tokenApiResponse);
            String applicationToken;
            try {
                applicationToken = (String) tokenApiResponseJson.get("access_token");
                authorizationString = Constants.AUTHORIZATION_BEARER_HEADER + applicationToken;

                Long timeBoundary = 3153600000L; // Seconds in a year. To check if validity of token is set to infinity
                Long expiresIn = tokenApiResponseJson.getLong("expires_in");

                Date expiryDate;
                if (expiresIn > timeBoundary) {
                    // Setting expiry to 100 years from now
                    expiryDate = new Date(System.currentTimeMillis() + (timeBoundary * 1000));
                } else {
                    expiryDate = new Date(
                            System.currentTimeMillis() + (tokenApiResponseJson.getLong("expires_in") * 1000));
                }

                accessTokenManagementDAO.saveApplicationToken(bank_uid, applicationToken, expiryDate);
            } catch (JSONException e) {
                log.error("Error: Application Token Missing. Check validity of parameters", e);
                throw new PispException("Application Token retrieval failed");
            }
        } else {
            throw new PispException("Application Token retrieval failed");
        }
    }


    /*
    =================================================================
    Rest of the methods are specific for Assertion Authentication
    =================================================================
    */

    /**
     * Generate signed claims set to be used in application authorization process.
     *
     * @return Signed Claim Set.
     */
    private String getKey() {
        return signJWTWithRSA(createClientAssertionClaimSet());
    }

    /**
     * Create claims set for application authorization process.
     *
     * @return The ClaimsSet created.
     */
    private JWTClaimsSet createClientAssertionClaimSet() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 6);

        JWTClaimsSet.Builder claimsSet = new JWTClaimsSet.Builder();
        claimsSet.issuer(clientID);
        claimsSet.subject(clientID);
        claimsSet.expirationTime(c.getTime());
        claimsSet.issueTime(new Date());
        claimsSet.jwtID(Long.toString(System.currentTimeMillis()));
        claimsSet.audience(audienceValue);

        return claimsSet.build();
    }

    /**
     * Sign a JWT claim with RSA algorithm.
     *
     * @param jwtClaimsSet The claim set to sign.
     * @return Signed claim set in string.
     * @throws PispException If signing errors.
     */
    private String signJWTWithRSA(JWTClaimsSet jwtClaimsSet) throws PispException {
        try {
            Key privateKey = getPrivateKey(keyStorePassword, keyStoreDomainName, keyStorePath);

            JWSSigner signer = new RSASSASigner((RSAPrivateKey) privateKey);
            JWSHeader jwsHeader = new JWSHeader.Builder(signatureAlgorithm).keyID(getThumbPrint()).build();
            SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
            signedJWT.sign(signer);
            String key = signedJWT.serialize();
            if (log.isDebugEnabled()) {
                log.info("Signed key is: " + key);
            }
            return key;
        } catch (JOSEException e) {
            log.error(ErrorMessages.KEY_SIGNING_ERROR, e);
            throw new PispException(ErrorMessages.KEY_SIGNING_ERROR);
        }
    }
    /**
     * Get the thumbprint for the certificate.
     *
     * @return The thumbprint for the certificate.
     * @throws PispException If thumb print creation errors.
     */
    private String getThumbPrint() throws PispException {
        Certificate certificate = this.certificate;

        MessageDigest digestValue;
        try {
            digestValue = MessageDigest.getInstance(Constants.SIGNING_ALGORITHM);
            byte[] der = certificate.getEncoded();
            digestValue.update(der);
            byte[] digestInBytes = digestValue.digest();

            String publicCertThumbprint = hexify(digestInBytes);
            if (log.isDebugEnabled()) {
                log.info("Thumb print is: " + publicCertThumbprint);
            }
            return publicCertThumbprint;
        } catch (NoSuchAlgorithmException | CertificateEncodingException e) {
            log.error(ErrorMessages.THUMBPRINT_ERROR, e);
            throw new PispException(ErrorMessages.THUMBPRINT_ERROR);
        }
    }


    /**
     * Get the private key of the application host key store.
     *
     * @param password The password for accessing key store.
     * @param domain   The domain of the key store.
     * @param path     The name of the keystore.
     * @return The private key.
     * @throws PispException If key was not read properly.
     */
    private Key getPrivateKey(char[] password, String domain, String path) throws PispException {
        try (FileInputStream fis =
                     new FileInputStream(this.getClass().getClassLoader().getResource(path).getFile())) {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(fis, password);

            KeyStore.PrivateKeyEntry pkEntry =
                    (KeyStore.PrivateKeyEntry) ks.getEntry(domain, new KeyStore.PasswordProtection(password));
            certificate = pkEntry.getCertificate();
            return pkEntry.getPrivateKey();
        } catch (KeyStoreException | IOException | CertificateException |
                UnrecoverableEntryException | NoSuchAlgorithmException | NullPointerException e) {
            log.error(ErrorMessages.PRIVATE_KEY_ERROR, e);
            throw new PispException(ErrorMessages.PRIVATE_KEY_ERROR);
        }

    }
    private String hexify(byte[] bytes) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (byte aByte : bytes) {
            buf.append(hexDigits[(aByte & 240) >> 4]);
            buf.append(hexDigits[aByte & 15]);
        }

        return buf.toString();
    }


































}
