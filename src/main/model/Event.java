package model;

import org.json.JSONObject;
import persistence.Writable;
import java.time.LocalDate;

// The 'Event' class represents specific events associated with a (unique) person object in the family
// contact management system.
// It contains information about the event's name, date, and a brief description, where the event's name
// and description are represented by strings and the event's date is in the LocalDate format.
// A standard event (or more) like a birthdate/graduation date created by the user can be added to a
// person's profile (the person object) to keep track of events and their dates.
// This class provides methods to set and retrieve the event attributes.
// It also implements the Writable interface and contains a toJson() method which returns the
// Event object in JSON format.
public class Event implements Writable {
    private String eventName;
    private LocalDate eventDate;
    private String eventDescription;

    // REQUIRES: 'eventDate' must be in appropriate date format of LocalDate data Type, as must
    // 'eventName' and 'eventDescription' be in String format
    // MODIFIES: this
    // EFFECTS: Constructs an Event object with given event name, event date and event
    // description ('eventName' and 'eventDescription' in String format, 'eventDate' in LocalDate format)
    public Event(String eventName, LocalDate eventDate, String eventDescription) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
    }

    // Getter methods

    public String getEventName() {
        return this.eventName;
    }

    public LocalDate getEventDate() {
        return this.eventDate;
    }

    public String getEventDescription() {
        return this.eventDescription;
    }

    // Setter methods

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    // EFFECTS: Returns a String representation of the event name, event date and the event
    // description within a single pair of curly brackets
    @Override
    public String toString() {
        return ("Event{" + "eventName='" + this.eventName
                + '\'' + ", eventDate='" + this.eventDate.toString() + '\''
                + ", eventDescription='" + this.eventDescription + '\'' + '}');
    }

    // MODIFIES: this
    // EFFECTS: Returns a string representation of the Event object in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("eventName", eventName);
        jsonObject.put("eventDate", eventDate.toString());
        jsonObject.put("eventDescription", eventDescription);
        return jsonObject;
    }
}