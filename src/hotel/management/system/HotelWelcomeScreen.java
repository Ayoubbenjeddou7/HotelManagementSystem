/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.management.system;

/**
 *
 * @author Z230
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

public class HotelWelcomeScreen extends JFrame {

    // Updated color scheme as requested
    private final Color GOLD = new Color(241, 211, 134);  // Primary accent
    private final Color NAVY = new Color(0, 51, 102);     // Dark background
    private final Color WHITE = new Color(255, 255, 255);  // Text/foreground
    private final Color BLACK = new Color(0, 0, 0);        // For contrast

    private final int WIDTH = 1000;
    private final int HEIGHT = 650;
    private WelcomePanel welcomePanel;

    public HotelWelcomeScreen() {
        setTitle("Hotel Management System");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, WIDTH, HEIGHT, 20, 20));

        welcomePanel = new WelcomePanel();
        add(welcomePanel);

        // Fade-in animation
        setOpacity(0f);
        Timer fadeTimer = new Timer(20, e -> {
            float opacity = getOpacity();
            if (opacity < 1f) {
                setOpacity(opacity + 0.05f);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        fadeTimer.start();
    }

    class WelcomePanel extends JPanel {

        private JButton btnLogin, btnRegister;
        private float panelOpacity = 0f;

        public WelcomePanel() {
            setLayout(new BorderLayout());
            setBackground(NAVY); // Use the deep navy as base

            createUI();

            // Fade-in animation
            Timer fadeTimer = new Timer(30, e -> {
                panelOpacity += 0.03f;
                if (panelOpacity >= 1f) {
                    panelOpacity = 1f;
                    ((Timer) e.getSource()).stop();
                }
                repaint();
            });
            fadeTimer.start();
        }

        private void createUI() {
            // Main content panel with grid layout
            JPanel contentPanel = new JPanel(new GridBagLayout());
            contentPanel.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.insets = new Insets(10, 0, 30, 0);

            // Hotel name label with gold color
            JLabel lblHotelName = new JLabel("GRANDE HORIZON HOTEL");
            lblHotelName.setFont(new Font("Garamond", Font.BOLD, 48));
            lblHotelName.setForeground(GOLD);
            lblHotelName.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            // Welcome message in white
            JLabel lblWelcome = new JLabel("Management System");
            lblWelcome.setFont(new Font("Garamond", Font.PLAIN, 28));
            lblWelcome.setForeground(WHITE);

            // Button panel with 30px gap between buttons
            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 30, 0));
            buttonPanel.setOpaque(false);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 0, 100));

            // Login Button (navy with gold text)
            btnLogin = createStyledButton("STAFF LOGIN", NAVY, GOLD);
            btnLogin.addActionListener(e -> {
                new Login().setVisible(true);
                dispose();
            });

            // Register Button (gold with navy text)
            btnRegister = createStyledButton("REGISTER", GOLD, NAVY);
            btnRegister.addActionListener(e -> {
                new Register().setVisible(true);
                dispose();
            });

            buttonPanel.add(btnLogin);
            buttonPanel.add(btnRegister);

            // Add components to content panel
            contentPanel.add(lblHotelName, gbc);
            contentPanel.add(lblWelcome, gbc);
            contentPanel.add(buttonPanel, gbc);

            JPanel footerPanel = new JPanel();
            footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
            footerPanel.setOpaque(false);
            footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

            JLabel lblFooter = new JLabel("© 2025 Grande Horizon Hotels. All Rights Reserved.");
            lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            lblFooter.setForeground(new Color(0, 51, 102, 180));

            footerPanel.add(Box.createHorizontalGlue());
            footerPanel.add(lblFooter);
            footerPanel.add(Box.createHorizontalGlue());

            add(footerPanel, BorderLayout.SOUTH);
            add(contentPanel, BorderLayout.CENTER);
        }

        private JButton createStyledButton(String text, Color bgColor, Color textColor) {
            JButton button = new JButton(text) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Button state changes
                    if (getModel().isPressed()) {
                        g2.setColor(bgColor.darker());
                    } else if (getModel().isRollover()) {
                        g2.setColor(bgColor.brighter());
                    } else {
                        g2.setColor(bgColor);
                    }

                    // Draw button shape with crisp edges
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                    // Draw border
                    g2.setColor(BLACK);
                    g2.setStroke(new BasicStroke(1.5f));
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

                    // Draw text
                    g2.setColor(textColor);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(getText())) / 2;
                    int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                    g2.drawString(getText(), x, y);

                    g2.dispose();
                }
            };

            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
            button.setPreferredSize(new Dimension(250, 60));
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            return button;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw solid navy background
            g2d.setColor(NAVY);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // Add gold accent at bottom
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, panelOpacity));
            g2d.setColor(GOLD);
            g2d.fillRect(0, getHeight() - 100, getWidth(), 100);

            // Add subtle radial gradient overlay
            GradientPaint gradient = new GradientPaint(
                    getWidth() / 2, getHeight() / 2, new Color(255, 255, 255, 10),
                    getWidth(), getHeight(), new Color(0, 0, 0, 20)
            );
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelWelcomeScreen frame = new HotelWelcomeScreen();
            frame.setVisible(true);
        });
    }
}
