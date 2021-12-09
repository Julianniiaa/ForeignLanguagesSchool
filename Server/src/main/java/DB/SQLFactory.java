package DB;

import java.sql.SQLException;

public class SQLFactory extends AbstractFactory {
    public SQLTeacher getTeachers() throws SQLException, ClassNotFoundException {
        return SQLTeacher.getInstance();
    }

    public SQLStudents getStudents() throws SQLException, ClassNotFoundException {
        return SQLStudents.getInstance();
    }

    public SQLAuthorization getRole() throws SQLException, ClassNotFoundException {
        return SQLAuthorization.getInstance();
    }

    public SQLCourses getCourse() throws SQLException, ClassNotFoundException {
        return SQLCourses.getInstance();
    }

    public SQLTimetable getTimetable() throws SQLException, ClassNotFoundException {
        return SQLTimetable.getInstance();
    }

    public SQLGroups getGroups() throws SQLException, ClassNotFoundException {
        return SQLGroups.getInstance();
    }

    public SQLAdmin getAdmin() throws SQLException, ClassNotFoundException {
        return SQLAdmin.getInstance();
    }

    public SQLReceive getReceive() throws SQLException, ClassNotFoundException {
        return SQLReceive.getInstance();
    }
}
