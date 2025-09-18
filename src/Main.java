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

    private static PriorityQueue<TareaPrioridad> colaPrioridadesTareas = new PriorityQueue<>(
        (t1, t2) -> {
            if (t1.getUrgencia() != t2.getUrgencia()) {
                return Integer.compare(t2.getUrgencia(), t1.getUrgencia());
            }
            return t1.getFechaEntrega().compareTo(t2.getFechaEntrega());
        }
    );
    private static ArbolBinarioEmpleados arbolEmpleados = new ArbolBinarioEmpleados();
    private static Map<String, TareaPrioridad> hashTareas = new HashMap<>();
    private static Map<String, Empleado> hashEmpleados = new HashMap<>();
    private static List<TareaGeneral> tareasGenerales = new ArrayList<>();


    public static void main(String[] args) {  // Método principal usado para iniciar el prrograma. 
        inicializarDatos();
        inicializarDatosFinal();
        mostrarMenuPrincipal();
    }

    private static void inicializarDatos() {
        // Inicializar Instructores de las diferentes clases del gy,
        instructor Ana = new instructor("Ana García", "Yoga y Pilates");
        instructor Luis = new instructor("Luis Pérez", "Entrenamiento de Cardio");
        instructor Sofia = new instructor("Sofía Cruz", "Zumba");
        instructor Ximena = new instructor("Ximena Cavazos", "Jumpling");

        instructores.add(Ana);
        instructores.add(Luis);
        instructores.add(Sofia);
        instructores.add(Ximena);

        // Inicializar Clases Programadas del gym (Cola)
        clasesEnEspera.add(new ClaseProgramada("Zumba", Sofia, "10:00 AM - 11:30 AM"));
        clasesEnEspera.add(new ClaseProgramada("Yoga", Ana, "12:00 PM - 1:30 PM"));
        clasesEnEspera.add(new ClaseProgramada("Spinning", Luis, "02:30 PM - 4:00 PM"));
        clasesEnEspera.add(new ClaseProgramada("Pilates", Ana, "05:00 PM - 6:30 PM"));
        clasesEnEspera.add(new ClaseProgramada("Jumping", Ximena, "08:00 PM - 9:30 PM"));

        System.out.println("Clases inicializadas.");

        // Inicializar las máquinas del gym (Lista)
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

    private static void inicializarDatosFinal() {
        // Inicializar empleados para el árbol binario y el HashMap
        Empleado emp1 = new Empleado("E001", "Juan López", "Ventas");
        Empleado emp2 = new Empleado("E002", "María González", "Ventas");
        Empleado emp3 = new Empleado("E003", "Carlos Ramírez", "IT");
        Empleado emp4 = new Empleado("E004", "Ana Torres", "IT");
        Empleado emp5 = new Empleado("E005", "Pedro Sánchez", "RRHH");

        arbolEmpleados.insertar(emp1);
        arbolEmpleados.insertar(emp2);
        arbolEmpleados.insertar(emp3);
        arbolEmpleados.insertar(emp4);
        arbolEmpleados.insertar(emp5);

        hashEmpleados.put(emp1.getId(), emp1);
        hashEmpleados.put(emp2.getId(), emp2);
        hashEmpleados.put(emp3.getId(), emp3);
        hashEmpleados.put(emp4.getId(), emp4);
        hashEmpleados.put(emp5.getId(), emp5);

        // Inicializar tareas para la cola de prioridades y el HashMap
        TareaPrioridad tarea1 = new TareaPrioridad("T001", "Reporte ventas", 3, "2025-09-20", 5);
        TareaPrioridad tarea2 = new TareaPrioridad("T002", "Actualizar software", 2, "2025-09-25", 8);
        TareaPrioridad tarea3 = new TareaPrioridad("T003", "Reunión RRHH", 1, "2025-09-18", 3);

        colaPrioridadesTareas.add(tarea1);
        colaPrioridadesTareas.add(tarea2);
        colaPrioridadesTareas.add(tarea3);

        hashTareas.put(tarea1.getId(), tarea1);
        hashTareas.put(tarea2.getId(), tarea2);
        hashTareas.put(tarea3.getId(), tarea3);

        // Tareas para el para ordenamiento y búsqueda
        tareasGenerales.add(new TareaGeneral("TG001", "Tarea general 1", 2, "2025-09-22", 4));
        tareasGenerales.add(new TareaGeneral("TG002", "Tarea general 2", 3, "2025-09-19", 6));
        tareasGenerales.add(new TareaGeneral("TG003", "Tarea general 3", 1, "2025-09-30", 2));

        System.out.println("Datos de fase final inicializados.");
    }

    // Es donde se muestra el menú principal del pograma y donde el usuario decide que opción quiere efectuar o elegir.
    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE TAREAS GYM ---");
            System.out.println("1. Tareas de Mantenimiento Urgente ");
            System.out.println("2. Clases Programadas ");
            System.out.println("3. Inventario de Máquinas ");
            System.out.println("4. Ver estado de todas las tareas y equipos");
            System.out.println("5. Gestión Avanzada (Cola de Prioridades, Árbol, Hash, Ordenamiento)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: menuPilaTareasUrgentes(); break;
                case 2: menuColaClases(); break;
                case 3: menuListaMaquinas(); break;
                case 4: verTodo(); break;
                case 5: menuGestionAvanzada(); break;
                case 6: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Tu opción no es válida. Intenta de nuevo.");
            }
        } while (opcion != 6);
    }

    // Gestiona el menú para las tareas urgentes usando pilas.
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

    // Gestiona el menú para las clases programadas usando colas.
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

    // Gestiona el menú para el inventario de máquinas del gym.
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

    // Muestra el estado completo del gimnasio, muestra tareas urgentes, clases y máquinas(ya sea que esten averiadas o no)
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

    // Método para el menú de Gestión Avanzada, y cada opción te lleva a su popio menú en donde puedes elegir sus propias opciones. 
    private static void menuGestionAvanzada() {
        int opcion;
        do {
            System.out.println("\n--- Gestión Avanzada ---");
            System.out.println("1. Cola de Prioridades");
            System.out.println("2. Árboles Binarios");
            System.out.println("3. Tablas Hash y HashMap");
            System.out.println("4. Métodos de Ordenamiento y Búsqueda");
            System.out.println("5. Recursividad y Algoritmos Divide y Vencerás");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuColaPrioridades();
                    break;
                case 2:
                    menuArbolesBinarios();
                    break;
                case 3:
                    menuTablasHash();
                    break;
                case 4:
                    menuOrdenamientoBusqueda();
                    break;
                case 5:
                    menuRecursividadDivideVencer();
                    break;
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 6);
    }

    // Menú para Cola de Prioridades
    private static void menuColaPrioridades() {
        int opcion;
        do {
            System.out.println("\n--- Cola de Prioridades ---");
            System.out.println("1. Agregar tarea a cola de prioridades");
            System.out.println("2. Procesar próxima tarea prioritaria");
            System.out.println("3. Mostrar todas las tareas en la cola");
            System.out.println("4. Volver al menú de Gestión Avanzada");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID de la tarea: ");
                    String idTarea = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String desc = scanner.nextLine();
                    System.out.print("Urgencia (1-3): ");
                    int urgencia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Fecha de entrega (YYYY-MM-DD): ");
                    String fecha = scanner.nextLine();
                    System.out.print("Tiempo estimado (horas): ");
                    int tiempo = scanner.nextInt();
                    scanner.nextLine();
                    TareaPrioridad tarea = new TareaPrioridad(idTarea, desc, urgencia, fecha, tiempo);
                    colaPrioridadesTareas.add(tarea);
                    hashTareas.put(idTarea, tarea);
                    tareasGenerales.add(new TareaGeneral(idTarea, desc, urgencia, fecha, tiempo));
                    System.out.println("Tarea agregada a cola de prioridades, HashMap y lista de tareas generales.");
                    break;
                case 2:
                    if (!colaPrioridadesTareas.isEmpty()) {
                        TareaPrioridad t = colaPrioridadesTareas.poll();
                        System.out.println("Tarea procesada: " + t);
                    } else {
                        System.out.println("No hay tareas prioritarias en la cola.");
                    }
                    break;
                case 3:
                    if (colaPrioridadesTareas.isEmpty()) {
                        System.out.println("No hay tareas en la cola de prioridades.");
                    } else {
                        System.out.println("Tareas en la cola de prioridades:");
                        List<TareaPrioridad> temp = new ArrayList<>(colaPrioridadesTareas);
                        temp.sort((t1, t2) -> {
                            if (t1.getUrgencia() != t2.getUrgencia()) {
                                return Integer.compare(t2.getUrgencia(), t1.getUrgencia());
                            }
                            return t1.getFechaEntrega().compareTo(t2.getFechaEntrega());
                        });
                        temp.forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.println("Volviendo al menú de Gestión Avanzada...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4);
    }

    // Menú para Árboles Binarios
    private static void menuArbolesBinarios() {
        int opcion;
        do {
            System.out.println("\n--- Árboles Binarios (Empleados por Departamento) ---");
            System.out.println("1. Insertar empleado en el árbol");
            System.out.println("2. Buscar empleados por departamento");
            System.out.println("3. Mostrar todos los empleados (Recorrido Inorden)");
            System.out.println("4. Volver al menú de Gestión Avanzada");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID del empleado: ");
                    String idEmp = scanner.nextLine();
                    System.out.print("Nombre del empleado: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Departamento: ");
                    String depto = scanner.nextLine();
                    Empleado nuevoEmp = new Empleado(idEmp, nombre, depto);
                    arbolEmpleados.insertar(nuevoEmp);
                    hashEmpleados.put(idEmp, nuevoEmp); // También se agrega al HashMap para consistencia
                    System.out.println("Empleado insertado en el árbol binario.");
                    break;
                case 2:
                    System.out.print("Ingrese departamento: ");
                    String dept = scanner.nextLine();
                    System.out.println("Empleados en " + dept + ":");
                    arbolEmpleados.buscarPorDepartamento(dept);
                    break;
                case 3:
                    System.out.println("Todos los empleados (recorrido inorden):");
                    arbolEmpleados.mostrarInorden();
                    break;
                case 4:
                    System.out.println("Volviendo al menú de Gestión Avanzada...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4);
    }

    // Menú para Tablas Hash y HashMap
    private static void menuTablasHash() {
        int opcion;
        do {
            System.out.println("\n--- Tablas Hash y HashMap ---");
            System.out.println("1. Buscar tarea por ID");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Agregar tarea al HashMap");
            System.out.println("4. Agregar empleado al HashMap");
            System.out.println("5. Mostrar todas las tareas en HashMap");
            System.out.println("6. Mostrar todos los empleados en HashMap");
            System.out.println("7. Volver al menú de Gestión Avanzada");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID de la tarea: ");
                    String idTareaBusq = scanner.nextLine();
                    TareaPrioridad tareaBusq = hashTareas.get(idTareaBusq);
                    if (tareaBusq != null) {
                        System.out.println("Tarea encontrada: " + tareaBusq);
                    } else {
                        System.out.println("Tarea no encontrada.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese ID del empleado: ");
                    String idEmpBusq = scanner.nextLine();
                    Empleado empBusq = hashEmpleados.get(idEmpBusq);
                    if (empBusq != null) {
                        System.out.println("Empleado encontrado: " + empBusq);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("ID de la tarea: ");
                    String idT = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descT = scanner.nextLine();
                    System.out.print("Urgencia (1-3): ");
                    int urgT = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Fecha de entrega (YYYY-MM-DD): ");
                    String fechaT = scanner.nextLine();
                    System.out.print("Tiempo estimado (horas): ");
                    int tiempoT = scanner.nextInt();
                    scanner.nextLine();
                    TareaPrioridad nuevaTarea = new TareaPrioridad(idT, descT, urgT, fechaT, tiempoT);
                    hashTareas.put(idT, nuevaTarea);
                    tareasGenerales.add(new TareaGeneral(idT, descT, urgT, fechaT, tiempoT));
                    System.out.println("Tarea agregada al HashMap.");
                    break;
                case 4:
                    System.out.print("ID del empleado: ");
                    String idE = scanner.nextLine();
                    System.out.print("Nombre del empleado: ");
                    String nombreE = scanner.nextLine();
                    System.out.print("Departamento: ");
                    String deptoE = scanner.nextLine();
                    Empleado nuevoEmpleado = new Empleado(idE, nombreE, deptoE);
                    hashEmpleados.put(idE, nuevoEmpleado);
                    arbolEmpleados.insertar(nuevoEmpleado); // También se agrega al árbol para consistencia
                    System.out.println("Empleado agregado al HashMap.");
                    break;
                case 5:
                    if (hashTareas.isEmpty()) {
                        System.out.println("No hay tareas en el HashMap.");
                    } else {
                        System.out.println("Tareas en el HashMap:");
                        hashTareas.values().forEach(System.out::println);
                    }
                    break;
                case 6:
                    if (hashEmpleados.isEmpty()) {
                        System.out.println("No hay empleados en el HashMap.");
                    } else {
                        System.out.println("Empleados en el HashMap:");
                        hashEmpleados.values().forEach(System.out::println);
                    }
                    break;
                case 7:
                    System.out.println("Volviendo al menú de Gestión Avanzada...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 7);
    }

    // Menú para Métodos de Ordenamiento y Búsqueda
    private static void menuOrdenamientoBusqueda() {
        int opcion;
        do {
            System.out.println("\n--- Métodos de Ordenamiento y Búsqueda ---");
            System.out.println("1. Ordenar tareas por prioridad y fecha");
            System.out.println("2. Buscar tarea por descripción");
            System.out.println("3. Volver al menú de Gestión Avanzada");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    List<TareaGeneral> tareasOrdenadas = new ArrayList<>(tareasGenerales);
                    tareasOrdenadas.sort((t1, t2) -> {
                        if (t1.getUrgencia() != t2.getUrgencia()) {
                            return Integer.compare(t2.getUrgencia(), t1.getUrgencia());
                        }
                        return t1.getFechaEntrega().compareTo(t2.getFechaEntrega());
                    });
                    System.out.println("Tareas ordenadas por prioridad y fecha:");
                    tareasOrdenadas.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Ingrese descripción o parte de ella: ");
                    String descBusqueda = scanner.nextLine();
                    System.out.println("Tareas encontradas:");
                    boolean encontrada = false;
                    for (TareaGeneral tg : tareasGenerales) {
                        if (tg.getDescripcion().toLowerCase().contains(descBusqueda.toLowerCase())) {
                            System.out.println(tg);
                            encontrada = true;
                        }
                    }
                    if (!encontrada) {
                        System.out.println("No se encontraron tareas con esa descripción.");
                    }
                    break;
                case 3:
                    System.out.println("Volviendo al menú de Gestión Avanzada...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    // Menú para Recursividad y Algoritmos Divide y Vencerás
    private static void menuRecursividadDivideVencer() {
        int opcion;
        do {
            System.out.println("\n--- Recursividad y Algoritmos Divide y Vencerás ---");
            System.out.println("1. Calcular tiempo estimado total (Recursividad)");
            System.out.println("2. Distribuir tareas (Divide y Vencerás)");
            System.out.println("3. Volver al menú de Gestión Avanzada");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    int tiempoTotal = calcularTiempoEstimadoRecursivo(tareasGenerales, 0);
                    System.out.println("Tiempo estimado total: " + tiempoTotal + " horas");
                    break;
                case 2:
                    List<List<TareaGeneral>> distribucion = distribuirTareas(tareasGenerales);
                    System.out.println("Distribución de tareas (Divide y Vencerás):");
                    for (int i = 0; i < distribucion.size(); i++) {
                        System.out.println("Grupo " + (i + 1) + ":");
                        if (distribucion.get(i).isEmpty()) {
                            System.out.println("  (vacío)");
                        } else {
                            distribucion.get(i).forEach(System.out::println);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Volviendo al menú de Gestión Avanzada...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 3);
    }

    // Cálculo recursivo
    private static int calcularTiempoEstimadoRecursivo(List<TareaGeneral> tareas, int indice) {
        if (indice >= tareas.size()) {
            return 0;
        }
        return tareas.get(indice).getTiempoEstimado() + calcularTiempoEstimadoRecursivo(tareas, indice + 1);
    }

    // Algoritmo Divide y Vencerás para distribuir tareas
    private static List<List<TareaGeneral>> distribuirTareas(List<TareaGeneral> tareas) {
        if (tareas.size() <= 1) {
            List<List<TareaGeneral>> result = new ArrayList<>();
            result.add(tareas);
            return result;
        }

        // Dividir tareas en dos grupos
        int mid = tareas.size() / 2;
        List<TareaGeneral> izquierda = new ArrayList<>(tareas.subList(0, mid));
        List<TareaGeneral> derecha = new ArrayList<>(tareas.subList(mid, tareas.size()));

        // Recursivamente distribuir
        List<List<TareaGeneral>> izquierdaDist = distribuirTareas(izquierda);
        List<List<TareaGeneral>> derechaDist = distribuirTareas(derecha);

        // Combinar resultados
        List<List<TareaGeneral>> resultado = new ArrayList<>();
        resultado.addAll(izquierdaDist);
        resultado.addAll(derechaDist);
        return resultado;
    }
}