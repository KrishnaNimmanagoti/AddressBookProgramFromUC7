package com.bridgeLabz.addressBookFromUC11;
import java.util.ArrayList;
import java.util.List;
public class AddressBook {

    String addressBookName;
    List<Contact> contactsList = new ArrayList<Contact>();

    public AddressBook() { }

    public String getAddressBookName() {
        return addressBookName;
    }

    public void setAddressBookName(String addressBookName) {
        this.addressBookName = addressBookName;
    }

    public List<Contact> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contact> contactsList) {
        this.contactsList = contactsList;
    }

}
