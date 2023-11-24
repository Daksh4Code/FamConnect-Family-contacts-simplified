package ui;

import javax.swing.*;
import java.awt.*;

import java.util.Random;

public class SplashScreen {
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Create the splash screen
            JFrame splashScreen = new JFrame();
            splashScreen.setSize(400, 200);
            splashScreen.setUndecorated(true);

            // Create the panel with a mouse listener for color changing
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    // Paint a background of random color
                    changeBackgroundColor(this);
                }
            };

            // Create the label with the welcome message
            JLabel welcomeLabel = new JLabel("Welcome to FamConnect!", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
            welcomeLabel.setForeground(Color.GREEN);

            // Create the progress bar
            JProgressBar progressBar = new JProgressBar();
            progressBar.setPreferredSize(new Dimension(300, 25));
            progressBar.setStringPainted(true);
            progressBar.setForeground(Color.GREEN);

            // Create a panel to hold the labels and progress bar using GridBagLayout
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 1.0; // Center vertically
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(welcomeLabel, gbc);

            gbc.gridy = 1;
            panel.add(progressBar, gbc);

            // Center the splash screen on the laptop screen
            splashScreen.setLocationRelativeTo(null);

            // Add the panel to the splash screen
            splashScreen.getContentPane().add(panel);

            // Display the splash screen
            splashScreen.setVisible(true);

            // Remove the splash screen after 4 seconds
            Timer timer = new Timer(3000, e -> {
                splashScreen.setVisible(false);
                splashScreen.dispose();

                // Launch the main GUI app here
                new FamilyContactManagerGUI();
            });
            timer.setRepeats(false);
            timer.start();

            // Update the progress bar every 100 milliseconds
            Timer progressBarTimer = new Timer(28, e -> {
                progressBar.setValue(progressBar.getValue() + 1);
            });
            progressBarTimer.start();
        });
    }

    private static void changeBackgroundColor(JPanel panel) {
        Random rand = new Random();
        int r = rand.nextInt(80) + 40;
        int g = rand.nextInt(80) + 40;
        int b = rand.nextInt(80) + 40;

        Color newColor = new Color(r, g, b);
        panel.setBackground(newColor);
    }
}