package DB;

import SchoolOrg.Role;
import SchoolOrg.Students;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLStudentsTest {

    @Test
    void getGrNumber() throws SQLException, ClassNotFoundException {
        SQLFactory sqlFactory = new SQLFactory();
        Role role = new Role();
        role.setId(32);
        Students objStudents = sqlFactory.getStudents().getGrNumber(role);
        assertEquals(100, objStudents.getGroup());
    }
}