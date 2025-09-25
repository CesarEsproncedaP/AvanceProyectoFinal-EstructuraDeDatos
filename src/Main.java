import javax.swing.SwingUtilities;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // Asegura que la ventana de Login se inicie en el hilo de Swing.
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            // Pasa las referencias de las ventanas al LoginFrame
            // para que pueda manejar la navegación
            
            loginFrame.setFrames(new MainFrame(loginFrame), new RegistroFrame(loginFrame));
            loginFrame.setVisible(true);
        });

        // El resto de este código inicializa los datos de tu aplicación
        // (empleados, tareas, etc.) y no debe ser modificado.
        
        // Inicializar datos para empleados
        ArbolBinarioEmpleados arbolEmpleados = new ArbolBinarioEmpleados();
        Map<String, Empleado> hashMapEmpleados = new HashMap<>();

        Empleado emp1 = new Empleado("E001", "Juan Lopez", "Departamento A");
        Empleado emp2 = new Empleado("E002", "Maria Gonzalez", "Departamento B");
        Empleado emp3 = new Empleado("E003", "Carlos Ramirez", "Departamento C");
        Empleado emp4 = new Empleado("E004", "Ana Torres", "Departamento D");
        Empleado emp5 = new Empleado("E005", "Pedro Sanchez", "Departamento A");

        arbolEmpleados.insertar(emp1);
        arbolEmpleados.insertar(emp2);
        arbolEmpleados.insertar(emp3);
        arbolEmpleados.insertar(emp4);
        arbolEmpleados.insertar(emp5);

        hashMapEmpleados.put(emp1.getId(), emp1);
        hashMapEmpleados.put(emp2.getId(), emp2);
        hashMapEmpleados.put(emp3.getId(), emp3);
        hashMapEmpleados.put(emp4.getId(), emp4);
        hashMapEmpleados.put(emp5.getId(), emp5);

        // Inicializar datos para tareas
        Queue<TareaPrioridad> colaPrioridades = new PriorityQueue<>();

        TareaPrioridad tarea1 = new TareaPrioridad("T001", "Limpiar area de pesas", 5);
        TareaPrioridad tarea2 = new TareaPrioridad("T002", "Verificar sistema de sonido", 3);
        TareaPrioridad tarea3 = new TareaPrioridad("T003", "Revisar maquina de cardio", 1);
        TareaPrioridad tarea4 = new TareaPrioridad("T004", "Programar reunion con instructores", 2);
        TareaPrioridad tarea5 = new TareaPrioridad("T005", "Limpiar vidrios del frente", 4);
        TareaPrioridad tarea6 = new TareaPrioridad("T006", "Inventario de equipos de spinning", 3);
        TareaPrioridad tarea7 = new TareaPrioridad("T007", "Actualizar perfil de cliente", 1);
        TareaPrioridad tarea8 = new TareaPrioridad("T008", "Reparar la caminadora 3", 5);
        TareaPrioridad tarea9 = new TareaPrioridad("T009", "Revisar las luces del gimnasio", 2);
        TareaPrioridad tarea10 = new TareaPrioridad("T010", "Organizar sala de spinning", 4);
        TareaPrioridad tarea11 = new TareaPrioridad("T011", "Comprar mas toallas", 1);
        TareaPrioridad tarea12 = new TareaPrioridad("T012", "Limpiar los baños", 3);
        TareaPrioridad tarea13 = new TareaPrioridad("T013", "Actualizar software de maquinas", 2);
        TareaPrioridad tarea14 = new TareaPrioridad("T014", "Revisar los aires acondicionados", 5);
        TareaPrioridad tarea15 = new TareaPrioridad("T015", "Reemplazar los tapetes del piso", 4);

        colaPrioridades.add(tarea1);
        colaPrioridades.add(tarea2);
        colaPrioridades.add(tarea3);
        colaPrioridades.add(tarea4);
        colaPrioridades.add(tarea5);
        colaPrioridades.add(tarea6);
        colaPrioridades.add(tarea7);
        colaPrioridades.add(tarea8);
        colaPrioridades.add(tarea9);
        colaPrioridades.add(tarea10);
        colaPrioridades.add(tarea11);
        colaPrioridades.add(tarea12);
        colaPrioridades.add(tarea13);
        colaPrioridades.add(tarea14);
        colaPrioridades.add(tarea15);
    }
}
