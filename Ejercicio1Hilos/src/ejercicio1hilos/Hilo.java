/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1hilos;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author 2dami
 */
public class Hilo extends Thread {
    private String nombreArchivo;
    private int contador;

     
    public Hilo (String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }

    public int getContador() {
        return contador;
    }

       
    @Override
    public void run() {
       contador= procesarArchivo(nombreArchivo, contador);
    }

    public static int procesarArchivo(String nombreArchivo, int contador) {
        try (FileInputStream fis = new FileInputStream(nombreArchivo)) {
            int byteLeido;
            contador = 0;
            System.out.println(nombreArchivo + " abierto");
            // Lee el archivo byte a byte
            while ((byteLeido = fis.read()) != -1) {
                contador++;
                
                
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
        return contador;

    }

}
