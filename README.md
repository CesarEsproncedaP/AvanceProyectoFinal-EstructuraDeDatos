# 🚀 Gestión de Gimnasio Avanzada: Estructuras y Algoritmos

¡Bienvenido al proyecto **Gestión de Gimnasio Avanzada**! Este es un sistema desarrollado en Java que implementa **estructuras de datos complejas y algoritmos de optimización** para una gestión eficiente de recursos, clases y mantenimiento.

## 📋 Tabla de Contenidos
- [Descripción](#-descripción)
- [Características Clave](#-características-clave)
- [Fundamentos Algorítmicos](#-fundamentos-algorítmicos)
- [Interfaz Gráfica (GUI)](#-interfaz-gráfica-gui---módulos-de-operación)
- [Requisitos](#-requisitos)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Uso](#-uso)
- [Estructura del Proyecto](#-estructura-del-proyecto)

---

## ✨ Descripción
El sistema es una aplicación con **Interfaz Gráfica (GUI)** construida en Java. Su objetivo es resolver problemas de **velocidad ($O(1)$) y planificación óptima** ($O(n \log n)$) en la gestión de un gimnasio. Integra módulos para la administración de personal, la información de **Clases Programadas** y la planificación estratégica de tareas de mantenimiento, incluyendo la gestión de **Máquinas en Reparación**.

---

## 🔑 Características Clave
* **Interfaz Gráfica (GUI):** Operación intuitiva mediante pestañas organizadas por módulos.
* **Velocidad Superior:** Acceso instantáneo a datos críticos usando **Tablas Hash**.
* **Gestión Estratégica de Mantenimiento:** Uso de **Cola de Prioridad** para Máquinas en Reparación y **Merge Sort** para optimizar la asignación de tareas.
* **Escalabilidad:** Implementación de estructuras con baja complejidad algorítmica (ej., $O(\log n)$) para soportar el crecimiento del negocio.

---

## ⚙️ Fundamentos Algorítmicos

La clave del proyecto es la aplicación estratégica de las siguientes estructuras y métodos, garantizando la eficiencia en cada módulo:

| Módulo/Función | Estructura/Algoritmo | Complejidad | Propósito Principal |
| :--- | :--- | :--- | :--- |
| **Clases / Búsqueda** | **Tabla Hash** (`HashMap`) | $O(1)$ | Acceso instantáneo a **Clases Programadas**. |
| **Personal / Organización** | **Árbol Binario (BST)** | $O(\log n)$ | Almacenar y listar empleados en orden alfabético. |
| **Tareas / Urgencia** | **Cola de Prioridad** (`PriorityQueue`) | $O(\log n)$ | Priorizar **Máquinas en Reparación** (lo más crítico va primero). |
| **Tareas / Optimización** | **Merge Sort** | $O(n \log n)$ | Implementación **Divide y Vencerás** para ordenar tareas por duración. |
| **Tareas / Carga Total** | **Función Recursiva** | $O(N)$ | Cálculo instantáneo de las horas totales de trabajo pendientes. |

---

## 🖼️ Interfaz Gráfica (GUI) - Módulos de Operación

La aplicación se presenta como un `JFrame` organizado en pestañas (`JTabbedPane`), donde cada módulo refleja la implementación de una estructura de datos clave para resolver una necesidad operativa específica del gimnasio:

| Pestaña (Módulo) | Estructura de Datos | Función en la Gestión del Gimnasio |
| :--- | :--- | :--- |
| **Personal** | **Árbol Binario (BST)** | Gestión y listado ordenado de empleados ($\boldsymbol{O(\log n)}$). |
| **Clases/Búsqueda** | **Tabla Hash** | Búsqueda y acceso **instantáneo** ($\boldsymbol{O(1)}$) a horarios, instructores y capacidad de **Clases Programadas**. |
| **Tareas (Prioridad)** | **Cola de Prioridad** | Sistema de tickets de mantenimiento que prioriza automáticamente las **Máquinas en Reparación** más críticas. |
| **Planificación/Optimización** | **Merge Sort / Recursividad** | Herramientas para el manager: permite ordenar tareas por duración (Merge Sort) y calcular el total de la carga de trabajo (Recursividad). |

---

## ✅ Requisitos
Para compilar y ejecutar este proyecto, necesitas lo siguiente:

* **Java Development Kit (JDK) 8 o superior.**
* Un Entorno de Desarrollo (IDE) como **NetBeans** o **IntelliJ IDEA** (recomendado para trabajar con la GUI).

---

## 🛠️ Instalación y Ejecución

Sigue estos pasos para configurar y ejecutar el proyecto:

1.  **Clona el repositorio** en tu máquina local:
    ```bash
    git clone [https://github.com/tu-usuario/gestion-gimnasio-avanzada.git](https://github.com/tu-usuario/gestion-gimnasio-avanzada.git)
    ```

2.  **Navega al directorio del proyecto**:
    ```bash
    cd gestion-gimnasio-avanzada
    ```

3.  **Ejecuta desde tu IDE**:
    * Abre la carpeta del proyecto en NetBeans o IntelliJ IDEA.
    * Ejecuta la clase principal (`Main.java` o el Frame principal).

---

## 🖥️ Uso
La aplicación opera de forma sencilla siguiendo la lógica de los módulos de la GUI, permitiendo al usuario interactuar con la gestión del gimnasio en tres áreas clave: Personal, Clases y Mantenimiento.

---

## 📁 Estructura del Proyecto
El código está organizado de manera modular, separando la lógica de las estructuras de la interfaz, como se muestra a continuación:

```bash
gestion-gimnasio-avanzada/
├── src/
│ ├── Main.java                 # Punto de entrada principal
│ ├── GestionAvanzadaFrame.java # Clase principal de la Interfaz Gráfica (GUI)
│ ├── Estructuras/
│ │ ├── HashTableManager.java   # Lógica para Tablas Hash (Clases/Búsqueda)
│ │ ├── ArbolBinarioManager.java# Lógica para Árboles Binarios (Personal)
│ │ └── PriorityQueueManager.java# Lógica para Colas de Prioridad (Tareas/Urgencia)
│ ├── Modelos/
│ │ ├── Empleado.java           # Clase para representar la Entidad del Personal
│ │ ├── Clase.java              # Clase para representar la Entidad de la Clase Programada
│ │ └── TareaMantenimiento.java # Clase para representar la Entidad de la Tarea/Reparación
│ └── Algoritmos/
│ └── MergeSort.java          # Implementación del algoritmo Divide y Vencerás
└── README.md                 # Este archivo (Documentación del proyecto)

