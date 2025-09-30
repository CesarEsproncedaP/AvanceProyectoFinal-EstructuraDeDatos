import java.util.ArrayList;
import java.util.List;

public class TaskOptimizer {
    
    
    public static void mergeSort(List<TareaPrioridad> tareas) {
        if (tareas.size() < 2) {
            return; 
        }
        
        
        int medio = tareas.size() / 2;
        List<TareaPrioridad> izquierda = new ArrayList<>(tareas.subList(0, medio));
        List<TareaPrioridad> derecha = new ArrayList<>(tareas.subList(medio, tareas.size()));

        mergeSort(izquierda);
        mergeSort(derecha);

        
        merge(tareas, izquierda, derecha);
    }

    
    private static void merge(List<TareaPrioridad> original, List<TareaPrioridad> izquierda, List<TareaPrioridad> derecha) {
        int i = 0, j = 0, k = 0; 
        
        while (i < izquierda.size() && j < derecha.size()) {
            if (izquierda.get(i).getTiempoEstimado() <= derecha.get(j).getTiempoEstimado()) {
                original.set(k++, izquierda.get(i++));
            } else {
                original.set(k++, derecha.get(j++));
            }
        }
        while (i < izquierda.size()) { original.set(k++, izquierda.get(i++)); }
        while (j < derecha.size()) { original.set(k++, derecha.get(j++)); }
    }
}