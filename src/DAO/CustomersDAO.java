package DAO;
/**
 * CustomersDAO runs select, update and delete queries to the database
 */

import Model.Customers;
import Model.Division;
import Model.OAL;
import Utilities.DBConnection;
import Utilities.DBQuery;
import Utilities.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.SQLException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CustomersDAO {

    public static void selectCustomers() {
        OAL.getAllCustomers().clear();
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT customers.*,Division, country_ID From customers, first_level_divisions where customers.Division_ID=first_level_divisions.Division_ID";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerAddress = address + " " + customerPostal;


                String customerPhone = rs.getString("Phone");

                LocalDate cDate = rs.getDate("Create_Date").toLocalDate();
                LocalTime cTime = rs.getTime("Create_Date").toLocalTime();
                LocalDateTime createdDate = LocalDateTime.of(cDate,cTime);

                String createdBy = rs.getString("Created_By");

                LocalDate lDate = rs.getDate("Last_Update").toLocalDate();
                LocalTime lTime = rs.getTime("Last_Update").toLocalTime();
                LocalDateTime lastUpdated = LocalDateTime.of(lDate,lTime);

                String lastUpdatedBy =rs.getString("Last_Updated_By");
                int divsionId = rs.getInt("Division_ID");
                String divisionName=rs.getString("Division");
                int countryId = rs.getInt("country_ID");


                Customers  customer= new Customers(customerId,customerName,customerAddress,customerPhone,customerPostal, createdDate,createdBy,lastUpdated,lastUpdatedBy,divsionId,divisionName,countryId);

                OAL.getAllCustomers().add(customer);


                int count = rs.getInt((1));
                System.out.println(count);


            }
        }

        catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (NullPointerException e)
        {
            System.out.println("Customer Not Found");
        }

    }

    public static void addCustomer(Customers customer) throws Exception
    {
        try {
            Connection conn = DBConnection.getConnection();
            String insertStatement = "INSERT INTO customers(Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID)VALUES(?,?,?,?,?,?,?,?,?)";
            DBQuery.setPreparedStatement(conn, insertStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            String customerName = customer.getCustomerName();

            String customerAddress = customer.getCustomerAddress();

            String customerPostal = customer.getCustomerPostal();

            String customerPhone = customer.getCustomerPhone();

            LocalDateTime createdDate = customer.getCreatedDate();
            String cDate = String.valueOf(createdDate);

            String userId = DataProvider.userId;
            String createdBy = userId;

            LocalDateTime lastUpdated = customer.getLastUpdated();
            String lUpdate= String.valueOf(lastUpdated);

            String lastUpdatedBy = customer.getLastUpdatedBy();

            int divisionId = customer.getDivisionId();


            //     set the user to DataProvder.userId

            int countryId=0;

            for (Division div : OAL.getAllDivisions())
            {
                if (divisionId == div.getDivisionId())
                {
                    countryId = div.getDivCountryId();
                }
            }
            String cId = String.valueOf(countryId);

           // ps.setString(1, id);
            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostal);
            ps.setString(4, customerPhone);
            ps.setString(5, cDate);
            ps.setString(6, createdBy);
            ps.setString(7, lUpdate);
            ps.setString(8, lastUpdatedBy);
            ps.setInt(9, divisionId);

            ps.execute();
            if (ps.getUpdateCount()>0)
            {
                System.out.println(getLastCustomerId());//hms 5/24
                if (getLastCustomerId()>0)
                {
                    customer.setCustomerId(getLastCustomerId());
                    //   OAL.updateApptList().add(appointments);
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void updateCustomer(Customers customer) throws Exception
    {

        try {
            Connection conn = DBConnection.getConnection();
            String updateStatement = "UPDATE customers SET Customer_Name=?,Address=?,Postal_Code=?,Phone=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=?,Division_ID=? WHERE Customer_ID=?";
            DBQuery.setPreparedStatement(conn, updateStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            int customerId = customer.getCustomerId();

            String customerName = customer.getCustomerName();

            String customerAddress = customer.getCustomerAddress();

            String customerPostal = customer.getCustomerPostal();

            String customerPhone = customer.getCustomerPhone();

            LocalDateTime createdDate = customer.getCreatedDate();
            String cDate = String.valueOf(createdDate);

            String userId = DataProvider.userId;
            String createdBy = userId;

            LocalDateTime lastUpdated = customer.getLastUpdated();
            String lUpdate= String.valueOf(lastUpdated);

            String lastUpdatedBy = customer.getLastUpdatedBy();

            int divisionId = customer.getDivisionId();


            ps.setString(1, customerName);
            ps.setString(2, customerAddress);
            ps.setString(3, customerPostal);
            ps.setString(4, customerPhone);
            ps.setString(5, cDate);
            ps.setString(6, createdBy);
            ps.setString(7, lUpdate);
            ps.setString(8, lastUpdatedBy);
            ps.setInt(9, divisionId);
            ps.setInt(10, customerId);

            ps.execute();

            if (ps.getUpdateCount()>0)
            {
                //System.out.println(getLastCustomerId());//hms 5/24
                //if (getLastCustomerId()>0)
                DataProvider.custUpdated =true;
                System.out.println("customerUpdated");
                   // customer.setCustomerId(getLastCustomerId());
                    //OAL.addCustomers(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  static int getLastCustomerId() throws Exception
    {
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM customers WHERE Customer_ID=(SELECT max(Customer_ID) FROM customers)";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                int custId = rs.getInt("Customer_ID");
                return custId;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // }
        return -1;
    }
    public static void deleteCustomer(Customers customer)
    {
        try {
            Connection conn = DBConnection.getConnection();
            String deleteAppt = "DELETE FROM appointments WHERE Customer_ID=?";

            DBQuery.setPreparedStatement(conn, deleteAppt);
            PreparedStatement psA = DBQuery.getPreparedStatement();

            int custIdA = customer.getCustomerId();
            //String idA = String.valueOf(custIdA);
            psA.setInt(1, custIdA);
            psA.execute();
            String deleteStatement = "DELETE FROM customers WHERE Customer_ID=?";
            DBQuery.setPreparedStatement(conn, deleteStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            int custId = customer.getCustomerId();
            String id = String.valueOf(custId);


            ps.setString(1, id);

            ps.execute();

            if (ps.getUpdateCount() > 0)
            {
                int delCount = ps.getUpdateCount();
                System.out.println(delCount + "deleted");
                DataProvider.custDeleted = true;

            }
            else
            {
                Alert cannotLoad = new Alert(Alert.AlertType.ERROR);
                cannotLoad.setTitle("Unsuccessful");
                cannotLoad.setContentText("Update Not Successful");
                cannotLoad.showAndWait();
            }


        }
        catch (SQLException e)
    {
        e.printStackTrace();
    }
    }
    public static ObservableList<Customers> getCustomerByCountry(int countryId) {
            ObservableList<Customers> customers = FXCollections.observableArrayList();//empty list

        try {
            Connection conn = DBConnection.getConnection();
            String selectCustomer = "SELECT customers.*,Division From customers, first_level_divisions where customers.Division_ID=first_level_divisions.Division_ID and COUNTRY_ID=?";
            DBQuery.setPreparedStatement(conn, selectCustomer);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setInt(1,countryId);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerAddress = address + " " + customerPostal;


                String customerPhone = rs.getString("Phone");

                LocalDate cDate = rs.getDate("Create_Date").toLocalDate();
                LocalTime cTime = rs.getTime("Create_Date").toLocalTime();
                LocalDateTime createdDate = LocalDateTime.of(cDate,cTime);

                String createdBy = rs.getString("Created_By");

                LocalDate lDate = rs.getDate("Last_Update").toLocalDate();
                LocalTime lTime = rs.getTime("Last_Update").toLocalTime();
                LocalDateTime lastUpdated = LocalDateTime.of(lDate,lTime);

                String lastUpdatedBy =rs.getString("Last_Updated_By");
                int divsionId = rs.getInt("Division_ID");
                String divisionName =rs.getString("Division");

                Customers  customer= new Customers(customerId,customerName,customerAddress,customerPhone,customerPostal, createdDate,createdBy,lastUpdated,lastUpdatedBy,divsionId,divisionName,countryId);

                //new Customers(customerId,customerName,customerAddress,customerPhone,createdDate,createdBy,lastUpdated,lastUpdatedBy,divisionId,countryId);
                customers.add(customer);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    return customers;
    }
}

