package model;

import java.util.*;

public class FamilyContact {

    private String name;
    private String relationship;
    private String birthdate;
    private String email;
    private String phoneNumber;
    private List<String> customEvents;

    public FamilyContact(String name, String relationship, String birthdate, String email, String phoneNumber) {
        this.name = name;
        this.relationship = relationship;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customEvents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getCustomEvents() {
        return customEvents;
    }

    public void addCustomEvent(String event) {
        customEvents.add(event);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setBirthday(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
