package model;

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
        assertEquals("new@email.com", retrievedPerson.getEmail());

        Person nonExistentPerson = new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210");
        assertThrows(IllegalArgumentException.class, () -> contactManager.updatePersonDetails("Alice", nonExistentPerson));
    }

    @Test
    public void testUpdateEvent() {
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);

        Event updatedEvent = new Event("Graduation", "05/20/2023", "Updated description");
        contactManager.updateEvent("Graduation", updatedEvent);

        Event retrievedEvent = contactManager.getEventByName("Graduation");
        assertEquals("Updated description", retrievedEvent.getDescription());

        Event nonExistentEvent = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");
        assertThrows(IllegalArgumentException.class, () -> contactManager.updateEvent("Birthday Party", nonExistentEvent));
    }

    @Test
    public void testDeleteEvent() {
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);
        contactManager.deleteEvent("Graduation");
        List<Event> events = contactManager.getAllEvents();
        assertEquals(0, events.size());
    }

    @Test
    public void testContactManagerEquals() {
        FamilyContactManager sameContactManager = new FamilyContactManager();
        sameContactManager.addPerson(new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210"));
        sameContactManager.addEvent(new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration"));

        FamilyContactManager differentContactManager = new FamilyContactManager();

        assertTrue(contactManager.equals(contactManager)); // ContactManager equals itself
        assertTrue(contactManager.equals(sameContactManager)); // ContactManager equals another ContactManager with the same properties
        assertFalse(contactManager.equals(differentContactManager)); // ContactManager does not equal a ContactManager with different properties
        assertFalse(contactManager.equals(null)); // ContactManager does not equal null
        assertFalse(contactManager.equals(new Object())); // ContactManager does not equal a different object

        // Test with the same object
        FamilyContactManager sameObject = contactManager;
        assertTrue(contactManager.equals(sameObject));
    }

    @Test
    public void testGetPersonByName() {
        Person person1 = new Person("Alice", "Sister", "2000-01-01", "alice@example.com", "123-456-7890");
        Person person2 = new Person("Bob", "Brother", "2001-02-02", "bob@example.com", "987-654-3210");

        contactManager.addPerson(person1);
        contactManager.addPerson(person2);

        Person resultAlice = contactManager.getPersonByName("Alice");
        Person resultBob = contactManager.getPersonByName("Bob");

        assertNotNull(resultAlice);
        assertEquals("Alice", resultAlice.getName());
        assertNotNull(resultBob);
        assertEquals("Bob", resultBob.getName());

        Person resultNotFound = contactManager.getPersonByName("Carol");

        assertNotNull(resultNotFound);
        assertEquals("Not Found", resultNotFound.getName());
    }

    @Test
    public void testGetEventByName() {
        Event event1 = new Event("Birthday", "2023-01-01", "Party");
        Event event2 = new Event("Graduation", "2023-06-30", "Congratulations");

        contactManager.addEvent(event1);
        contactManager.addEvent(event2);

        Event resultBirthday = contactManager.getEventByName("Birthday");
        Event resultGraduation = contactManager.getEventByName("Graduation");

        assertNotNull(resultBirthday);
        assertEquals("Birthday", resultBirthday.getEventName());
        assertNotNull(resultGraduation);
        assertEquals("Graduation", resultGraduation.getEventName());

        Event resultNotFound = contactManager.getEventByName("Anniversary");

        assertNotNull(resultNotFound);
        assertEquals("Not Found", resultNotFound.getEventName());
    }

    @Test
    public void testDeletePersonNull() {
        // Test deleting a person when the person exists
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);
        contactManager.deletePerson("John");
        assertEquals(0, contactManager.getAllContacts().size());

        // Test deleting a person when the person is null
        contactManager.deletePerson(null); // Should not throw an exception
        assertEquals(0, contactManager.getAllContacts().size());
    }

    @Test
    public void testDeleteEventNull() {
        // Test deleting an event when the event exists
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);
        contactManager.deleteEvent("Graduation");
        assertEquals(0, contactManager.getAllEvents().size());

        // Test deleting an event when the event is null
        contactManager.deleteEvent(null); // Should not throw an exception
        assertEquals(0, contactManager.getAllEvents().size());
    }

    @Test
    public void testUpdatePersonDetailsNull() {
        // Test updating a person's details when the person exists
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);

        Person updatedPerson = new Person("John", "Brother", "01/15/1980", "new@email.com", "987-654-3210");
        contactManager.updatePersonDetails("John", updatedPerson);
        Person retrievedPerson = contactManager.getPersonByName("John");
        assertEquals("new@email.com", retrievedPerson.getEmail());

        // Test updating a person's details when the name is null
        Person updatedPersonNullName = new Person("John", "Brother", "01/15/1980", "another@email.com", "987-654-3210");
        contactManager.updatePersonDetails(null, updatedPersonNullName);
        assertEquals("another@email.com", retrievedPerson.getEmail()); // Ensure email remains unchanged

        // Test updating a person's details when the updatedPerson is null
        contactManager.updatePersonDetails("John", null);
        assertEquals("another@email.com", retrievedPerson.getEmail());// Ensure email remains unchanged

        // Test updating a person's details when the person is null
        Person personNull = null;
        assertNull(personNull);

        // Create a person and then update it with a null person
        Person randomPerson = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(randomPerson);
        contactManager.updatePersonDetails("John", personNull);

    }


}
