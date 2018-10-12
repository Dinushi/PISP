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

package pisp.models;

import java.util.HashMap;
import java.util.Map;

public class DebtorBank extends Bank {

    private String specForOB = null;


    private String clientId = null;


    private String consumerSecreat = null;


    private Map<String, String> linksTobankAPIResources;

    public DebtorBank(){
        this.setLinksTobankAPIResources(new HashMap<String, String>());
    }

    public String getSpecForOB() {
        return specForOB;
    }

    public void setSpecForOB(String specForOB) {
        this.specForOB = specForOB;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getConsumerSecreat() {
        return consumerSecreat;
    }

    public void setConsumerSecreat(String consumerSecreat) {
        this.consumerSecreat = consumerSecreat;
    }

    public Map<String, String> getLinksTobankAPIResources() {
        return linksTobankAPIResources;
    }

    public void setLinksTobankAPIResources(Map<String, String> linksTobankAPIResources) {
        this.linksTobankAPIResources = linksTobankAPIResources;
    }
}
