package bd2.controllers;

import bd2.models.GroupModel;
import bd2.models.LoginModel;
import bd2.views.LoginView;
import bd2.views.NewGroupView;

public class GroupController {
    private final NewGroupView view;
    private final GroupModel model;

    public GroupController() {
        this.view = new NewGroupView();
        this.model = new GroupModel();

        init();
    }

    private void init() {
        view.getOkButton().addActionListener(e -> getData());
        view.getCancelButton().addActionListener(e -> view.dispose());
    }

    public GroupModel getModel() {
        return model;
    }

    private void getData() {
        model.group_id = Integer.parseInt(view.getTxtGroupID().getText());
        model.professor_id = Integer.parseInt(view.getTxtProfessorID().getText());
        model.subject_id = Integer.parseInt(view.getTxtSubjectID().getText());
        model.parity = view.getTxtParity().getText();
        model.day = Integer.parseInt(view.getTxtDay().getText());
        model.time = view.getTxtTime().getText();
        model.form = view.getTxtForm().getText();
        model.student_limit = Integer.parseInt(view.getTxtStudentLimit().getText());

        view.dispose();
    }
}
