import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClassSeatFrame extends JFrame {
    private Clase clase;
    private final int ROWS = 5;
    private final int COLS = 5;

    // En este constructor se configur la ventana para seleccionar asientos de una clase específica.
    public ClassSeatFrame(Clase clase) {
        this.clase = Objects.requireNonNull(clase); // Asegura que la clase no sea null
        setTitle("Seleccionar Asiento para: " + clase.getNombre());
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(ROWS + 1, COLS, 10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(20, 30, 48));

        // Es para poner el título de la Clase
        JLabel titleLabel = new JLabel("Seleccionar Asiento: " + clase.getNombre(), SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        mainPanel.setLayout(new GridLayout(ROWS + 1, COLS, 10, 10));
        mainPanel.add(titlePanel);
        for(int k = 0; k < COLS - 1; k++) { // Rellena el resto de la primera fila si el GridLayout tiene más columnas
            mainPanel.add(new JLabel("")); 
        }

        String[][] mapaAsientos = clase.getMapaAsientos();

        // Aquí se crean los botones de losasientos en un loop para filas y columnas.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                final int fila = i;
                final int columna = j;
                final int seatNumber = i * COLS + j + 1;

                JButton asientoButton = new JButton(String.valueOf(seatNumber));
                asientoButton.setPreferredSize(new Dimension(60, 60));
                asientoButton.setFont(new Font("Arial", Font.BOLD, 16));
                asientoButton.setForeground(Color.WHITE);
                asientoButton.setFocusPainted(false);
                asientoButton.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2));

                // Se obiene el estado del asiento
                String status = mapaAsientos[fila][columna]; 

                if ("OCUPADO".equals(status)) {
                    // Si está OCUPADO
                    asientoButton.setBackground(Color.RED);
                    asientoButton.setEnabled(false);
                } else {
                    // Si está DISPONIBLE
                    asientoButton.setBackground(new Color(74, 189, 172));
                    asientoButton.setEnabled(true);
                }

                // Se agrega un listener al botón para reservar el asiento si es que está disponible y se cambia el color a rojo y se muestra un mensaje.
                asientoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!"OCUPADO".equals(clase.getMapaAsientos()[fila][columna])) {
                            clase.reservarAsiento(fila, columna); 
                            
                            asientoButton.setBackground(Color.RED);
                            asientoButton.setEnabled(false);

                            String mensaje = "Has reservado el asiento " + seatNumber + " para la clase de " + clase.getNombre();
                            JOptionPane.showMessageDialog(ClassSeatFrame.this, mensaje, "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
                            
                            ClassSeatFrame.this.dispose(); 
                        }
                    }
                });
                
                mainPanel.add(asientoButton);
            }
        }
        
        add(mainPanel);
    }
}