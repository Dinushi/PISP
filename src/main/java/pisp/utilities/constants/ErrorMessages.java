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

package pisp.utilities.constants;
//package com.wso2.openbanking.account.aggregator.utilities.constants;

/**
 * All the Error messages used in the application is here.
 */
public final class ErrorMessages {
    public static final String USERNAME_EXISTS = "Username already in the database";
    public static final String USERNAME_DOESNT_EXIST = "Username not found in the database";
    public static final String SESSION_DOESNT_EXIST = "Session not found / expired";
    public static final String BANK_EXISTS = "Bank already in the database";
    public static final String BANK_DOESNT_EXIST = "Bank not found in the database";
    public static final String SQL_QUERY_PREPARATION_ERROR = "Unexpected error while preparing SQL Query";
    public static final String ERROR_OCCURRED = "Unexpected error occurred";
    public static final String DB_CLOSE_ERROR = "Unexpected error occurred while closing the database connection";
    public static final String DB_PARSE_ERROR = "Unexpected error occurred while parsing DB object";
    public static final String PARAMETERS_MISSING = "Missing required parameters in request";
    public static final String INCORRECT_PASSWORD = "Incorrect password";
    public static final String DB_SAVING_ERROR = "Error while saving to DB";
    public static final String BANK_NOT_FOUND = "Bank not found in database";
    public static final String BANK_API_NOT_RECOGNISED = "Bank API specification not recognized";
    public static final String INCORRECT_PAYLOAD = "Incorrect payload. Check validity of payload";
    public static final String MALFORMED_BODY = "Mal-formed Body in the payload";
    public static final String PROPERTIES_FILE_ERROR = "Error reading properties file";
    public static final String KEY_SIGNING_ERROR = "Error occurred at Key Signing";
    public static final String PRIVATE_KEY_ERROR = "Error while getting private key";
    public static final String THUMBPRINT_ERROR = "Error while generating ThumbPrint";
    public static final String GET_CALL_FAILED = "Error executing HTTP GET";
    public static final String POST_CALL_FAILED = "Error executing HTTP POST";
    public static final String CONTENT_PARSING_ERROR = "Error while parsing content";
    public static final String PARAMETERS_NULL = "Null parameters in request";
    public static final String EXPIRED_TOKEN = "Access Token Expired. Re-authenticate";
    public static final String APPLICATION_TOKEN_EXPIRED = "Application Token Expired. Contact System Administrator";
    public static final String ACCOUNT_INITIATION_FAILED = "Account Initiation Failed";
    public static final String ERROR_WHILE_AGGREGATING = "Error while aggregating bank details";

    public static final String ERROR_PAYMENT_REQUEST_NOT_PROCESSED = "The payment initiation request has not processed";
    public static final String ERROR_WHILE_RETRIEVING_PAYMENT_INITIATION = "The relevant payment initiation request is not retrieved from DB";

}
