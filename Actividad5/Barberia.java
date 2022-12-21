package Actividad5;

import java.util.ArrayList;
import java.util.List;

/* Creacion de la clase que hara que clientes y barbero trabajen */
public class Barberia {
    
	/* Atributos:*/
    private int contadorClientes;					//ContadorClientes -> los clientes a atender.
    private int contadorAtendidos;					//ContadorAtendidos -> clientes atendidos.
    private int contadorNoAtendidos;				//ContadorNoAtendidos -> clientes no atendidos
    private List<String> clientes;					//Clientes -> Lista con los clientes.


    /* Constructor, inicializa los atributos*/
    public Barberia(int sillas, int contadorClientes) {
        
    	this.contadorClientes = contadorClientes;
    
        /*Inicializo algunos valores a 0.*/
        this.clientes = new ArrayList<>();
        this.contadorAtendidos = 0;
        this.contadorNoAtendidos = 0;
    }


    /* Se añade un cliente a las sillas, si están todas ocupadas, se va.  */
    public synchronized void anyadirNuevoCliente(String pnom) {
    	
        /*En caso de que no queden sillas, el cliente no se añade a la lista, sumo 1 a los no atendidos y aviso al barbero.
        * Hago, return para parar y que ese cliente ya no haga nada más.*/
        if (!quedanSillas()){
            System.out.println("No quedan sillas. " + pnom  + " se va de la barbería.");
            contadorNoAtendidos++;
            notify();
        }
        else {
        	/*Si hay sillas lo añado.*/
            this.clientes.add(pnom);
            System.out.println("El cliente " + pnom + " tiene sito en las sillas.");

            /*Sumo 1 a los clientes atendidos.
            * Aviso al barbero.*/
            this.contadorAtendidos++;
            notify();	
        }
    }


    /* Función para que el barbero atienda al cliente que está en espera.  */
    public synchronized void atenderAlCliente() {

    	String cliente = "";
    	int segundosQueTarda = 0;

        /*Mientras que las sillas estén vacías y no sea porque se ha terminado el servicio, el barbero duerme (espera).*/
        while (this.clientes.size() == 0 && !terminarServicio()){
            try {
                System.out.println("El barbero duerme...");
                wait();
            } 
            catch (InterruptedException e) {
                System.err.println("Error al hacer que el barbero espere.");
            }
        }

        /*Si hay clientes, el barbero atiende a uno, y lo elimino de la lista de sillas.*/
        cliente = clientes.get(0);
        System.out.println("Atendiendo al cliente " + cliente);
        this.clientes.remove(cliente);

        /*El barbero atiende a un cliente, tardando un número aleatorio de segundos.*/
        try {
            Thread.sleep(segundosQueTarda=(int)(Math.random()*2000+1000));
        } 
        catch (InterruptedException e) {
            System.err.println("Error al crear un cliente.");
        }

        /*Sumo un número aleatorio de ganancias al total.*/

        System.out.println("Se ha atendido al cliente " + cliente + " en " + segundosQueTarda + " segundos. ");

        notify();
    }


    /* Método para comprobar si quedan sillas libres.     */
    public boolean quedanSillas() {
    	boolean valor=true;
    	
        if (this.clientes.size() == ProbarBarberia.NUMERO_DE_SILLAS) {
            valor=false;
        }
        
        return valor;
    }

    /* Método para comprobar cuando se termina el servicio.  */
    public boolean terminarServicio() {
    	boolean valor=false;
    	
    	/*Condición: para terminar no tiene que haber clientes en espera y compruebo también si ya han pasado todos los clientes.*/
        if (this.clientes.size() == 0 && this.contadorClientes == (this.contadorAtendidos + this.contadorNoAtendidos)){
            valor=true;
        }
        return valor;
    }

    /* Suma +1 a los no atendidos.      */
    public void sumarANoAtendidos(){
        this.contadorNoAtendidos++;
    }

    /* Getter de los clientes atendidos.  */
    public int getContadorAtendidos() {
        return contadorAtendidos;
    }

    /* Getter de los clientes no atendidos. */
    public int getContadorNoAtendidos() {
        return contadorNoAtendidos;
    }
}
