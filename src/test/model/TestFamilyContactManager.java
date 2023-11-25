package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Test class to test methods from the FamilyContactManager class
public class TestFamilyContactManager {
    private FamilyContactManager contactManager;
    private LocalDate eventDate = LocalDate.of(2004, 7, 10);

    @BeforeEach
    public void runBefore() {
        contactManager = new FamilyContactManager();
    }

    @Test
    public void testAddPerson() {
        Person person = new Person("Alice", "Sister", eventDate, "alice@email.com", 123456789);
        contactManager.addPerson(person);
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(1, contacts.size());
        assertEquals("Alice", contacts.get(0).getName());
    }

    @Test
    public void testDeletePerson() {
        Person person = new Person("Alice", "Sister", eventDate, "alice@email.com", 123457890);
        contactManager.addPerson(person);
        contactManager.deletePerson("Alice");
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(0, contacts.size());
    }

    @Test
    public void testUpdatePersonDetails() {
        Person person = new Person("John", "Brother", eventDate, "john@email.com", 123456780);
        contactManager.addPerson(person);
        Person updatedPerson = new Person("John", "Brother", eventDate, "updatedPerson@email.com", 87643210);
        contactManager.updatePersonDetails("John", updatedPerson);
        Person retrievedPerson = contactManager.getPersonByName("John");
        assertEquals("updatedPerson@email.com", retrievedPerson.getEmailID());
    }

    @Test
    public void testDeletePersonNull() {
        Person person = new Person("John", "Brother", eventDate, "john@email.com", 123456789);
        contactManager.addPerson(person);
        contactManager.deletePerson("John");
        assertEquals(0, contactManager.getAllContacts().size());
        contactManager.deletePerson(null);
        assertEquals(0, contactManager.getAllContacts().size());
        contactManager.deletePerson("Alice");
        assertEquals(0, contactManager.getAllContacts().size());
    }

    @Test
    public void testGetPersonByNameNotFound() {
        Person person1 = new Person("Alice", "Sister", eventDate, "alice@email.com", 123456780);
        contactManager.addPerson(person1);
        Person nullResult = contactManager.getPersonByName("Sam");
        assertNull(nullResult);
    }

    @Test
    public void testAddNullPerson() {
        contactManager.addPerson(null);
        List<Person> contacts = contactManager.getAllContacts();
        assertTrue(contacts.isEmpty());
    }

    @Test
    public void testAddMultiplePersons() {
        Person person1 = new Person("Alice", "Sister", eventDate, "alice@email.com", 986543210);
        Person person2 = new Person("Sam", "Brother", eventDate, "sam@email.com", 1234567890);
        contactManager.addPerson(person1);
        contactManager.addPerson(person2);
        List<Person> contacts = contactManager.getAllContacts();
        assertEquals(2, contacts.size());
        assertTrue(contacts.contains(person1));
        assertTrue(contacts.contains(person2));
    }

    @Test
    public void testGetPersonByNameWithNullName() {
        Person nullPerson = contactManager.getPersonByName(null);
        assertNull(nullPerson);
    }

    @Test
    public void testGetPersonByName() {
        Person person = new Person("Alice", "Sister", eventDate, "alice@email.com", 234567890);
        contactManager.addPerson(person);
        Person gotPerson = contactManager.getPersonByName("Alice");
        assertNotNull(gotPerson);
        assertEquals("Alice", gotPerson.getName());
        assertEquals("Sister", gotPerson.getRelationship());
        assertEquals(eventDate, gotPerson.getBirthdate());
        assertEquals("alice@email.com", gotPerson.getEmailID());
        assertEquals(234567890, gotPerson.getPhoneNumber());
    }

    @Test
    public void testFamilyContactManagerConstructor1() {
        FamilyContactManager familyContactManager = new FamilyContactManager();
        assertNotNull(familyContactManager.getAllContacts());
        assertTrue(familyContactManager.getAllContacts().isEmpty());
    }

    @Test
    public void testFamilyContactManagerConstructor2() {
        List<Person> personList = new ArrayList<>();
        FamilyContactManager familyContactManager = new FamilyContactManager(personList);
        assertNotNull(familyContactManager.getAllContacts());
        assertEquals(personList, familyContactManager.getAllContacts());
    }
}






