package DB;

import SchoolOrg.Timetable;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SQLTimetable implements ISQLTimetable {
    private static SQLTimetable instance;
    private ConnectionDB dbConnection;

    private SQLTimetable() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLTimetable getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLTimetable();
        }
        return instance;
    }

    public boolean insert(Timetable obj) {
        String proc = "{call alter_group(?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getId());
            callableStatement.setInt(2, obj.getClassNumber());
            callableStatement.setString(3, obj.getTime());
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

    public boolean change(Timetable obj) {
        String proc = "{call change_timetable(?,?,?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getId());
            callableStatement.setInt(2, obj.getClassNumber());
            callableStatement.setString(3, obj.getTime());
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
