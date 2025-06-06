package persistence;

import model.FamilyContactManager;

import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of FamilyContactManager to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: Constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: Opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: Writes JSON representation of FamilyContactManager to file
    public void write(FamilyContactManager manager) {
        JSONObject json = manager.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: Closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: Writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}