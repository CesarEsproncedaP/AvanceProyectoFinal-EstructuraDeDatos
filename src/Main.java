import javax.swing.SwingUtilities;
import java.util.*;
import java.text.*;

// Clase principal para gestionar datos del gimnasio
public class Main {
    // Estructuras para almacenar datos
    public static Map<String, String> users = new HashMap<>(); // Usuarios y contraseñas
    private static PriorityQueue<TareaPrioridad> colaTareas = new PriorityQueue<>(); // Cola de tareas por prioridad
    private static ArbolBinarioEmpleados arbolEmpleados = new ArbolBinarioEmpleados(); // Árbol de empleados
    private static Map<String, Empleado> hashEmpleados = new HashMap<>(); // HashMap de empleados por ID
    private static List<Maquina> listaMaquinas = new ArrayList<>(); // Lista de máquinas
    private static List<Clase> clasesDisponibles = new ArrayList<>(); // Clases disponibles
    private static Map<String, List<Clase>> clasesProgramadasPorUsuario = new HashMap<>(); // Clases programadas por usuario
    private static Map<String, TareaPrioridad> hashTareas = new HashMap<>(); // HashMap de tareas por ID
    public static String currentUser = null; // Usuario actualmente logueado

    // Bloque estático para inicializar datos
    static {
        // Usuarios predefinidos
        users.put("admin", "admin123");
        users.put("clienteA", "passA");
        users.put("clienteB", "passB");

        // Crear empleados por categoría
        Empleado coach1 = new Empleado("E001", "Ana García", "Coach");
        Empleado coach2 = new Empleado("E002", "Sofía Cruz", "Coach");
        Empleado coach3 = new Empleado("E003", "Luis Pérez", "Coach");
        Empleado coach4 = new Empleado("E004", "Pedro Díaz", "Coach");
        Empleado coach5 = new Empleado("E005", "Marta Gómez", "Coach");
        Empleado coach6 = new Empleado("E006", "Roberto Estrada", "Coach");
        Empleado coach7 = new Empleado("E007", "Ximena Cavazos", "Coach");
        Empleado coach8 = new Empleado("E008", "Juan López", "Coach");
        Empleado coach9 = new Empleado("E009", "María González", "Coach");
        Empleado coach10 = new Empleado("E010", "Carlos Rivera", "Coach");
        Empleado coach11 = new Empleado("E011", "Laura Flores", "Coach");
        Empleado coach12 = new Empleado("E012", "Roberto Sánchez", "Coach");
        Empleado coach13 = new Empleado("E013", "Sofía Mendoza", "Coach");
        Empleado coach14 = new Empleado("E014", "Andrés Castro", "Coach");
        Empleado coach15 = new Empleado("E015", "Isabel Pérez", "Coach");

        Empleado ventas1 = new Empleado("E016", "Diego Vargas", "Ventas");
        Empleado ventas2 = new Empleado("E017", "Elena Ruiz", "Ventas");
        Empleado ventas3 = new Empleado("E018", "Francisco Ortega", "Ventas");
        Empleado ventas4 = new Empleado("E019", "Gabriela Soto", "Ventas");
        Empleado ventas5 = new Empleado("E020", "Hugo Mendoza", "Ventas");
        Empleado ventas6 = new Empleado("E021", "Irene Castro", "Ventas");
        Empleado ventas7 = new Empleado("E022", "Javier López", "Ventas");
        Empleado ventas8 = new Empleado("E023", "Karla González", "Ventas");
        Empleado ventas9 = new Empleado("E024", "Luis Rivera", "Ventas");
        Empleado ventas10 = new Empleado("E025", "Mónica Flores", "Ventas");
        Empleado ventas11 = new Empleado("E026", "Nicolás Sánchez", "Ventas");
        Empleado ventas12 = new Empleado("E027", "Olivia Mendoza", "Ventas");
        Empleado ventas13 = new Empleado("E028", "Pablo Castro", "Ventas");
        Empleado ventas14 = new Empleado("E029", "Quintana Pérez", "Ventas");
        Empleado ventas15 = new Empleado("E030", "Raúl Vargas", "Ventas");

        Empleado marketing1 = new Empleado("E031", "Sara Ruiz", "Marketing");
        Empleado marketing2 = new Empleado("E032", "Tomás Ortega", "Marketing");
        Empleado marketing3 = new Empleado("E033", "Ursula Soto", "Marketing");
        Empleado marketing4 = new Empleado("E034", "Víctor Mendoza", "Marketing");
        Empleado marketing5 = new Empleado("E035", "Wanda Castro", "Marketing");
        Empleado marketing6 = new Empleado("E036", "Xavier López", "Marketing");
        Empleado marketing7 = new Empleado("E037", "Yolanda González", "Marketing");
        Empleado marketing8 = new Empleado("E038", "Zacarias Rivera", "Marketing");
        Empleado marketing9 = new Empleado("E039", "Alicia Flores", "Marketing");
        Empleado marketing10 = new Empleado("E040", "Benito Sánchez", "Marketing");
        Empleado marketing11 = new Empleado("E041", "Carmen Mendoza", "Marketing");
        Empleado marketing12 = new Empleado("E042", "David Castro", "Marketing");
        Empleado marketing13 = new Empleado("E043", "Eva Pérez", "Marketing");
        Empleado marketing14 = new Empleado("E044", "Felipe Vargas", "Marketing");
        Empleado marketing15 = new Empleado("E045", "Gloria Ruiz", "Marketing");

        Empleado limpieza1 = new Empleado("E046", "Héctor Ortega", "Limpieza");
        Empleado limpieza2 = new Empleado("E047", "Inés Soto", "Limpieza");
        Empleado limpieza3 = new Empleado("E048", "Jorge Mendoza", "Limpieza");
        Empleado limpieza4 = new Empleado("E049", "Katia Castro", "Limpieza");
        Empleado limpieza5 = new Empleado("E050", "Leonel López", "Limpieza");
        Empleado limpieza6 = new Empleado("E051", "Marta González", "Limpieza");
        Empleado limpieza7 = new Empleado("E052", "Néstor Rivera", "Limpieza");
        Empleado limpieza8 = new Empleado("E053", "Olga Flores", "Limpieza");
        Empleado limpieza9 = new Empleado("E054", "Pedro Sánchez", "Limpieza");
        Empleado limpieza10 = new Empleado("E055", "Quinta Mendoza", "Limpieza");

        Empleado mantenimiento1 = new Empleado("E056", "Ramón Castro", "Mantenimiento");
        Empleado mantenimiento2 = new Empleado("E057", "Susana Pérez", "Mantenimiento");
        Empleado mantenimiento3 = new Empleado("E058", "Tomás Vargas", "Mantenimiento");
        Empleado mantenimiento4 = new Empleado("E059", "Úrsula Ruiz", "Mantenimiento");
        Empleado mantenimiento5 = new Empleado("E060", "Víctor Ortega", "Mantenimiento");
        Empleado mantenimiento6 = new Empleado("E061", "Wendy Soto", "Mantenimiento");
        Empleado mantenimiento7 = new Empleado("E062", "Xavier Mendoza", "Mantenimiento");
        Empleado mantenimiento8 = new Empleado("E063", "Yessica Castro", "Mantenimiento");
        Empleado mantenimiento9 = new Empleado("E064", "Zoe López", "Mantenimiento");
        Empleado mantenimiento10 = new Empleado("E065", "Alberto González", "Mantenimiento");

        Empleado cajero1 = new Empleado("E066", "Beatriz Rivera", "Cajero");
        Empleado cajero2 = new Empleado("E067", "Carlos Flores", "Cajero");
        Empleado cajero3 = new Empleado("E068", "Daniel Sánchez", "Cajero");
        Empleado cajero4 = new Empleado("E069", "Elena Mendoza", "Cajero");
        Empleado cajero5 = new Empleado("E070", "Fernando Castro", "Cajero");
        Empleado cajero6 = new Empleado("E071", "Gabriela Pérez", "Cajero");
        Empleado cajero7 = new Empleado("E072", "Humberto Vargas", "Cajero");
        Empleado cajero8 = new Empleado("E073", "Isabel Ruiz", "Cajero");
        Empleado cajero9 = new Empleado("E074", "Juan Ortega", "Cajero");
        Empleado cajero10 = new Empleado("E075", "Karla Soto", "Cajero");

        Empleado operaciones1 = new Empleado("E076", "Luis Mendoza", "Operaciones");
        Empleado operaciones2 = new Empleado("E077", "María Castro", "Operaciones");
        Empleado operaciones3 = new Empleado("E078", "Nicolás López", "Operaciones");
        Empleado operaciones4 = new Empleado("E079", "Olga González", "Operaciones");
        Empleado operaciones5 = new Empleado("E080", "Pablo Rivera", "Operaciones");
        Empleado operaciones6 = new Empleado("E081", "Quintana Flores", "Operaciones");
        Empleado operaciones7 = new Empleado("E082", "Raúl Sánchez", "Operaciones");
        Empleado operaciones8 = new Empleado("E083", "Sara Mendoza", "Operaciones");
        Empleado operaciones9 = new Empleado("E084", "Tomás Castro", "Operaciones");
        Empleado operaciones10 = new Empleado("E085", "Ursula Pérez", "Operaciones");

        Empleado rh1 = new Empleado("E086", "Víctor Vargas", "Recursos Humanos");
        Empleado rh2 = new Empleado("E087", "Wanda Ruiz", "Recursos Humanos");
        Empleado rh3 = new Empleado("E088", "Xavier Ortega", "Recursos Humanos");
        Empleado rh4 = new Empleado("E089", "Yolanda Soto", "Recursos Humanos");
        Empleado rh5 = new Empleado("E090", "Zacarias Mendoza", "Recursos Humanos");

        Empleado finanzas1 = new Empleado("E091", "Alicia Castro", "Finanzas");
        Empleado finanzas2 = new Empleado("E092", "Benito Pérez", "Finanzas");
        Empleado finanzas3 = new Empleado("E093", "Carmen Vargas", "Finanzas");
        Empleado finanzas4 = new Empleado("E094", "David Ruiz", "Finanzas");
        Empleado finanzas5 = new Empleado("E095", "Eva Ortega", "Finanzas");
        Empleado finanzas6 = new Empleado("E096", "Felipe Soto", "Finanzas");
        Empleado finanzas7 = new Empleado("E097", "Gloria Mendoza", "Finanzas");
        Empleado finanzas8 = new Empleado("E098", "Héctor Castro", "Finanzas");
        Empleado finanzas9 = new Empleado("E099", "Inés Pérez", "Finanzas");
        Empleado finanzas10 = new Empleado("E100", "Jorge Vargas", "Finanzas");

        // Insertar empleados en el árbol
        arbolEmpleados.insertar(coach1);
        arbolEmpleados.insertar(coach2);
        arbolEmpleados.insertar(coach3);
        arbolEmpleados.insertar(coach4);
        arbolEmpleados.insertar(coach5);
        arbolEmpleados.insertar(coach6);
        arbolEmpleados.insertar(coach7);
        arbolEmpleados.insertar(coach8);
        arbolEmpleados.insertar(coach9);
        arbolEmpleados.insertar(coach10);
        arbolEmpleados.insertar(coach11);
        arbolEmpleados.insertar(coach12);
        arbolEmpleados.insertar(coach13);
        arbolEmpleados.insertar(coach14);
        arbolEmpleados.insertar(coach15);
        arbolEmpleados.insertar(ventas1);
        arbolEmpleados.insertar(ventas2);
        arbolEmpleados.insertar(ventas3);
        arbolEmpleados.insertar(ventas4);
        arbolEmpleados.insertar(ventas5);
        arbolEmpleados.insertar(ventas6);
        arbolEmpleados.insertar(ventas7);
        arbolEmpleados.insertar(ventas8);
        arbolEmpleados.insertar(ventas9);
        arbolEmpleados.insertar(ventas10);
        arbolEmpleados.insertar(ventas11);
        arbolEmpleados.insertar(ventas12);
        arbolEmpleados.insertar(ventas13);
        arbolEmpleados.insertar(ventas14);
        arbolEmpleados.insertar(ventas15);
        arbolEmpleados.insertar(marketing1);
        arbolEmpleados.insertar(marketing2);
        arbolEmpleados.insertar(marketing3);
        arbolEmpleados.insertar(marketing4);
        arbolEmpleados.insertar(marketing5);
        arbolEmpleados.insertar(marketing6);
        arbolEmpleados.insertar(marketing7);
        arbolEmpleados.insertar(marketing8);
        arbolEmpleados.insertar(marketing9);
        arbolEmpleados.insertar(marketing10);
        arbolEmpleados.insertar(marketing11);
        arbolEmpleados.insertar(marketing12);
        arbolEmpleados.insertar(marketing13);
        arbolEmpleados.insertar(marketing14);
        arbolEmpleados.insertar(marketing15);
        arbolEmpleados.insertar(limpieza1);
        arbolEmpleados.insertar(limpieza2);
        arbolEmpleados.insertar(limpieza3);
        arbolEmpleados.insertar(limpieza4);
        arbolEmpleados.insertar(limpieza5);
        arbolEmpleados.insertar(limpieza6);
        arbolEmpleados.insertar(limpieza7);
        arbolEmpleados.insertar(limpieza8);
        arbolEmpleados.insertar(limpieza9);
        arbolEmpleados.insertar(limpieza10);
        arbolEmpleados.insertar(mantenimiento1);
        arbolEmpleados.insertar(mantenimiento2);
        arbolEmpleados.insertar(mantenimiento3);
        arbolEmpleados.insertar(mantenimiento4);
        arbolEmpleados.insertar(mantenimiento5);
        arbolEmpleados.insertar(mantenimiento6);
        arbolEmpleados.insertar(mantenimiento7);
        arbolEmpleados.insertar(mantenimiento8);
        arbolEmpleados.insertar(mantenimiento9);
        arbolEmpleados.insertar(mantenimiento10);
        arbolEmpleados.insertar(cajero1);
        arbolEmpleados.insertar(cajero2);
        arbolEmpleados.insertar(cajero3);
        arbolEmpleados.insertar(cajero4);
        arbolEmpleados.insertar(cajero5);
        arbolEmpleados.insertar(cajero6);
        arbolEmpleados.insertar(cajero7);
        arbolEmpleados.insertar(cajero8);
        arbolEmpleados.insertar(cajero9);
        arbolEmpleados.insertar(cajero10);
        arbolEmpleados.insertar(operaciones1);
        arbolEmpleados.insertar(operaciones2);
        arbolEmpleados.insertar(operaciones3);
        arbolEmpleados.insertar(operaciones4);
        arbolEmpleados.insertar(operaciones5);
        arbolEmpleados.insertar(operaciones6);
        arbolEmpleados.insertar(operaciones7);
        arbolEmpleados.insertar(operaciones8);
        arbolEmpleados.insertar(operaciones9);
        arbolEmpleados.insertar(operaciones10);
        arbolEmpleados.insertar(rh1);
        arbolEmpleados.insertar(rh2);
        arbolEmpleados.insertar(rh3);
        arbolEmpleados.insertar(rh4);
        arbolEmpleados.insertar(rh5);
        arbolEmpleados.insertar(finanzas1);
        arbolEmpleados.insertar(finanzas2);
        arbolEmpleados.insertar(finanzas3);
        arbolEmpleados.insertar(finanzas4);
        arbolEmpleados.insertar(finanzas5);
        arbolEmpleados.insertar(finanzas6);
        arbolEmpleados.insertar(finanzas7);
        arbolEmpleados.insertar(finanzas8);
        arbolEmpleados.insertar(finanzas9);
        arbolEmpleados.insertar(finanzas10);

        // Insertar empleados en HashMap
        hashEmpleados.put(coach1.getId(), coach1);
        hashEmpleados.put(coach2.getId(), coach2);
        hashEmpleados.put(coach3.getId(), coach3);
        hashEmpleados.put(coach4.getId(), coach4);
        hashEmpleados.put(coach5.getId(), coach5);
        hashEmpleados.put(coach6.getId(), coach6);
        hashEmpleados.put(coach7.getId(), coach7);
        hashEmpleados.put(coach8.getId(), coach8);
        hashEmpleados.put(coach9.getId(), coach9);
        hashEmpleados.put(coach10.getId(), coach10);
        hashEmpleados.put(coach11.getId(), coach11);
        hashEmpleados.put(coach12.getId(), coach12);
        hashEmpleados.put(coach13.getId(), coach13);
        hashEmpleados.put(coach14.getId(), coach14);
        hashEmpleados.put(coach15.getId(), coach15);
        hashEmpleados.put(ventas1.getId(), ventas1);
        hashEmpleados.put(ventas2.getId(), ventas2);
        hashEmpleados.put(ventas3.getId(), ventas3);
        hashEmpleados.put(ventas4.getId(), ventas4);
        hashEmpleados.put(ventas5.getId(), ventas5);
        hashEmpleados.put(ventas6.getId(), ventas6);
        hashEmpleados.put(ventas7.getId(), ventas7);
        hashEmpleados.put(ventas8.getId(), ventas8);
        hashEmpleados.put(ventas9.getId(), ventas9);
        hashEmpleados.put(ventas10.getId(), ventas10);
        hashEmpleados.put(ventas11.getId(), ventas11);
        hashEmpleados.put(ventas12.getId(), ventas12);
        hashEmpleados.put(ventas13.getId(), ventas13);
        hashEmpleados.put(ventas14.getId(), ventas14);
        hashEmpleados.put(ventas15.getId(), ventas15);
        hashEmpleados.put(marketing1.getId(), marketing1);
        hashEmpleados.put(marketing2.getId(), marketing2);
        hashEmpleados.put(marketing3.getId(), marketing3);
        hashEmpleados.put(marketing4.getId(), marketing4);
        hashEmpleados.put(marketing5.getId(), marketing5);
        hashEmpleados.put(marketing6.getId(), marketing6);
        hashEmpleados.put(marketing7.getId(), marketing7);
        hashEmpleados.put(marketing8.getId(), marketing8);
        hashEmpleados.put(marketing9.getId(), marketing9);
        hashEmpleados.put(marketing10.getId(), marketing10);
        hashEmpleados.put(marketing11.getId(), marketing11);
        hashEmpleados.put(marketing12.getId(), marketing12);
        hashEmpleados.put(marketing13.getId(), marketing13);
        hashEmpleados.put(marketing14.getId(), marketing14);
        hashEmpleados.put(marketing15.getId(), marketing15);
        hashEmpleados.put(limpieza1.getId(), limpieza1);
        hashEmpleados.put(limpieza2.getId(), limpieza2);
        hashEmpleados.put(limpieza3.getId(), limpieza3);
        hashEmpleados.put(limpieza4.getId(), limpieza4);
        hashEmpleados.put(limpieza5.getId(), limpieza5);
        hashEmpleados.put(limpieza6.getId(), limpieza6);
        hashEmpleados.put(limpieza7.getId(), limpieza7);
        hashEmpleados.put(limpieza8.getId(), limpieza8);
        hashEmpleados.put(limpieza9.getId(), limpieza9);
        hashEmpleados.put(limpieza10.getId(), limpieza10);
        hashEmpleados.put(mantenimiento1.getId(), mantenimiento1);
        hashEmpleados.put(mantenimiento2.getId(), mantenimiento2);
        hashEmpleados.put(mantenimiento3.getId(), mantenimiento3);
        hashEmpleados.put(mantenimiento4.getId(), mantenimiento4);
        hashEmpleados.put(mantenimiento5.getId(), mantenimiento5);
        hashEmpleados.put(mantenimiento6.getId(), mantenimiento6);
        hashEmpleados.put(mantenimiento7.getId(), mantenimiento7);
        hashEmpleados.put(mantenimiento8.getId(), mantenimiento8);
        hashEmpleados.put(mantenimiento9.getId(), mantenimiento9);
        hashEmpleados.put(mantenimiento10.getId(), mantenimiento10);
        hashEmpleados.put(cajero1.getId(), cajero1);
        hashEmpleados.put(cajero2.getId(), cajero2);
        hashEmpleados.put(cajero3.getId(), cajero3);
        hashEmpleados.put(cajero4.getId(), cajero4);
        hashEmpleados.put(cajero5.getId(), cajero5);
        hashEmpleados.put(cajero6.getId(), cajero6);
        hashEmpleados.put(cajero7.getId(), cajero7);
        hashEmpleados.put(cajero8.getId(), cajero8);
        hashEmpleados.put(cajero9.getId(), cajero9);
        hashEmpleados.put(cajero10.getId(), cajero10);
        hashEmpleados.put(operaciones1.getId(), operaciones1);
        hashEmpleados.put(operaciones2.getId(), operaciones2);
        hashEmpleados.put(operaciones3.getId(), operaciones3);
        hashEmpleados.put(operaciones4.getId(), operaciones4);
        hashEmpleados.put(operaciones5.getId(), operaciones5);
        hashEmpleados.put(operaciones6.getId(), operaciones6);
        hashEmpleados.put(operaciones7.getId(), operaciones7);
        hashEmpleados.put(operaciones8.getId(), operaciones8);
        hashEmpleados.put(operaciones9.getId(), operaciones9);
        hashEmpleados.put(operaciones10.getId(), operaciones10);
        hashEmpleados.put(rh1.getId(), rh1);
        hashEmpleados.put(rh2.getId(), rh2);
        hashEmpleados.put(rh3.getId(), rh3);
        hashEmpleados.put(rh4.getId(), rh4);
        hashEmpleados.put(rh5.getId(), rh5);
        hashEmpleados.put(finanzas1.getId(), finanzas1);
        hashEmpleados.put(finanzas2.getId(), finanzas2);
        hashEmpleados.put(finanzas3.getId(), finanzas3);
        hashEmpleados.put(finanzas4.getId(), finanzas4);
        hashEmpleados.put(finanzas5.getId(), finanzas5);
        hashEmpleados.put(finanzas6.getId(), finanzas6);
        hashEmpleados.put(finanzas7.getId(), finanzas7);
        hashEmpleados.put(finanzas8.getId(), finanzas8);
        hashEmpleados.put(finanzas9.getId(), finanzas9);
        hashEmpleados.put(finanzas10.getId(), finanzas10);

        // Inicializar tareas con fechas y prioridades
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            TareaPrioridad tarea1 = new TareaPrioridad("T001", "Limpiar area de pesas", sdf.parse("2025-09-01"), 2);
            TareaPrioridad tarea2 = new TareaPrioridad("T002", "Verificar sistema de sonido", sdf.parse("2025-09-02"), 1);
            TareaPrioridad tarea3 = new TareaPrioridad("T003", "Revisar maquina de cardio", sdf.parse("2025-09-03"), 3);
            TareaPrioridad tarea4 = new TareaPrioridad("T004", "Programar reunion con instructores", sdf.parse("2025-09-04"), 1);
            TareaPrioridad tarea5 = new TareaPrioridad("T005", "Limpiar vidrios del frente", sdf.parse("2025-09-05"), 2);
            TareaPrioridad tarea6 = new TareaPrioridad("T006", "Inventario de equipos de spinning", sdf.parse("2025-09-06"), 4);
            TareaPrioridad tarea7 = new TareaPrioridad("T007", "Actualizar perfil de cliente", sdf.parse("2025-09-07"), 1);
            TareaPrioridad tarea8 = new TareaPrioridad("T008", "Reparar la caminadora 3", sdf.parse("2025-09-08"), 5);
            TareaPrioridad tarea9 = new TareaPrioridad("T009", "Revisar las luces del gimnasio", sdf.parse("2025-09-09"), 2);
            TareaPrioridad tarea10 = new TareaPrioridad("T010", "Organizar sala de spinning", sdf.parse("2025-09-10"), 3);
            TareaPrioridad tarea11 = new TareaPrioridad("T011", "Comprar mas toallas", sdf.parse("2025-09-11"), 1);
            TareaPrioridad tarea12 = new TareaPrioridad("T012", "Limpiar los baños", sdf.parse("2025-09-12"), 2);
            TareaPrioridad tarea13 = new TareaPrioridad("T013", "Actualizar software de maquinas", sdf.parse("2025-09-13"), 4);
            TareaPrioridad tarea14 = new TareaPrioridad("T014", "Revisar los aires acondicionados", sdf.parse("2025-09-14"), 3);
            TareaPrioridad tarea15 = new TareaPrioridad("T015", "Reemplazar los tapetes del piso", sdf.parse("2025-09-15"), 5);

            // Agregar tareas a la cola y HashMap
            colaTareas.add(tarea1);
            colaTareas.add(tarea2);
            colaTareas.add(tarea3);
            colaTareas.add(tarea4);
            colaTareas.add(tarea5);
            colaTareas.add(tarea6);
            colaTareas.add(tarea7);
            colaTareas.add(tarea8);
            colaTareas.add(tarea9);
            colaTareas.add(tarea10);
            colaTareas.add(tarea11);
            colaTareas.add(tarea12);
            colaTareas.add(tarea13);
            colaTareas.add(tarea14);
            colaTareas.add(tarea15);

            hashTareas.put(tarea1.getId(), tarea1);
            hashTareas.put(tarea2.getId(), tarea2);
            hashTareas.put(tarea3.getId(), tarea3);
            hashTareas.put(tarea4.getId(), tarea4);
            hashTareas.put(tarea5.getId(), tarea5);
            hashTareas.put(tarea6.getId(), tarea6);
            hashTareas.put(tarea7.getId(), tarea7);
            hashTareas.put(tarea8.getId(), tarea8);
            hashTareas.put(tarea9.getId(), tarea9);
            hashTareas.put(tarea10.getId(), tarea10);
            hashTareas.put(tarea11.getId(), tarea11);
            hashTareas.put(tarea12.getId(), tarea12);
            hashTareas.put(tarea13.getId(), tarea13);
            hashTareas.put(tarea14.getId(), tarea14);
            hashTareas.put(tarea15.getId(), tarea15);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Inicializar máquinas
        listaMaquinas.add(new Maquina("M001", "Cinta de correr", "Operativa"));
        listaMaquinas.add(new Maquina("M002", "Bicicleta de estática", "En reparación"));
        listaMaquinas.add(new Maquina("M003", "Máquina de remo", "Operativa"));
        listaMaquinas.add(new Maquina("M004", "Elíptica", "Operativa"));
        listaMaquinas.add(new Maquina("M005", "Prensa de piernas", "Operativa"));
        listaMaquinas.add(new Maquina("M006", "Polea alta", "En reparación"));
        listaMaquinas.add(new Maquina("M007", "Banco de pesas", "Operativa"));
        listaMaquinas.add(new Maquina("M008", "Máquina de abdominales", "Operativa"));
        listaMaquinas.add(new Maquina("M009", "Stepper", "En reparación"));
        listaMaquinas.add(new Maquina("M010", "Rack de sentadillas", "Operativa"));
        listaMaquinas.add(new Maquina("M011", "Maquina Smith", "Operativa"));
        listaMaquinas.add(new Maquina("M012", "Jaula de potencia", "Operativa"));
        listaMaquinas.add(new Maquina("M013", "Pesas rusas", "Operativa"));
        listaMaquinas.add(new Maquina("M014", "Barra de dominadas", "Operativa"));
        listaMaquinas.add(new Maquina("M015", "Maquina de remo", "En reparación"));
        listaMaquinas.add(new Maquina("M016", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M017", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M018", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M019", "Bicicleta de spinning", "Operativa"));
        listaMaquinas.add(new Maquina("M020", "Bicicleta de spinning", "Operativa"));

        // Inicializar clases disponibles
        clasesDisponibles.add(new Clase("Yoga Avanzado", "Ana García", "Lunes 10:00 AM - 11:30 AM"));
        clasesDisponibles.add(new Clase("Zumba Fitness", "Sofía Cruz", "Lunes 12:00 PM - 1:30 PM"));
        clasesDisponibles.add(new Clase("Danza Aeróbica", "Sofía Cruz", "Lunes 6:00 PM - 7:30 PM"));
        clasesDisponibles.add(new Clase("Spinning Intenso", "Luis Pérez", "Martes 8:00 PM - 9:30 PM"));
        clasesDisponibles.add(new Clase("Pilates Mat", "Ana García", "Miércoles 6:00 AM - 7:00 AM"));
        clasesDisponibles.add(new Clase("Levantamiento Olímpico", "Pedro Díaz", "Miércoles 9:00 AM - 10:00 AM"));
        clasesDisponibles.add(new Clase("Cardio Extremo", "Luis Pérez", "Jueves 7:00 PM - 8:00 PM"));
        clasesDisponibles.add(new Clase("Funcional HIIT", "Marta Gómez", "Jueves 9:00 AM - 10:00 AM"));
        clasesDisponibles.add(new Clase("Boxeo Fit", "Roberto Estrada", "Viernes 9:00 AM - 10:00 AM"));
        clasesDisponibles.add(new Clase("Cross Training", "Ximena Cavazos", "Sábado 11:00 AM - 12:00 PM"));
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                LoginFrame loginFrame = new LoginFrame();
                MainFrame mainFrame = new MainFrame(loginFrame);
                RegistroFrame registroFrame = new RegistroFrame(loginFrame);
                loginFrame.setFrames(mainFrame, registroFrame);
                loginFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al iniciar la aplicación. Asegúrate de que todas las clases Frame existen y compilan.");
            }
        });
    }

    // Métodos para acceder a datos
    public static PriorityQueue<TareaPrioridad> getColaTareas() { return colaTareas; }
    public static ArbolBinarioEmpleados getArbolEmpleados() { return arbolEmpleados; }
    public static Map<String, Empleado> getHashEmpleados() { return hashEmpleados; }
    public static List<Maquina> getListaMaquinas() { return listaMaquinas; }
    public static List<Clase> getClasesDisponibles() { return clasesDisponibles; }
    public static Map<String, TareaPrioridad> getHashTareas() { return hashTareas; }

    // Obtener clases programadas del usuario actual
    public static List<Clase> getClasesProgramadasUsuario() {
        if (currentUser == null) {
            return new ArrayList<>();
        }
        return clasesProgramadasPorUsuario.getOrDefault(currentUser, new ArrayList<>());
    }

    // Agregar clase programada para el usuario actual
    public static void addClaseProgramada(Clase clase) {
        if (currentUser == null) return;
        List<Clase> listaUsuario = clasesProgramadasPorUsuario.computeIfAbsent(currentUser, k -> new ArrayList<>());
        if (!listaUsuario.contains(clase)) {
            listaUsuario.add(clase);
        }
    }

    // Eliminar clase programada para todos los usuarios
    public static void removeClaseProgramadaForAll(Clase clase) {
        for (List<Clase> userClasses : clasesProgramadasPorUsuario.values()) {
            userClasses.remove(clase);
        }
    }

    // Ordenar tareas por fecha de entrega
    public static List<TareaPrioridad> ordenarTareasPorPrioridadYFecha(List<TareaPrioridad> tareas) {
        List<TareaPrioridad> sorted = new ArrayList<>(tareas);
        Collections.sort(sorted, Comparator.comparing(TareaPrioridad::getFechaEntrega));
        return sorted;
    }

    // Buscar tarea por ID
    public static TareaPrioridad buscarTareaPorId(String id) {
        return hashTareas.get(id);
    }

    // Calcular tiempo total de tareas recursivamente
    public static int calcularTiempoTotalRecursivo(List<TareaPrioridad> tareas, int index) {
        if (index >= tareas.size()) {
            return 0;
        }
        return tareas.get(index).getTiempoEstimado() + calcularTiempoTotalRecursivo(tareas, index + 1);
    }
}