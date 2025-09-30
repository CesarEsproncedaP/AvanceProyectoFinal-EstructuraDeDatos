import java.util.Date;
import java.util.Objects;

public class TareaPrioridad implements Comparable<TareaPrioridad> {
    private String id;
    private String descripcion;
    private Date fechaEntrega;
    private int tiempoEstimado;

    public TareaPrioridad(String id, String descripcion, Date fechaEntrega, int tiempoEstimado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Date getFechaEntrega() { return fechaEntrega; }
    public int getTiempoEstimado() { return tiempoEstimado; } 

    @Override
    public int compareTo(TareaPrioridad otra) {
        int dateComparison = this.fechaEntrega.compareTo(otra.fechaEntrega);
        if (dateComparison != 0) {
            return dateComparison;
        }
        return Integer.compare(this.tiempoEstimado, otra.tiempoEstimado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaPrioridad that = (TareaPrioridad) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}