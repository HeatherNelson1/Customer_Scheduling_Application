package DAO;
/**
 * AppointmentsDAO connects to database, houses select, update and delete methods.
 */

import Model.Appointments;
import Model.OAL;
import Utilities.DBConnection;
import Utilities.DBQuery;
import Utilities.DataProvider;
import Utilities.GeneralInterface;
import com.mysql.cj.util.DnsSrv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZoneId;
import javax.print.DocFlavor;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * AppointmentDAO class holds CRUD
 */

public class AppointmentsDAO {



    /**
     * queryAppts runs select query to db returning all appointments and sets result set to apptResult object
     */
    public static void selectAppts()

    {
        OAL.getAllAppts().clear();
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT  appointments.*, Contact_Name FROM appointments,contacts where appointments.Contact_ID=contacts.Contact_ID";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
               // LocalDateTime apptStartDate = rs.getDate("Start").toLocalDateTime();

                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime zdt = apptStart.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime ldtStart = zdt.toLocalDateTime();
                LocalDate apptStartDate = ldtStart.toLocalDate();
                //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                LocalTime apptStartTime = ldtStart.toLocalTime();
                               // String s = dtf.format(apptStartDate);
               // LocalDateTime apptS = LocalDateTime.parse(s,dtf);
              //  System.out.println(apptS);


                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();//gets db end time stored as timestamp in db, converts to localdatetime as var apptEnd
                ZonedDateTime zdtEnd = apptEnd.atZone(ZoneId.of(ZoneId.systemDefault().toString())); //convert to zonedatetime-time based on system default of user
                LocalDateTime ldtEnd = zdtEnd.toLocalDateTime();//convert the zoned to local date time
                LocalDate apptEndDate = ldtEnd.toLocalDate(); //local date time splits to date  (model has separate)
                LocalTime apptEndTime = ldtEnd.toLocalTime(); //local date time splits to time (model has separate)

                LocalDate createdDate = rs.getDate("Create_Date").toLocalDate();
                LocalTime createdTime = rs.getTime("Create_Date").toLocalTime();
                String createdBy = rs.getString("Created_By");
                Timestamp apptLastUpdateDate = rs.getTimestamp("Last_Update");
                String apptlastUpdateBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                String customerName = null; //rs.getInt("Customer_ID");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                int userId = rs.getInt("User_ID");

                Appointments apptResult = new Appointments(apptId, apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptStartTime, apptEndDate, apptEndTime, createdDate, createdTime, createdBy, apptLastUpdateDate, apptlastUpdateBy, customerId,customerName,contactId,contactName, userId);

                OAL.addAppts(apptResult);
               // OAL.getWeekAppts().add(apptResult);

              //  List<Appointments> allAppts = OAL.getAllAppts();
                int count = rs.getInt((1));
                System.out.println(apptResult);
            }

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void queryByApptType()
    {
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM appointments ORDER BY month(Start);";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                // LocalDateTime apptStartDate = rs.getDate("Start").toLocalDateTime();

                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime zdt = apptStart.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime ldtStart = zdt.toLocalDateTime();
                LocalDate apptStartDate = ldtStart.toLocalDate();
                //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                LocalTime apptStartTime = ldtStart.toLocalTime();
                // String s = dtf.format(apptStartDate);
                // LocalDateTime apptS = LocalDateTime.parse(s,dtf);
                //  System.out.println(apptS);


                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();//gets db end time stored as timestamp in db, converts to localdatetime as var apptEnd
                ZonedDateTime zdtEnd = apptEnd.atZone(ZoneId.of(ZoneId.systemDefault().toString())); //convert to zonedatetime-time based on system default of user
                LocalDateTime ldtEnd = zdtEnd.toLocalDateTime();//convert the zoned to local date time
                LocalDate apptEndDate = ldtEnd.toLocalDate(); //local date time splits to date  (model has separate)
                LocalTime apptEndTime = ldtEnd.toLocalTime(); //local date time splits to time (model has separate)

                LocalDate createdDate = rs.getDate("Create_Date").toLocalDate();
                LocalTime createdTime = rs.getTime("Create_Date").toLocalTime();
                String createdBy = rs.getString("Created_By");
                Timestamp apptLastUpdateDate = rs.getTimestamp("Last_Update");
                String apptlastUpdateBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                String customerName = null; //rs.getInt("Customer_ID");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                int userId = rs.getInt("User_ID");

                Appointments apptByTypeResult = new Appointments(apptId, apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptStartTime, apptEndDate, apptEndTime, createdDate, createdTime, createdBy, apptLastUpdateDate, apptlastUpdateBy, customerId,customerName,contactId,contactName, userId);

                OAL.addapptsByType(apptByTypeResult);

                int count = rs.getInt((1));
                System.out.println(apptByTypeResult);
            }

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * addAppt adds new appointment to database from Appoitnment object passed from the addApptController
     * @param appointments
     * @throws Exception
     */
    public static void  addAppt(Appointments appointments) throws Exception  {
        try{
        Connection conn = DBConnection.getConnection();
        String insertStatement = "INSERT INTO appointments(Title,Description,Location,Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        //int apptid = appointments.getApptId();
        //String id = String.valueOf(apptid);

        String title = appointments.getApptTitle();
        String desc = appointments.getApptDescription();
        String location = appointments.getApptLocation();
        String type = appointments.getApptType();

        LocalDate startDate = appointments.getApptStartDate();
        LocalTime startTime = appointments.getApptStartTime();
        LocalDateTime startLDT = LocalDateTime.of(startDate,startTime);
        Timestamp startTS = Timestamp.valueOf(startLDT);

        LocalDate endDate = appointments.getApptEndDate();
        LocalTime endTime = appointments.getApptEndTime();
        LocalDateTime endLDT = LocalDateTime.of(endDate,endTime);
        Timestamp endTS = Timestamp.valueOf(endLDT);

        String start = String.valueOf(startLDT);
        String end = String.valueOf(endLDT);

        LocalDate cdate = appointments.getCreatedDate();
        String createdDate = String.valueOf(cdate);

        String creator = appointments.getCreatedBy();


        Timestamp up = appointments.getApptlastUpdateDate();
        String updateDate = String.valueOf(up);

        String lastUpdatedBy = appointments.getApptlastUpdatedBy();

        int customer = appointments.getCustomerId(); //**check-once queryCustomers working remove null
        int user = appointments.getUserId();
        int contact = appointments.getContactId();

        ps.setString(1, title);
        ps.setString(2, desc);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startTS);
        ps.setTimestamp(6, endTS);
        ps.setString(7, createdDate);
        ps.setString(8, creator);
        ps.setString(9,  updateDate);
        ps.setString(10, lastUpdatedBy);
        ps.setInt(11, customer);
        ps.setInt(12, user);
        ps.setInt(13, contact);


        /*
        apptid title des loc cont appt type start date time user id customer 12
         */

            ps.execute();
            if (ps.getUpdateCount()>0)
            {
                System.out.println(getLastId());//hms 5/24
                if (getLastId()>0)
                {
                    appointments.setApptId(getLastId());
                    OAL.addAppts(appointments);
                }


            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static int getLastId() throws Exception {
        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM appointments WHERE Appointment_ID=(SELECT max(Appointment_ID) FROM appointments)";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                return apptId;
            }
        }
                   catch (SQLException e)
                {
                    e.printStackTrace();
                }

       // }
        return -1;
    }

    /**
     * updateAppt accepts Appointment object from the UpdateApptointmentController and updates database passing updated fields.
     * @param appointments
     * @throws Exception
     */

    public static void updateAppt(Appointments appointments) throws Exception {

    try{
        Connection conn = DBConnection.getConnection();
        String updateStatement =
                "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?,Start=?,End=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=?,Customer_ID=?,User_ID=?,Contact_ID=? WHERE Appointment_ID=?";

        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();


        int apptid = appointments.getApptId();
       // String id = String.valueOf(apptid);

        String title = appointments.getApptTitle();
        String desc = appointments.getApptDescription();
        String location = appointments.getApptLocation();
        String type = appointments.getApptType();

        LocalDate startDate = appointments.getApptStartDate();
        LocalTime startTime = appointments.getApptStartTime();
        LocalDateTime startLDT = LocalDateTime.of(startDate,startTime);
        Timestamp startTS = Timestamp.valueOf(startLDT);

        LocalDate endDate = appointments.getApptEndDate();
        LocalTime endTime = appointments.getApptEndTime();
        LocalDateTime endLDT = LocalDateTime.of(endDate,endTime);
        Timestamp endTS = Timestamp.valueOf(endLDT);

        String start = String.valueOf(startLDT);
        String end = String.valueOf(endLDT);

        LocalDate cdate = appointments.getCreatedDate();
        String createdDate = String.valueOf(cdate);

        String creator = appointments.getCreatedBy();

        Timestamp updateDate = appointments.getApptlastUpdateDate();
      // String updateDate = String.valueOf(up);

       // String userId = DataProvider.userId;
        String lastUpdatedBy= appointments.getApptlastUpdatedBy();

        int customer = appointments.getCustomerId(); //**check-once queryCustomers working remove null
        int user = appointments.getUserId();
        int contact = appointments.getContactId();
//        DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      //  LocalDateTime localDateTimeStart = LocalDateTime.parse(startTime, datetimeDTF);



      //  System.out.println(localDateTimeStart);



        //ps.setString(1, appId);
        ps.setString(1, title);
        ps.setString(2, desc);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startTS);
        ps.setTimestamp(6, endTS);
        ps.setString(7, createdDate);
        ps.setString(8, creator);
        ps.setTimestamp(9, updateDate);
        ps.setString(10, lastUpdatedBy);
        ps.setInt(11, customer);
        ps.setInt(12, user);
        ps.setInt(13, contact);
        ps.setInt(14, apptid);



        /*
        apptid title des loc cont appt type start date time user id customer 12
         */
        ps.execute();
        if (ps.getUpdateCount()>0) {
           // System.out.println(getLastId());//hms 5/24
            {
                DataProvider.apptUpdated=true;
            }


        }
    }
        catch (SQLException e)
    {
        e.printStackTrace();
    }
    }



    /**
     * deleteAppt method removes appointment from appointment table based on given appointment Id
     * @param appointments
     * @throws Exception
     */
    public static void deleteAppt(Appointments appointments) throws Exception {
        try {

            Connection conn = DBConnection.getConnection();
            String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID=?";

            DBQuery.setPreparedStatement(conn, deleteStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            int apptid = appointments.getApptId();
            String id = String.valueOf(apptid);


            ps.setString(1, id);

            ps.execute();

            if (ps.getUpdateCount() > 0) {
                int delCount = ps.getUpdateCount();
                System.out.println(delCount + "deleted");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ObservableList<Appointments> apptByType = FXCollections.observableArrayList();//empty array list


    /**
     * getApptBytype Obs list method queries database with month, type, values selected by user.
     * returns appointments and provides count
     *  lambda expression Improves the code by making the method to count the size of the observable list easier
     *  to re-use for any list throughout the program. Previous method was limited to this specific list only.
     */
    public static ObservableList<Appointments> getapptByType(int month, String type) {
        AppointmentsDAO.apptByType.clear();

        try {
            Connection conn = DBConnection.getConnection();
            String selectStatement = "SELECT * FROM appointments WHERE month(Start) =? and Type=?";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();

            ps.setInt(1, month);
            ps.setString(2, type);

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int apptId = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                String apptType = rs.getString("Type");
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                ZonedDateTime zdt = apptStart.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
                LocalDateTime ldtStart = zdt.toLocalDateTime();
                LocalDate apptStartDate = ldtStart.toLocalDate();
                LocalTime apptStartTime = ldtStart.toLocalTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();//gets db end time stored as timestamp in db, converts to localdatetime as var apptEnd
                ZonedDateTime zdtEnd = apptEnd.atZone(ZoneId.of(ZoneId.systemDefault().toString())); //convert to zonedatetime-time based on system default of user
                LocalDateTime ldtEnd = zdtEnd.toLocalDateTime();//convert the zoned to local date time
                LocalDate apptEndDate = ldtEnd.toLocalDate(); //local date time splits to date  (model has separate)
                LocalTime apptEndTime = ldtEnd.toLocalTime(); //local date time splits to time (model has separate)
                LocalDate createdDate = rs.getDate("Create_Date").toLocalDate();
                LocalTime createdTime = rs.getTime("Create_Date").toLocalTime();
                String createdBy = rs.getString("Created_By");
                Timestamp apptLastUpdateDate = rs.getTimestamp("Last_Update");
                String apptlastUpdateBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                String customerName = null; //rs.getInt("Customer_ID");
                int contactId = rs.getInt("Contact_ID");
                String contactName = null;
                int userId = rs.getInt("User_ID");

                Appointments apptResult = new Appointments(apptId, apptTitle, apptDescription, apptLocation, apptType, apptStartDate, apptStartTime, apptEndDate, apptEndTime, createdDate, createdTime, createdBy, apptLastUpdateDate, apptlastUpdateBy, customerId, customerName, contactId, contactName, userId);
                apptByType.add(apptResult); //adds the query results to apptByType OAL



                //int countApptByMonthType = rs.getInt((1));
               // System.out.println(apptByType.size());
            }
           // DataProvider.apptByTypeCount = apptByType.size();

            /**
             * lambda expression
             * Improves the code by making the method to count the size of the observable list easier to re-use for any list throughout the program.
             * Previous method was limited to this specific list only.
             */

            GeneralInterface count = o -> o.size();
            DataProvider.apptByTypeCount = count.obsListSize(apptByType);
            System.out.println(count.obsListSize(apptByType)+"  number of appts in the list as of  "+ LocalDateTime.now());





        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptByType;
    }


}