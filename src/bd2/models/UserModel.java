package bd2.models;

import bd2.App;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserModel extends LoginModel {
    private ResultSet personalData;

    public UserModel(int id, String type) {
        super(id, type);
    }

    public ResultSet getPersonalData() throws SQLException {
        if (personalData != null)
            personalData.beforeFirst();
        else
            fetchPersonalData();

        return personalData;
    }

    private void fetchPersonalData() throws SQLException {
        Statement st = App.cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        personalData = st.executeQuery("SELECT * FROM BD." + getType() + "_VIEW WHERE STUDENT_ID = " + getId());
    }
}
