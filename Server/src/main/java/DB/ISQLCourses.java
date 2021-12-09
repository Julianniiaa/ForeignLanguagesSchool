package DB;

import SchoolOrg.Courses;

import java.util.ArrayList;

public interface ISQLCourses {
    boolean insert(Courses obj);
    ArrayList<Courses> get();
    ArrayList<Courses> find(Courses c);
    boolean insertCourse(Courses obj);
    boolean registration(Courses obj);
}
