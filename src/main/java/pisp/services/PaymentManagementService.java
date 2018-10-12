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

package pisp.services;

import pisp.PispFlow.PaymentMediator;
import pisp.PispFlow.PispFlow;
import pisp.dao.BankManagementDAO;
import pisp.dao.PaymentManagementDAO;
import pisp.exception.PispException;
import pisp.models.Bank;
import pisp.models.BankAccount;
import pisp.models.PaymentInitiationRequest;
import pisp.models.PaymentInitiationResponse;

import pisp.utilities.constants.ErrorMessages;

public class PaymentManagementService {

    private PaymentInitiationRequest paymentInitiationRequest;
    private boolean error;
    private String errorMessage;

    public PaymentManagementService (){
        this.error=false;
    }

    public PaymentManagementService(PaymentInitiationRequest paymentInitiationRequest){
        this.error=false;
        this.paymentInitiationRequest=paymentInitiationRequest;
        generateUniquePaymentId();
        storePaymentDataInDB();
    }


    /*
    =========================================================================================
    This section responsible of responding to a payment initiation request to the application
    =========================================================================================
    */

    public void generateUniquePaymentId(){
        //use some algo to generate a unique id each time
        this.paymentInitiationRequest.setPaymentInitReqId("werr647586885");
    }

    public void storePaymentDataInDB() {
        this.paymentInitiationRequest.setPaymentStatus("Initiated");
        PaymentManagementDAO paymentManagementDAO=new PaymentManagementDAO();
        boolean result=paymentManagementDAO.addPaymentInitiation(paymentInitiationRequest);
        if(!result) {
            this.error = true;
            this.errorMessage= ErrorMessages.DB_SAVING_ERROR;
        }

    }
    public PaymentInitiationResponse getPaymentInitiationResponse() throws PispException {
        //Here request data is taken from db and response is generated using the data from db

        retrievePaymentInitiationData(this.paymentInitiationRequest.getPaymentInitReqId());
        PaymentInitiationResponse paymentInitiationResponse=new PaymentInitiationResponse();

        if(this.error || this.paymentInitiationRequest==null){
            paymentInitiationResponse.setErrorStatus(true);
            paymentInitiationResponse.setErrorMessage(ErrorMessages.ERROR_PAYMENT_REQUEST_NOT_PROCESSED);
        }else{
            paymentInitiationResponse.setEShopUsername(paymentInitiationRequest.getEShopUsername());
            //complete the rest of response body(getters and setters)
        }
        return paymentInitiationResponse;
    }


    public boolean retrievePaymentInitiationData(String paymentInitReqId) {
        PaymentManagementDAO paymentManagementDAO=new PaymentManagementDAO();
        this.paymentInitiationRequest=paymentManagementDAO.retrivePayment(paymentInitReqId);
        if(this.paymentInitiationRequest==null){
            return false;
        }
        return true;
    }


    /*
    =================================================================================================================================
    This section handles the bank selection by customer and processing the payment to invoke the payment initiation endpoint of bank
    =================================================================================================================================
    */

    public boolean updatePaymentDebtorAccountData(String bank_uid, BankAccount customerBankAccount) {

        this.paymentInitiationRequest.setCustomerBank(retrieveCustomerBankById(bank_uid));;
        this.paymentInitiationRequest.setCustomerBankAccount(customerBankAccount);
        this.paymentInitiationRequest.setPaymentStatus("DebtorBankSpecified");
        PaymentManagementDAO paymentManagementDAO=new PaymentManagementDAO();
        paymentManagementDAO.updatePaymentInitiation(this.paymentInitiationRequest);
        return true;

    }

    public Bank retrieveCustomerBankById(String bankId){
        BankManagementDAO bankManagementDAO=new BankManagementDAO();
        return bankManagementDAO.retriveBankInfo(bankId);

    }

    public void processPaymentInitiationWithBank(){
        PaymentMediator paymentMediator=new PaymentMediator();
        paymentMediator.selectPispFlow(this.paymentInitiationRequest.getCustomerBank());
        //need a proper way to handle these payment initiation requsts and sunbmissions with banks
    }





}
