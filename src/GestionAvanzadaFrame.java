import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.TitledBorder;

public class GestionAvanzadaFrame extends JFrame {
    private ArbolBinarioEmpleados arbolEmpleados = new ArbolBinarioEmpleados();
    private Map<String, Empleado> hashEmpleados = new HashMap<>();
    
    private GradientPanel mainPanel; 
    private JFrame previousFrame;
    
    public GestionAvanzadaFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Gestión Avanzada del Gimnasio");
        setSize(950, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Color gradientStart = new Color(30, 30, 30);
        Color gradientEnd = new Color(50, 70, 90);
        
        mainPanel = new GradientPanel(gradientStart, gradientEnd);
        mainPanel.setLayout(new CardLayout());
        
        Color arbolAccent = new Color(100, 255, 200);
        Color hashAccent = new Color(255, 100, 100);
        
        JPanel arbolPanel = createArbolPanel(mainPanel.getBackground(), arbolAccent); 
        JPanel hashPanel = createHashPanel(mainPanel.getBackground(), hashAccent); 
        
        mainPanel.add(arbolPanel, "Arboles");
        mainPanel.add(hashPanel, "Hashes");

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 25));
        navPanel.setOpaque(false);
        JButton arbolButton = createStyledButton("Árboles Binarios", arbolAccent, arbolAccent.darker());
        JButton hashButton = createStyledButton("Tablas Hash", hashAccent, hashAccent.darker());
        JButton backButton = createStyledButton("Volver", new Color(255, 105, 180), new Color(200, 80, 140));
        
        arbolButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "Arboles");
        });
        
        hashButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, "Hashes");
        });

        backButton.addActionListener(e -> {
            this.dispose();
            if (previousFrame != null) {
                previousFrame.setVisible(true);
            }
        });

        navPanel.add(arbolButton);
        navPanel.add(hashButton);
        navPanel.add(backButton);

        GradientPanel containerPanel = new GradientPanel(gradientStart, gradientEnd); 
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        containerPanel.add(navPanel, BorderLayout.NORTH);
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        
        add(containerPanel);
        
        Empleado emp1 = new Empleado("E001", "Juan López", "Ventas");
        Empleado emp2 = new Empleado("E002", "María González", "Marketing");
        Empleado emp3 = new Empleado("E003", "Carlos Rivera", "Operaciones");
        Empleado emp4 = new Empleado("E004", "Laura Flores", "Ventas");
        Empleado emp5 = new Empleado("E005", "Roberto Sánchez", "Limpieza");
        Empleado emp6 = new Empleado("E006", "Sofía Mendoza", "Marketing");
        Empleado emp7 = new Empleado("E007", "Andrés Castro", "Mantenimiento");
        Empleado emp8 = new Empleado("E008", "Isabel Pérez", "Ventas");
        
        arbolEmpleados.insertar(emp1);
        arbolEmpleados.insertar(emp2);
        arbolEmpleados.insertar(emp3);
        arbolEmpleados.insertar(emp4);
        arbolEmpleados.insertar(emp5);
        arbolEmpleados.insertar(emp6);
        arbolEmpleados.insertar(emp7);
        arbolEmpleados.insertar(emp8);

        hashEmpleados.put(emp1.getId(), emp1);
        hashEmpleados.put(emp2.getId(), emp2);
        hashEmpleados.put(emp3.getId(), emp3);
        hashEmpleados.put(emp4.getId(), emp4);
        hashEmpleados.put(emp5.getId(), emp5);
        hashEmpleados.put(emp6.getId(), emp6);
        hashEmpleados.put(emp7.getId(), emp7);
        hashEmpleados.put(emp8.getId(), emp8);
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

// Clases de soporte necesarias para GestionAvanzadaFrame
class Empleado {
    private String id;
    private String nombre;
    private String departamento;

    public Empleado(String id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
}

class NodoEmpleado {
    Empleado empleado;
    NodoEmpleado izquierdo;
    NodoEmpleado derecho;

    public NodoEmpleado(Empleado empleado) {
        this.empleado = empleado;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinarioEmpleados {
    private NodoEmpleado raiz;

    public ArbolBinarioEmpleados() {
        this.raiz = null;
    }

    public NodoEmpleado getRaiz() {
        return raiz;
    }

    public void insertar(Empleado empleado) {
        this.raiz = insertarRecursivo(this.raiz, empleado);
    }

    private NodoEmpleado insertarRecursivo(NodoEmpleado actual, Empleado empleado) {
        if (actual == null) {
            return new NodoEmpleado(empleado);
        }

        if (empleado.getDepartamento().compareTo(actual.empleado.getDepartamento()) < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, empleado);
        } else {
            actual.derecho = insertarRecursivo(actual.derecho, empleado);
        }
        return actual;
    }

    public void mostrarInorden(NodoEmpleado nodo, JTextArea area) {
        if (nodo != null) {
            mostrarInorden(nodo.izquierdo, area);
            area.append(String.format("%-7s| %-25s| %s\n", 
                                      nodo.empleado.getId(), 
                                      nodo.empleado.getNombre(), 
                                      nodo.empleado.getDepartamento()));
            mostrarInorden(nodo.derecho, area);
        }
    }
    
    public void buscarPorDepartamento(NodoEmpleado nodo, String depto, JTextArea area) {
        if (nodo == null) {
            return;
        }

        // Si el departamento actual coincide, lo mostramos
        if (nodo.empleado.getDepartamento().equalsIgnoreCase(depto)) {
            area.append(String.format("ID: %s | Nombre: %-20s | Departamento: %s\n", 
                                      nodo.empleado.getId(), 
                                      nodo.empleado.getNombre(), 
                                      nodo.empleado.getDepartamento()));
        }

        // Buscamos en ambos subárboles (ya que el departamento no es clave única)
        buscarPorDepartamento(nodo.izquierdo, depto, area);
        buscarPorDepartamento(nodo.derecho, depto, area);
    }
}
