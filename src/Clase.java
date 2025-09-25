import java.util.Random;

public class Clase {
    private String nombre;
    private String instructor;
    private String horario;
    private String[][] mapaAsientos;

    public Clase(String nombre, String instructor, String horario) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.horario = horario;
        this.mapaAsientos = new String[5][5];
        inicializarAsientos();
    }

    private void inicializarAsientos() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Simular algunos asientos ocupados aleatoriamente
                if (rand.nextInt(100) < 20) { // 20% de probabilidad de estar ocupado
                    mapaAsientos[i][j] = "OCUPADO";
                } else {
                    mapaAsientos[i][j] = "DISPONIBLE";
                }
            }
        }
    }

    public void reservarAsiento(int fila, int columna) {
        if (fila >= 0 && fila < 5 && columna >= 0 && columna < 5) {
            mapaAsientos[fila][columna] = "OCUPADO";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getHorario() {
        return horario;
    }
    
    public String[][] getMapaAsientos() {
        return mapaAsientos;
    }
}