package Model;
/**
 * holds public observable lists
 */

import DAO.DivisionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

/**
 * Observable List class holds observable lists for the model package classes
 */

public class OAL {
    //+getTimeZone() : ObservableList<TimeZone>

    /**
     * OAL list to return list of all countries
     */
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    public static void addCountry(Countries country)
    {
        allCountries.add(country);
    }

    public static ObservableList<Countries>getAllCountries()
    {

        return allCountries;
    }
    //******************************************************************************
    /**
     * 
     */
    private static ObservableList<Appointments> weekAppts = FXCollections.observableArrayList();


    public static void addWeekAppts(Appointments appt)
    {
        //call the DAO for the query results

        weekAppts.add(appt);
    }
    public static ObservableList<Appointments>getWeekAppts()
    {
        LocalDate today = LocalDate.now();
        LocalDate week = today.plusDays(7);
      //  int day = today.getDayOfMonth();
        System.out.println(today);
        System.out.println(week);

        // LocalDate apptDates = Appointments.getApptStartDate();
        for (Appointments appointments: OAL.getAllAppts())
        {
            if ((appointments.getApptStartDate().isBefore(week)) & appointments.getApptStartDate().isAfter(today))//boolean enters loop if before the date
            {
                weekAppts.add(appointments);
                System.out.println("appt added");
                System.out.println(appointments.getApptStartDate());
            }
            else {
                System.out.println("no appts this week");
            }
         }
        return weekAppts;
    }


    //******************************************************************************


    //******************************************************************************
    /**
     *
     */

    private static ObservableList<Appointments> monthAppts = FXCollections.observableArrayList();

    public static ObservableList<Appointments>getMonthAppts()
    {

        LocalDate today = LocalDate.now();
        Year year = Year.of(today.getYear());
        Month month = today.getMonth();
        System.out.println(month);
        System.out.println(year);

        for (Appointments appointments: OAL.getAllAppts())
        {

            Month m = appointments.getApptStartDate().getMonth();
            Year y = Year.of(appointments.getApptStartDate().getYear());

            if ((month.equals(m) & (year.equals(y))))
            {
                monthAppts.add(appointments);
                System.out.println(appointments + "found within month");
                System.out.println(m);
                System.out.println(y);

            }

            {
                System.out.println("no appts this month");
            }
        }
        return monthAppts;

    }


    public static void addMonthAppts(Appointments appt)
    {

       monthAppts.add(appt);
    }

    //******************************************************************************

    /**
     *
     */

    private static ObservableList<Appointments> apptsByType = FXCollections.observableArrayList();

    public static ObservableList<Appointments>getapptsByType() //returns apptsByType
    {
        return apptsByType;

    }
    /**
     *
     * @param appt addAppts method adds appt object to OAL apptsByType
     */
    public static void addapptsByType(Appointments appt)
    {
        apptsByType.add(appt);

    }

    //******************************************************************************
    //******************************************************************************

    /**
     *allAppts creates list of all appointments added from the DAO.queryAppt select query
     */

    private static ObservableList<Appointments> allAppts = FXCollections.observableArrayList();

    public static ObservableList<Appointments>getAllAppts() //returns all appointments OAL
    {
        return allAppts;

    }

    /**
     *
     * @param appt addAppts method adds appt object to OAL allAppts
     */
    public static void addAppts(Appointments appt)
    {
        allAppts.add(appt);
     //   System.out.println(allAppts);
    }

    //******************************************************************************

    /**
     *
     */
    private static ObservableList<Appointments>apptByContact = FXCollections.observableArrayList();
    public static ObservableList<Appointments>getApptByContact()
    {
        return apptByContact;
    }
    public static void addApptsByContact(Appointments appt)
    {
        apptByContact.add(appt);

    }

    //******************************************************************************

    /**
     * updateApptList method removes all appt objects from OAL allAppts and adds new with the changes made
     */
    public static  void updateApptList(int indexAppt, Appointments appt)
    {
        Appointments selectAppt = lookupSelectedAppt(indexAppt);
                deleteAppts(selectAppt);
        System.out.println(selectAppt + "deleted");
        addAppts(appt);
        System.out.println(appt + "added");
    }

    public static Appointments lookupSelectedAppt(int delAppt)
    {
        for(Appointments appt : allAppts){
            if (appt.getApptId()==delAppt){
                return appt;
            }
        }
        return null;
    }

    public static void deleteAppts(Appointments appt)
    {
        allAppts.removeAll(appt);
    }



    //******************************************************************************
    /**
     * allFirstLevelDivisions OAL returns all first level divisions added to this list to be displayed in table view
      */
    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public static ObservableList<Division> getAllDivisions() {
        return allDivisions;
           }
       public static void   addDivisions(Division divisions)
       {
           allDivisions.add(divisions);
       }
    //******************************************************************************

    /**
     * DELETE ONCE CLEARED
     * allFirstLevelDivisions OAL returns all first level divisions added to this list to be displayed in table view
     * filtered by country via combo box
     */
    /*
    private static ObservableList<Division> selectDiv = FXCollections.observableArrayList();

    public static ObservableList<Division> getSelectDiv()
    {

      //  DivisionDAO.queryDivisions(); //return all division
        //return selected country
        //compare selected country
        return selectDiv;
    }
    public static void   addSelectDiv(Division divisions)
    {

        selectDiv.add(divisions);
    }

     */
    //******************************************************************************
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    public static ObservableList<Customers> getAllCustomers(){
        return allCustomers;
    }
    public static void addCustomers(Customers customers)
    {
        allCustomers.add(customers);
    }


    //**************************************************************************
    /**
     * updateCustomer method removes all customer objects from OAL allCustomers and adds new with the changes made
     */
    public static  void updateCustomerList(int indexCustomer, Customers customer)

    {
        Customers selectCust = lookupSelectedCust(indexCustomer);
        deleteCustomers(selectCust);
        System.out.println(selectCust + "deleted");
        addCustomers(customer);
        System.out.println(customer + "added");
    }

    public static Customers lookupSelectedCust(int delCust)
    {
        for(Customers cust : allCustomers){
            if (cust.getCustomerId()==delCust){
                return cust;
            }
        }
        return null;
    }

    public static void deleteCustomers(Customers customers)
    {
        allCustomers.removeAll(customers);
    }

    //****************************************************************************

    //******************************************************************************
    private static final ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    public static ObservableList<Contacts> getAllContacts() {
        return allContacts;
    }
    public static void addContacts(Contacts contacts)
    {
        allContacts.add(contacts);
    }
    //******************************************************************************
    private static final ObservableList<Users> allUsers = FXCollections.observableArrayList();

    public static ObservableList<Users> getAllUsers()
    {
        return allUsers;
    }
    public static void addUsers(Users users)
    {
            allUsers.add(users);
    }
    //******************************************************************************
    private static final ObservableList<Users> selectedUsers = FXCollections.observableArrayList();

    public static ObservableList<Users> getSelectedUsers()  //check if not used remove
    {
        return selectedUsers;
    }
    public static void addSelectedUsers(Users users)
    {
        selectedUsers.add(users);
        System.out.println("users added" +"" + selectedUsers);
    }

    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************

}
