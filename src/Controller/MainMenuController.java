package Controller;
/**
 * MainMenuController displays buttons to the appointment menu, customer menu, appointment by type report, appointments/schedule by contact,  customer by country report,
 */

import DAO.AppointmentsDAO;
import DAO.CountriesDAO;
import Model.Appointments;
import Model.Countries;
import Model.OAL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable{


        @FXML
        private Button exit;

        @FXML
        private Button apptMenuBtn;

        @FXML
        private Button customerMenuBtn;

        @FXML
        private Button apptTypeReportBtn;

        @FXML
        private Button scheduleReportBtn;

        @FXML
        private Button customReportBtn;



        @FXML
        void apptMenu(MouseEvent event) throws IOException {

                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/ApptMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
        }
        @FXML
        void apptReport(MouseEvent event)   throws IOException {

                        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/ApptReport.fxml"));
                        Scene bill = new Scene(roger);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(bill);
                        window.show();
                }

        @FXML
        void customerByCountry(MouseEvent event) throws IOException {
                        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/CustomersByCountryReport.fxml"));
                        Scene bill = new Scene(roger);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(bill);
                        window.show();

        }


        @FXML
        void customerMenu(MouseEvent event) throws IOException {

                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/CustomerMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
        }

        @FXML
        void exitToMain(MouseEvent event) throws IOException {
                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/LogIn.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
        }


        @FXML
        void schedReport(MouseEvent event) throws IOException {
                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/ScheduleReport.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();

        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
}



