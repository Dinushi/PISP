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

import pisp.*;
import pisp.dto.*;


import pisp.dto.EShopRegistrationResponseDTO;
import pisp.dto.EShopProfileDTO;
import pisp.dto.LoginCredentialsDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import pisp.exception.PispException;
import pisp.mappings.EshopMapping;
import pisp.models.E_shop;
import pisp.models.E_shopRegResponse;
import pisp.services.EShopManagementService;

import javax.ws.rs.core.Response;

public class EShopApiServiceImpl extends EShopApiService {

    @Override
    public Response addNewEshop(String contentType,EShopProfileDTO body){
        E_shop new_e_shop= EshopMapping.createEshopInstance(body);

        EShopManagementService eShopManagementService=new EShopManagementService();

        if(eShopManagementService.validateNewEshop(new_e_shop)){
            boolean registration_result=eShopManagementService.registerNewEshop(new_e_shop);
            if(registration_result){
                //return the response as registered
                E_shopRegResponse responseBody=eShopManagementService.getEshopRegistrationResponse(new_e_shop.getUsername());

                if (responseBody.isError()) {
                    throw new PispException(responseBody.getErrorMessage());
                }
                EShopRegistrationResponseDTO responseBodyDTO=EshopMapping.getEShopRegistrationResponseDTO(responseBody);

                return Response.ok().header("Content_Type", "application/json")
                        .header("Set-Cookie","ffhdgrgiu")
                        .entity(responseBodyDTO).build();
            }
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteEshop(String username,String cookie){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response eshopLogin(String contentType,LoginCredentialsDTO body){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getEshopProfile(String username,String cookie){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateProfile(String username,String contentType,String cookie,EShopProfileDTO body){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
