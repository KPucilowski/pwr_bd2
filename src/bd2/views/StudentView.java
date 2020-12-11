package bd2.views;

import bd2.App;

import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class StudentView extends Component {

    private JPanel studentPanel;
    private final JFrame studentFrame;
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


    public StudentView()
    {
        studentFrame = new JFrame("StudentMain");
        studentFrame.setContentPane(studentPanel);
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentFrame.pack();
        studentFrame.setVisible(true);
        studentFrame.setSize(new Dimension(1366, 750));

        init();
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getPersonalDataButton() {
        return personalDataButton;
    }

    void init() {

        zapiszPanel.remove(zapiszButton);
        zapiszPanel.remove(wypiszButton);
        JTable table = new JTable();

        gradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grades();
            }
        });


        zapisyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapiszPanel.add(zapiszButton);
                zapiszPanel.add(wypiszButton);
                Zapisy();
            }
        });


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
                studentFrame.dispose();

            }
        });
     }

    void Zapisy()
    {

    }

    void  Grades(){
        DefaultTableModel model = new DefaultTableModel();

        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        model.setColumnIdentifiers(
                new String[]{"Group ID", "Student ID", "RECORD DATE", "GRADE", "GRADE DATE"});
        scrollPane.setBorder(BorderFactory.createTitledBorder("Students"));
        table.setModel(model);


        try {
        Statement st = App.cn.createStatement();
        ResultSet rs = st.executeQuery("select * from BD.RECORD ");
        while (rs.next()) {

            String group_id = Integer.toString(rs.getInt("STUDENT_ID"));
            String student_id = rs.getString("STUDENT_ID");
            String record_date = rs.getString("RECORD_DATE");
            String grade = rs.getString("GRADE");
            String grade_date = rs.getString("GRADE_DATE");
            model.addRow(new String[]{group_id, student_id, record_date, grade, grade_date});
        }

    }catch(SQLException e){
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
