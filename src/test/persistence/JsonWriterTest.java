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
            // You can add more specific checks here based on your JSON file's content
            assertEquals(0, manager.getAllContacts().size());
            assertEquals(0, manager.getAllEvents().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralFamilyContactManager() {
        try {
            // Create a FamilyContactManager with test data
            FamilyContactManager manager = new FamilyContactManager();
            Person person = new Person("John", "Family", "1990-01-15", "john@example.com", "123-456-7890");
            person.addCustomEvent(new Event("Birthday", "1990-01-15", "John's birthday"));
            manager.addPerson(person);

            // Write the manager to a file
            JsonWriter jsonWriter = new JsonWriter(TEST_FILE);
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();

            // Read the manager from the file
            JsonReader jsonReader = new JsonReader(TEST_FILE);
            FamilyContactManager readManager = jsonReader.read();

            // Assert that the read data matches the original data
            List<Person> people = readManager.getAllContacts();
            assertEquals(1, people.size()); // Adjust this value based on your test data
            Person readPerson = people.get(0);
            assertEquals("John", readPerson.getName());
            assertEquals("Family", readPerson.getRelationship());
            assertEquals("1990-01-15", readPerson.getBirthday());
            assertEquals("john@example.com", readPerson.getEmail());
            assertEquals("123-456-7890", readPerson.getPhoneNumber());

            List<Event> events = readPerson.getCustomEvents();
            assertEquals(1, events.size()); // Adjust this value based on your test data
            Event readEvent = events.get(0);
            assertEquals("Birthday", readEvent.getEventName());
            assertEquals("1990-01-15", readEvent.getEventDate());
            assertEquals("John's birthday", readEvent.getEventDescription());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
