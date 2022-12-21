package Actividad5;

public class Barbero extends Thread {

	/* Atributo con la barberia */
	private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    
    /* Metodo del proceso del barbero donde tiene un bucle que mientras no termina su servicio,
     * corete el pelo al cliente sentado en una silla */
    public synchronized void run() {

    	/*Trabaja mientras no le diga que no se acabe el servicio.*/
        while (!barberia.terminarServicio()) {
            barberia.atenderAlCliente();
        }

        /*Informo cuando acaba.*/
        System.out.println("No quedan más clientes.");
    }
}
