package college.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ExaminationDetails extends JFrame implements ActionListener {

    JTextField search;
    JButton submit, cancel;
    JTable table;
    JComboBox<String> cbsemester;

    ExaminationDetails() {
        setSize(1000, 475);
        setLocation(300, 100);

        setTitle("Check Result");
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Check Result");
        heading.setBounds(40, 15, 400, 50);
        heading.setForeground(new Color(124, 71, 0));
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        search = new JTextField();
        search.setBounds(40, 90, 200, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);

        submit = new JButton("Result");
        submit.setBounds(450, 90, 120, 30);
        submit.setBackground(new Color(124, 71, 0));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Back");
        cancel.setBounds(580, 90, 120, 30);
        cancel.setBackground(new Color(124, 71, 0));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        String[] semester = {"1st Semester", "2nd Semester", "3rd Semester", "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester"};
        cbsemester = new JComboBox<>(semester);
        cbsemester.setBounds(300, 90, 120, 30);
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(40, 130, 1000, 400);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jsp);


        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 2).toString());
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String semester = cbsemester.getSelectedItem().toString();
        if (ae.getSource() == submit) {
            String rollno = search.getText();
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from subject where rollno = '" + rollno + "' and semester = '" + semester + "'");
                if (rs.next()) {
//                    setVisible(false);
                    new Marks(rollno, semester);
                } else {
                    JOptionPane.showMessageDialog(this, "No records found for roll number " + rollno + " and semester " + semester);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            dispose();
        }
    }


    public static void main(String[] args) {
        new ExaminationDetails();
    }
}