package DB;

import SchoolOrg.Role;
import SchoolOrg.Teacher;

import java.util.ArrayList;

public interface ISQLTeacher {
    void findTeacher(Teacher obj);
    boolean insert(Teacher obj);
    boolean changeTeacher(Teacher obj);
    ArrayList<Teacher> getTeacher(Role r);
    Role getIdByTeacher(Role obj);
    Role getIdByStudents(Role obj);
}
