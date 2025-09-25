import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

public class TareasUrgentesFrame extends JFrame {
    private JTable tareasTable;
    private DefaultTableModel tableModel;
    private Queue<TareaPrioridad> colaTareas;
    private JFrame previousFrame;

    public TareasUrgentesFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Gestión de Tareas Urgentes - GYM MASTER");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        colaTareas = new PriorityQueue<>();
        
        // Datos de ejemplo
        colaTareas.add(new TareaPrioridad("T001", "Limpiar area de pesas", 5));
        colaTareas.add(new TareaPrioridad("T002", "Verificar sistema de sonido", 3));
        colaTareas.add(new TareaPrioridad("T003", "Revisar maquina de cardio", 1));
        colaTareas.add(new TareaPrioridad("T004", "Programar reunion con instructores", 2));
        colaTareas.add(new TareaPrioridad("T005", "Limpiar vidrios del frente", 4));
        colaTareas.add(new TareaPrioridad("T006", "Inventario de equipos de spinning", 3));
        colaTareas.add(new TareaPrioridad("T007", "Actualizar perfil de cliente", 1));
        colaTareas.add(new TareaPrioridad("T008", "Reparar la caminadora 3", 5));
        colaTareas.add(new TareaPrioridad("T009", "Revisar las luces del gimnasio", 2));
        colaTareas.add(new TareaPrioridad("T010", "Organizar sala de spinning", 4));
        colaTareas.add(new TareaPrioridad("T011", "Comprar mas toallas", 1));
        colaTareas.add(new TareaPrioridad("T012", "Limpiar los baños", 3));
        colaTareas.add(new TareaPrioridad("T013", "Actualizar software de maquinas", 2));
        colaTareas.add(new TareaPrioridad("T014", "Revisar los aires acondicionados", 5));
        colaTareas.add(new TareaPrioridad("T015", "Reemplazar los tapetes del piso", 4));

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(26, 26, 26));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Panel superior con título y botón de "Volver"
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

        // Configuración de la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Descripción", "Prioridad"}, 0) {
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
        tareasTable.setDefaultRenderer(Object.class, new PriorityTableCellRenderer());
        JScrollPane scrollPane = new JScrollPane(tareasTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones inferiores
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
        mostrarTareas(); // Se llama automáticamente al iniciar
    }

    private void mostrarTareas() {
        tableModel.setRowCount(0);
        if (colaTareas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tareas en la cola.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            List<TareaPrioridad> listaTemporal = new ArrayList<>();
            while (!colaTareas.isEmpty()) {
                TareaPrioridad tarea = colaTareas.poll();
                listaTemporal.add(tarea);
                tableModel.addRow(new Object[]{tarea.getId(), tarea.getDescripcion(), tarea.getPrioridad()});
            }
            colaTareas.addAll(listaTemporal);
        }
    }

    private void tomarSiguienteTarea() {
        if (!colaTareas.isEmpty()) {
            TareaPrioridad tareaTomada = colaTareas.poll();
            JOptionPane.showMessageDialog(this, "Has tomado la tarea más urgente:\n" +
                "ID: " + tareaTomada.getId() + "\n" +
                "Descripción: " + tareaTomada.getDescripcion() + "\n" +
                "Prioridad: " + tareaTomada.getPrioridad(), "Tarea Tomada", JOptionPane.INFORMATION_MESSAGE);
            mostrarTareas();
        } else {
            JOptionPane.showMessageDialog(this, "No hay tareas en la cola.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarNuevaTarea() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(new Color(26, 26, 26));
        JTextField descripcionField = new JTextField(20);
        descripcionField.setForeground(Color.WHITE); // Corrected
        descripcionField.setBackground(new Color(50, 50, 50)); // Corrected
        
        String[] prioridades = {"1 - Crítico (Rojo)", "2 - Alta (Naranja)", "3 - Media (Amarillo)", "4 - Baja (Azul)", "5 - Muy Baja (Verde)"};
        JComboBox<String> prioridadCombo = new JComboBox<>(prioridades);
        prioridadCombo.setForeground(Color.WHITE); // Corrected
        prioridadCombo.setBackground(new Color(50, 50, 50)); // Corrected
        
        prioridadCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    String item = (String) value;
                    int prioridad = Integer.parseInt(item.split(" ")[0]);
                    Color color = getColorForPriority(prioridad);
                    label.setForeground(color);
                }
                label.setBackground(new Color(50, 50, 50));
                return label;
            }
        });

        JLabel descripcionLabel = new JLabel("Descripción:", SwingConstants.CENTER);
        descripcionLabel.setForeground(Color.WHITE); // Corrected
        panel.add(descripcionLabel);
        panel.add(descripcionField);
        
        JLabel prioridadLabel = new JLabel("Prioridad (1 = más urgente):", SwingConstants.CENTER);
        prioridadLabel.setForeground(Color.WHITE); // Corrected
        panel.add(prioridadLabel);
        panel.add(prioridadCombo);
        
        UIManager.put("OptionPane.background", new Color(26, 26, 26));
        UIManager.put("Panel.background", new Color(26, 26, 26));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Tarea", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String nuevaDescripcion = descripcionField.getText().trim();
            int nuevaPrioridad = Integer.parseInt(((String) prioridadCombo.getSelectedItem()).split(" ")[0]);

            if (!nuevaDescripcion.isEmpty()) {
                String nuevoId = "T" + (colaTareas.size() + 16); 
                colaTareas.add(new TareaPrioridad(nuevoId, nuevaDescripcion, nuevaPrioridad));
                JOptionPane.showMessageDialog(this, "Tarea agregada: " + nuevaDescripcion, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                mostrarTareas();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una descripción para la tarea.", "Error", JOptionPane.ERROR_MESSAGE);
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
            colaTareas = nuevaCola;
            
            if (tareaEncontrada) {
                JOptionPane.showMessageDialog(this, "Tarea " + idTarea + " eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                mostrarTareas();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna tarea con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (idTarea != null) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Clase interna para el renderizador de color de la tabla
    class PriorityTableCellRenderer extends JLabel implements TableCellRenderer {
        public PriorityTableCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Object prioridadValue = table.getValueAt(row, 2);
            int prioridad = (prioridadValue instanceof Integer) ? (int) prioridadValue : 0;
            
            Color color = getColorForPriority(prioridad);
            setBackground(color);
            setForeground(Color.BLACK);
            setText(value.toString());

            return this;
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
    
    // Método auxiliar para obtener color por prioridad
    private Color getColorForPriority(int prioridad) {
        switch (prioridad) {
            case 1: return new Color(255, 100, 100);
            case 2: return new Color(255, 165, 0);
            case 3: return new Color(255, 255, 100);
            case 4: return new Color(173, 216, 230);
            case 5: return new Color(144, 238, 144);
            default: return Color.WHITE;
        }
    }
}

class TareaPrioridad implements Comparable<TareaPrioridad> {
    private String id;
    private String descripcion;
    private int prioridad;

    public TareaPrioridad(String id, String descripcion, int prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(TareaPrioridad otraTarea) {
        return Integer.compare(this.prioridad, otraTarea.prioridad);
    }
}