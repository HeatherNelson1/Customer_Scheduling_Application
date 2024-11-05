package Controller;
/**
 * addCustomerController adds new customer to database CustomersDAO and observable list allCustomers
 */

import DAO.CustomersDAO;
import DAO.DivisionDAO;
import Model.*;
import Utilities.DataProvider;
import com.google.protobuf.LazyStringArrayList;
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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;



import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerController {

    @FXML
    private Button saveCustomerBtn;

    @FXML
    private Button cancelAddCustomerBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button exitBtn;

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

    @FXML
    private TextField postalCode;


    @FXML
    private ComboBox<Division> customerDivisionCombo;

    @FXML
    private ComboBox<Countries> customerCountryCombo;




    /**
     * backBtn returns user to the customerMenu
     * @param event
     * @throws IOException
     */
    @FXML
    void backBtn(MouseEvent event) throws IOException{
        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/CustomerMenu.fxml"));
        Scene bill = new Scene(roger);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(bill);
        window.show();

    }

    /**
     * cancelAddCustomer cancels save of new customer and issues warning before concel
     * @param event
     * @throws IOException
     */
    @FXML
    void cancelAddCustomer(MouseEvent event)  throws IOException{

        Alert confirmCancel = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Cancel, Click OK To Continue Without Saving");
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

    /**
     * exitToMain returns to main menu
     * @param event
     * @throws IOException
     */
    @FXML
    void exitToMain(MouseEvent event) throws IOException{
            Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
            Scene bill = new Scene(roger);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(bill);
            window.show();


        }


    /**
     * saveNewCustomer saves new customer object to the OAL.allCustomers and the db. all fields are required
     * @param event
     * @throws IOException
     */
    @FXML
    void saveNewCustomer(MouseEvent event) throws IOException {
        try {
            int id = 1; //Integer.parseInt(customerId.getText());
            String name = customerName.getText();
            String add = customerAddress.getText();
            String post = postalCode.getText();
            String phone = customerPhone.getText();
            LocalDateTime date = LocalDateTime.now();
            String creator = DataProvider.userId;
            LocalDateTime update = LocalDateTime.now();
            String updateBy = DataProvider.userId;
            int divId = customerDivisionCombo.getSelectionModel().getSelectedItem().getDivisionId();
            String divisionName=customerDivisionCombo.getSelectionModel().getSelectedItem().getDivision();
            int cId = DataProvider.uID;

            if (name.isEmpty()

                    ||phone.isEmpty()
                    ||add.isEmpty()
                    ||post.isEmpty())
            {
                Alert nullValue = new Alert(Alert.AlertType.ERROR);
                nullValue.setTitle("Incomplete");
                nullValue.setContentText("Please Complete All Fields" + "\n" + "Name: " + name + "\n" + "Address: " + add + "\n" + "Phone: " + phone + "\n" + "Postal: " + post);
                nullValue.showAndWait();
            }
            else {


                Customers newCustomer = new Customers(id, name, add, post, phone, date, creator, update, updateBy, divId,divisionName, cId);
                DAO.CustomersDAO.addCustomer(newCustomer);

                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
                Scene bill = new Scene(roger);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(bill);
                window.show();
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


    /**
     * selectCountry method allows user to select country from drop down list
     * @param event
     */
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

    /**
     * displays available countries for selection
     * @param event
     */
    @FXML
    void selectCustomerCountry(MouseEvent event)
    {

        toString();
    }

    ObservableList<Division> divFilteredByCountry=FXCollections.observableArrayList();

    /**
     * selectCustomerDivision displays list of available divisions based on selected country.Errors if country not select first.
     * @param event
     */
    @FXML
    void selectCustomerDivision(MouseEvent event) {
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

/*
                ObservableList<Division> divFilteredByCountry= OAL.getAllDivisions().forEach().add(d->
                        {
                            if (d.getDivCountryId() == selectCountry) {

                                return true;
                            }
                        return false;
                        }


                ObservableList<Appointments> uList=OAL.getAllAppts().filtered(a->
            {
            if (a.getUserId()==logUserId)
            {
                return true;
            }
            return false;
                 */

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
    void selectCustomerPostalCode(MouseEvent event) {
    }


    public void initialize()
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
}