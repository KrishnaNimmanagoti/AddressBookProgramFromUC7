package com.bridgeLabz.addressBookFromUC11;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MainApp {

    static HashMap<String, AddressBook> addressBookMap = new HashMap<>();
    static HashMap<Integer, String> addressBooksList = new HashMap<>();
    static Scanner scan = new Scanner(System.in);
    static String choice;
    static Integer addressBookKey = 1;
    static HashMap<Integer, String> contactsList;
    static List<String> uniqueContacts = new ArrayList<>();
    static HashMap<String, String> cityAndPerson = new HashMap<>();
    static HashMap<String, String> stateAndPerson = new HashMap<>();
    int count;

    public static void sortByCityOrState() {
        System.out.println("Select City or State to sort and Show Contacts: \n1-City: \n2-State: ");
        String userChoice = scan.next();
        if(userChoice.equals("1")){
            HashMap<String, String> sortedCitydMap = new HashMap<>();
            cityAndPerson.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x -> sortedCitydMap.put(x.getKey(), x.getValue()));
            sortedCitydMap.entrySet().stream().forEach(System.out::println);
        }
        if(userChoice.equals("2")){
            HashMap<String, String> sortedStatedMap = new HashMap<>();
            stateAndPerson.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x -> sortedStatedMap.put(x.getKey(), x.getValue()));
            sortedStatedMap.entrySet().stream().forEach(System.out::println);
        }
    }

    public static void sortingByPersons() {
        List<String> sortedList=uniqueContacts.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }

    public static void viewPersonByCityOrState() {
        MainApp mainapp = new MainApp();

        System.out.println("Enter a option to show persons in : \n1-city: \n2-state: ");
        int choice = scan.nextInt();
        System.out.println("Enter name :");
        String name = scan.next();
        for(AddressBook addressBook : addressBookMap.values()) {
            for (Contact contact : addressBook.getContactsList()) {
                if(choice == 1) {
                    if(contact.getCity().equalsIgnoreCase(name)) {
                        mainapp.count++;
                        System.out.println(contact.getCity()+" "+contact.getFirstName());
                    }
                }
                else if(choice == 2) {
                    if(contact.getState().equalsIgnoreCase(name)) {
                        mainapp.count++;
                        System.out.println(contact.getState()+" "+contact.getFirstName());
                    }
                }
                else {
                    System.out.println("you have entered wrong choice.");
                }
            }
        }

        System.out.println("Total no.of persons: " + mainapp.count);
    }

    public static void searchByCity() {

        System.out.println("Enter a city to search persons: ");
        String city = scan.next();

        for(AddressBook addressBook : addressBookMap.values()) {

            for (Contact contact : addressBook.getContactsList()) {

                if(contact.getCity().equalsIgnoreCase(city)) {

                    System.out.println(contact.getCity()+" "+contact.getFirstName());

                }
            }

        }
    }

    private static void addAddressBook() {

        System.out.println("Enter Address Book name: ");
        String addressBookName = scan.next();
        addressBookMap.put(addressBookName, new AddressBook());
        addressBooksList.put(addressBookKey++, addressBookName);
        System.out.println("Address Book creation is successfull with name " + addressBookName + "\n");

    }

    private static void showAddressBooks() {
        for (Entry<Integer, String> entry : addressBooksList.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private static void addContact() {

        System.out.println("List of Address Books:");
        showAddressBooks();
        System.out.println("Select the Address Book from the above: ");
        String addressBookName = addressBooksList.get(Integer.parseInt(scan.next()));

        System.out.println("Enter first name: ");
        String firstName = scan.next();

        System.out.println("Enter last name: ");
        String lastName = scan.next();

        System.out.println("Enter  address: ");
        String address = scan.next();

        System.out.println("Enter city: ");
        String city = scan.next();

        System.out.println("Enter state: ");
        String state = scan.next();

        System.out.println("Enter zip: ");
        String zip = scan.next();

        System.out.println("Enter mobile number: ");
        String mobileNumber = scan.next();

        System.out.println("Enter email: ");
        String email = scan.next();

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, mobileNumber, email);
        cityAndPerson.put(firstName, city);
        stateAndPerson.put(firstName, state);

        if (!uniqueContacts.contains(firstName)) {
            addressBookMap.get(addressBookName).getContactsList().add(contact);
            uniqueContacts.add(firstName);
            System.out.println("Contact creation is successfull.\n");
        } else {
            System.out.println("Creation failed as already exist a contact with entered first name - " + firstName);
        }

        System.out.println("\n");
    }

    private static void editContact() {

        System.out.println("List of Address Books:");
        showAddressBooks();
        System.out.println("Select the Address Book from the above: ");
        String addressBookName = addressBooksList.get(Integer.parseInt(scan.next()));
        contactsList = new HashMap<>();
        AddressBook addressBook = addressBookMap.get(addressBookName);
        int j = 1;
        for (Contact c : addressBook.getContactsList()) {
            System.out.println(j + ". " + c.getFirstName());
            contactsList.put(j, c.getFirstName());
            j = j + 1;
        }
        System.out.println("\n");
        System.out.println("Select a contact to show details: ");
        int userSelectedContact = Integer.parseInt(scan.next());
        Contact newCon = null;
        for (Contact contact : addressBook.getContactsList()) {
            if (contact.getFirstName().equals(contactsList.get(userSelectedContact))) {
                System.out.println("Contact full details:");
                System.out.println("1. First Name: " + contact.getFirstName());
                System.out.println("2. Last Name: " + contact.getLastName());
                System.out.println("3. Address: " + contact.getAddress());
                System.out.println("4. City: " + contact.getCity());
                System.out.println("5. State: " + contact.getState());
                System.out.println("6. Zip: " + contact.getZip());
                System.out.println("7. Mobile number: " + contact.getMobileNumber());
                System.out.println("8. Email: " + contact.getEmail());
                newCon = contact;
                break;
            }
        }

        System.out.println("Enter which field you want to edit: ");
        int userChoice = Integer.parseInt(scan.next());

        switch (userChoice) {
            case 1 -> {
                System.out.println("Enter new first Name: ");
                String newFirstName = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setFirstName(newFirstName);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
            case 2 -> {
                System.out.println("Enter new last Name: ");
                String newLastName = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setLastName(newLastName);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
            case 3 -> {
                System.out.println("Enter new Address: ");
                String newAddress = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setAddress(newAddress);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
            case 4 -> {
                System.out.println("Enter new City: ");
                String newCity = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setCity(newCity);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
            case 5 -> {
                System.out.println("Enter new State: ");
                String newState = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setState(newState);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
            case 6 -> {
                System.out.println("Enter new Zip: ");
                String newZip = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setZip(newZip);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
            case 7 -> {
                System.out.println("Enter new mobile number: ");
                String newMobileNumber = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setMobileNumber(newMobileNumber);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
            case 8 -> {
                System.out.println("Enter new Email: ");
                String newEmail = scan.next();
                for (int i = 0; i < addressBookMap.get(addressBookName).getContactsList().size(); i++) {
                    Contact c = addressBookMap.get(addressBookName).getContactsList().get(i);
                    if (c.getFirstName().equals(newCon.getFirstName())) {
                        addressBookMap.get(addressBookName).getContactsList().remove(i);
                        newCon.setEmail(newEmail);
                        addressBookMap.get(addressBookName).getContactsList().add(newCon);
                    }
                }
            }
        }
        System.out.println("Successfully edited.\n");
        System.out.println("\n");
    }

    private static void deleteContact() {

        System.out.println("List of Address Books:");
        showAddressBooks();
        System.out.println("Select the Address Book from the above: ");
        String aBookName7 = addressBooksList.get(Integer.parseInt(scan.next()));

        contactsList = new HashMap<>();

        AddressBook ab7 = addressBookMap.get(aBookName7);
        int i7 = 1;
        for (Contact c : ab7.getContactsList()) {
            System.out.println(i7 + ". " + c.getFirstName());
            contactsList.put(i7, c.getFirstName());
            i7 = i7 + 1;
        }
        System.out.println("\n");

        System.out.println("Select a contact to show details: ");
        int opt7 = Integer.parseInt(scan.next());

        for (Contact c : ab7.getContactsList()) {
            if (c.getFirstName().equals(contactsList.get(opt7))) {
                addressBookMap.get(aBookName7).getContactsList().remove(c);
                break;
            }
        }
        System.out.println("Deleted successfully. \n");

    }

    private static void showContacts() {

        System.out.println("List of Address Books:");
        showAddressBooks();
        System.out.println("Select the Address Book from the above: ");
        String addressBookName = addressBooksList.get(Integer.parseInt(scan.next()));

        contactsList = new HashMap<>();

        AddressBook ab = addressBookMap.get(addressBookName);
        int i = 1;
        for (Contact contact : ab.getContactsList()) {
            System.out.println(i + ". " + contact.getFirstName());
            contactsList.put(i, contact.getFirstName());
            i = i + 1;
        }
        System.out.println("\n");

        System.out.println("Select a contact to show details: ");
        int userSelectedContact = Integer.parseInt(scan.next());

        for (Contact contact : ab.getContactsList()) {
            if (contact.getFirstName().equals(contactsList.get(userSelectedContact))) {
                System.out.println("Contact full details:");
                System.out.println("1. First Name: " + contact.getFirstName());
                System.out.println("2. Last Name: " + contact.getLastName());
                System.out.println("3. Address: " + contact.getAddress());
                System.out.println("4. City: " + contact.getCity());
                System.out.println("5. State: " + contact.getState());
                System.out.println("6. Zip: " + contact.getZip());
                System.out.println("7. Mobile number: " + contact.getMobileNumber());
                System.out.println("8. Email: " + contact.getEmail());
                break;
            }
        }
        System.out.println("\n");

    }

    public static void main(String[] args) {

        do {
            System.out.println("********** Menu **********");
            System.out.println("1. Add Address Book");
            System.out.println("2. Show Address Books");
            System.out.println("3. Add Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Show Contacts");
            System.out.println("7. search by City");
            System.out.println("8. Persons in City Or State");
            System.out.println("9. Sort and show all Persons");
            System.out.println("10. Sort by city or state and show all Persons");
            System.out.println("11. Exit\\n");

            System.out.println("Press a digit to select the required option: ");
            choice = scan.next();
            int option = Integer.parseInt(choice);

            switch (option) {
                case 1 -> addAddressBook();
                case 2 -> {
                    System.out.println("List of Address Books:");
                    showAddressBooks();
                    System.out.println("\n");
                }
                case 3 -> addContact();
                case 4 -> editContact();
                case 5 -> deleteContact();
                case 6 -> showContacts();
                case 7 -> searchByCity();
                case 8 -> viewPersonByCityOrState();
                case 9 -> sortingByPersons();
                case 10 -> sortByCityOrState();
                case 11 -> System.out.println("Program exited successfully.");
            }

        } while (!"11".equals(choice));
        scan.close();
    }
}



