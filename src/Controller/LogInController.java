package Controller;

/**
 * LogInController accepts username and password from user and enters the program
 */

import DAO.*;
import Language.Language;
import Model.Appointments;
import Model.OAL;
import Model.Users;
import Utilities.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;


public class LogInController implements Initializable {

    @FXML
    private Button submitBtn;



    @FXML
    private Label userIdLabel;

    @FXML
    private TextField userId; //text field, entered as username

    @FXML
    private Label passwordLabel;


    @FXML
    private TextField userPassword;


    @FXML
    private Button exitBtn;

    @FXML
    private Label zoneID;
    //display the location by calling getUserLocation()


    Timestamp loginTime;
    boolean successfulLogin = false;
    boolean loginAttempt = false;

    public void logActivity() throws IOException
    {

        String filename = "login_activity.txt";
        System.out.println("logged Activity");


        String attemptedUserName = userId.getText();
        loginTime = Timestamp.valueOf(LocalDateTime.now());

        String success;

        if (successfulLogin==true)
        {
            success = "Login Successful";
        }
        else
        {
            success = "Login Failed";
        }
        FileWriter fileWriter = new FileWriter(filename,true);
        PrintWriter outputFile = new PrintWriter(fileWriter);

        outputFile.print("\n"+"user: " + attemptedUserName + "  date and time:" + loginTime + "  Status:" + success);
        outputFile.close();//close file

      //  System.out.println("user: "+attemptedUserName+ "  date and time:"+loginTime+ "  Status:" +success);

    }




    public void setDisplay()
    {
        String locale = Language.getUserBundle().getString("zoneId");
        zoneID.setText(ZoneId.systemDefault().toString());

        String exit = Language.getUserBundle().getString("exit");
        exitBtn.setText(String.valueOf(exit));

        String submit = Language.getUserBundle().getString("submit");
        submitBtn.setText(String.valueOf(submit));

        String user = Language.getUserBundle().getString("userid");
        userIdLabel.setText(String.valueOf(user));

        String pass = Language.getUserBundle().getString("password");
        passwordLabel.setText(String.valueOf(pass));


        ContactsDAO.selectContacts();
        CustomersDAO.selectCustomers();
        CountriesDAO.queryCountries(); //all countries added to OAL.getAllCountries
        DivisionDAO.queryDivisions(); //all divisions added to the OAL.getAllDivisions
        AppointmentsDAO.selectAppts();

    }


    @FXML
    void clearText(MouseEvent event) {
//ON SUBMIT ERROR-CLEAR ON CLICK
    }

    @FXML
    void cleartText(MouseEvent event) {

        //clear password

    }




    /**
     * exits program
     *
     * @param event
     */
    @FXML
    void exitProgram(MouseEvent event) {
        System.exit(0);
    }

    public void invalidUser()
    {
        Alert userNotFound = new Alert(Alert.AlertType.ERROR);
        String unfTitle = Language.getUserBundle().getString("UserNotFound");
        userNotFound.setTitle(unfTitle);
        String unfmsg = Language.getUserBundle().getString("UserNotFoundMessage");
        userNotFound.setContentText(unfmsg);
        userNotFound.showAndWait();
    }


    @FXML
    void submitPassword(MouseEvent event)  throws IOException
    {
        loginAttempt = true;
        try {
            if (userId.getText().isEmpty()) //empty
            {
                Alert userEmpty = new Alert(Alert.AlertType.ERROR);
                String emptyTitle = Language.getUserBundle().getString("EmptyUserTitle");
                userEmpty.setTitle(emptyTitle);
                String emptyMsg = Language.getUserBundle().getString("EmptyUserMessage");
                userEmpty.setContentText(emptyMsg);
                userEmpty.showAndWait();
            } else {//not empty

               // int enteredId = Integer.parseInt(userId.getText());  //gets userID entered by user sets to enteredId
                String enteredUserName =userId.getText();//gets username entered
                String pass = userPassword.getText(); //gets password entered by user sets to pass

                UserDAO.queryUser();  //queries the database for all users and sets to the allUsers obs list
                Users currentUser = null; //creates variable currentUser and sets to null

                for (Users user : OAL.getAllUsers())   //user object checking the obs list All users
                {
                    if (user.getUserName().equals(enteredUserName)) //entered user matches a user in the db  -correct
                    {
                        currentUser = user;  //assigns user object to variable currentUser if found
                        OAL.addSelectedUsers(user); // adds the user object to the list of selected users (do i still need this? CHECK)
//break out of the for loop

                        if (currentUser.getUserPassword().isEmpty()) //tests if current user is empty (=not found in db)
                        {
                            Alert userEmpty = new Alert(Alert.AlertType.ERROR);
                            String emptypass = Language.getUserBundle().getString("EmptyPasswordTitle");
                            userEmpty.setTitle(emptypass);
                            String passmsg = Language.getUserBundle().getString("EmptyPasswordMessage");
                            userEmpty.setContentText(passmsg);
                            userEmpty.showAndWait();
                        } else if (currentUser.getUserPassword().equals(pass)) //retrieves the password in the result set and tests the pass against the result set
                        {
                            System.out.println("password matched");
                            successfulLogin = true;

                            try  //loads page if password matches
                            {
                                DataProvider.userId = currentUser.getUserName();
                                DataProvider.uID = currentUser.getUserId();

                                apptFifteen();

                                Parent roger = FXMLLoader.load(getClass().getResource("/ViewController/MainMenu.fxml"));
                                Scene bill = new Scene(roger);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(bill);
                                window.show();

                          } catch (IOException e) {
                                Alert cannotLoad = new Alert(Alert.AlertType.ERROR);
                                cannotLoad.setTitle("Page Not Loaded");
                                cannotLoad.setContentText("Page Cannot be Loaded");
                                cannotLoad.showAndWait();


                            }
                        }
                         else
                    {
                    Alert invalidPassword = new Alert(Alert.AlertType.ERROR);
                    String incTitle = Language.getUserBundle().getString("IncorrectPasswordTitle");
                    invalidPassword.setTitle(incTitle);

                    String incPass = Language.getUserBundle().getString("IncorrectPasswordMessage");
                    invalidPassword.setContentText(incPass);
                    invalidPassword.showAndWait();
                    successfulLogin = false;

                    }

                    }

                }

                //(!(userId.getText().isEmpty()))
                if (currentUser == null) {
                    Alert userNotFound = new Alert(Alert.AlertType.ERROR);
                    String unfTitle = Language.getUserBundle().getString("UserNotFound");
                    userNotFound.setTitle(unfTitle);
                    String unfmsg = Language.getUserBundle().getString("UserNotFoundMessage");
                    userNotFound.setContentText(unfmsg);
                    userNotFound.showAndWait();
                }

            }
        }
        catch(NumberFormatException e){
            e.printStackTrace();
            Alert userEmpty = new Alert(Alert.AlertType.ERROR);
            String emptyTitle = Language.getUserBundle().getString("EmptyUserTitle");
            userEmpty.setTitle(emptyTitle);

            String emptyMsg = Language.getUserBundle().getString("EmptyUserMessage");
            userEmpty.setContentText(emptyMsg);

            userEmpty.showAndWait();
        }
        catch(NullPointerException e){
            e.printStackTrace();
            Alert invalidPassword = new Alert(Alert.AlertType.ERROR);
            String incTitle = Language.getUserBundle().getString("IncorrectPasswordTitle");
            invalidPassword.setTitle(incTitle);

            String incPass = Language.getUserBundle().getString("IncorrectPasswordMessage");
            invalidPassword.setContentText(incPass);
            invalidPassword.showAndWait();
        }
        System.out.println(OAL.getSelectedUsers());

        logActivity();
    }

    /**
     * apptFifteen method checks the logged user against the appointment list for any upcoming appts in the next 15 mins
     * Lambda expression filters the appointment list to appointments for the logged user returning true when logged user (logUserId) equals a user in the list. Previously continued through the for loop checking each appt
     * for match with user. The lambda improved efficiency by filtering to just the user's list of appointments.
     */
    @FXML
    public void apptFifteen() {
        //String logUserName = DataProvider.userId;
        int logUserId = DataProvider.uID;
        boolean found =false;

        ObservableList<Appointments> uList=OAL.getAllAppts().filtered(a->
        {
            if (a.getUserId()==logUserId)
            {
                return true;
            }
            return false;



        });


        for (Appointments a : uList) {
            int aUserId = a.getUserId();


            int aId = a.getApptId();
            LocalTime aStart = a.getApptStartTime();
            LocalDate aDate = a.getApptStartDate();
            LocalDateTime aDT = LocalDateTime.of(aDate,aStart);
            LocalDateTime now = LocalDateTime.now();

            if (aDT.isAfter(now) && (aDT.isBefore(now.plusMinutes(15))))
            {
                Alert inFifteen = new Alert(Alert.AlertType.ERROR);

                String upcomingTitle = Language.getUserBundle().getString("ApptInFifteenTitle");
                inFifteen.setTitle(upcomingTitle);

                String upcoming= Language.getUserBundle().getString("ApptInFifteenMessage");
                inFifteen.setContentText(upcoming);
                inFifteen.showAndWait();
                found=true;
            }
        }

        if (!found) {
            Alert noFifteen = new Alert(Alert.AlertType.INFORMATION);

            String noApptTitle = Language.getUserBundle().getString("NoApptFifteenTitle");
            noFifteen.setTitle(noApptTitle);

            String noApptmsg=Language.getUserBundle().getString("NoApptFifteenMessage");
            noFifteen.setContentText(noApptmsg);
            noFifteen.showAndWait();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setDisplay();
    }


}//end***log in controller***
