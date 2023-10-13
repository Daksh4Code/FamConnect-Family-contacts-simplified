package model;  // Ensure it's in the correct package

public class Event {
    private String eventName;
    private String eventDate;
    private String description;

    public Event(String eventName, String eventDate, String description) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" + "eventName='" + eventName + '\''
                + ", eventDate='" + eventDate + '\'' + ", description='"
                + description + '\'' + '}';
    }
}
