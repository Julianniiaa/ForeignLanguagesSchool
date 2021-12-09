package DB;

import SchoolOrg.Role;
import SchoolOrg.Students;

import java.util.ArrayList;

public interface ISQLStudents {
    ArrayList<Students> findStudent(Students obj);
    Role insert(Students obj);
    boolean deleteStud(Students obj);
    ArrayList<Students> get();
    Students getStudent(Role r);
    ArrayList<Students> getMarks(Role r);
    Students getMark(Students s, Role r);
    void setMarks(Students s);
    Students getGrNumber(Role r);
    ArrayList<Students> getProgress();
}
