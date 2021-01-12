package bd2.controllers.dialogs;

import bd2.models.dialogs.NewStudentModel;
import bd2.views.dialogs.NewStudentView;

public class NewStudentController {
    private final NewStudentView view;
    private NewStudentModel model;

    public NewStudentController() {
        this.view = new NewStudentView();

        init();
    }

    private void init() {
        view.getOkButton().addActionListener(e -> getData());
        view.getCancelButton().addActionListener(e -> view.dispose());

        view.setModal(true);
        view.pack();
        view.setVisible(true);
    }

    private void getData() {
        model = new NewStudentModel();
        model.firstName = view.getTxtFirstName().getText();
        model.lastName = view.getTxtLastName().getText();
        model.pesel = view.getTxtPESEL().getText();

        view.dispose();
    }

    public NewStudentModel getModel() {
        return model;
    }
}
