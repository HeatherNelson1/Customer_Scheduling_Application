package Model;
/**
 * Contacts class provides contact information
 */

import Controller.AddApptController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;

public class Contacts {
    private int contactId;
    private String contactName;
    private String contactEmail;

    public Contacts(int contactId, String contactName, String contactEmail)
    {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    @Override
    public String toString()
    {
        return  contactName;

    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }


    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //List.allContacts.


    }
}
