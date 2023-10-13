package model;

import java.util.ArrayList;
import java.util.List;

// The 'FamilyContactManager' class manages family contacts and events in a contact management system.
// It contains a list of all family contacts and a list of all events.
// It provides methods to add, retrieve, delete, update, and list family contacts and events related to
// each contact.
public class FamilyContactManager {
    private List<Person> familyContacts;
    private List<Event> events;

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Constructs a family contact manager object with two (initially empty) lists - one for
    // all family contacts, and one for all events
    public FamilyContactManager() {
        this.familyContacts = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Adds the Person object to the list of family contacts, and does nothing
    // if person is null
    public void addPerson(Person person) {
        if (person != null) {
            this.familyContacts.add(person);
        }
    }

    // REQUIRES: None
    // MODIFIES: None
    // EFFECTS: Returns a list of family contacts
    public List<Person> getAllContacts() {
        return familyContacts;
    }

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Removes the Person object with the specified name from
    // the list of family contacts, and does nothing if the name is null
    public void deletePerson(String name) {
        Person person = getPersonByName(name);
        if (person != null) {
            familyContacts.remove(person);
        }
    }

    // REQUIRES: 'name' should not be null
    // MODIFIES: None
    // EFFECTS: Retrieves and returns the Person object with the specified name from
    // the list of family contacts
    public Person getPersonByName(String name) {
        for (Person person : familyContacts) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null; // Return null if not found
    }


    // REQUIRES: 'name', 'updatedPerson' should not be null
    // MODIFIES: this
    // EFFECTS: Removes the Person object with the specified name from
    // the list of family contacts
    public void updatePersonDetails(String name, Person updatedPerson) {
        Person person = getPersonByName(name);
        if (person != null) {
            person.setRelationship(updatedPerson.getRelationship());
            person.setBirthday(updatedPerson.getBirthday());
        }
    }

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Adds the Event object to the list of events, and does nothing
    // if event is null
    public void addEvent(Event event) {
        if (event != null) {
            events.add(event);
        }
    }

    // REQUIRES: None
    // MODIFIES: None
    // EFFECTS: Returns a list of events
    public List<Event> getAllEvents() {
        return events;
    }

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Removes the Event object with the specified event name from
    // the list of events, and does nothing if the event name is null
    public void deleteEvent(String eventName) {
        Event event = getEventByName(eventName);
        if (event != null) {
            events.remove(event);
        }
    }

    // REQUIRES: 'eventName' should not be null
    // MODIFIES: None
    // EFFECTS: Retrieves and returns the Event object with the specified event name from
    // the list of events
    public Event getEventByName(String eventName) {
        for (Event event : events) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    // REQUIRES: 'eventName', 'updatedEvent' should not be null
    // MODIFIES: this
    // EFFECTS: Removes the Event object with the specified event name from
    // the list of events
    public void updateEvent(String eventName, Event updatedEvent) {
        Event event = getEventByName(eventName);
        if (event != null) {
            event.setEventDate(updatedEvent.getEventDate());
            event.setDescription(updatedEvent.getDescription());
        }
    }



}
