package bd2.models;

import bd2.App;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentModel extends UserModel {
    private ResultSet grades;
    private ResultSet records;

    public StudentModel(LoginModel prevModel) {
        super(prevModel.getId(), prevModel.getType());
    }

    public ResultSet getRecords() throws SQLException {
        if (records != null)
            records.beforeFirst();
        else fetchRecords();

        return records;
    }

    public ResultSet getGrades() throws SQLException {
        if (grades != null)
            grades.beforeFirst();
        else
            fetchGrades();

        return grades;
    }

    private void fetchRecords() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        records = st.executeQuery("select * from BD.RECORD_VIEW where student_id = " + this.id);
    }

    private void fetchGrades() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        grades = st.executeQuery("select * from BD.STUDENT_GRADES_VIEW where student_id = " + this.id);
    }
}
