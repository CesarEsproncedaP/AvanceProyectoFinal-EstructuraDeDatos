import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Clase para representar una tarea con prioridad
public class Tarea implements Comparable<Tarea> {
    private String nombre; // Nombre de la tarea
    private int prioridad; // Prioridad de la tarea (menor número = mayor prioridad)
    private String departamento; // Departamento asignado
    private Date fechaLimite; // Fecha límite de la tarea
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha

    // Constructor para inicializar una tarea
    public Tarea(String nombre, int prioridad, String departamento, String fechaLimite) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.departamento = departamento;
        try {
            this.fechaLimite = sdf.parse(fechaLimite); // Convierte la fecha de String a Date
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Obtener el nombre de la tarea
    public String getNombre() {
        return nombre;
    }

    // Obtener la prioridad de la tarea
    public int getPrioridad() {
        return prioridad;
    }

    // Obtener el departamento de la tarea
    public String getDepartamento() {
        return departamento;
    }

    // Obtener la fecha límite como String
    public String getFechaLimite() {
        return sdf.format(fechaLimite);
    }
    
    // Compara tareas por prioridad
    @Override
    public int compareTo(Tarea otraTarea) {
        return Integer.compare(this.prioridad, otraTarea.prioridad);
    }
}