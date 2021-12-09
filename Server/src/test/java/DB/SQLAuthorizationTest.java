package DB;

import SchoolOrg.Authorization;
import SchoolOrg.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLAuthorizationTest {

    @Test
    void TestGetRole() throws SQLException, ClassNotFoundException {
        SQLFactory sqlFactory = new SQLFactory();
        Authorization objAuthorization = new Authorization();
        objAuthorization.setLogin("julianna");
        objAuthorization.setPassword("12345");
        Role r = sqlFactory.getRole().getRole(objAuthorization);
        Assertions.assertTrue(r.getRole().equals("student"));
    }
}