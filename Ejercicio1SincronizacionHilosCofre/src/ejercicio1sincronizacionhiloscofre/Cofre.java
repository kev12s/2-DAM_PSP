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
public class Cofre {

    int monedas;

    public Cofre() {
        this.monedas = 0;
    }

    public Cofre(int monedas) {
        this.monedas = monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public int getMonedas() {
        return monedas;
    }

    public synchronized boolean recogerMoneda() {
        boolean hay;
        if (monedas > 0) {
            monedas--;
            hay = true;
        }else{
            hay = false;
        }
        return hay;
    }
    
    public boolean hayMonedas(){
       return monedas>0;
    }

}
