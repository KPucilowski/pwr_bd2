package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfessorView extends Component {

    private final JFrame frame;
    public JPanel professorPanel;
    private JScrollPane scrollPane;
    public  JTable dataTable;
    private JTextField idField;
    private JLabel loggedAsLabel;
    private JButton groupsButton;
    private JButton timetableButton;
    private JButton personalDataButton;
    public JButton saveButton;
    private JButton logOutButton;

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

    public ProfessorView() {
        frame = new JFrame("Professor menu");
        frame.setContentPane(professorPanel);
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

    public DefaultTableModel getTableModel2() {
        return tableModel2;
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

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }
}