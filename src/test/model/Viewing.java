package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Viewing {
    private Database database;

    @BeforeEach
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testViewAllContacts() {
        FamilyContact contact1 = new FamilyContact("Alice Johnson", "Aunt", "1970-01-10", "alice@example.com", "111-111-1111");
        FamilyContact contact2 = new FamilyContact("Bob Smith", "Uncle", "1965-07-05", "bob@example.com", "222-222-2222");

        database.addContact(contact1);
        database.addContact(contact2);

        List<FamilyContact> contacts = database.getAllContacts();

        assertEquals(2, contacts.size());
        assertEquals("Alice Johnson", contacts.get(0).getName());
        assertEquals("Bob Smith", contacts.get(1).getName());
    }
}

