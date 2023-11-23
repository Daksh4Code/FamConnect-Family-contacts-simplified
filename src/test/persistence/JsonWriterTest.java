package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    private static final String TEST_FILE = "./data/testContacts.json";
    LocalDate eventDate = LocalDate.of(2004, 7, 10);
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

            // Ensure the list of persons is empty before attempting to get a person
            if (!manager.getAllContacts().isEmpty()) {
                Person person = manager.getAllContacts().get(0);
                assertEquals(0, person.getEvents().size());
            }

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralFamilyContactManager() {
        try {
            FamilyContactManager manager = new FamilyContactManager();
            Person person = new Person("John", "Family", this.eventDate, "john@example.com", 1234567898);
            person.addEvent(new Event("Birthday", this.eventDate, "John's birthday"));
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
            assertEquals(this.eventDate, readPerson.getBirthdate());
            assertEquals("john@example.com", readPerson.getEmailID());
            assertEquals(1234567898, readPerson.getPhoneNumber());
            List<Event> events = readPerson.getEvents();
            assertEquals(1, events.size());
            Event readEvent = events.get(0);
            assertEquals("Birthday", readEvent.getEventName());
            assertEquals(this.eventDate, readEvent.getEventDate());
            assertEquals("John's birthday", readEvent.getEventDescription());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
