import java.util.*;

public class Main {

    private static Stack<Tarea> tareasUrgentes = new Stack<>();
    private static Queue<ClaseProgramada> clasesEnEspera = new LinkedList<>();
    private static LinkedList<Maquina> maquinasInventario = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static List<instructor> instructores = new ArrayList<>();
    private static List<String> horariosPredefinidos = Arrays.asList(
        "10:00 AM - 11:30 AM",
        "12:00 PM - 1:30 PM",
        "02:30 PM - 4:00 PM",
        "05:00 PM - 6:30 PM",
        "08:00 PM - 9:30 PM"
    );

    public static void main(String[] args) {
        inicializarDatos();
        mostrarMenuPrincipal();
    }

    private static void inicializarDatos() {
        // Inicializar Instructores
        instructor Ana = new instructor("Ana García", "Yoga y Pilates");
        instructor Luis = new instructor("Luis Pérez", "Entrenamiento de Cardio");
        instructor Sofia = new instructor("Sofía Cruz", "Zumba");
        instructor Ximena = new instructor("Ximena Cavazos", "Jumpling");

        instructores.add(Ana);
        instructores.add(Luis);
        instructores.add(Sofia);
        instructores.add(Ximena);

        // Inicializar Clases Programadas (Cola)
        clasesEnEspera.add(new ClaseProgramada("Zumba", Sofia, "10:00 AM - 11:30 AM"));
        clasesEnEspera.add(new ClaseProgramada("Yoga", Ana, "12:00 PM - 1:30 PM"));
        clasesEnEspera.add(new ClaseProgramada("Spinning", Luis, "02:30 PM - 4:00 PM"));
        clasesEnEspera.add(new ClaseProgramada("Pilates", Ana, "05:00 PM - 6:30 PM"));
        clasesEnEspera.add(new ClaseProgramada("Jumping", Ximena, "08:00 PM - 9:30 PM"));

        System.out.println("Clases inicializadas.");

        // Inicializar Máquinas (Lista)
        maquinasInventario.add(new Maquina("Caminadora", "Cardio", true));
        maquinasInventario.add(new Maquina("Eliptica", "Cardio", true));
        maquinasInventario.add(new Maquina("Prensa de Piernas", "Musculo", true));
        maquinasInventario.add(new Maquina("Maquina de Jalon", "Musculo", true));
        maquinasInventario.add(new Maquina("Press banca", "Musculo", true));
        maquinasInventario.add(new Maquina("Maquina de curl de biceps", "Musculo", true));
        maquinasInventario.add(new Maquina("Maquina de femoral acostado", "Musculo", true));
        maquinasInventario.add(new Maquina("Maquina de abductores", "Musculo", true));
        maquinasInventario.add(new Maquina("Maquina de elevaciones laterales", "Musculo", true));

        System.out.println("Máquinas inicializadas. ");
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE TAREAS GYM ---");
            System.out.println("1. Tareas de Mantenimiento Urgente ");
            System.out.println("2. Clases Programadas ");
            System.out.println("3. Inventario de Máquinas ");
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
                default: System.out.println("Tu opción no es válida. Intenta de nuevo.");
            }
        } while (opcion != 5);
    }

    // --- MENÚS Y LÓGICA DE CADA ESTRUCTURA ---
    private static void menuPilaTareasUrgentes() {
        int opcion;
        do {
            System.out.println("\n--- Mantenimiento Urgente ---");
            System.out.println("1. Agregar tarea ");
            System.out.println("2. Completar tarea ");
            System.out.println("3. Ver próxima tarea ");
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
        System.out.println("\n--- Clases Programadas  ---");
        System.out.println("1. Ver próxima clase ");
        System.out.println("2. Iniciar próxima clase ");
        System.out.println("3. Ver todas las clases programadas");
        System.out.println("4. Cancelar una clase");
        System.out.println("5. Agregar una clase programada");
        System.out.println("6. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                if (clasesEnEspera.isEmpty()) {
                    System.out.println("No hay clases programadas.");
                } else {
                    System.out.println("La próxima clase es: " + clasesEnEspera.peek().getNombreClase());
                }
                break;
            case 2:
                if (clasesEnEspera.isEmpty()) {
                    System.out.println("No hay clases para iniciar.");
                } else {
                    ClaseProgramada c = clasesEnEspera.poll();
                    c.setEstado("Finalizada");
                    System.out.println("¡Clase de " + c.getNombreClase() + " ha iniciado y se ha finalizado!");
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
                Iterator<ClaseProgramada> it = clasesEnEspera.iterator();
                boolean encontrada = false;
                while (it.hasNext()) {
                    ClaseProgramada c = it.next();
                    if (c.getNombreClase().equalsIgnoreCase(claseCancelar)) {
                        it.remove();
                        System.out.println("Clase de " + c.getNombreClase() + " ha sido cancelada.");
                        encontrada = true;
                        break;
                    }
                }
                if (!encontrada) {
                    System.out.println("Clase no encontrada.");
                }
                break;
            case 5:
                System.out.print("Nombre de la clase: ");
                String nombreClase = scanner.nextLine();
                
                System.out.println("Instructores disponibles:");
                int idx = 1;
                for (instructor inst : instructores) {
                    System.out.println(idx + ". " + inst.getNombre() + " - " + inst.getEspecialidad());
                    idx++;
                }
                System.out.print("Seleccione el número del instructor: ");
                int numInst = scanner.nextInt();
                scanner.nextLine();
                if (numInst < 1 || numInst > instructores.size()) {
                    System.out.println("Instructor no válido.");
                    break;
                }
                instructor selectedInst = instructores.get(numInst - 1);
                
                Set<String> occupied = new HashSet<>();
                for (ClaseProgramada c : clasesEnEspera) {
                    occupied.add(c.getHorario());
                }
                List<String> available = new ArrayList<>();
                for (String h : horariosPredefinidos) {
                    if (!occupied.contains(h)) {
                        available.add(h);
                    }
                }
                if (available.isEmpty()) {
                    System.out.println("No hay horarios disponibles.");
                    break;
                }
                System.out.println("Horarios disponibles:");
                idx = 1;
                for (String h : available) {
                    System.out.println(idx + ". " + h);
                    idx++;
                }
                System.out.print("Seleccione el número del horario: ");
                int numH = scanner.nextInt();
                scanner.nextLine();
                if (numH < 1 || numH > available.size()) {
                    System.out.println("Horario no válido.");
                    break;
                }
                String selectedH = available.get(numH - 1);
                
                ClaseProgramada nueva = new ClaseProgramada(nombreClase, selectedInst, selectedH);
                clasesEnEspera.add(nueva);
                System.out.println("¡Clase agregada exitosamente!");
                break;
        }
    } while (opcion != 6);
}

    private static void menuListaMaquinas() {
        int opcion;
        do {
            System.out.println("\n--- Inventario de Máquinas ---");
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
                            if (m.isFuncionando()) {
                                System.out.println("Esta máquina no estaba averiada.");
                            } else {
                                m.setFuncionando(true);
                                System.out.println("La máquina fue marcada como reparada.");
                            }
                            reparada = true;
                            break;
                        }
                    }
                    if (!reparada) {
                        System.out.println("La máquina no ha sido encontrada, asegurate de quee la hayas escrito bien.");
                    }
                    break;
            }
        } while (opcion != 4);
    }

    private static void verTodo() {
        System.out.println("\n--- ESTADO COMPLETO DEL GIMNASIO ---");
        System.out.println("\n--- TAREAS URGENTES ---");
        if (tareasUrgentes.isEmpty()) {
            System.out.println("¡No hay tareas urgentes pendientes!");
        } else {
            tareasUrgentes.forEach(System.out::println);
        }

        System.out.println("\n--- CLASES PROGRAMADAS ---");
        if (clasesEnEspera.isEmpty()) {
            System.out.println("Todas las clases han sido impartidas hoy.");
        } else {
            clasesEnEspera.forEach(System.out::println);
        }

        System.out.println("\n--- INVENTARIO DE MÁQUINAS ---");
        maquinasInventario.forEach(System.out::println);
    }
} 