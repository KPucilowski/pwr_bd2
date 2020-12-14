package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentView extends Component {

    private final JFrame frame;
    DefaultTableModel tableModel = new DefaultTableModel();
    private JPanel contentPane;
    private JTextField idField;
    private JTable table;
    private JButton zapisyButton;
    private JButton gradesButton;
    private JButton timetableButton;
    private JButton personalDataButton;
    private JButton logOutButton;
    private JPanel zapiszPanel;
    private JButton wypiszButton;
    private JButton zapiszButton;
    private JScrollPane scrollPane;

    public StudentView() {
        frame = new JFrame("Student menu");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        table.setModel(tableModel);
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

    public JButton getGradesButton() {
        return gradesButton;
    }
}
