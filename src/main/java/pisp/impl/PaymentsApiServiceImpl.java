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

package pisp.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pisp.*;
import pisp.PispFlow.impl.PispFlowOpenBankingUK;
import pisp.dto.*;


import pisp.dto.PaymentInitResponseDTO;
import pisp.dto.PaymentInitRequestDTO;
import pisp.dto.DeborBankAccountDTO;
import pisp.dto.BankSelectionResponseDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import pisp.exception.InvalidPayloadException;
import pisp.exception.PispException;
import pisp.mappings.BankAccountMapping;
import pisp.mappings.PaymentInitiationRequestMapping;
import pisp.models.PaymentInitiationRequest;
import pisp.models.PaymentInitiationResponse;
import pisp.services.PaymentManagementService;
import pisp.utilities.constants.ErrorMessages;

import javax.ws.rs.core.Response;

public class PaymentsApiServiceImpl extends PaymentsApiService {

    private Log log = LogFactory.getLog(PaymentsApiServiceImpl.class);

    @Override
    public Response getPaymentInitRequestById(String contentType,String paymentInitReqId,String authorization){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response reqPaymentInitiation(String contentType,String clientId,String purchaseId,String authorization,PaymentInitRequestDTO body){
        //payload validation
        log.info("Received a payment initiation request from "+ body.getEShopUsername());

        if (clientId == null) {
            throw new InvalidPayloadException("Client ID is required to request a payment initiation.");
        }
        if (purchaseId == null) {
            throw new InvalidPayloadException("Purchase ID issued by Ecommerce site is required to request a payment initiation.");
        }
        //if (authorization == null) {
        //throw new InvalidPayloadException("Access token is required to request a payment initiation.");
        //}

        //validate access token and client)id
        //validation request goes here


        PaymentInitiationRequest paymentInitiationRequest= PaymentInitiationRequestMapping.createpaymentInitiationrequestInstance(body);

        if(paymentInitiationRequest.isError()){
            //return error when fails to map data in Http request to internal model
            return  Response.serverError().entity(paymentInitiationRequest.getErrorMessage()).build();
        }else{
            PaymentManagementService paymentManagementService=new PaymentManagementService(paymentInitiationRequest);
            PaymentInitiationResponse responseBody=paymentManagementService.getPaymentInitiationResponse();

            if (responseBody.isError()) {
                return  Response.serverError().entity(responseBody.getErrorMessage()).build();
            }
            PaymentInitResponseDTO responseBodyDTO=PaymentInitiationRequestMapping.getpaymentInitiationResponseDTO(responseBody);
            return Response.ok().header("Content_Type", "application/json")
                    .entity(responseBodyDTO).build();
        }

    }
    @Override
    public Response selectBank(String contentType,String paymentInitReqId,DeborBankAccountDTO body){
        log.info("The customer has selected the debtor bank as "+body.getDebtorBank().getBankName());

        PaymentManagementService paymentManagementService=new PaymentManagementService();
        if(paymentManagementService.retrievePaymentInitiationData(paymentInitReqId)){
            paymentManagementService.updatePaymentDebtorAccountData(body.getDebtorBank().getBankUid(),BankAccountMapping.createBankAccountInstance(body.getDebtorAccount()));
            paymentManagementService.processPaymentInitiationWithBank();//get the response as the output which contain the redirec url to authorize endpoint
        }else{
            return  Response.serverError().entity(ErrorMessages.ERROR_WHILE_RETRIEVING_PAYMENT_INITIATION).build();
        }


        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
