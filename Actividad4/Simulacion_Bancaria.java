package Actividad4;

/* Clase principal del ejercicio */
public class Simulacion_Bancaria {

	/* Constantes necesarias para el proceso, tenemos la cantidad de hilos que
	 * ingresaran cantidades diferentes	 */
    private static final int kNUM_OPS_CON_100 = 40;
    private static final int kNUM_OPS_CON_50  = 20;
    private static final int kNUM_OPS_CON_20  = 60;

    
    /* Proceso principal */
	public static void main(String[] args) throws InterruptedException{
		
		Cuenta cuenta = new Cuenta(100);
	    
		Cliente[] ingresa100 = new Cliente[kNUM_OPS_CON_100];
		Cliente[] retira100 = new Cliente[kNUM_OPS_CON_100];
		Cliente[] ingresa50 = new Cliente[kNUM_OPS_CON_50];
		Cliente[] retira50 = new Cliente[kNUM_OPS_CON_50];
		Cliente[] ingresa20 = new Cliente[kNUM_OPS_CON_20];
		Cliente[] retira20 = new Cliente[kNUM_OPS_CON_20];

		
	    /* Arrancamos todos los hilos*/
	    for (int i=0; i<kNUM_OPS_CON_100;i++){
	    	ingresa100[i] = new Cliente(cuenta, 100);
	        retira100[i]  = new Cliente(cuenta, -100);

	        ingresa100[i].start();
	        retira100[i].start();
	    }

	    for (int i=0; i<kNUM_OPS_CON_50;i++){
	    	ingresa50[i] = new Cliente(cuenta, 50);
	        retira50[i]  = new Cliente(cuenta, -50);

	        ingresa50[i].start();
	        retira50[i].start();
	    }

	    for (int i=0; i<kNUM_OPS_CON_20;i++){
	    	ingresa20[i] = new Cliente(cuenta, 20);
	        retira20[i]  = new Cliente(cuenta, -20);

	        ingresa20[i].start();
	        retira20[i].start();
	    }

	    
	    /* En este punto todos los hilos están arrancados,  ahora toca esperarlos */
        for (int i=0; i<kNUM_OPS_CON_100;i++){
        	ingresa100[i].join();
        	retira100[i].join();
        }

	    for (int i=0; i<kNUM_OPS_CON_50;i++){
	    	ingresa50[i].join();
	    	retira50[i].join();
	    }

	    for (int i=0; i<kNUM_OPS_CON_20;i++){
	    	ingresa20[i].join();
	    	retira20[i].join();
	    }
	        
	        
	    if (cuenta.esSimulacionCorrecta())
	    	System.out.println("La simulación fue correcta");
        else {
        	System.out.println("La simulación falló ");
        	System.out.println("La cuenta tiene:"+ cuenta.getSaldo());
        	System.out.println("Revise sus synchronized");
        }
	}
}