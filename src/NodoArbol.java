// Clase para nodos del árbol binario
class NodoArbol {
    Empleado empleado;
    NodoArbol izquierda, derecha; // Referencias a hijos izquierdo y derecho

    public NodoArbol(Empleado empleado) {
        this.empleado = empleado;
        this.izquierda = null;
        this.derecha = null;
    }
}
