package Controller;
/**
 * CustomerMenuController displays all customers, buttons to add, update and delete selected customers
 */

import DAO.CustomersDAO;
import Model.Customers;
import Model.OAL;
import Utilities.DataProvider;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {


        @FXML
        private Button backBtn;

        @FXML
        private Button exit;

        @FXML
        private Button addCustomerBtn;

        @FXML
        private Button updateCustomerBtn;

        @FXML
        private Button deleteCustomerBtn;

        @FXML
        private TableView<Customers> custTable;

        @FXML
        private TableColumn<Customers, Integer> customerIdCol;

        @FXML
        private TableColumn<Customers, String> customerNameCol;

        @FXML
        private TableColumn<Customers, String> addressCol;

        @FXML
        private TableColumn<Customers, String> zipCol;

        @FXML
        private TableColumn<Customers, Integer> stateCol;


        @FXML
        void addCustomer(MouseEvent event) {
                try {


                        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/AddCustomer.fxml"));
                        Scene bill = new Scene(roger);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(bill);
                        window.show();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @FXML
        void backBtn(MouseEvent event) throws IOException {

                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
        }

        /**
         * deleteCustomer deletes selected customer from database and allCustomers observable list
         * @param event
         * @throws IOException
         */
        @FXML
        void deleteCustomer(MouseEvent event) throws IOException {

                Customers delselectCustomer = custTable.getSelectionModel().getSelectedItem();
                Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete Customer");
                Optional<ButtonType> result = confirmCancel.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                {
                        System.out.println("entered for loop");

                        DAO.CustomersDAO.deleteCustomer(delselectCustomer);

                        if (DataProvider.custDeleted == true) {
                                OAL.getAllCustomers().clear();
                                OAL.deleteCustomers(delselectCustomer);//deletes customer from Obs list
                                CustomersDAO.selectCustomers();
                                setCustomerTable();
                                custTable.setItems(OAL.getAllCustomers());

                                Alert custDeleted = new Alert(Alert.AlertType.CONFIRMATION);
                                custDeleted.setTitle("Confirmation");
                                custDeleted.setContentText("Customer Deleted");
                                custDeleted.showAndWait();
                        }

                }


        }





        @FXML
        void exitToMain(MouseEvent event)
throws IOException{

                        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
                        Scene bill = new Scene(roger);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(bill);
                        window.show();
                }


        /**
         * updateCustomer event opens the updateCustomerController to selected customer from table for updating.
          * @param event
         * @throws IOException
         */
        @FXML
        void updateCustomer(MouseEvent event)          throws IOException
        {
                Customers selectCustomer = custTable.getSelectionModel().getSelectedItem();


                FXMLLoader loader =  new FXMLLoader();
                loader.setLocation(getClass().getResource("/ViewController/UpdateCustomer.fxml"));
                Parent roger = loader.load();
                Scene bill = new Scene(roger);

                if (selectCustomer == null)
                {
                        Alert nullAppt = new Alert(Alert.AlertType.ERROR);
                        nullAppt.setTitle("No Customer Selected");
                        nullAppt.setContentText("Please Select Customer to Modify ");
                        nullAppt.showAndWait();
                } else
                {


                        UpdateCustomerController controller = loader.getController();
                        controller.displaySelectedCustomer(selectCustomer);//displays the selected object, selectCustomer

                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(bill);
                        window.show();
                }

        }

        /**
         * setCustomerTable sets the customer table column names
         */

        public void setCustomerTable()
        {
                customerIdCol.setCellValueFactory((new PropertyValueFactory<>("customerId")));
                customerNameCol.setCellValueFactory((new PropertyValueFactory<>("customerName")));
                addressCol.setCellValueFactory((new PropertyValueFactory<>("customerAddress")));
                zipCol.setCellValueFactory((new PropertyValueFactory<>("customerPostal")));
                stateCol.setCellValueFactory((new PropertyValueFactory<>("divisionName")));
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                setCustomerTable();

               CustomersDAO.selectCustomers();
                custTable.setItems(OAL.getAllCustomers());

        }

}

