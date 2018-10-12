/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.com). All Rights Reserved.
 *
 * This software is the property of WSO2 Inc. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein is strictly forbidden, unless permitted by WSO2 in accordance with
 * the WSO2 Commercial License available at http://wso2.com/licenses.
 * For specific language governing the permissions and limitations under this
 * license, please see the license as well as any agreement you’ve entered into
 * with WSO2 governing the purchase of this software and any associated services.
 */
package pisp.exception;

/**
 * Exception for format of certain request fields are not matching the XS2A requirements. An explicit path to the
 * corresponding field might be added in the return message.
 */
public class InvalidPayloadException extends RuntimeException {

    private String message;

    public InvalidPayloadException(String msg) {
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
