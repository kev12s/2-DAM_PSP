/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrireclipseprocessbuilder;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author 2dami
 */
public class AbrirEclipseProcessBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Ejecutar Eclipse desde el escritorio
            ProcessBuilder builder = new ProcessBuilder("C:\\Users\\2dami\\eclipse\\java-2024-06\\eclipse\\eclipse.exe");
            
            System.out.println("Abriendo Eclipse desde el escritorio...");
            Process process = builder.start();
            
        } catch (IOException e) {
            System.out.println("Error: No se pudo encontrar eclipse.exe");
            e.printStackTrace();
        }
    }
    
    
}
