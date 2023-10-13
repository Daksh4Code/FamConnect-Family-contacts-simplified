package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestFamilyContactManager {
    private FamilyContactManager contactManager;

    @BeforeEach
    public void runBefore() {
        contactManager = new FamilyContactManager();
    }

    @Test
    public void testAddPerson() {
        Person person = new Person("Alice", "Sister", "10/12/1982", "alice@email.com", "123-456-7890");
        contactManager.addPerson(person);
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(1, contacts.size());
        assertEquals("Alice", contacts.get(0).getName());
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person("Alice", "Sister", "10/12/1982", "alice@email.com", "123-456-7890");
        contactManager.addPerson(person);
        contactManager.deletePerson("Alice");
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(0, contacts.size());
    }

    @Test
    public void testAddEvent() {
        Event event = new Event("Anniversary", "08/30/2020", "John's college graduation");
        contactManager.addEvent(event);
        List<Event> events = contactManager.getAllEvents();
        assertEquals(1, events.size());
        assertEquals("Anniversary", events.get(0).getEventName());
    }

    @Test
    public void testUpdatePersonDetails() {
        Person person = new Person("John", "Brother", "01/19/1985", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);
        Person updatedPerson = new Person("John", "Brother", "02/19/1985", "updatedPerson@email.com", "987-654-3210");
        contactManager.updatePersonDetails("John", updatedPerson);
        Person retrievedPerson = contactManager.getPersonByName("John");
        assertEquals("john@email.com", retrievedPerson.getEmail());
    }

    @Test
    public void testUpdateEvent() {
        Event event = new Event("Graduation", "05/20/2023", "Mark's college graduation");
        contactManager.addEvent(event);
        Event updatedEvent = new Event("Graduation", "05/20/2023", "Updated description of graduation");
        contactManager.updateEvent("Graduation", updatedEvent);
        Event retrievedEvent = contactManager.getEventByName("Graduation");
        assertEquals("Updated description of graduation", retrievedEvent.getDescription());
    }

    @Test
    public void testDeletePersonNull() {
        Person person = new Person("John", "Brother", "03/12/1985", "john@email.com", "123-456-7890");
        contactManager.addPerson(person);
        contactManager.deletePerson("John");
        assertEquals(0, contactManager.getAllContacts().size());
        contactManager.deletePerson(null);
        assertEquals(0, contactManager.getAllContacts().size());
        contactManager.deletePerson("Alice");
        assertEquals(0, contactManager.getAllContacts().size());
    }

    @Test
    public void testDeleteEventNull() {
        Event event = new Event("Graduation", "05/20/2023", "John's college graduation");
        contactManager.addEvent(event);
        contactManager.deleteEvent("Graduation");
        assertEquals(0, contactManager.getAllEvents().size());
        contactManager.deleteEvent(null);
        assertEquals(0, contactManager.getAllEvents().size());
        contactManager.deleteEvent("Birthday");
        assertEquals(0, contactManager.getAllEvents().size());
    }

    @Test
    public void testUpdatePersonDetailsWithNullPerson() {
        Person person = null;
        contactManager.updatePersonDetails("John", person);
        assertNull(person);
    }


    @Test
    public void testGetPersonByNameNotFound() {
        Person person1 = new Person("Alice", "Sister", "01/02/2003", "alice@email.com", "123-456-7890");
        contactManager.addPerson(person1);
        Person nullResult = contactManager.getPersonByName("Sam");
        assertNull(nullResult);
    }

    @Test
    public void testAddNullPerson() {
        contactManager.addPerson(null);
        List<Person> contacts = contactManager.getAllContacts();
        assertTrue(contacts.isEmpty());
    }

    @Test
    public void testAddMultiplePersons() {
        Person person1 = new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210");
        Person person2 = new Person("Sam", "Brother", "09/11/1997", "sam@email.com", "123-456-7890");
        contactManager.addPerson(person1);
        contactManager.addPerson(person2);
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(2, contacts.size());
        assertTrue(contacts.contains(person1));
        assertTrue(contacts.contains(person2));
    }

    @Test
    public void testAddNullEvent() {
        contactManager.addEvent(null);
        List<Event> events = contactManager.getAllEvents();
        assertTrue(events.isEmpty());
    }

    @Test
    public void testAddMultipleEvents() {
        Event event1 = new Event("Birthday party", "03/10/2023", "Alice's birthday celebration");
        Event event2 = new Event("Anniversary", "05/15/2023", "Anniversary celebration");
        contactManager.addEvent(event1);
        contactManager.addEvent(event2);
        List<Event> events = contactManager.getAllEvents();
        assertEquals(2, events.size());
        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
    }

    @Test
    public void testUpdateNonExistentEvent() {
        Event updatedEvent = new Event("Anniversary", "05/15/2023", "Anniversary celebration");
        contactManager.updateEvent("Anniversary", updatedEvent);
        List<Event> events = contactManager.getAllEvents();
        assertTrue(events.isEmpty());
    }

    @Test
    public void testUpdateNullEvent() {
        contactManager.updateEvent("Birthday party", null);
        List<Event> events = contactManager.getAllEvents();
        assertTrue(events.isEmpty());
    }

    @Test
    public void testUpdateEventDescription() {
        Event event = new Event("Birthday party", "03/10/2023", "Alice's birthday celebration");
        contactManager.addEvent(event);
        Event updatedEvent = new Event("Birthday party", "03/10/2023", "Updated description of party");
        contactManager.updateEvent("Birthday party", updatedEvent);
        Event gotEvent = contactManager.getEventByName("Birthday party");
        assertEquals("Updated description of party", gotEvent.getDescription());
    }

    @Test
    public void testGetPersonByNameWithNullName() {
        Person nullPerson = contactManager.getPersonByName(null);
        assertNull(nullPerson);
    }

    @Test
    public void testGetEventByName() {
        Event event1 = new Event("Birthday", "2023-01-01", "party time");
        Event event2 = new Event("Graduation", "2023-06-30", "College graduation");
        Event event3 = new Event("Anniversary", "2023-04-15", "Anniversary celebration");
        contactManager.addEvent(event1);
        contactManager.addEvent(event2);
        contactManager.addEvent(event3);
        Event gotEvent1 = contactManager.getEventByName("Birthday");
        assertNotNull(gotEvent1);
        assertEquals("Birthday", gotEvent1.getEventName());
        assertEquals("2023-01-01", gotEvent1.getEventDate());
        assertEquals("party time", gotEvent1.getDescription());
        Event gotEvent2 = contactManager.getEventByName("Anniversary");
        assertNotNull(gotEvent2);
        assertEquals("Anniversary", gotEvent2.getEventName());
        assertEquals("2023-04-15", gotEvent2.getEventDate());
        assertEquals("Anniversary celebration", gotEvent2.getDescription());
    }

    @Test
    public void testGetEventByNameNotFound() {
        Event nullEvent1 = contactManager.getEventByName("Anniversary");
        assertNull(nullEvent1);
    }

    @Test
    public void testGetEventByNameWithNullName() {
        Event nullEvent2 = contactManager.getEventByName(null);
        assertNull(nullEvent2);
    }

    @Test
    public void testGetPersonByName() {
        Person person = new Person("Alice", "Sister", "03/04/2005", "alice@email.com", "123-456-7890");
        contactManager.addPerson(person);
        Person gotPerson = contactManager.getPersonByName("Alice");
        assertNotNull(gotPerson);
        assertEquals("Alice", gotPerson.getName());
        assertEquals("Sister", gotPerson.getRelationship());
        assertEquals("03/04/2005", gotPerson.getBirthday());
        assertEquals("alice@email.com", gotPerson.getEmail());
        assertEquals("123-456-7890", gotPerson.getPhoneNumber());
    }
}






