package college.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class EnterMarks extends JFrame implements ActionListener {
    Choice crollno;
    JComboBox<String> cbsemester;
    JTextField[] tfSubjects = new JTextField[5];
    JTextField[] tfMarks = new JTextField[5];
    JButton cancel, submit;

    EnterMarks() {

        setTitle("Enter Student Marks");

        setSize(1000, 500);
        setLocation(300, 150);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 40, 400, 300);
        add(image);

        JLabel heading = new JLabel("Enter Marks of Student");
        heading.setBounds(50, 0, 500, 50);
        heading.setForeground(new Color(124, 71, 0));
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setForeground(new Color(184, 115, 51));
        lblrollnumber.setBounds(50, 70, 150, 20);
        add(lblrollnumber);

        crollno = new Choice();
        crollno.setBounds(200, 70, 150, 20);
        add(crollno);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblsemester = new JLabel("Select Semester");
        lblsemester.setBounds(50, 110, 150, 20);
        lblsemester.setForeground(new Color(184, 115, 51));
        add(lblsemester);

        String[] semester = {"1st Semester", "2nd Semester", "3rd Semester", "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester"};
        cbsemester = new JComboBox<>(semester);
        cbsemester.setBounds(200, 110, 150, 20);
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        JLabel lblentersubject = new JLabel("Enter Subject");
        lblentersubject.setForeground(new Color(184, 115, 51));
        lblentersubject.setBounds(100, 150, 200, 40);
        add(lblentersubject);

        JLabel lblentermarks = new JLabel("Enter Marks");
        lblentermarks.setForeground(new Color(184, 115, 51));
        lblentermarks.setBounds(320, 150, 200, 40);
        add(lblentermarks);

        for (int i = 0; i < 5; i++) {
            tfSubjects[i] = new JTextField();
            tfSubjects[i].setBounds(50, 200 + (i * 30), 200, 20);
            add(tfSubjects[i]);

            tfMarks[i] = new JTextField();
            tfMarks[i].setBounds(250, 200 + (i * 30), 200, 20);
            add(tfMarks[i]);
        }

        submit = new JButton("Submit");
        submit.setBounds(70, 360, 150, 25);
        submit.setBackground(new Color(124, 71, 0));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Back");
        cancel.setBounds(280, 360, 150, 25);
        cancel.setBackground(new Color(124, 71, 0));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                Conn c = new Conn();
                String rollno = crollno.getSelectedItem();
                String semester = cbsemester.getSelectedItem().toString();
                String[] subjects = new String[5];
                String[] marks = new String[5];

                for (int i = 0; i < 5; i++) {
                    subjects[i] = tfSubjects[i].getText();
                    marks[i] = tfMarks[i].getText();
                }

                // Check if the roll number and semester already exist in the database
                String checkQuery = "select * from subject where rollno = '" + rollno + "' and semester = '" + semester + "'";
                ResultSet rs = c.s.executeQuery(checkQuery);
                if (rs.next()) {
                    // Update existing record
                    String updateSubjectQuery = "update subject set subject1 = '" + subjects[0] + "', subject2 = '" + subjects[1] + "', subject3 = '" + subjects[2] + "', subject4 = '" + subjects[3] + "', subject5 = '" + subjects[4] + "' where rollno = '" + rollno + "' and semester = '" + semester + "'";
                    String updateMarksQuery = "update marks set marks1 = '" + marks[0] + "', marks2 = '" + marks[1] + "', marks3 = '" + marks[2] + "', marks4 = '" + marks[3] + "', marks5 = '" + marks[4] + "' where rollno = '" + rollno + "' and semester = '" + semester + "'";
                    c.s.executeUpdate(updateSubjectQuery);
                    c.s.executeUpdate(updateMarksQuery);
                    JOptionPane.showMessageDialog(null, "Marks Updated Successfully");
                } else {
                    // Insert new record
                    String insertSubjectQuery = "insert into subject (rollno, semester, subject1, subject2, subject3, subject4, subject5) values('" + rollno + "', '" + semester + "', '" + subjects[0] + "', '" + subjects[1] + "', '" + subjects[2] + "', '" + subjects[3] + "', '" + subjects[4] + "')";
                    String insertMarksQuery = "insert into marks (rollno, semester, marks1, marks2, marks3, marks4, marks5) values('" + rollno + "', '" + semester + "', '" + marks[0] + "', '" + marks[1] + "', '" + marks[2] + "', '" + marks[3] + "', '" + marks[4] + "')";
                    c.s.executeUpdate(insertSubjectQuery);
                    c.s.executeUpdate(insertMarksQuery);
                    JOptionPane.showMessageDialog(null, "Marks Inserted Successfully");
                }
//                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new EnterMarks();
    }
}