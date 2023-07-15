package AddressBookingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email Address: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> searchContacts(String keyword) {
        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().contains(keyword) ||
                    contact.getPhoneNumber().contains(keyword) ||
                    contact.getEmailAddress().contains(keyword)) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }
}

class AddressBookApp {
    private AddressBook addressBook;
    private Scanner scanner;

    public AddressBookApp() {
        addressBook = new AddressBook();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("Address Book System");
            System.out.println("-------------------");
            System.out.println("1. Add a new contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    displayContacts();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
        scanner.close();
    }

    private void addContact() {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter contact email address: ");
        String emailAddress = scanner.nextLine();

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(contact);
        System.out.println("Contact added successfully!");
    }

    private void removeContact() {
        System.out.print("Enter the name of the contact to remove: ");
        String name = scanner.nextLine();

        List<Contact> searchResults = addressBook.searchContacts(name);
        if (searchResults.isEmpty()) {
            System.out.println("No contacts found with the given name.");
        } else if (searchResults.size() == 1) {
            Contact contact = searchResults.get(0);
            addressBook.removeContact(contact);
            System.out.println("Contact removed successfully!");
        } else {
            System.out.println("Multiple contacts found with the given name. Please provide additional details.");
            displayContacts(searchResults);
            System.out.print("Enter the phone number of the contact to remove: ");
            String phoneNumber = scanner.nextLine();

            for (Contact contact : searchResults) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    addressBook.removeContact(contact);
                    System.out.println("Contact removed successfully!");
                    return;
                }
            }
            System.out.println("No contact found with the given phone number.");
        }
    }

    private void searchContact() {
        System.out.print("Enter a name to search for: ");
        String keyword = scanner.nextLine();

        List<Contact> searchResults = addressBook.searchContacts(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No contacts found matching the given keyword.");
        } else {
            System.out.println("Search results:");
            displayContacts(searchResults);
        }
    }

    private void displayContacts() {
        List<Contact> allContacts = addressBook.getAllContacts();
        if (allContacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("All contacts:");
            displayContacts(allContacts);
        }
    }

    private void displayContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}

public class main {
    public static void main(String[] args) {
        AddressBookApp addressBookApp = new AddressBookApp();
        addressBookApp.start();
    }
}

