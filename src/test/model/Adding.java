package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class Adding {
    private Database database;

    @BeforeEach
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAddContact() {
        FamilyContact contact = new FamilyContact("John Doe", "Cousin", "1990-05-20", "john@example.com", "123-456-7890");
        database.addContact(contact);
        assertEquals(1, database.getAllContacts().size());
    }
}
