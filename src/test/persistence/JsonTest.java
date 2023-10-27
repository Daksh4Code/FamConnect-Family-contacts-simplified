package persistence;

import model.FamilyContactManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFamilyContactManager(int expectedContacts, int expectedEvents, FamilyContactManager manager) {
        assertEquals(expectedContacts, manager.getAllContacts().size());
        assertEquals(expectedEvents, manager.getAllEvents().size());
    }
}