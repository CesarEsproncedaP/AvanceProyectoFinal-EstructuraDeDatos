import java.util.*;

public class SistemaGestionGimnasio {

    private static Stack<Tarea> tareasUrgentes = new Stack<>();
    private static Queue<ClaseProgramada> clasesEnEspera = new LinkedList<>();
    private static LinkedList<Maquina> maquinasInventario = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarDatos();
        mostrarMenuPrincipal();
    }

    private static void inicializarDatos() {
        // Inicializar Instructores
        instructor ana = new instructor("Ana García", "Yoga y Pilates");
        instructor luis = new instructor("Luis Pérez", "Entrenamiento de Cardio");
        instructor sofia = new instructor("Sofía Cruz", "Zumba y Baile");

        // Inicializar Clases Programadas (Cola)
        clasesEnEspera.add(new ClaseProgramada("Zumba", sofia, "10:00 AM"));
        clasesEnEspera.add(new ClaseProgramada("Yoga", ana, "11:30 AM"));
        clasesEnEspera.add(new ClaseProgramada("Spinning", luis, "05:00 PM"));
        System.out.println("Clases inicializadas. 🧘‍♀️");

        // Inicializar Máquinas (Lista)
        maquinasInventario.add(new Maquina("Caminadora 1", "Cardio", true));
        maquinasInventario.add(new Maquina("Elíptica 2", "Cardio", true));
        maquinasInventario.add(new Maquina("Prensa de Piernas", "Musculación", true));
        maquinasInventario.add(new Maquina("Máquina de Jalón", "Musculación", true));
        System.out.println("Máquinas inicializadas. 💪");
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DEL GIMNASIO ---");
            System.out.println("1. Tareas de Mantenimiento Urgente (Pila)");
            System.out.println("2. Clases Programadas (Cola)");
            System.out.println("3. Inventario de Máquinas (Lista)");
            System.out.println("4. Ver estado de todas las tareas y equipos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: menuPilaTareasUrgentes(); break;
                case 2: menuColaClases(); break;
                case 3: menuListaMaquinas(); break;
                case 4: verTodo(); break;
                case 5: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // --- MENÚS Y LÓGICA DE CADA ESTRUCTURA ---
    private static void menuPilaTareasUrgentes() {
        int opcion;
        do {
            System.out.println("\n--- Mantenimiento Urgente (Pila) ---");
            System.out.println("1. Agregar tarea (push)");
            System.out.println("2. Completar tarea (pop)");
            System.out.println("3. Ver próxima tarea (peek)");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Descripción de la tarea: ");
                    String desc = scanner.nextLine();
                    System.out.print("Departamento: ");
                    String dep = scanner.nextLine();
                    tareasUrgentes.push(new Tarea(desc, dep));
                    System.out.println("¡Tarea urgente agregada!");
                    break;
                case 2:
                    if (!tareasUrgentes.isEmpty()) {
                        Tarea t = tareasUrgentes.pop();
                        System.out.println("Tarea completada: " + t.getDescripcion());
                    } else {
                        System.out.println("No hay tareas urgentes.");
                    }
                    break;
                case 3:
                    if (!tareasUrgentes.isEmpty()) {
                        System.out.println("Próxima tarea: " + tareasUrgentes.peek().getDescripcion());
                    } else {
                        System.out.println("No hay tareas urgentes.");
                    }
                    break;
            }
        } while (opcion != 4);
    }

    private static void menuColaClases() {
        int opcion;
        do {
            System.out.println("\n--- Clases Programadas (Cola) ---");
            System.out.println("1. Ver próxima clase (front)");
            System.out.println("2. Iniciar próxima clase (dequeue)");
            System.out.println("3. Ver todas las clases");
            System.out.println("4. Cancelar una clase");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (!clasesEnEspera.isEmpty()) {
                        System.out.println("La próxima clase es: " + clasesEnEspera.peek().getNombreClase());
                    } else {
                        System.out.println("No hay clases programadas.");
                    }
                    break;
                case 2:
                    if (!clasesEnEspera.isEmpty()) {
                        ClaseProgramada c = clasesEnEspera.poll();
                        c.setEstado("Finalizada");
                        System.out.println("¡Clase de " + c.getNombreClase() + " ha iniciado y se ha finalizado!");
                    } else {
                        System.out.println("No hay clases para iniciar.");
                    }
                    break;
                case 3:
                    if (clasesEnEspera.isEmpty()) {
                        System.out.println("No hay clases programadas.");
                    } else {
                        clasesEnEspera.forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre de la clase a cancelar: ");
                    String claseCancelar = scanner.nextLine();
                    boolean encontrada = false;
                    for (ClaseProgramada c : clasesEnEspera) {
                        if (c.getNombreClase().equalsIgnoreCase(claseCancelar)) {
                            c.setEstado("Cancelada");
                            System.out.println("Clase de " + c.getNombreClase() + " ha sido cancelada.");
                            encontrada = true;
                            break;
                        }
                    }
                    if (!encontrada) {
                        System.out.println("Clase no encontrada.");
                    }
                    break;
            }
        } while (opcion != 5);
    }

    private static void menuListaMaquinas() {
        int opcion;
        do {
            System.out.println("\n--- Inventario de Máquinas (Lista) ---");
            System.out.println("1. Ver todas las máquinas");
            System.out.println("2. Marcar una máquina como averiada");
            System.out.println("3. Marcar una máquina como reparada");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    maquinasInventario.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Nombre de la máquina a averiar: ");
                    String nombreAveriar = scanner.nextLine();
                    boolean averiada = false;
                    for (Maquina m : maquinasInventario) {
                        if (m.getNombre().equalsIgnoreCase(nombreAveriar)) {
                            m.setFuncionando(false);
                            tareasUrgentes.push(new Tarea("Reparar " + m.getNombre(), m.getDepartamento()));
                            System.out.println("Máquina averiada. Tarea de reparación urgente creada.");
                            averiada = true;
                            break;
                        }
                    }
                    if (!averiada) {
                        System.out.println("Máquina no encontrada.");
                    }
                    break;
                case 3:
                    System.out.print("Nombre de la máquina reparada: ");
                    String nombreReparada = scanner.nextLine();
                    boolean reparada = false;
                    for (Maquina m : maquinasInventario) {
                        if (m.getNombre().equalsIgnoreCase(nombreReparada)) {
                            m.setFuncionando(true);
                            System.out.println("Máquina marcada como reparada.");
                            reparada = true;
                            break;
                        }
                    }
                    if (!reparada) {
                        System.out.println("Máquina no encontrada.");
                    }
                    break;
            }
        } while (opcion != 4);
    }

    private static void verTodo() {
        System.out.println("\n--- ESTADO COMPLETO DEL GIMNASIO ---");
        System.out.println("\n--- TAREAS URGENTES (Pila) ---");
        if (tareasUrgentes.isEmpty()) {
            System.out.println("✅ ¡No hay tareas urgentes pendientes!");
        } else {
            tareasUrgentes.forEach(System.out::println);
        }

        System.out.println("\n--- CLASES PROGRAMADAS (Cola) ---");
        if (clasesEnEspera.isEmpty()) {
            System.out.println("✅ Todas las clases han sido impartidas hoy.");
        } else {
            clasesEnEspera.forEach(System.out::println);
        }

        System.out.println("\n--- INVENTARIO DE MÁQUINAS ---");
        maquinasInventario.forEach(System.out::println);
    }
}