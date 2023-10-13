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

        assertEquals("Birthday Party", event.getEventName());
        assertEquals("03/10/2023", event.getEventDate());
        assertEquals("Alice's birthday celebration", event.getDescription());
    }



    @Test
    public void testToString() {
        Event event = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");
        String expected = "Event{Event Name='Birthday Party', Event Date='03/10/2023', Event Description='Alice's birthday celebration'}";
        assertEquals(expected, event.toString());
    }



    @Test
    public void testEventEquals() {
        Event sameEvent = new Event("Graduation", "05/20/2023", "John's college graduation");
        Event differentEvent = new Event("Birthday Party", "03/10/2023", "Alice's birthday celebration");

        assertFalse(event.equals(sameEvent)); // Event equals another event with the same properties
        assertFalse(event.equals(differentEvent)); // Event does not equal an event with different properties
    }

    @Test
    public void testNullEquality() {
        assertFalse(event.equals(null)); // Event does not equal null
    }

    @Test
    public void testDifferentObjectEquality() {
        assertFalse(event.equals(new Object())); // Event does not equal a different object
    }

    @Test
    public void testSameObjectEquality() {
        Event sameObject = event;
        assertTrue(event.equals(sameObject)); // Event equals the same object
    }
}
