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

    private JPanel StudentPanel;
    private final JFrame StudentFrame;
    private JTextField STUDENT_IDTextField;
    private JTable table;
    private JButton zapisyButton;
    private JButton gradesButton;
    private JButton timetableButton;
    private JButton showPersonalDataButton;
    private JButton logOutButton;
    private JPanel zapiszPanel;
    private JButton wypiszButton;
    private JButton zapiszButton;
    private JScrollPane scrollPane;


    public StudentView()
    {
        StudentFrame = new JFrame("StudentMain");
        StudentFrame.setContentPane(StudentPanel);
        StudentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StudentFrame.pack();
        StudentFrame.setVisible(true);
        StudentFrame.setSize(new Dimension(1366, 750));

        init();
    }

    public JTextField getSTUDENT_IDTextField() {
        return STUDENT_IDTextField;
    }

    void init() {

        zapiszPanel.remove(zapiszButton);
        zapiszPanel.remove(wypiszButton);
        STUDENT_IDTextField.setText("fsd");
        JTable table = new JTable();
        showPersonalDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data();
            }
        });

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
                StudentFrame.dispose();

            }
        });
     }

    void Zapisy()
    {

    }

    void Data()
    {
        try {
            Statement st = App.cn.createStatement();
            ResultSet rs = st.executeQuery("select STUDENT_ID from BD.STUDENT order by STUDENT_ID");
            while (rs.next()) {
                String data = rs.getString("STUDENT_ID");
                System.out.println("Fetching data by column index for row " + rs.getRow() + " : " + data);
                //JOptionPane.showMessageDialog(null, data, "Error", JOptionPane.INFORMATION_MESSAGE);
                STUDENT_IDTextField.setText(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
