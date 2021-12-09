package DB;

import SchoolOrg.Role;
import SchoolOrg.Teacher;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;

public class SQLTeacher implements ISQLTeacher {
    private static SQLTeacher instance;
    private ConnectionDB dbConnection;

    private SQLTeacher() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLTeacher getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLTeacher();
        }
        return instance;
    }

    public void findTeacher(Teacher obj) {

    }

    public boolean insert(Teacher obj) {
        String proc = "{call insert_teacher(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getFirstname());
            callableStatement.setString(2, obj.getLastname());
            callableStatement.setString(3, obj.getCategory());
            callableStatement.setString(4, obj.getLogin());
            callableStatement.setString(5, obj.getPassword());
            callableStatement.setString(6, obj.getLanguage());
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

    public boolean changeTeacher(Teacher obj) {
        String proc = "{call change_teacher(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getLastlogin());
            callableStatement.setString(2, obj.getFirstname());
            callableStatement.setString(3, obj.getLastname());
            callableStatement.setString(4, obj.getCategory());
            callableStatement.setString(5, obj.getLogin());
            callableStatement.setString(6, obj.getPassword());
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

    public ArrayList<Teacher> getTeacher(Role r) {
        String str = "select `keys`.login, firstname, lastname, category, languages.`language`" +
                " from teachers" +
                " join `keys` on `keys`.id_keys = teachers.id_keys" +
                " join languages on languages.idlanguage = teachers.idlanguage" +
                " where `keys`.id_keys = " + r.getId() + ";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Teacher> teacherList = new ArrayList<>();
        for (String[] items: result){
            Teacher teacher = new Teacher();
            teacher.setLogin(items[0]);
            teacher.setFirstname(items[1]);
            teacher.setLastname(items[2]);
            teacher.setCategory(items[3]);
            teacher.setLanguage(items[4]);
            teacherList.add(teacher);
        }
        return teacherList;
    }

    public Role getIdByTeacher(Role obj) {
        String proc = "{call get_idteacher_bykeys(?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getId());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            obj.setId(callableStatement.getInt(2));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Role getIdByStudents(Role obj) {
        String proc = "{call get_idkeys_bystudents(?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getId());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            obj.setId(callableStatement.getInt(2));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
