package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    private static final String TEST_FILE = "./data/testContacts.json";

    @Test
    void testWriterInvalidFile() {
        try {
            FamilyContactManager manager = new FamilyContactManager();
            JsonWriter jsonWriter = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            fail("IOException was expected");
        } catch (IOException e) {
            // Pass
        }
    }

    @Test
    void testWriterEmptyFamilyContactManager() {
        try {
            FamilyContactManager manager = new FamilyContactManager();
            JsonWriter jsonWriter = new JsonWriter(TEST_FILE);
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader(TEST_FILE);
            manager = jsonReader.read();
            assertEquals(0, manager.getAllContacts().size());
            assertEquals(0, manager.getAllEvents().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFamilyContactManager() {
        try {
            FamilyContactManager manager = new FamilyContactManager();
            Person person = new Person("John", "Family", "1986-10-12", "john@example.com", "1234567898");
            person.addCustomEvent(new Event("Birthday", "2009-02-25", "John's birthday"));
            manager.addPerson(person);
            JsonWriter jsonWriter = new JsonWriter(TEST_FILE);
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader(TEST_FILE);
            FamilyContactManager readManager = jsonReader.read();
            List<Person> people = readManager.getAllContacts();
            assertEquals(1, people.size());
            Person readPerson = people.get(0);
            assertEquals("John", readPerson.getName());
            assertEquals("Family", readPerson.getRelationship());
            assertEquals("1986-10-12", readPerson.getBirthdate());
            assertEquals("john@example.com", readPerson.getEmail());
            assertEquals("1234567898", readPerson.getPhoneNumber());
            List<Event> events = readPerson.getCustomEvents();
            assertEquals(1, events.size());
            Event readEvent = events.get(0);
            assertEquals("Birthday", readEvent.getEventName());
            assertEquals("2009-02-25", readEvent.getEventDate());
            assertEquals("John's birthday", readEvent.getEventDescription());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
