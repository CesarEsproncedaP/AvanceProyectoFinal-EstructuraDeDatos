import java.util.Date;
import java.util.Objects;

// Clase para representar una tarea con prioridad y fecha de entrega
public class TareaPrioridad implements Comparable<TareaPrioridad> {
    private String id; // Identificador único de la tarea
    private String descripcion; // Descripción de la tarea
    private Date fechaEntrega; // Fecha de entrega de la tarea
    private int tiempoEstimado; // Tiempo estimado en horas para completar la tarea

    // Constructor para inicializar una tarea
    public TareaPrioridad(String id, String descripcion, Date fechaEntrega, int tiempoEstimado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.tiempoEstimado = tiempoEstimado;
    }

    // Obtener el ID de la tarea
    public String getId() { return id; }

    // Obtener la descripción de la tarea
    public String getDescripcion() { return descripcion; }

    // Obtener la fecha de entrega
    public Date getFechaEntrega() { return fechaEntrega; }

    // Obtener el tiempo estimado
    public int getTiempoEstimado() { return tiempoEstimado; }

    // Compara tareas por fecha de entrega y tiempo estimado
    @Override
    public int compareTo(TareaPrioridad otra) {
        int dateComparison = this.fechaEntrega.compareTo(otra.fechaEntrega);
        if (dateComparison != 0) {
            return dateComparison; // Prioriza por fecha de entrega
        }
        return Integer.compare(this.tiempoEstimado, otra.tiempoEstimado); // Si fechas son iguales, compara por tiempo
    }

    // Verifica si dos tareas son iguales por ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaPrioridad that = (TareaPrioridad) o;
        return Objects.equals(id, that.id);
    }

    // Genera el código hash basado en el ID
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}