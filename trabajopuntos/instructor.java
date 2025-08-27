public class instructor {
    private String nombre;
    private String especialidad;

    public instructor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return "Instructor: " + nombre + " | Especialidad: " + especialidad;
    }
}