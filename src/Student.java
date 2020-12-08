import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Student extends Component {

    private JPanel StudentPanel;
    private static JFrame StudentFrame;
    private JTextField STUDENT_IDTextField;
    private JTable table1;

    private JButton zapisyButton;
    private JButton gradesButton;
    private JButton timetableButton;
    private JButton showPersonalDataButton;
    private JButton logOutButton;
    private JPanel zapiszPanel;
    private JButton wypiszButton;
    private JButton zapiszButton;


    public Student()
    {
        StudentFrame = new JFrame("StudentMain");
        StudentFrame.setContentPane(StudentPanel);
        StudentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StudentFrame.pack();
        StudentFrame.setVisible(true);
        StudentFrame.setSize(new Dimension(1366, 750));

        init();
    }

    void init() {
        zapiszPanel.remove(zapiszButton);
        zapiszPanel.remove(wypiszButton);
        STUDENT_IDTextField.setText("fsd");

        showPersonalDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Data();
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

                LoginView loginView = new LoginView();
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
            ResultSet rs = st.executeQuery("select * from STUDENT ");
            while (rs.next()) {
                String data = rs.getString("STUDENT_ID");
                //System.out.println("Fetching data by column index for row " + rs.getRow() + " : " + data);
                JOptionPane.showMessageDialog(null, data, "Error", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
