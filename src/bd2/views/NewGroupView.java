package bd2.views;

import javax.swing.*;
import java.awt.*;

public class NewGroupView extends Component {
    private JFrame frame;
    private JPanel newGroupPanel;
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


    public NewGroupView( ){
        frame = new JFrame("New group menu");
        frame.setContentPane(newGroupPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        getCancelButton().addActionListener(e->dispose());
    }

    public void dispose() {
        frame.dispose();
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
