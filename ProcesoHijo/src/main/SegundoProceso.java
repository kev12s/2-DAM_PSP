package main;

import java.io.IOException;

public class SegundoProceso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ProcessBuilder pb = new ProcessBuilder("java",
				    "-cp",
				    "bin",
				    "main.TercerProceso");

			pb.inheritIO();
			Process process;

			process = pb.start();


			process.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Segundo programa terminado");
	}

}
