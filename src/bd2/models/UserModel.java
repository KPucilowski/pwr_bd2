package bd2.models;

public class UserModel {
    private int id;
    private String type;

    public UserModel(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public UserModel(String type) {
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
