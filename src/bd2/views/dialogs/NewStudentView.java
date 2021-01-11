package bd2.views.dialogs;

import javax.swing.*;
import java.awt.*;

public class NewStudentView extends JDialog {
    private JPanel newStudentPanel;
    public JButton okButton;
    public JButton cancelButton;
    public JTextField txtFirstName;
    public JTextField txtLastName;
    public JTextField txtFacultyID;
    public JTextField txtPESEL;

    public NewStudentView(){
        setTitle("Student menu");
        setContentPane(newStudentPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getTxtFirstName() {
        return txtFirstName;
    }

    public JTextField getTxtLastName() {
        return txtLastName;
    }

    public JTextField getTxtFacultyID() {
        return txtFacultyID;
    }

    public JTextField getTxtPESEL() {
        return txtPESEL;
    }
}
