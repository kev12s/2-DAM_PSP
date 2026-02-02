package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		System.out.println("Escribe el nombre a buscar: ");
		String nombre = teclado.next();

		encontrarProceso(nombre);
		redirigirFichero(nombre);
	}

	private static void encontrarProceso(String nombreProceso) {	


		try {
			ProcessBuilder pb = new ProcessBuilder("tasklist");

			Process process = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedWriter writer = new BufferedWriter(new FileWriter("filtrado.txt"));

			String linea; 

			while((linea = reader.readLine())!=null) {

				if(linea.toLowerCase().contains(nombreProceso.toLowerCase())) {
					writer.write(linea + "\n"); //o writer.newLine(); o writer.println(linea);
				}
			}

			process.waitFor();

			reader.close();
			writer.close();

			System.out.println("Terminado primero");

		}catch(Exception e) {
			e.getStackTrace();
		}


		/*try {
			ProcessBuilder pb = new ProcessBuilder("tasklist");

			Process proceso = pb.start(); //Guardar el proceso para hacer cosas con él

			BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream())); //Crear input y leer input del proceso almacenado

			String linea;

			File archivoSalida = new File("archivoFiltrado.txt");

			while((linea =reader.readLine()) != null ) { //IMPORTANTE! meter reader aqui para actualizarlo en cada bucle
				if(linea.contains(nombreProceso))
				pb.redirectOutput(archivoSalida);
			}

			proceso.waitFor();

			System.out.println("Terminado");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	private static void redirigirFichero(String nombre) {		

		try {
			ProcessBuilder pb = new ProcessBuilder("tasklist");

			pb.redirectOutput(new File("redirigido.txt"));

			Process process = pb.start();

			process.waitFor();

			BufferedReader reader = new BufferedReader(new FileReader("redirigido.txt"));

			BufferedWriter writer = new BufferedWriter(new FileWriter("redirigidoFiltrado.txt"));

			String linea; 

			while((linea = reader.readLine())!= null) {
				if(linea.toLowerCase().contains(nombre.toLowerCase())) {
					writer.write(linea);
					writer.newLine();
				}
			}

			reader.close();
			writer.close();
			System.out.println("segundo terminado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
