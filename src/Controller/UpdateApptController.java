package Controller;
/**
 * UpdateApptController displays appointment selected for update in the Appointment menu, updates changes to the database, updates observable list allAppointments
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

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateApptController implements Initializable {

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
    private ComboBox<Customers> customerCombo;

    @FXML
    private ComboBox<Users> userCombo;


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


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");


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
        /*
        LocalTime checkBusinessStart =apptStartTimeCombo.getSelectionModel().getSelectedItem();

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

    /**
     * updateAppt method displays the existing appointment in text fields for user
     *
     * @param appt
     */

    private Appointments selectAppt;

    @FXML
    public void displaySelectedAppt(Appointments appt) //check-last edit 5/2 5/31
    {
        DAO.AppointmentsDAO.selectAppts();
        selectAppt = appt;

        //set each to show string.value of
        appointmentId.setText(String.valueOf(selectAppt.getApptId())); //inactivate
        apptTitle.setText(String.valueOf(selectAppt.getApptTitle()));
        apptDescription.setText(String.valueOf(selectAppt.getApptDescription()));
        apptLocation.setText(String.valueOf(selectAppt.getApptLocation()));
        contactCombo.setSelectionModel(contactCombo.getSelectionModel());//is this working? 5/31 check
        apptTypeCombo.setValue(String.valueOf(selectAppt.getApptType()));
        apptStartDate.setValue(selectAppt.getApptStartDate());
        apptEndDate.setValue(selectAppt.getApptEndDate());

        apptStartTimeCombo.setValue((selectAppt.getApptStartTime()));
     //  ZonedDateTime zdtStart =
        apptEndTimeCombo.setValue(selectAppt.getApptEndTime());

        //userCombo.setValue(userCombo.getValue());
        //customerCombo.setSelectionModel(customerCombo.getItems());

        for (Contacts contact : contactCombo.getItems()) {
            if (selectAppt.getContactId() == contact.getContactId()) {
                contactCombo.setValue(contact);
                break;
            }
        }
        for (Customers customer : customerCombo.getItems()) {
            if (selectAppt.getCustomerId() == customer.getCustomerId()) {
                customerCombo.setValue(customer);
                break;
            }
        }
        for (Users user : userCombo.getItems()) {
            if (selectAppt.getUserId() == user.getUserId()) {
                userCombo.setValue(user);
                break;
            }

        }
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
        Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to cancel changes?");

        Optional<ButtonType> result = confirmCancel.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
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
     * saveAppt mouse event run
     *
     * @param event saveAppt run updateApptList removing the old appt and adding new reflecting the changes made
     * @throws Exception
     */
    @FXML
    void saveAppt(MouseEvent event) throws IOException {
        try {
            int id = Integer.parseInt(appointmentId.getText());//inactivate
            String title = apptTitle.getText();
            String desc = apptDescription.getText();
            String location = apptLocation.getText();
            String type = apptTypeCombo.getSelectionModel().getSelectedItem();
            LocalDate startDate = apptStartDate.getValue();
            LocalTime startTime = apptStartTimeCombo.getSelectionModel().getSelectedItem();
            LocalDate endDate = apptEndDate.getValue();
            LocalTime endTime = apptEndTimeCombo.getSelectionModel().getSelectedItem();
            LocalDate createdDate = LocalDate.now();
            LocalTime createdTime = LocalTime.now();
            String creator = selectAppt.getCreatedBy();
            Timestamp updateDate = Timestamp.valueOf(LocalDateTime.now());
            String updatedBy = selectAppt.getApptlastUpdatedBy();
            int customerId = customerCombo.getSelectionModel().getSelectedItem().getCustomerId();
            String customerName = String.valueOf(customerCombo.getSelectionModel().getSelectedItem());
            int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();
            String contactName = String.valueOf(contactCombo.getSelectionModel().getSelectedItem());
            String u = DataProvider.userId;
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
            System.out.println("EST" + lEnd + "PST" + endTime);

            checkOverlapAppt();

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

                        Appointments updated = new Appointments(id, title, desc, location, type, startDate, startTime, endDate, endTime, createdDate, createdTime, creator, updateDate, updatedBy, customerId, customerName, contactId, contactName, user);

                        int del = selectAppt.getApptId();
                        DAO.AppointmentsDAO.updateAppt(updated);

                        if (DataProvider.apptUpdated == true) {
                            OAL.updateApptList(del, updated);
                            Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/ApptMenu.fxml"));
                            Scene bill = new Scene(roger);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(bill);
                            window.show();
                        } else {
                            Alert cannotLoad = new Alert(Alert.AlertType.ERROR);
                            cannotLoad.setTitle("Update Failed");
                            cannotLoad.setContentText("Appointment Update Failed");
                            cannotLoad.showAndWait();
                        }
                    }
                    else if (overlap==true)
                    {
                        overlapAlert();
                    }

                }


            }

        }
        catch (NullPointerException e) {
            Alert nullValue = new Alert(Alert.AlertType.ERROR);
            nullValue.setTitle("Incomplete");
            nullValue.setContentText("Please Complete All Fields");
            nullValue.showAndWait();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCustomerCombo(ActionEvent event) {
        toString();
    }


    @FXML
    void onUsercombo(ActionEvent event) {}



    /**
     * overlap method errors when called during appointment save if appointment scheduled has an overlapping start or end time for the customer.
     */
    public void checkOverlapAppt(){
        overlap=false;
        int currentCustId = customerCombo.getSelectionModel().getSelectedItem().getCustomerId();
        int currentApptId = Integer.valueOf(appointmentId.getText());

        for (Appointments a : OAL.getAllAppts()) {
            int acustId = a.getCustomerId();
            int apptId = a.getApptId();

            if (acustId != currentCustId) {
                continue; //sends back through the loop if not found
            }

            if (apptId != currentApptId) {

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

                /*
                //1 ns<=xs && (ne<=xe && ne>=xs)   END is between
                if ((nSDT.isBefore(xSDT) || nSDT.equals(xSDT))
                        && ((nEDT.isAfter(xSDT) && ((nEDT.isBefore(xEDT) || nEDT.equals(xEDT)))))) {
                    overlap = true;
                    System.out.println("1");
                    break;
                }

                 */

                //2 bookends-start ns<=xs && ne<=xe  existing starts/ends during new
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
                /*
                //3 ns>=xs && <=xe && ne>=xe start is between
                if ((nSDT.isAfter(xSDT)||(nSDT.equals(xSDT) && (nSDT.isBefore(xEDT))))
                    && ((nEDT.isAfter(xEDT) || nEDT.equals(xEDT)))) {
                    overlap = true;
                    System.out.println("3");
                    break;
                }

                 */
                /*
                //4 between ns>=xs && ne<=xe new starts/ends during existing
                if (nSDT.isAfter(xSDT)||(nSDT.equals(xSDT)) && (nEDT.isBefore(xEDT) || nEDT.equals(xEDT))) {
                    overlap = true;
                    System.out.println("4");
                    break;
                                 }

                 */
                //starts in between/during existing appt
            }
            continue;

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
        ContactsDAO.selectContacts();

        ObservableList<Customers> customers = OAL.getAllCustomers();
        customerCombo.setItems(customers);
        customerCombo.setVisibleRowCount(10);
        customerCombo.setPromptText("Select Customer");
        //CustomersDAO.queryCustomers();


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





