package model;

public class Person {
    private String name;
    private String relationship;
    private String birthday;
    private String email;
    private String phoneNumber;

    public Person(String name, String relationship, String birthday, String email, String phoneNumber) {
        this.name = name;
        this.relationship = relationship;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", relationship='"
                + relationship + '\'' + ", birthday='" + birthday + '\''
                + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber
                + '\'' + '}';
    }
}
