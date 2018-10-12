package pisp.mappings;

import pisp.models.E_shop;
import pisp.models.E_shopRegResponse;
import pisp.dto.EShopProfileDTO;
import pisp.dto.EShopRegistrationResponseDTO;

public class EshopMapping {

    /**
     *
     * @param eshopProfileBody
     * @return E_shop instance which is mapped to the request body of EShopProfileDTO
     */

    public static E_shop createEshopInstance(EShopProfileDTO eshopProfileBody){
        if (eshopProfileBody == null) {
            return null;
        }

        E_shop e_shop=new E_shop();

        e_shop.setEShopUsername(eshopProfileBody.getUsername());
        e_shop.setEShopRegistrationNo(eshopProfileBody.getEShopRegistrationNo());
        e_shop.setRegisteredBussinessName(eshopProfileBody.getRegisteredBussinessName());
        e_shop.setRegisteredAuthority(eshopProfileBody.getRegisteredAuthority());
        e_shop.setRegisteredCountry(eshopProfileBody.getRegisteredCountry());
        e_shop.setEcommerceMarketplaceCategory(eshopProfileBody.getEcommerceMarketplaceCategory().toString());
        e_shop.setEmail(eshopProfileBody.getEmail());
        e_shop.setPassword(eshopProfileBody.getPassword());

        if(eshopProfileBody.getEcommerceMarketplaceCategory().toString().equals("single_vendor")){
            e_shop.setMerchantCategoryCode(eshopProfileBody.getMerchantCategoryCode());
            e_shop.setMerchantBank(BankMapping.createBankInstance(eshopProfileBody.getMerchantBank()));
            e_shop.setMerchantBankAccountData(BankAccountMapping.createBankAccountInstance(eshopProfileBody.getMerchantBankAccountData()));
        }

        return e_shop;
    }

    public static EShopRegistrationResponseDTO getEShopRegistrationResponseDTO(E_shopRegResponse e_shopRegResponse){
        EShopRegistrationResponseDTO eShopRegistrationResponseDTO=new EShopRegistrationResponseDTO();
        //need to change with spec change
        eShopRegistrationResponseDTO.setEShopUsername(e_shopRegResponse.getE_shopUsername());
        eShopRegistrationResponseDTO.setCliendId("hjgrgh7568347");
        eShopRegistrationResponseDTO.setClientSecreat("8t58ghrtjg89uyhj");
        return  eShopRegistrationResponseDTO;

    }

}