public class ClaseProgramada {
    private String nombreClase;
    private instructor instructor;
    private String horario;
    private String estado; // Nuevo atributo: "Programada", "En curso", "Cancelada", "Finalizada"

    public ClaseProgramada(String nombreClase, instructor instructor, String horario) {
        this.nombreClase = nombreClase;
        this.instructor = instructor;
        this.horario = horario;
        this.estado = "Programada"; // Estado inicial
    }

    public String getNombreClase() { return nombreClase; }
    public instructor getInstructor() { return instructor; }
    public String getHorario() { return horario; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Clase: " + nombreClase + " | Instructor: " + instructor.getNombre() + " | Horario: " + horario + " | Estado: " + estado;
    }
}