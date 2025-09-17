public class Tarea {
    private String descripcion;
    private String departamento;

    public Tarea(String descripcion, String departamento) {
        this.descripcion = descripcion;
        this.departamento = departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Tarea: " + descripcion + " | Departamento: " + departamento;
    }
}