import javax.swing.JTextArea;

public class ArbolBinarioEmpleados {
    private NodoArbol raiz;

    public ArbolBinarioEmpleados() {
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void insertar(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }

    private NodoArbol insertarRecursivo(NodoArbol actual, Empleado empleado) {
        if (actual == null) {
            return new NodoArbol(empleado);
        }
        if (empleado.getDepartamento().compareTo(actual.empleado.getDepartamento()) < 0) {
            actual.izquierda = insertarRecursivo(actual.izquierda, empleado);
        } else if (empleado.getDepartamento().compareTo(actual.empleado.getDepartamento()) > 0) {
            actual.derecha = insertarRecursivo(actual.derecha, empleado);
        }
        return actual;
    }

    public void mostrarInorden(NodoArbol nodo, JTextArea displayArea) {
        if (nodo != null) {
            mostrarInorden(nodo.izquierda, displayArea);
            displayArea.append(nodo.empleado.toString() + "\n");
            mostrarInorden(nodo.derecha, displayArea);
        }
    }

    public void buscarPorDepartamento(NodoArbol nodo, String departamento, JTextArea displayArea) {
        if (nodo != null) {
            if (departamento.equals(nodo.empleado.getDepartamento())) {
                displayArea.append(nodo.empleado.toString() + "\n");
            }
            buscarPorDepartamento(nodo.izquierda, departamento, displayArea);
            buscarPorDepartamento(nodo.derecha, departamento, displayArea);
        }
    }
}
