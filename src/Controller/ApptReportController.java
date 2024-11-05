package Controller;
/**
 * ApptReportController displays total count of appts by selected type and month
 */

import DAO.AppointmentsDAO;
import Model.Appointments;
import Utilities.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class ApptReportController implements Initializable{

        private int monthNumber;
        private String selectType;

        @FXML
        private Label apptTypeLbl;

        @FXML
        private ComboBox<String> apptTypeCombo;

        @FXML
        private ComboBox<Month> monthCombo;

        @FXML
        private Button backBtn;

        @FXML
        private Button exitBtn;

        @FXML
        private TextField totalAppts;

        @FXML
        void clearTotal(MouseEvent event)
        {
                totalAppts.clear();
             //   apptTypeCombo.getSelectionModel().clearSelection();
                System.out.println("type cleared");
        }

        @FXML
        void clearCount(MouseEvent event)
        {
               totalAppts.clear();
                             // monthCombo.getSelectionModel().clearSelection();
               System.out.println("month cleared");
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
        void exitToMain(MouseEvent event) throws IOException {

                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
        }



        @FXML
        void selectType(ActionEvent actionEvent) {

                selectType = apptTypeCombo.getValue();
                System.out.println(selectType+"type");

                setMonthType();
        }
        @FXML
        void selectMonth(ActionEvent actionEvent) {

                monthNumber = monthCombo.getSelectionModel().getSelectedItem().getValue();
                System.out.println(monthNumber+"month");
                setMonthType();

        }

        /**
         * setMonthType checks for null values in the month and type then sets the count of returned values
         */
        public void setMonthType() {

                if (selectType != null) {
                        if (monthNumber >=1) {
                                try {

                                        DAO.AppointmentsDAO.getapptByType(monthNumber, selectType);

                                                totalAppts.setText(String.valueOf(DataProvider.apptByTypeCount));


                                } catch (NullPointerException e) {
                                        e.printStackTrace();
                                }
                        }
                }

                        else{
                                return;
                        }
                }






        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                ObservableList<String> type = FXCollections.observableArrayList("Established", "New");
                apptTypeCombo.setItems(type);
                apptTypeCombo.setVisibleRowCount(2);

                ObservableList<Month> monthList = FXCollections.observableArrayList();
                        for(int i=1; i<13; i++)
                        {
                                LocalDate date = LocalDate.of(2021,i,1);
                                Month monthName = date.getMonth();
                                monthList.add(monthName);
                        }
               monthCombo.setItems(monthList);
            //   monthCombo.getSelectionModel().select(11);


               //<|onmouseclikc to on selection
               //set text display to count of obs list

        }


}
