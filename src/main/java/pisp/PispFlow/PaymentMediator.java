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

package pisp.PispFlow;

import pisp.dao.BankManagementDAO;
import pisp.models.Bank;
import pisp.models.DebtorBank;

public class PaymentMediator {
    private PispFlow pispFlow;

    /**
     *to get the required pisp flow object according to the spec of bank
     *
     * @param bank
     * @return
     */

    public void selectPispFlow( Bank bank) {

        BankManagementDAO bankManagementDAO=new BankManagementDAO();
        DebtorBank debtorBank=bankManagementDAO.retriveBankInfo(bank.getIdentification());

        this.pispFlow=PispFlowFactory.getPispFlow(debtorBank.getSpecForOB(),debtorBank.getIdentification());
    }

    public void invokeBankAPI(){

    }
}
