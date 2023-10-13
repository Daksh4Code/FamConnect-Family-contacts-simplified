package model;

// The 'Event' class represents a specific event associated with a person object in the family contact
// management system.
// It contains information about the event's name, date, and a brief description, all represented
// by strings.
// A standard event (or more) like a birthday or a custom event created by the user can be added to a
// person's profile (the person object) to keep track of events and their dates.
// This class provides methods to set and retrieve the event attributes.
public class Event {
    private String eventName;
    private String eventDate;
    private String eventDescription;

    // REQUIRES: None
    // MODIFIES: this
    // EFFECTS: Constructs an Event object with given event name, event date and event
    // description (all in String format)
    public Event(String eventName, String eventDate, String eventDescription) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
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
        return this.eventDescription;
    }

    public void setDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    // REQUIRES: None
    // MODIFIES: None
    // EFFECTS: Returns a String representation of the event name, event date and the event
    // description within a single curly bracket
    @Override
    public String toString() {
        return ("Event{" + "Event Name='" + this.eventName
                + '\'' + ", Event Date='" + this.eventDate + '\''
                + ", Event Description='" + this.eventDescription + '\'' + '}');
    }

}
