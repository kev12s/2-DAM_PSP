/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dami
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Socket cliente;
        ObjectOutputStream salida;
        ObjectInputStream entrada;
        
        try {
            cliente = new Socket("localhost", 50030);
            System.out.println("conexion realizada");
            
            Persona p = new Persona("Juan", 20, "123");
            salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.writeObject(p);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
