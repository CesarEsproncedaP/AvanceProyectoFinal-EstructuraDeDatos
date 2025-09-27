import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HorarioClasesFrame extends JFrame {

    private JTable clasesTable;
    private DefaultTableModel tableModel;
    // Lista estática para que los datos persistan entre ventanas
    private static List<Clase> listaClases = new ArrayList<>();
    private JFrame previousFrame;

    // Se agrega un constructor sin el previousFrame para la primera llamada desde el menú principal.
    public HorarioClasesFrame() {
        this(null);
    }
    
    public HorarioClasesFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Horario de Clases - GYM MASTER");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (listaClases.isEmpty()) {
            // Estas clases son usadas para poblar la tabla, así que no se remueven.
            // Si no se usan en el proyecto general, puedes comentarlas o removerlas, pero la tabla quedaría vacía.
            listaClases.add(new Clase("Spinning", "10:00 - 11:00", "Lunes", "Sala 1"));
            listaClases.add(new Clase("Yoga", "11:00 - 12:00", "Martes", "Sala 2"));
            listaClases.add(new Clase("Zumba", "17:00 - 18:00", "Miércoles", "Sala 1"));
            listaClases.add(new Clase("CrossFit", "18:00 - 19:00", "Jueves", "Sala 3"));
        }

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(26, 26, 26));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Horario de Clases", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        tableModel = new DefaultTableModel(new Object[]{"Clase", "Horario", "Día", "Sala"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        clasesTable = new JTable(tableModel);
        clasesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        clasesTable.setBackground(new Color(40, 40, 40));
        clasesTable.setForeground(Color.WHITE);
        clasesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        clasesTable.getTableHeader().setBackground(new Color(50, 50, 50));
        clasesTable.getTableHeader().setForeground(new Color(74, 189, 172));
        clasesTable.setSelectionBackground(new Color(70, 70, 70));
        clasesTable.setSelectionForeground(new Color(255, 204, 0));

        cargarClases();

        JScrollPane scrollPane = new JScrollPane(clasesTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        
        JButton reservarButton = createStyledButton("Reservar Asiento", new Color(74, 189, 172), new Color(47, 128, 114));
        reservarButton.addActionListener(e -> {
            int selectedRow = clasesTable.getSelectedRow();
            if (selectedRow != -1) {
                Clase claseSeleccionada = listaClases.get(selectedRow);
                AsientosFrame asientosFrame = new AsientosFrame(this, claseSeleccionada);
                asientosFrame.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona una clase para reservar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton backButton = createStyledButton("Volver", new Color(255, 204, 0), new Color(200, 150, 0));
        backButton.addActionListener(e -> {
            this.dispose();
            if (previousFrame != null) {
                previousFrame.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(reservarButton);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void cargarClases() {
        tableModel.setRowCount(0);
        for (Clase clase : listaClases) {
            tableModel.addRow(new Object[]{clase.getNombre(), clase.getHorario(), clase.getDia(), clase.getSala()});
        }
    }
    
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

    public static List<Clase> getListaClases() {
        return listaClases;
    }
}

// Clase para la estructura de una clase
class Clase {
    private String nombre;
    private String horario;
    private String dia;
    private String sala;
    private int[] asientosReservados = new int[20]; // 20 asientos por defecto
    private int asientosOcupados = 0;

    public Clase(String nombre, String horario, String dia, String sala) {
        this.nombre = nombre;
        this.horario = horario;
        this.dia = dia;
        this.sala = sala;
    }

    public String getNombre() { return nombre; }
    public String getHorario() { return horario; }
    public String getDia() { return dia; }
    public String getSala() { return sala; }
    public int[] getAsientosReservados() { return asientosReservados; }
    public int getAsientosOcupados() { return asientosOcupados; }

    public void setAsientosOcupados(int asientosOcupados) { this.asientosOcupados = asientosOcupados; }
}

// Definición de AsientosFrame con el constructor correcto (JFrame, Clase)
class AsientosFrame extends JFrame {
    private JFrame previousFrame;
    private Clase clase;
    private JButton[] botonesAsiento;

    public AsientosFrame(JFrame previousFrame, Clase clase) {
        this.previousFrame = previousFrame;
        this.clase = clase;
        setTitle("Seleccionar Asiento para " + clase.getNombre());
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

        JPanel asientosPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        asientosPanel.setOpaque(false);
        botonesAsiento = new JButton[20];
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
        mainPanel.add(asientosPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Volver");
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBackground(new Color(255, 204, 0));
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
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
}