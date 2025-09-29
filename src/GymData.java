import java.util.ArrayList;
import java.util.List;

public class GymData {
    private static List<Clase> clasesProgramadas = new ArrayList<>();

    public static List<Clase> getClasesProgramadas() {
        return clasesProgramadas;
    }

    public static void addClaseProgramada(Clase clase) {
        clasesProgramadas.add(clase);
    }

    public static void clearClasesProgramadas() {
        clasesProgramadas.clear();
    }
}