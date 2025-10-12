import java.util.Objects;
import java.util.Random;

public class Clase {
    private String nombre;
    private String instructor;
    private String horario;
    private String[][] mapaAsientos;

    // En este constructor inicializo los atributos de la clase con los valores que recibo y se creó el mapa de asientos llamando a inicializarAsientos.
    public Clase(String nombre, String instructor, String horario) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.horario = horario;
        this.mapaAsientos = new String[5][5];
        inicializarAsientos(); 
    }

    // Este método  inicializa los asientos de manera aleatoria, ocupado algunos y otros disponibles, todo eso usando un random.
    private void inicializarAsientos() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (rand.nextInt(100) < 20) { 
                    mapaAsientos[i][j] = "OCUPADO";
                } else {
                    mapaAsientos[i][j] = "DISPONIBLE";
                }
            }
        }
    }

    // Aquí se reserva el asiento seleccionado por el usuario, verificando que la fila y columna estén dentro del rango, marcandolo como ocupado.
    public void reservarAsiento(int fila, int columna) {
        if (fila >= 0 && fila < 5 && columna >= 0 && columna < 5) {
            mapaAsientos[fila][columna] = "OCUPADO";
        }
    }

    // Este getter devuelve el nombre de la clase.
    public String getNombre() {
        return nombre;
    }

    // Este getter devuelve el instructor de la clase.
    public String getInstructor() {
        return instructor;
    }

    // Este getter devuelve el horario de la clase.
    public String getHorario() {
        return horario;
    }
    
    // Este getter devuelve el mapa de asientos completo.
    public String[][] getMapaAsientos() {
        return mapaAsientos;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clase clase = (Clase) o;
        return Objects.equals(nombre, clase.nombre) && Objects.equals(instructor, clase.instructor) && Objects.equals(horario, clase.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, instructor, horario);
    }
}