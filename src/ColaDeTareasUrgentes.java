import java.util.PriorityQueue;
import java.util.List;
import java.util.stream.Collectors;

public class ColaDeTareasUrgentes {
    private PriorityQueue<Tarea> colaPrioridades;

    public ColaDeTareasUrgentes() {
        colaPrioridades = new PriorityQueue<>();
    }

    public void insertarTarea(Tarea tarea) {
        colaPrioridades.add(tarea);
    }

    public Tarea extraerTarea() {
        return colaPrioridades.poll();
    }

    public boolean estaVacia() {
        return colaPrioridades.isEmpty();
    }
    
    public List<Tarea> getTareas() {
        return colaPrioridades.stream().sorted().collect(Collectors.toList());
    }
