package DAO;
/**
 * CountriesDAO class runs select queries from the Countries table in the database
 */

import Model.Countries;
import Model.OAL;
import Utilities.DBConnection;
import Utilities.DBQuery;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static Utilities.DBQuery.setPreparedStatement;

public class CountriesDAO {
    /**
     * queryCountries runs select query to db returning all countries and sets result set to countryResult object
     */
    public static void queryCountries() {
        OAL.getAllCountries().clear();
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM countries";
            setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID"); // type modelVariable = rs.getType("sql db name")
                String countryName = rs.getString("Country");
                LocalDate createdDate = rs.getDate("Create_Date").toLocalDate();
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                Countries countryResult = new Countries(countryId, countryName, createdDate, createdBy, lastUpdate, lastUpdatedBy);


                OAL.getAllCountries().add(countryResult);
                //   List<Countries> allCountries = OAL.getAllCountries();
                int count = rs.getInt((1));
                System.out.println(countryResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * addCountries check bascially a practice for insert
     */
    public static void addCountries() {
        try {
            Connection conn = DBConnection.getConnection();
            String insertStatement = "INSERT INTO countries(Country,Create_Date,Created_By,Last_Updated_By) VALUES(?,?,?,?,?)";//one question mark for each column inserted
            setPreparedStatement(conn, insertStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            String countryId =null;


            //all created as strings
            String countryName = "Russia";
            String createdDate ="2021-10-05 00:00:00";
            String createdBy = "admin";
            String lastUpdate = "2021-10-05 00:00:00";
            String lastUpdateBy = "admin";

            ps.setString(1, countryName);
            ps.setString(2, createdDate);
            ps.setString(3, createdBy);
            ps.setString(4, lastUpdate);
            ps.setString(5, lastUpdateBy);

            //type in column names from db

            ps.execute();
            ResultSet rs = ps.getResultSet(); //allows you to retrieve the countryId auto incremented
            rs.next();
            countryId = rs.getString(1);

            if (ps.getUpdateCount() > 0) {
                System.out.println(ps.getUpdateCount() + "row(s) added");
            } else {
                System.out.println("No Additions");

            }
        }
        catch(SQLException e){
                e.printStackTrace();
            }

        }



}

