package DAO;
/**
 * UsersDAO runs select queries from the database users table.
 */

import Model.OAL;
import Model.Users;
import Utilities.DBConnection;
import Utilities.DBQuery;
import Utilities.DataProvider;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class UserDAO {


    public void passwordIsValid() {
        //test for valid passowrd
    }



    public static void queryUser() {
    OAL.getAllUsers().clear();
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM users";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                LocalDate createdDate = rs.getDate("Create_Date").toLocalDate();
                LocalTime createdTime = rs.getTime("Create_Date").toLocalTime();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");


                Users userResult = new Users(userId, userName, password, createdDate, createdTime, createdBy, lastUpdate, lastUpdatedBy);
                OAL.getAllUsers().add(userResult);


                int count = rs.getInt((1));
                System.out.println(count);
                //System.out.println("worked");


            }

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (NullPointerException e)
        {
            System.out.println("user not found");
        }




    }

}