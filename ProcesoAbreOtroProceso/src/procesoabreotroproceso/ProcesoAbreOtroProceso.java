/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesoabreotroproceso;

import java.io.IOException;

/**
 *
 * @author 2dami
 */
public class ProcesoAbreOtroProceso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {      

        // abrir cmd para ejecutar un programa
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "java", "-cp", ".", "OtroPrograma");
        Process cmd = pb.start();

        cmd.waitFor();
    }
    
}

class OtroPrograma {
    public static void main(String[] args) throws IOException {

        //otra vez lo mismo para abrir el ultimo 
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "java", "-cp", ".", "UltimoPrograma");
        pb.start();
    }
}

class UltimoPrograma {
    public static void main(String[] args) throws IOException {

        //abre cmd y escribe
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "echo", "Hola");
        pb.start();
    }
}

    

