package pisp.dto;

import java.util.ArrayList;
import java.util.List;
import pisp.dto.BankAccountDTO;
import pisp.dto.BankDTO;
import pisp.dto.PaymentHistoryInnerInstructedAmountDTO;
import pisp.dto.PaymentInitRequestDeliveryAddressDTO;
import pisp.dto.PaymentInitRequestItemsPurchasedDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PaymentInitRequestDTO  {
  
  
  
  private String eShopUsername = null;
  
  
  private String merchantEndToEndidentification = null;
  
  @NotNull
  private String merchantName = null;
  
  @NotNull
  private String merchantCategoryCodeOfMerchant = null;
  
  
  private PaymentHistoryInnerInstructedAmountDTO instructedAmount = null;
  
  @NotNull
  private BankDTO merchantBank = null;
  
  @NotNull
  private BankAccountDTO merchantBankAccountData = null;
  
  
  private String customerIdentificationByEShop = null;
  
  
  private String customerName = null;
  
  
  private List<PaymentInitRequestItemsPurchasedDTO> itemsPurchased = new ArrayList<PaymentInitRequestItemsPurchasedDTO>();
  
  
  private PaymentInitRequestDeliveryAddressDTO deliveryAddress = null;
  
  @NotNull
  private String redirectURI = null;

  
  /**
   * The username of ecommerce user as registered at PISP
   **/
  @ApiModelProperty(value = "The username of ecommerce user as registered at PISP")
  @JsonProperty("eShopUsername")
  public String getEShopUsername() {
    return eShopUsername;
  }
  public void setEShopUsername(String eShopUsername) {
    this.eShopUsername = eShopUsername;
  }

  
  /**
   * conditional,only for multi vendor ecommerce sites/apps, The id of the merchant/product seller as registered in ecommerce site/app.
   **/
  @ApiModelProperty(value = "conditional,only for multi vendor ecommerce sites/apps, The id of the merchant/product seller as registered in ecommerce site/app.")
  @JsonProperty("merchantEndToEndidentification")
  public String getMerchantEndToEndidentification() {
    return merchantEndToEndidentification;
  }
  public void setMerchantEndToEndidentification(String merchantEndToEndidentification) {
    this.merchantEndToEndidentification = merchantEndToEndidentification;
  }

  
  /**
   * conditional,only for multi vendor ecommerce users, The name of the merchant/product seller as registered in ecommerce site
   **/
  @ApiModelProperty(required = true, value = "conditional,only for multi vendor ecommerce users, The name of the merchant/product seller as registered in ecommerce site")
  @JsonProperty("merchantName")
  public String getMerchantName() {
    return merchantName;
  }
  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }

  
  /**
   * Category code of merchant conforms to ISO 18245, related to the type of services or goods the single vendor/merchant provides for the purchase
   **/
  @ApiModelProperty(required = true, value = "Category code of merchant conforms to ISO 18245, related to the type of services or goods the single vendor/merchant provides for the purchase")
  @JsonProperty("merchantCategoryCodeOfMerchant")
  public String getMerchantCategoryCodeOfMerchant() {
    return merchantCategoryCodeOfMerchant;
  }
  public void setMerchantCategoryCodeOfMerchant(String merchantCategoryCodeOfMerchant) {
    this.merchantCategoryCodeOfMerchant = merchantCategoryCodeOfMerchant;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("instructedAmount")
  public PaymentHistoryInnerInstructedAmountDTO getInstructedAmount() {
    return instructedAmount;
  }
  public void setInstructedAmount(PaymentHistoryInnerInstructedAmountDTO instructedAmount) {
    this.instructedAmount = instructedAmount;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("merchantBank")
  public BankDTO getMerchantBank() {
    return merchantBank;
  }
  public void setMerchantBank(BankDTO merchantBank) {
    this.merchantBank = merchantBank;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("merchantBankAccountData")
  public BankAccountDTO getMerchantBankAccountData() {
    return merchantBankAccountData;
  }
  public void setMerchantBankAccountData(BankAccountDTO merchantBankAccountData) {
    this.merchantBankAccountData = merchantBankAccountData;
  }

  
  /**
   * The ID used by ecommerce site/app to uniquely identify the customer who requested to initiate the payment
   **/
  @ApiModelProperty(value = "The ID used by ecommerce site/app to uniquely identify the customer who requested to initiate the payment")
  @JsonProperty("customerIdentificationByEShop")
  public String getCustomerIdentificationByEShop() {
    return customerIdentificationByEShop;
  }
  public void setCustomerIdentificationByEShop(String customerIdentificationByEShop) {
    this.customerIdentificationByEShop = customerIdentificationByEShop;
  }

  
  /**
   * The name of the customer as refered by the e-commerce site
   **/
  @ApiModelProperty(value = "The name of the customer as refered by the e-commerce site")
  @JsonProperty("customerName")
  public String getCustomerName() {
    return customerName;
  }
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  
  /**
   * The items purchased by the payer/customer
   **/
  @ApiModelProperty(value = "The items purchased by the payer/customer")
  @JsonProperty("itemsPurchased")
  public List<PaymentInitRequestItemsPurchasedDTO> getItemsPurchased() {
    return itemsPurchased;
  }
  public void setItemsPurchased(List<PaymentInitRequestItemsPurchasedDTO> itemsPurchased) {
    this.itemsPurchased = itemsPurchased;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("deliveryAddress")
  public PaymentInitRequestDeliveryAddressDTO getDeliveryAddress() {
    return deliveryAddress;
  }
  public void setDeliveryAddress(PaymentInitRequestDeliveryAddressDTO deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  
  /**
   * Link URI
   **/
  @ApiModelProperty(required = true, value = "Link URI")
  @JsonProperty("redirectURI")
  public String getRedirectURI() {
    return redirectURI;
  }
  public void setRedirectURI(String redirectURI) {
    this.redirectURI = redirectURI;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentInitRequestDTO {\n");
    
    sb.append("  eShopUsername: ").append(eShopUsername).append("\n");
    sb.append("  merchantEndToEndidentification: ").append(merchantEndToEndidentification).append("\n");
    sb.append("  merchantName: ").append(merchantName).append("\n");
    sb.append("  merchantCategoryCodeOfMerchant: ").append(merchantCategoryCodeOfMerchant).append("\n");
    sb.append("  instructedAmount: ").append(instructedAmount).append("\n");
    sb.append("  merchantBank: ").append(merchantBank).append("\n");
    sb.append("  merchantBankAccountData: ").append(merchantBankAccountData).append("\n");
    sb.append("  customerIdentificationByEShop: ").append(customerIdentificationByEShop).append("\n");
    sb.append("  customerName: ").append(customerName).append("\n");
    sb.append("  itemsPurchased: ").append(itemsPurchased).append("\n");
    sb.append("  deliveryAddress: ").append(deliveryAddress).append("\n");
    sb.append("  redirectURI: ").append(redirectURI).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
