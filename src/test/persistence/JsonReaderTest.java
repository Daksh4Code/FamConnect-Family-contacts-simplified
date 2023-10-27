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



}
