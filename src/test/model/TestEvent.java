package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {

    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("Graduation", "08/10/2026", "Lily's college graduation");
    }

    @Test
    public void testGetters() {
        assertEquals("Graduation", event.getEventName());
        assertEquals("08/10/2026", event.getEventDate());
        assertEquals("Lily's college graduation", event.getDescription());
    }

    @Test
    public void testSetters() {
        event.setEventName("Birthday Party");
        event.setEventDate("02/20/2023");
        event.setDescription("Grace's birthday celebration");
        assertEquals("Birthday Party", event.getEventName());
        assertEquals("02/20/2023", event.getEventDate());
        assertEquals("Grace's birthday celebration", event.getDescription());
    }

    @Test
    public void testToString() {
        Event event = new Event("Bachelor Party", "13/10/2023", "Alice's bachelor party celebration");
        String expected = "Event{Event Name='Bachelor Party', Event Date='13/10/2023', Event Description='Alice's bachelor party celebration'}";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventEquals() {
        Event sameEvent = new Event("Graduation", "05/11/2023", "John's college graduation");
        Event differentEvent = new Event("Birthday Party", "24/08/2023", "Mary's birthday celebration");
        assertFalse(event.equals(sameEvent));
        assertFalse(event.equals(differentEvent));
    }
}
