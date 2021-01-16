package test;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.StudentModel;
import bd2.tools.LoginTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class StudentTest {
    private StudentModel model;

    @BeforeEach
    void login() throws SQLException, NoSuchAlgorithmException {
        App.reconnect();
        var loginModel = new LoginModel("STUDENT");
        var pass = LoginTools.charToSha256(new char[]{'1'});
        loginModel.login("1", pass);
        model = new StudentModel(loginModel);
        App.cn = DriverManager.getConnection(
                "jdbc:oracle:thin:@146.59.17.101:1521:XE", "STUDENT", "pass");
    }

    @Test
    public void testPersonalData() throws SQLException {
        var rs = model.getPersonalData();

        var id = 200001;
        var first_name = "Alice";
        var last_name = "Leroux";
        var faculty = "Wydział 1";

        int rs_id = 0;
        String rs_first_name = "", rs_last_name = "", rs_faculty = "";

        if (rs.next()) {
            rs_id = rs.getInt("STUDENT_ID");
            rs_first_name = rs.getString("FIRST_NAME");
            rs_last_name = rs.getString("LAST_NAME");
            rs_faculty = rs.getString("FACULTY");
        }

        assertEquals(id, rs_id);
        assertEquals(first_name, rs_first_name);
        assertEquals(last_name, rs_last_name);
        assertEquals(faculty, rs_faculty);
    }

    @Test
    public void testAvgGrade() throws SQLException {
        double rs_avg_grade = 0f;

        var rs = model.getPersonalData();

        if (rs.next())
            rs_avg_grade = rs.getDouble("AVG_GRADE");

        ArrayList<Float> grades = new ArrayList<>();
        rs = model.getGrades();
        while (rs.next()) {
            var grade = rs.getFloat("GRADE");
            grades.add(grade);
        }

        float sum = 0f;
        for (Float grade : grades) sum += grade;
        double avg_grade = sum / grades.size();

        assertEquals(rs_avg_grade, avg_grade);
    }
}
