package Utilities;
/**
 * establishes connection to the database
 */

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
// done 2/19/2021

public class DBQuery {
    private static PreparedStatement statement;
    //Statement reference
    //CREATE STATEMENT OBJECT

    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }
    public static PreparedStatement getPreparedStatement(){
        return statement;
    }
}
