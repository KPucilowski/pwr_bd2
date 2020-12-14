package bd2.models;

public class LoginModel {
    public int id;
    public String type;

    public LoginModel(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public LoginModel(String type) {
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}