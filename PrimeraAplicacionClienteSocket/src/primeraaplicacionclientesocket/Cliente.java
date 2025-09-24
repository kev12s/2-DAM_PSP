/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeraaplicacionclientesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author 2dami
 */
public class Cliente {

    //no hace falta hacer un metodo pero para verlo mas claro
    private static void iniciar(){
        //Se crea el socket del cliente
        Socket cli;
        ObjectInputStream entrada;
        
        try{
            //se le pone IP o nombre y Puerto al que se va a conectar, en este
            //caso nombre LocalHost y puerto 5000
            cli = new Socket("LocalHost", 5000);
            
            entrada = new ObjectInputStream(cli.getInputStream());
            
            Object mensaje = entrada.readObject();
            
            System.out.println(mensaje);
                    
            
            
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //se inicializa el metodo
        iniciar();
    }
    
}
