/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primera.aplicacion.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dami
 */
public class Servidor {

    
    private static void iniciar(){
        //Se crea el socket del servidor
        ServerSocket miServicio;     
        Socket clienteSocket; 
        
        ObjectOutputStream salida;
        
        try {
            //
            miServicio = new ServerSocket (5000);
            clienteSocket = miServicio.accept();
            
            salida = new ObjectOutputStream(clienteSocket.getOutputStream());
            salida.writeObject("Hola");
            
            salida.close();
            
            clienteSocket.close();
            miServicio.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        iniciar();
    }
    
}
