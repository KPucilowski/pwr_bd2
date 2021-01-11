package bd2.controllers.dialogs;

import bd2.models.dialogs.NewGroupModel;
import bd2.views.dialogs.NewGroupView;

public class NewGroupController {
    private final NewGroupView view;
    private final NewGroupModel model;

    public NewGroupController() {
        this.view = new NewGroupView();
        this.model = new NewGroupModel();

        init();
    }

    private void init() {
        view.getOkButton().addActionListener(e -> getData());
        view.getCancelButton().addActionListener(e -> view.dispose());
    }

    public NewGroupModel getModel() {
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
