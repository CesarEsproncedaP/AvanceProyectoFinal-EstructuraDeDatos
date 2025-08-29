# Gestión de Gimnasio

¡Bienvenido al proyecto **Gestión de Gimnasio**! Este es un sistema desarrollado en Java para gestionar las operaciones de un gimnasio, incluyendo tareas urgentes de mantenimiento (usando una pila), clases programadas (usando una cola) e inventario de máquinas (usando una lista). El programa permite agregar, completar y visualizar tareas, programar y cancelar clases, y administrar el estado de las máquinas del gimnasio.

## Tabla de Contenidos
- [Descripción](#descripción)
- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Cómo Ejecutar](#cómo-ejecutar)
- [Uso](#uso)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Contribuir](#contribuir)
- [Licencia](#licencia)
- [Contacto](#contacto)
- [Notas Adicionales](#notas-adicionales)

## Descripción
El sistema **Gestión de Gimnasio** es una codigo escrito en Java que utiliza estructuras de datos para organizar las operaciones diarias de un gimnasio. Incluye:
- **Pila** para tareas urgentes de mantenimiento (por ejemplo, reparar máquinas).
- **Cola** para gestionar clases programadas con horarios e instructores.
- **Lista** para el inventario de máquinas, con seguimiento de su estado (funcionando o averiadas).

## Características
- **Gestión de tareas urgentes**: Agrega, completa y consulta tareas de mantenimiento usando una pila (`Stack`).
- *Programación de clases*: Visualiza, inicia, cancela o agrega clases programadas usando una cola (`LinkedList` como `Queue`).
- **Inventario de máquinas**: Administra máquinas, marcándolas como averiadas o reparadas, usando una lista (`LinkedList`).
- **Interfaz de consola interactiva**: Menús claros para interactuar con el sistema.
- **Datos predefinidos**: Incluye instructores, clases y máquinas inicializadas para pruebas rápidas.

## Requisitos
Para ejecutar este proyecto, necesitas lo siguiente:

| Requisito  | Versión      | Instalación                                                                 |
|------------|--------------|-----------------------------------------------------------------------------|
| Java (JDK) | 8 o superior | [Descarga Java](https://www.oracle.com/java/technologies/javase-downloads.html) o `sudo apt install openjdk-11-jdk` (Linux) |
| Git        | Latest       | [Descarga Git](https://git-scm.com/downloads) o `sudo apt install git` (Linux) |

- No se requieren dependencias externas, ya que el proyecto usa la biblioteca estándar de Java (`java.util`).

## Instalación
Sigue estos pasos para configurar el proyecto en tu máquina:

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/gestion-gimnasio.git
   
2. **Navega al directorio del proyecto**:
   ```bash
   cd gestion-gimnasio

-Verifica que tienes Java instalado:bash

3. **Verifica que tienes Java instalado**:
   ```bash
   java -version
-Si no está instalado, sigue las instrucciones de instalación de Java según tu sistema operativo.

## Cómo Ejecutar
```bash
javac *.java
java Main
```

💡 Consejo: Asegúrate de estar en el directorio que contiene los archivos `.java` al compilar y ejecutar.

## Uso
Una vez que ejecutes el programa, aparecerá un menú interactivo en la consola con las siguientes opciones:

### Tareas de Mantenimiento Urgente (Pila)
- Agrega tareas urgentes (por ejemplo, reparar una máquina).
- Completa la tarea más reciente.
- Consulta la próxima tarea sin eliminarla.

### Clases Programadas (Cola)
- Visualiza la próxima clase.
- Inicia (y finaliza) la próxima clase.
- Lista todas las clases programadas.
- Cancela una clase por nombre.
- Agrega una nueva clase seleccionando instructor y horario.

### Inventario de Máquinas (Lista)
- Lista todas las máquinas y su estado.
- Marca una máquina como averiada (genera una tarea urgente).
- Marca una máquina como reparada.

### Otras opciones
- **Ver estado completo**: Muestra el estado de tareas, clases y máquinas.
- **Salir**: Cierra el programa.

### Ejemplo de interacción
```
--- SISTEMA DE GESTIÓN DE TAREAS GYM ---
1. Tareas de Mantenimiento Urgente (Pila)
2. Clases Programadas (Cola)
3. Inventario de Máquinas (Lista)
4. Ver estado de todas las tareas y equipos
5. Salir
Seleccione una opción: 2

--- Clases Programadas (Cola) ---
1. Ver próxima clase (front)
2. Iniciar próxima clase (dequeue)
3. Ver todas las clases programadas
4. Cancelar una clase
5. Agregar una clase programada
6. Volver al menú principal
Seleccione una opción: 1
La próxima clase es: Zumba
```

## Estructura del Proyecto
El proyecto está organizado de la siguiente manera:
```
gestion-gimnasio/
├── Main.java             # Clase principal con el menú y lógica del programa
├── ClaseProgramada.java  # Clase para gestionar clases programadas
├── instructor.java       # Clase para representar instructores
├── Maquina.java          # Clase para gestionar máquinas del gimnasio
├── Tarea.java            # Clase para representar tareas urgentes
└── README.md             # Este archivo
```

