package pisp.dto;

import pisp.dto.BankAccountDTO;
import pisp.dto.BankDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class EShopProfileDTO  {
  
  
  @NotNull
  private String eShopUsername = null;
  
  @NotNull
  private String eShopRegistrationNo = null;
  
  
  private String registeredBussinessName = null;
  
  @NotNull
  private String registeredCountry = null;
  
  @NotNull
  private String registeredAuthority = null;
  
  public enum EcommerceMarketplaceCategoryEnum {
     single_vendor,  multi_vendor, 
  };
  @NotNull
  private EcommerceMarketplaceCategoryEnum ecommerceMarketplaceCategory = null;
  
  
  private String merchantCategoryCode = null;
  
  
  private String merchantProductType = null;
  
  
  private BankDTO merchantBank = null;
  
  
  private BankAccountDTO merchantBankAccountData = null;
  
  @NotNull
  private String username = null;
  
  @NotNull
  private String password = null;
  
  @NotNull
  private String email = null;
  
  
  private String redirectLink = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("eShopUsername")
  public String getEShopUsername() {
    return eShopUsername;
  }
  public void setEShopUsername(String eShopUsername) {
    this.eShopUsername = eShopUsername;
  }

  
  /**
   * Reg number issued by a authorized institute
   **/
  @ApiModelProperty(required = true, value = "Reg number issued by a authorized institute")
  @JsonProperty("eShopRegistrationNo")
  public String getEShopRegistrationNo() {
    return eShopRegistrationNo;
  }
  public void setEShopRegistrationNo(String eShopRegistrationNo) {
    this.eShopRegistrationNo = eShopRegistrationNo;
  }

  
  /**
   * Name used for registration
   **/
  @ApiModelProperty(value = "Name used for registration")
  @JsonProperty("registeredBussinessName")
  public String getRegisteredBussinessName() {
    return registeredBussinessName;
  }
  public void setRegisteredBussinessName(String registeredBussinessName) {
    this.registeredBussinessName = registeredBussinessName;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("registeredCountry")
  public String getRegisteredCountry() {
    return registeredCountry;
  }
  public void setRegisteredCountry(String registeredCountry) {
    this.registeredCountry = registeredCountry;
  }

  
  /**
   * The name of the registration authority
   **/
  @ApiModelProperty(required = true, value = "The name of the registration authority")
  @JsonProperty("registeredAuthority")
  public String getRegisteredAuthority() {
    return registeredAuthority;
  }
  public void setRegisteredAuthority(String registeredAuthority) {
    this.registeredAuthority = registeredAuthority;
  }

  
  /**
   * Avaialble categories-single-vendor, multi-vendor. Should be selected as single-vendor if the owner of the site is the only merchant itself and multi-vendor if the site is hosting for multiple vendors.
   **/
  @ApiModelProperty(required = true, value = "Avaialble categories-single-vendor, multi-vendor. Should be selected as single-vendor if the owner of the site is the only merchant itself and multi-vendor if the site is hosting for multiple vendors.")
  @JsonProperty("ecommerceMarketplaceCategory")
  public EcommerceMarketplaceCategoryEnum getEcommerceMarketplaceCategory() {
    return ecommerceMarketplaceCategory;
  }
  public void setEcommerceMarketplaceCategory(EcommerceMarketplaceCategoryEnum ecommerceMarketplaceCategory) {
    this.ecommerceMarketplaceCategory = ecommerceMarketplaceCategory;
  }

  
  /**
   * conditional. Only valid for single vendor.Category code conforms to ISO 18245, related to the type of services or goods the single vendor/merchant provides for the transaction
   **/
  @ApiModelProperty(value = "conditional. Only valid for single vendor.Category code conforms to ISO 18245, related to the type of services or goods the single vendor/merchant provides for the transaction")
  @JsonProperty("merchantCategoryCode")
  public String getMerchantCategoryCode() {
    return merchantCategoryCode;
  }
  public void setMerchantCategoryCode(String merchantCategoryCode) {
    this.merchantCategoryCode = merchantCategoryCode;
  }

  
  /**
   * conditional. Only valid for single vendor
   **/
  @ApiModelProperty(value = "conditional. Only valid for single vendor")
  @JsonProperty("merchantProductType")
  public String getMerchantProductType() {
    return merchantProductType;
  }
  public void setMerchantProductType(String merchantProductType) {
    this.merchantProductType = merchantProductType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("merchantBank")
  public BankDTO getMerchantBank() {
    return merchantBank;
  }
  public void setMerchantBank(BankDTO merchantBank) {
    this.merchantBank = merchantBank;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("merchantBankAccountData")
  public BankAccountDTO getMerchantBankAccountData() {
    return merchantBankAccountData;
  }
  public void setMerchantBankAccountData(BankAccountDTO merchantBankAccountData) {
    this.merchantBankAccountData = merchantBankAccountData;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
  /**
   * Link URI to redirect the customer back to ecommerce sitr/app
   **/
  @ApiModelProperty(value = "Link URI to redirect the customer back to ecommerce sitr/app")
  @JsonProperty("redirectLink")
  public String getRedirectLink() {
    return redirectLink;
  }
  public void setRedirectLink(String redirectLink) {
    this.redirectLink = redirectLink;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EShopProfileDTO {\n");
    
    sb.append("  eShopUsername: ").append(eShopUsername).append("\n");
    sb.append("  eShopRegistrationNo: ").append(eShopRegistrationNo).append("\n");
    sb.append("  registeredBussinessName: ").append(registeredBussinessName).append("\n");
    sb.append("  registeredCountry: ").append(registeredCountry).append("\n");
    sb.append("  registeredAuthority: ").append(registeredAuthority).append("\n");
    sb.append("  ecommerceMarketplaceCategory: ").append(ecommerceMarketplaceCategory).append("\n");
    sb.append("  merchantCategoryCode: ").append(merchantCategoryCode).append("\n");
    sb.append("  merchantProductType: ").append(merchantProductType).append("\n");
    sb.append("  merchantBank: ").append(merchantBank).append("\n");
    sb.append("  merchantBankAccountData: ").append(merchantBankAccountData).append("\n");
    sb.append("  username: ").append(username).append("\n");
    sb.append("  password: ").append(password).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  redirectLink: ").append(redirectLink).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
