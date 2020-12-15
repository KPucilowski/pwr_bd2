package bd2.models;

import bd2.App;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentModel extends UserModel {
    private ResultSet grades;
    private ResultSet records;

    public StudentModel(LoginModel prevModel) {
        super(prevModel.getId(), prevModel.getType());
    }

    public ResultSet getRecords() {
        if (records != null) {
            try {
                records.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return records;
    }

    public void fetchRecords() {
        try {
            Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            records = st.executeQuery("select * from BD.RECORD_VIEW where student_id = " + this.id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
