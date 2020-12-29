package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfessorView extends Component {

    private final JFrame frame;
    public JPanel professorPanel;
    private JButton groupsButton;
    private JButton timetableButton;
    private JButton personalDataButton;
    private JButton logOutButton;
    private JTextField idField;
    private JTable dataTable;
    private JScrollPane scrollPane;
    private JLabel loggedAsLabel;
    private JButton saveButton;
    DefaultTableModel tableModel = new DefaultTableModel();

    public ProfessorView() {

        frame = new JFrame("Professor menu");
        frame.setContentPane(professorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        dataTable.setModel(tableModel);
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public void dispose() {
        frame.dispose();
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getPersonalDataButton() {

        return personalDataButton;
    }

    public JButton getTimetableButton() {

        return timetableButton;
    }
    
    public JButton getGroupsButton() {
        return groupsButton;
    }

}
