package bd2.views.dialogs;

import javax.swing.*;

public class NewGroupView extends JDialog {
    public JButton okButton;
    public JButton cancelButton;
    public JTextField txtGroupID;
    public JTextField txtProfessorID;
    public JTextField txtSubjectID;
    public JTextField txtParity;
    public JTextField txtDay;
    public JTextField txtTime;
    public JTextField txtForm;
    public JTextField txtStudentLimit;
    private JPanel newGroupPanel;


    public NewGroupView() {
        setTitle("New group menu");
        setContentPane(newGroupPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getTxtGroupID() {
        return txtGroupID;
    }

    public JTextField getTxtProfessorID() {
        return txtProfessorID;
    }

    public JTextField getTxtSubjectID() {
        return txtSubjectID;
    }

    public JTextField getTxtParity() {
        return txtParity;
    }

    public JTextField getTxtDay() {
        return txtDay;
    }

    public JTextField getTxtTime() {
        return txtTime;
    }

    public JTextField getTxtForm() {
        return txtForm;
    }

    public JTextField getTxtStudentLimit() {
        return txtStudentLimit;
    }
}
