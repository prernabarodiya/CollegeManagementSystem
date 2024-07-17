package college.management.system;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {

    About() {
        setTitle("About Developer");
        setSize(700, 500);
        setLocation(400, 150);
        getContentPane().setBackground(new Color(255, 248, 220)); // Set a light beige background color

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/codingimg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(350, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(320, 20, 350, 200);
        add(image);

        JLabel heading = new JLabel("<html>College Management <br/>System</html>");
        heading.setBounds(40, 40, 500, 150);
        heading.setForeground(new Color(139, 69, 19)); // Dark brown color
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        add(heading);

        JLabel name = new JLabel("Developed By: Prerna");
        name.setBounds(40, 250, 300, 40);
        name.setForeground(new Color(184, 115, 51));
        name.setFont(new Font("Arial", Font.BOLD, 20));
        add(name);

        JLabel rollno = new JLabel("Roll number: 210321106049");
        rollno.setBounds(40, 290, 300, 40);
        rollno.setForeground(new Color(139, 69, 19));
        rollno.setFont(new Font("Arial", Font.PLAIN, 18));
        add(rollno);

        JLabel contact = new JLabel("Contact: prernabarodiya@gmail.com");
        contact.setBounds(40, 330, 400, 40);
        contact.setForeground(new Color(139, 69, 19));
        contact.setFont(new Font("Arial", Font.PLAIN, 18));
        add(contact);

        setLayout(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new About();
    }
}
