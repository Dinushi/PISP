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

package pisp.dao;

import pisp.exception.PispException;
import pisp.models.PaymentInitiationRequest;
import pisp.utilities.constants.ErrorMessages;
import pisp.utilities.constants.MySQLStatements;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentManagementDAO {

    private Log log = LogFactory.getLog(PaymentInitiationRequest.class);

    /**
     *
     * @param paymentInitiationRequest
     * @throws PispException
     * store the payment initiation request in database
     */

    public boolean addPaymentInitiation(PaymentInitiationRequest paymentInitiationRequest) throws PispException {

        Validate.notNull(paymentInitiationRequest, ErrorMessages.PARAMETERS_NULL);

        final String sql = MySQLStatements.ADD_NEW_E_SHOP;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, paymentInitiationRequest.getEShopUsername());
                preparedStatement.setString(2, paymentInitiationRequest.getMerchantName());
                //preparedStatement.setBytes(3, hash);
                //preparedStatement.setBytes(4, salt);
                //preparedStatement.setString(5, user.getAdditionalAttributes().toString());

                preparedStatement.executeUpdate();

                log.info("User Registered");
                return true;

            } catch (SQLException e) {
                log.error(ErrorMessages.SQL_QUERY_PREPARATION_ERROR, e);
                throw new PispException(ErrorMessages.SQL_QUERY_PREPARATION_ERROR);
            }
        } catch (SQLException e) {
            log.error(ErrorMessages.DB_CLOSE_ERROR, e);
            throw new PispException(ErrorMessages.DB_CLOSE_ERROR);
        }
    }

    /**
     *
     * @param paymentInitiationRequest
     * @throws PispException
     * update the debtor bank and account in db
     */
    public void updatePaymentInitiation(PaymentInitiationRequest paymentInitiationRequest) throws PispException {

        Validate.notNull(paymentInitiationRequest, ErrorMessages.PARAMETERS_NULL);

        final String sql = MySQLStatements.ADD_NEW_E_SHOP;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                //update status too
                preparedStatement.setString(1, paymentInitiationRequest.getCustomerBank().getSchemeName());
                preparedStatement.setString(2, paymentInitiationRequest.getCustomerBank().getBankName());
                //add rest of values
                //preparedStatement.setBytes(3, hash);
                //preparedStatement.setBytes(4, salt);
                //preparedStatement.setString(5, user.getAdditionalAttributes().toString());

                preparedStatement.executeUpdate();

                log.info("User Registered");

            } catch (SQLException e) {
                log.error(ErrorMessages.SQL_QUERY_PREPARATION_ERROR, e);
                throw new PispException(ErrorMessages.SQL_QUERY_PREPARATION_ERROR);
            }
        } catch (SQLException e) {
            log.error(ErrorMessages.DB_CLOSE_ERROR, e);
            throw new PispException(ErrorMessages.DB_CLOSE_ERROR);
        }
    }


    /**
     *
     * @return
     * @throws PispException
     * retrieve a selected payment initiation request
     */
    public PaymentInitiationRequest retrivePayment(String paymentInitReqId) throws PispException {
        //Validate.notNull(userName, ErrorMessages.PARAMETERS_NULL);

        //log.info("retrieving e-shop: " + userName);
        final String sql = MySQLStatements.GET_A_BANK;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                //preparedStatement.setString(1, userName);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    PaymentInitiationRequest paymentInitiationRequest =null;
                    if (rs.next()) {
                        paymentInitiationRequest = new PaymentInitiationRequest();
                        paymentInitiationRequest.setRedirectURI("ruhfureiea");
                        log.info("Successfully retrieved initiated payment");
                    } else {
                        log.info("User not in DB");
                    }
                    return paymentInitiationRequest;
                } catch (SQLException e) {
                    log.error(ErrorMessages.DB_PARSE_ERROR, e);
                    throw new PispException(ErrorMessages.DB_PARSE_ERROR);
                }

            } catch (SQLException e) {
                log.error(ErrorMessages.SQL_QUERY_PREPARATION_ERROR, e);
                throw new PispException(ErrorMessages.SQL_QUERY_PREPARATION_ERROR);
            }
        } catch (SQLException e) {
            log.error(ErrorMessages.DB_CLOSE_ERROR, e);
            throw new PispException(ErrorMessages.DB_CLOSE_ERROR);
        }
    }
}
