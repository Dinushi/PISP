package pisp.dto;

import pisp.dto.BankDTO;
import pisp.dto.BankInfoLinksToResourcesDTO;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class BankInfoDTO  {
  
  
  @NotNull
  private BankDTO bank = null;
  
  public enum SpecForOBEnum {
     UK,  BERLIN,  STET, 
  };
  @NotNull
  private SpecForOBEnum specForOB = null;
  
  
  private String clientId = null;
  
  
  private String clientSecreat = null;
  
  
  private BankInfoLinksToResourcesDTO linksToResources = null;

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("bank")
  public BankDTO getBank() {
    return bank;
  }
  public void setBank(BankDTO bank) {
    this.bank = bank;
  }

  
  /**
   * The open banking specification followed by the bank
   **/
  @ApiModelProperty(required = true, value = "The open banking specification followed by the bank")
  @JsonProperty("specForOB")
  public SpecForOBEnum getSpecForOB() {
    return specForOB;
  }
  public void setSpecForOB(SpecForOBEnum specForOB) {
    this.specForOB = specForOB;
  }

  
  /**
   * The production keys issued by bank to the PISP, once registered as a TPP and created an Application subscribing to payment API. Will be use in client credentials grant.
   **/
  @ApiModelProperty(value = "The production keys issued by bank to the PISP, once registered as a TPP and created an Application subscribing to payment API. Will be use in client credentials grant.")
  @JsonProperty("clientId")
  public String getClientId() {
    return clientId;
  }
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  
  /**
   * The production keys issued by bank to the PISP, once registered as a TPP and created an Application subscribing to payment API of the bank. Will be use in client credentials grant.
   **/
  @ApiModelProperty(value = "The production keys issued by bank to the PISP, once registered as a TPP and created an Application subscribing to payment API of the bank. Will be use in client credentials grant.")
  @JsonProperty("clientSecreat")
  public String getClientSecreat() {
    return clientSecreat;
  }
  public void setClientSecreat(String clientSecreat) {
    this.clientSecreat = clientSecreat;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("linksToResources")
  public BankInfoLinksToResourcesDTO getLinksToResources() {
    return linksToResources;
  }
  public void setLinksToResources(BankInfoLinksToResourcesDTO linksToResources) {
    this.linksToResources = linksToResources;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankInfoDTO {\n");
    
    sb.append("  bank: ").append(bank).append("\n");
    sb.append("  specForOB: ").append(specForOB).append("\n");
    sb.append("  clientId: ").append(clientId).append("\n");
    sb.append("  clientSecreat: ").append(clientSecreat).append("\n");
    sb.append("  linksToResources: ").append(linksToResources).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
