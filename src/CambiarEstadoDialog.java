import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CambiarEstadoDialog extends JDialog {
    private JTable maquinasTable;
    private DefaultTableModel tableModel;
    private List<Maquina> listaMaquinas;
    private JComboBox<String> estadoComboBox;
    private boolean stateChanged = false;

    public CambiarEstadoDialog(JFrame parentFrame, List<Maquina> listaMaquinas) {
        super(parentFrame, "Cambiar Estado de Máquina", true);
        this.listaMaquinas = listaMaquinas;
        setSize(500, 400);
        setLocationRelativeTo(parentFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(26, 26, 26));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        maquinasTable = new JTable(tableModel);
        maquinasTable.setBackground(new Color(40, 40, 40));
        maquinasTable.setForeground(Color.WHITE);
        maquinasTable.getTableHeader().setBackground(new Color(50, 50, 50));
        maquinasTable.getTableHeader().setForeground(new Color(74, 189, 172));
        JScrollPane scrollPane = new JScrollPane(maquinasTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de control
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        controlPanel.setOpaque(false);

        JLabel estadoLabel = new JLabel("Nuevo Estado:");
        estadoLabel.setForeground(Color.WHITE);

        estadoComboBox = new JComboBox<>(new String[]{"Operativa", "En reparación"});
        estadoComboBox.setBackground(new Color(50, 50, 50));
        estadoComboBox.setForeground(Color.WHITE);

        estadoComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setForeground(Color.WHITE);
                label.setBackground(new Color(50, 50, 50));
                return label;
            }
        });

        JButton cambiarButton = createStyledButton("Cambiar", new Color(74, 189, 172), new Color(47, 128, 114));
        cambiarButton.addActionListener(e -> cambiarEstado());

        // Botón "Volver"
        JButton backButton = createStyledButton("Volver", new Color(255, 204, 0), new Color(200, 150, 0));
        backButton.addActionListener(e -> dispose());

        controlPanel.add(estadoLabel);
        controlPanel.add(estadoComboBox);
        controlPanel.add(cambiarButton);
        controlPanel.add(backButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        cargarMaquinas();
    }
    
    // Nuevo método para obtener el estado del cambio
    public boolean isStateChanged() {
        return stateChanged;
    }

    private void cargarMaquinas() {
        tableModel.setRowCount(0);
        for (Maquina maquina : listaMaquinas) {
            tableModel.addRow(new Object[]{maquina.getId(), maquina.getNombre(), maquina.getEstado()});
        }
    }

    private void cambiarEstado() {
        int selectedRow = maquinasTable.getSelectedRow();
        if (selectedRow != -1) {
            String idMaquina = (String) maquinasTable.getValueAt(selectedRow, 0);
            String nuevoEstado = (String) estadoComboBox.getSelectedItem();

            for (Maquina maquina : listaMaquinas) {
                if (maquina.getId().equals(idMaquina)) {
                    maquina.setEstado(nuevoEstado);
                    JOptionPane.showMessageDialog(this, "El estado de la máquina " + maquina.getNombre() + " ha sido cambiado a: " + nuevoEstado, "Estado Cambiado", JOptionPane.INFORMATION_MESSAGE);
                    cargarMaquinas();
                    stateChanged = true;
                    dispose();
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una máquina de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton createStyledButton(String text, Color baseColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(baseColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
}