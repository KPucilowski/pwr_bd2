package bd2.models;

public class StudentModel extends UserModel {
    public StudentModel(LoginModel prevModel) {
        super(prevModel.getId(), prevModel.getType());
    }
}
