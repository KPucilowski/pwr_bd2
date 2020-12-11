package bd2.controllers;

import bd2.models.LoginModel;
import bd2.models.StudentModel;
import bd2.views.StudentView;

public class StudentController implements IController {
    private final StudentView view;
    private final StudentModel model;

    public StudentController(StudentView view, LoginModel model) {
        this.view = view;
        this.model = new StudentModel(model);

        init();
    }

    public StudentController(StudentView view, StudentModel model) {
        this.view = view;
        this.model = model;

        init();
    }

    @Override
    public void init() {
        model.fetchPersonalData();
        view.getIdField().setText(String.valueOf(model.getId()));
        view.getPersonalDataButton().addActionListener(e -> updatePersonalData());
    }

    private void updatePersonalData() {
        if (model.getPersonalData() == null)
            model.fetchPersonalData();
    }
}
