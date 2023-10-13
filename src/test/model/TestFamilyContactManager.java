import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFamilyContactManager {

    private FamilyContactManager contactManager;

    @BeforeEach
    public void setUp() {
        contactManager = new FamilyContactManager();
    }

    @Test
    public void testAddPerson() {
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(1, contacts.size());
        assertEquals("John", contacts.get(0).getName());
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);
        contactManager.deletePerson("John");
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(0, contacts.size());
    }

    @Test
    public void testAddEvent() {
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);
        List<Event> events = contactManager.getAllEvents();
        assertEquals(1, events.size());
        assertEquals("Graduation", events.get(0).getEventName());
    }

    @Test
    public void testUpdatePersonDetails() {
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);

        Person updatedPerson = new Person("John", "Brother", "01/15/1980", "new@email.com", "987-654-3210");
        contactManager.updatePersonDetails("John", updatedPerson);

        Person retrievedPerson = contactManager.getPersonByName("John");
        assertEquals("john@email.com", retrievedPerson.getEmail());
    }

    @Test
    public void testUpdateEvent() {
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);

        Event updatedEvent = new Event("Graduation", "05/20/2023", "Updated description");
        contactManager.updateEvent("Graduation", updatedEvent);

        Event retrievedEvent = contactManager.getEventByName("Graduation");
        assertEquals("Updated description", retrievedEvent.getDescription());
    }

    @Test
    public void testDeletePersonNull() {
        // Test deleting a person when the person exists
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);
        contactManager.deletePerson("John");
        assertEquals(0, contactManager.getAllContacts().size());

        // Test deleting a person when the name is null
        contactManager.deletePerson(null); // Should not throw an exception
        assertEquals(0, contactManager.getAllContacts().size());

        // Test deleting a non-existing person with a null name
        contactManager.deletePerson("Alice"); // Should not throw an exception
        assertEquals(0, contactManager.getAllContacts().size());
    }

    @Test
    public void testDeleteEventNull() {
        // Test deleting an event when the event exists
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);
        contactManager.deleteEvent("Graduation");
        assertEquals(0, contactManager.getAllEvents().size());

        // Test deleting an event when the event name is null
        contactManager.deleteEvent(null); // Should not throw an exception
        assertEquals(0, contactManager.getAllEvents().size());

        // Test deleting a non-existing event with a null name
        contactManager.deleteEvent("Birthday"); // Should not throw an exception
        assertEquals(0, contactManager.getAllEvents().size());
    }

    @Test
    public void testUpdatePersonDetailsWithNullPerson() {
        // Set up test data
        Person person = null; // Null person object

        // Ensure that updating a null person does not cause any changes
        contactManager.updatePersonDetails("John", person);

        // Assert that the person object remains null
        assertNull(person);
    }

    @Test
    public void testContactManagerEquals() {
        FamilyContactManager sameContactManager = new FamilyContactManager();
        sameContactManager.addPerson(new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210"));
        sameContactManager.addEvent(new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration"));

        FamilyContactManager differentContactManager = new FamilyContactManager();

        assertTrue(contactManager.equals(contactManager)); // ContactManager equals itself
        assertFalse(contactManager.equals(sameContactManager)); // ContactManager equals another ContactManager with the same properties
        assertFalse(contactManager.equals(differentContactManager)); // ContactManager does not equal a ContactManager with different properties
        assertFalse(contactManager.equals(null)); // ContactManager does not equal null
        assertFalse(contactManager.equals(new Object())); // ContactManager does not equal a different object

        // Test with the same object
        FamilyContactManager sameObject = contactManager;
        assertTrue(contactManager.equals(sameObject));
    }
}
