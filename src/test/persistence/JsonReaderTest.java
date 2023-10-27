package persistence;

import model.*;

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

            Event event = new Event("Test Event", "2023-12-31", "Test description");
            manager.addEvent(event);

            // Get the list of events from the FamilyContactManager
            List<Event> events = manager.getAllEvents();

            assertEquals(1, events.size());

            // Check the details of the added event
            Event addedEvent = events.get(0);
            assertEquals("Test Event", addedEvent.getEventName());
            assertEquals("2023-12-31", addedEvent.getEventDate());
            assertEquals("Test description", addedEvent.getEventDescription());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
