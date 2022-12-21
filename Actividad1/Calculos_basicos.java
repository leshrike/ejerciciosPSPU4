package Actividad1;


/* Clase principal que crea una cantidad de hilos determinada por el argumento recibido en primer lugar y
 * que añadiran a una cuenta (con un valor inicial aleatorio) un valor fijo recibido como segundo
 * argumento */
public class Calculos_basicos {

	
	/* Método principal */
	public static void main(String[] args) throws InterruptedException {
		
		
		/* Inicialización de las variables necesarias en el proceso */
		Calcular cuenta=null;
		Hilo[] hilos=null;
		int numHilos=0;
		int cantidad=0;
		int inicial;
		
		
		/* Controlamos que se incluyen los dos argumentos necesarios */ 
		if(args.length<2) {
			System.err.println("Error en la entrada de datos");
		}
		
		/* Si están los dos argumentos */
		else {
			
			inicial=(int)(Math.random()*100+100);		//Saldo inicial en la cuenta
			cuenta=new Calcular(inicial);				//Objeto cuenta con un saldo inicial
			numHilos=Integer.parseInt(args[0]);			//Cantidad de hilos a ingresar 
			cantidad=Integer.parseInt(args[1]);			//Cantidad que ingresara cada hilo
			hilos=new Hilo[numHilos];					//instancia de cada hilo creado
			
			System.out.println("Vamos a crear "+numHilos+" hilos que ingresaran "+cantidad +
					" euros cada uno en la cuenta que inicialmente tiene "+inicial+" euros.");
			
			/* Creamos y ejecutamos los hilos solicitados */
			for(int i=0; i<numHilos; i++) {
				hilos[i]=new Hilo("Hilo"+(i+1), cantidad, cuenta);
				hilos[i].start();
			}
			
			/* vamos a esperar a que todo el mundo acabe para finalizar este proceso principal */
			for(int i=0; i<numHilos; i++) {
				hilos[i].join();
			}
				
			/* Información final del proceso principal */
			System.out.println("\nIngresos acabados. Saldo final: "+cuenta.getTotalSuma()+" Euros y un promedio de: "+cuenta.getPromedio()+" Euros.");
		}
	}
}
