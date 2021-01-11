package bd2.controllers;

import bd2.App;
import bd2.models.LoginModel;
import bd2.models.DeansWorkerModel;
import bd2.views.NewGroupView;
import bd2.views.WorkerView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DeansWorkerController implements IController {
    private final WorkerView view;
    public  NewGroupView newGroupView;
    private final DeansWorkerModel model;
    public int group_id;
    public int student_id;
    public int choose;
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
        view.getIdField().setText(String.valueOf(model.getId()));
        view.getLogOutButton().addActionListener(e -> dispose());
        view.getAddStudentsToListButton().addActionListener(e -> addStudentToList());
        view.getAddStudentToGroupButton().addActionListener(e -> addStudentToGroup());
        view.getDeleteStudentsFromListButton().addActionListener(e -> deleteStudentsFromList());
        view.getDeleteStudentFromGroupButton().addActionListener(e -> deleteStudentsFromGroup());
        view.getDeleteGroupButton().addActionListener(e -> deleteGroup());
        view.getEditGroupButton().addActionListener(e -> editGroup());
        view.getOpenNewGroupButton().addActionListener(e -> openNewGroup());
        view.getAccept_button().addActionListener(e->accept());
        view.getAddStudentToGroup().addActionListener(e->accept_modification());
        view.getFirst_name().setVisible(false);
        view.getLast_name().setVisible(false);
        view.getFaculty().setVisible(false);
        view.getPesel().setVisible(false);
        view.getPeselField().setVisible(false);
        view.getLastNameField().setVisible(false);
        view.getFacultyField().setVisible(false);
        view.getFirstNameField().setVisible(false);
        view.getAccept_button().setVisible(false);
        view.getIDStudenta().setVisible(false);
        view.getAddStudentToGroup().setVisible(false);
    }
    public void notVisible(){
        view.getFirst_name().setVisible(false);
        view.getLast_name().setVisible(false);
        view.getFaculty().setVisible(false);
        view.getPesel().setVisible(false);
        view.getPeselField().setVisible(false);
        view.getLastNameField().setVisible(false);
        view.getFacultyField().setVisible(false);
        view.getFirstNameField().setVisible(false);
        view.getAccept_button().setVisible(false);
        view.getIDStudenta().setVisible(false);
        view.getAddStudentToGroup().setVisible(false);
    }
    private void accept_modification() {
        notVisible();

        student_id = Integer.parseInt(view.getFirstNameField().getText());
        System.out.println(student_id);
        if(choose==1) {
            view.getAddStudentToGroup().setVisible(false);
            view.getIDStudenta().setVisible(false);
            view.getFirstNameField().setVisible(false);
            view.dataTable.setModel(view.tableModel);

            view.getTableModel().setRowCount(0);
            view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Day", "Time", "Parity", "Form"});
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

            view.dataTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (me.getClickCount() == 2) {
                        JTable target = (JTable) me.getSource();
                        String string_group_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                        int clicked_group_id = Integer.parseInt(string_group_id);
                        System.out.println(student_id + " " + clicked_group_id);
                        try {
                            model.addStudentToGroup(student_id, clicked_group_id);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            });
        }
        if(choose==2){
            System.out.println(student_id +" "+ group_id);
            try {
                model.removeStudentFromGroup(student_id,group_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void accept() {
        String first_name;
        String last_name;
        String faculty_id;
        String pesel;
        first_name = view.getFirstNameField().getText();
        last_name = view.getLastNameField().getText();
        faculty_id = view.getFacultyField().getText();
        pesel = view.getPeselField().getText();
        try {
            model.addStudent(first_name,last_name,faculty_id,pesel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        view.getFirst_name().setVisible(false);
        view.getLast_name().setVisible(false);
        view.getFaculty().setVisible(false);
        view.getPesel().setVisible(false);
        view.getPeselField().setVisible(false);
        view.getLastNameField().setVisible(false);
        view.getFacultyField().setVisible(false);
        view.getFirstNameField().setVisible(false);
        view.getAccept_button().setVisible(false);

    }

    private void editGroup() {
        notVisible();
        view.dataTable.setModel(view.tableModel);

        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Professor id", "Parity", "Day", "Time", "Form", "Student limit"});
        try {
            var rs = model.getGroups();
            while (rs.next()) {
                var group_id = rs.getString("GROUP_ID");
                var subject_id = rs.getString("SUBJECT_ID");
                var professor_id = rs.getString("PROFESSOR_ID");
                var parity = rs.getString("PARITY");
                var day = rs.getString("DAY");
                var time = rs.getString("TIME");
                var form = rs.getString("FORM");
                var student_limit = rs.getString("STUDENT_LIMIT");
                view.getTableModel().addRow(new String[]{group_id, subject_id, professor_id, parity, day, time, form, student_limit});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    NewGroupView newGroupViewX = new NewGroupView();
                    newGroupView = newGroupViewX;
                    newGroupView.getOkButton().addActionListener(e->editSelGroup());
                    String string_group_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    newGroupView.getTxtGroupID().setText(string_group_id);
                    String string_subject_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 1);
                    newGroupView.getTxtSubjectID().setText(string_subject_id);
                    String string_professor_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 2);
                    newGroupView.getTxtProfessorID().setText(string_professor_id);
                    String parity = (String) view.dataTable.getValueAt(target.getSelectedRow(), 3);
                    newGroupView.getTxtParity().setText(parity);
                    String string_day = (String) view.dataTable.getValueAt(target.getSelectedRow(), 4);
                    newGroupView.getTxtDay().setText(string_day);
                    String time = (String) view.dataTable.getValueAt(target.getSelectedRow(), 5);
                    newGroupView.getTxtTime().setText(time);
                    String form = (String) view.dataTable.getValueAt(target.getSelectedRow(), 6);
                    newGroupView.getTxtForm().setText(form);
                    String string_student_limit = (String) view.dataTable.getValueAt(target.getSelectedRow(), 7);
                    newGroupView.getTxtStudentLimit().setText(string_student_limit);
                }
            }
        });
    }

    public void editSelGroup()
    {
        String string_group_id = newGroupView.getTxtGroupID().getText();
        String string_subject_id = newGroupView.getTxtSubjectID().getText();
        String string_professor_id = newGroupView.getTxtProfessorID().getText();
        String parity = newGroupView.getTxtParity().getText();
        String string_day = newGroupView.getTxtDay().getText();
        String time = newGroupView.getTxtTime().getText();
        String form = newGroupView.getTxtForm().getText();
        String string_student_limit = newGroupView.getTxtStudentLimit().getText();
        int group_id = Integer.parseInt(string_group_id);
        int subject_id = Integer.parseInt(string_subject_id);
        int professor_id = Integer.parseInt(string_professor_id);
        int student_limit = Integer.parseInt(string_student_limit);
        int day = Integer.parseInt(string_day);
        try {
            //TUTAJ SQL!!!!!!!!!
            //TUTAJ SQL!!!!!!!!!
            model.addGroup(group_id, subject_id, professor_id,parity, day, time, form, student_limit);//TUTAJ SQL!!!!!!!!!
            //TUTAJ SQL!!!!!!!!!
            //TUTAJ SQL!!!!!!!!!
            //TUTAJ SQL!!!!!!!!!
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void deleteGroup() {
        notVisible();
        view.dataTable.setModel(view.tableModel);

        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Day", "Time", "Parity", "Form"});
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

        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_group_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int cliked_group_id = Integer.parseInt(string_group_id);
                    System.out.println(cliked_group_id);
                    try {
                        model.removeGroup(cliked_group_id);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }


    private void deleteStudentsFromGroup() {
        notVisible();
        view.dataTable.setModel(view.tableModel);
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Day", "Time", "Parity", "Form"});
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

        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_group_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    group_id = Integer.parseInt(string_group_id);
                    System.out.println(group_id);

                    showStudentsFromGroup();
                }
            }
        });
    }
    private void showStudentsFromGroup() {
        notVisible();
        view.getTableModel2().setRowCount(0);
        view.dataTable.setModel(view.tableModel2);
        view.getTableModel2().setColumnIdentifiers(new String[]{"Student ID", "Group ID","Grade", "Student name", "Email"});
        try {
            var rs = model.getStudents(group_id);
            while (rs.next()) {
                var student_id = rs.getString("STUDENT_ID");
                var group_id = rs.getString("GROUP_ID");
                var student = rs.getString("STUDENT");
                var email = rs.getString("EMAIL");
                var grade = rs.getString("GRADE");
                view.getTableModel2().addRow(new String[]{student_id, group_id, grade, student, email});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        view.getIDStudenta().setVisible(true);
        view.getFirstNameField().setVisible(true);
        view.getAddStudentToGroup().setVisible(true);
        choose=2;
    }


    private void deleteStudentsFromList() {
        notVisible();
        view.dataTable.repaint();
        view.getTableModel2().setRowCount(0);
        view.dataTable.setModel(view.tableModel2);
        view.getTableModel2().setColumnIdentifiers(new String[]{"Student ID", "Group ID","Grade", "Student name", "Email"});
        try {
            var rs = model.getAllStudents();
            while (rs.next()) {
                var student_id = rs.getString("STUDENT_ID");
                var group_id = rs.getString("GROUP_ID");
                var student = rs.getString("STUDENT");
                var email = rs.getString("EMAIL");
                var grade = rs.getString("GRADE");
                view.getTableModel2().addRow(new String[]{student_id, group_id, grade, student, email});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_student_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int clicked_student_id = Integer.parseInt(string_student_id);
                    System.out.println(clicked_student_id);
                    try {

                        model.removeStudent(clicked_student_id);

                    } catch (SQLException throwables) {

                        throwables.printStackTrace();

                    }
                }
            }
        });
    }




    private void addStudentToGroup() {
        notVisible();
        view.getIDStudenta().setVisible(true);
        view.getFirstNameField().setVisible(true);
        view.getAddStudentToGroup().setVisible(true);
        choose=1;


    }

    private void showGroups(){
        view.dataTable.setModel(view.tableModel);
        view.getTableModel().setRowCount(0);
        view.getTableModel().setColumnIdentifiers(new String[]{"Group ID", "Subject ID", "Subject name", "Day", "Time", "Parity", "Form"});
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

        view.dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JTable target = (JTable)me.getSource();
                    String string_group_id = (String) view.dataTable.getValueAt(target.getSelectedRow(), 0);
                    int clicked_group_id = Integer.parseInt(string_group_id);
                    System.out.println(clicked_group_id);
                    group_id = clicked_group_id;
                    try {
                        model.addStudentToGroup(student_id,group_id);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
    private void addStudentToList() {
        notVisible();
        view.getFirst_name().setVisible(true);
        view.getLast_name().setVisible(true);
        view.getFaculty().setVisible(true);
        view.getPesel().setVisible(true);
        view.getAccept_button().setVisible(true);
        view.getLastNameField().setVisible(true);
        view.getFirstNameField().setVisible(true);
        view.getFacultyField().setVisible(true);
        view.getPeselField().setVisible(true);
    }

    private void openNewGroup() {
        notVisible();
        NewGroupView newGroupViewX = new NewGroupView();
        newGroupView = newGroupViewX;
        this.newGroupView.getOkButton().addActionListener(e->makeNewGroup());
    }

    public void makeNewGroup()
    {
        String string_group_id = newGroupView.getTxtGroupID().getText();
        String string_subject_id = newGroupView.getTxtSubjectID().getText();
        String string_professor_id = newGroupView.getTxtProfessorID().getText();
        String parity = newGroupView.getTxtParity().getText();
        String string_day = newGroupView.getTxtDay().getText();
        String time = newGroupView.getTxtTime().getText();
        String form = newGroupView.getTxtForm().getText();
        String string_student_limit = newGroupView.getTxtStudentLimit().getText();
        int group_id = Integer.parseInt(string_group_id);
        int subject_id = Integer.parseInt(string_subject_id);
        int professor_id = Integer.parseInt(string_professor_id);
        int student_limit = Integer.parseInt(string_student_limit);
        int day = Integer.parseInt(string_day);
        try {
            model.addGroup(group_id, subject_id, professor_id,parity, day, time, form, student_limit);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}