package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
    }

    @Test
    public void testGetters() {
        assertEquals("John", person.getName());
        assertEquals("Brother", person.getRelationship());
        assertEquals("01/15/1980", person.getBirthday());
        assertEquals("john@email.com", person.getEmail());
        assertEquals("123-456-7890", person.getPhoneNumber());
    }

    @Test
    public void testSetters() {
        person.setName("Alice");
        person.setRelationship("Sister");
        person.setBirthday("02/20/1985");
        person.setEmail("alice@email.com");
        person.setPhoneNumber("987-654-3210");

        assertEquals("Alice", person.getName());
        assertEquals("Sister", person.getRelationship());
        assertEquals("02/20/1985", person.getBirthday());
        assertEquals("alice@email.com", person.getEmail());
        assertEquals("987-654-3210", person.getPhoneNumber());
    }

    @Test
    public void testNewConstructor() {
        Person personWithRelation = new Person("Alice", "Sister", "02/20/1985");
        assertEquals("Alice", personWithRelation.getName());
        assertEquals("Sister", personWithRelation.getRelationship());
        assertEquals("02/20/1985", personWithRelation.getBirthday());
        assertNull(personWithRelation.getEmail()); // The email and phone number should be null
        assertNull(personWithRelation.getPhoneNumber());
    }
}
