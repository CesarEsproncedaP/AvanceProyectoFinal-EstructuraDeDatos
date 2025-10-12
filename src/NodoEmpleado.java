public class NodoEmpleado {
    Empleado empleado;
    NodoEmpleado izquierda;
    NodoEmpleado derecha;

    public NodoEmpleado(Empleado empleado) {
        this.empleado = empleado;
        this.izquierda = null;
        this.derecha = null;
    }
}