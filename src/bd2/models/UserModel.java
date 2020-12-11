package bd2.models;

import bd2.App;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserModel extends LoginModel {
    private PersonalDataModel personalData;

    public UserModel(int id, String type) {
        super(id, type);
    }

    public PersonalDataModel getPersonalData() {
        return personalData;
    }

    public void fetchPersonalData() {
        try {
            Statement st = App.cn.createStatement();
            ResultSet rs = st.executeQuery("select * from BD.PERSONAL_DATA pd where pd.user_id = " + this.id);
            personalData = new PersonalDataModel(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
