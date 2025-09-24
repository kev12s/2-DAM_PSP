/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1hilos;

import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Utilidades;

/**
 *
 * @author 2dami
 */
public class Ejercicio1Hilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String imagen=null;
        String imagen2=null;

        System.out.println("Introduce la ruta de tu primera imagen:");
        imagen = Utilidades.introducirCadena();
        
        System.out.println("Introduce la ruta de tu segunda imagen:");
        imagen2 = Utilidades.introducirCadena();
        
        Hilo h1 = new Hilo(imagen);
        Hilo h2 = new Hilo(imagen2);
        
        h1.start();
        
        try {
            h1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ejercicio1Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Este archivo tiene " + h1.getContador() + "bytes");
        
        h2.start();

        try {
            h2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ejercicio1Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Este archivo tiene " + h2.getContador() + "bytes");
        
        
    }
    
}
