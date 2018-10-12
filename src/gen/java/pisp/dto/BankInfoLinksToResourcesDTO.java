package pisp.dto;

import io.swagger.annotations.ApiModel;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;



/**
 * The required links can be optinal according to the spec followed by the bank
 **/


@ApiModel(description = "The required links can be optinal according to the spec followed by the bank")
public class BankInfoLinksToResourcesDTO  {
  
  
  
  private String paymentAPI = null;
  
  
  private String authorizeAPI = null;
  
  
  private String tokenAPI = null;

  
  /**
   * The base uri to access the payment API of the bank
   **/
  @ApiModelProperty(value = "The base uri to access the payment API of the bank")
  @JsonProperty("paymentAPI")
  public String getPaymentAPI() {
    return paymentAPI;
  }
  public void setPaymentAPI(String paymentAPI) {
    this.paymentAPI = paymentAPI;
  }

  
  /**
   * The base uri to access the Authorize API of the bank
   **/
  @ApiModelProperty(value = "The base uri to access the Authorize API of the bank")
  @JsonProperty("authorizeAPI")
  public String getAuthorizeAPI() {
    return authorizeAPI;
  }
  public void setAuthorizeAPI(String authorizeAPI) {
    this.authorizeAPI = authorizeAPI;
  }

  
  /**
   * The base uri to access the Token API of the bank
   **/
  @ApiModelProperty(value = "The base uri to access the Token API of the bank")
  @JsonProperty("tokenAPI")
  public String getTokenAPI() {
    return tokenAPI;
  }
  public void setTokenAPI(String tokenAPI) {
    this.tokenAPI = tokenAPI;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankInfoLinksToResourcesDTO {\n");
    
    sb.append("  paymentAPI: ").append(paymentAPI).append("\n");
    sb.append("  authorizeAPI: ").append(authorizeAPI).append("\n");
    sb.append("  tokenAPI: ").append(tokenAPI).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
