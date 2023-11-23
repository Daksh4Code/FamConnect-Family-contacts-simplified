package ui;

import model.Event;
import model.FamilyContactManager;
import model.Person;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

// The 'FamilyContactManagerApp' class represents an application for managing family contacts and
// the events associated with each unique contact.
// It provides a menu-driven interface to add, view, update, save to a JSON file, load from a JSON
// file and delete family contacts and any events associated with each unique Person object.
public class FamilyContactManagerApp {
    private FamilyContactManager familyContactManager;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: Constructs a FamilyContactManagerApp object by initializing the
    // objects for 'FamilyContactManager' class and 'Scanner' class to take user input
    public FamilyContactManagerApp() {
        familyContactManager = new FamilyContactManager();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter("./data/contacts.json");
        jsonReader = new JsonReader("./data/contacts.json");
    }

    // MODIFIES: this
    // EFFECTS: Starts the Family Contact Manager application and displays a menu which
    // processes and performs functions based on user input until the user chooses to exit
    // An error message is displayed for any option entered which is not in the menu.
    // Makes use of a helper/extracted method to stay within allowed method length
    public void start() {
        extractedMethod();
        while (true) {
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
                saveContacts();
            } else if (choice == 7) {
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Helper method to reduce length of start() method
    private static void extractedMethod() {
        System.out.println("Welcome to FamConnect Version 1!");
        System.out.println("Your personal family contact manager awaits you.");
    }

    // EFFECTS: Displays a menu with options and asks the user to enter a choice
    private void displayMenu() {
        System.out.println("Please choose from the following menu: ");
        System.out.println("1. Add a new contact to your contact list");
        System.out.println("2. View all contacts from your contact list");
        System.out.println("3. Delete a contact from your contact list");
        System.out.println("4. Update a contact's details in your contact list");
        System.out.println("5. Load your contact database from a JSON file");
        System.out.println("6. Save your contact database to a JSON file");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // REQUIRES: Contact details are case-sensitive and to be in proper format and readable
    // Phone number should not contain alphabets
    // EFFECTS:  Creates a new 'Person' object, adds it to the 'contactManager' based
    // on the inputs of the contact details entered by the user
    // Also allows the user to add associated events if desired
    // Makes use of a helper/extracted method to stay within allowed method length
    private void addNewContact() {
        Person person = getPerson();
        while (true) {
            System.out.print("Do you wish to add an event associated for this contact? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.print("Enter the event's name: ");
                String eventName = scanner.nextLine();
                System.out.print("Enter the event's date (YYYY-MM-DD): ");
                LocalDate eventDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter the event's description: ");
                String eventDescription = scanner.nextLine();
                Event event = new Event(eventName, eventDate, eventDescription);
                person.addEvent(event);
            } else if (choice.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        familyContactManager.addPerson(person);
        System.out.println("The contact was successfully added to your contact list!");
    }

    // Helper method to reduce length of addNewContact() method
    private Person getPerson() {
        System.out.print("Enter person's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person's relationship: ");
        String relation = scanner.nextLine();
        System.out.print("Enter person's birthdate (YYYY-MM-DD): ");
        LocalDate birthdate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter person's email ID: ");
        String emailID = scanner.nextLine();
        int phoneNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter phone number (as an integer): ");
            try {
                phoneNumber = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid phone number using integers only.");
            }
        }
        Person person = new Person(name, relation, birthdate, emailID, phoneNumber);
        return person;
    }

    // EFFECTS: Returns and retrieves the list of family contacts and events associated with
    // unique contacts and displays them as output
    private void viewAllContacts() {
        List<Person> contacts = familyContactManager.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("There were no contacts found in your contact list.");
        }
        System.out.println("Here are all your contacts:");
        for (Person contact : contacts) {
            System.out.println(contact);
            List<Event> customEvents = contact.getEvents();
            if (!customEvents.isEmpty()) {
                System.out.println("Associated Events:");
                for (Event event : customEvents) {
                    System.out.println(event);
                }
            }
        }
    }

    // REQUIRES: contact name should exist in contact list already, inputs are case-sensitive
    // MODIFIES: this
    // EFFECTS: Removes the specified contact from 'contactManager'
    private void deleteContact() {
        System.out.print("Enter the name of the contact you wish to be deleted from your contact list: ");
        String name = scanner.nextLine();
        familyContactManager.deletePerson(name);
        System.out.println("The contact was successfully deleted from your contact list!");
    }

    // Phone number should not contain alphabets
    // MODIFIES: this
    // EFFECTS: Enables user to update the relationship, birthdate, email ID, and phone number of a
    // contact
    // Also provides option to update unique events associated with the contact.
    // Makes use of several helper/extracted methods to stay within allowed method length
    private void updateContactDetails() {
        Person person = getPerson1();
        if (person != null) {
            Result result = getResult(person);
            while (true) {
                System.out.print("Do you wish to update associated events for this contact? (y/n): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    Event existingEvent = getEvent(person);
                    if (existingEvent != null) {
                        extractedMethod2(existingEvent);
                    } else {
                        System.out.println("The event was not found associated with this contact.");
                    }
                } else if (choice.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.print("Invalid choice! Please try again.");
                }
            }
            extractedMethod3(person, result);
        } else {
            System.out.println("The contact was not found in your contact list!");
        }
    }

    // Helper method to reduce length of updateContactDetails() method
    private Result getResult(Person person) {
        System.out.println("Here are the current contact details of this person:");
        System.out.println(person);
        System.out.print("Enter the updated relationship: ");
        String relation = this.scanner.nextLine();
        System.out.print("Enter the updated birthdate (YYYY-MM-DD): ");
        LocalDate birthdate = LocalDate.parse(this.scanner.nextLine());
        System.out.print("Enter the updated email ID: ");
        String email = this.scanner.nextLine();
        int updatedPhoneNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter the updated phone number (as an integer): ");
            try {
                updatedPhoneNumber = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid phone number using integers only.");
            }
        }
        Result result = new Result(relation, birthdate, email, updatedPhoneNumber);
        return result;
    }

    // Helper method to reduce length of updateContactDetails() method
    private void extractedMethod2(Event existingEvent) {
        System.out.print("Enter the event's updated date (YYYY-MM-DD): ");
        LocalDate eventDate = LocalDate.parse(this.scanner.nextLine());
        existingEvent.setEventDate(eventDate);
        System.out.print("Enter the event's updated description: ");
        String eventDescription = this.scanner.nextLine();
        existingEvent.setEventDescription(eventDescription);
        System.out.println("The associated event has been successfully updated!");
    }

    // Helper method to reduce length of updateContactDetails() method
    private static void extractedMethod3(Person person, Result result) {
        person.setRelationship(result.relationship);
        person.setBirthdate(result.birthdate);
        person.setEmailID(result.email);
        person.setPhoneNumber(result.phoneNumber);
        System.out.println("The contact details have been successfully updated!");
    }

    // Helper method to reduce length of updateContactDetails() method
    private Event getEvent(Person person) {
        System.out.print("Enter the name of the event: ");
        String eventName = this.scanner.nextLine();
        Event existingEvent = person.getEventByName(eventName);
        return existingEvent;
    }

    // Helper method to reduce length of updateContactDetails() method
    private Person getPerson1() {
        System.out.print("Enter the name of the contact you wish to update: ");
        String name = this.scanner.nextLine();
        Person person = this.familyContactManager.getPersonByName(name);
        return person;
    }

    // Helper class for getResult() helper method
    private static class Result {
        public final String relationship;
        public final LocalDate birthdate;
        public final String email;
        public final int phoneNumber;

        // REQUIRES: 'phoneNumber' and 'birthdate' should be in appropriate format of data int and
        // LocalDate types respectively, as must the other fields be in String format
        // MODIFIES: this
        // EFFECTS: Constructs a Result object by initializing the given object with
        // a relationship, birthdate, email and phoneNumber ('phoneNumber' and 'birthdate' in int and
        // LocalDate types respectively, and all other fields in String format)
        public Result(String relationship, LocalDate birthdate, String email, int phoneNumber) {
            this.relationship = relationship;
            this.birthdate = birthdate;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }
    }

    // MODIFIES: this
    // EFFECTS: Saves the data onto a JSON file; catches a FileNotFoundException if found and
    // displays an error message accordingly
    private void saveContacts() {
        try {
            this.jsonWriter.open();
            this.jsonWriter.write(this.familyContactManager);
            this.jsonWriter.close();
            System.out.println("The data was successfully saved!");
        } catch (FileNotFoundException e) {
            System.out.println("There is an error saving data to the JSON file: " + e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the data from a JSON file; catches a IOException if found and
    // displays an error message accordingly
    private void loadContacts() {
        try {
            this.familyContactManager = this.jsonReader.read();
            System.out.println("The data was successfully loaded!");
        } catch (IOException e) {
            System.out.println("There is an error loading data from the JSON file: " + e.getMessage());
        }
    }
}