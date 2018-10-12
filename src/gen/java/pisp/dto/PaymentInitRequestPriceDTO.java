package pisp.dto;

import io.swagger.annotations.ApiModel;
import java.math.BigDecimal;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;



/**
 * The price of a unit of item
 **/


@ApiModel(description = "The price of a unit of item")
public class PaymentInitRequestPriceDTO  {
  
  
  
  private BigDecimal amount = null;
  
  
  private String currency = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentInitRequestPriceDTO {\n");
    
    sb.append("  amount: ").append(amount).append("\n");
    sb.append("  currency: ").append(currency).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
