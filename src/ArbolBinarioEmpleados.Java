import javax.swing.JTextArea;

public class ArbolBinarioEmpleados {
    private NodoEmpleado raiz;

    public ArbolBinarioEmpleados() {
        this.raiz = null;
    }

    public NodoEmpleado getRaiz() {
        return raiz;
    }

    public void insertar(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }

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