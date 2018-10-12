package pisp;

import pisp.*;
import pisp.dto.*;

import pisp.dto.LoginCredentialsDTO;
import pisp.dto.AdminUserDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class AdminUserApiService {
    public abstract Response loginAdminUser(String contentType,LoginCredentialsDTO body);
    public abstract Response updateAdminUser(String username,String contentType,String cookie,AdminUserDTO body);
}

