package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// The 'FamilyContactManager' class manages family contacts (people) and events in a contact
// management system.
// It contains a list of all family contacts and a list of all events.
// It provides methods to add, retrieve, delete, update, and list family contacts and events related to
// each contact (i.e., each Person object).
// It also contains methods related to JSON files for data persistence of the app.
public class FamilyContactManager implements Writable {
    private List<Person> familyContacts;
    private List<Event> events;

    // MODIFIES: this
    // EFFECTS: Constructs a family contact manager object with two (initially empty) lists - one for
    // all family contacts, and one for all events
    public FamilyContactManager() {
        this.familyContacts = new ArrayList<>();
        this.events = new ArrayList<>();
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
    // the list of family contacts
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
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds the Event object to the list of events, and does nothing
    // if event is null
    public void addEvent(Event event) {
        if (event != null) {
            this.events.add(event);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the Event object with the specified event name from
    // the list of events, and does nothing if the event name is null
    public void deleteEvent(String eventName) {
        Event event = getEventByName(eventName);
        if (event != null) {
            this.events.remove(event);
        }
    }

    // REQUIRES: 'eventName' should not be null
    // EFFECTS: Retrieves and returns the Event object with the specified event name from
    // the list of events
    public Event getEventByName(String eventName) {
        for (Event event : this.events) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    // REQUIRES: 'eventName', 'updatedEvent' should not be null
    // MODIFIES: this
    // EFFECTS: Updates the Event object with the specified event name from
    // the list of events
    public void updateEvent(String eventName, Event updatedEvent) {
        Event event = getEventByName(eventName);
        if (event != null) {
            event.setEventDate(updatedEvent.getEventDate());
            event.setEventDescription(updatedEvent.getEventDescription());
        }
    }

    // Getter methods

    public List<Person> getAllContacts() {
        return this.familyContacts;
    }

    public List<Event> getAllEvents() {
        return this.events;
    }

    // Returns a string representation of the list of family contacts and the list
    // of events, in JSON format
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
