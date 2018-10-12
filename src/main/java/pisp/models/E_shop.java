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

package pisp.models;


public class E_shop {


    private String eShopUsername;
    private String eShopRegistrationNo;
    private String registeredBussinessName;
    private String registeredCountry;
    private String registeredAuthority;
    private String username;
    private String password;
    private String email;
    private String redirectLink;
    private String ecommerceMarketplaceCategory;

    private String client_id=null;
    private String client_secreat=null;

    private String merchantCategoryCode;
    private String merchantProductType;
    private Bank merchantBank;
    private BankAccount merchantBankAccountData;





    public String getEShopUsername() {
        return eShopUsername;
    }

    public void setEShopUsername(String eShopUsername) {
        this.eShopUsername = eShopUsername;
    }

    public String getEShopRegistrationNo() {
        return eShopRegistrationNo;
    }

    public void setEShopRegistrationNo(String eShopRegistrationNo) {
        this.eShopRegistrationNo = eShopRegistrationNo;
    }

    public String getRegisteredBussinessName() {
        return registeredBussinessName;
    }

    public void setRegisteredBussinessName(String registeredBussinessName) {
        this.registeredBussinessName = registeredBussinessName;
    }

    public String getRegisteredCountry() {
        return registeredCountry;
    }

    public void setRegisteredCountry(String registeredCountry) {
        this.registeredCountry = registeredCountry;
    }

    public String getRegisteredAuthority() {
        return registeredAuthority;
    }

    public void setRegisteredAuthority(String registeredAuthority) {
        this.registeredAuthority = registeredAuthority;
    }

    public String getEcommerceMarketplaceCategory() {
        return ecommerceMarketplaceCategory;
    }

    public void setEcommerceMarketplaceCategory(String ecommerceMarketplaceCategory) {
        this.ecommerceMarketplaceCategory = ecommerceMarketplaceCategory;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public String getMerchantProductType() {
        return merchantProductType;
    }

    public void setMerchantProductType(String merchantProductType) {
        this.merchantProductType = merchantProductType;
    }

    public Bank getMerchantBank() {
        return merchantBank;
    }

    public void setMerchantBank(Bank merchantBank) {
        this.merchantBank = merchantBank;
    }

    public BankAccount getMerchantBankAccountData() {
        return merchantBankAccountData;
    }

    public void setMerchantBankAccountData(BankAccount merchantBankAccountData) {
        this.merchantBankAccountData = merchantBankAccountData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public void setRedirectLink(String redirectLink) {
        this.redirectLink = redirectLink;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secreat() {
        return client_secreat;
    }

    public void setClient_secreat(String client_secreat) {
        this.client_secreat = client_secreat;
    }
}
