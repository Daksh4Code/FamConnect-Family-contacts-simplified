package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestPerson {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
    }

    @Test
    public void testGetters() {
        assertEquals("John", person.getName());
        assertEquals("Brother", person.getRelationship());
        assertEquals("01/15/1980", person.getBirthday());
        assertEquals("john@email.com", person.getEmail());
        assertEquals("123-456-7890", person.getPhoneNumber());
    }

    @Test
    public void testSetters() {
        person.setName("Alice");
        person.setRelationship("Sister");
        person.setBirthday("02/30/1985");
        person.setEmail("alice@email.com");
        person.setPhoneNumber("987-654-3210");
        assertEquals("Alice", person.getName());
        assertEquals("Sister", person.getRelationship());
        assertEquals("02/30/1985", person.getBirthday());
        assertEquals("alice@email.com", person.getEmail());
        assertEquals("987-654-3210", person.getPhoneNumber());
    }

    @Test
    public void testNewConstructor() {
        Person newPerson = new Person("Alice", "Sister", "12/22/1985");
        assertEquals("Alice", newPerson.getName());
        assertEquals("Sister", newPerson.getRelationship());
        assertEquals("12/22/1985", newPerson.getBirthday());
        assertNull(newPerson.getEmail());
        assertNull(newPerson.getPhoneNumber());
    }

    @Test
    public void testAddCustomEvent() {
        Event event1 = new Event("Graduation", "15/28/2023", "John's college graduation");
        person.addCustomEvent(event1);
        assertEquals(1, person.getCustomEvents().size());
        Event event2 = new Event("Anniversary", "06/10/2023", "John and Emily's anniversary");
        person.addCustomEvent(event2);
        assertEquals(2, person.getCustomEvents().size());
    }

    @Test
    public void testGetCustomEventByName() {
        Event event1 = new Event("Graduation", "05/20/2023", "John's college graduation");
        Event event2 = new Event("Anniversary", "06/10/2023", "John and Emily's anniversary");
        person.addCustomEvent(event1);
        person.addCustomEvent(event2);
        Event gotEvent = person.getCustomEventByName("Graduation");
        assertNotNull(gotEvent);
        assertEquals("Graduation", gotEvent.getEventName());
        Event nonExistentEvent = person.getCustomEventByName("Birthday");
        assertNull(nonExistentEvent);
    }

    @Test
    public void testToStringWithNoCustomEvents() {
        String expected = "Person{Name='John', Relationship='Brother', Birthday='01/15/1980', Email='john@email.com', Phone Number='123-456-7890}";
        assertEquals(expected, person.toString());
    }

    @Test
    public void testToStringWithCustomEvents() {
        Event event1 = new Event("Graduation", "05/20/2023", "John's college graduation");
        Event event2 = new Event("Anniversary", "06/10/2023", "John and Emily's anniversary");
        person.addCustomEvent(event1);
        person.addCustomEvent(event2);
        String expected = "Person{Name='John', Relationship='Brother', Birthday='01/15/1980', Email='john@email.com', Phone Number='123-456-7890, Custom Events=[Graduation, Anniversary]}";
        assertEquals(expected, person.toString());
    }

    @Test
    public void testGetCustomEvents() {
        Event event1 = new Event("Graduation", "05/20/2023", "John's college graduation");
        Event event2 = new Event("Anniversary", "06/10/2023", "John and Emily's anniversary");
        person.addCustomEvent(event1);
        person.addCustomEvent(event2);
        List<Event> customEvents = person.getCustomEvents();
        assertTrue(customEvents.contains(event1));
        assertTrue(customEvents.contains(event2));
        assertEquals(2, customEvents.size());
    }
}
