import java.util.ArrayList;
import java.util.List;

public class GymData {
    private static List<Clase> clasesProgramadas = new ArrayList<>();

    // Este getter devuelve la lista de clases programadas.
    public static List<Clase> getClasesProgramadas() {
        return clasesProgramadas;
    }

    // Aquí se agrega una clase a la lista de programadas.
    public static void addClaseProgramada(Clase clase) {
        clasesProgramadas.add(clase);
    }

    // Este método limpia la lista de clases programadas, la vacía completamente.
    public static void clearClasesProgramadas() {
        clasesProgramadas.clear();
    }
}