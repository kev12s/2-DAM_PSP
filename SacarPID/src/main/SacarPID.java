package main;
import java.io.*;
import java.util.*;

public class SacarPID {


	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("=== PROCESOS EN EJECUCIÓN ===");
		System.out.println("PID\t\tNombre");
		System.out.println("---\t\t------");

		Map<Integer, String> procesos = obtenerProcesosConPID();

		for (Map.Entry<Integer, String> entry : procesos.entrySet()) {
			System.out.printf("%-10d\t%s%n", entry.getKey(), entry.getValue());
		}

		System.out.println("\nTotal procesos: " + procesos.size());
	}
	

	 public static Map<Integer, String> obtenerProcesosConPID() 
	            throws IOException, InterruptedException {
	        
	        Map<Integer, String> procesos = new TreeMap<>();
	        
	        ProcessBuilder pb = new ProcessBuilder("tasklist");
	        Process p = pb.start();
	        
	        try (BufferedReader reader = new BufferedReader(
	                new InputStreamReader(p.getInputStream()))) {
	            
	            String linea;
	            boolean primeraLinea = true;
	            
	            while ((linea = reader.readLine()) != null) {
	                if (primeraLinea || linea.trim().isEmpty()) {
	                    primeraLinea = false;
	                    continue;
	                }
	                
	                // Parsear línea: nombre.exe   PID   sesión   memoria
	                // Ej: "java.exe                   12345 Console                    1     45,000 K"
	                String[] partes = linea.split("\\s+");
	                
	                if (partes.length >= 2) {
	                    try {
	                        String nombre = partes[0];
	                        int pid = Integer.parseInt(partes[1]);
	                        procesos.put(pid, nombre);
	                    } catch (NumberFormatException e) {
	                        // Ignorar líneas sin PID válido
	                    }
	                }
	            }
	        }
	        
	        p.waitFor();
	        return procesos;
	    }


}
