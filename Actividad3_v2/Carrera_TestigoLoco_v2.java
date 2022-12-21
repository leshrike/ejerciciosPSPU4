package Actividad3_v2;

/* Clase principal de la actividad3 */
public class Carrera_TestigoLoco_v2 {
	
	/* Declaramos las constantes necesarias */
	public static final int kNUM_CORREDORES=10;
	
	/* Método principal */
	public static void main(String[] args) throws InterruptedException {
				
		/* Inicialización de las variables necesarias en el proceso */
		Testigo_v2 miTestigo=null;
		Juez_v2 juez=null;
		Corredor_v2[] corredores=null;
		int tiempo=0;
	
		
		/* Creamos el testigo, memoria compartida */
		miTestigo=new Testigo_v2();

		
		/* Rwservamos memoria para corredores */
		corredores=new Corredor_v2[kNUM_CORREDORES];
		
				
		/* Creamos y ejecutamos los hilos solicitados */
		for(int i=0; i<kNUM_CORREDORES; i++) {
			corredores[i]=new Corredor_v2("Corredor"+(i+1), miTestigo);
			corredores[i].start();
		}
		System.out.println("Todos los corredores creados y esperando el testigo.");
		
		/* creamos y ejecutamos el juez */
		juez=new Juez_v2(miTestigo);
		juez.start();
		
		
		/* El proceso principal espera que todos los hilos hayan acabado*/
		for(int i=0; i<kNUM_CORREDORES; i++)
			corredores[i].join();
		
		juez.join();
			
		
		/* Fin del proceso */
		System.out.println("El juez indica que la carrera ha finalizado.");
		System.out.println("El orden de llegada es: "+miTestigo.getGanador());
		
		tiempo=miTestigo.getTiempo()/1000;
		System.out.println("Se ha utilizado un total de "+tiempo+" segundos");
	}
}
