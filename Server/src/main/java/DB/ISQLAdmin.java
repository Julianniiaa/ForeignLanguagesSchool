package DB;

import SchoolOrg.Admin;
import SchoolOrg.Teacher;

import java.util.ArrayList;

public interface ISQLAdmin {
    ArrayList<Admin> get();
    void change(Teacher obj);
}
