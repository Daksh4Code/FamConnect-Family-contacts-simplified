package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// The 'Person' class represents an individual person in the family contact management system.
// It contains information about a person including their name, relationship to the user, birthday,
// email, phone number, and any custom events associated with them which the user chooses to add.
// Custom events are unique to a person, and can include anything from anniversaries to graduation
// ceremonies.
// This class provides methods to set and retrieve the person's attributes.
// It also implements the Writable interface and contains a toJson() method which returns the
// Person object and associated details in JSON format.
public class Person implements Writable {
    private String name;
    private String relationship;
    private String birthdate;
    private String email;
    private String phoneNumber;
    private List<Event> customEvents;

    // REQUIRES: phoneNumber and birthdate should be in appropriate format of data String type
    // MODIFIES: this
    // EFFECTS: Constructs a Person object with given name, relation, birthday,
    // email and phone number (all in String format to maintain uniformity and ease of maintenance)
    public Person(String name, String relation, String birthdate, String email, String phoneNumber) {
        this.name = name;
        this.relationship = relation;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customEvents = new ArrayList<>();
    }

    // REQUIRES: birthdate should be in appropriate format of data String type
    // MODIFIES: this
    // EFFECTS: Constructs a Person object with given name, relation and birthday
    // (all in String format)
    public Person(String name, String relation, String birthdate) {
        this.name = name;
        this.relationship = relation;
        this.birthdate = birthdate;
    }

    // Getter methods

    public String getName() {
        return this.name;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public List<Event> getCustomEvents() {
        return this.customEvents;
    }

    // Setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // REQUIRES: 'event' should not be null
    // MODIFIES: this
    // EFFECTS: Adds a custom event to the list of custom events associated with the person
    public void addCustomEvent(Event event) {
        this.customEvents.add(event);
    }

    // REQUIRES: 'eventName' should not be null
    // EFFECTS: Retrieves and returns a custom event by its specified event name from the person's
    // list of custom events
    public Event getCustomEventByName(String eventName) {
        for (Event event : this.customEvents) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    // EFFECTS: Retrieves and returns a well formatted string representation of the person,
    // including their name, relationship, birthday, email, phone number, and a list of
    // custom events - if any are associated with the person.
    @Override
    public String toString() {
        String result = "Person{Name='" + this.name  + "', Relationship='"
                + this.relationship + "', Birthday='" + this.birthdate
                + "', Email='" + this.email + "', Phone Number='" + this.phoneNumber;
        if (!this.customEvents.isEmpty()) {
            result += ", Custom Events=[";
            boolean bool = true;
            for (Event event : this.customEvents) {
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
        jsonObject.put("birthday", birthdate);
        jsonObject.put("email", email);
        jsonObject.put("phoneNumber", phoneNumber);
        JSONArray customEventsArray = new JSONArray();
        for (Event event : customEvents) {
            customEventsArray.put(event.toJson());
        }
        jsonObject.put("customEvents", customEventsArray);
        return jsonObject;
    }
}
