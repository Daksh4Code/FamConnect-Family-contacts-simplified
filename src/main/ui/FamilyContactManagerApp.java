package ui;

import model.Event;
import model.FamilyContactManager;
import model.Person;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

// The 'FamilyContactManagerApp' class represents an application for managing family contacts and
// the custom events associated with them.
// It provides a menu-driven interface to add, view, update, and delete family contacts and any
// custom events associated with each unique Person object.
public class FamilyContactManagerApp {
    private FamilyContactManager contactManager;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Constructs a FamilyContactManagerApp object by initializing the
    // objects for 'FamilyContactManager' class and 'Scanner' class to take user input
    public FamilyContactManagerApp() {
        contactManager = new FamilyContactManager();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter("./data/contacts.json"); // Adjust the path as needed
        jsonReader = new JsonReader("./data/contacts.json"); // Adjust the path as needed
    }

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Starts the Family Contact Manager application and displays a menu which
    // processes and performs functions based on user input until the user chooses to exit
    // An error message is displayed for any option entered which is not in the menu
    @SuppressWarnings("methodlength")
    public void start() {
        boolean isRunning = true;
        System.out.println("Welcome to FamConnect Version 1! ");
        System.out.println("Your personal family contact manager awaits you.");
        while (isRunning) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                addNewContact();
            } else if (choice == 2) {
                viewAllContacts();
            } else if (choice == 3) {
                deleteContact();
            } else if (choice == 4) {
                updateContactDetails();
            } else if (choice == 5) {
                loadContacts();
            } else if (choice == 6) {
                saveAndExit();
            } else if (choice == 7) {
                isRunning = false;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // REQUIRES: None
    // MODIFIES: None
    // EFFECTS: Displays a menu with options and asks the user to enter a choice
    private void displayMenu() {

        System.out.println("Please choose from the following menu: ");
        System.out.println("1. Add new contact");
        System.out.println("2. View all contacts");
        System.out.println("3. Delete contact");
        System.out.println("4. Update contact details");
        System.out.println("5. Load database from file");
        System.out.println("6. Save database to file");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // REQUIRES: Contact details are case-sensitive and to be in proper format and readable
    // MODIFIES: None
    // EFFECTS:  Creates a new 'Person' object, adds it to the 'contactManager' based
    // on the inputs of the contact details entered by the user
    // Also allows the user to add custom events if desired
    @SuppressWarnings("methodlength")
    private void addNewContact() {
        System.out.print("Enter person's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person's relation: ");
        String relation = scanner.nextLine();
        System.out.print("Enter person's birthday: ");
        String birthday = scanner.nextLine();
        System.out.print("Enter person's email: ");
        String email = scanner.nextLine();
        System.out.print("Enter person's phone number: ");
        String phoneNumber = scanner.nextLine();
        Person person = new Person(name, relation, birthday, email, phoneNumber);
        while (true) {
            System.out.print("Do you wish to add a custom event? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.print("Enter custom event name: ");
                String eventName = scanner.nextLine();
                System.out.print("Enter custom event date: ");
                String eventDate = scanner.nextLine();
                System.out.print("Enter custom event description: ");
                String eventDescription = scanner.nextLine();
                Event event = new Event(eventName, eventDate, eventDescription);
                person.addCustomEvent(event);
            } else if (choice.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        contactManager.addPerson(person);
        System.out.println("Contact added successfully!");
    }

    // REQUIRES: None
    // MODIFIES: None
    // EFFECTS: Returns and retrieves the list of family contacts and custom events associated with
    // unique contacts and displays them as output
    private void viewAllContacts() {
        List<Person> contacts = contactManager.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        }
        System.out.println("All Contacts:");
        for (Person contact : contacts) {
            System.out.println(contact);
            List<Event> customEvents = contact.getCustomEvents();
            if (!customEvents.isEmpty()) {
                System.out.println("Custom Events:");
                for (Event event : customEvents) {
                    System.out.println(event);
                }
            }
        }
    }

    // REQUIRES: contact name should exist in contact list already
    // MODIFIES: this
    // EFFECTS: Removes the specified contact from 'contactManager'
    private void deleteContact() {
        System.out.print("Enter the name of the contact to be deleted: ");
        String name = scanner.nextLine();
        contactManager.deletePerson(name);
        System.out.println("Contact deleted successfully.");
    }

    // REQUIRES: inputs should be case-sensitive
    // MODIFIES: this
    // EFFECTS: Enables user to update the relationship, birthday, email, and phone number of a
    // contact
    // Also provides option to update unique custom events associated with the contact.

    @SuppressWarnings("methodlength")
    private void updateContactDetails() {
        System.out.print("Enter the name of the contact to update: ");
        String name = this.scanner.nextLine();
        Person person = this.contactManager.getPersonByName(name);
        if (person != null) {
            System.out.println("Current contact details:");
            System.out.println(person);
            System.out.print("Enter new relation: ");
            String relation = this.scanner.nextLine();
            System.out.print("Enter new birthday: ");
            String birthday = this.scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = this.scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String phoneNumber = this.scanner.nextLine();
            while (true) {
                System.out.print("Update custom events? (y/n): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    Event existingEvent = person.getCustomEventByName(eventName);
                    if (existingEvent != null) {
                        System.out.print("Enter updated event date: ");
                        String eventDate = this.scanner.nextLine();
                        System.out.print("Enter updated event description: ");
                        String eventDescription = this.scanner.nextLine();
                        existingEvent.setEventDate(eventDate);
                        existingEvent.setEventDescription(eventDescription);
                        System.out.println("Custom event updated successfully.");
                    } else {
                        System.out.println("Event not found for this person.");
                    }
                } else if (choice.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.print("Invalid choice! Please try again.");
                }
            }
            person.setRelationship(relation);
            person.setBirthday(birthday);
            person.setEmail(email);
            person.setPhoneNumber(phoneNumber);
            System.out.println("Contact details updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private void saveContacts() {
        try {
            jsonWriter.open();
            jsonWriter.write(contactManager);
            jsonWriter.close();
            System.out.println("Data saved successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    private void loadContacts() {
        try {
            contactManager = jsonReader.read();
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    private void saveAndExit() {
        saveContacts();
        System.out.println("Goodbye!");
    }

}
