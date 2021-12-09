package DB;

import SchoolOrg.Groups;
import SchoolOrg.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLGroups implements ISQLGroups {
    String query = null;
    public static PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private static SQLGroups instance;
    private ConnectionDB dbConnection;

    private SQLGroups() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLGroups getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLGroups();
        }
        return instance;
    }

    public ArrayList<Groups> get() {
        String str = "Select `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`," +
                " `teachers`.`firstname`, `teachers`.`lastname`" +
                " from `groups`" +
                " join timetables on timetables.idtimetable = `groups`.`idtimetable`" +
                " join teachers on teachers.idteacher = `groups`.`idteacher`" +
                " join courses on courses.idcourse = `groups`.`idcourse`" +
                " join languages on languages.idlanguage = courses.idlanguage;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Groups> groupsList = new ArrayList<>();
        for (String[] items: result){
            Groups group = new Groups();
            group.setGrNumber(Integer.parseInt(items[0]));
            group.setCourse(items[1]);
            group.setLanguage(items[2]);
            group.setClassNumber(Integer.parseInt(items[3]));
            group.setTime(items[4]);
            group.setFirstname(items[5]);
            group.setLastname(items[6]);
            groupsList.add(group);
        }
        return groupsList;
    }

    public ArrayList<Groups> find(Groups gr) {
        String str = "Select `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`," +
                " `teachers`.`firstname`, `teachers`.`lastname`" +
                " from `groups`" +
                " join timetables on timetables.idtimetable = `groups`.`idtimetable`" +
                " join teachers on teachers.idteacher = `groups`.`idteacher`" +
                " join courses on courses.idcourse = `groups`.`idcourse`" +
                " join languages on languages.idlanguage = courses.idlanguage where numberGroup = " + gr.getGrNumber()
                + ";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Groups> groupsList = new ArrayList<>();
        for (String[] items: result){
            Groups group = new Groups();
            group.setGrNumber(Integer.parseInt(items[0]));
            group.setCourse(items[1]);
            group.setLanguage(items[2]);
            group.setClassNumber(Integer.parseInt(items[3]));
            group.setTime(items[4]);
            group.setFirstname(items[5]);
            group.setLastname(items[6]);
            groupsList.add(group);
        }
        return groupsList;
    }

    public ArrayList<Groups> getTeacher() {
        String str = "Select `keys`.`login`, `teachers`.`lastname`, `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`" +
                " from `groups`" +
                " join timetables on timetables.idtimetable = `groups`.`idtimetable`" +
                " join teachers on teachers.idteacher = `groups`.`idteacher`" +
                " join `keys` on `keys`.id_keys = teachers.id_keys" +
                " join courses on courses.idcourse = `groups`.`idcourse`" +
                " join languages on languages.idlanguage = courses.idlanguage" +
                " order by keys.login;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Groups> groupsList = new ArrayList<>();
        for (String[] items: result){
            Groups group = new Groups();
            group.setGrNumber(Integer.parseInt(items[2]));
            group.setCourse(items[3]);
            group.setLanguage(items[4]);
            group.setClassNumber(Integer.parseInt(items[5]));
            group.setTime(items[6]);
            group.setFirstname(items[0]);
            group.setLastname(items[1]);
            groupsList.add(group);
        }
        return groupsList;
    }

    public ArrayList<Groups> findTeacher(Groups t) {
        String str = "Select `keys`.`login`, `teachers`.`lastname`, `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`" +
                " from `groups`" +
                " join timetables on timetables.idtimetable = `groups`.`idtimetable`" +
                " join teachers on teachers.idteacher = `groups`.`idteacher`" +
                " join `keys` on `keys`.id_keys = teachers.id_keys" +
                " join courses on courses.idcourse = `groups`.`idcourse`" +
                " join languages on languages.idlanguage = courses.idlanguage" +
                " where `keys`.login = \"" + String.valueOf(t.getFirstname()) + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Groups> groupsList = new ArrayList<>();
        for (String[] items: result){
            Groups group = new Groups();
            group.setGrNumber(Integer.parseInt(items[2]));
            group.setCourse(items[3]);
            group.setLanguage(items[4]);
            group.setClassNumber(Integer.parseInt(items[5]));
            group.setTime(items[6]);
            group.setFirstname(items[0]);
            group.setLastname(items[1]);
            groupsList.add(group);
        }
        return groupsList;
    }

    public ArrayList<Groups> getpersTimetable(Role r) {
        String str = "Select `keys`.`login`, `teachers`.`lastname`, `groups`.`numberGroup`, `courses`.`coursename`, `languages`.`language`, `timetables`.`class`, `timetables`.`time`" +
                " from `groups`" +
                " join timetables on timetables.idtimetable = `groups`.`idtimetable`" +
                " join teachers on teachers.idteacher = `groups`.`idteacher`" +
                " join `keys` on `keys`.id_keys = teachers.id_keys" +
                " join courses on courses.idcourse = `groups`.`idcourse`" +
                " join languages on languages.idlanguage = courses.idlanguage" +
                " where `keys`.id_keys =" + r.getId() + ";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Groups> groupsList = new ArrayList<>();
        for (String[] items: result){
            Groups group = new Groups();
            group.setGrNumber(Integer.parseInt(items[2]));
            group.setCourse(items[3]);
            group.setLanguage(items[4]);
            group.setClassNumber(Integer.parseInt(items[5]));
            group.setTime(items[6]);
            group.setFirstname(items[0]);
            group.setLastname(items[1]);
            groupsList.add(group);
        }
        return groupsList;
    }
}
