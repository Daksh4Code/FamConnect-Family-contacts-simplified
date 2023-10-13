package model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String relationship;
    private String birthday;
    private String email;
    private String phoneNumber;
    private List<Event> customEvents;

    public Person(String name, String relation, String birthday, String email, String phoneNumber) {
        this.name = name;
        this.relationship = relation;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customEvents = new ArrayList<>();
    }

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

    public void removeCustomEvent(Event event) {
        customEvents.remove(event);
    }

    public void updateCustomEvent(String eventName, String newEventDate, String newDescription) {
        for (Event event : customEvents) {
            if (event.getEventName().equals(eventName)) {
                event.setEventDate(newEventDate);
                event.setDescription(newDescription);
                return;
            }
        }
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
        StringBuilder builder = new StringBuilder();
        builder.append("Person{name='").append(name).append('\'');
        builder.append(", relationship='").append(relationship).append('\'');
        builder.append(", birthday='").append(birthday).append('\'');
        builder.append(", email='").append(email).append('\'');
        builder.append(", phoneNumber='").append(phoneNumber).append('\'');

        if (!customEvents.isEmpty()) {
            builder.append(", customEvents=[");
            for (Event event : customEvents) {
                builder.append(event.getEventName()).append(", ");
            }
            builder.setLength(builder.length() - 2);
            builder.append("]");
        }

        builder.append('}');
        return builder.toString();
    }
}
