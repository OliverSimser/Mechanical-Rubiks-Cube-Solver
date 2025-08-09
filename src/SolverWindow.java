import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class SolverWindow extends JFrame {
    // Constructor for the solver window
    public SolverWindow(){
        try { //Makes GUI work on Mac
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        setSize(800, 600); // Set the JFrame's size
        JPanel panel1 = new JPanel(); // Create a new panel
        panel1.setBounds(0, 0, 800, 600);
        
        // Display a welcome message
        JLabel instructionsLabel = new JLabel("Click on the boxes to enter the colors of your cube");
        instructionsLabel.setFont(new Font("Serif", Font.BOLD, 20));
        instructionsLabel.setBounds(70, 1, 1000, 70); 
        instructionsLabel.setForeground(Color.WHITE);

        // Create a new button to solve the cube
        JButton solveButton = new JButton("Solve!");
        solveButton.setBackground(Color.LIGHT_GRAY);
        solveButton.setBounds(310, 490, 123, 60);
        solveButton.setForeground(Color.BLACK);
        solveButton.setFont(new Font("Serif", Font.BOLD, 19));
        
        panel1.setBackground(Color.BLACK); // Set the background color of the panel
        panel1.setLayout(null); // Disable the layout manager
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Allows user to close window
        add(panel1); // Add the panel to the window
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
        setResizable(false); // Prevent the window from being resized
        
        // Create a label for the yellow face
        JLabel yellowLabel = new JLabel("<html>When inputing the red, green, blue, and orange faces,<br>make sure the white face is on the top,<br>when inputing the yellow and white face,<br>make sure the red face is on the top</html>");
        yellowLabel.setFont(new Font("Serif", Font.BOLD, 14)); 
        yellowLabel.setBounds(10, 280, 300, 120); 
        yellowLabel.setForeground(Color.WHITE);

        // Add the labels to the panel
        panel1.add(instructionsLabel);
        panel1.add(yellowLabel);
        panel1.add(solveButton);
        
        solveButton.addMouseListener(new MouseListener() { //When user clicks on the solve button
            public void mouseClicked(MouseEvent e) {
                final Cube cube1 = new Cube(ColorWindow.whiteFaceCopyable, ColorWindow.redFaceCopyable, ColorWindow.blueFaceCopyable, ColorWindow.orangeFaceCopyable, ColorWindow.greenFaceCopyable, ColorWindow.yellowFaceCopyable); //Create a new cube object with a copy of the colors on each face set by the user
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        
        final int DIMENSION = 3; //Max size of the arrays of the buttons for the cube
        //Create arrays for the buttons on the GUI
        JButton[][] whiteButtons = new JButton[DIMENSION][DIMENSION];
        JButton[][] redButtons = new JButton[DIMENSION][DIMENSION];
        JButton[][] greenButtons = new JButton[DIMENSION][DIMENSION];
        JButton[][] blueButtons = new JButton[DIMENSION][DIMENSION];
        JButton[][] yellowButtons = new JButton[DIMENSION][DIMENSION];
        JButton[][] orangeButtons = new JButton[DIMENSION][DIMENSION];

        // Create buttons, set properties, and add listeners
            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) { //Creates a three by three grid of buttons
                    whiteButtons[i][j] = createButton("", 330 + j * 30, 75 + i * 30); // Calls the createButton method and positions the buttons
                    whiteButtons[i][j].setBackground(Color.BLACK);
                    panel1.add(whiteButtons[i][j]); // Add buttons to panel
                    redButtons[i][j] = createButton("", 330 + j * 30, 175 + i * 30); //Creates buttons for each face
                    redButtons[i][j].setBackground(Color.BLACK);
                    panel1.add(redButtons[i][j]); 
                    greenButtons[i][j] = createButton("", 230 + j * 30, 175 + i * 30);
                    greenButtons[i][j].setBackground(Color.BLACK);
                    panel1.add(greenButtons[i][j]);
                    blueButtons[i][j] = createButton("", 430 + j * 30, 175 + i * 30);
                    blueButtons[i][j].setBackground(Color.BLACK);
                    panel1.add(blueButtons[i][j]); 
                    yellowButtons[i][j] = createButton("", 330 + j * 30, 275 + i * 30);
                    yellowButtons[i][j].setBackground(Color.BLACK);
                    panel1.add(yellowButtons[i][j]); 
                    orangeButtons[i][j] = createButton("", 330 + j * 30, 375 + i * 30);
                    orangeButtons[i][j].setBackground(Color.BLACK);
                    panel1.add(orangeButtons[i][j]); 
                    if (i == 1 && j == 1){
                    }
                    else{ //Adds listeners for each button that is not a center piece
                        whiteButtons[i][j].addMouseListener(new ButtonMouseListener(whiteButtons[i][j], i, j, 'w'));
                        redButtons[i][j].addMouseListener(new ButtonMouseListener(redButtons[i][j], i, j, 'r'));
                        greenButtons[i][j].addMouseListener(new ButtonMouseListener(greenButtons[i][j], i, j, 'g'));
                        blueButtons[i][j].addMouseListener(new ButtonMouseListener(blueButtons[i][j], i, j, 'b'));
                        yellowButtons[i][j].addMouseListener(new ButtonMouseListener(yellowButtons[i][j], i, j, 'y'));
                        orangeButtons[i][j].addMouseListener(new ButtonMouseListener(orangeButtons[i][j], i, j ,'o'));
                        }
                }
            }
            // Special case for the center piece (1,1) of each array as it never changes
            whiteButtons[1][1].setBackground(Color.WHITE);
            ColorWindow.whiteFaceCopyable[1][1] = 0;
            redButtons[1][1].setBackground(Color.RED);
            ColorWindow.redFaceCopyable[1][1] = 1;
            greenButtons[1][1].setBackground(Color.GREEN);
            ColorWindow.greenFaceCopyable[1][1] = 4;
            blueButtons[1][1].setBackground(new Color(30,135,255)); //Sets a more specific blue color
            ColorWindow.blueFaceCopyable[1][1] = 2;
            yellowButtons[1][1].setBackground(Color.YELLOW);
            ColorWindow.yellowFaceCopyable[1][1] = 5;
            orangeButtons[1][1].setBackground(new Color(255,110,0)); //Sets a more specific orange color
            ColorWindow.orangeFaceCopyable[1][1] = 3;
    }

        // Method to create the buttons
        private JButton createButton(String text, int x, int y) {
            JButton button = new JButton(text);
            button.setBounds(x, y, 25, 25);
            button.setForeground(Color.WHITE);
            return button;
        }
        // Class to handle button clicks
        private class ButtonMouseListener implements MouseListener { 
        private JButton button; //Takes a button as a parameter
        private int row, col; //Takes the row and column of the array to match that of the cube
        private char color; //Takes the color of the face its on to match that of the cube

        // Constructor for the ButtonMouseListener class
        public ButtonMouseListener(JButton button, int row, int col, char color) {
            this.button = button;
            this.row = row;
            this.col = col;
            this.color = color;
        }

        public void mouseClicked(MouseEvent e) {
            new ColorWindow(button, row, col, color); // Create a new ColorWindow object to change color of button and value in the cube arrays
        }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        };
}