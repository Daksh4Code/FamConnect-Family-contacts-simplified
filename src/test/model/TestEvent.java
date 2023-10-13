package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {

    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("Graduation", "05/20/2023", "John's college graduation");
    }

    @Test
    public void testGetters() {
        assertEquals("Graduation", event.getEventName());
        assertEquals("05/20/2023", event.getEventDate());
        assertEquals("John's college graduation", event.getDescription());
    }

    @Test
    public void testSetters() {
        event.setEventName("Birthday Party");
        event.setEventDate("03/10/2023");
        event.setDescription("Alice's birthday celebration");

        assertEquals("Birthday Party", event.getEventDate());
        assertEquals("03/10/2023", event.getEventDate());
        assertEquals("Alice's birthday celebration", event.getDescription());
    }

    @Test
    public void testToString() {
        String expected = "Event{name='Graduation', date='05/20/2023', description='John's college graduation'}";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventEquals() {
        Event sameEvent = new Event("Graduation", "05/20/2023", "John's college graduation");
        Event differentEvent = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");
        Event nullEvent = null;


        assertTrue(event.equals(sameEvent)); // Event equals another event with the same properties
        assertFalse(event.equals(differentEvent)); // Event does not equal an event with different properties
        assertFalse(event.equals(nullEvent)); // Event does not equal null
        assertFalse(event.equals(new Object())); // Event does not equal a different object

        // Test with the same object
        Event sameObject = event;
        assertTrue(event.equals(sameObject));
    }
}
