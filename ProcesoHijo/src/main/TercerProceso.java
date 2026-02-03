package main;

import java.io.File;
import java.io.IOException;

public class TercerProceso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProcessBuilder builder = new ProcessBuilder("tasklist");
        builder.redirectOutput(new File("salida.txt"));
        builder.redirectError(new File("errores.txt"));
        
        try {
            Process process = builder.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		System.out.println("Hola");
	}

}
