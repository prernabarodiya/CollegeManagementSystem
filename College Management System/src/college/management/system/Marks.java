package college.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.sql.*;

public class Marks extends JFrame implements ActionListener, Printable {

    String rollno;
    JButton cancel, print;
    JTable table;
    JLabel lblname, lblcourse, lblrollno;

    Marks(String rollno, String semester) {

        this.rollno = rollno;

        setTitle("Result of " + rollno);
        setSize(700, 700);
        setLocation(500, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Maa Shakumbhari Univerisity, Saharnpur");
        heading.setForeground(new Color(124, 71, 0));
        heading.setBounds(150, 50, 500, 40);
        heading.setFont(new Font("serif", Font.BOLD, 26));
        add(heading);

        lblname = new JLabel();
        lblname.setBounds(100, 130, 300, 20);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblname);

        lblrollno = new JLabel();
        lblrollno.setBounds(450, 130, 500, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);
        lblrollno.setText("Roll Number: " + rollno);

        lblcourse = new JLabel();
        lblcourse.setBounds(100, 150, 300, 20);
        lblcourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblcourse);

        JLabel lblsemester = new JLabel();
        lblsemester.setBounds(450, 150, 200, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        lblsemester.setText("Semester: " + semester);

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Subject");
        model.addColumn("Marks");

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(100, 220, 550, 115);
        add(jsp);

        try {
            Conn c = new Conn();

            ResultSet rsStudent = c.s.executeQuery("select * from student where rollno = '" + rollno + "'");
            if (rsStudent.next()) {
                lblname.setText("Name: " + rsStudent.getString("name"));
                lblcourse.setText("Course: " + rsStudent.getString("course"));
            }

            ResultSet rs1 = c.s.executeQuery("select * from subject where rollno = '" + rollno + "' and semester = '" + semester + "'");
            while (rs1.next()) {
                model.addRow(new Object[]{rs1.getString("subject1"), ""});
                model.addRow(new Object[]{rs1.getString("subject2"), ""});
                model.addRow(new Object[]{rs1.getString("subject3"), ""});
                model.addRow(new Object[]{rs1.getString("subject4"), ""});
                model.addRow(new Object[]{rs1.getString("subject5"), ""});
            }

            ResultSet rs2 = c.s.executeQuery("select * from marks where rollno = '" + rollno + "' and semester = '" + semester + "'");
            while (rs2.next()) {
                model.setValueAt(rs2.getString("marks1"), 0, 1);
                model.setValueAt(rs2.getString("marks2"), 1, 1);
                model.setValueAt(rs2.getString("marks3"), 2, 1);
                model.setValueAt(rs2.getString("marks4"), 3, 1);
                model.setValueAt(rs2.getString("marks5"), 4, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        print = new JButton("Print");
        print.setBounds(220, 400, 120, 30);
        print.setBackground(new Color(124, 71, 0));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        print.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(print);

        cancel = new JButton("Back");
        cancel.setBackground(new Color(124, 71, 0));
        cancel.setBounds(400, 400, 120, 30);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == print) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            PageFormat pf = job.defaultPage();
            pf.setOrientation(PageFormat.LANDSCAPE);

            Paper paper = pf.getPaper();
            double margin = 10; // margin in points
            paper.setImageableArea(margin, margin, paper.getWidth() - 2 * margin, paper.getHeight() - 2 * margin);
            pf.setPaper(paper);

            job.setPrintable(this, pf);

            boolean doPrint = job.printDialog();
            if (doPrint) {
                try {
                    job.print();
                } catch (PrinterException e) {
                    e.printStackTrace();
                }
            }
        } else {
            setVisible(false);
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        double scaleX = pf.getImageableWidth() / this.getWidth();
        double scaleY = pf.getImageableHeight() / this.getHeight();
        double scale = Math.min(scaleX, scaleY);
        g2d.scale(scale, scale);

        // Calculate the height of the title bar
        Insets insets = getInsets();
        int titleBarHeight = insets.top;

        // Clip the graphics to the printable area, excluding the title bar
        g2d.setClip(0, titleBarHeight, getWidth(), 390 - titleBarHeight);

        // Print the frame's content
        this.printAll(g2d);

        return PAGE_EXISTS;
    }






    public static void main(String[] args) {
        new Marks("", "");
    }
}
