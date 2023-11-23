package persistence;

import model.FamilyContactManager;

import model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
    private static final String TEST_FILE = "./data/contacts.json";
    LocalDate eventDate = LocalDate.of(2004, 7, 10);

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
            assertEquals(3, manager.getAllContacts().size());
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
            assertEquals(3, manager.getAllContacts().size());
            Person person = manager.getAllContacts().get(0);
            assertEquals(0, person.getEvents().size());
        } catch (IOException e) {
            fail("Error! Couldn't read from file");
        }
    }
}
