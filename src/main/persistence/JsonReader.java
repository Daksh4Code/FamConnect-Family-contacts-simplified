package persistence;

import model.Event;
import model.FamilyContactManager;
import model.Person;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads FamilyContactManager from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads FamilyContactManager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FamilyContactManager read() throws IOException {
        String jsonData = readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFamilyContactManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses FamilyContactManager from JSON object and returns it
    private FamilyContactManager parseFamilyContactManager(JSONObject jsonObject) {
        FamilyContactManager manager = new FamilyContactManager();
        addPeople(manager, jsonObject.getJSONArray("familyContacts"));
        return manager;
    }

    // MODIFIES: manager
    // EFFECTS: parses person object from JSON object and adds it and subsequent details
    // to FamilyContactManager (JSON format)
    private void addPeople(FamilyContactManager manager, JSONArray people) {
        for (Object obj : people) {
            JSONObject personJson = (JSONObject) obj;
            String name = personJson.getString("name");
            String relationship = personJson.getString("relationship");
            String birthdateString = personJson.getString("birthday");
            LocalDate birthdate = LocalDate.parse(birthdateString);
            String emailID = personJson.getString("email");
            int phoneNumber = personJson.getInt("phoneNumber");
            Person person = new Person(name, relationship, birthdate, emailID, phoneNumber);
            JSONArray associatedEventsJson = personJson.getJSONArray("Associated Events");
            for (Object eventObj : associatedEventsJson) {
                JSONObject eventJson = (JSONObject) eventObj;
                String eventName = eventJson.getString("eventName");
                String eventDateString = eventJson.getString("eventDate");
                LocalDate eventDate = LocalDate.parse(eventDateString);
                String eventDescription = eventJson.getString("eventDescription");
                Event associatedEvent = new Event(eventName, eventDate, eventDescription);
                person.addEvent(associatedEvent);
            }
            manager.addPerson(person);
        }
    }
}