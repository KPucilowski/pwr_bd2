package bd2.models;

import bd2.App;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserModel extends LoginModel {
    private ResultSet personalData;

    public UserModel(int id, String type) {
        super(id, type);
    }

    public ResultSet getPersonalData() {
        return personalData;
    }

    public void fetchPersonalData() {
        try {
            // for some reason PreparedStatement doesn't work
            String sql_st = String.format("select * from BD.%s join BD.PERSONAL_DATA on user_id = %s_id where user_id = %d", getType(), getType(), getId());
            Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            personalData = st.executeQuery(sql_st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
