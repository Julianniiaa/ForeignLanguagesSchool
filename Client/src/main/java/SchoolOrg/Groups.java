package SchoolOrg;

import java.io.Serializable;

public class Groups implements Serializable {
    int id;
    int grNumber;
    int classNumber;
    String time;
    String language;
    String course;
    String firstname;
    String lastname;

    public Groups(Groups groups) {
        this.id = groups.id;
        this.grNumber = groups.grNumber;
        this.classNumber = groups.classNumber;
        this.time = groups.time;
        this.language = groups.language;
        this.course = groups.course;
        this.firstname = groups.firstname;
        this.lastname = groups.lastname;
    }

    public Groups() {
        this.grNumber = 0;
        this.classNumber = 0;
        this.time = "";
        this.language = "";
        this.course = "";
        this.firstname = "";
        this.lastname = "";
    }

    public Groups(int grNumber, int classNumber, String time, String language, String course, String firstname, String lastname) {
        this.grNumber = grNumber;
        this.classNumber = classNumber;
        this.time = time;
        this.language = language;
        this.course = course;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrNumber() {
        return grNumber;
    }

    public void setGrNumber(int grNumber) {
        this.grNumber = grNumber;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", grNumber=" + grNumber +
                ", classNumber=" + classNumber +
                ", time='" + time + '\'' +
                ", language='" + language + '\'' +
                ", course='" + course + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
