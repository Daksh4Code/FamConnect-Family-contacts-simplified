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
    public void testToString() {
        String expected = "Person{name='John', relation='Brother', birthdate='01/15/1980', email='john@email.com', phoneNumber='123-456-7890'}";
        assertEquals(expected, person.toString());
    }

    @Test
    public void testPersonEquals() {
        Person samePerson = new Person("John", "Brother", "01/15/1980", "john@email.com", "123-456-7890");
        Person differentPerson = new Person("Alice", "Sister", "02/20/1985", "alice@email.com", "987-654-3210");
        Person nullPerson = null;

        assertTrue(person.equals(person)); // Person equals itself
        assertTrue(person.equals(samePerson)); // Person equals another person with the same properties
        assertFalse(person.equals(differentPerson)); // Person does not equal a person with different properties
        assertFalse(person.equals(nullPerson)); // Person does not equal null
        assertFalse(person.equals(new Object())); // Person does not equal a different object

        // Test with the same object
        Person sameObject = person;
        assertTrue(person.equals(sameObject));
    }
}
