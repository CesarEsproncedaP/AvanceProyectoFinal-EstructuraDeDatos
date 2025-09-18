public class instructor {
    private String nombre;
    private String especialidad;

    // Inicializa a el instructor del gym juunto con su nombre y su especialidad
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

    // Devuelve tanto el nombre como la especialidad del instructor
    @Override
    public String toString() {
        return "Instructor: " + nombre + " | Especialidad: " + especialidad;
    }
}