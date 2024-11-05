package Controller;
/**
 * UpdateCustomerController displays selected customer from the CustomerMenuController, updates changes to the database Customer table and updates allCustomers observable list.
 */

import Model.Countries;
import Model.Customers;
import Model.Division;
import Model.OAL;
import Utilities.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateCustomerController {
    @FXML
    private Button saveCustomerBtn;

    @FXML
    private Button cancelAddCustomerBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField customerPostal;

    @FXML
    private TextField customerId;

    @FXML
    private TextField customerName;

    @FXML
    private Label customerDivision;

    @FXML
    private TextField customerPhone;

    @FXML
    private TextField customerAddress;

    /*@FXML
    private ComboBox<?> customerPostalCombo;  removed 7/3/2021

     */

    @FXML
    private ComboBox<Division> customerDivisionCombo;

    @FXML
    private ComboBox<Countries> customerCountryCombo;

    private Customers selectCustomer;

    ObservableList<Division> divFilteredByCountry= FXCollections.observableArrayList();

    @FXML
    void backBtn(MouseEvent event) throws IOException {

        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/CustomerMenu.fxml"));
        Scene bill = new Scene(roger);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(bill);
        window.show();

    }

    @FXML
    void cancelAddCustomer(MouseEvent event) throws IOException {
        Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to cancel changes?");

        Optional<ButtonType> result = confirmCancel.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/CustomerMenu.fxml"));
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

    public void displaySelectedCustomer(Customers cust)
    {
        //DAO.CustomersDAO.queryCustomers();
        selectCustomer = cust;

        customerId.setText(String.valueOf(selectCustomer.getCustomerId()));
        customerName.setText(String.valueOf(selectCustomer.getCustomerName()));
        customerPhone.setText(String.valueOf(selectCustomer.getCustomerPhone()));
        customerAddress.setText(String.valueOf(selectCustomer.getCustomerAddress()));
        customerPostal.setText(String.valueOf(selectCustomer.getCustomerPostal()));
        customerCountryCombo.setSelectionModel(customerCountryCombo.getSelectionModel());
        customerDivisionCombo.setSelectionModel(customerDivisionCombo.getSelectionModel());


        for(Division div : customerDivisionCombo.getItems())
        {
            if(selectCustomer.getDivisionId() == div.getDivisionId()) {
                customerDivisionCombo.setValue(div);
                int cID = div.getDivCountryId();

                for (Countries country : OAL.getAllCountries()) {
                    if (cID == country.getCountryId())
                    {
                        customerCountryCombo.setValue(country);
                        break;
                    }

                }
            }
        }
    }

    /**
     * saveCustomer event runs the updateCustomer update query to the database, updates the allCustomers observable list.
     * @param event
     * @throws Exception
     */
    @FXML
    void saveCustomer(MouseEvent event) throws Exception {

        int id = Integer.parseInt(customerId.getText());
        String name = customerName.getText();
        String phone = customerPhone.getText();
        String address = customerAddress.getText();
        String postal = customerPostal.getText();
        LocalDateTime date = selectCustomer.getCreatedDate();
        String creator = selectCustomer.getCreatedBy();
        LocalDateTime update = java.time.LocalDateTime.now();
        String updateBy = DataProvider.userId;
        int division = customerDivisionCombo.getSelectionModel().getSelectedItem().getDivisionId();
        String divisionName=customerDivisionCombo.getSelectionModel().getSelectedItem().getDivision();
        int country = customerCountryCombo.getSelectionModel().getSelectedItem().getCountryId();

        if ( name.isEmpty()                 || phone.isEmpty()                 || address.isEmpty()                 || postal.isEmpty())
        {
            Alert nullValue = new Alert(Alert.AlertType.ERROR);
            nullValue.setTitle("Incomplete");
            nullValue.setContentText("Please Complete All Fields" + "\n" + "Name: " + name + "\n" + "Address: " + address + "\n" + "Phone: " + phone + "\n" + "Postal: " + postal);
            nullValue.showAndWait();
        }
        else {
            Customers updatedCustomer = new Customers(id, name, phone, address, postal, date, creator, update, updateBy, division, divisionName, country);
            int del = selectCustomer.getCustomerId();

            DAO.CustomersDAO.updateCustomer(updatedCustomer);

            if (DataProvider.custUpdated == true) {
                OAL.updateCustomerList(del, updatedCustomer);//change to update the customer
                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/CustomerMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
            } else {
                Alert cannotLoad = new Alert(Alert.AlertType.ERROR);
                cannotLoad.setTitle("Update Failed");
                cannotLoad.setContentText("Customer Update Failed");
                cannotLoad.showAndWait();
            }
        }
    }

    @FXML
    void selectCountry(ActionEvent event) {

        System.out.println("selected country");
        customerDivisionCombo.getSelectionModel().clearSelection();
        divFilteredByCountry.clear();
        System.out.println("cleared division");
    }



    @FXML
    void selectDivision(ActionEvent event) {
        System.out.println("selected division");

    }

    @FXML
    void selectCustomerCountry(MouseEvent event) {
            toString();
        }

    @FXML
    void selectCustomerDivision (MouseEvent event) {
        toString();//display division name
        customerDivisionCombo.getSelectionModel().clearSelection();
        divFilteredByCountry.clear();

        try{

            Countries country = customerCountryCombo.getSelectionModel().getSelectedItem();
            int selectCountry = country.getCountryId();//sets to country id from customerCountryCombo selection

            if(selectCountry <= 0){
                return;
            }
            else {
                //OAL.getSelectDiv().clear();

                customerDivisionCombo.getSelectionModel().clearSelection();

                for (Division div : OAL.getAllDivisions()) {
                    int divId = div.getDivCountryId();
                    if (selectCountry == divId ) {//checks the country ID from db to division
                        //  OAL.addSelectDiv(div); //add division to OAL selectDiv
                        divFilteredByCountry.add(div);
                    }
                }
                customerDivisionCombo.setItems(divFilteredByCountry);
            }
        }
        catch (NumberFormatException e)
        {
            return;
        }
        catch (NullPointerException e){
            Alert nullCountry = new Alert(Alert.AlertType.ERROR);
            nullCountry.setTitle("Null Country");
            nullCountry.setContentText("Select Country First");
            nullCountry.showAndWait();

        }

    }


        @FXML
        void selectCustomerPostalCode (MouseEvent event){

        }

        public void initialize ()
        {
            customerId.setDisable(true);
            ObservableList<Countries> country = OAL.getAllCountries();
            customerCountryCombo.setItems(country);
            customerCountryCombo.setVisibleRowCount(10);
            customerCountryCombo.setPromptText("Select Country");


            ObservableList<Division> div = OAL.getAllDivisions();
            customerDivisionCombo.setItems(div);
            customerDivisionCombo.setVisibleRowCount(15);
            customerDivisionCombo.setPromptText("Select Division");


        }

        //   *************end UpdateCustomerController*************
}


