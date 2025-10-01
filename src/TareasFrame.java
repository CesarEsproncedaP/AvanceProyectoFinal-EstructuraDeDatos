import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Clase para la ventana de agregar tareas
public class TareasFrame extends JFrame {

    private JTextField tareaTextField; // Campo para ingresar la nueva tarea
    private JButton agregarButton; // Botón para agregar la tarea

    // Constructor de la ventana
    public TareasFrame() {
        setTitle("Agregar Tarea");
        setSize(400, 200);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al salir

        // Panel principal
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(20, 30, 48));

        // Campo de texto para la tarea
        tareaTextField = new JTextField(25);
        tareaTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Botón de agregar tarea
        agregarButton = new JButton("Agregar Tarea");
        agregarButton.setBackground(new Color(74, 189, 172));
        agregarButton.setForeground(Color.WHITE);
        agregarButton.setFocusPainted(false);

        // Acción para agregar tarea
        agregarButton.addActionListener(e -> {
            String nuevaTarea = tareaTextField.getText();
            if (!nuevaTarea.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tarea agregada: " + nuevaTarea);
                tareaTextField.setText(""); // Limpia el campo
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una tarea.");
            }
        });

        // Añadir componentes al panel
        panel.add(new JLabel("Nueva Tarea: ")).setForeground(Color.WHITE);
        panel.add(tareaTextField);
        panel.add(agregarButton);

        add(panel);
    }
}