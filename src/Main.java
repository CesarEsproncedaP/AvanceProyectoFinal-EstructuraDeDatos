import javax.swing.SwingUtilities;
import java.util.*;
import java.text.*;

public class Main {
    public static Map<String, String> users = new HashMap<>();
    private static PriorityQueue<TareaPrioridad> colaTareas = new PriorityQueue<>();
    private static ArbolBinarioEmpleados arbolEmpleados = new ArbolBinarioEmpleados();
    private static Map<String, Empleado> hashEmpleados = new HashMap<>();
    private static List<Maquina> listaMaquinas = new ArrayList<>();
    private static List<Clase> clasesDisponibles = new ArrayList<>();
    

    private static Map<String, List<Clase>> clasesProgramadasPorUsuario = new HashMap<>(); 
    
    private static Map<String, TareaPrioridad> hashTareas = new HashMap<>(); 
    public static String currentUser = null; // Usuario actual logueado

    static {
        users.put("admin", "admin123");

        users.put("clienteA", "passA");
        users.put("clienteB", "passB"); 

        Empleado emp1 = new Empleado("E001", "Juan López", "Ventas");
        Empleado emp2 = new Empleado("E002", "María González", "Marketing");
        Empleado emp3 = new Empleado("E003", "Carlos Rivera", "Operaciones");
        Empleado emp4 = new Empleado("E004", "Laura Flores", "Ventas");
        Empleado emp5 = new Empleado("E005", "Roberto Sánchez", "Limpieza");
        Empleado emp6 = new Empleado("E006", "Sofía Mendoza", "Marketing");
        Empleado emp7 = new Empleado("E007", "Andrés Castro", "Mantenimiento");
        Empleado emp8 = new Empleado("E008", "Isabel Pérez", "Ventas");
        
        arbolEmpleados.insertar(emp1);
        arbolEmpleados.insertar(emp2);
        arbolEmpleados.insertar(emp3);
        arbolEmpleados.insertar(emp4);
        arbolEmpleados.insertar(emp5);
        arbolEmpleados.insertar(emp6);
        arbolEmpleados.insertar(emp7);
        arbolEmpleados.insertar(emp8);

        hashEmpleados.put(emp1.getId(), emp1);
        hashEmpleados.put(emp2.getId(), emp2);
        hashEmpleados.put(emp3.getId(), emp3);
        hashEmpleados.put(emp4.getId(), emp4);
        hashEmpleados.put(emp5.getId(), emp5);
        hashEmpleados.put(emp6.getId(), emp6);
        hashEmpleados.put(emp7.getId(), emp7);
        hashEmpleados.put(emp8.getId(), emp8);

        
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

        listaMaquinas.add(new Maquina("M001", "Cinta de correr", "Operativa"));
        listaMaquinas.add(new Maquina("M002", "Bicicleta estática", "En reparación"));
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

    public static PriorityQueue<TareaPrioridad> getColaTareas() { return colaTareas; }
    public static ArbolBinarioEmpleados getArbolEmpleados() { return arbolEmpleados; }
    public static Map<String, Empleado> getHashEmpleados() { return hashEmpleados; }
    public static List<Maquina> getListaMaquinas() { return listaMaquinas; }
    public static List<Clase> getClasesDisponibles() { return clasesDisponibles; }
    public static Map<String, TareaPrioridad> getHashTareas() { return hashTareas; }


    public static List<Clase> getClasesProgramadasUsuario() { 
        if (currentUser == null) {
            return new ArrayList<>(); 
        }
        return clasesProgramadasPorUsuario.getOrDefault(currentUser, new ArrayList<>()); 
    }
    

    public static void addClaseProgramada(Clase clase) { 
        if (currentUser == null) return; 

        List<Clase> listaUsuario = clasesProgramadasPorUsuario.computeIfAbsent(currentUser, k -> new ArrayList<>());
        
        
        if (!listaUsuario.contains(clase)) {
            listaUsuario.add(clase);
        }
    }

    public static List<TareaPrioridad> ordenarTareasPorPrioridadYFecha(List<TareaPrioridad> tareas) {
        List<TareaPrioridad> sorted = new ArrayList<>(tareas);
        Collections.sort(sorted, Comparator.comparing(TareaPrioridad::getFechaEntrega));
        return sorted;
    }

    public static TareaPrioridad buscarTareaPorId(String id) {
        return hashTareas.get(id);
    }
}