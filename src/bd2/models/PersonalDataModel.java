package bd2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalDataModel {
    public String firstName;
    public String lastName;
    public String email;
    public String facultyName;

    PersonalDataModel(ResultSet rs) throws SQLException {
        while(rs.next()) {
            this.firstName = rs.getString(2);
            this.lastName = rs.getString(3);
            this.email = rs.getString(4);
            this.facultyName = rs.getString(5);
        }
    }
}
