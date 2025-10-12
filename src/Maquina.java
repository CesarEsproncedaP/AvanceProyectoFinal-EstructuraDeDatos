// Clase para representar una máquina del gimnasio
public class Maquina {
    private String id; // Identificador único de la máquina
    private String nombre; // Nombre de la máquina
    private String estado; // Estado de la máquina (Operativa o En reparación)

    // Constructor para inicializar una máquina
    public Maquina(String id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    // Obtener el ID de la máquina
    public String getId() {
        return id;
    }

    // Obtener el nombre de la máquina
    public String getNombre() {
        return nombre;
    }

    // Obtener el estado de la máquina
    public String getEstado() {
        return estado;
    }

    // Cambiar el estado de la máquina
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
}