package DB;

import SchoolOrg.Groups;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SQLGroupsTest {

    @Test
    void find() throws SQLException, ClassNotFoundException {
        SQLFactory sqlFactory = new SQLFactory();
        Groups objGroups = new Groups();
        objGroups.setGrNumber(100);
        ArrayList<Groups> gr = sqlFactory.getGroups().find(objGroups);
        assertEquals(45, gr.get(0).getClassNumber());
    }
}