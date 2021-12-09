package DB;

import SchoolOrg.Role;
import SchoolOrg.Students;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;

public class SQLStudents implements ISQLStudents {
    private static SQLStudents instance;
    private ConnectionDB dbConnection;

    private SQLStudents() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLStudents getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLStudents();
        }
        return instance;
    }

    @Override
    public ArrayList<Students> findStudent(Students obj) {
        String str = "select `keys`.login, firstname, lastname, averageMark, `groups`.numberGroup, payments.payment" +
                " from students" +
                " join `keys` on `keys`.`id_keys` = students.id_keys" +
                " left join `groups` on `groups`.idgroup = students.idgroup" +
                " left join payments on payments.idpayment = students.idpayment" +
                " where `keys`.login = \"" + obj.getLogin() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Students> studList = new ArrayList<>();
        for (String[] items: result){
            Students students = new Students();
            students.setLogin(items[0]);
            students.setFirstname(items[1]);
            students.setLastname(items[2]);
            students.setAverageMark(items[3]);
            try {
                students.setGroup(Integer.parseInt(items[4]));
                students.setPayment(Integer.parseInt(items[5]));
            } catch (NumberFormatException e) {
                System.out.println("null");
            }
            studList.add(students);
        }
        return studList;
    }

    public boolean deleteStud(Students obj) {
        String proc = "{call delete_student(?)}";
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getLogin());
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

    @Override
    public ArrayList<Students> get() {
        String str = "select `keys`.login, firstname, lastname, averageMark, `groups`.numberGroup, payments.payment" +
                " from students" +
                " join `keys` on `keys`.`id_keys` = students.id_keys" +
                " left join `groups` on `groups`.idgroup = students.idgroup" +
                " left join payments on payments.idpayment = students.idpayment;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Students> infList = new ArrayList<>();
        for (String[] items: result){
            Students students = new Students();
            students.setLogin(items[0]);
            students.setFirstname(items[1]);
            students.setLastname(items[2]);
            students.setAverageMark(items[3]);
            try {
                students.setGroup(Integer.parseInt(items[4]));
                students.setPayment(Integer.parseInt(items[5]));
            } catch (NumberFormatException e) {
                System.out.println("null");
            }
            infList.add(students);
        }
        return infList;
    }

    @Override
    public Students getStudent(Role r) {
        String str = "select `keys`.login, firstname, lastname, averageMark, `groups`.numberGroup, payments.payment" +
                " from students" +
                " join `keys` on `keys`.`id_keys` = students.id_keys" +
                " left join `groups` on `groups`.idgroup = students.idgroup" +
                " left join payments on payments.idpayment = students.idpayment" +
                " where students.id_keys = " + r.getId();
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Students students = new Students();
        for (String[] items: result){
            students.setLogin(items[0]);
            students.setFirstname(items[1]);
            students.setLastname(items[2]);
            students.setAverageMark(items[3]);
            try {
                students.setGroup(Integer.parseInt(items[4]));
                students.setPayment(Integer.parseInt(items[5]));
            } catch (NumberFormatException e) {
                System.out.println("null");
            }
        }
        return students;
    }

    @Override
    public ArrayList<Students> getMarks(Role r) {
        String str = "select `keys`.login, firstname, lastname, averageMark, `groups`.numberGroup, payments.payment\n" +
                " from students\n" +
                " join `keys` on `keys`.`id_keys` = students.id_keys\n" +
                " left join `groups` on `groups`.idgroup = students.idgroup\n" +
                " left join payments on payments.idpayment = students.idpayment\n" +
                " where idteacher =" + r.getId() + ";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Students> studList = new ArrayList<>();
        for (String[] items: result){
            Students students = new Students();
            students.setLogin(items[0]);
            students.setFirstname(items[1]);
            students.setLastname(items[2]);
            students.setAverageMark(items[3]);
            try {
                students.setGroup(Integer.parseInt(items[4]));
                students.setPayment(Integer.parseInt(items[5]));
            } catch (NumberFormatException e) {
                System.out.println("null");
            }
            studList.add(students);
        }
        return studList;
    }

    @Override
    public Students getMark(Students s, Role r) {
        String proc = "{call get_marks(?,?,?,?,?)}";
        Students st = new Students();
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, r.getId());
            callableStatement.setString(2, s.getLogin());
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.execute();
            st.setAverageMark(callableStatement.getString(3));
            st.setColMarks(callableStatement.getInt(4));
            st.setLogin(String.valueOf(callableStatement.getString(5)));
            System.out.println(st.toString());
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    @Override
    public void setMarks(Students s) {
        String proc = "{call set_marks(?,?,?)}";
        Students st = new Students();
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, Integer.parseInt(s.getLogin()));
            callableStatement.setString(2, s.getAverageMark());
            callableStatement.setInt(3, s.getColMarks());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Students getGrNumber(Role r) {
        String proc = "{call get_grNumber(?,?)}";
        Students st = new Students();
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, r.getId());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            st.setGroup(callableStatement.getInt(2));
            System.out.println(st.toString());
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    @Override
    public ArrayList<Students> getProgress() {
        String str = "select `keys`.login, averageMark from students\n" +
                "    join `keys` on `keys`.id_keys = students.id_keys;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Students> studList = new ArrayList<>();
        for (String[] items: result){
            Students students = new Students();
            students.setLogin(items[0]);
            students.setAverageMark(items[1]);
            studList.add(students);
        }
        return studList;
    }

    @Override
    public Role insert(Students obj) {
        String proc = "{call insert_student(?,?,?,?,?)}";
        Role r = new Role();
        try (CallableStatement callableStatement = ConnectionDB.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getFirstname());
            callableStatement.setString(2, obj.getLastname());
            callableStatement.setString(3, obj.getLogin());
            callableStatement.setString(4, obj.getPassword());
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.execute();
            r.setId(callableStatement.getInt(5));
            r.setRole("student");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
