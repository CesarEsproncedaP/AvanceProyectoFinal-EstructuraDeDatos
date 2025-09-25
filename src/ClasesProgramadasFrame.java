import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClasesProgramadasFrame extends JFrame {
    private List<Clase> clasesDisponibles;
    private List<Clase> clasesProgramadas;
    private JTable table;
    private DefaultTableModel tableModel;
    private JFrame previousFrame;

    public ClasesProgramadasFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Horario de Clases - GYM MASTER");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        GradientPanel mainPanel = new GradientPanel(new Color(20, 30, 48), new Color(36, 59, 85));
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Horario de Clases", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = createStyledButton("Volver", new Color(255, 105, 180), new Color(200, 80, 140));
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

        clasesDisponibles = new ArrayList<>();
        clasesProgramadas = new ArrayList<>();

        clasesDisponibles.add(new Clase("Yoga Avanzado", "Ana García", "Lunes 10:00 AM - 11:30 AM"));
        clasesDisponibles.add(new Clase("Zumba Fitness", "Sofía Cruz", "Lunes 12:00 PM - 1:30 PM"));
        clasesDisponibles.add(new Clase("Spinning Intenso", "Luis Pérez", "Martes 8:00 PM - 9:30 PM"));
        clasesDisponibles.add(new Clase("Pilates Mat", "Ana García", "Miércoles 6:00 AM - 7:00 AM"));
        clasesDisponibles.add(new Clase("Cardio Extremo", "Luis Pérez", "Jueves 7:00 PM - 8:00 PM"));
        clasesDisponibles.add(new Clase("Boxeo Fit", "Roberto Estrada", "Viernes 9:00 AM - 10:00 AM"));
        clasesDisponibles.add(new Clase("Cross Training", "Ximena Cavazos", "Sábado 11:00 AM - 12:00 PM"));
        clasesDisponibles.add(new Clase("Danza Aeróbica", "Sofía Cruz", "Lunes 6:00 PM - 7:30 PM"));
        clasesDisponibles.add(new Clase("Levantamiento Olímpico", "Pedro Díaz", "Miércoles 9:00 AM - 10:00 AM"));
        clasesDisponibles.add(new Clase("Funcional HIIT", "Marta Gómez", "Jueves 9:00 AM - 10:00 AM"));

        clasesDisponibles.sort(Comparator.comparing(Clase::getInstructor)
                                         .thenComparing(Clase::getNombre)
                                         .thenComparing(Clase::getHorario));

        String[] columnNames = {"Instructor", "Clase", "Horario"};
        Object[][] data = new Object[clasesDisponibles.size()][3];
        for (int i = 0; i < clasesDisponibles.size(); i++) {
            Clase c = clasesDisponibles.get(i);
            data[i][0] = c.getInstructor();
            data[i][1] = c.getNombre();
            data[i][2] = c.getHorario();
        }

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setBackground(new Color(30, 30, 30));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(60, 60, 60));
        table.getTableHeader().setBackground(new Color(50, 50, 50));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.setSelectionBackground(new Color(74, 189, 172));
        table.setSelectionForeground(Color.BLACK);
        table.setRowHeight(30);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        
        JButton selectButton = createStyledButton("Seleccionar Asiento", new Color(74, 189, 172), new Color(47, 128, 114));
        selectButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                new AsientoFrame(clasesDisponibles.get(selectedRow)).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona una clase de la tabla.", "Selecciona una clase", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        JButton programButton = createStyledButton("Programar Clase", new Color(255, 204, 0), new Color(200, 150, 0));
        programButton.addActionListener(e -> programarClase());
        
        JButton myClassesButton = createStyledButton("Mis Clases Programadas", new Color(70, 130, 180), new Color(50, 100, 150));
        myClassesButton.addActionListener(e -> mostrarClasesProgramadas());
        
        buttonPanel.add(selectButton);
        buttonPanel.add(programButton);
        buttonPanel.add(myClassesButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    
    private void programarClase() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Clase claseAProgramar = clasesDisponibles.get(selectedRow);
            clasesProgramadas.add(claseAProgramar);
            JOptionPane.showMessageDialog(this, "Clase de " + claseAProgramar.getNombre() + " programada con éxito.", "Clase Programada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una clase para programar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarClasesProgramadas() {
        if (clasesProgramadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No has programado ninguna clase aún.", "Mis Clases", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Crear una nueva ventana para mostrar la tabla de clases programadas
        JDialog dialog = new JDialog(this, "Mis Clases Programadas", true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);

        // Crear el modelo de la tabla con los datos
        DefaultTableModel programadasTableModel = new DefaultTableModel(new Object[]{"Clase", "Instructor", "Horario"}, 0);
        for (Clase clase : clasesProgramadas) {
            programadasTableModel.addRow(new Object[]{clase.getNombre(), clase.getInstructor(), clase.getHorario()});
        }
        
        JTable programadasTable = new JTable(programadasTableModel);
        programadasTable.setRowHeight(25);
        programadasTable.setFont(new Font("Arial", Font.PLAIN, 14));
        programadasTable.setBackground(new Color(40, 40, 40));
        programadasTable.setForeground(Color.WHITE);
        programadasTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        programadasTable.getTableHeader().setBackground(new Color(50, 50, 50));
        programadasTable.getTableHeader().setForeground(new Color(255, 105, 180));
        
        JScrollPane scrollPane = new JScrollPane(programadasTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
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
                    public void mouseEntered(MouseEvent e) { hovered = true; repaint(); }
                    @Override
                    public void mouseExited(MouseEvent e) { hovered = false; repaint(); }
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

class Clase {
    private String nombre;
    private String instructor;
    private String horario;

    public Clase(String nombre, String instructor, String horario) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getHorario() {
        return horario;
    }
}

class AsientoFrame extends JFrame {
    private Clase clase;
    private JButton[] asientos;

    public AsientoFrame(Clase clase) {
        this.clase = clase;
        setTitle("Seleccionar Asiento para " + clase.getNombre());
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(20, 30, 48));

        asientos = new JButton[25];
        for (int i = 0; i < 25; i++) {
            JButton asientoButton = new JButton("Asiento " + (i + 1));
            asientoButton.setBackground(new Color(74, 189, 172));
            asientoButton.setForeground(Color.WHITE);
            asientoButton.setFocusPainted(false);
            asientoButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Has seleccionado el " + asientoButton.getText() + " para la clase de " + clase.getNombre() + ".");
                this.dispose();
            });
            asientos[i] = asientoButton;
            mainPanel.add(asientoButton);
        }
        
        add(mainPanel);
    }
}
