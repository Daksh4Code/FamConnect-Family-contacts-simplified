package ui;

import model.FamilyContact;
import model.Database;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Database database;

    public Main(Database database) {
        this.database = database;
    }

    private void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter relationship: ");
        String relationship = scanner.nextLine();
        System.out.print("Enter birthday: ");
        String birthday = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        FamilyContact contact = new FamilyContact(name, relationship, birthday, email, phoneNumber);
        database.addContact(contact);
        System.out.println("Contact added successfully!");
    }

    private void viewAllContacts() {
        List<FamilyContact> contacts = database.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (FamilyContact contact : contacts) {
                printContactInfo(contact);
            }
        }
    }

    private void updateContact() {
        System.out.print("Enter name of the contact to update: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();

        database.updateContact(name, newEmail, newPhoneNumber);
        System.out.println("Contact information updated successfully!");
    }

    private void deleteContact() {
        System.out.print("Enter name of the contact to delete: ");
        String name = scanner.nextLine();
        if (database.deleteContact(name)) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add new contact\n2. View contacts\n3. Update contact\n4. Delete contact\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addContact();
            } else if (choice == 2) {
                viewAllContacts();
            } else if (choice == 3) {
                updateContact();
            } else if (choice == 4) {
                deleteContact();
            } else if (choice == 5) {
                exit = true;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private void printContactInfo(FamilyContact contact) {
        System.out.println("Name: " + contact.getName());
        System.out.println("Relationship: " + contact.getRelationship());
        System.out.println("Birthday: " + contact.getBirthdate());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Phone Number: " + contact.getPhoneNumber());
        System.out.println("Custom Events: " + contact.getCustomEvents());
        System.out.println();
    }

    public static void main(String[] args) {
        Database database = new Database();
        Main famContactUI = new Main(database);
        famContactUI.run();
    }

}
