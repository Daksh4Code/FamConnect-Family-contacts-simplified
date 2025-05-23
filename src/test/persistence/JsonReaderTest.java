package persistence;

import model.FamilyContactManager;
import model.Person;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// Test class to test methods from the JsonReader class
class JsonReaderTest extends JsonTest {
    private static final String TEST_FILE = "./data/contacts.json"; // change file path name as required
                                                                    // while running tests on your local machine

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
            Person person = manager.getAllContacts().get(0);
            assertEquals(0, person.getEvents().size());
        } catch (IOException e) {
            fail("Error! Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFamilyContactManager() {
        JsonReader jsonReader = new JsonReader(TEST_FILE);
        try {
            FamilyContactManager manager = jsonReader.read();
            assertEquals(1, manager.getAllContacts().size());
        } catch (IOException e) {
            fail("Error! Couldn't read from file");
        }
    }
}
