package college.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FeeStructure extends JFrame {

    FeeStructure() {
        setTitle("Fee Structure");
        setSize(1000, 700);
        setLocation(250, 50);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(20, 10, 400, 50);
        heading.setForeground(new Color(124, 71, 0));
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        JTable table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }


        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 80, 950, 700);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jsp);

        setVisible(true);

    }

    public static void main(String[] args) {
        new FeeStructure();
    }
}
