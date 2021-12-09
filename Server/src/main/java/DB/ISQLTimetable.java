package DB;

import SchoolOrg.Timetable;

public interface ISQLTimetable {
    boolean insert(Timetable obj);
    boolean change(Timetable obj);
}
