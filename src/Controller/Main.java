package Controller;

/**
 * Main starts the application
 */

import DAO.CountriesDAO;
import Language.Language;
import Model.Countries;
import Model.Contacts;
import Model.Appointments;
import Utilities.DBConnection;
import Utilities.DBQuery;
import Utilities.GeneralInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.*;


import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("running");

        Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/LogIn.fxml"));

        Scene bill = new Scene(roger);
        primaryStage.setScene(bill);
        primaryStage.setResizable(false);
        primaryStage.show();



    }




    public static void main(String[] args) throws IOException{
        //call the startConnection method

      //  Locale.setDefault(new Locale("fr"));
        //rem 3/21 don't need* added back 3/28  dt npt exception,
        DBConnection.startConnection();

        //GeneralInterface message = s -> "hello world" + s;  //message variable, pts object imp interface, -->after executed in lambda exp
       // System.out.println(message.getMessage(ZoneId.systemDefault().toString()));

        //void lambda expression with one parameter
        //reference variable message

        //GeneralInterface message = parameterString -> System.out.println("this is now set in the message variable"); //this is a void, so it will only run the expression, not return
        //when parameterString is here in the expression to print, it looks to the displayMessage for

        //message is set to parameter string
        //displayMessage has the content of the parameter string

                    //parameter string is the method body
          // message.displayMessage("something type"+message );

        //mult parameter lambda expression
       // GeneralInterface sum = (n1,n2)-> n1+ n2;
       // System.out.println(sum.calculateSum(5,10));
        //int total = sum.calculateSum(10,15);
        //System.out.println(total);

        //abstract method that does not have any parameters

       // GeneralInterface message =()->System.out.println("no parameters");
       // message.displayMessage();





        Connection conn = DBConnection.getConnection();//connect to database



        launch(args);
        //closes the database
        DBConnection.closeConnection();


    }


}
