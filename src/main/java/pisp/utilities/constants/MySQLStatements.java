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
 * All MySQL Queries reside here.
 */
public class MySQLStatements {

    //E-Shop related
    public static final String ADD_NEW_E_SHOP = "INSERT INTO E_SHOPS  (E_SHOP_USERNAME , REGISTERED_NO, REGISTERED_BUSSINESS_NAME, REGISTERED_AUTHORITY," +
            "REGISTERED_COUNTRY, ECOMMERCE_MARKETPALCE_CATEGORY, CONNECTED_DATE, PASSWORDHASH, SALT,EMIAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String GET_E_SHOP = "SELECT E_SHOP_USERNAME , REGISTERED_NO, REGISTERED_BUSSINESS_NAME, REGISTERED_AUTHORITY, REGISTERED_COUNTRY, "+
            "ECOMMERCE_MARKETPALCE_CATEGORY,CONNECTED_DATE, PASSWORDHASH, SALT,EMIAL FROM AGGREGATOR_USER WHERE E_SHOP_USERNAME=?;";

    public static final String GET_PASSWORD = "SELECT USERNAME, NAME, PASSWORD_HASH, SALT FROM AGGREGATOR_USER " +
            "WHERE USERNAME=?;";

    //bank related

    public static final String ADD_NEW_BANK = "INSERT INTO BANKS (BANK_NAME, BANK_UID, SPEC_FOR_OB , CLIENT_ID, CLIENT_SECREAT,) " +
            "TOKENAPIURL, AUTHORIZEAPIURL, PAYMENTAPIURL VALUES (?, ?, ?, 'Active');";


    public static final String GET_A_BANK = "SELECT * FROM BANKS WHERE BANK_UID=?;";





    //user related


    public static final String UPDATE_USER_NAME = "UPDATE AGGREGATOR_USER SET NAME = ? WHERE USERNAME = ?;";

    public static final String UPDATE_USER_PASSWORD = "UPDATE AGGREGATOR_USER " +
            "SET NAME = ?, PASSWORD_HASH = ?, SALT = ? " +
            "WHERE USERNAME = ?;";

    // Bank Related

    public static final String UPDATE_BANK_RECORD = "UPDATE BANKS SET BANK_NAME = ?, BANK_STANDARD = ?, STATUS = ? " +
            "WHERE BANK_UID = ?";

    public static final String REMOVE_BANK_RECORD = "UPDATE BANKS SET STATUS = 'Removed' WHERE BANK_UID = ?";

    public static final String GET_ACTIVE_BANKS = "SELECT * FROM BANKS WHERE STATUS = 'Active';";

    // Bank-User Related
    public static final String GET_HISTORY_OF_USER_LINKING_BANK = "SELECT * FROM USER_BANKS WHERE " +
            "USERNAME=? AND BANK_UID=?";

    public static final String LINK_USER_AND_BANK = "INSERT INTO USER_BANKS (USERNAME, BANK_UID, STATUS) VALUES" +
            " (?, ?, 'Linking Initiated');";

    public static final String UPDATE_USER_BANK_LINK = "UPDATE USER_BANKS SET STATUS=? WHERE USERNAME=? AND BANK_UID=?";

    public static final String GET_ACTIVE_BANKS_OF_USER = "SELECT BANK_UID, LINKING_INITIATED FROM USER_BANKS " +
            "WHERE USERNAME=? AND (STATUS='Linked' OR STATUS='Re-linking Initiated');";

    public static final String GET_ALL_BANKS_LINKED_WITH_A_USER = "SELECT BANK_UID, LINKING_INITIATED, STATUS FROM" +
            " USER_BANKS WHERE USERNAME=?;";

    // Tokens related
    public static final String ADD_A_NEW_APPLICATION_TOKEN = "INSERT INTO APPLICATION_TOKENS " +
            "(BANK_UID, TOKEN, VALID_TILL) VALUES (?, ?, ?);";

    public static final String GET_APPLICATION_TOKEN = "SELECT TOKEN, VALID_TILL FROM APPLICATION_TOKENS WHERE" +
            " BANK_UID = ? ORDER BY TIMESTAMP DESC LIMIT 1;";

    public static final String ADD_ACCESS_TOKEN = "INSERT INTO ACCESS_TOKENS (USERNAME, BANK_UID, PRIMARY_TOKEN, " +
            "REFRESH_TOKEN, VALID_TILL) VALUES (?, ?, ?, ?, ?);";

    public static final String ADD_AUDIT_TOKEN = "INSERT INTO TOKENS_AUDIT (USERNAME, BANK_UID, TOKEN, TYPE) VALUES " +
            "(?, ?, ?, ?);";

    public static final String GET_ALL_LAST_ADDED_ACCESS_TOKEN_OF_USER = "" +
            "SELECT BANK_UID, PRIMARY_TOKEN, REFRESH_TOKEN, VALID_TILL " +
            "FROM ACCESS_TOKENS " +
            "WHERE TIMESTAMP IN (" +
            "   SELECT MAX(TIMESTAMP) " +
            "   FROM ACCESS_TOKENS " +
            "   GROUP BY BANK_UID) " +
            "   AND USERNAME = ?;";

    // Initiation/ConsentID Related
    public static final String ADD_NEW_INITIATION_ID = "INSERT INTO INITIATION_IDS " +
            "(USERNAME, BANK_UID, INITIATION_ID) VALUES (?, ?, ?);";

    public static final String GET_ACTIVE_INITIATION_ID_OF_USER_FOR_BANK = "SELECT INITIATION_ID FROM INITIATION_IDS " +
            "WHERE USERNAME = ? AND BANK_UID = ? AND ACCESS_TOKEN_UID IS NOT NULL " +
            "ORDER BY TIMESTAMP DESC " +
            "LIMIT 1;";

    public static final String GET_LAST_INITIATION_ID_OF_USER_FOR_BANK = "SELECT INITIATION_ID FROM INITIATION_IDS " +
            "WHERE USERNAME = ? AND BANK_UID = ? " +
            "ORDER BY TIMESTAMP DESC " +
            "LIMIT 1;";

    public static final String MAP_INITIATION_ID_WITH_ACCESS_TOKEN = "UPDATE INITIATION_IDS " +
            "SET ACCESS_TOKEN_UID = (" +
            "   SELECT UID FROM ACCESS_TOKENS " +
            "   WHERE BANK_UID = ? AND USERNAME = ? " +
            "   ORDER BY TIMESTAMP DESC " +
            "   LIMIT 1) " +
            "WHERE UID = (" +
            "   SELECT UID FROM (SELECT * FROM INITIATION_IDS) AS IDs " +
            "   WHERE BANK_UID = ? AND USERNAME = ? AND ACCESS_TOKEN_UID IS NULL " +
            "   ORDER BY TIMESTAMP DESC " +
            "   LIMIT 1);";

    // Session Related

    public static final String ADD_SESSION_TOKEN = "INSERT INTO SESSION_TOKENS (USERNAME, SESSION_KEY, EXPIRY_TIME) " +
            "VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE " +
            "  SESSION_KEY=?, EXPIRY_TIME=?;";

    public static final String GET_SESSION_TOKEN = "SELECT * FROM SESSION_TOKENS WHERE SESSION_KEY = ?;";
}
