package SchoolOrg;

import java.io.Serializable;

public class Timetable implements Serializable {
    int id;
    int classNumber;
    String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", classNumber=" + classNumber +
                ", time='" + time + '\'' +
                '}';
    }
}
