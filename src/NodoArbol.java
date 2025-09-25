public class NodoArbol {
    Empleado empleado;
    NodoArbol izquierda;
    NodoArbol derecha;

    public NodoArbol(Empleado empleado) {
        this.empleado = empleado;
        this.izquierda = null;
        this.derecha = null;
    }
}