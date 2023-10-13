package model;

import java.util.ArrayList;
import java.util.List;


// The 'Person' class represents an individual person in the family contact management system.
// It contains information about a person including their name, relationship to the user, birthday,
// email, phone number, and any custom events associated with them which the user chooses to add.
// Custom events are unique to a person, and can include anything from anniversaries to graduation
// ceremonies.
// It provides methods to set and retrieve the person's attributes
public class Person {
    private String name;
    private String relationship;
    private String birthday;
    private String email;
    private String phoneNumber;
    private List<Event> customEvents;

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Constructs a Person object with given name, relation, birthday,
    // email and phone number (all in String format to maintain uniformity and ease of maintenance)
    public Person(String name, String relation, String birthday, String email, String phoneNumber) {
        this.name = name;
        this.relationship = relation;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customEvents = new ArrayList<>();
    }

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Constructs a Person object with given name, relation and birthday
    // (all in String format)
    public Person(String name, String relation, String birthday) {
        this.name = name;
        this.relationship = relation;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Event> getCustomEvents() {
        return customEvents;
    }

    public void addCustomEvent(Event event) {
        customEvents.add(event);
    }


    public Event getCustomEventByName(String eventName) {
        for (Event event : customEvents) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "Person{name='" + name
                + "', relationship='" + relationship + "', birthday='"
                + birthday + "', email='" + email + "', phoneNumber='" + phoneNumber;

        if (!customEvents.isEmpty()) {
            result += ", customEvents=[";
            boolean first = true;
            for (Event event : customEvents) {
                if (!first) {
                    result += ", ";
                }
                result += event.getEventName();
                first = false;
            }
            result += "]";
        }
        result += "}";
        return result;
    }


}
