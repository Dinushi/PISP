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
import pisp.models.DebtorBank;
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

public class BankManagementDAO {
    private Log log = LogFactory.getLog(PaymentInitiationRequest.class);

    public DebtorBank retriveBankInfo(String bankUId) throws PispException {
        Validate.notNull(bankUId, ErrorMessages.PARAMETERS_NULL);

        log.info("retrieving debtor bank: " + bankUId);
        final String sql = MySQLStatements.GET_A_BANK;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, bankUId);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    DebtorBank bank = null;
                    if (rs.next()) {
                        bank = new DebtorBank();
                        bank.setBankName("");
                        log.info("Successfully retrieved user");
                    } else {
                        log.info("User not in DB");
                    }
                    return bank;
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
    public boolean addnewBankConnection(DebtorBank debtorbank){

        Validate.notNull(debtorbank, ErrorMessages.PARAMETERS_NULL);

        log.info("storing bank: " + debtorbank.getBankName());
        final String sql = MySQLStatements.ADD_NEW_BANK;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                //preparedStatement.setString(1, e_shop.getName());
                preparedStatement.setString(2, debtorbank.getBankName());
                preparedStatement.setString(3, debtorbank.getSchemeName());
                preparedStatement.setString(4, debtorbank.getSpecForOB());
                //preparedStatement.setString(5, e_shop.getAdditionalAttributes().toString());

                preparedStatement.executeUpdate();

                log.info("New bank connection is added");
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

}
