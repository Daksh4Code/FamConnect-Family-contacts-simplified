package persistence;

import model.Event;
import model.FamilyContactManager;
import org.json.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
    private static final String TEST_FILE = "./data/contacts.json";

    @Test
    void testReaderNonExistentFile() {
        JsonReader jsonReader = new JsonReader("./data/nonExistentFile.json");
        try {
            FamilyContactManager manager = jsonReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Pass
        }
    }

    @Test
    void testReaderEmptyFamilyContactManager() {
        JsonReader jsonReader = new JsonReader(TEST_FILE);
        try {
            FamilyContactManager manager = jsonReader.read();
            assertEquals(1, manager.getAllContacts().size());
            assertEquals(0, manager.getAllEvents().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFamilyContactManager() {
        JsonReader jsonReader = new JsonReader(TEST_FILE);
        try {
            FamilyContactManager manager = jsonReader.read();
            assertEquals(1, manager.getAllContacts().size());
            assertEquals(0, manager.getAllEvents().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testAddEvents() {
        JsonReader jsonReader = new JsonReader(TEST_FILE);
        try {
            FamilyContactManager manager = jsonReader.read();

            // Create a JSON array representing a list of events
            JSONArray eventsArray = new JSONArray();
            JSONObject event1 = new JSONObject();
            event1.put("eventName", "Event 1");
            event1.put("eventDate", "2023-12-31");
            event1.put("eventDescription", "Description 1");
            eventsArray.put(event1);

            JSONObject event2 = new JSONObject();
            event2.put("eventName", "Event 2");
            event2.put("eventDate", "2023-11-15");
            event2.put("eventDescription", "Description 2");
            eventsArray.put(event2);

            // Call the addEvents method to parse and add events
            jsonReader.addEvents(manager, eventsArray);

            // Get the list of events from the FamilyContactManager
            List<Event> events = manager.getAllEvents();

            // Check that the correct number of events was added
            assertEquals(2, events.size());

            // Check the details of the added events
            Event addedEvent1 = events.get(0);
            assertEquals("Event 1", addedEvent1.getEventName());
            assertEquals("2023-12-31", addedEvent1.getEventDate());
            assertEquals("Description 1", addedEvent1.getEventDescription());

            Event addedEvent2 = events.get(1);
            assertEquals("Event 2", addedEvent2.getEventName());
            assertEquals("2023-11-15", addedEvent2.getEventDate());
            assertEquals("Description 2", addedEvent2.getEventDescription());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

}
