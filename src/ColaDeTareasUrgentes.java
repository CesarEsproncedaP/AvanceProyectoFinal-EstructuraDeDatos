import java.util.PriorityQueue;
import java.util.List;
import java.util.stream.Collectors;

public class ColaDeTareasUrgentes {
    private PriorityQueue<Tarea> colaPrioridades;

    // En este constructor se inicializa la cola de prioridades como una PriorityQueue vacía para manejar tareas urgentes.
    public ColaDeTareasUrgentes() {
        colaPrioridades = new PriorityQueue<>();
    }

    // Aquí se inserta una tarea en la cola usando el add.
    public void insertarTarea(Tarea tarea) {
        colaPrioridades.add(tarea);
    }

    // Este método extrae la tarea más urgente de la cola usando poll, que devuelve y remueve el elemento con mayor prioridad.
    public Tarea extraerTarea() {
        return colaPrioridades.poll();
    }

    // Se verfica si la cola está vacía y devuelve true si no hay tareas pendientes.
    public boolean estaVacia() {
        return colaPrioridades.isEmpty();
    }
    
    // Este getter devuelve una lista ordenada de las tareas en la cola, usando stream y sorted para organizarla.
    public List<Tarea> getTareas() {
        return colaPrioridades.stream().sorted().collect(Collectors.toList());
    }
}