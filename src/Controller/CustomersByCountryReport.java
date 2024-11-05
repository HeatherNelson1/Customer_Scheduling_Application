package Controller;
/**
 * CustomersByCountryReport controller displays all customers in a table view for the country selected by combo box
 */

import Model.Countries;
import Model.Customers;
import Model.OAL;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomersByCountryReport implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private ComboBox<Countries> countryCombo;

    @FXML
    private TableView<Customers> custTable;

    @FXML
    private TableColumn<Customers, Integer> customerID;

    @FXML
    private TableColumn<Customers, Integer> customerName;

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

    /**
     * selectCountry method gets country Id from user selected country and passes as parameter to  DAO.Customers.getCustomerByCountry(int countryId) which queries the db with country ID returning list of customers from that country
     * @param event
     */
    @FXML
    void selectCountry(ActionEvent event) {
       Countries c = countryCombo.getValue();
       if (c==null)//avoids a null if accidently click the table without a selected customer
       {
           return;
       }
       ObservableList<Customers> custList = DAO.CustomersDAO.getCustomerByCountry(c.getCountryId());
       custTable.setItems(custList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        OAL.getAllCountries().clear();
        DAO.CountriesDAO.queryCountries();

        ObservableList<Countries> country = OAL.getAllCountries();

        countryCombo.setItems(country);
        countryCombo.setVisibleRowCount(10);
        countryCombo.setPromptText("Select Country");

        customerID.setCellValueFactory((new PropertyValueFactory<>("customerId")));
        customerName.setCellValueFactory((new PropertyValueFactory<>("customerName")));


    }
}
