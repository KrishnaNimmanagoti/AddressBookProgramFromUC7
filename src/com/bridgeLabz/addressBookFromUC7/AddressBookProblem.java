package com.bridgeLabz.addressBookFromUC7;

import java.util.*;

public class AddressBookProblem {

    ArrayList<ContactUpdation> contactBook = new ArrayList<ContactUpdation>();
    public static AddressBookProblem addressBook = new AddressBookProblem();
    public static HashMap<String, AddressBookProblem> addressBooks = new HashMap<>();

    public int addContacts(ContactUpdation contact) {
        int index = contactBook.indexOf(contact);
        if(index == -1) {
            contactBook.add(contact);
            System.out.println("contect added");
            contact.printContact();
        }else {
            System.out.println("Contact Already Exist");
            getContact(index).printContact();
        }
        return contactBook.size();
    }

    public int findContact(String firstName) {
        for (int i = 0; i < contactBook.size(); i++) {
            if (contactBook.get(i).values[0].equals(firstName))
                return i;
        }
        return -1;
    }
    public ContactUpdation getContact(int index){
        return contactBook.get(index);
    }

    public void editContact(int index, int field, String val){
        contactBook.get(index).values[field] = val;
    }

    public void deleteContact(int index){
        contactBook.remove(index);
    }

    public void printall(){
        for(ContactUpdation a:contactBook){
            System.out.println("------------------------------------------------");
            a.printContact();
        }
        System.out.println("------------------------------------------------");

    }
    public static void addEntries(){
        int  noOfContacts;

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter number of Contacts to be saved: ");
        noOfContacts = sc.nextInt();
        sc.nextLine();
        for (int j = 0; j < noOfContacts; j++) {
            String[] values = new String[8];
            for (int i = 0; i < values.length; i++) {
                System.out.print("\nenter " + ContactUpdation.fields[i] + ": ");
                values[i] = sc.nextLine();
            }
            ContactUpdation contact = new ContactUpdation(values);
            addressBook.addContacts(contact);
        }
    }

    public static void editEntry(){
        int reply = 1;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Contact to be edit");
        String searchName = sc.nextLine();
        int index = addressBook.findContact(searchName);
        if(index >= 0){
            addressBook.getContact(index).printContact();
            while(reply == 1){
                System.out.println("enter choice to edit = ");
                for(int i=0; i< ContactUpdation.fields.length; i++){
                    System.out.println(i +1 + " :" + " " + ContactUpdation.fields[i]);
                }
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter new value of "+ ContactUpdation.fields[choice - 1]);
                String newVal = sc.nextLine();
                addressBook.editContact(index, choice-1, newVal);
                addressBook.getContact(index).printContact();
                System.out.println("want to make more changes then press 1");
                reply =  sc.nextInt();
            }
        }else{
            System.out.println("Contact not presnt");
        }
    }
    public static void deleteEntry(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contact Name to be deleted");
        String deleteName = sc.nextLine();
        int index = addressBook.findContact(deleteName);

        index = addressBook.findContact(deleteName);
        if(index >=0){
            addressBook.getContact(index).printContact();
            addressBook.deleteContact(index);
            System.out.println("Contact Deleted successfully");
        }else{
            System.out.println("contact not found");
        }

    }

    public static void addAddressBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of new address book");
        String name = sc.nextLine() ;
        addressBook  = new AddressBookProblem();
        addressBooks.put(name, addressBook) ;
        System.out.println("New addressbook added ");
        showAddressBooks();
    }
    public static void showAddressBooks(){
        System.out.println("Available address books are");
        for(Map.Entry<String,AddressBookProblem> e: addressBooks.entrySet() ){
            System.out.println(e.getKey());
        }
    }

    public static void main(String[] args) {
        addressBooks.put("default", addressBook);
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        while(choice != 9){
            System.out.println("0.Add Address book \n1. Add contact \n2. Edit contact " +
                    "\n3.delete contact \n4. view all contacts. \n5. Exit");
            System.out.print("\nEnter choice: ");
            choice = sc.nextInt();

            switch(choice){
                case 0:
                    addAddressBook();
                    break;
                case 1:
                    addEntries();
                    break;
                case 2:
                    editEntry();
                    break;
                case 3:
                    deleteEntry();
                    break;
                case 4:
                    addressBook.printall();
                    break;

                case 5:
                    System.out.println("thank you..!!!");
                    break;

            }
        }
    }
}



