/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1sincronizacionhiloscofre;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dami
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cofre cofre = new Cofre(10);

        Jugador j1 = new Jugador("Juan", cofre, 0);
        Jugador j2 = new Jugador("Marcos", cofre, 0);
        Jugador j3 = new Jugador("Pedro", cofre, 0);

        j1.start();
        j2.start();
        j3.start();

        try {
            j1.join();
            j2.join();
            j3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("El jugador " + j1.getNombre() + " tiene " + j1.getMonedasRecogidas());
        System.out.println("El jugador " + j2.getNombre() + " tiene " + j2.getMonedasRecogidas());
        System.out.println("El jugador " + j3.getNombre() + " tiene " + j3.getMonedasRecogidas());

    }

}
