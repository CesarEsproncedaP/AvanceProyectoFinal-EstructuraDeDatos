import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Clase para la ventana principal del sistema de gestión del gimnasio
public class MainFrame extends JFrame {

    private JFrame loginFrame;
    private JLabel userLabel; // Etiqueta para mostrar el usuario actual

    // Constructor de la ventana
    public MainFrame(JFrame loginFrame) {
        this.loginFrame = loginFrame;
        setTitle("Sistema de Gestión de Gimnasio - 67GYM");
        setSize(850, 600);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al salir

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(20, 30, 48));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Panel superior con título y usuario
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("67GYM", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        String userName = Main.currentUser != null ? Main.currentUser.substring(0, 1).toUpperCase() + Main.currentUser.substring(1) : "Invitado";
        userLabel = new JLabel("Usuario Actual: " + userName, SwingConstants.RIGHT);
        userLabel.setForeground(new Color(255, 204, 0));
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(userLabel, BorderLayout.NORTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel de botones en cuadrícula
        JPanel buttonGridPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonGridPanel.setOpaque(false);

        // Botón para tareas urgentes (solo admin)
        JButton tareasButton = createStyledButton("Tareas Urgentes", new Color(74, 189, 172));
        tareasButton.addActionListener(e -> {
            if ("admin".equalsIgnoreCase(Main.currentUser)) {
                TareasUrgentesFrame tareasFrame = new TareasUrgentesFrame(this);
                tareasFrame.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Acceso denegado. Solo el usuario 'admin' puede acceder a Tareas Urgentes.", "Permiso Requerido", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón para clases programadas
        JButton clasesButton = createStyledButton("Clases Programadas", new Color(255, 105, 180));
        clasesButton.addActionListener(e -> {
            ClasesProgramadasFrame clasesFrame = new ClasesProgramadasFrame(this);
            clasesFrame.setVisible(true);
            this.setVisible(false);
        });

        // Botón para inventario de máquinas
        JButton inventarioButton = createStyledButton("Inventario de Máquinas", new Color(47, 128, 237));
        inventarioButton.addActionListener(e -> {
            InventarioMaquinasFrame inventarioFrame = new InventarioMaquinasFrame(this);
            inventarioFrame.setVisible(true);
            this.setVisible(false);
        });

        // Botón para gestión avanzada
        JButton gestionButton = createStyledButton("Gestión Avanzada", new Color(255, 193, 7));
        gestionButton.addActionListener(e -> {
            GestionAvanzadaFrame gestionFrame = new GestionAvanzadaFrame(this);
            gestionFrame.setVisible(true);
            this.setVisible(false);
        });

        buttonGridPanel.add(tareasButton);
        buttonGridPanel.add(clasesButton);
        buttonGridPanel.add(inventarioButton);
        buttonGridPanel.add(gestionButton);
        
        mainPanel.add(buttonGridPanel, BorderLayout.CENTER);

        // Panel inferior con botones de volver y salir
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottomPanel.setOpaque(false);
        
        JButton backButton = createStyledButton("Volver", new Color(255, 105, 180));
        backButton.addActionListener(e -> {
            this.dispose();
            loginFrame.setVisible(true);
        });
        
        JButton logoutButton = createStyledButton("Salir", new Color(255, 50, 50));
        logoutButton.addActionListener(e -> {
            System.exit(0);
        });
        
        bottomPanel.add(backButton);
        bottomPanel.add(logoutButton);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Actualiza la etiqueta del usuario
    public void updateUserLabel() {
        String userName = Main.currentUser != null ? Main.currentUser.substring(0, 1).toUpperCase() + Main.currentUser.substring(1) : "Invitado";
        userLabel.setText("Usuario Actual: " + userName);
    }

    // Crea botones estilizados con efecto hover
    private JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text) {
            private boolean hovered = false;
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                Color c1 = hovered ? baseColor.brighter() : baseColor;
                Color c2 = hovered ? baseColor.darker() : baseColor.darker();
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
        button.setFont(new Font("Arial", Font.BOLD, 22));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}