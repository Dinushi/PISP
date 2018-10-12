package pisp.dto;

import pisp.dto.PaymentInitRequestPriceDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class PaymentInitRequestItemsPurchasedDTO  {
  
  
  
  private Integer quantity = null;
  
  
  private PaymentInitRequestPriceDTO price = null;
  
  
  private String itemCode = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("quantity")
  public Integer getQuantity() {
    return quantity;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("price")
  public PaymentInitRequestPriceDTO getPrice() {
    return price;
  }
  public void setPrice(PaymentInitRequestPriceDTO price) {
    this.price = price;
  }

  
  /**
   * The item code issued for the item purchased
   **/
  @ApiModelProperty(value = "The item code issued for the item purchased")
  @JsonProperty("itemCode")
  public String getItemCode() {
    return itemCode;
  }
  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentInitRequestItemsPurchasedDTO {\n");
    
    sb.append("  quantity: ").append(quantity).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("  itemCode: ").append(itemCode).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
