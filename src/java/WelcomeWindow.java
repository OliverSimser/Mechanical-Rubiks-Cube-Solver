import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class WelcomeWindow extends JFrame {
    // Constructor for the User GUI
    public WelcomeWindow(){
        try { //Makes GUI work on Mac
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        setSize(800, 600);  // Set the JFrame's size
        JPanel panel = new JPanel(); // Create a new panel
        panel.setBounds(0, 0, 800, 600);
        // Display a welcome message
        JLabel instructionsLabel = new JLabel("Welcome to Oliver's Rubiks Cube Solver!");
        instructionsLabel.setFont(new Font("Serif", Font.BOLD, 20)); 
        instructionsLabel.setBounds(150, 1, 1000, 70); // Position the message
        instructionsLabel.setForeground(Color.WHITE);
        panel.setBackground(Color.BLACK); // Set the background color of the panel
        // Create option buttons
        JButton solverButton = new JButton("Solver");
        solverButton.setBounds(330, 75, 175, 75); // Set its position and size
        solverButton.setForeground(Color.BLACK); // Set its text color
        solverButton.setBackground(Color.LIGHT_GRAY); // Set its background color
        solverButton.setFont(new Font("Serif", Font.BOLD, 22)); // Set its font and size
        
        panel.setLayout(null); // Disable the layout manager
        
        // Add the buttons to the panel
        panel.add(solverButton);
        panel.add(instructionsLabel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows user to close the window
        add(panel); // Add the panel to the window
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
        setResizable(false); // Prevent the window from being resized

        
        solverButton.addMouseListener(new MouseListener() { //When user clicks on Solver button
            public void mouseClicked(MouseEvent e) {
                new SolverWindow(); // Create a new SolverWindow object
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
    }
}
