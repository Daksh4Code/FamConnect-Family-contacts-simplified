package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {
    private Event event;

    @BeforeEach
    public void runBefore() {
        event = new Event("Graduation", "08/10/2026", "Lily's college graduation");
    }

    @Test
    public void testGetters() {
        assertEquals("Graduation", event.getEventName());
        assertEquals("08/10/2026", event.getEventDate());
        assertEquals("Lily's college graduation", event.getEventDescription());
    }

    @Test
    public void testSetters() {
        event.setEventName("Birthday party");
        event.setEventDate("02/20/2023");
        event.setEventDescription("Grace's birthday party");
        assertEquals("Birthday party", event.getEventName());
        assertEquals("02/20/2023", event.getEventDate());
        assertEquals("Grace's birthday party", event.getEventDescription());
    }

    @Test
    public void testToString() {
        Event event = new Event("Bachelor party", "13/10/2023", "Alice's bachelor party celebration");
        String expected = "Event{Event Name='Bachelor party', Event Date='13/10/2023', Event Description='Alice's bachelor party celebration'}";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventEquals() {
        Event sameEvent = new Event("Graduation", "05/11/2023", "John's college graduation");
        Event differentEvent = new Event("Birthday party", "24/08/2023", "Mary's birthday party");
        assertFalse(event.equals(sameEvent));
        assertFalse(event.equals(differentEvent));
    }
}
