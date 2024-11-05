package DAO;
/**
 * ContactsDAO runs select queries from the Contacts table in the database
 */

import Model.Contacts;
import Model.Countries;
import Model.OAL;
import Utilities.DBConnection;
import Utilities.DBQuery;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static Utilities.DBQuery.setPreparedStatement;

public class ContactsDAO {
    /**
     * runs select query to db returning all contacts and sets result set to contactResult object
     */
    public static void selectContacts()    {
        OAL.getAllContacts().clear();
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM contacts";
            setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {

                int contactId = rs.getInt("Contact_ID");
                String contactName =rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contacts contactResult = new Contacts(contactId,contactName,contactEmail);

                OAL.getAllContacts().add(contactResult);

                //adds all returned contacts to the OAL
                int count = rs.getInt((1));
            }



            //method sort results all contacts
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void  selectApptsbyContact() {
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM contacts";//returns appts by selected contact
            setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {

                //use format: int contactId = rs.getInt("Contact_ID");

                int customerId;
                int apptId;
                String apptTitle;
                String apptType;
                String apptDesc;
                LocalDateTime startDateTime;
                LocalDateTime endDateTime;
                //create new object Contacts contactResult = new Contacts(contactId,contactName,contactEmail);


                // OAL.getAllContacts().add(contactResult); //adds all returned contacts to the OAL
                // int count = rs.getInt((1));
                // System.out.println(contactResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

