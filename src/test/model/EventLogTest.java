package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the EventLog class
 */
public class EventLogTest {
    private EventAlarmSystem e1;
    private EventAlarmSystem e2;
    private EventAlarmSystem e3;

    @BeforeEach
    public void loadEvents() {
        e1 = new EventAlarmSystem("A1");
        e2 = new EventAlarmSystem("A2");
        e3 = new EventAlarmSystem("A3");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);
    }

    @Test
    public void testLogEvent() {
        List<EventAlarmSystem> l = new ArrayList<EventAlarmSystem>();
        EventLog el = EventLog.getInstance();
        for (EventAlarmSystem next : el) {
            l.add(next);
        }

        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
    }

    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<EventAlarmSystem> itr = el.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}