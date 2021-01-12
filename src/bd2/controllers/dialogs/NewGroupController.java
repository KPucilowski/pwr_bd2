package bd2.controllers.dialogs;

import bd2.models.dialogs.NewGroupModel;
import bd2.views.dialogs.NewGroupView;


public class NewGroupController {
    private final NewGroupView view;
    private NewGroupModel model;

    public NewGroupController() {
        this.view = new NewGroupView();

        init();
    }

    public NewGroupController(String group_id, String subject_id, String professor_id, String parity, String day, String time, String form, String student_limit) {
        this.view = new NewGroupView();

        init(group_id, subject_id, professor_id, parity, day, time, form, student_limit);
    }

    private void init() {
        view.getOkButton().addActionListener(e -> getData());
        view.getCancelButton().addActionListener(e -> view.dispose());

        view.setModal(true);
        view.pack();
        view.setVisible(true);
    }

    private void init(String group_id, String subject_id, String professor_id, String parity, String day, String time, String form, String student_limit) {
        view.getTxtGroupID().setText(group_id);
        view.getTxtSubjectID().setText(subject_id);
        view.getTxtProfessorID().setText(professor_id);
        view.getTxtParity().setText(parity);
        view.getTxtDay().setText(day);
        view.getTxtTime().setText(time);
        view.getTxtForm().setText(form);
        view.getTxtStudentLimit().setText(student_limit);

        init();
    }

    public NewGroupModel getModel() {
        return model;
    }

    private void getData() {
        model = new NewGroupModel();

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
