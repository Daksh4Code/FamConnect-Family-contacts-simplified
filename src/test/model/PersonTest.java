package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

// Test class to test methods from the Person class
public class PersonTest {
    private Person person;
    private LocalDate eventDate = LocalDate.of(2004, 7, 10);

    @BeforeEach
    public void runBefore() {
        person = new Person("John", "Brother", eventDate, "john@email.com", 1234567890);
    }

    @Test
    public void testGetters() {
        assertEquals("John", person.getName());
        assertEquals("Brother", person.getRelationship());
        assertEquals(LocalDate.parse("2004-07-10"), person.getBirthdate());
        assertEquals("john@email.com", person.getEmailID());
        assertEquals(1234567890, person.getPhoneNumber());
    }

    @Test
    public void testSetters() {
        person.setName("Alice");
        person.setRelationship("Sister");
        person.setBirthdate(this.eventDate);
        person.setEmailID("alice@email.com");
        person.setPhoneNumber(987654320);
        assertEquals("Alice", person.getName());
        assertEquals("Sister", person.getRelationship());
        assertEquals("alice@email.com", person.getEmailID());
        assertEquals(987654320, person.getPhoneNumber());
    }

    @Test
    public void testNewConstructor() {
        Person newPerson = new Person("Alice", "Sister", eventDate);
        assertEquals("Alice", newPerson.getName());
        assertEquals("Sister", newPerson.getRelationship());
        assertEquals(eventDate, newPerson.getBirthdate());
        assertNull(newPerson.getEmailID());
    }

    @Test
    public void testAddEvent2() {
        Event event1 = new Event("Graduation", eventDate, "John's college graduation");
        person.addEvent(event1);
        assertEquals(1, person.getEvents().size());
        Event event2 = new Event("Anniversary", eventDate, "John and Emily's anniversary");
        person.addEvent(event2);
        assertEquals(2, person.getEvents().size());
    }

    @Test
    public void testGetEventByName() {
        Event event1 = new Event("Graduation", eventDate, "John's college graduation");
        Event event2 = new Event("Anniversary", eventDate, "John and Emily's anniversary");
        person.addEvent(event1);
        person.addEvent(event2);
        Event gotEvent = person.getEventByName("Graduation");
        assertNotNull(gotEvent);
        assertEquals("Graduation", gotEvent.getEventName());
        Event nonExistentEvent = person.getEventByName("Birthday");
        assertNull(nonExistentEvent);
    }

    @Test
    public void testToStringWithNoCustomEvents() {
        String expected = "Person{name = 'John', relationship = 'Brother', birthday = '2004-07-10', email = 'john@email.com', phoneNumber = '1234567890}";
        assertEquals(expected, person.toString());
    }

    @Test
    public void testToStringWithCustomEvents() {
        Event event1 = new Event("Graduation", eventDate, "John's college graduation");
        Event event2 = new Event("Anniversary", eventDate, "John and Emily's anniversary");
        person.addEvent(event1);
        person.addEvent(event2);
        String expected = "Person{name = 'John', relationship = 'Brother', birthday = '2004-07-10', email = 'john@email.com', phoneNumber = '1234567890, Associated Events = [Graduation, Anniversary]}";
        assertEquals(expected, person.toString());
    }

    @Test
    public void testGetCustomEvents() {
        Event event1 = new Event("Graduation", eventDate, "John's college graduation");
        Event event2 = new Event("Anniversary", eventDate, "John and Emily's anniversary");
        person.addEvent(event1);
        person.addEvent(event2);
        List<Event> customEvents = person.getEvents();
        assertTrue(customEvents.contains(event1));
        assertTrue(customEvents.contains(event2));
        assertEquals(2, customEvents.size());
    }

    @Test
    public void testAddEvent() {
        Event event = new Event("Anniversary", eventDate, "John's college graduation");
        person.addEvent(event);
        List<Event> events = person.getEvents();
        assertEquals(1, events.size());
        assertEquals("Anniversary", events.get(0).getEventName());
    }

    @Test
    public void testUpdateEvent() {
        Event event = new Event("Graduation", eventDate, "Mark's college graduation");
        person.addEvent(event);
        Event updatedEvent = new Event("Graduation", eventDate, "Updated description of graduation");
        person.updateEvent("Graduation", updatedEvent);
        Event retrievedEvent = person.getEventByName("Graduation");
        assertEquals("Updated description of graduation", retrievedEvent.getEventDescription());
    }

    @Test
    public void testAddNullEvent() {
        person.addEvent(null);
        List<Event> events = person.getEvents();
        assertTrue(events.isEmpty());
    }

    @Test
    public void testAddMultipleEvents() {
        Event event1 = new Event("Birthday party", eventDate, "Alice's birthday celebration");
        Event event2 = new Event("Anniversary", eventDate, "Anniversary celebration");
        person.addEvent(event1);
        person.addEvent(event2);
        List<Event> events = person.getEvents();
        assertEquals(2, events.size());
        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
    }

    @Test
    public void testUpdateNonExistentEvent() {
        Event updatedEvent = new Event("Anniversary", eventDate, "Anniversary celebration");
        person.updateEvent("Anniversary", updatedEvent);
        List<Event> events = person.getEvents();
        assertTrue(events.isEmpty());
    }

    @Test
    public void testUpdateNullEvent() {
        person.updateEvent("Birthday party", null);
        List<Event> events = person.getEvents();
        assertTrue(events.isEmpty());
    }

    @Test
    public void testUpdateEventDescription() {
        Event event = new Event("Birthday party", eventDate, "Alice's birthday celebration");
        person.addEvent(event);
        Event updatedEvent = new Event("Birthday party", eventDate, "Updated description of party");
        person.updateEvent("Birthday party", updatedEvent);
        Event gotEvent = person.getEventByName("Birthday party");
        assertEquals("Updated description of party", gotEvent.getEventDescription());
    }

    @Test
    public void testGetEventByName2() {
        Event event1 = new Event("Birthday", eventDate, "party time");
        Event event2 = new Event("Graduation", eventDate, "College graduation");
        Event event3 = new Event("Anniversary", eventDate, "Anniversary celebration");
        person.addEvent(event1);
        person.addEvent(event2);
        person.addEvent(event3);
        Event gotEvent1 = person.getEventByName("Birthday");
        assertNotNull(gotEvent1);
        assertEquals("Birthday", gotEvent1.getEventName());
        assertEquals(eventDate, gotEvent1.getEventDate());
        assertEquals("party time", gotEvent1.getEventDescription());
        Event gotEvent2 = person.getEventByName("Anniversary");
        assertNotNull(gotEvent2);
        assertEquals("Anniversary", gotEvent2.getEventName());
        assertEquals(eventDate, gotEvent2.getEventDate());
        assertEquals("Anniversary celebration", gotEvent2.getEventDescription());
    }

    @Test
    public void testGetEventByNameNotFound() {
        Event nullEvent1 = person.getEventByName("Anniversary");
        assertNull(nullEvent1);
    }

    @Test
    public void testGetEventByNameWithNullName() {
        Event nullEvent2 = person.getEventByName(null);
        assertNull(nullEvent2);
    }

    @Test
    public void testDeleteEventNull() {
        Event event = new Event("Graduation", eventDate, "John's college graduation");
        person.addEvent(event);
        person.deleteEvent("Graduation");
        assertEquals(0, person.getEvents().size());
        person.deleteEvent(null);
        assertEquals(0, person.getEvents().size());
        person.deleteEvent("Birthday");
        assertEquals(0, person.getEvents().size());
    }

    @Test
    public void testPersonConstructor() {
        Person person = new Person();
        assertNotNull(person.getEvents());
        assertTrue(person.getEvents().isEmpty());
    }
}
