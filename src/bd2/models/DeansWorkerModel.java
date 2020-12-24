package bd2.models;

import bd2.App;
import bd2.tools.LoginTools;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;


public class DeansWorkerModel extends UserModel {
    public DeansWorkerModel(int id, String type) {
        super(id, type);
    }

    private void addGroup(
            int group_id,
            int subject_id,
            int professor_id,
            String parity,
            DayOfWeek day,
            String time,
            String form,
            int student_limit
    ) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.ADD_GROUP(?, ?, ?, ?, ?, ?, ?, ?)}");
        stmt.setInt(1, group_id);
        stmt.setInt(2, subject_id);
        stmt.setInt(3, professor_id);
        stmt.setString(4, parity);
        stmt.setInt(5, day.getValue());
        stmt.setString(6, time);
        stmt.setString(7, form);
        stmt.setInt(8, student_limit);
        stmt.execute();
    }

    public ResultSet getGroups() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from BD.GROUP_VIEW");
    }

    public ResultSet getSubjects() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from BD.SUBJECT");
    }

    /* Use to find professors that you can assign to subject/group */
    public ResultSet getProfessorsOfFaculty(String faculty_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from BD.PROFESSOR_VIEW where FACULTY_ID = " + faculty_id);
    }

    private void addStudent(
            String first_name,
            String last_name,
            String faculty_id,
            String pesel
    ) throws SQLException, NoSuchAlgorithmException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.ADD_STUDENT(?, ?, ?, ?, ?, ?)}");

        var randLogin = LoginTools.randomString(10);
        var randPassword = LoginTools.randomPassword(10);

        stmt.setString(1, first_name);
        stmt.setString(2, last_name);
        stmt.setString(3, faculty_id);
        stmt.setString(4, pesel);
        stmt.setString(5, randLogin);
        stmt.setString(6, randPassword);
        stmt.execute();
    }

    private void removeStudent(int student_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.REMOVE_STUDENT(?)}");
        stmt.setInt(1, student_id);
        stmt.execute();
    }

    private void closeGroup(int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.CLOSE_GROUP(?)}");
        stmt.setInt(1, group_id);
        stmt.execute();
    }

    private void addStudentToGroup(int student_id, int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.ADD_STUDENT_TO_GROUP(?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.execute();
    }

    private void removeStudentFromGroup(int student_id, int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.REMOVE_STUDENT_FROM_GROUP(?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.execute();
    }
}
