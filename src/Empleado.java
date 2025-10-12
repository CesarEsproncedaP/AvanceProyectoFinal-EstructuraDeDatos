public class Empleado {
    private String id;
    private String nombre;
    private String departamento;

    // En este constructor se inicializan los atributos id, nombre y departamento.
    public Empleado(String id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    // Este getter devuelve el ID del empleado.
    public String getId() {
        return id;
    }

    // Este getter devuelve el nombre del empleado.
    public String getNombre() {
        return nombre;
    }

    // Este getter devuelve el departamento del empleado.
    public String getDepartamento() {
        return departamento;
    }

    // Se sobre escribe el toString para devolver una representación en string del empleado con sus atributos.
    @Override
    public String toString() {
        return "Empleado{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", departamento='" + departamento +
               '}';
    }
}