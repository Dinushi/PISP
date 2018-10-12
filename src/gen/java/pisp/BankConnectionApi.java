package pisp;

import pisp.dto.*;
import pisp.BankConnectionApiService;
import pisp.factories.BankConnectionApiServiceFactory;

import io.swagger.annotations.ApiParam;

import pisp.dto.BankInfoDTO;
import pisp.dto.Bank1DTO;
import pisp.dto.BankDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/bank-connection")


@io.swagger.annotations.Api(value = "/bank-connection", description = "the bank-connection API")
public class BankConnectionApi  {

   private final BankConnectionApiService delegate = BankConnectionApiServiceFactory.getBankConnectionApi();

    @POST
    @Path("/")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add connection to a bank API", notes = "The PISP will register as a TPP in ASPSP and admins will add required details to the PISP system to connect with the bank exposed APIs", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Authentication failed"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Invalid data supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Some required body parameters are missing"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Unexpected error in server") })

    public Response addBank(@ApiParam(value = "Chosen content type" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Content-Type") String contentType,
    @ApiParam(value = "The session id is set in the cookie" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Cookie") String cookie,
    @ApiParam(value = "Information relevent to the bank" ,required=true ) BankInfoDTO body)
    {
    return delegate.addBank(contentType,cookie,body);
    }
    @GET
    @Path("/")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "get list of banks supported by PISP", notes = "", response = Bank1DTO.class, responseContainer = "List")
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully retrieved the payment history"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Required parameter missing"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Username not found") })

    public Response getListOfBanks(@ApiParam(value = "The session id is set in the cookie" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Cookie") String cookie)
    {
    return delegate.getListOfBanks(cookie);
    }
    @DELETE
    @Path("/")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Remove the bank from pisp service", notes = "This to pisp admins to remove a bank from its supported banks list.", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The bank successfully removed"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Required parameters missing"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Authentication failed"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Username not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Unexpected error in server") })

    public Response removeBank(@ApiParam(value = "Chosen content type" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Content-Type") String contentType,
    @ApiParam(value = "The session id is set in the cookie" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Cookie") String cookie,
    @ApiParam(value = "Information relevent to the bank" ,required=true ) BankDTO body)
    {
    return delegate.removeBank(contentType,cookie,body);
    }
}

