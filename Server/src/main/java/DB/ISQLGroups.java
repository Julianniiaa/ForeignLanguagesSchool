package DB;

import SchoolOrg.Groups;
import SchoolOrg.Role;

import java.util.ArrayList;

public interface ISQLGroups {
    ArrayList<Groups> get();
    ArrayList<Groups> find(Groups gr);
    ArrayList<Groups> getTeacher();
    ArrayList<Groups> findTeacher(Groups t);
    ArrayList<Groups> getpersTimetable(Role r);
}
