package DB;

import SchoolOrg.Receive;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLReceive implements ISQLReceive {
    private static SQLReceive instance;
    private ConnectionDB dbConnection;

    private SQLReceive() throws SQLException, ClassNotFoundException {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLReceive getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLReceive();
        }
        return instance;
    }

    public ArrayList<Receive> get() {
        String str = "select sum(payments.balance) as receive, languages.`language`\n" +
                "    from students\n" +
                "    join `groups` on `groups`.idgroup = students.idgroup\n" +
                "    join `courses` on `courses`.idcourse = `groups`.idcourse\n" +
                "    join languages on `languages`.idlanguage = courses.idlanguage\n" +
                "    join `payments` on `payments`.idpayment = students.idpayment\n" +
                "    group by languages.`language`";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Receive> infList = new ArrayList<>();
        for (String[] items: result){
            Receive receive = new Receive();
            receive.setBalance(Integer.parseInt(items[0]));
            receive.setLanguage(items[1]);
            infList.add(receive);
        }
        return infList;
    }

    public ArrayList<Receive> getChart() {
        String str = "select sum(payments.balance) as receive, languages.`language`\n" +
                "    from students\n" +
                "    join `groups` on `groups`.idgroup = students.idgroup\n" +
                "    join `courses` on `courses`.idcourse = `groups`.idcourse\n" +
                "    join languages on `languages`.idlanguage = courses.idlanguage\n" +
                "    join `payments` on `payments`.idpayment = students.idpayment\n" +
                "    group by languages.`language`";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Receive> infList = new ArrayList<>();
        for (String[] items: result){
            Receive receive = new Receive();
            receive.setBalance(Integer.parseInt(items[0]));
            receive.setLanguage(items[1]);
            infList.add(receive);
        }
        return infList;
    }
}
