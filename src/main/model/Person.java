package model;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// The 'Person' class represents an individual contact in the family contact management system.
// It contains information about a person including their name, relationship to the user, birthdate,
// email, phone number, and any events associated with them which the user chooses to add.
// Events are unique to a person, and can include anything from anniversaries to graduation
// ceremonies.
// This class provides methods to set and retrieve the person's attributes, along with methods to
// add, retrieve, delete, update, and list events related to each contact (i.e., each Person object).
// It also implements the Writable interface and contains a toJson() method which returns the
// Person object and associated details in JSON format.
public class Person implements Writable {
    private String name;
    private String relationship;
    private LocalDate birthdate;
    private String emailID;
    private int phoneNumber;
    private List<Event> events;

    // REQUIRES: 'phoneNumber', 'events' and 'birthdate' should be in appropriate format of data int,
    // List and LocalDate types respectively, as must the other fields be in String format
    // MODIFIES: this
    // EFFECTS: Constructs a Person object with given name, relation, birthday, email ID, phone number
    // and an empty list of events ('phoneNumber', 'events' and 'birthdate' in int, List and
    // LocalDate types respectively, and all other fields in String format)
    public Person(String name, String relation, LocalDate birthdate, String emailID, int phoneNumber) {
        this.name = name;
        this.relationship = relation;
        this.birthdate = birthdate;
        this.emailID = emailID;
        this.phoneNumber = phoneNumber;
        this.events = new ArrayList<>();
    }

    // REQUIRES: 'birthdate' should be in appropriate format of data LocalDate type
    // MODIFIES: this
    // EFFECTS: Constructs a Person object with given name, relation and birthday
    // (all in String format except 'birthdate' which is in LocalDate format)
    public Person(String name, String relation, LocalDate birthdate) {
        this.name = name;
        this.relationship = relation;
        this.birthdate = birthdate;
    }

    // MODIFIES: this
    // EFFECTS: A no parameter constructor which constructs an empty list of events
    public Person() {
        this.events = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds an event to the list of events associated with the person, and does nothing
    // if event is null
    public void addEvent(Event event) {
        if (event != null) {
            this.events.add(event);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the Event object with the specified event name from
    // the list of events associated with the person, and does nothing if the event name is null
    public void deleteEvent(String eventName) {
        Event event = getEventByName(eventName);
        if (event != null) {
            this.events.remove(event);
        }
    }

    // REQUIRES: 'eventName' should not be null
    // EFFECTS: Retrieves and returns an event by its specified event name from the person's
    // list of events, returns null if the event name is not found in the list of events associated
    // with the person
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

    public String getName() {
        return this.name;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public String getEmailID() {
        return this.emailID;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    // Setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // EFFECTS: Retrieves and returns a well formatted string representation of the person,
    // including their name, relationship, birthdate, email ID, phone number, and a list of
    // events - if any are associated with the person (all within a pair of curly bracketss).
    @Override
    public String toString() {
        String result = "Person{name = '" + this.name  + "', relationship = '"
                + this.relationship + "', birthday = '" + this.birthdate.toString()
                + "', email = '" + this.emailID + "', phoneNumber = '" + this.phoneNumber;
        if (!this.events.isEmpty()) {
            result += ", Associated Events = [";
            boolean bool = true;
            for (Event event : this.events) {
                if (!bool) {
                    result += ", ";
                }
                result += event.getEventName();
                bool = false;
            }
            result += "]";
        }
        result += "}";
        return result;
    }

    // MODIFIES: this
    // EFFECTS: Returns a string representation of the person object and associated details
    // in JSON format
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("relationship", relationship);
        jsonObject.put("birthday", birthdate.toString());
        jsonObject.put("email", emailID);
        jsonObject.put("phoneNumber", phoneNumber);
        JSONArray associatedEventsArray = new JSONArray();
        for (Event event : events) {
            associatedEventsArray.put(event.toJson());
        }
        jsonObject.put("Associated Events", associatedEventsArray);
        return jsonObject;
    }
}