package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertEquals(1, contactManager.getAllContacts().size());
    }



    @Test
    public void testDeletePerson() {
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);
        contactManager.deletePerson("John");
        assertEquals(0, contactManager.getAllContacts().size());
    }

    @Test
    public void testAddEvent() {
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);
        assertEquals(1, contactManager.getAllEvents().size());
    }

    @Test
    public void testUpdatePersonDetails() {
        // Create a person
        Person person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);

        // Update email address
        Person updatedPerson = new Person("John", "Brother", "01/15/1980", "new@email.com", "987-654-3210");
        contactManager.updatePersonDetails("John", updatedPerson);
        Person retrievedPerson = contactManager.getPersonByName("John");
        assertEquals("new@email.com", retrievedPerson.getEmail());

        // Test updating non-existent person
        Person nonExistentPerson = new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210");
        assertThrows(IllegalArgumentException.class, () -> contactManager.updatePersonDetails("Alice", nonExistentPerson));
    }

    @Test
    public void testUpdateEvent() {
        // Create an event
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);

        // Update description
        Event updatedEvent = new Event("Graduation", "05/20/2023", "Updated description");
        contactManager.updateEvent("Graduation", updatedEvent);
        Event retrievedEvent = contactManager.getEventByName("Graduation");
        assertEquals("Updated description", retrievedEvent.getDescription());

        // Test updating non-existent event
        Event nonExistentEvent = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");
        assertThrows(IllegalArgumentException.class, () -> contactManager.updateEvent("Birthday Party", nonExistentEvent));
    }


    @Test
    public void testDeleteEvent() {
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);
        contactManager.deleteEvent("Graduation");
        assertEquals(0, contactManager.getAllEvents().size());
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
        FamilyContactManager contactManager = new FamilyContactManager();

        // Add some persons to the contact manager
        Person person1 = new Person("Alice", "Sister", "2000-01-01", "alice@example.com", "123-456-7890");
        Person person2 = new Person("Bob", "Brother", "2001-02-02", "bob@example.com", "987-654-3210");
        contactManager.addPerson(person1);
        contactManager.addPerson(person2);

        // Test when the person exists in the contact manager
        Person resultAlice = contactManager.getPersonByName("Alice");
        Person resultBob = contactManager.getPersonByName("Bob");

        assertNotNull(resultAlice);
        assertNotNull(resultBob);
        assertEquals("Alice", resultAlice.getName());
        assertEquals("Bob", resultBob.getName());

        // Test when the person does not exist in the contact manager
        Person resultNotFound = contactManager.getPersonByName("Carol");

        assertNotNull(resultNotFound);
        assertEquals("Not Found", resultNotFound.getName());
    }

    @Test
    public void testGetEventByName() {
        FamilyContactManager contactManager = new FamilyContactManager();

        // Add some events to the contact manager
        Event event1 = new Event("Birthday", "2023-01-01", "Party");
        Event event2 = new Event("Graduation", "2023-06-30", "Congratulations");
        contactManager.addEvent(event1);
        contactManager.addEvent(event2);

        // Test when the event exists in the contact manager
        Event resultBirthday = contactManager.getEventByName("Birthday");
        Event resultGraduation = contactManager.getEventByName("Graduation");

        assertNotNull(resultBirthday);
        assertNotNull(resultGraduation);
        assertEquals("Birthday", resultBirthday.getEventName());
        assertEquals("Graduation", resultGraduation.getEventName());

        // Test when the event does not exist in the contact manager
        Event resultNotFound = contactManager.getEventByName("Anniversary");

        assertNotNull(resultNotFound);
        assertEquals("Not Found", resultNotFound.getEventName());
    }


}
