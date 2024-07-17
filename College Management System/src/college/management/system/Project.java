package college.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Project extends JFrame implements ActionListener {

    Project() {

        setTitle("MSU, Saharanpur");
        new UnravelingTextAnimation();

        setSize(1540, 850);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/msu2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(2000, 950, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        // Adding text to the image
        JLabel text = new JLabel("<html><div style='text-align: center;'>"
                + "<h1 style='color: #5e2605; font-size: 36px;'>MSU Saharanpur</h1>"
                + "<p style='color: #5e2605; font-size: 20px;'>"
                + "MSU Saharanpur offers a diverse range of regular and self-financed courses across various disciplines."
                + "<br><br><b style='font-size: 24px;'>Courses Offered</b><br><br>"
                + "<table style='color: #000000; font-size: 18px;'>"
                + "<tr><td><b style='color: #000000;'>Under Graduate</b></td><td style='color: #000000;'>"
                + "B.Sc., B.A.,B.C.A., B.Com., B.Ed., B.A. - B.Ed., B.El.Ed., "
                + "B.A.J.M.C., B.L.I.Sc., LL.B., BA-LL.B., B.F.A."
                + "</td></tr>"
                + "<tr><td><b style='color: #000000;'>Post Graduate</b></td><td style='color: #000000;'>"
                + "M.Sc., M.A., M.Com., M.Ed., M.A.J.M.C., M.L.I.Sc., "
                + "M.S.W., M.F.A., M.Lib., MBE, MIB"
                + "</td></tr>"
                + "<tr><td><b style='color: #000000;'>Ph.D. Programs</b></td><td style='color: #000000;'>Doctoral research programs</td></tr>"
                + "</table>"
                + "<br><br>"
                + "</p>"
                + "</div></html>");
        text.setBounds(50, 50, 800, 450);  // Adjust the position and size
        image.add(text);

        add(image);
        JMenuBar mb = new JMenuBar();
        mb.setPreferredSize(new Dimension(mb.getWidth(), 35));
        mb.setBackground(Color.LIGHT_GRAY);


        // New Information
        JMenu newInformation = new JMenu("New Information");
        newInformation.setForeground(new Color(165, 42, 42));
        mb.add(newInformation);

        JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.setBackground(Color.WHITE);
        facultyInfo.addActionListener(this);
        newInformation.add(facultyInfo);

        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInformation.add(studentInfo);

        //Details
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.BLACK);
        mb.add(details);

        JMenuItem facultydetails = new JMenuItem("View Faculty Details");
        facultydetails.setBackground(Color.WHITE);
        facultydetails.addActionListener(this);
        details.add(facultydetails);

        JMenuItem studentDetails = new JMenuItem("View Student Details");
        studentDetails.setBackground(Color.WHITE);
        studentDetails.addActionListener(this);
        details.add(studentDetails);

        //Leave
//        JMenu leave = new JMenu("Leave");
//        leave.setForeground(new Color(165, 42, 42));
//        mb.add(leave);
//
//        JMenuItem facultyLeave = new JMenuItem("Apply Teacher Leave");
//        facultyLeave.setBackground(Color.WHITE);
//        facultyLeave.addActionListener(this);
//        leave.add(facultyLeave);
//
//        JMenuItem studentLeave = new JMenuItem("Apply Student Leave");
//        studentLeave.setBackground(Color.WHITE);
//        studentLeave.addActionListener(this);
//        leave.add(studentLeave);
//
//        //Leave Details
//        JMenu leaveDetails = new JMenu("Leave Details");
//        leaveDetails.setForeground(Color.BLACK);
//        mb.add(leaveDetails);
//
//        JMenuItem facultyLeaveDetails = new JMenuItem("Faculty Leave Details");
//        facultyLeaveDetails.setBackground(Color.WHITE);
//        facultyLeaveDetails.addActionListener(this);
//        leaveDetails.add(facultyLeaveDetails);
//
//        JMenuItem studentLeaveDetails = new JMenuItem("Student Leave Details");
//        studentLeaveDetails.setBackground(Color.WHITE);
//        studentLeaveDetails.addActionListener(this);
//        leaveDetails.add(studentLeaveDetails);

        //Exams

        JMenu exam = new JMenu("Examination");
        exam.setForeground(new Color(165, 42, 42));
        mb.add(exam);

        JMenuItem examinationResult = new JMenuItem("Examination Result");
        examinationResult.setBackground(Color.WHITE);
        examinationResult.addActionListener(this);
        exam.add(examinationResult);

        JMenuItem enterMarks = new JMenuItem("Enter Marks");
        enterMarks.setBackground(Color.WHITE);
        enterMarks.addActionListener(this);
        exam.add(enterMarks);

        //Update Information
        JMenu updateInfo = new JMenu("Update Information");
        updateInfo.setForeground(Color.BLACK);
        mb.add(updateInfo);

        JMenuItem updateFacultyInfo = new JMenuItem("Update Faculty Information");
        updateFacultyInfo.setBackground(Color.WHITE);
        updateFacultyInfo.addActionListener(this);
        updateInfo.add(updateFacultyInfo);

        JMenuItem updateStudentInfo = new JMenuItem("Update Student Information");
        updateStudentInfo.setBackground(Color.WHITE);
        updateStudentInfo.addActionListener(this);
        updateInfo.add(updateStudentInfo);

        //Fee
        JMenu fees = new JMenu("Fee Details");
        fees.setForeground(new Color(165, 42, 42));
        mb.add(fees);

        JMenuItem feeStructure = new JMenuItem("Fee Structure");
        feeStructure.setBackground(Color.WHITE);
        feeStructure.addActionListener(this);
        fees.add(feeStructure);

        JMenuItem feeForm = new JMenuItem("Student Fee Form");
        feeForm.setBackground(Color.WHITE);
        feeForm.addActionListener(this);
        fees.add(feeForm);

        //Utility
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLACK);
        mb.add(utility);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setBackground(Color.WHITE);
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(Color.WHITE);
        notepad.addActionListener(this);
        utility.add(notepad);

        //about
        JMenu about = new JMenu("About");
        about.setForeground(new Color(165, 42, 42));
        mb.add(about);

        JMenuItem ab = new JMenuItem("About");
        ab.setBackground(Color.WHITE);
        ab.addActionListener(this);
        about.add(ab);

        //Exit
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.BLACK);
        mb.add(exit);

        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);

        for (Component comp : mb.getComponents()) {
            if (comp instanceof JMenu) {
                JMenu menu = (JMenu) comp;
                menu.setFont(menu.getFont().deriveFont(14f)); // Set the font size here (e.g., 18)
            }
        }

        setJMenuBar(mb);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ea) {

        String msg = ea.getActionCommand();
        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {

            }
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {

            }
        } else if (msg.equals("New Faculty Information")) {
            new AddTeacher();
        } else if (msg.equals("New Student Information")) {
            new AddStudent();
        } else if (msg.equals("View Faculty Details")) {
            new TeacherDetails();
        } else if (msg.equals("View Student Details")) {
            new StudentDetails();
        }else if (msg.equals("Examination Result")) {
            new college.management.system.ExaminationDetails();
        }else if (msg.equals("Enter Marks")) {
            new EnterMarks();
        }else if (msg.equals("Update Faculty Information")) {
            new UpdateTeacher();
        }else if (msg.equals("Update Student Information")) {
            new UpdateStudent();
        }else if (msg.equals("Fee Structure")) {
            new college.management.system.FeeStructure();
        }else if (msg.equals("Student Fee Form")) {
            new StudentFeeForm();
        }else if (msg.equals("About")) {
            new About();
        }
    }

    public static void main(String[] args) {
        new Project();
    }


}

