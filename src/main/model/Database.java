package model;

import java.util.*;

public class Database {
    private List<FamilyContact> contacts;

    public Database() {
        contacts = new ArrayList<>();
    }

    public void addContact(FamilyContact contact) {
        contacts.add(contact);
    }

    public List<FamilyContact> getAllContacts() {
        return contacts;
    }

    public List<FamilyContact> filterContactsByBirthday(String birthday) {
        List<FamilyContact> filteredContacts = new ArrayList<>();
        for (FamilyContact contact : contacts) {
            if (contact.getBirthdate().equals(birthday)) {
                filteredContacts.add(contact);
            }
        }
        return filteredContacts;
    }

    public void updateContact(String name, String email, String phoneNumber) {
        for (FamilyContact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setEmail(email);
                contact.setPhoneNumber(phoneNumber);
            }
        }
    }

    public boolean deleteContact(String name) {
        FamilyContact contactToDelete = null;
        for (FamilyContact contact : contacts) {
            if (contact.getName().equals(name)) {
                contactToDelete = contact;
                break;
            }
        }

        if (contactToDelete != null) {
            contacts.remove(contactToDelete);
            return true; // Contact was deleted successfully
        } else {
            return false; // Contact not found
        }
    }
}

