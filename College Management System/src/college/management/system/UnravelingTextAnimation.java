package college.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnravelingTextAnimation extends JPanel implements ActionListener {
    private final String text = "Welcome to MSU Saharanpur!";
    private int currentIndex = 0;
    private final Timer timer;

    public UnravelingTextAnimation() {
        timer = new Timer(150, this); // Update every 150 milliseconds
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Serif", Font.BOLD, 36));
        g.setColor(Color.RED);

        // Draw the text up to the current index
        String textToShow = text.substring(0, currentIndex);
        g.drawString(textToShow, 50, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentIndex < text.length()) {
            currentIndex++; // Increment the index to show more characters
            repaint(); // Redraw the text
        } else {
            timer.stop(); // Stop the timer when the full text is displayed
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Unraveling Text Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        frame.add(new UnravelingTextAnimation());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UnravelingTextAnimation::createAndShowGUI);
    }
}
