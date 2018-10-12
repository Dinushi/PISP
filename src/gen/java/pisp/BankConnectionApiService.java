package pisp;

import pisp.*;
import pisp.dto.*;

import pisp.dto.BankInfoDTO;
import pisp.dto.Bank1DTO;
import pisp.dto.BankDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class BankConnectionApiService {
    public abstract Response addBank(String contentType,String cookie,BankInfoDTO body);
    public abstract Response getListOfBanks(String cookie);
    public abstract Response removeBank(String contentType,String cookie,BankDTO body);
}

