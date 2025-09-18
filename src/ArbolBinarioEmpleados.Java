// Clase para árbol binario de instructores
class ArbolBinarioEmpleados {
    private NodoArbol raiz;

    public void insertar(Empleado empleado) {
        raiz = insertarRecursivo(raiz, empleado);
    }

    private NodoArbol insertarRecursivo(NodoArbol nodo, Empleado empleado) {
        if (nodo == null) {
            return new NodoArbol(empleado);
        }
        if (empleado.getDepartamento().compareTo(nodo.empleado.getDepartamento()) < 0) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, empleado);
        } else if (empleado.getDepartamento().compareTo(nodo.empleado.getDepartamento()) > 0) {
            nodo.derecha = insertarRecursivo(nodo.derecha, empleado);
        }
        return nodo;
    }

    public void buscarPorDepartamento(String departamento) {
        buscarPorDepartamentoRecursivo(raiz, departamento);
    }

    private void buscarPorDepartamentoRecursivo(NodoArbol nodo, String departamento) {
        if (nodo == null) {
            return;
        }
        if (nodo.empleado.getDepartamento().equalsIgnoreCase(departamento)) {
            System.out.println(nodo.empleado);
        }
        buscarPorDepartamentoRecursivo(nodo.izquierda, departamento);
        buscarPorDepartamentoRecursivo(nodo.derecha, departamento);
    }

    // Método auxiliar para recorrido inorden en el árbol
    public void mostrarInorden() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
        } else {
            mostrarInordenRecursivo(raiz);
        }
    }

    private void mostrarInordenRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            mostrarInordenRecursivo(nodo.izquierda);
            System.out.println(nodo.empleado);
            mostrarInordenRecursivo(nodo.derecha);
        }
    }
}

