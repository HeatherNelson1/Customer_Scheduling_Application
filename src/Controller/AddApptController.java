
package Controller;
/**
 * addApptController adds a new appointment
 */

import DAO.*;
import Model.*;
import Utilities.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;




public class AddApptController implements Initializable {

        LocalTime open = LocalTime.of(8, 0, 0);
        LocalTime close = LocalTime.of(22, 0, 0);
        boolean validAppt = false;
        boolean overlap = false; //false=no overlap, true-overlap



        @FXML
        private Button saveBtn;

        @FXML
        private Button cancelAddBtn;

        @FXML
        private Button backBtn;

        @FXML
        private Button exitBtn;

        @FXML
        private TextField appointmentId;

        @FXML
        private TextField apptTitle;

        @FXML
        private TextField apptDescription;

        @FXML
        private TextField apptLocation;

        @FXML
        public ComboBox<Contacts> contactCombo;

        @FXML
        private ComboBox<Users> userCombo;

        @FXML
        private ComboBox<Customers> customerCombo;


        @FXML
        private ComboBox<String> apptTypeCombo;

        @FXML
        private DatePicker apptStartDate;

        @FXML
        private ComboBox<LocalTime> apptStartTimeCombo;

        @FXML
        private DatePicker apptEndDate;

        @FXML
        private ComboBox<LocalTime> apptEndTimeCombo;

        @FXML
        private TextField userId;

        @FXML
        private TextField customerId;


        @FXML
        void onApptTypeCombo(ActionEvent event) {

        }

        @FXML
        void onContactCombo(ActionEvent event) {
                toString();

        }


        @FXML
        void onEndTimeCombo(ActionEvent event) {
                /*
                LocalTime checkBusinessEnd = apptEndTimeCombo.getSelectionModel().getSelectedItem();

                if (checkBusinessEnd.isBefore(busStartTime)||checkBusinessEnd.isAfter(busEndTime))
                {
                        Alert cannotLoad = new Alert(Alert.AlertType.ERROR);
                        cannotLoad.setTitle("Outside business hours");
                        cannotLoad.setContentText("Please select appointment within business hours");
                        cannotLoad.showAndWait();
                        validAppt=false;
                }
                else
                        {
                        validAppt = true;
                }
*/
        }


        @FXML
        void onStartTimeCombo(ActionEvent event) {

              /* LocalTime checkBusinessStart =apptStartTimeCombo.getSelectionModel().getSelectedItem();

                if (checkBusinessStart.isBefore(busStartTime)||checkBusinessStart.isAfter(busEndTime))
                {
                        Alert cannotLoad = new Alert(Alert.AlertType.ERROR);
                        cannotLoad.setTitle("Outside business hours");
                        cannotLoad.setContentText("Please select appointment within business hours");
                        cannotLoad.showAndWait();
                        validAppt=false;
                }
                else
                {
                        validAppt = true;
                }

               */
        }


        @FXML
        void backBtn(MouseEvent event) throws IOException {

                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/ApptMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
        }

        @FXML
        void cancelAdd(MouseEvent event) throws IOException {

                Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Cancel, Click OK To Continue Without Saving");
                Optional<ButtonType> result = confirmCancel.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/ApptMenu.fxml"));
                        Scene bill = new Scene(roger);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(bill);
                        window.show();
                }
        }

        @FXML
        void exitToMain(MouseEvent event) throws IOException {

                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();

        }

        /**
         * saveAppt creates new appt object from user entered text, passes to the apptDAO to the db.
         *
         * @param event
         * @throws Exception
         */
        @FXML
        void saveAppt(MouseEvent event) throws IOException {

                try {
                        int id = 1;//Integer.parseInt(appointmentId.getText()); //run the id# from the result hms 5/24 set to 1 to get in to save
                        String title = apptTitle.getText();
                        String desc = apptDescription.getText();
                        String location = apptLocation.getText();
                        String type = apptTypeCombo.getSelectionModel().getSelectedItem();
                        LocalDate startDate = apptStartDate.getValue();
                        LocalTime startTime = apptStartTimeCombo.getSelectionModel().getSelectedItem(); //users time zone
                        LocalDate endDate = apptEndDate.getValue();
                        LocalTime endTime = apptEndTimeCombo.getSelectionModel().getSelectedItem();

                        LocalDate createdDate = LocalDate.now();
                        LocalTime createdTime = LocalTime.now();
                        String creator = DataProvider.userId;
                        Timestamp updateDate = Timestamp.valueOf(LocalDateTime.now());
                        String updatedBy = DataProvider.userId;
                        int customerId = customerCombo.getSelectionModel().getSelectedItem().getCustomerId();
                        String customerName = String.valueOf(customerCombo.getSelectionModel().getSelectedItem());
                        int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();
                        String contactName = String.valueOf(contactCombo.getSelectionModel().getSelectedItem());
                        int user = DataProvider.uID;

                        //Converts start time to EST to compare against business hours
                        LocalDateTime sLdt = LocalDateTime.of(startDate, startTime);
                        ZonedDateTime zstart = sLdt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
                        ZonedDateTime sZdt = zstart.withZoneSameInstant(ZoneId.of("America/New_York"));
                        LocalDateTime ldtStart = sZdt.toLocalDateTime();
                        LocalTime lStart = ldtStart.toLocalTime(); //coverted to EST
                        System.out.println("EST" + lStart + "PST" + startTime);

                        //Converts start time to EST to compare against business hours
                        LocalDateTime eLdt = LocalDateTime.of(endDate, endTime);
                        ZonedDateTime zEnd = eLdt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
                        ZonedDateTime eZdt = zEnd.withZoneSameInstant(ZoneId.of("America/New_York"));
                        LocalDateTime ldtEnd = eZdt.toLocalDateTime();
                        LocalTime lEnd = ldtEnd.toLocalTime();



                        if (lEnd.isBefore(lStart)) {
                                Alert cannotLoad = new Alert(Alert.AlertType.ERROR);
                                cannotLoad.setTitle("Start Before End");
                                cannotLoad.setContentText("Please Set Start Time Before End Time");
                                cannotLoad.showAndWait();
                                validAppt = false;

                        } else {

                                if (lStart.isBefore(open) || lStart.isAfter(close) || lEnd.isAfter(close) || lEnd.isBefore(open)) {
                                        Alert outsideHours = new Alert(Alert.AlertType.ERROR);
                                        outsideHours.setTitle("Outside business hours");
                                        outsideHours.setContentText("Selected Start: " + lStart + " or selected end: " + lEnd + " falls outside business hours. " + " Please select appointment within business hours 8:00am-10:00pm EST");
                                        outsideHours.showAndWait();
                                        validAppt = false;
                                        System.out.println(validAppt);
                                } else {
                                        validAppt = true;
                                        System.out.println(validAppt);

                                        checkOverlapAppt();


                                        if (overlap == false) {
                                                Appointments apptNew = new Appointments(id, title, desc, location, type, startDate, startTime, endDate, endTime, createdDate, createdTime, creator, updateDate, updatedBy, customerId, customerName, contactId, contactName, user);

                                                System.out.println(OAL.getAllAppts());
                                                DAO.AppointmentsDAO.addAppt(apptNew); //calls the add method adding to the database

                                                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/ApptMenu.fxml"));
                                                Scene bill = new Scene(roger);
                                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                window.setScene(bill);
                                                window.show();
                                        }
                                        else if(overlap==true)
                                        {
                                                overlapAlert();
                                        }


                                }
                        }
                } catch (NullPointerException e) {
                        Alert nullValue = new Alert(Alert.AlertType.ERROR);
                        nullValue.setTitle("Incomplete");
                        nullValue.setContentText("Please Complete All Fields");
                        nullValue.showAndWait();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }





       @FXML
       void onCustomerCombo(ActionEvent event) {
               toString();
       }



        @FXML
        void onUsercombo(ActionEvent event) {
                toString();

        }



        /**
         * overlap method errors when called during appointment save if appointment scheduled has an overlapping start or end time for the customer.
         */
        public void checkOverlapAppt() {
                overlap = false;
                int currentCustId = customerCombo.getSelectionModel().getSelectedItem().getCustomerId();

                for (Appointments a : OAL.getAllAppts()) {
                        int acustId = a.getCustomerId();

                        if (acustId != currentCustId) {
                                continue; //sends back through the loop if not found
                        }

                        LocalTime xStartTime = a.getApptStartTime();
                        LocalDate xStartDate = a.getApptStartDate();
                        LocalDateTime xSDT = LocalDateTime.of(xStartDate, xStartTime);

                        LocalTime xEndTime = a.getApptEndTime();
                        LocalDate xEndDate = a.getApptStartDate();
                        LocalDateTime xEDT = LocalDateTime.of(xEndDate, xEndTime); //existing appointment for customer

                        LocalTime nStartTime = apptStartTimeCombo.getSelectionModel().getSelectedItem();
                        LocalDate nStartDate = apptStartDate.getValue();
                        LocalDateTime nSDT = LocalDateTime.of(nStartDate, nStartTime);

                        LocalTime nEndTime = apptEndTimeCombo.getSelectionModel().getSelectedItem();
                        LocalDate nEndDate = apptEndDate.getValue();
                        LocalDateTime nEDT = LocalDateTime.of(nEndDate, nEndTime);


                        if ((nSDT.isBefore(xSDT) || nSDT.equals(xSDT)) && (nEDT.isAfter(xEDT) || nEDT.equals(xEDT))) {
                                overlap = true;
                                System.out.println("1-starts before and ends after");
                                break;

                        }
                        if ((nSDT.isAfter(xSDT) && nSDT.isBefore(xEDT))) {
                                overlap = true;
                                System.out.println("2-starts in between/during existing appt");
                                break;

                        }
                        //ends in between/during existing appt
                        if ((nEDT.isAfter(xSDT) && (nEDT.isBefore(xEDT)))) {
                                overlap = true;
                                System.out.println("3- ends in between/during existing appt");
                                break;

                        }
                }


        }




        public void overlapAlert()
        {
                Alert overlapAlert = new Alert(Alert.AlertType.ERROR);
                overlapAlert.setTitle("Overlapping Appointment ");
                overlapAlert.setContentText("Selected Appointment Overlaps with Existing Customer Appointment");
                overlapAlert.showAndWait();

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                appointmentId.setDisable(true);

                ObservableList<LocalTime> startTimesList = FXCollections.observableArrayList();
                LocalTime startTime = LocalTime.of(04,30);
                startTimesList.add(startTime);

                for (int i=0; i<38; i++)
                {
                        startTime = startTime.plusMinutes(30); //startTime.plusHours(1);
                        startTimesList.add(startTime);
                }
                ObservableList<LocalTime> endTimesList = FXCollections.observableArrayList();
                LocalTime endTime = LocalTime.of(4,30);
                endTimesList.add(endTime);

                for (int i=0; i<38; i++)
                {
                        endTime = endTime.plusMinutes(30); //endTime.plusHours(1);
                        endTimesList.add(endTime);
                }

                ObservableList<LocalDate> startDateList = FXCollections.observableArrayList();
                LocalDate startDate = LocalDate.of(2021,5,1);
                startDateList.add(startDate);


                ObservableList<LocalDate> endDateList = FXCollections.observableArrayList();
                LocalDate endDate = LocalDate.of(2021,5,1);
                endDateList.add(endDate);
                apptStartTimeCombo.setItems(startTimesList);
                apptEndTimeCombo.setItems(endTimesList);

                ObservableList<Contacts> contacts = OAL.getAllContacts();
                contactCombo.setItems(contacts);
                contactCombo.setVisibleRowCount(10);
                contactCombo.setPromptText("Select Contact");


                ObservableList<Customers> customers = OAL.getAllCustomers();
                customerCombo.setItems(customers);
                customerCombo.setVisibleRowCount(10);
                customerCombo.setPromptText("Select Customer");

                ObservableList<Users> user = OAL.getAllUsers();
                userCombo.setItems(user);
                userCombo.setVisibleRowCount(10);
                userCombo.setPromptText("Select User");
                UserDAO.queryUser();



                ObservableList<String> type = FXCollections.observableArrayList("Established","New");
                apptTypeCombo.setItems(type);
                apptTypeCombo.setVisibleRowCount(10);
                apptTypeCombo.setPromptText("Select Type");


        }
}





