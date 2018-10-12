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
import pisp.models.E_shop;
import pisp.utilities.PasswordHashGenerator;
import pisp.utilities.constants.ErrorMessages;
import pisp.utilities.constants.MySQLStatements;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EshopManagementDAO {

    private Log log = LogFactory.getLog(EshopManagementDAO.class);

    /**
     * Validate if the e_shop username exists in the database.
     *
     * @param userName The username to check.
     * @return True if username exists or False if doesn't.
     * @throws PispException If database connection errors.
     */

    public boolean validateUsername(String userName) throws PispException {
        Validate.notNull(userName, ErrorMessages.PARAMETERS_NULL);

        log.info("Validating username: " + userName);
        final String sql = MySQLStatements.GET_E_SHOP;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userName);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    boolean isInDb = rs.next();
                    if (isInDb) {
                        log.info("Validated: User in DB");
                    } else {
                        log.info("Validated: User not in DB");
                    }
                    return isInDb;
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

    /**
     * Add a e_shop record to the database.
     *
     * @param e_shop User to add.
     * @throws PispException If database connection errors.
     */
    public boolean registerEshop(E_shop e_shop) throws PispException {
        Validate.notNull(e_shop, ErrorMessages.PARAMETERS_NULL);

        final String sql = MySQLStatements.ADD_NEW_E_SHOP;

        byte[] salt = PasswordHashGenerator.getNextSalt();
        byte[] hash = PasswordHashGenerator.hash(e_shop.getPassword().toCharArray(), salt);

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                //preparedStatement.setString(1, e_shop.getName());
                preparedStatement.setString(2, e_shop.getUsername());
                preparedStatement.setBytes(3, hash);
                preparedStatement.setBytes(4, salt);
                //preparedStatement.setString(5, e_shop.getAdditionalAttributes().toString());

                preparedStatement.executeUpdate();

                log.info("E-shop Registered");
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
     * @param userName
     * @return requested e-shop user details
     * @throws PispException
     */
    public E_shop retriveE_Shop(String userName) throws PispException {
        Validate.notNull(userName, ErrorMessages.PARAMETERS_NULL);

        log.info("retrieving e-shop: " + userName);
        final String sql = MySQLStatements.GET_E_SHOP;

        try (Connection connection = JDBCPersistenceManager.getInstance().getDBConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userName);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    E_shop e_shop =null;
                    if (rs.next()) {
                        e_shop = new E_shop();
                        e_shop.setUsername(rs.getString("username"));
                        log.info("Successfully retrieved user");
                    } else {
                        log.info("User not in DB");
                    }
                    return e_shop;
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
