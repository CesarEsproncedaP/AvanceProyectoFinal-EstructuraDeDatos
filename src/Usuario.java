// Clase para representar un usuario del sistema
public class Usuario {
    private String id; // Identificador único del usuario
    private String nombre; // Nombre del usuario
    private String password; // Contraseña del usuario

    // Constructor para inicializar un usuario
    public Usuario(String id, String nombre, String password) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
    }

    // Obtener el ID del usuario
    public String getId() {
        return id;
    }

    // Obtener el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Obtener la contraseña del usuario
    public String getPassword() {
        return password;
    }
}