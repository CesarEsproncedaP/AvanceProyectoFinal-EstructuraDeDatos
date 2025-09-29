import java.util.*;

public class TareaPrioridad implements Comparable<TareaPrioridad> {
    private String id;
    private String descripcion;
    private Date fechaEntrega;
    private int tiempoEstimado; //En horas

    public TareaPrioridad(String id, String descripcion, Date fechaEntrega, int tiempoEstimado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.tiempoEstimado = tiempoEstimado;
    }

    @Override
    public int compareTo(TareaPrioridad otra) {
        return this.fechaEntrega.compareTo(otra.fechaEntrega);
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Date getFechaEntrega() { return fechaEntrega; }
    public int getTiempoEstimado() { return tiempoEstimado; }
}