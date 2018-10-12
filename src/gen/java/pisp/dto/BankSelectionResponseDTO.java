package pisp.dto;

import pisp.dto.Bank1DTO;
import pisp.dto.BankAccountDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class BankSelectionResponseDTO  {
  
  
  
  private Bank1DTO debtorBank = null;
  
  
  private BankAccountDTO debtorAccount = null;
  
  @NotNull
  private String redirectLink = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("debtorBank")
  public Bank1DTO getDebtorBank() {
    return debtorBank;
  }
  public void setDebtorBank(Bank1DTO debtorBank) {
    this.debtorBank = debtorBank;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("debtorAccount")
  public BankAccountDTO getDebtorAccount() {
    return debtorAccount;
  }
  public void setDebtorAccount(BankAccountDTO debtorAccount) {
    this.debtorAccount = debtorAccount;
  }

  
  /**
   * The link to invoke the authorize end point of the bank
   **/
  @ApiModelProperty(required = true, value = "The link to invoke the authorize end point of the bank")
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
    sb.append("class BankSelectionResponseDTO {\n");
    
    sb.append("  debtorBank: ").append(debtorBank).append("\n");
    sb.append("  debtorAccount: ").append(debtorAccount).append("\n");
    sb.append("  redirectLink: ").append(redirectLink).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
