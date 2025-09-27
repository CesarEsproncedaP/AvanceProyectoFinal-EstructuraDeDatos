import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventarioMaquinasFrame extends JFrame {
    private JTable maquinasTable;
    private DefaultTableModel tableModel;
    private List<Maquina> listaMaquinas;
    private JFrame previousFrame;
    private String currentFilter = "Todas";

    public InventarioMaquinasFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        setTitle("Inventario de Máquinas - GYM MASTER");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listaMaquinas = new ArrayList<>();
        listaMaquinas.add(new Maquina("M001", "Cinta de correr", "Operativa"));
        listaMaquinas.add(new Maquina("M002", "Bicicleta estática", "En reparación"));
        listaMaquinas.add(new Maquina("M003", "Máquina de remo", "Operativa"));
        listaMaquinas.add(new Maquina("M004", "Elíptica", "Operativa"));
        listaMaquinas.add(new Maquina("M005", "Prensa de piernas", "Operativa"));
        listaMaquinas.add(new Maquina("M006", "Polea alta", "En reparación"));
        listaMaquinas.add(new Maquina("M007", "Banco de pesas", "Operativa"));
        listaMaquinas.add(new Maquina("M008", "Máquina de abdominales", "Operativa"));
        listaMaquinas.add(new Maquina("M009", "Stepper", "En reparación"));
        listaMaquinas.add(new Maquina("M010", "Rack de sentadillas", "Operativa"));
        listaMaquinas.add(new Maquina("M011", "Maquina Smith", "Operativa"));
        listaMaquinas.add(new Maquina("M012", "Jaula de potencia", "Operativa"));
        listaMaquinas.add(new Maquina("M013", "Pesas rusas", "Operativa"));
        listaMaquinas.add(new Maquina("M014", "Barra de dominadas", "Operativa"));
        listaMaquinas.add(new Maquina("M015", "Maquina de remo", "En reparación"));
        listaMaquinas.add(new Maquina("M016", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M017", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M018", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M019", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M020", "Bicicleta de spinning", "Operativa"));

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(26, 26, 26));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Inventario de Máquinas", SwingConstants.CENTER);
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

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        maquinasTable = new JTable(tableModel);
        maquinasTable.setRowHeight(25);
        maquinasTable.setFont(new Font("Arial", Font.PLAIN, 14));
        maquinasTable.setBackground(new Color(40, 40, 40));
        maquinasTable.setForeground(Color.WHITE);
        maquinasTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        maquinasTable.getTableHeader().setBackground(new Color(50, 50, 50));
        maquinasTable.getTableHeader().setForeground(new Color(74, 189, 172));
        maquinasTable.setDefaultRenderer(Object.class, new EstadoTableCellRenderer());
        JScrollPane scrollPane = new JScrollPane(maquinasTable);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomButtonPanel.setOpaque(false);

        JButton showAllButton = createStyledButton("Mostrar Todas", new Color(74, 189, 172), new Color(47, 128, 114));
        showAllButton.addActionListener(e -> {
            currentFilter = "Todas";
            refreshTable();
        });

        JButton showOperativasButton = createStyledButton("Máquinas Operativas", new Color(47, 128, 237), new Color(30, 90, 180));
        showOperativasButton.addActionListener(e -> {
            currentFilter = "Operativa";
            refreshTable();
        });

        JButton showReparacionButton = createStyledButton("En Reparación", new Color(255, 50, 50), new Color(180, 30, 30));
        showReparacionButton.addActionListener(e -> {
            currentFilter = "En reparación";
            refreshTable();
        });

        JButton changeStateButton = createStyledButton("Cambiar Estado", new Color(138, 43, 226), new Color(100, 30, 180));
        changeStateButton.addActionListener(e -> {
            CambiarEstadoDialog dialog = new CambiarEstadoDialog(this, listaMaquinas);
            dialog.setVisible(true); 
            if (dialog.isStateChanged()) { // Verifica si se realizó un cambio antes de cerrar
                refreshAndResetFilter(); // Refresca
            }
        });

        bottomButtonPanel.add(showAllButton);
        bottomButtonPanel.add(showOperativasButton);
        bottomButtonPanel.add(showReparacionButton);
        bottomButtonPanel.add(changeStateButton);

        mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Maquina> maquinasFiltradas = listaMaquinas.stream()
            .filter(m -> currentFilter.equals("Todas") || m.getEstado().equals(currentFilter))
            .collect(Collectors.toList());

        for (Maquina maquina : maquinasFiltradas) {
            tableModel.addRow(new Object[]{maquina.getId(), maquina.getNombre(), maquina.getEstado()});
        }
    }

    public void refreshAndResetFilter() {
        this.currentFilter = "Todas";
        refreshTable();
    }

    class EstadoTableCellRenderer extends JLabel implements TableCellRenderer {
        public EstadoTableCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Object estadoValue = table.getValueAt(row, 2);
            if (estadoValue != null) {
                String estado = estadoValue.toString();
                if (estado.equals("En reparación")) {
                    setBackground(new Color(255, 100, 100));
                    setForeground(Color.BLACK);
                } else if (estado.equals("Operativa")) {
                    setBackground(new Color(144, 238, 144));
                    setForeground(Color.BLACK);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
            }
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
}