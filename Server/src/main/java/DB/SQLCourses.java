package DB;

import SchoolOrg.Courses;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class SQLCourses implements ISQLCourses {
    private static SQLCourses instance;
    private ConnectionDB dbConnection;

    private SQLCourses() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLCourses getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLCourses();
        }
        return instance;
    }

    public boolean insert(Courses obj) {
        String proc = "{call insert_language(?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getLanguage());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Courses> get() {
        String str = "select coursename, languages.`language` from courses\n" +
                "    join languages on languages.idlanguage = courses.idlanguage;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Courses> courseList = new ArrayList<>();
        for (String[] items: result){
            Courses courses = new Courses();
            courses.setCourse(items[0]);
            courses.setLanguage(items[1]);
            courseList.add(courses);
        }
        return courseList;
    }

    public ArrayList<Courses> find(Courses c) {
        String str = "select coursename, languages.`language` from courses\n" +
                "    join languages on languages.idlanguage = courses.idlanguage where languages.`language` = " +
                "\"" + c.getLanguage() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Courses> courseList = new ArrayList<>();
        for (String[] items: result){
            Courses courses = new Courses();
            courses.setCourse(items[0]);
            courses.setLanguage(items[1]);
            courseList.add(courses);
        }
        return courseList;
    }

    public boolean insertCourse(Courses obj) {
        String proc = "{call insert_course(?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getCourse());
            callableStatement.setString(2, obj.getLanguage());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean registration(Courses obj) {
        String proc = "{call alter_student(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getIdstud());
            callableStatement.setString(2, obj.getCourse());
            callableStatement.setString(3, obj.getLanguage());
            callableStatement.setInt(4, (int) (Math.random()*(9999+1)) + 1000);
            callableStatement.setInt(5, obj.getBalance());
            callableStatement.setInt(6, (int) (Math.random()*(999+1)) + 100);
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
