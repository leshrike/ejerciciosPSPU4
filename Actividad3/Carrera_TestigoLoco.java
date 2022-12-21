package Actividad3;

/* Clase principal de la actividad3 */
public class Carrera_TestigoLoco {
	
	/* Declaramos las constantes necesarias */
	public static final int kNUM_CORREDORES=10;
	
	/* Método principal */
	public static void main(String[] args) throws InterruptedException {
				
		/* Inicialización de las variables necesarias en el proceso */
		Testigo miTestigo=null;
		Juez juez=null;
		Corredor[] corredores=null;
	
		
		/* Creamos el testigo, memoria compartida */
		miTestigo=new Testigo();
		
		
		/* creamos y ejecutamos el juez */
		juez=new Juez(miTestigo);
		juez.start();
		
		
		/* Rwservamos memoria para corredores */
		corredores=new Corredor[kNUM_CORREDORES];
		
				
		/* Creamos y ejecutamos los hilos solicitados */
		for(int i=0; i<kNUM_CORREDORES; i++) {
			corredores[i]=new Corredor("Corredor"+(i+1), miTestigo);
			corredores[i].start();
		}
		
		
		/* El proceso principal espera que todos los hilos hayan acabado*/
		for(int i=0; i<kNUM_CORREDORES; i++)
			corredores[i].join();
		
		juez.join();
			
		
		/* Fin del proceso */
		System.out.println("Finalizo la carrera, el ganador es el "+miTestigo.getGanador());
	}
}
