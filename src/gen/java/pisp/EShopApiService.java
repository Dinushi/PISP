package pisp;

import pisp.*;
import pisp.dto.*;

import pisp.dto.EShopRegistrationResponseDTO;
import pisp.dto.EShopProfileDTO;
import pisp.dto.LoginCredentialsDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class EShopApiService {
    public abstract Response addNewEshop(String contentType,EShopProfileDTO body);
    public abstract Response deleteEshop(String username,String cookie);
    public abstract Response eshopLogin(String contentType,LoginCredentialsDTO body);
    public abstract Response getEshopProfile(String username,String cookie);
    public abstract Response updateProfile(String username,String contentType,String cookie,EShopProfileDTO body);
}

