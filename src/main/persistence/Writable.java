package persistence;

import org.json.JSONObject;

// The 'Writable' interface initializes the 'toJson' function of the JSONObject class,
// and is implemented by the Event, FamilyContactManager and Person classes
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}