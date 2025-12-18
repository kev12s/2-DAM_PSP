/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processbuilderredirigirsalida;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dami
 */
public class ProcessBuilderRedirigirSalida {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProcessBuilder builder = new ProcessBuilder("tasklist");
        builder.redirectOutput(new File("salida.txt"));
        builder.redirectError(new File("errores.txt"));
        
        try {
            Process process = builder.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
