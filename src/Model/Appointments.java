package Model;

import Model.Contacts;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

public class Appointments {
    private int apptId;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptType;
    private LocalDate apptStartDate;
    private LocalTime apptStartTime;
    private LocalDate apptEndDate;
    private LocalTime apptEndTime;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy; //check can remove?
    private Timestamp apptlastUpdateDate;
    private String apptlastUpdatedBy; //check can remove?
    private int customerId;
    private String customerName;
    private int contactId;
    private String contactName;
    private int userId;  //check can remove?


    private int countApptByType = 0;


    private String displayMonth = null;




    public Appointments(int apptId, String apptTitle, String apptDescription, String apptLocation, String apptType, LocalDate apptStartDate,
                        LocalTime apptStartTime, LocalDate apptEndDate, LocalTime apptEndTime, LocalDate createdDate, LocalTime createdTime, String createdBy,
                        Timestamp apptlastUpdateDate,  String apptlastUpdatedBy, int customerId, String customerName, int contactId,String contactName,int userId)
    {
        this.apptId = apptId ;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.apptStartDate = apptStartDate;
        this.apptStartTime = apptStartTime;
        this.apptEndDate = apptEndDate;
        this.apptEndTime = apptEndTime;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy= createdBy;
        this.apptlastUpdateDate = apptlastUpdateDate;
        this.apptlastUpdatedBy = apptlastUpdatedBy;
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactId =contactId;
        this.contactName = contactName;
        this.userId = userId;
    }
    /**
     * inBuisnessHours returns and for an appointments that are not within business hours
     */
    //public LocalDateTime validApptTime(){}

    /**
     * apptOverlaps method errors when customer tries scheduling appointments that overlap
     * @return
     */

    /**
     * upcomingAppt method is a boolean method that checks the selected user against allAppts for an upcoming appt within 15 minutes
     * @return
     */
    /**
     * addAppt method adds the selected appointment to the appointment table (booleon)
     * @return
     */
    /**
     * updateAppt method saves the changes of the selected appointment (booleon)
     * @return
     */

    /**
     * deleteAppt method deletes the selected appointment (booleon)
     * @return
     */
    @Override
    public String toString()
    {
        return customerName;
            }



    public void setApptStartTime(LocalTime apptStartTime) {
        this.apptStartTime = apptStartTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }


    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public void setApptTitle(String apptTitle) {

        this.apptTitle = apptTitle;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    public LocalDate getApptStartDate() {
        return apptStartDate;
    }

    public void setApptStartDate(LocalDate apptStartDate) {
        this.apptStartDate = apptStartDate;
    }

    public LocalTime getApptStartTime() {
        return apptStartTime;
    }

    public void setApptTime(LocalTime apptStartTime) {
        this.apptStartTime = apptStartTime;
    }

    public LocalDate getApptEndDate() {
        return apptEndDate;
    }

    public void setApptEndDate(LocalDate apptEndDate) {
        this.apptEndDate = apptEndDate;
    }

    public LocalTime getApptEndTime() {
        return apptEndTime;
    }

    public void setApptEndTime(LocalTime apptEndTime) {
        this.apptEndTime = apptEndTime;
    }


     public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getApptlastUpdateDate() {
        return apptlastUpdateDate;
    }

    public void setApptlastUpdateDate(Timestamp apptlastUpdateDate) {
        this.apptlastUpdateDate = apptlastUpdateDate;
    }

    public String getApptlastUpdatedBy() {
        return apptlastUpdatedBy;
    }

    public void setApptlastUpdatedBy(String apptlastUpdatedBy) {
        this.apptlastUpdatedBy = apptlastUpdatedBy;
    }

    public int getCustomerId() {

        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getCountApptByType() {

        return countApptByType;
    }

    public void setCountApptByType(int countApptByType)
    {

        this.countApptByType = countApptByType;
    }


    public String getDisplayMonth() {
        return displayMonth;
    }

    public void setDisplayMonth(String displayMonth)
    {
        displayMonth = String.valueOf(getApptStartDate());

        this.displayMonth = displayMonth;
    }

    //initialize
    //disable the appt_id
}
