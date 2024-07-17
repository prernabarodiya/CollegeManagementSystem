package college.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TeacherLeave extends JFrame implements ActionListener {

    Choice cEmpId, ctime;
    JDateChooser dcdate;
    JButton submit, cancel;

    TeacherLeave() {
        setTitle("Teacher Leave Application");

        setSize(500, 550);
        setLocation(550, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Apply Leave (Teacher)");
        heading.setBounds(60, 50, 300, 30);
        heading.setForeground(new Color(124, 71, 0));
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel lblrollno = new JLabel("Search by Employee Id");
        lblrollno.setBounds(60, 100, 200, 20);
        lblrollno.setForeground(new Color(184, 115, 51));
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);

        cEmpId = new Choice();
        cEmpId.setBounds(60, 130, 200, 20);
        add(cEmpId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
            while (rs.next()) {
                cEmpId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 180, 200, 20);
        lbldate.setForeground(new Color(184, 115, 51));
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(60, 210, 200, 25);
        add(dcdate);

        JLabel lbltime = new JLabel("Time Duration");
        lbltime.setBounds(60, 260, 200, 20);
        lbltime.setForeground(new Color(184, 115, 51));
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbltime);

        ctime = new Choice();
        ctime.setBounds(60, 290, 200, 20);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);

        submit = new JButton("Submit");
        submit.setBounds(60, 350, 90, 25);
        submit.setBackground(new Color(124, 71, 0));
        submit.addActionListener(this);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 350, 90, 25);
        cancel.setBackground(new Color(124, 71, 0));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String rollno = cEmpId.getSelectedItem();
            String date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            String duration = ctime.getSelectedItem();

            String query = "insert into teacherleave values('" + rollno + "', '" + date + "', '" + duration + "')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new TeacherLeave();
    }
}