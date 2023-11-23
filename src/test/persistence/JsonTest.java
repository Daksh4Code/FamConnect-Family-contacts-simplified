package persistence;

import model.FamilyContactManager;
import model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFamilyContactManager(int expectedContacts, int expectedEvents, FamilyContactManager manager, Person person) {
        assertEquals(expectedContacts, manager.getAllContacts().size());
        assertEquals(expectedEvents, person.getEvents().size());
    }
}