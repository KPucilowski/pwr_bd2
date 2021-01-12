package bd2.controllers.dialogs;

import bd2.models.dialogs.NewStudentModel;
import bd2.views.dialogs.NewStudentView;

import java.sql.SQLException;

public class NewStudentController {
    private final NewStudentView view;
    private NewStudentModel model;
    private final String faculty_id;

    public NewStudentController(String faculty_id) {
        this.view = new NewStudentView();
        this.faculty_id = faculty_id;

        init();
    }

    private void init() {
        view.getOkButton().addActionListener(e -> getData());
        view.getCancelButton().addActionListener(e -> view.dispose());

        try {
            var rs = NewStudentModel.getSpecsOfFaculty(faculty_id);
            while (rs.next()) {
                view.getSpecializationCbx().addItem(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        view.setModal(true);
        view.pack();
        view.setVisible(true);
    }

    private void getData() {
        model = new NewStudentModel();

        model.firstName = view.getTxtFirstName().getText();
        model.lastName = view.getTxtLastName().getText();
        var specialization_name = (String) view.getSpecialization();
        try {
            model.specialization_id = model.findSpecIdByName(specialization_name, faculty_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.pesel = view.getTxtPESEL().getText();

        view.dispose();
    }

    public NewStudentModel getModel() {
        return model;
    }
}
