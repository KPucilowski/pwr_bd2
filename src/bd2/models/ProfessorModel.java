package bd2.models;

import bd2.App;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorModel extends UserModel {
    private ResultSet groups;
    private ResultSet groupsStudent;

    public ProfessorModel(int id, String type) {
        super(id, type);
    }

    public ProfessorModel(LoginModel loginModel) {
        super(loginModel.getId(), loginModel.getType());
    }

    public void removeGrade(int student_id, int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.REMOVE_GRADE(?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.execute();
    }

    /* Can also use to edit grade */
    public void addGrade(int student_id, int group_id, Double grade) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.ADD_GRADE(?, ?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.setDouble(3, grade);
        stmt.execute();
    }

    public ResultSet getStudents(int clicked_group_id) throws SQLException {
        fetchStudents(clicked_group_id);
        return groupsStudent;
    }

    private void fetchStudents(int clicked_group_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        groupsStudent = st.executeQuery("select * from BD.GROUP_STUDENTS_VIEW where professor_id = " + this.id  + "and group_id = " + clicked_group_id);
    }


    public ResultSet getStudentsGrades() throws SQLException {
        fetchStudentsGrades();
        return groupsStudent;
    }

    private void fetchStudentsGrades() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        groupsStudent = st.executeQuery("select * from BD.GROUP_STUDENTS_VIEW where professor_id = " + this.id);
    }

    public ResultSet getGroups() throws SQLException {
        fetchGroups();
        return groups;
    }

    private void fetchGroups() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        groups = st.executeQuery("select * from BD.GROUP_VIEW where professor_id = " + this.id);
    }
}