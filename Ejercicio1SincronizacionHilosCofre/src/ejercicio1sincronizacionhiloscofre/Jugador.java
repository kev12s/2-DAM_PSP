/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1sincronizacionhiloscofre;

/**
 *
 * @author 2dami
 */
public class Jugador extends Thread {

    private String nombre;
    private Cofre cofre;
    private int monedasRecogidas;

    public Jugador() {
        this.nombre = "";
        this.cofre = null;
        this.monedasRecogidas = 0;
    }

    public Jugador(String nombre, Cofre cofre, int monedas) {
        this.nombre = nombre;
        this.cofre = cofre;
        this.monedasRecogidas = monedas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMonedasRecogidas() {
        return monedasRecogidas;
    }
    
    

    @Override
    public void run() {
        while (cofre.hayMonedas()) {
            System.out.println("Jugador" + nombre + "inicio abrio el cofre");
            if (cofre.recogerMoneda()) {
                long startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 500_000_000) {
                    double x = Math.sqrt(Math.random());
                }
                monedasRecogidas++;
                System.out.println("Jugador" + nombre + "recogio la moneda");
            }
        }
    }

}
