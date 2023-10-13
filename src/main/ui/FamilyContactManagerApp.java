package ui;

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
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addNewPerson() {
        System.out.print("Enter person's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter person's relation: ");
        String relation = scanner.nextLine();
        System.out.print("Enter person's birthday: ");
        String birthday = scanner.nextLine();
        // Add more details as needed

        Person person = new Person(name, relation, birthday);
        contactManager.addPerson(person);

        System.out.println("Person added successfully.");
    }

    private void viewAllContacts() {
        List<Person> contacts = contactManager.getAllContacts();
        System.out.println("All Contacts:");
        for (Person contact : contacts) {
            System.out.println(contact);
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
        // Retrieve the person by name
        Person person = contactManager.getPersonByName(name);

        if (person != null) {
            // Prompt the user to update details
            System.out.println("Current Details:");
            System.out.println(person);
            System.out.print("Enter new relation: ");
            String relation = scanner.nextLine();
            System.out.print("Enter new birthday: ");
            String birthday = scanner.nextLine();
            // Update more details as needed

            // Update the person's details
            person.setRelationship(relation);
            person.setBirthday(birthday);
            // Update more details as needed

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
