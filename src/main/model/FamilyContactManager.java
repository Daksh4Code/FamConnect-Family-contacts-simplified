package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// The 'FamilyContactManager' class manages family contacts (people) in a contact management system.
// It contains a list of all family contacts.
// It provides methods to add, retrieve, delete, update, and list family contacts.
// It also contains methods related to JSON files for data persistence of the app.
public class FamilyContactManager implements Writable {
    private List<Person> familyContacts;

    // MODIFIES: this
    // EFFECTS: Constructs a family contact manager object with one (initially empty) list - for
    // family contacts
    public FamilyContactManager() {
        this.familyContacts = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the Person object to the list of family contacts, and does nothing
    // if person is null
    public void addPerson(Person person) {
        if (person != null) {
            this.familyContacts.add(person);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the Person object with the specified name from
    // the list of family contacts, and does nothing if the name is null
    public void deletePerson(String name) {
        Person person = getPersonByName(name);
        if (person != null) {
            this.familyContacts.remove(person);
        }
    }

    // REQUIRES: 'name' should not be null
    // EFFECTS: Retrieves and returns the Person object with the specified name from
    // the list of family contacts, and returns null if the name is not found in the list
    // of family contacts
    public Person getPersonByName(String name) {
        for (Person person : this.familyContacts) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    // REQUIRES: 'name', 'updatedPerson' should not be null
    // MODIFIES: this
    // EFFECTS: Updates the Person object with the specified name from
    // the list of family contacts
    public void updatePersonDetails(String name, Person updatedPerson) {
        Person person = getPersonByName(name);
        if (person != null) {
            person.setRelationship(updatedPerson.getRelationship());
            person.setBirthdate(updatedPerson.getBirthdate());
            person.setEmailID(updatedPerson.getEmailID());
            person.setPhoneNumber(updatedPerson.getPhoneNumber());
        }
    }

    // Getter method(s)

    public List<Person> getAllContacts() {
        return this.familyContacts;
    }

    // Returns a string representation of the list of family contacts, in JSON format
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("familyContacts", familyContactsToJsonArray());
        return jsonObject;
    }

    // MODIFIES: this
    // EFFECTS: Returns an array of JSON type representing the list of family contacts
    public JSONArray familyContactsToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Person person : this.familyContacts) {
            jsonArray.put(person.toJson());
        }
        return jsonArray;
    }
}