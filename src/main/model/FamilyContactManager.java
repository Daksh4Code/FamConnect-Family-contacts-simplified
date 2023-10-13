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
        for (int i = 0; i < familyContacts.size(); i++) {
            Person existingPerson = familyContacts.get(i);
            if (existingPerson.getName().equals(name)) {
                familyContacts.set(i, updatedPerson);
                break;
            }
        }
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

}
