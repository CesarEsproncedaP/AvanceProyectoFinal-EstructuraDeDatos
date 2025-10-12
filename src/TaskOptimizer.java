import java.util.ArrayList;
import java.util.List;

// Clase para optimizar la ordenación de tareas por tiempo estimado
public class TaskOptimizer {
    
    // Ordena una lista de tareas usando el algoritmo MergeSort
    public static void mergeSort(List<TareaPrioridad> tareas) {
        if (tareas.size() < 2) {
            return; // Si la lista tiene menos de 2 elementos, no necesita ordenación
        }
        
        // Divide la lista en dos mitades
        int medio = tareas.size() / 2;
        List<TareaPrioridad> izquierda = new ArrayList<>(tareas.subList(0, medio));
        List<TareaPrioridad> derecha = new ArrayList<>(tareas.subList(medio, tareas.size()));

        // Ordena recursivamente ambas mitades
        mergeSort(izquierda);
        mergeSort(derecha);

        // Combina las mitades ordenadas
        merge(tareas, izquierda, derecha);
    }
    
    // Combina dos listas ordenadas en una sola lista
    private static void merge(List<TareaPrioridad> original, List<TareaPrioridad> izquierda, List<TareaPrioridad> derecha) {
        int i = 0, j = 0, k = 0; // Índices para recorrer las listas
        
        // Compara y combina elementos en orden ascendente según tiempo estimado
        while (i < izquierda.size() && j < derecha.size()) {
            if (izquierda.get(i).getTiempoEstimado() <= derecha.get(j).getTiempoEstimado()) {
                original.set(k++, izquierda.get(i++));
            } else {
                original.set(k++, derecha.get(j++));
            }
        }
        // Añade elementos restantes de la lista izquierda, si los hay
        while (i < izquierda.size()) { original.set(k++, izquierda.get(i++)); }
        // Añade elementos restantes de la lista derecha, si los hay
        while (j < derecha.size()) { original.set(k++, derecha.get(j++)); }
    }
}