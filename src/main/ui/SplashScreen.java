package ui;

import javax.swing.*;
import java.awt.*;

import java.util.Random;

// The SplashScreen class creates a simple graphical splash screen for a Java Swing application.
// It displays a welcome message, a progress bar, and changes the background color dynamically.
// The splash screen is shown for a fixed duration, and then it transitions to the main application
// window.
// The splash screen features a welcome message in a large font, a progress bar to indicate
// loading progress, and a dynamically changing background color for visual appeal.
// The main method initializes the Swing components, sets up timers for controlling the duration and
// progress bar, and transitions to the main application window (FamilyContactManagerGUI) after
// the splash screen period.
public class SplashScreen {

    // EFFECTS: The 'Main' method initializes the 'SplashScreen' and starts the splash screen.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createSplashScreen();
            Timer timer = new Timer(3000, e -> {
                splashScreen.setVisible(false);
                splashScreen.dispose();
                new FamilyContactManagerGUI();
            });
            timer.setRepeats(false);
            timer.start();
            Timer progressBarTimer = new Timer(28, e -> {
                progressBar.setValue(progressBar.getValue() + 1);
            });
            progressBarTimer.start();
        });
    }

    private static JFrame splashScreen;
    private static JProgressBar progressBar;

    // MODIFIES: this
    // EFFECTS: Initializes and displays the splash screen with a welcome message and progress bar
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private static void createSplashScreen() {
        splashScreen = new JFrame();
        splashScreen.setSize(400, 200);
        splashScreen.setUndecorated(true);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                changeBackgroundColor(this);
            }
        };
        JLabel welcomeLabel = new JLabel("Welcome to FamConnect!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.GREEN);
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(300, 25));
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Center vertically
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(welcomeLabel, gbc);
        gbc.gridy = 1;
        panel.add(progressBar, gbc);
        splashScreen.setLocationRelativeTo(null);
        splashScreen.getContentPane().add(panel);
        splashScreen.setVisible(true);
    }

    // REQUIRES: JPanel panel should not be null
    // EFFECTS: Sets the JPanel's background color to a new randomly generated color
    private static void changeBackgroundColor(JPanel panel) {
        Random rand = new Random();
        int r = rand.nextInt(80) + 40;
        int g = rand.nextInt(80) + 40;
        int b = rand.nextInt(80) + 40;
        Color newColor = new Color(r, g, b);
        panel.setBackground(newColor);
    }
}