package model;

import java.util.ArrayList;
import java.util.List;

public class FamilyContactManager {
    private List<Person> familyContacts;
    private List<Event> events;

    public FamilyContactManager() {
        familyContacts = new ArrayList<>();
        events = new ArrayList<>();
    }

    public void addPerson(Person person) {
        familyContacts.add(person);
    }

    public List<Person> getAllContacts() {
        return familyContacts;
    }

    public void deletePerson(String name) {
        Person person = getPersonByName(name);
        if (person != null) {
            familyContacts.remove(person);
        }
    }

    public Person getPersonByName(String name) {
        for (Person person : familyContacts) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null; // Return null if the person with the given name is not found
    }

    public void updatePersonDetails(String name, Person updatedPerson) {
        Person person = getPersonByName(name);
        if (person != null) {
            // Update person's details
            person.setRelationship(updatedPerson.getRelationship());
            person.setBirthday(updatedPerson.getBirthday());
            // Update more details as needed
        }
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public void deleteEvent(String eventName) {
        Event event = getEventByName(eventName);
        if (event != null) {
            events.remove(event);
        }
    }

    public Event getEventByName(String eventName) {
        for (Event event : events) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null; // Return null if the event with the given name is not found
    }

    public void updateEvent(String eventName, Event updatedEvent) {
        Event event = getEventByName(eventName);
        if (event != null) {
            // Update event's details
            event.setEventDate(updatedEvent.getEventDate());
            event.setDescription(updatedEvent.getDescription());
            // Update more details as needed
        }
    }
}
