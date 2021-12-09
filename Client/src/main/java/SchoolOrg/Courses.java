package SchoolOrg;

import java.io.Serializable;

public class Courses implements Serializable {
    private int idstud;
    private String course;
    private String language;
    private int balance;

    public int getIdstud() {
        return idstud;
    }

    public void setIdstud(int idstud) {
        this.idstud = idstud;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    @Override
    public String toString() {
        return "Courses{" +
                "idstud=" + idstud +
                ", course='" + course + '\'' +
                ", language='" + language + '\'' +
                ", balance=" + balance +
                '}';
    }
}
