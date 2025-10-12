import javax.swing.JTextArea;

public class ArbolBinarioEmpleados {
    private NodoEmpleado raiz;

    // En este constructor se inicializa la raíz del árbol como null para que empiece vacío.
    public ArbolBinarioEmpleados() {
        this.raiz = null;
    }

    // Aqui se devuelve la raíz del árbol.
    public NodoEmpleado getRaiz() {
        return raiz;
    }

    // Aquí se inserto un nuevo empleado en el árbol llamando al método recursivo.
    public void insertar(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }

    // Este es el método recursivo para insertar. 
    // Si el nodo actual es null, se crea uno nuevo. Si no, se comparo el departamento y va hacia la izquierda o derecha según sea menor o mayor.
    private NodoEmpleado insertarRecursivo(NodoEmpleado actual, Empleado empleado) {
        if (actual == null) {
            return new NodoEmpleado(empleado);
        }
        if (empleado.getDepartamento().compareTo(actual.empleado.getDepartamento()) < 0) {
            actual.izquierda = insertarRecursivo(actual.izquierda, empleado);
        } else {
            actual.derecha = insertarRecursivo(actual.derecha, empleado);
        }
        return actual;
    }

    // Aquí con este método se ven los empleados en Inorden, dando la lista ordenada por departamento.
    public void mostrarInorden(NodoEmpleado nodo, JTextArea displayArea) {
        if (nodo != null) {
            mostrarInorden(nodo.izquierda, displayArea);
            displayArea.append(String.format("%-7s| %-25s| %s\n", 
                nodo.empleado.getId(), 
                nodo.empleado.getNombre(), 
                nodo.empleado.getDepartamento()));
            mostrarInorden(nodo.derecha, displayArea);
        }
    }

    // Aquí se buscan los empleados por departamento de forma recursiva, revisando todos los nodos que coincidan.
    public void buscarPorDepartamento(NodoEmpleado nodo, String departamento, JTextArea displayArea) {
        if (nodo != null) {
            if (departamento.equalsIgnoreCase(nodo.empleado.getDepartamento())) {
                displayArea.append(String.format("%-7s| %-25s| %s\n", 
                    nodo.empleado.getId(), 
                    nodo.empleado.getNombre(), 
                    nodo.empleado.getDepartamento()));
            }
            buscarPorDepartamento(nodo.izquierda, departamento, displayArea);
            buscarPorDepartamento(nodo.derecha, departamento, displayArea);
        }
    }
}