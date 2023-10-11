package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Update {
    private Database database;

    @BeforeEach
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testUpdateContactInformation() {
        FamilyContact contact = new FamilyContact("David Brown", "Brother", "1995-12-20", "david@example.com", "333-333-3333");
        database.addContact(contact);

        database.updateContact("David Brown", "david_new@example.com", "444-444-4444");

        FamilyContact updatedContact = database.getAllContacts().get(0);

        assertEquals("david_new@example.com", updatedContact.getEmail());
        assertEquals("444-444-4444", updatedContact.getPhoneNumber());
    }
}
