package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentView extends Component {

    private final JFrame frame;
    private JPanel studentPanel;
    private JScrollPane scrollPane;
    private JLabel loggedAsLabel;
    private JTable dataTable;
    private JTextField idField;
    private JButton enrollButton;
    private JButton gradesButton;
    private JButton timetableButton;
    private JButton personalDataButton;
    private JButton logOutButton;

    DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        ;
    };

    public StudentView() {
        frame = new JFrame("Student menu");
        frame.setContentPane(studentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        dataTable.setModel(tableModel);
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

    public JButton getTimetableButton() {
        return timetableButton;
    }

    public JButton getPersonalDataButton() {
        return personalDataButton;
    }

    public JButton getGradesButton() {
        return gradesButton;
    }

    public JButton getEnrollButton() {
        return enrollButton;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }
}