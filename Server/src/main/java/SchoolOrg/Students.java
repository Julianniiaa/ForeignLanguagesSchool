package SchoolOrg;

import java.io.Serializable;
import java.util.Objects;

public class Students implements Serializable {
    private String firstname;
    private String lastname;
    private String averageMark;
    private int colMarks;
    private String login;
    private String password;
    private int payment;
    private int group;

    public Students() {
        this.colMarks = 0;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAverageMark(String averageMark) {
        this.averageMark = averageMark;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAverageMark() {
        return averageMark;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getColMarks() {
        return colMarks;
    }

    public void setColMarks(int colMarks) {
        this.colMarks = colMarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Students that = (Students) o;

        return  Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.firstname, that.firstname) &&
                Objects.equals(this.lastname, that.lastname) &&
                Objects.equals(this.averageMark, that.averageMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.login, this.password, this.firstname, this.lastname, this.averageMark);
    }

    @Override
    public String toString() {
        return "Students{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", averageMark='" + averageMark + '\'' +
                ", colMarks='" + colMarks + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", payment=" + payment +
                ", group=" + group +
                '}';
    }
}
