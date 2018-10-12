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

package pisp.mappings;

import pisp.models.Bank;
import pisp.models.DebtorBank;
import pisp.dto.BankDTO;
import pisp.dto.BankInfoDTO;
import pisp.models.Bank;

public class BankMapping {

    /**
     *
     * @param bankData
     * @return a instance of bank
     */

    public static Bank createBankInstance(BankDTO bankData){
        if (bankData == null) {
            return null;
        }

        Bank bank=new Bank();
        bank.setSchemeName(bankData.getSchemeName().toString());
        bank.setIdentification(bankData.getIdentification());
        bank.setBankName(bankData.getBankName());


        return bank ;
    }

    public static Bank createDebtorbankInstance(BankInfoDTO bankInfo){
        if(bankInfo == null){
            return null;
        }
        Bank bank=new DebtorBank();
        bank.setBankName(bankInfo.getBank().getBankName());
        bank.setSchemeName(bankInfo.getBank().getSchemeName().toString());
        bank.setIdentification(bankInfo.getBank().getIdentification());
        ((DebtorBank) bank).setClientId(bankInfo.getClientId());
        ((DebtorBank) bank).setConsumerSecreat(bankInfo.getClientSecreat());
        ((DebtorBank) bank).setSpecForOB(bankInfo.getSpecForOB().toString());

        //((DebtorBank) bank).setLinksTobankAPIResources(bankInfo.getLinksToResources().);
        //set these links in a proper way
        return bank;

    }
}

