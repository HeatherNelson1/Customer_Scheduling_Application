package DAO;
/**
 * DivisionDAO runs select queries from the divsion table database
 */

import Model.Division;
import Model.OAL;
import Utilities.DBConnection;
import Utilities.DBQuery;

import java.sql.*;
import java.time.LocalDate;

public class DivisionDAO {
    /**
     * queryDivisions method runs select query to db returning all divisions as resultset, adds to allDivisions obs list.
     */
    public static void queryDivisions()
        {
            OAL.getAllDivisions().clear();
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM first_level_divisions";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                LocalDate createdDate = rs.getDate("Create_Date").toLocalDate();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdateDate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("COUNTRY_ID");

                Division div = new Division(divisionId,division,createdDate,createdBy,lastUpdateDate,lastUpdatedBy,countryId);
                OAL.getAllDivisions().add(div);
            }

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("user not found");
        }


    }
}
