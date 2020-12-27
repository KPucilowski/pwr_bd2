package bd2.models;

import bd2.App;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorModel extends UserModel {
    private ResultSet groups;

    public ProfessorModel(int id, String type) {
        super(id, type);
    }

    public ProfessorModel(LoginModel loginModel) {
        super(loginModel.getId(), loginModel.getType());
    }

    private void removeGrade(int student_id, int group_id) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.REMOVE_GRADE(?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.execute();
    }

    /* Can also use to edit grade */
    private void addGrade(int student_id, int group_id, double grade) throws SQLException {
        CallableStatement stmt = App.cn.prepareCall("{call BD.ADD_GRADE(?, ?, ?)}");
        stmt.setInt(1, student_id);
        stmt.setInt(2, group_id);
        stmt.setDouble(3, grade);
        stmt.execute();
    }

    public ResultSet getGroupStudents(int group_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from BD.GROUP_STUDENT_VIEW where professor_id = " + this.id + " group_id = " + group_id);
    }

    public ResultSet getGroups() throws SQLException {
        if (groups != null)
            groups.beforeFirst();
        else
            fetchGroups();

        return personalData;
    }

    private void fetchGroups() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        groups = st.executeQuery("select * from BD.GROUP_VIEW where professor_id = " + this.id);
    }
}
