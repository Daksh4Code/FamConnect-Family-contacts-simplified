package persistence;

import model.FamilyContactManager;
import model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unused test class
public class JsonTest {
    // Not in use for this development phase of the project
    protected void checkFamilyContactManager(int expectedContacts, int expectedEvents, FamilyContactManager manager, Person person) {
        assertEquals(expectedContacts, manager.getAllContacts().size());
        assertEquals(expectedEvents, person.getEvents().size());
    }
}