package Controller;
/**
 * ScheduleReport Controller displays report of schedules by selected contact.
 */

import DAO.ContactsDAO;
import Model.Appointments;
import Model.Contacts;
import Model.OAL;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ScheduleReportController implements Initializable {



        @FXML
        private Button backBtn;

        @FXML
        private Button exitBtn;

        @FXML
        private TableView<Contacts> contactsTableView;


        @FXML
        private TableColumn<Contacts, Integer> contactId;

        @FXML
        private TableColumn<Contacts, String> contactName;

        @FXML
        private Button viewSchedule;


        @FXML
        private TableView<Appointments> scheduleTableView;

    @FXML
    private TableColumn<Appointments, Integer> customerId;

    @FXML
    private TableColumn<Appointments, Integer> apptId;


    @FXML
    private TableColumn<Appointments, String> apptTitle;

    @FXML
    private TableColumn<Appointments, String> apptType;

    @FXML
    private TableColumn<Appointments, String> apptDescription;

    @FXML
    private TableColumn<Appointments, LocalDateTime> startDate;
    //***what is the wrapper class for the local date time?

    @FXML
    private TableColumn<Appointments, LocalDateTime> startTime;

    @FXML
    private TableColumn<Appointments, LocalDateTime> endDate;

    @FXML
    private TableColumn<Appointments, LocalDateTime> endTime;

    @FXML
    private Button resetSelection;



        @FXML
        void backBtn(MouseEvent event) throws IOException {

            Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
            Scene bill = new Scene(roger);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(bill);
            window.show();
        }

        @FXML
        void clearSelection(MouseEvent event) {
//when clicking the reset button
            OAL.getApptByContact().clear();
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
     * @param event viewCustomerSchedule method displays the appointments in table view based on the selection from customer table.
     * viewCustomerSchedule lambda filters appointments based on contact id. This improved efficency as previously set to a separate observable list. The filter allows quick assessment whether contact selected matches contact listed for any appointments and return
     * those appointments in the scheduleTableView.
     */
    @FXML
        void viewCustomerSchedule(MouseEvent event)  {
            /*selected customer
            getAllAppointments
            */
          //dont' think i'll need this 7/10 check  DAO.ContactsDAO.selectApptsbyContact();

            Contacts selectContact = contactsTableView.getSelectionModel().getSelectedItem();
            int contactId = selectContact.getContactId();

            if(selectContact != null){

                ObservableList<Appointments> cList=OAL.getAllAppts().filtered(a->{
                    if (a.getContactId()==contactId)
                    {
                        return true;
                    }
                    return false;
                });

                scheduleTableView.setItems(cList);




                /*
                OAL.getApptByContact().clear();
                for (Appointments appt : OAL.getAllAppts()) {
                    int apptContactId = appt.getContactId();
                    if (contactId == apptContactId) {
                        //OAL set tot the table
                        OAL.addApptsByContact(appt);
                        scheduleTableView.setItems(OAL.getApptByContact());
                         }

                }
                */
            }
        }

    /**
     * setContactTable sets the column names for contact table
     */
    @FXML
        void setContactTable()
        {
                contactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
                contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        }

    /**
     * setApptTable sets the column names for Appointment table
     */
    @FXML
        void setApptTable()
        {

            customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            apptId.setCellValueFactory(new PropertyValueFactory<>("apptId"));
            apptTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
            apptType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
            apptDescription.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
            startDate.setCellValueFactory(new PropertyValueFactory<>("apptStartDate"));
            startTime.setCellValueFactory(new PropertyValueFactory<>("apptStartTime"));
            endDate.setCellValueFactory(new PropertyValueFactory<>("apptEndDate"));
            endTime.setCellValueFactory(new PropertyValueFactory<>("apptEndTime"));

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
            DAO.AppointmentsDAO.selectAppts();
            OAL.getAllContacts().clear();
            ContactsDAO.selectContacts();

            contactsTableView.setItems(OAL.getAllContacts());
            setContactTable();


            setApptTable();

        }

        //*****************end*******************
    }





