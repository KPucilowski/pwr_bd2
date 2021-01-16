package test;

import bd2.models.LoginModel;
import bd2.models.StudentModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class StudentTest {
    private static StudentModel model;

    @Test
    @BeforeAll
    static void login() throws SQLException {
        var loginModel = new LoginModel(200001, "STUDENT");
        loginModel.login("1", "1");
        model = new StudentModel(loginModel);
    }
}
