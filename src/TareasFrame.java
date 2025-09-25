import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TareasFrame extends JFrame {

    private JTextField tareaTextField;
    private JButton agregarButton;

    public TareasFrame() {
        setTitle("Agregar Tarea");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(20, 30, 48));

        tareaTextField = new JTextField(25);
        tareaTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        
        agregarButton = new JButton("Agregar Tarea");
        agregarButton.setBackground(new Color(74, 189, 172));
        agregarButton.setForeground(Color.WHITE);
        agregarButton.setFocusPainted(false);

        // Agrega un listener para guardar la tarea
        agregarButton.addActionListener(e -> {
            String nuevaTarea = tareaTextField.getText();
            if (!nuevaTarea.isEmpty()) {
                // Aquí va la lógica para guardar la tarea en tu sistema
                // Puedes guardarla en una lista, un archivo, etc.
                JOptionPane.showMessageDialog(this, "Tarea agregada: " + nuevaTarea);
                tareaTextField.setText(""); // Limpia el campo
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una tarea.");
            }
        });

        panel.add(new JLabel("Nueva Tarea: ")).setForeground(Color.WHITE);
        panel.add(tareaTextField);
        panel.add(agregarButton);

        add(panel);
    }
}