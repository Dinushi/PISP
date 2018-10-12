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

package pisp;

import pisp.dto.*;
import pisp.PaymentsApiService;
import pisp.factories.PaymentsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import pisp.dto.PaymentInitResponseDTO;
import pisp.dto.PaymentInitRequestDTO;
import pisp.dto.DeborBankAccountDTO;
import pisp.dto.BankSelectionResponseDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/payments")


@io.swagger.annotations.Api(value = "/payments", description = "the payments API")
public class PaymentsApi  {

   private final PaymentsApiService delegate = PaymentsApiServiceFactory.getPaymentsApi();

    @GET
    @Path("/{payment_init_req_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "query about a payment initiation request made ", notes = "Return a single payment-init-request object", response = PaymentInitResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully retrieved the details"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "The requested payment_init_req_id not found ") })

    public Response getPaymentInitRequestById(@ApiParam(value = "Chosen content type" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Content-Type") String contentType,
    @ApiParam(value = "ID of payment init request as sent by PISP in the response for payment initation request",required=true ) @PathParam("payment_init_req_id")  String paymentInitReqId,
    @ApiParam(value = "Bearer Token" ,required=true )@HeaderParam("Authorization") String authorization)
    {
    return delegate.getPaymentInitRequestById(contentType,paymentInitReqId,authorization);
    }
    @POST
    @Path("/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Make a new payment initiation request", notes = "The ecommerce site can make a payment initiation request onbehalf of customer", response = PaymentInitResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Payment Initiation Request setup successful"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Required parameters missing"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Authentication failed"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Ecommerce user not found"),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "unexpected error in the server") })

    public Response reqPaymentInitiation(@ApiParam(value = "Chosen content type" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Content-Type") String contentType,
    @ApiParam(value = "The unique id assigned to e-shop by PISPduring the registration." ,required=true )@HeaderParam("Client_Id") String clientId,
    @ApiParam(value = "The unique id assigned by the ecommerce site/app to uniquely identify a purchase which a payment will be proceeded." ,required=true )@HeaderParam("purchase_id") String purchaseId,
    @ApiParam(value = "Bearer Token" ,required=true )@HeaderParam("Authorization") String authorization,
    @ApiParam(value = "created payment initiation request object" ,required=true ) PaymentInitRequestDTO body)
    {
    return delegate.reqPaymentInitiation(contentType,clientId,purchaseId,authorization,body);
    }
    @POST
    @Path("/debtor-bank-account")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "The customer selects a bank as debtor agent", notes = "The customer chooses his debtor account bank once he is redirected to pisp", response = BankSelectionResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Authentication failed"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Invalid data supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Some required body parameters are missing"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Unexpected error in server") })

    public Response selectBank(@ApiParam(value = "Chosen content type" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Content-Type") String contentType,
    @ApiParam(value = "The payment_init_req_id which uniquely identify the payment resource relevent to the customer" ,required=true )@HeaderParam("Payment_init_req_id") String paymentInitReqId,
    @ApiParam(value = "Information relevent to the bank selected by customer" ,required=true ) DeborBankAccountDTO body)
    {
    return delegate.selectBank(contentType,paymentInitReqId,body);
    }
}

