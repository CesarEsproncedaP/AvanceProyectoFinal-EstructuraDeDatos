import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.text.*;

public class TareasUrgentesFrame extends JFrame {
    private JTable tareasTable;
    private DefaultTableModel tableModel;
    private PriorityQueue<TareaPrioridad> colaTareas;
    private JFrame previousFrame;
    private static final Date CURRENT_DATE = new Date(); 
    
    public TareasUrgentesFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Gestión de Tareas Urgentes - GYM MASTER");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        colaTareas = Main.getColaTareas();

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(26, 26, 26));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Gestión de Tareas Urgentes", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        
        JButton backButton = createStyledButton("Volver", new Color(255, 204, 0), new Color(200, 150, 0));
        backButton.addActionListener(e -> {
            this.dispose();
            if (previousFrame != null) {
                previousFrame.setVisible(true);
            }
        });
        
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButtonPanel.setOpaque(false);
        backButtonPanel.add(backButton);
        topPanel.add(backButtonPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Descripción", "Fecha Entrega", "Tiempo Estimado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tareasTable = new JTable(tableModel);
        tareasTable.setRowHeight(25);
        tareasTable.setFont(new Font("Arial", Font.PLAIN, 14));
        tareasTable.setBackground(new Color(40, 40, 40));
        tareasTable.setForeground(Color.WHITE);
        tareasTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tareasTable.getTableHeader().setBackground(new Color(50, 50, 50));
        tareasTable.getTableHeader().setForeground(new Color(74, 189, 172));
        tareasTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(40, 40, 40)); 
                c.setForeground(Color.WHITE);
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tareasTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomButtonPanel.setOpaque(false);

        JButton showButton = createStyledButton("Mostrar Tareas", new Color(74, 189, 172), new Color(47, 128, 114));
        showButton.addActionListener(e -> mostrarTareas());

        JButton takeNextButton = createStyledButton("Tomar Siguiente Tarea", new Color(255, 105, 180), new Color(200, 80, 140));
        takeNextButton.addActionListener(e -> tomarSiguienteTarea());

        JButton addButton = createStyledButton("Agregar Tarea", new Color(47, 128, 237), new Color(30, 90, 180));
        addButton.addActionListener(e -> agregarNuevaTarea());

        JButton deleteButton = createStyledButton("Eliminar Tarea", new Color(255, 50, 50), new Color(180, 30, 30));
        deleteButton.addActionListener(e -> eliminarTareaPorId());



        bottomButtonPanel.add(showButton);
        bottomButtonPanel.add(takeNextButton);
        bottomButtonPanel.add(addButton);
        bottomButtonPanel.add(deleteButton);

        mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        mostrarTareas();
    }

    private void mostrarTareas() {
        tableModel.setRowCount(0);
        if (colaTareas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tareas en la cola.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.List<TareaPrioridad> listaTemporal = new java.util.ArrayList<>(colaTareas);
            Collections.sort(listaTemporal, Comparator.comparing(TareaPrioridad::getFechaEntrega)); 
            for (TareaPrioridad tarea : listaTemporal) {
                tableModel.addRow(new Object[]{tarea.getId(), tarea.getDescripcion(), sdf.format(tarea.getFechaEntrega()), tarea.getTiempoEstimado()});
            }
        }
    }

    private void tomarSiguienteTarea() {
        if (!colaTareas.isEmpty()) {
            TareaPrioridad tareaTomada = colaTareas.poll();
            Main.getHashTareas().remove(tareaTomada.getId());
            JOptionPane.showMessageDialog(this, "Has tomado la tarea más urgente:\n" +
                "ID: " + tareaTomada.getId() + "\n" +
                "Descripción: " + tareaTomada.getDescripcion(), "Tarea Tomada", JOptionPane.INFORMATION_MESSAGE);
            mostrarTareas();
        } else {
            JOptionPane.showMessageDialog(this, "No hay tareas en la cola.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarNuevaTarea() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(new Color(26, 26, 26));
        JTextField descripcionField = new JTextField(20);
        descripcionField.setForeground(Color.WHITE);
        descripcionField.setBackground(new Color(50, 50, 50));
        
        JTextField fechaField = new JTextField(20);
        fechaField.setForeground(Color.WHITE);
        fechaField.setBackground(new Color(50, 50, 50));
        JTextField tiempoField = new JTextField(20);
        tiempoField.setForeground(Color.WHITE);
        tiempoField.setBackground(new Color(50, 50, 50));

        JLabel descripcionLabel = new JLabel("Descripción:", SwingConstants.CENTER);
        descripcionLabel.setForeground(Color.WHITE);
        panel.add(descripcionLabel);
        panel.add(descripcionField);

        JLabel fechaLabel = new JLabel("Fecha Entrega (yyyy-MM-dd):", SwingConstants.CENTER);
        fechaLabel.setForeground(Color.WHITE);
        panel.add(fechaLabel);
        panel.add(fechaField);

        JLabel tiempoLabel = new JLabel("Tiempo Estimado (horas):", SwingConstants.CENTER);
        tiempoLabel.setForeground(Color.WHITE);
        panel.add(tiempoLabel);
        panel.add(tiempoField);
        
        UIManager.put("OptionPane.background", new Color(26, 26, 26));
        UIManager.put("Panel.background", new Color(26, 26, 26));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Tarea", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String nuevaDescripcion = descripcionField.getText().trim();
            String fechaStr = fechaField.getText().trim();
            String tiempoStr = tiempoField.getText().trim();

            if (!nuevaDescripcion.isEmpty() && !fechaStr.isEmpty() && !tiempoStr.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date nuevaFecha = sdf.parse(fechaStr);
                    int nuevoTiempo = Integer.parseInt(tiempoStr);
                    String nuevoId = "T" + (Main.getHashTareas().size() + 1);
                    TareaPrioridad nuevaTarea = new TareaPrioridad(nuevoId, nuevaDescripcion, nuevaFecha, nuevoTiempo);
                    colaTareas.add(nuevaTarea);
                    Main.getHashTareas().put(nuevoId, nuevaTarea);
                    JOptionPane.showMessageDialog(this, "Tarea agregada: " + nuevaDescripcion, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    mostrarTareas();
                } catch (NumberFormatException | ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato inválido para fecha o tiempo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarTareaPorId() {
        UIManager.put("OptionPane.background", new Color(26, 26, 26));
        UIManager.put("Panel.background", new Color(26, 26, 26));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("TextField.background", new Color(50, 50, 50));
        UIManager.put("TextField.foreground", Color.WHITE);

        String idTarea = JOptionPane.showInputDialog(this, "Ingresa el ID de la tarea a eliminar:", "Eliminar Tarea por ID", JOptionPane.PLAIN_MESSAGE);
        
        if (idTarea != null && !idTarea.trim().isEmpty()) {
            boolean tareaEncontrada = false;
            Queue<TareaPrioridad> nuevaCola = new PriorityQueue<>();
            while (!colaTareas.isEmpty()) {
                TareaPrioridad tarea = colaTareas.poll();
                if (!tarea.getId().equalsIgnoreCase(idTarea)) {
                    nuevaCola.add(tarea);
                } else {
                    tareaEncontrada = true;
                }
            }
            colaTareas.clear();
            colaTareas.addAll(nuevaCola);
            if (tareaEncontrada) {
                Main.getHashTareas().remove(idTarea.toUpperCase());
                JOptionPane.showMessageDialog(this, "Tarea " + idTarea + " eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                mostrarTareas();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna tarea con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (idTarea != null) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private JButton createStyledButton(String text, Color baseColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(baseColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
}