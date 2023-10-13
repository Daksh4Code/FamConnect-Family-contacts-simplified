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
    @Test
    public void testGetPersonByNameNotFound() {
        Person person1 = new Person("Alice", "Sister", "2000-01-01", "alice@example.com", "123-456-7890");
        contactManager.addPerson(person1);

        Person resultNotFound = contactManager.getPersonByName("Bob");
        assertNull(resultNotFound);
    }



    @Test
    public void testAddNullPerson() {
        // Adding a null person should do nothing
        contactManager.addPerson(null);
        List<Person> contacts = contactManager.getAllContacts();

        assertTrue(contacts.isEmpty());
    }

    @Test
    public void testAddMultiplePersons() {
        // Adding multiple valid persons
        Person person1 = new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210");
        Person person2 = new Person("Bob", "Brother", "01/10/1990", "bob@email.com", "123-456-7890");

        contactManager.addPerson(person1);
        contactManager.addPerson(person2);
        List<Person> contacts = contactManager.getAllContacts();

        assertEquals(2, contacts.size());
        assertTrue(contacts.contains(person1));
        assertTrue(contacts.contains(person2));
    }


    @Test
    public void testAddNullEvent() {
        // Adding a null event should do nothing
        contactManager.addEvent(null);
        List<Event> events = contactManager.getAllEvents();

        assertTrue(events.isEmpty());
    }

    @Test
    public void testAddMultipleEvents() {
        // Adding multiple valid events
        Event event1 = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");
        Event event2 = new Event("Anniversary", "05/15/2023", "Family anniversary celebration");

        contactManager.addEvent(event1);
        contactManager.addEvent(event2);
        List<Event> events = contactManager.getAllEvents();

        assertEquals(2, events.size());
        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
    }



    @Test
    public void testUpdateNonExistentEvent() {
        // Attempting to update a non-existent event should do nothing
        Event updatedEvent = new Event("Anniversary", "05/15/2023", "Family anniversary celebration");
        contactManager.updateEvent("Anniversary", updatedEvent);

        List<Event> events = contactManager.getAllEvents();

        assertTrue(events.isEmpty());
    }

    @Test
    public void testUpdateNullEvent() {
        // Attempting to update a null event should do nothing
        contactManager.updateEvent("Birthday Party", null);

        List<Event> events = contactManager.getAllEvents();

        assertTrue(events.isEmpty());
    }

    @Test
    public void testUpdateEventDescription() {
        // Creating an event
        Event event = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");
        contactManager.addEvent(event);

        // Updating the event's description
        Event updatedEvent = new Event("Birthday Party", "03/10/2023", "Updated description");
        contactManager.updateEvent("Birthday Party", updatedEvent);

        Event retrievedEvent = contactManager.getEventByName("Birthday Party");
        assertEquals("Updated description", retrievedEvent.getDescription());
    }



    @Test
    public void testGetPersonByNameWithNullName() {
        // Attempting to retrieve a person with a null name should return null
        Person retrievedPerson = contactManager.getPersonByName(null);
        assertNull(retrievedPerson);
    }

    @Test
    public void testGetEventByName() {
        // Adding an event to the contact manager
        Event event1 = new Event("Birthday", "2023-01-01", "Party");
        contactManager.addEvent(event1);

        // Retrieving the event by name
        Event retrievedEvent = contactManager.getEventByName("Birthday");
        assertNotNull(retrievedEvent);
        assertEquals("Birthday", retrievedEvent.getEventName());
        assertEquals("2023-01-01", retrievedEvent.getEventDate());
        assertEquals("Party", retrievedEvent.getDescription());
    }

    @Test
    public void testGetEventByNameNotFound() {
        // Retrieving an event that does not exist
        Event retrievedEvent = contactManager.getEventByName("Anniversary");
        assertNull(retrievedEvent);
    }

    @Test
    public void testGetEventByNameWithNullName() {
        // Attempting to retrieve an event with a null name should return null
        Event retrievedEvent = contactManager.getEventByName(null);
        assertNull(retrievedEvent);
    }

    @Test
    public void testGetPersonByName() {
        // Adding a person to the contact manager
        Person person1 = new Person("Alice", "Sister", "2000-01-01", "alice@example.com", "123-456-7890");
        contactManager.addPerson(person1);

        // Retrieving the person by name
        Person retrievedPerson = contactManager.getPersonByName("Alice");
        assertNotNull(retrievedPerson);
        assertEquals("Alice", retrievedPerson.getName());
        assertEquals("Sister", retrievedPerson.getRelationship());
        assertEquals("2000-01-01", retrievedPerson.getBirthday());
        assertEquals("alice@example.com", retrievedPerson.getEmail());
        assertEquals("123-456-7890", retrievedPerson.getPhoneNumber());
    }
}






