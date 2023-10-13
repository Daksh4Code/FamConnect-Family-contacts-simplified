package ui;  // Ensure it's in the correct package

import model.Event;
import model.FamilyContactManager;
import model.Person;

import java.util.List;

public class FamilyContactManagerApp {
    private FamilyContactManager contactManager;

    public FamilyContactManagerApp() {
        contactManager = new FamilyContactManager();
    }

    public void start() {
        // Create and add persons and events
        Person person1 = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        Person person2 = new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210");

        Event event1 = new Event("Graduation", "05/20/2023", "John's college graduation");
        Event event2 = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");

        contactManager.addPerson(person1);
        contactManager.addPerson(person2);
        contactManager.addEvent(event1);
        contactManager.addEvent(event2);

        // Update person details
        Person updatedPerson = new Person("John", "Brother", "01/15/1980", "new@email.com", "987-654-3210");
        contactManager.updatePersonDetails("John", updatedPerson);

        // Print all contacts and events
        List<Person> contacts = contactManager.getAllContacts();
        List<Event> events = contactManager.getAllEvents();

        System.out.println("All Contacts:");
        for (Person contact : contacts) {
            System.out.println(contact);
        }

        System.out.println("\nAll Events:");
        for (Event event : events) {
            System.out.println(event);
        }

        // Perform other operations as per user stories
    }
}
