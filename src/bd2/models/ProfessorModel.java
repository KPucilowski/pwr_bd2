package bd2.models;

import bd2.App;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorModel extends UserModel {
    public ProfessorModel(int id, String type) {
        super(id, type);
    }

    public ResultSet getGroupStudents(int group_id) throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery("select * from BD.GROUP_STUDENT_VIEW where professor_id = " + this.id + " group_id = " + group_id);
    }
}
