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

package pisp.models;

public class PaymentInitiationResponse {


    private boolean errorStatus = false;
    private String errorMessage = null;

    private String paymentInitReqId = null;


    private String paymentStatus = null;


    private String username = null;


    private String merchantCategoryCodeOfMerchant = null;


    private String endToEndIdentificationOfPayment = null;



    private float instructedAmount = 0;

    private String instructedAmountCurrency;


    private String merchantName = null;

    private Bank merchantBank = null;


    private BankAccount merchantBankAccount = null;

    private String  deliveryAddress = null;
    private String redirectLink = null;

    private String eShopUsername = null;


    private String merchantEndToEndidentification = null;


    public boolean isError() {
        return errorStatus;
    }

    public void setErrorStatus(boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPaymentInitReqId() {
        return paymentInitReqId;
    }

    public void setPaymentInitReqId(String paymentInitReqId) {
        this.paymentInitReqId = paymentInitReqId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMerchantCategoryCodeOfMerchant() {
        return merchantCategoryCodeOfMerchant;
    }

    public void setMerchantCategoryCodeOfMerchant(String merchantCategoryCodeOfMerchant) {
        this.merchantCategoryCodeOfMerchant = merchantCategoryCodeOfMerchant;
    }

    public String getEndToEndIdentificationOfPayment() {
        return endToEndIdentificationOfPayment;
    }

    public void setEndToEndIdentificationOfPayment(String endToEndIdentificationOfPayment) {
        this.endToEndIdentificationOfPayment = endToEndIdentificationOfPayment;
    }

    public float getInstructedAmount() {
        return instructedAmount;
    }

    public void setInstructedAmount(float instructedAmount) {
        this.instructedAmount = instructedAmount;
    }

    public String getInstructedAmountCurrency() {
        return instructedAmountCurrency;
    }

    public void setInstructedAmountCurrency(String instructedAmountCurrency) {
        this.instructedAmountCurrency = instructedAmountCurrency;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Bank getMerchantBank() {
        return merchantBank;
    }

    public void setMerchantBank(Bank merchantBank) {
        this.merchantBank = merchantBank;
    }

    public BankAccount getMerchantBankAccount() {
        return merchantBankAccount;
    }

    public void setMerchantBankAccount(BankAccount merchantBankAccount) {
        this.merchantBankAccount = merchantBankAccount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public void setRedirectLink(String redirectLink) {
        this.redirectLink = redirectLink;
    }

    public String getEShopUsername() {
        return eShopUsername;
    }

    public void setEShopUsername(String eShopUsername) {
        this.eShopUsername = eShopUsername;
    }

    public String getMerchantEndToEndidentification() {
        return merchantEndToEndidentification;
    }

    public void setMerchantEndToEndidentification(String merchantEndToEndidentification) {
        this.merchantEndToEndidentification = merchantEndToEndidentification;
    }
}
