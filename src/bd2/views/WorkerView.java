package bd2.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

    public JButton getLogOutButton(){return logOutButton;}
    public JButton getEditGroupButton(){return editGroupButton;}
    public JButton getDeleteGroupButton(){return deleteGroupButton;}
    public JButton getOpenNewGroupButton(){return openNewGroupButton;}
    public JButton getAddStudentToGroupButton(){return addStudentToGroupButton;}
    public JButton getDeleteStudentFromGroupButton(){return deleteStudentFromGroupButton;}
    public JButton getAddStudentsToListButton(){return addStudentsToListButton;}
    public JButton getDeleteStudentsFromListButton(){return deleteStudentsFromListButton;}
    public DefaultTableModel getTableModel2() {
        return tableModel2;
    }
}
