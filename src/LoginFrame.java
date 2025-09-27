import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;

    // Referencias a las otras ventanas para la navegación
    private MainFrame mainFrame;
    private RegistroFrame registroFrame;

    public LoginFrame() {
        setTitle("Iniciar Sesión - GYM MASTER");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(20, 30, 48));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel titleLabel = new JLabel("Bienvenido a 67GYM", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setOpaque(false);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField(15);

        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(formPanel);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        
        JButton loginButton = createStyledButton("Iniciar Sesión", new Color(74, 189, 172), new Color(47, 128, 114));
        loginButton.addActionListener(e -> attemptLogin());
        
        JButton registerButton = createStyledButton("Registrarse", new Color(255, 105, 180), new Color(200, 80, 140));
        registerButton.addActionListener(e -> {
            // Oculta la ventana actual de login y muestra la de registro
            this.setVisible(false);
            if (registroFrame != null) {
                registroFrame.setVisible(true);
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
    
    // Método para pasar las referencias de las otras ventanas
    public void setFrames(MainFrame mainFrame, RegistroFrame registroFrame) {
        this.mainFrame = mainFrame;
        this.registroFrame = registroFrame;
    }

    private void attemptLogin() {
        String user = userField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (user.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa usuario y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica contra el mapa de usuarios
        if (Main.users.containsKey(user) && Main.users.get(user).equals(password)) {
            JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            if (mainFrame != null) {
                mainFrame.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos. Inténtelo de nuevo.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
            userField.setText("");
            passwordField.setText("");
        }
    }
    
    private JButton createStyledButton(String text, Color baseColor, Color hoverColor) {
        JButton button = new JButton(text) {
            private boolean hovered = false;
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                Color c1 = hovered ? hoverColor.brighter() : baseColor;
                Color c2 = hovered ? hoverColor.darker() : baseColor.darker();
                GradientPaint gp = new GradientPaint(0, 0, c1, w, h, c2); 
                g2.setPaint(gp);
                g2.fillRect(0, 0, w, h);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setContentAreaFilled(false);
                setBorderPainted(false);
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) { hovered = true; repaint(); }
                    @Override
                    public void mouseExited(MouseEvent e) { hovered = false; repaint(); }
                });
            }
        };
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}