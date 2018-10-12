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

import pisp.dao.EshopManagementDAO;
import pisp.exception.InvalidPayloadException;
import pisp.exception.PispException;
import pisp.models.E_shop;
import pisp.models.E_shopRegResponse;

public class EShopManagementService {

    EshopManagementDAO eshopManagementDAO;

    public EShopManagementService(){
        this.eshopManagementDAO=new EshopManagementDAO();
    }

    /**
     *
     * @param e_shop
     * @return result after validating the data entered by new e-shop user for the registration
     */
    public boolean validateNewEshop(E_shop e_shop) {

        //validate the e-shop user for a unique username
        try {
            if(eshopManagementDAO.validateUsername(e_shop.getUsername())){
                //validate the remaining data sent by e-shop\
                return true;
            }else{
                //echo that the username is already allocated
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void generateClientCredentialsForEshop(){


    }


    public boolean registerNewEshop(E_shop e_shop){
        return this.eshopManagementDAO.registerEshop(e_shop);

    }

    //find a way ghow to genearte the response back with decided response format/data


    public E_shopRegResponse getEshopRegistrationResponse(String username)
            throws PispException {

        // Payload validation.
        if (username == null) {
            throw new InvalidPayloadException("E-shop username is required to get E-shop details.");
        }

        E_shop e_shop=eshopManagementDAO.retriveE_Shop(username);
        E_shopRegResponse e_shopRegResponse=new E_shopRegResponse();
        if(e_shop==null){
            e_shopRegResponse.setErrorStatus(true);
            e_shopRegResponse.setErrorMessage("The user registration has failed");

        }
        e_shopRegResponse.setE_shopUsername(e_shop.getEShopUsername());
        e_shopRegResponse.setClient_id(e_shop.getClient_id());
        e_shopRegResponse.setClient_secreat(e_shop.getClient_secreat());
        return e_shopRegResponse;
    }
}
