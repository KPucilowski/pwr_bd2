package bd2.views.dialogs;

import javax.swing.*;

public class NewStudentView {
    private JFrame frame;
    private JPanel newStudentPanel;
    public JButton okButton;
    public JButton cancelButton;
    public JTextField txtFirstName;
    public JTextField txtLastName;
    public JTextField txtFacultyID;
    public JTextField txtPESEL;

    public NewStudentView( ){
        frame = new JFrame("Student menu");
        frame.setContentPane(newStudentPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        getCancelButton().addActionListener(e->dispose());
    }

    public void dispose() {
        frame.dispose(); ;
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
