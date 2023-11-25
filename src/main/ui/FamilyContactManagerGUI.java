package ui;

import model.*;
import model.Event;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// The 'FamilyContactManagerGUI' class represents a GUI application for managing family contacts and
// the events associated with each unique contact.
// Provides a button-driven interface to add contacts, view contacts, save to a file, load from a file
// and search family contacts by relation and display all contact any events associated with each unique
// 'Person' (contact) object.
// It also gives an option to display the contact whose birthday is coming up next.
public class FamilyContactManagerGUI {
    private JFrame frame;
    private List<Person> familyContacts;
    private JLabel imageLabel;
    private JTextField nameField;
    private JTextField relationshipField;
    private JTextField birthdateField;
    private JTextField emailField;
    private JTextField phoneNumberField;

    // MODIFIES: this
    // EFFECTS: Constructs a FamilyContactManagerGUI which initializes a list of contacts to be stored,
    // a new JFrame with appropriate heading as the GUI and a new JLabel
    // The layout & size of the GUI, buttons to be displayed and all required settings which are
    // needed for the GUI are set
    public FamilyContactManagerGUI() {
        familyContacts = new ArrayList<>();
        frame = new JFrame("FamConnect Version 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));
        createAndAddButtons();
        frame.setVisible(true);
        imageLabel = new JLabel();
        frame.add(imageLabel);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a button to the frame with the label "Add a new contact."
    // When the button is clicked, it invokes the addContact() method
    private void createAndAddAddContactButton() {
        JButton addContactButton = new JButton("Add a new contact");
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });
        frame.add(addContactButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a button to the frame with the label "View all contacts."
    // When the button is clicked, it invokes the viewContacts() method
    private void createAndAddViewContactButton() {
        JButton viewContactsButton = new JButton("View all contacts");
        viewContactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewContacts();
            }
        });
        frame.add(viewContactsButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a button to the frame with the label "Save contacts."
    // When the button is clicked, it invokes the saveContacts() method
    private void createAndAddSaveContactButton() {
        JButton saveContactsButton = new JButton("Save contacts");
        saveContactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveContacts();
            }
        });
        frame.add(saveContactsButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a button to the frame with the label "Load contacts."
    // When the button is clicked, it invokes the loadContacts() method
    private void createAndAddLoadContactButton() {
        JButton loadContactsButton = new JButton("Load contacts");
        loadContactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadContacts();
            }
        });
        frame.add(loadContactsButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a button to the frame with the label "Exit."
    // When the button is clicked, it exits the GUI.
    private void createAndAddExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(exitButton);
    }

    // MODIFIES : this
    // EFFECTS: Creates and adds buttons for various functionalities, including adding a new contact,
    // viewing contacts, saving contacts, loading contacts, searching by relationship,
    // and showing upcoming birthdays. Each button is associated with its respective action.
    private void createAndAddButtons() {
        createAndAddAddContactButton();
        createAndAddViewContactButton();
        createAndAddSaveContactButton();
        createAndAddLoadContactButton();
        JButton searchByRelationshipButton = new JButton("Search by relationship");
        JButton showNextBirthdayButton = new JButton("Show upcoming birthday");
        searchByRelationshipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByRelationship();
            }
        });
        showNextBirthdayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextBirthday();
            }
        });
        frame.add(searchByRelationshipButton);
        frame.add(showNextBirthdayButton);
        createAndAddExitButton();
    }

    // EFFECTS: Displays a dialog box for adding a new contact. It includes input fields for
    // the name, relationship, birthdate, email, and phone number of the contact. Upon clicking the
    // "Save contact" button, it checks if the contact can be saved using the saveContactBool
    // method. If successful, it opens the addEventsDialog() method and disposes of the addContactDialog.
    private void addContact() {
        JDialog addContactDialog = createContactDialog("Add a new contact", 6);
        JButton saveContactButton = new JButton("Save contact");
        addContactDialog.add(saveContactButton);
        saveContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saveContactBool()) {
                    addEventsDialog();
                    addContactDialog.dispose();
                }
            }
        });
        addContactDialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Acts as a helper method (to reduce length of addContact() method) which creates
    // a JDialog with input fields for name, relationship, birthdate, email, and phone number.
    // The dialog is formatted with a specified title and a grid layout with the given
    // number of rows.
    private JDialog createContactDialog(String title, int rows) {
        JDialog contactDialog = new JDialog(frame, title, true);
        contactDialog.setSize(300, 200);
        contactDialog.setLayout(new GridLayout(rows, 2));
        nameField = new JTextField();
        relationshipField = new JTextField();
        birthdateField = new JTextField();
        emailField = new JTextField();
        phoneNumberField = new JTextField();
        contactDialog.add(new JLabel("Name:"));
        contactDialog.add(nameField);
        contactDialog.add(new JLabel("Relationship:"));
        contactDialog.add(relationshipField);
        contactDialog.add(new JLabel("B'day (YYYY-MM-DD):"));
        contactDialog.add(birthdateField);
        contactDialog.add(new JLabel("Email:"));
        contactDialog.add(emailField);
        contactDialog.add(new JLabel("Phone Number:"));
        contactDialog.add(phoneNumberField);
        return contactDialog;
    }

    // MODIFIES: this
    // EFFECTS: Validates input fields for name, relationship, email, and phone number in the
    // 'Add a new contact' dialog. Displays error messages accordingly for invalid inputs.
    // If the validations pass, it creates a new Person, sets contact details, adds it to the lst of
    // contacts, clears the input fields, and returns true.
    // If there is an error in parsing or validating fields, displays an error message and
    // returns false.
    private boolean saveContactBool() {
        if (validateField(nameField, "Name", "cannot be empty. Please enter a name.")
                && validateField(relationshipField, "Relation", "cannot be empty. Please enter a relation.")
                && validateEmail()
                && validatePhoneNumber()) {
            return saveContact();
        }
        return false;
    }

    // EFFECTS: Validates a JTextField to ensure it is not empty. Displays an error message if empty.
    // Returns true if validation passes, false otherwise
    private boolean validateField(JTextField field, String fieldName, String errorMessage) {
        if (field.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, fieldName + " " + errorMessage);
            return false;
        }
        return true;
    }

    // EFFECTS: Validates the emailField in the 'Add a new contact' dialog
    // Displays an error message for an invalid email format
    // Returns true if the email is valid or empty, false otherwise
    private boolean validateEmail() {
        if (!emailField.getText().isEmpty()) {
            if (!emailField.getText().contains("@")
                    || (!emailField.getText().contains(".com") && !emailField.getText().contains(".COM"))) {
                JOptionPane.showMessageDialog(frame, "Invalid email format. Email must be in "
                        + "the format 'example@email.com'. Please try again.");
                return false;
            }
        }
        return true;
    }

    // EFFECTS: Validates the phoneNumberField in the 'Add a new contact' dialog
    // Displays an error message for an invalid phone number length
    // Returns true if the phone number is valid, false otherwise
    private boolean validatePhoneNumber() {
        if (!phoneNumberField.getText().isEmpty() && (phoneNumberField.getText().length() < 8
                || phoneNumberField.getText().length() > 10)) {
            JOptionPane.showMessageDialog(frame, "Invalid phone number. Please enter a "
                     + "valid phone number between 8 and 10 digits.");
            return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: Creates a new Person object, sets contact details, adds it to familyContacts, clears input
    // fields, and returns true if successful
    // Displays an error message and returns false if there is an error in parsing or validating fields
    private boolean saveContact() {
        Person person = new Person();
        try {
            person.setName(nameField.getText());
            person.setRelationship(relationshipField.getText());
            person.setBirthdate(LocalDate.parse(birthdateField.getText()));
            person.setEmailID(emailField.getText());
            person.setPhoneNumber(Integer.parseInt(phoneNumberField.getText()));
            familyContacts.add(person);
            clearFields();
            return true;
        } catch (DateTimeParseException de) {
            JOptionPane.showMessageDialog(frame, "Invalid date format. Please try again. "
                    + "Date should be in YYYY-MM-DD format.");
        } catch (NumberFormatException ne) {
            if (!phoneNumberField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Invalid phone number format. Please try "
                        + "again. Phone numbers can only have numbers.");
            }
        }
        return false;
    }

    // REQUIRES: The saveEvent() method should be properly implemented.
    // MODIFIES: this
    // EFFECTS: Creates and displays a dialog for adding events to the frame. The dialog includes
    // input fields for the event name, date, and description, as well as buttons for saving
    // the event. The user can choose to add another event or close the dialog. The method handles
    // the save action and displays an error message for an invalid date format.
    private JDialog createAddEventsDialog() {
        JDialog addEventsDialog = new JDialog(frame, "Add Events", true);
        addEventsDialog.setSize(300, 200);
        addEventsDialog.setLayout(new GridLayout(4, 2));
        JTextField eventNameField = new JTextField();
        JTextField eventDateField = new JTextField();
        JTextField eventDescriptionField = new JTextField();
        JButton saveEventButton = new JButton("Save Event");
        addEventsDialog.add(new JLabel("Event Name:"));
        addEventsDialog.add(eventNameField);
        addEventsDialog.add(new JLabel("Event Date (YYYY-MM-DD):"));
        addEventsDialog.add(eventDateField);
        addEventsDialog.add(new JLabel("Event Description:"));
        addEventsDialog.add(eventDescriptionField);
        addEventsDialog.add(saveEventButton);
        saveEventButton.addActionListener(createSaveEventButtonListener(addEventsDialog, eventNameField,
                eventDateField, eventDescriptionField));
        return addEventsDialog;
    }

    // MODIFIES: this
    // EFFECTS: Creates an action listener for the saveEventButton. When the button is
    // clicked, the handleSaveEvent() method is called to handle the save action.
    private ActionListener createSaveEventButtonListener(JDialog addEventsDialog, JTextField eventNameField,
                                                         JTextField eventDateField, JTextField eventDescriptionField) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveEvent(eventNameField.getText(), eventDateField.getText(),
                        eventDescriptionField.getText(), addEventsDialog);
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: Handles the save action for the event
    // Parses the event date and calls the saveEvent method, then disposes of the addEventsDialog
    // Displays an error message for an invalid date format.
    private void handleSaveEvent(String eventName, String eventDateText,
                                 String eventDescription, JDialog addEventsDialog) {
        if (!eventDateText.isEmpty()) {
            try {
                LocalDate eventDate = LocalDate.parse(eventDateText);
                saveEvent(eventName, eventDate, eventDescription);
                addEventsDialog.dispose();
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid event date format. "
                        + "Please try again. Date should be in the format YYYY-MM-DD");
            }
        } else {
            saveEvent(eventName, null, eventDescription);
            addEventsDialog.dispose();
        }
    }

    // REQUIRES: The createAddEventsDialog() method should be properly implemented.
    // MODIFIES: this
    // EFFECTS: Displays a series of dialogs for adding events to the frame. Each dialog includes
    // input fields for the event name, date, and description, as well as buttons for saving the
    // event or adding another event.
    // The method handles the save action and allows the user to continue adding events
    // or close the dialog.
    private void addEventsDialog() {
        while (true) {
            JDialog addEventsDialog = createAddEventsDialog();
            addEventsDialog.setVisible(true);

            if (JOptionPane.showConfirmDialog(frame, "Do you want to add another event?", "Add another event",
                    JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Saves the event to the associated contact
    // If the event date is in an invalid format, displays an error message in a dialog box.
    private void saveEvent(String eventName, LocalDate eventDate, String eventDescription) {
        try {
            familyContacts.get(familyContacts.size() - 1).addEvent(new Event(eventName, eventDate, eventDescription));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(frame, "Invalid date format. Please try again. Date "
                    + "should be in the format YYYY-MM-DD");
        }
    }

    // EFFECTS: Displays a dialog box titled 'All Contacts' containing a well-formatted list of
    // all family contacts, including their names, relationships, birthdates, email addresses,
    // phone numbers, and associated events with event names, dates, and descriptions.
    private void viewContacts() {
        StringBuilder contactsInfo = new StringBuilder();
        for (Person person : familyContacts) {
            contactsInfo.append("Name: ").append(person.getName())
                    .append(", Relationship: ").append(person.getRelationship())
                    .append(", Birthdate: ").append(person.getBirthdate())
                    .append(", Email: ").append(person.getEmailID())
                    .append(", Phone Number: ").append(person.getPhoneNumber())
                    .append("\n");
            for (Event event : person.getEvents()) {
                contactsInfo.append("    Event: ").append(event.getEventName())
                        .append(", Date: ").append(event.getEventDate())
                        .append(", Description: ").append(event.getEventDescription())
                        .append("\n");
            }
            contactsInfo.append("\n");
        }
        JOptionPane.showMessageDialog(frame, contactsInfo.toString(), "All Contacts", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: Loads the family contacts from a JSON file named "testContacts.json" in the "./data/"
    // directory
    // Displays a success message if the contacts are successfully loaded; otherwise, displays an
    // error message
    // Note: File path name to change as per local device
    private void loadContacts() {
        try {
            JsonReader jsonReader = new JsonReader("./data/testContacts.json"); // Change file path name
                                                                                    // according to your local machine
            FamilyContactManager loadedManager = jsonReader.read();
            familyContacts.clear();
            familyContacts.addAll(loadedManager.getAllContacts());
            JOptionPane.showMessageDialog(frame, "Contacts were successfully loaded!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error in loading contacts!");
        }
    }

    // EFFECTS: Saves the family contacts to a JSON file named "testContacts.json" in the "./data/"
    // directory
    // Displays a success message if the contacts are successfully saved; otherwise, displays an
    // error message
    // Note: File path name to change as per local device
    private void saveContacts() {
        JsonWriter jsonWriter = null;
        try {
            jsonWriter = new JsonWriter("./data/testContacts.json"); // Change file path name
                                                                            // according to your local machine
            jsonWriter.open();
            jsonWriter.write(new FamilyContactManager(familyContacts));
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Contacts were successfully saved!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error in saving contacts!");
        } finally {
            if (jsonWriter != null) {
                jsonWriter.close();
            }
        }
    }

    // EFFECTS: Prompts the user to enter a relationship to search
    // If a valid relationship is entered, searches the familyContacts list for contacts with
    // the specified relationship.
    // If contacts are found, displays the search results using the displaySearchResults() method.
    // If no contacts are found, displays a message indicating that no contacts were found.
    private void searchByRelationship() {
        String relationToSearch = JOptionPane.showInputDialog(frame, "Enter the relationship you wish to search:");
        if (relationToSearch != null && !relationToSearch.isEmpty()) {
            List<Person> results = searchContactsByRelationship(relationToSearch);
            if (!results.isEmpty()) {
                displaySearchResults(results); // Pass the results to displaySearchResults
            } else {
                JOptionPane.showMessageDialog(frame, "No contacts were found with the specified relationship!");
            }
        }
    }

    // EFFECTS: Searches the familyContacts list for contacts with the specified relationship
    // Returns a list of Person (contacts) objects that match the specified relationship
    private List<Person> searchContactsByRelationship(String relation) {
        List<Person> results = new ArrayList<>();
        String searchTermLower = relation.toLowerCase().trim();
        for (Person person : familyContacts) {
            String storedRelationLower = person.getRelationship().toLowerCase().trim();
            if (storedRelationLower.equals(searchTermLower)) {
                results.add(person);
            }
        }
        return results;
    }

    // EFFECTS: Opens a dialog box titled 'Search results' which displays the search results (the
    // appropriate contacts and their subsequent details, including associated events) in a well formatted
    // manner, when the 'Search by relationship' option on the GUI is used
    private void displaySearchResults(List<Person> results) {
        StringBuilder resultInfo = new StringBuilder("Results:\n");
        for (Person person : results) {
            resultInfo.append("    Name: ").append(person.getName())
                    .append(", Relationship: ").append(person.getRelationship())
                    .append(", Birthdate: ").append(person.getBirthdate())
                    .append(", Email: ").append(person.getEmailID())
                    .append(", Phone Number: ").append(person.getPhoneNumber())
                    .append("\n");
            for (Event event : person.getEvents()) {
                resultInfo.append("         Event: ").append(event.getEventName())
                        .append(", Date: ").append(event.getEventDate())
                        .append(", Description: ").append(event.getEventDescription())
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(frame, resultInfo.toString(), "Search results", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: Displays a dialog box titled 'Upcoming birthdays' containing a well-formatted list
    // of upcoming birthdays if they are on the same day (names and birthdays) based on the current
    // date. Otherwise, displays only the name and birthday of the contact of the one upcoming
    // birthday.
    // If there are no upcoming birthdays, displays a message indicating the same.
    private void showNextBirthday() {
        List<Person> upcomingBirthdays = getNextBirthdayPeople();
        if (upcomingBirthdays != null && !upcomingBirthdays.isEmpty()) {
            StringBuilder message = new StringBuilder("Upcoming birthdays:\n");
            for (Person person : upcomingBirthdays) {
                message.append("    Name: ").append(person.getName()).append("\n");
                message.append("    Birthday: ").append(formatBirthday(person.getBirthdate())).append("\n\n");
            }
            JOptionPane.showMessageDialog(frame, message.toString(), "Upcoming birthdays",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "There are no upcoming birthdays.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Returns a list of contacts with upcoming birthdays or the person whose birthday is
    // today, sorted by their birthdates in ascending order. Returns null if there are no
    // upcoming birthdays
    private List<Person> getNextBirthdayPeople() {
        LocalDate currentDate = LocalDate.now();
        List<Person> upcomingBirthdays = new ArrayList<>();
        LocalDate earliestBirthday = null;
        for (Person person : familyContacts) {
            LocalDate nextBirthday = getNextBirthdayDate(person.getBirthdate(), currentDate);
            if (nextBirthday.isAfter(currentDate) || nextBirthday.isEqual(currentDate)) {
                if (earliestBirthday == null || nextBirthday.isBefore(earliestBirthday)) {
                    earliestBirthday = nextBirthday;
                    upcomingBirthdays.clear();
                    upcomingBirthdays.add(person);
                } else if (nextBirthday.isEqual(earliestBirthday)) {
                    upcomingBirthdays.add(person);
                }
            }
        }
        return upcomingBirthdays.isEmpty() ? null : upcomingBirthdays;
    }

    // EFFECTS: Returns the date of the next upcoming birthday after the current date.
    // If the birthday has already occurred this year, returns the date of the next year's birthday.
    private LocalDate getNextBirthdayDate(LocalDate birthday, LocalDate currentDate) {
        LocalDate nextBirthday = birthday.withYear(currentDate.getYear());
        if (nextBirthday.isBefore(currentDate) || nextBirthday.isEqual(currentDate)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        return nextBirthday;
    }

    // EFFECTS: Formats the birthday date to include the year of the current/next year's birthday.
    private String formatBirthday(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        LocalDate formattedBirthday = getNextBirthdayDate(birthday, currentDate);
        return formattedBirthday.format(DateTimeFormatter.ofPattern("MMM d, uuuu"));
    }

    // MODIFIES: this
    // EFFECTS: Clears the text in the input fields of the dialog, allowing the user to input new data
    private void clearFields() {
        nameField.setText("");
        relationshipField.setText("");
        birthdateField.setText("");
        emailField.setText("");
        phoneNumberField.setText("");
    }

    // EFFECTS: The 'Main' method initializes the 'FamilyContactManagerGUI' and starts the application.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FamilyContactManagerGUI();
            }
        });
    }
}
