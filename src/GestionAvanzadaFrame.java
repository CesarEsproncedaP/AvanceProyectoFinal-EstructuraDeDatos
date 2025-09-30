import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Comparator; // Importación necesaria para Collections.sort si se usa, aunque aquí se llama TaskOptimizer.mergeSort

public class GestionAvanzadaFrame extends JFrame {
    private ArbolBinarioEmpleados arbolEmpleados;
    private Map<String, Empleado> hashEmpleados;
    private PriorityQueue<TareaPrioridad> colaTareas;
    
    private GradientPanel mainPanel; 
    private JFrame previousFrame;
    
    public GestionAvanzadaFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Gestión Avanzada del Gimnasio");
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        arbolEmpleados = Main.getArbolEmpleados();  
        hashEmpleados = Main.getHashEmpleados(); 
        colaTareas = Main.getColaTareas(); 
        
        Color gradientStart = new Color(30, 30, 30);
        Color gradientEnd = new Color(50, 70, 90);
        
        Color arbolAccent = new Color(100, 255, 200);
        Color hashAccent = new Color(255, 100, 100);
        Color colasAccent = new Color(150, 100, 255);
        
        mainPanel = new GradientPanel(gradientStart, gradientEnd);
        mainPanel.setLayout(new CardLayout());
        
        JPanel arbolPanel = createArbolPanel(mainPanel.getBackground(), arbolAccent); 
        JPanel hashPanel = createHashPanel(mainPanel.getBackground(), hashAccent); 
        JPanel colasPanel = createColasPanel(mainPanel.getBackground(), colasAccent);
        
        mainPanel.add(arbolPanel, "Arboles");
        mainPanel.add(hashPanel, "Hashes");
        mainPanel.add(colasPanel, "Colas");

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 25));
        navPanel.setOpaque(false);
        JButton arbolButton = createStyledButton("Árboles Binarios", arbolAccent, arbolAccent.darker());
        JButton hashButton = createStyledButton("Tablas Hash", hashAccent, hashAccent.darker());
        JButton colasButton = createStyledButton("Colas de Prioridad", colasAccent, colasAccent.darker());
        JButton backButton = createStyledButton("Volver", new Color(255, 105, 180), new Color(200, 80, 140));
        
        arbolButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "Arboles");
            mainPanel.revalidate(); 
            mainPanel.repaint();
        });
        
        hashButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "Hashes");
            mainPanel.revalidate(); 
            mainPanel.repaint();
        });
        
        colasButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "Colas");
            mainPanel.revalidate(); 
            mainPanel.repaint(); 
        });

        backButton.addActionListener(e -> {
            this.dispose();
            if (previousFrame != null) {
                previousFrame.setVisible(true);
            }
        });

        navPanel.add(arbolButton);
        navPanel.add(hashButton);
        navPanel.add(colasButton);
        navPanel.add(backButton);

        GradientPanel containerPanel = new GradientPanel(gradientStart, gradientEnd); 
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        containerPanel.add(navPanel, BorderLayout.NORTH);
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        
        add(containerPanel);
        
        this.pack();
        setSize(1000, 750); 
    }
    


    private JPanel createArbolPanel(Color bg, Color accent) {
        JPanel panel = new JPanel(new BorderLayout(20, 20)); 
        panel.setOpaque(false); 
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel title = createTitleLabel("Gestión de Empleados (Árboles Binarios)", accent);
        panel.add(title, BorderLayout.NORTH);
        
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(30, 30, 30));
        displayArea.setForeground(new Color(200, 200, 200));
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        displayArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accent, 3), "Empleados por Departamento (Ordenado)", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Arial", Font.BOLD, 16), Color.WHITE));
        
        JScrollPane scroll = new JScrollPane(displayArea);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        buttonPanel.setOpaque(false);

        JButton showInordenButton = createStyledButton("Mostrar Empleados (Inorden)", accent, accent.darker());
        showInordenButton.addActionListener(e -> {
            displayArea.setText("");
            if (arbolEmpleados.getRaiz() == null) {
                displayArea.append("El árbol de empleados está vacío.");
            } else {
                 displayArea.append("ID     | NOMBRE                  | DEPARTAMENTO\n");
                 displayArea.append("--------------------------------------------------------\n");
                 arbolEmpleados.mostrarInorden(arbolEmpleados.getRaiz(), displayArea); 
            }
        });

        JButton searchDeptButton = createStyledButton("Buscar por Departamento", accent, accent.darker());
        searchDeptButton.addActionListener(e -> {
            String depto = JOptionPane.showInputDialog(this, "Ingrese el departamento a buscar:", "Buscar Empleados", JOptionPane.PLAIN_MESSAGE);
            if (depto != null && !depto.trim().isEmpty()) {
                displayArea.setText("Resultados para Departamento: " + depto + "\n");
                displayArea.append("--------------------------------------------------------\n");
                int initialLength = displayArea.getText().length();
                arbolEmpleados.buscarPorDepartamento(arbolEmpleados.getRaiz(), depto, displayArea); 
                if (displayArea.getText().length() == initialLength) {
                     displayArea.append("No se encontraron empleados en el departamento '" + depto + "'.\n");
                }
            } else if (depto != null) { 
                 JOptionPane.showMessageDialog(this, "Debe ingresar un departamento válido para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        buttonPanel.add(showInordenButton);
        buttonPanel.add(searchDeptButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHashPanel(Color bg, Color accent) {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setOpaque(false); 
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel title = createTitleLabel("Gestión de Datos (Tablas Hash)", accent);
        panel.add(title, BorderLayout.NORTH);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(30, 30, 30));
        displayArea.setForeground(new Color(200, 200, 200));
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        displayArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accent, 3), "Búsqueda Rápida de Empleados por ID", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Arial", Font.BOLD, 16), Color.WHITE));
        
        JScrollPane scroll = new JScrollPane(displayArea);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        buttonPanel.setOpaque(false);

        JButton searchEmpButton = createStyledButton("Buscar Empleado por ID", accent, accent.darker());
        searchEmpButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado (ej. E001):", "Buscar Empleado", JOptionPane.PLAIN_MESSAGE);
            if (id != null && !id.trim().isEmpty()) {
                Empleado emp = hashEmpleados.get(id.toUpperCase());
                if (emp != null) {
                    displayArea.setText("Empleado encontrado:\n");
                    displayArea.append("ID: " + emp.getId() + "\n");
                    displayArea.append("Nombre: " + emp.getNombre() + "\n");
                    displayArea.append("Departamento: " + emp.getDepartamento() + "\n");
                } else {
                    displayArea.setText("Empleado no encontrado con ID: " + id + ".");
                }
            } else if (id != null) {
                 JOptionPane.showMessageDialog(this, "Debe ingresar un ID de empleado válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton showAllButton = createStyledButton("Mostrar Todos los Empleados (Hash)", accent, accent.darker());
        showAllButton.addActionListener(e -> {
            displayArea.setText("Todos los empleados registrados (Hash):\n");
            displayArea.append("--------------------------------------------------------\n");
            if (hashEmpleados.isEmpty()) {
                displayArea.append("No hay empleados registrados en la tabla hash.");
            } else {
                hashEmpleados.values().forEach(emp -> 
                    displayArea.append(String.format("ID: %s | Nombre: %-20s | Departamento: %s\n", 
                                                    emp.getId(), emp.getNombre(), emp.getDepartamento())));
            }
        });
        
        buttonPanel.add(searchEmpButton);
        buttonPanel.add(showAllButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    
    

    private JPanel createColasPanel(Color bg, Color accent) {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel title = createTitleLabel("Gestión de Tareas (Colas de Prioridad)", accent);
        panel.add(title, BorderLayout.NORTH);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(30, 30, 30));
        displayArea.setForeground(new Color(200, 200, 200));
        // Usamos Monospaced para asegurar la alineación de las columnas
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 15)); 
        
        displayArea.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accent, 3), "Estadísticas y Optimización de Tareas", 
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
                new Font("Arial", Font.BOLD, 16), Color.WHITE));
        
        JScrollPane scroll = new JScrollPane(displayArea);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel buttonGridPanel = new JPanel(new GridLayout(1, 2, 25, 0)); 
        buttonGridPanel.setOpaque(false);

        // --- Botón 1: Calcular Estadísticas (Recursividad) ---
        JButton calcButton = createStyledButton("Calcular Estadísticas (Rec.)", accent, accent.darker());
        calcButton.addActionListener(e -> {
            PriorityQueue<TareaPrioridad> cola = Main.getColaTareas();
            if (cola.isEmpty()) {
                displayArea.setText("No hay tareas restantes en la cola.");
                return;
            }
            List<TareaPrioridad> listaTareas = new ArrayList<>(cola);
            double totalTiempo = Main.calcularTiempoTotalRecursivo(listaTareas, 0); 
            
            // FORMATO MEJORADO PARA LA RECURSIVIDAD
            displayArea.setText("ESTADÍSTICAS RECURSIVAS:\n");
            displayArea.append("--------------------------------------------------------\n");
            displayArea.append(String.format("Número de tareas restantes: %d\n", listaTareas.size()));
            displayArea.append(String.format("Tiempo total estimado:      %.0f horas\n", totalTiempo));
            displayArea.append("--------------------------------------------------------\n");
            displayArea.append("Presione 'Optimizar Distribución (D&V)' para ordenar.");
        });
        
        // --- Botón 2: Optimización (Divide y Vencerás) ---
        JButton optimizeButton = createStyledButton("Optimizar Distribución (D&V)", accent, accent.darker());
        optimizeButton.addActionListener(e -> {
            PriorityQueue<TareaPrioridad> cola = Main.getColaTareas();
            if (cola.isEmpty()) {
                displayArea.setText("No hay tareas para optimizar.");
                return;
            }
            
            List<TareaPrioridad> tareasParaOptimizar = new ArrayList<>(cola);
            // Llama al algoritmo Merge Sort (Divide y Vencerás)
            TaskOptimizer.mergeSort(tareasParaOptimizar);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            displayArea.setText("OPTIMIZACIÓN DE TAREAS (Divide y Vencerás):\n");
            displayArea.append("Tareas ordenadas por Tiempo Estimado (de menor a mayor).\n");
            displayArea.append("------------------------------------------------------------------------------------------------\n");
            
            // ENCABEZADOS DE COLUMNA ALINEADOS
            displayArea.append(String.format("%-4s | %-40s | %-8s | %s\n", 
                                            "No.", "DESCRIPCIÓN", "TIEMPO", "FECHA LÍMITE"));
            displayArea.append("------------------------------------------------------------------------------------------------\n");
            
            for (int i = 0; i < tareasParaOptimizar.size(); i++) {
                TareaPrioridad tarea = tareasParaOptimizar.get(i);
                
                // FORMATO MEJORADO PARA ALINEAR
                displayArea.append(String.format("%-4s | %-40s | %-8d | %s\n", 
                                                (i + 1) + ".",
                                                tarea.getDescripcion(),        
                                                tarea.getTiempoEstimado(),     
                                                sdf.format(tarea.getFechaEntrega())));    
            }
            displayArea.append("------------------------------------------------------------------------------------------------\n");
            displayArea.append("**Uso:** La lista ayuda a balancear la carga de trabajo, asignando las\n");
            displayArea.append("tareas más cortas primero o identificando cuellos de botella.");
        });
        
        buttonGridPanel.add(calcButton);
        buttonGridPanel.add(optimizeButton); 
        
        JPanel buttonWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonWrapperPanel.setOpaque(false);
        buttonWrapperPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0)); // Espacio superior
        buttonWrapperPanel.add(buttonGridPanel);
        
        panel.add(buttonWrapperPanel, BorderLayout.SOUTH);

        return panel;
    }
    

    private JLabel createTitleLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(color.brighter());
        label.setFont(new Font("Arial", Font.BOLD, 28));
        return label;
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
                    public void mouseEntered(MouseEvent e) {
                        hovered = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hovered = false;
                        repaint();
                    }
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