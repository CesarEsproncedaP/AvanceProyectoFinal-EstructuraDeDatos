// Clase para representar empleados
class Empleado {
    private String id; // Identificador único del empleado
    private String nombre; // Nombre del empleado
    private String departamento; // Departamento al que pertenece

    public Empleado(String id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Departamento: " + departamento;
    }
}