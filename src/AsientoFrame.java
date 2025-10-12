import javax.swing.*;
import java.awt.*;

public class AsientoFrame extends JFrame {
    private JFrame previousFrame;
    private JPanel asientosPanel;
    private JButton[] botonesAsiento;

    // En este constructor se configura la ventana para seleccionar asientos y el botón de volver el cual solamente hace volver al frame anterior.
    public AsientoFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Seleccionar Asiento");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(26, 26, 26));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Selecciona un Asiento", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        asientosPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        asientosPanel.setOpaque(false);
        botonesAsiento = new JButton[20];
        crearAsientos();
        mainPanel.add(asientosPanel, BorderLayout.CENTER);

        JButton backButton = createStyledButton("Volver", new Color(255, 204, 0), new Color(200, 150, 0));
        backButton.addActionListener(e -> {
            this.dispose();
            previousFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Y en este método se crean los botones de los asientos en un ciclo, cada uno con un número para hacerlo más fácil de ver y elegir.
    private void crearAsientos() {
        for (int i = 0; i < 20; i++) {
            final int asientoNumero = i + 1;
            botonesAsiento[i] = new JButton(String.valueOf(asientoNumero));
            botonesAsiento[i].setFont(new Font("Arial", Font.BOLD, 18));
            botonesAsiento[i].setForeground(Color.BLACK);
            botonesAsiento[i].setBackground(new Color(144, 238, 144));
            
            botonesAsiento[i].addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Has reservado el asiento " + asientoNumero, "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                previousFrame.setVisible(true);
            });
            asientosPanel.add(botonesAsiento[i]);
        }
    }
    
    // Aquí creo un botón con los colores base
    private JButton createStyledButton(String text, Color baseColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(baseColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
}