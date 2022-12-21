package Actividad4;


/* Clase cliente */
public class Cliente extends Thread{
	
	/* atributos de la clase*/
	private Cuenta cuenta;
    private int cantidad;

    
    /* Constructor del cliente, con los atributos necesarios*/
    public Cliente(Cuenta cuenta, int cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }


    /*Proceso principal del hilo */
    public void run() {
    	
        /* Forzamos la maquinaria: repetimos la operación muchísimas veces para intentar verificar 
         * si la simulación es correcta */
        for (int i=0; i<100; i++){
            cuenta.hacerMovimiento(cantidad);
           
            try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}




