package bd2.controllers;

import bd2.models.UserModel;
import bd2.views.StudentView;

public class StudentController implements IController{
    private final StudentView view;
    private final UserModel model;

    public StudentController(StudentView view, UserModel model) {
        this.view = view;
        this.model = model;

        init();
    }

    @Override
    public void init() {
        view.getSTUDENT_IDTextField().setText(String.valueOf(model.getId()));
    }
}
