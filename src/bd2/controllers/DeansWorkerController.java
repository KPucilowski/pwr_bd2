package bd2.controllers;

import bd2.App;
import bd2.controllers.dialogs.NewGroupController;
import bd2.controllers.dialogs.NewStudentController;
import bd2.models.DeansWorkerModel;
import bd2.models.LoginModel;
import bd2.views.WorkerView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DeansWorkerController implements IController {
    private final WorkerView view;
    private final DeansWorkerModel model;

    private int current_table_model;
    private int current_group;

    public DeansWorkerController(WorkerView view, LoginModel model) {
        this.view = view;
        this.model = new DeansWorkerModel(model);

        init();
    }

    public DeansWorkerController(WorkerView view, DeansWorkerModel model) {
        this.view = view;
        this.model = model;
        init();
    }

    @Override
    public void dispose() {
        view.dispose();
        App.reconnect();
        new LoginController();
    }

    @Override
    public void init() {
        showStudents();
        view.getIdField().setText(String.valueOf(model.getId()));
        view.getLogOutButton().addActionListener(e -> dispose());
        view.getStudentsButton().addActionListener(e -> showStudents());
        view.getGroupsButton().addActionListener(e -> showGroups());
        view.getAddButton().addActionListener(e -> addButtonListener());
        view.getEditButton().addActionListener(e -> editButtonListener());
        view.getRemoveButton().addActionListener(e -> removeButtonListener());
        view.getDataTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    switch (current_table_model) {
                        case 0 -> editStudent();
                        case 1 -> showGroup(getIdFromSelectedRow());
                    }
                }
            }
        });
    }

    private void addButtonListener() {
        switch (current_table_model) {
            case 0 -> addStudent();
            case 1 -> addGroup();
            case 2 -> addStudentToGroup();
        }
    }

    private void editButtonListener() {
        switch (current_table_model) {
            case 0 -> editStudent();
            case 1 -> editGroup();
        }
    }

    private void removeButtonListener() {
        switch (current_table_model) {
            case 0 -> removeStudent();
            case 1 -> removeGroup();
            case 2 -> removeStudentFromGroup();
        }
    }

    private void editStudent() {
    }

    private void editGroup() {
    }

    private void removeStudent() {
        var id = getIdFromSelectedRow();
        try {
            model.removeStudent(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void removeGroup() {
        var id = getIdFromSelectedRow();
        try {
            model.removeGroup(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void removeStudentFromGroup() {
        var id = getIdFromSelectedRow();
        try {
            model.removeStudentFromGroup(id, current_group);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addGroup() {
        NewGroupController ct = new NewGroupController();
        try {
            var group = ct.getModel();
            model.addGroup(group.group_id, group.subject_id, group.professor_id, group.parity, group.day, group.time, group.form, group.student_limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStudent() {
        NewStudentController ct = new NewStudentController();
        try {
            var student = ct.getModel();
            model.addStudent(student.firstName, student.lastName, student.facultyID, student.pesel);
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        showStudents();
    }

    private void addStudentToGroup() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        try {
            if (id != null && !id.equals(""))
                model.addStudentToGroup(Integer.parseInt(id), current_group);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void showStudents() {
        current_table_model = 0;
        setButtonsVisible();
        view.setTableModel(current_table_model);

        try {
            var rs = model.getStudents();
            while (rs.next()) {
                var first_name = rs.getString("FIRST_NAME");
                var last_name = rs.getString("LAST_NAME");
                var email = rs.getString("EMAIL");
                var faculty = rs.getString("FACULTY");
                var year = rs.getString("YEAR");
                var pesel = rs.getString("PESEL");
                var semester = rs.getString("SEMESTER");
                var specialization = rs.getString("SPECIALIZATION");
                view.getTableModel().addRow(new String[]{first_name, last_name, email, faculty, pesel, year, semester, specialization});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showGroup(int group_id) {
        current_table_model = 2;
        current_group = group_id;
        view.getEditButton().setVisible(false);
        view.setTableModel(current_table_model);

        try {
            var rs = model.getStudentsFromGroup(group_id);
            while (rs.next()) {
                var student_id = rs.getString("STUDENT_ID");
                var student = rs.getString("STUDENT");
                var email = rs.getString("EMAIL");
                var grade = rs.getString("GRADE");
                var rec_date = rs.getString("RECORD_DATE");
                view.getTableModel().addRow(new String[]{student_id, student, email, grade, rec_date});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showGroups() {
        current_table_model = 1;
        setButtonsVisible();
        view.setTableModel(current_table_model);
        try {
            var rs = model.getGroups();
            while (rs.next()) {
                var group_id = rs.getString("GROUP_ID");
                var subject_id = rs.getString("SUBJECT_ID");
                var subject_name = rs.getString("SUBJECT_NAME");
                var day = rs.getString("DAY");
                var time = rs.getString("TIME");
                var parity = rs.getString("PARITY");
                var form = rs.getString("FORM");
                view.getTableModel().addRow(new String[]{group_id, subject_id, subject_name, day, time, parity, form});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void setButtonsVisible() {
        view.getAddButton().setVisible(true);
        view.getEditButton().setVisible(true);
        view.getRemoveButton().setVisible(true);
    }

    private int getIdFromSelectedRow() {
        var test = view.getDataTable().getSelectedRow();
        return Integer.parseInt((String) view.getTableModel().getValueAt(test, 0));
    }
}