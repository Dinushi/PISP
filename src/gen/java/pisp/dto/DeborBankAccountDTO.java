package pisp.dto;

import pisp.dto.Bank1DTO;
import pisp.dto.BankAccountDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class DeborBankAccountDTO  {
  
  
  @NotNull
  private Bank1DTO debtorBank = null;
  
  @NotNull
  private BankAccountDTO debtorAccount = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("debtorBank")
  public Bank1DTO getDebtorBank() {
    return debtorBank;
  }
  public void setDebtorBank(Bank1DTO debtorBank) {
    this.debtorBank = debtorBank;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("debtorAccount")
  public BankAccountDTO getDebtorAccount() {
    return debtorAccount;
  }
  public void setDebtorAccount(BankAccountDTO debtorAccount) {
    this.debtorAccount = debtorAccount;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeborBankAccountDTO {\n");
    
    sb.append("  debtorBank: ").append(debtorBank).append("\n");
    sb.append("  debtorAccount: ").append(debtorAccount).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
