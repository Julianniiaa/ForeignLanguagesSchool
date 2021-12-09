package DB;

import SchoolOrg.Authorization;
import SchoolOrg.Role;

public interface ISQLAuthorization {
    Role getRole(Authorization obj);
}
