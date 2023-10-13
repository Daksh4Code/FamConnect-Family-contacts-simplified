package model;

// The 'Event' class represents a specific event associated with a person object in the family contact
// management system.
// It contains information about the event's name, date, and a brief description, all represented
// by strings.
// A standard event (or more) like a birthday or a custom event created by the user can be added to a
// person's profile (the person object) to keep track of events and their dates.
// This class provides methods to set and retrieve the event attributes
public class Event {
    private String eventName;
    private String eventDate;
    private String description;

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Constructs an Event object with given event name, event date and event
    // description (all in String format)
    public Event(String eventName, String eventDate, String description) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.description = description;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // REQUIRES: None
    // MODIFIES: None
    // EFFECTS: Returns a String representation of the event name, event date and the event
    // description within a single curly bracket
    @Override
    public String toString() {
        return ("Event{" + "eventName='" + this.eventName + '\'' + ", eventDate='"
                + this.eventDate + '\'' + ", description='"
                + this.description + '\'' + '}');
    }

}
