package model;

import java.util.ArrayList;
import java.util.List;

public class FamilyContactManager {
    private List<Person> familyContacts;
    private List<Event> customEvents;

    public FamilyContactManager() {
        familyContacts = new ArrayList<>();
        customEvents = new ArrayList<>();
    }

    public void addPerson(Person person) {
        familyContacts.add(person);
    }

    public void updatePersonDetails(String name, Person updatedPerson) {
        // Find the person with the given name, if it exists
        Person personToUpdate = null;
        for (Person person : familyContacts) {
            if (person.getName().equals(name)) {
                personToUpdate = person;
                break; // Exit the loop after finding the person
            }
        }

        // Check if the person exists in the list
        if (personToUpdate != null) {
            // Update the person's details
            personToUpdate.setName(updatedPerson.getName());
            personToUpdate.setRelationship(updatedPerson.getRelationship());
            personToUpdate.setBirthday(updatedPerson.getBirthday());
            personToUpdate.setEmail(updatedPerson.getEmail());
            personToUpdate.setPhoneNumber(updatedPerson.getPhoneNumber());
        }
        // If the person with the given name doesn't exist, do nothing (no exception is thrown)
    }


    public void deletePerson(String name) {
        familyContacts.removeIf(person -> person.getName().equals(name));
    }

    public void addEvent(Event event) {
        customEvents.add(event);
    }

    public void updateEvent(String eventName, Event updatedEvent) {
        for (int i = 0; i < customEvents.size(); i++) {
            Event existingEvent = customEvents.get(i);
            if (existingEvent.getEventName().equals(eventName)) {
                customEvents.set(i, updatedEvent);
                break;
            }
        }
    }

    public void deleteEvent(String eventName) {
        customEvents.removeIf(event -> event.getEventName().equals(eventName));
    }

    public List<Person> getAllContacts() {
        return new ArrayList<>(familyContacts);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(customEvents);
    }

    public Person getPersonByName(String name) {
        Person notFoundPerson = new Person("Not Found", "", "", "", ""); // Create a default Person object
        for (Person person : familyContacts) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        // Return the default Person object if the person with the given name is not found
        return notFoundPerson;
    }


    public Event getEventByName(String name) {
        Event notFoundEvent = new Event("Not Found", "", ""); // Create a default Event object
        for (Event event : customEvents) {
            if (event.getEventName().equals(name)) {
                return event;
            }
        }
        // Return the default Event object if the event with the given name is not found
        return notFoundEvent;
    }






}
