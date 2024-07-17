package college.management.system;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class TeacherLeaveDetails extends JFrame implements ActionListener {
    Choice cEmpId;
    JTable table;
    JButton search, print, cancel;

    TeacherLeaveDetails() {

        setTitle("Teacher Leave Details");

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee Id");
        heading.setBounds(20, 20, 150, 20);
        heading.setForeground(new Color(184, 115, 51));
        add(heading);

        cEmpId = new Choice();
        cEmpId.setBounds(180, 20, 150, 20);
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
            String query = "select teacherleave.empId, teacher.name, teacherleave.* from teacherleave join teacher on teacherleave.empId = teacher.empId";
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
            String query = "select teacherleave.empId, teacher.name, teacherleave.* from teacherleave join teacher on teacherleave.empId = teacher.empId where teacherleave.empId = '" + cEmpId.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                // Check if the result set is empty
                if (!rs.isBeforeFirst()) {
                    // Show a message dialog if no records are found
                    JOptionPane.showMessageDialog(null, "No records found for Employee Id: " + cEmpId.getSelectedItem());
                    // Clear the table
                    table.setModel(new javax.swing.table.DefaultTableModel());
                    //show previous data
                    ResultSet rs2 = c.s.executeQuery("select teacherleave.empId, teacher.name, teacherleave.* from teacherleave join teacher on teacherleave.empId = teacher.empId");
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
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args) {
        new TeacherLeaveDetails();
    }
}
