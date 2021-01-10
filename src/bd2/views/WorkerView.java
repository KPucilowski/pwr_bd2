package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.DayOfWeek;

import bd2.models.DeansWorkerModel;


public class WorkerView extends Component {
    
    private final JFrame frame;
    private JLabel loggedAsLabel;
    private JButton logOutButton;
    private JTextField idField;
    private JButton showButton;
    private JScrollPane scrollPane;
    public JTable dataTable;
    private JButton editGroupButton;
    private JButton deleteGroupButton;
    private JButton openNewGroupButton;
    private JButton addStudentToGroupButton;
    private JButton deleteStudentFromGroupButton;
    private JButton addStudentsToListButton;
    private JButton deleteStudentsFromListButton;
    private JPanel workerPanel;
    private JTextField FirstNameField;
    private JTextField LastNameField;
    private JTextField FacultyField;
    private JTextField PeselField;
    private JLabel first_name;
    private JButton accept_button;
    private JLabel last_name;
    private JLabel faculty;
    private JLabel pesel;
    private JLabel IDStudenta;
    private JButton addStudentToGroup;
    public DefaultTableModel tableModel = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        };
    };
    public DefaultTableModel tableModel2 = new DefaultTableModel()
    {
        @Override
        public boolean isCellEditable(int row, int column){
            return column == 2;
        };
    };
    public WorkerView(){

        frame = new JFrame("Deans Worker menu");
        frame.setContentPane(workerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dataTable.setModel(tableModel);
    }
    public void dispose(){
        frame.dispose();
    }
    public JTextField getIdField() {
        return idField;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    public JTextField getFirstNameField(){return FirstNameField;}
    public JTextField getLastNameField(){return LastNameField;}
    public JTextField getFacultyField(){return FacultyField;}
    public JTextField getPeselField(){return PeselField;}
    public JLabel getFirst_name(){return first_name;}
    public JLabel getLast_name(){return last_name;}
    public JLabel getFaculty(){return faculty;}
    public JLabel getPesel(){return pesel;}
    public JButton getAccept_button(){return accept_button;}
    public JButton getLogOutButton(){return logOutButton;}
    public JButton getEditGroupButton(){return editGroupButton;}
    public JButton getDeleteGroupButton(){return deleteGroupButton;}
    public JButton getOpenNewGroupButton(){return openNewGroupButton;}
    public JButton getAddStudentToGroupButton(){return addStudentToGroupButton;}
    public JButton getDeleteStudentFromGroupButton(){return deleteStudentFromGroupButton;}
    public JButton getAddStudentsToListButton(){return addStudentsToListButton;}
    public JButton getDeleteStudentsFromListButton(){return deleteStudentsFromListButton;}
    public JButton getAddStudentToGroup(){return addStudentToGroup;}
    public JLabel getIDStudenta(){return IDStudenta;}
    public DefaultTableModel getTableModel2() {
        return tableModel2;
    }

    
    public static class addNewGroupView extends Frame implements ActionListener {
        private JButton okButton = new JButton("Ok");;
        private JButton cancelButton = new JButton("Cancel");;

        private JLabel lblGroupID = new JLabel("Group ID:");
        private JLabel lblSubjectID = new JLabel("Subject ID:");
        private JLabel lblProfessorID = new JLabel("Professor ID:");
        private JLabel lblParity = new JLabel("Parity:");
        private JLabel lblDay = new JLabel("Day:");
        private JLabel lblTime = new JLabel("Time:");
        private JLabel lblForm = new JLabel("Form:");
        private JLabel lblStudentLimit = new JLabel("Student limit:");

        public JTextField txtGroupID  = new JTextField(16);
        public JTextField txtSubjectID  = new JTextField(16);
        public JTextField txtProfessorID  = new JTextField(16);
        public JTextField txtParity  = new JTextField(16);
        public JTextField txtDay  = new JTextField(16);
        public JTextField txtTime  = new JTextField(16);
        public JTextField txtForm  = new JTextField(16);
        public JTextField txtStudentLimit  = new JTextField(16);

        JFrame frameAddNewGroupView = new JFrame("group");
        public addNewGroupView(DeansWorkerModel model){
            //this.model = model;
            this.model = new DeansWorkerModel(model);
            frameAddNewGroupView.setLayout( new GridBagLayout());
            frameAddNewGroupView.setResizable(false);
            okButton.addActionListener(this);
            cancelButton.addActionListener(this);
            GridBagConstraints gbc = new GridBagConstraints();
            frameAddNewGroupView.setPreferredSize(new Dimension(300, 300));
            frameAddNewGroupView.setLocationRelativeTo(null);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(4, 4, 0, 4);
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            frameAddNewGroupView.add(lblGroupID, gbc);
            gbc.gridy = 1;
            frameAddNewGroupView.add(lblSubjectID, gbc);
            gbc.gridy = 2;
            frameAddNewGroupView.add(lblProfessorID, gbc);
            gbc.gridy = 3;
            frameAddNewGroupView.add(lblParity, gbc);
            gbc.gridy = 4;
            frameAddNewGroupView.add(lblDay, gbc);
            gbc.gridy = 5;
            frameAddNewGroupView.add(lblTime, gbc);
            gbc.gridy = 6;
            frameAddNewGroupView.add(lblForm, gbc);
            gbc.gridy = 7;
            frameAddNewGroupView.add(lblStudentLimit, gbc);
            gbc.gridy = 8;
            frameAddNewGroupView.add(okButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            frameAddNewGroupView.add(txtGroupID, gbc);
            gbc.gridy = 1;
            frameAddNewGroupView.add(txtSubjectID, gbc);
            gbc.gridy = 2;
            frameAddNewGroupView.add(txtProfessorID, gbc);
            gbc.gridy = 3;
            frameAddNewGroupView.add(txtParity, gbc);
            gbc.gridy = 4;
            frameAddNewGroupView.add(txtDay, gbc);
            gbc.gridy = 5;
            frameAddNewGroupView.add(txtTime, gbc);
            gbc.gridy = 6;
            frameAddNewGroupView.add(txtForm, gbc);
            gbc.gridy = 7;
            frameAddNewGroupView.add(txtStudentLimit, gbc);
            gbc.gridy = 8;
            frameAddNewGroupView.add(cancelButton, gbc);

            frameAddNewGroupView.setTitle("Add new group menu");
            frameAddNewGroupView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameAddNewGroupView.setSize(350, 300);
            frameAddNewGroupView.setVisible(true);
        }
        private final DeansWorkerModel model;

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Object e = actionEvent.getSource();

            if (e == okButton)
            {
                String string_group_id = txtGroupID.getText();
                String string_subject_id = txtSubjectID.getText();
                String string_professor_id = txtProfessorID.getText();
                String parity = txtParity.getText();
                String string_day = txtDay.getText();
                String time = txtTime.getText();
                String form = txtForm.getText();
                String string_student_limit = txtStudentLimit.getText();

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
                frameAddNewGroupView.dispose();
            }
            else if (e == cancelButton)
            {
                frameAddNewGroupView.dispose();
            }
        }
    }
}
