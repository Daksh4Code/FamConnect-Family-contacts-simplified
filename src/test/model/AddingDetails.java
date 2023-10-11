package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddingDetails {
    private Database database;

    @BeforeEach
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAddContactWithDetails() {
        FamilyContact contact = new FamilyContact("Jane Smith", "Sister", "1985-03-15", "jane@example.com", "987-654-3210");
        contact.addCustomEvent("Graduation 2022");
        database.addContact(contact);

        FamilyContact retrievedContact = database.getAllContacts().get(0);

        assertEquals("Jane Smith", retrievedContact.getName());
        assertEquals("Sister", retrievedContact.getRelationship());
        assertEquals("1985-03-15", retrievedContact.getBirthdate());
        assertEquals("jane@example.com", retrievedContact.getEmail());
        assertEquals("987-654-3210", retrievedContact.getPhoneNumber());
        assertEquals(1, retrievedContact.getCustomEvents().size());
        assertEquals("Graduation 2022", retrievedContact.getCustomEvents().get(0));
    }
}
