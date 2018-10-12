package pisp.mappings;

import pisp.models.PaymentInitiationRequest;
import pisp.models.PaymentInitiationResponse;
import pisp.dto.PaymentInitRequestDTO;
import pisp.dto.PaymentInitResponseDTO;

public class PaymentInitiationRequestMapping {


    public static PaymentInitiationRequest createpaymentInitiationrequestInstance(PaymentInitRequestDTO paymentInitRequestDTO){
        if (paymentInitRequestDTO == null) {
            return null;
        }

        //PaymentInitiationRequest paymentInitiationRequest=new PaymentInitiationRequest();


        PaymentInitiationRequest paymentInitiationRequest=validatePaymentInitiationrequestBody(paymentInitRequestDTO);
        ////set purchase_id and client_id
        if(paymentInitiationRequest.isError()){
            return paymentInitiationRequest;

        }else{
            paymentInitiationRequest.setEShopUsername(paymentInitiationRequest.getEShopUsername());
            paymentInitiationRequest.setMerchantName(paymentInitiationRequest.getMerchantName());
            paymentInitiationRequest.setInstructedAmountCurrency(paymentInitiationRequest.getInstructedAmountCurrency());
            paymentInitiationRequest.setInstructedAmount(paymentInitiationRequest.getInstructedAmount());
            paymentInitiationRequest.setMerchantName(paymentInitiationRequest.getMerchantName());
            paymentInitiationRequest.setMerchantEndToEndidentification(paymentInitiationRequest.getMerchantEndToEndidentification());
            paymentInitiationRequest.setMerchantCategoryCodeOfMerchant(paymentInitiationRequest.getMerchantCategoryCodeOfMerchant());
            paymentInitiationRequest.setMerchantBank(paymentInitiationRequest.getMerchantBank());
            paymentInitiationRequest.setMerchantBankAccount(paymentInitiationRequest.getMerchantBankAccount());
            paymentInitiationRequest.setCustomerIdentification(paymentInitiationRequest.getCustomerIdentification());
            paymentInitiationRequest.setItemsPurchased(paymentInitiationRequest.getItemsPurchased());
            paymentInitiationRequest.setDeliveryAddress(paymentInitiationRequest.getDeliveryAddress());
            paymentInitiationRequest.setRedirectURI(paymentInitiationRequest.getRedirectURI());
            //complete the rest

            return paymentInitiationRequest;

        }




    }

    /**
     *
     * @param paymentInitRequestDTO
     * @return
     *
     */



    private static PaymentInitiationRequest validatePaymentInitiationrequestBody(PaymentInitRequestDTO paymentInitRequestDTO){

        PaymentInitiationRequest paymentInitiationRequest=new PaymentInitiationRequest();
        if(paymentInitRequestDTO.getInstructedAmount()==null){
            paymentInitiationRequest.setErrorStatus(true);
            paymentInitiationRequest.setErrorMessage("The instructed amount cannot be null");
        }
        if(paymentInitRequestDTO.getMerchantBank()==null) {
            paymentInitiationRequest.setErrorStatus(true);
            paymentInitiationRequest.setErrorMessage("The instructed amount cannot be null");
        }
        return paymentInitiationRequest;

    }

    /**
     *
     * @param paymentInitiationResponse
     * @return
     */
    public static PaymentInitResponseDTO getpaymentInitiationResponseDTO(PaymentInitiationResponse paymentInitiationResponse){
        PaymentInitResponseDTO paymentInitResponseDTO=new PaymentInitResponseDTO();

        paymentInitResponseDTO.setMerchantName(paymentInitiationResponse.getMerchantName());
        //paymentInitResponseDTO.setMerchantBank(paymentInitiationResponse.getMerchantBank());
        paymentInitResponseDTO.setRedirectLink("Https:dfhgfyeg");
        return   paymentInitResponseDTO;

    }


}
