// Clase para representar tareas en la cola de prioridades
class TareaPrioridad {
    private String id; // Identificador único de la tarea
    private String descripcion;
    private int urgencia; // Urgencia (1-3, mayor es más urgente)
    private String fechaEntrega; // formato YYYY-MM-DD
    private int tiempoEstimado; // Tiempo estimado en horas

    public TareaPrioridad(String id, String descripcion, int urgencia, String fechaEntrega, int tiempoEstimado) {
        this.id = id;
        this.descripcion = descripcion;
        this.urgencia = urgencia;
        this.fechaEntrega = fechaEntrega;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public int getUrgencia() { return urgencia; }
    public String getFechaEntrega() { return fechaEntrega; }
    public int getTiempoEstimado() { return tiempoEstimado; }

    @Override
    public String toString() {
        return "ID: " + id + ", Descripción: " + descripcion + ", Urgencia: " + urgencia + 
               ", Fecha: " + fechaEntrega + ", Tiempo Estimado: " + tiempoEstimado + " horas";
    }
}

