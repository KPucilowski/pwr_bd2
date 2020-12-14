package bd2.models;

import bd2.App;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserModel extends LoginModel {
    private ResultSet personalData;

    public UserModel(int id, String type) {
        super(id, type);
    }

    public ResultSet getPersonalData() {
        if (personalData != null) {
            try {
                personalData.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return personalData;
    }

    public void fetchPersonalData() {
        try {
            Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            personalData = st.executeQuery("SELECT * FROM BD." + getType() + "_VIEW WHERE STUDENT_ID = " + getId());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
