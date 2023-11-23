package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvent {
    private Event event;
    LocalDate eventDate = LocalDate.of(2004, 7, 10);

    @BeforeEach
    public void runBefore() {
        event = new Event("Graduation", LocalDate.parse("2004-07-10"), "Lily's college graduation");
    }

    @Test
    public void testGetters() {
        assertEquals("Graduation", event.getEventName());
        assertEquals(eventDate, event.getEventDate());
        assertEquals("Lily's college graduation", event.getEventDescription());
    }

    @Test
    public void testSetters() {
        event.setEventName("Birthday party");
        event.setEventDate(LocalDate.parse("2004-07-10"));
        event.setEventDescription("Grace's birthday party");
        assertEquals("Birthday party", event.getEventName());
        assertEquals(eventDate, event.getEventDate());
        assertEquals("Grace's birthday party", event.getEventDescription());
    }

    @Test
    public void testToString() {
        Event event = new Event("Bachelor party", eventDate, "Alice's bachelor party celebration");
        String expected = "Event{eventName = 'Bachelor party', eventDate = '2004-07-10', eventDescription = 'Alice's bachelor party celebration'}";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventEquals() {
        Event sameEvent = new Event("Graduation", LocalDate.parse("2004-07-10"), "John's college graduation");
        Event differentEvent = new Event("Birthday party", LocalDate.parse("2005-07-10"), "Mary's birthday party");
        assertFalse(event.equals(sameEvent));
        assertFalse(event.equals(differentEvent));
    }
}
