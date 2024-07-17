package college.management.system;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentLeaveDetails extends JFrame implements ActionListener {

    Choice crollno;
    JTable table;
    JButton search, print, update, add, cancel;

    StudentLeaveDetails() {

        setTitle("Student Leave Details");

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        heading.setForeground(new Color(184, 115, 51));
        add(heading);

        crollno = new Choice();
        crollno.setBounds(180, 20, 150, 20);
        add(crollno);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                } else {
                    c.setBackground(Color.YELLOW);
                }
                return c;
            }
        };

        try {
            Conn c = new Conn();
            String query = "select studentleave.rollno, student.name, studentleave.* from studentleave join student on studentleave.rollno = student.rollno";

            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 100, 900, 600);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        search.setBackground(new Color(124, 71, 0));
        search.setForeground(Color.WHITE);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.setBackground(new Color(124, 71, 0));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 70, 80, 20);
        cancel.setBackground(new Color(124, 71, 0));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {

            String query = "select studentleave.rollno, student.name, studentleave.* from studentleave join student on studentleave.rollno = student.rollno where studentleave.rollno = '" + crollno.getSelectedItem() + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    // Show a message dialog if no records are found
                    JOptionPane.showMessageDialog(null, "No records found for Roll Number: " + crollno.getSelectedItem());
                    // Clear the table
                    table.setModel(new javax.swing.table.DefaultTableModel());
                    ResultSet rs2 = c.s.executeQuery("select sudentleave.rollno, student.name, sudentleave.* from sudentleave join student on sudentleave.rollno = student.rollno");
                    table.setModel(DbUtils.resultSetToTableModel(rs2));
                } else {
                    // Populate the table if records are found
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new StudentLeaveDetails();
    }
}