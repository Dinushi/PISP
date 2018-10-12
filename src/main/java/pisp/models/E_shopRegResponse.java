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

public class E_shopRegResponse {

    private String e_shopUsername;
    private boolean errorStatus = false;
    private String errorMessage = null;
    private String client_id;
    private String client_secreat;


    public String getE_shopUsername() {
        return e_shopUsername;
    }

    public void setE_shopUsername(String e_shopUsername) {
        this.e_shopUsername = e_shopUsername;
    }

    public boolean isError() {
        return errorStatus;
    }

    public void setErrorStatus(boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secreat() {
        return client_secreat;
    }

    public void setClient_secreat(String client_secreat) {
        this.client_secreat = client_secreat;
    }
}
