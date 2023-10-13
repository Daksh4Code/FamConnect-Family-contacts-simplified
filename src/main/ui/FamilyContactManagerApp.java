package ui;

import model.Event;
import model.FamilyContactManager;
import model.Person;

import java.util.List;
import java.util.Scanner;

public class FamilyContactManagerApp {
    private FamilyContactManager contactManager;
    private Scanner scanner;

    public FamilyContactManagerApp() {
        contactManager = new FamilyContactManager();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                addNewPerson();
            } else if (choice == 2) {
                viewAllContacts();
            } else if (choice == 3) {
                deletePerson();
            } else if (choice == 4) {
                updatePersonDetails();
            } else if (choice == 5) {
                isRunning = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Family Contact Manager");
        System.out.println("1. Add New Person");
        System.out.println("2. View All Contacts");
        System.out.println("3. Delete Person");
        System.out.println("4. Update Person Details");
        System.out.println("5. Add Custom Event");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }


    private void addNewPerson() {
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
            System.out.print("Add a custom event? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter event name: ");
                String eventName = scanner.nextLine();
                System.out.print("Enter event date: ");
                String eventDate = scanner.nextLine();
                System.out.print("Enter event description: ");
                String eventDescription = scanner.nextLine();
                Event event = new Event(eventName, eventDate, eventDescription);
                person.addCustomEvent(event);
            } else {
                break;
            }
        }

        contactManager.addPerson(person);

        System.out.println("Person added successfully.");
    }


    private void addCustomEventToPerson(Person person) {
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter event date: ");
        String eventDate = scanner.nextLine();
        System.out.print("Enter event description: ");
        String eventDescription = scanner.nextLine();

        Event customEvent = new Event(eventName, eventDate, eventDescription);
        person.addCustomEvent(customEvent);
        System.out.println("Custom event added successfully.");
    }

    private void viewAllContacts() {
        List<Person> contacts = contactManager.getAllContacts();
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


    private void deletePerson() {
        System.out.print("Enter the name of the person to delete: ");
        String name = scanner.nextLine();
        contactManager.deletePerson(name);
        System.out.println("Person deleted successfully.");
    }


    private void updatePersonDetails() {
        System.out.print("Enter the name of the person to update: ");
        String name = scanner.nextLine();
        Person person = contactManager.getPersonByName(name);
        if (person != null) {
            System.out.println("Current Details:");
            System.out.println(person);
            System.out.print("Enter new relation: ");
            String relation = scanner.nextLine();
            System.out.print("Enter new birthday: ");
            String birthday = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String phoneNumber = scanner.nextLine();
            while (true) {
                System.out.print("Update custom events? (yes/no): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    Event existingEvent = person.getCustomEventByName(eventName);

                    if (existingEvent != null) {
                        System.out.print("Enter updated event date: ");
                        String eventDate = scanner.nextLine();
                        System.out.print("Enter updated event description: ");
                        String eventDescription = scanner.nextLine();
                        existingEvent.setEventDate(eventDate);
                        existingEvent.setDescription(eventDescription);
                        System.out.println("Custom event updated successfully.");
                    } else {
                        System.out.println("Event not found for this person.");
                    }
                } else {
                    break;
                }
            }
            person.setRelationship(relation);
            person.setBirthday(birthday);
            person.setEmail(email);
            person.setPhoneNumber(phoneNumber);
            System.out.println("Details updated successfully.");
        } else {
            System.out.println("Person not found.");
        }
    }

    public static void main(String[] args) {
        FamilyContactManagerApp app = new FamilyContactManagerApp();
        app.start();
    }
}
