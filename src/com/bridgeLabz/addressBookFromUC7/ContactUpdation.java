package com.bridgeLabz.addressBookFromUC7;

public class ContactUpdation {

    public static final String[] fields = new String[]{"firstName", "lastName", "address", "city", "state", "zipCode",
            "phoneNumber", "email"};
    public String[] values;

    public ContactUpdation(String[] values) {
        this.values = values;

    }

    public void printContact() {
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i] + ":" + values[i]);
        }
    }

    public boolean equals(Object contact) {
        if(contact instanceof ContactUpdation){
            return isEquivalent((ContactUpdation) contact);
        }
        return false;
    }

    private boolean isEquivalent(ContactUpdation contactUpdation) {
        return (this.values[0].equals(contactUpdation.values[0]) && this.values[1].equals(contactUpdation.values[1]));
    }

}
