package eus.tartanga.psp.AlumnosWeb.app;


import java.util.List;
import java.util.Scanner;

import eus.tartanga.psp.AlumnosWebClient.client.AlumnoClient;
import eus.tartanga.psp.AlumnosWebClient.model.Alumno;

public class Main {
    private final AlumnoClient cliente;
    private final Scanner scanner;
    
    public Main() {
        this.cliente = new AlumnoClient("http://localhost:8080");
        this.scanner = new Scanner(System.in);
    }
    
    public void ejecutar() {
        System.out.println("=== CLIENTE ALUMNOS WEBFLUX ===");
        
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            
            switch (opcion) {
            case 1:
                verTodosAlumnos();
                break;
            case 2:
                buscarPorId();
                break;
            case 3:
                buscarPorCurso();
                break;
            case 4:
                contarAlumnos();
                break;
            case 5:
                crearAlumno();
                break;
            case 6:
                actualizarAlumno();
                break;
            case 7:
                eliminarAlumno();
                break;
                case 0:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        
        scanner.close();
    }
    
    private void mostrarMenu() {
        System.out.println("\n1. Ver todos los alumnos");
        System.out.println("2. Buscar alumno por ID");
        System.out.println("3. Buscar alumnos por curso");
        System.out.println("4. Contar alumnos totales");
        System.out.println("5. Crear nuevo alumno");
        System.out.println("6. Actualizar alumno");
        System.out.println("7. Eliminar alumno");
        System.out.println("0. Salir");
        System.out.print("Seleccione opcion: ");
    }
    
    private int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void verTodosAlumnos() {
        System.out.println();
        List<Alumno> alumnos = cliente.getAllAlumnos();
        
        if (alumnos.isEmpty()) {
            System.out.println("No se encontraron alumnos");
        } else {
        	for (Alumno alumno : alumnos) {
        	    System.out.println(alumno);
        	}
        }
    }
    
    private void buscarPorId() {
        System.out.print("\nID del alumno: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Alumno alumno = cliente.getAlumnoById(id);
            
            if (alumno != null) {
                System.out.println("Encontrado: " + alumno);
            } else {
                System.out.println("No se encontro alumno con ID " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no valido");
        }
    }
    
    private void buscarPorCurso() {
        System.out.print("\nNombre del curso: ");
        String curso = scanner.nextLine();
        
        List<Alumno> alumnos = cliente.getAlumnosByCurso(curso);
        
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos en el curso '" + curso + "'");
        } else {
            alumnos.forEach(System.out::println);
            System.out.println("Total: " + alumnos.size() + " alumno(s)");
        }
    }
    
    private void contarAlumnos() {
        System.out.println();
        Integer total = cliente.getTotalAlumnos();
        
        if (total != null) {
            System.out.println("Total de alumnos: " + total);
        } else {
            System.out.println("No se pudo obtener el conteo");
        }
    }
    
    private void crearAlumno() {
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        
        Alumno nuevoAlumno = new Alumno(nombre, curso);
        boolean creado = cliente.createAlumno(nuevoAlumno);
        
        if (creado) {
            System.out.println("Alumno creado");
        } else {
            System.out.println("Error al crear alumno");
        }
    }
    
    
    private void actualizarAlumno() {
        System.out.println();
        
        System.out.print("ID del alumno a actualizar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            
            Alumno existente = cliente.getAlumnoById(id);
            if (existente == null) {
                System.out.println("No existe alumno con ID " + id);
                return;
            }
            
            System.out.println("Datos actuales: " + existente);
            System.out.println();
            
            System.out.print("Nombre (" + existente.getNombre() + "): ");
            String nombre = scanner.nextLine();
            if (nombre.isEmpty()) nombre = existente.getNombre();
            
            System.out.print("Curso (" + existente.getCurso() + "): ");
            String curso = scanner.nextLine();
            if (curso.isEmpty()) curso = existente.getCurso();
            
            Alumno actualizado = new Alumno(id, nombre, curso);
            Alumno resultado = cliente.updateAlumno(id, actualizado);
            
            if (resultado != null) {
                System.out.println("Alumno actualizado: " + resultado);
            } else {
                System.out.println("Error al actualizar");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no valido");
        }
    }
    
    private void eliminarAlumno() {
        System.out.println();
        
        System.out.print("ID del alumno a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("¿Seguro? (s/n): ");
            String confirmacion = scanner.nextLine();
            
            if (confirmacion.equalsIgnoreCase("s")) {
                boolean eliminado = cliente.deleteAlumno(id);
                if (eliminado) {
                    System.out.println("Alumno eliminado");
                } else {
                    System.out.println("No se pudo eliminar");
                }
            } else {
                System.out.println("Operacion cancelada");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no valido");
        }
    }
    
    
    public static void main(String[] args) {
        Main aplicacion = new Main();
        aplicacion.ejecutar();
    }
}
