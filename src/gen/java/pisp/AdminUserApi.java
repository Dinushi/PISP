package pisp;

import pisp.dto.*;
import pisp.AdminUserApiService;
import pisp.factories.AdminUserApiServiceFactory;

import io.swagger.annotations.ApiParam;

import pisp.dto.LoginCredentialsDTO;
import pisp.dto.AdminUserDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/admin-user")


@io.swagger.annotations.Api(value = "/admin-user", description = "the admin-user API")
public class AdminUserApi  {

   private final AdminUserApiService delegate = AdminUserApiServiceFactory.getAdminUserApi();

    @POST
    @Path("/login")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Log admin user to the PISP system", notes = "", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Admin User login successful"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Required parameters missing"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Invalid password supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Username not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Unexpected error in server") })

    public Response loginAdminUser(@ApiParam(value = "Chosen content type" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Content-Type") String contentType,
    @ApiParam(value = "Created admin user object" ,required=true ) LoginCredentialsDTO body)
    {
    return delegate.loginAdminUser(contentType,body);
    }
    @PUT
    @Path("/{username}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update ecommerce user details", notes = "This is to pisp admin to update his credentials.", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Admin User successfully updated"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Required parameters missing"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Authentication failed"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Username not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Unexpected error in server") })

    public Response updateAdminUser(@ApiParam(value = "username of the admin user that need to be updated",required=true ) @PathParam("username")  String username,
    @ApiParam(value = "Chosen content type" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Content-Type") String contentType,
    @ApiParam(value = "The session id is set in the cookie" ,required=true , allowableValues="{values=[application/json]}")@HeaderParam("Cookie") String cookie,
    @ApiParam(value = "Updated user object" ,required=true ) AdminUserDTO body)
    {
    return delegate.updateAdminUser(username,contentType,cookie,body);
    }
}

