import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea implements Comparable<Tarea> {
    private String nombre;
    private int prioridad;
    private String departamento;
    private Date fechaLimite;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Tarea(String nombre, int prioridad, String departamento, String fechaLimite) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.departamento = departamento;
        try {
            this.fechaLimite = sdf.parse(fechaLimite);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getFechaLimite() {
        return sdf.format(fechaLimite);
    }
    
    @Override
    public int compareTo(Tarea otraTarea) {
        // Criterio de comparación: prioridad ascendente
        return Integer.compare(this.prioridad, otraTarea.prioridad);
    }
}