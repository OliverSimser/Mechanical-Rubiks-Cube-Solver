import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorWindow extends JFrame {

    private JPanel colorPanel; 
      // Create copies of each faces array
      public static int[][] whiteFaceCopyable = new int[3][3]; 
      public static int[][] redFaceCopyable = new int[3][3];
      public static int[][] blueFaceCopyable = new int[3][3];
      public static int[][] orangeFaceCopyable = new int[3][3];
      public static int[][] greenFaceCopyable = new int[3][3];
      public static int[][] yellowFaceCopyable = new int[3][3];

    public ColorWindow(final JButton jb, final int a, final int b, final char color) { //ColorWindow constructor
        try { //Makes GUI work on Mac
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        super("Color Options");
        setSize(200, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(3, 2)); //Add 3x2 grid layout to the panel

        // Create buttons for each color
        JButton whiteButton = new JButton("White");
        whiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jb.setBackground(Color.WHITE);
                switch (color) {
                    case 'w': //If color passed in is white
                        whiteFaceCopyable[a][b] = 0; //Set white face array element with the row and column passed in to 0 (white)
                        break;
                    case 'r': //Same with the other colors
                        redFaceCopyable[a][b] = 0; 
                        break;
                    case 'g':
                        greenFaceCopyable[a][b] = 0;
                        break;
                    case 'b':
                        blueFaceCopyable[a][b] = 0;
                        break;
                    case 'y':
                        yellowFaceCopyable[a][b] = 0;
                        break;
                    case 'o':
                        orangeFaceCopyable[a][b] = 0;
                        break;
                }
                dispose(); //Dispose of window after setting the color
            }
        });
        colorPanel.add(whiteButton); //Adds white button to the panel

        JButton redButton = new JButton("Red");
        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jb.setBackground(Color.RED);
                switch (color) {
                    case 'w': 
                        whiteFaceCopyable[a][b] = 1;
                        break;
                    case 'r':
                        redFaceCopyable[a][b] = 1;
                        break;
                    case 'g':
                        greenFaceCopyable[a][b] = 1;
                        break;
                    case 'b':
                        blueFaceCopyable[a][b] = 1;
                        break;
                    case 'y':
                        yellowFaceCopyable[a][b] = 1;
                        break;
                    case 'o':
                        orangeFaceCopyable[a][b] = 1;
                        break;
                }
                dispose();
            }
        });
        colorPanel.add(redButton);

        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jb.setBackground(new Color(30,135,255));
                switch (color) {
                    case 'w': 
                        whiteFaceCopyable[a][b] = 2;
                        break;
                    case 'r':
                        redFaceCopyable[a][b] = 2;
                        break;
                    case 'g':
                        greenFaceCopyable[a][b] = 2;
                        break;
                    case 'b':
                        blueFaceCopyable[a][b] = 2;
                        break;
                    case 'y':
                        yellowFaceCopyable[a][b] = 2;
                        break;
                    case 'o':
                        orangeFaceCopyable[a][b] = 2;
                        break;
                }
                dispose();
            }
        });
        colorPanel.add(blueButton);

        JButton orangeButton = new JButton("Orange");
        orangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jb.setBackground(new Color(255,110,0));
                switch (color) {
                    case 'w': 
                        whiteFaceCopyable[a][b] = 3;
                        break;
                    case 'r':
                        redFaceCopyable[a][b] = 3;
                        break;
                    case 'g':
                        greenFaceCopyable[a][b] = 3;
                        break;
                    case 'b':
                        blueFaceCopyable[a][b] = 3;
                        break;
                    case 'y':
                        yellowFaceCopyable[a][b] = 3;
                        break;
                    case 'o':
                        orangeFaceCopyable[a][b] = 3;
                        break;
                }
                dispose();
            }
        });
        colorPanel.add(orangeButton);

        JButton greenButton = new JButton("Green");
        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jb.setBackground(Color.GREEN);
                switch (color) {
                    case 'w': 
                        whiteFaceCopyable[a][b] = 4;
                        break;
                    case 'r':
                        redFaceCopyable[a][b] = 4;
                        break;
                    case 'g':
                        greenFaceCopyable[a][b] = 4;
                        break;
                    case 'b':
                        blueFaceCopyable[a][b] = 4;
                        break;
                    case 'y':
                        yellowFaceCopyable[a][b] = 4;
                        break;
                    case 'o':
                        orangeFaceCopyable[a][b] = 4;
                        break;
                }
                dispose();
            }
        });
        colorPanel.add(greenButton);

        JButton yellowButton = new JButton("Yellow");
        yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jb.setBackground(Color.YELLOW);
                switch (color) {
                    case 'w': 
                        whiteFaceCopyable[a][b] = 5;
                        break;
                    case 'r':
                        redFaceCopyable[a][b] = 5;
                        break;
                    case 'g':
                        greenFaceCopyable[a][b] = 5;
                        break;
                    case 'b':
                        blueFaceCopyable[a][b] = 5;
                        break;
                    case 'y':
                        yellowFaceCopyable[a][b] = 5;
                        break;
                    case 'o':
                        orangeFaceCopyable[a][b] = 5;
                        break;
                }
                dispose();
            }
        });
        colorPanel.add(yellowButton);

        add(colorPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}