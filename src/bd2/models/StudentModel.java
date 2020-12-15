package bd2.models;

import bd2.App;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentModel extends UserModel {
    private ResultSet grades;

    public StudentModel(LoginModel prevModel) {
        super(prevModel.getId(), prevModel.getType());
    }

    public void fetchGrades() {
        try {
            Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            grades = st.executeQuery("select * from BD.STUDENT_GRADES_VIEW where student_id = " + this.id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ResultSet getGrades() {
        if (grades != null) {
            try {
                grades.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return grades;
    }
}
