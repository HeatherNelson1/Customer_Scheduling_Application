package Controller;

/**
 * ApptMenuController displays all appointments in table view, allows update and delete of selected appt.
 */

import DAO.AppointmentsDAO;
import Model.Appointments;
import Model.OAL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.*;

public class ApptMenuController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button exit;

    @FXML
    private Button addApptBtn;

    @FXML
    private Button updateApptBtn;

    @FXML
    private Button deleteApptBtn;

    @FXML
    private AnchorPane exitBtn;

    @FXML
    private TableView<Appointments> apptTable;
    //getAllAppointments
    @FXML
    private TableColumn<Appointments, Integer> apptIdCol;

    @FXML
    private TableColumn<Appointments, String> titleCol;

    @FXML
    private TableColumn<Appointments, String> descCol;

    @FXML
    private TableColumn<Appointments, String> locationCol;

    @FXML
    private TableColumn<Appointments, Integer> contactCol;

    @FXML
    private TableColumn<Appointments, String> typeCol;

    @FXML
    private TableColumn<Appointments, Date> startDateCol;

    @FXML
    private TableColumn<Appointments, Time> startTimeCol;

    @FXML
    private TableColumn<Appointments, Date> endDateCol;

    @FXML
    private TableColumn<Appointments, Time> endTimeCol;

    @FXML
    private TableColumn<Appointments, Integer> customerIdCol;

    @FXML
    private RadioButton allRadio;
    @FXML
    private RadioButton monthRadio;

    @FXML
    private RadioButton weekRadio;

    @FXML
    private ToggleGroup sourceTG;


    @FXML
    void addAppt(MouseEvent event) throws IOException {

        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/AddAppt.fxml"));
        Scene bill = new Scene(roger);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(bill);
        window.show();
    }

    @FXML
    void backBtn(MouseEvent event) throws IOException {

        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
        Scene bill = new Scene(roger);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(bill);
        window.show();
    }

    @FXML
    void deleteAppt(MouseEvent event)
    {
        Appointments delSelectAppt = apptTable.getSelectionModel().getSelectedItem();
        try {
            if(delSelectAppt !=null) //if selected proceeds to delete
            {
                Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete Appointment? click OK to continue");
                Optional<ButtonType> result = confirmCancel.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    AppointmentsDAO.deleteAppt(delSelectAppt); //delete from db
                    OAL.deleteAppts(delSelectAppt);//delete from obs list
                    OAL.getAllAppts().clear(); //clear obs list of all apps
                    AppointmentsDAO.selectAppts(); //  sets obs list from select query to db
                    apptTable.setItems(OAL.getAllAppts());
                    setApptTable();

                    Alert apptDeleted = new Alert(Alert.AlertType.CONFIRMATION);
                    apptDeleted.setTitle("Confirmation");
                    apptDeleted.setContentText(delSelectAppt.getApptType()+"  Appointment Number: "+delSelectAppt.getApptId()+"\n" +"Cancelled/deleted");
                    apptDeleted.showAndWait();
                }
            }

        }
        catch (Exception e)
        {
        e.printStackTrace();
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

    @FXML
    void setApptTable()
    {
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));

        startDateCol.setCellValueFactory(new PropertyValueFactory<>("apptStartDate"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptStartTime"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("apptEndDate"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("apptEndTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));

        toString();
    }

    @FXML
    void radioButton(ActionEvent event) {
// filters all, week, month view to tableview


       if (sourceTG.getSelectedToggle().equals(allRadio)) {
       // if(allRadio.isSelected()){
            OAL.getAllAppts().clear();
            AppointmentsDAO.selectAppts();
            ObservableList<Appointments> allAppts = OAL.getAllAppts();  //lamda exp1



            apptTable.setItems(allAppts);
            System.out.println("loading all");
            setApptTable();

        }
        if (sourceTG.getSelectedToggle().equals(weekRadio)) {
            OAL.getWeekAppts().clear();
            ObservableList<Appointments> weekAppts = OAL.getWeekAppts();
            apptTable.setItems(weekAppts);
            System.out.println("loading week");
            setApptTable();

        }
        else if (sourceTG.getSelectedToggle().equals(monthRadio)) {
            OAL.getMonthAppts().clear();
            ObservableList<Appointments> monthAppts = OAL.getMonthAppts();
            apptTable.setItems(monthAppts);
            System.out.println("loading month");
            setApptTable();

        }

    }



/*
    @FXML
    void allAppts(MouseEvent event) {
        if (sourceTG.getSelectedToggle().equals(allRadio)) {
            ObservableList<Appointments> allAppts = OAL.getAllAppts();
            apptTable.setItems(allAppts);
            setColumns();
        }
    }

    @FXML
    void monthAppts(MouseEvent event)
    {
        if (sourceTG.getSelectedToggle().equals(monthRadio)) {
            ObservableList<Appointments> monthAppts = OAL.getMonthAppts();
            apptTable.setItems(monthAppts);
            setColumns();

        }
    }
    @FXML
        void weekAppts (MouseEvent event){
            if (sourceTG.getSelectedToggle().equals(weekRadio)) {
                ObservableList<Appointments> weekAppts = OAL.getWeekAppts();
                apptTable.setItems(weekAppts);
                setColumns();
            }
        }

*/

    /**
     * updateApptBtn mouse event to open update appointment controller to the selected appointment from the table
     * @param event
     * @throws IOException
     */
        @FXML
        void updateApptBtn(MouseEvent event) throws IOException { //check-last Edit 5/2 6/1,

            Appointments selectAppt = apptTable.getSelectionModel().getSelectedItem();
            System.out.println(selectAppt);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ViewController/UpdateAppt1.fxml"));
            Parent roger = loader.load();
            Scene bill = new Scene(roger);

            if (selectAppt == null) {
                Alert nullAppt = new Alert(Alert.AlertType.ERROR);
                nullAppt.setTitle("No Appointment Selected");
                nullAppt.setContentText("Please Select Appointment to Modify ");
                nullAppt.showAndWait();
            } else {

                UpdateApptController controller = loader.getController();
                controller.displaySelectedAppt(selectAppt); //displays the selected object
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();

            }
        }
   /* public void initialize(URL url, ResourceBundle resourceBundle){

        List<Appointments> allAppts = OAL.getAllAppts();
        apptTable.setItems(OAL.getAllAppts());

        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("Start Date"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("Start Time"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("End Date"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("End Time"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer"));


    }
*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        OAL.getAllAppts().clear();

            sourceTG = new ToggleGroup();
            allRadio.setToggleGroup(sourceTG);
            weekRadio.setToggleGroup(sourceTG);
            monthRadio.setToggleGroup(sourceTG);


        //List<Appointments> allAppts = OAL.getAllAppts(); //check-deactivate once radiobutton handler built

        AppointmentsDAO.selectAppts();
        apptTable.setItems(OAL.getAllAppts());
        setApptTable();





    }
}

