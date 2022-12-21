package Actividad2_v3;


/* Calse principal de la actividad, no secontrola quien escribe y quien lee, lo hace el primero que llegue,
 * aunque eso signifique que un escritor añade todos los mensajes y un lector los lee todos*/
public class Cola_de_mensajes_v3 {
	
	/* Declaramos las constantes necesarias */
	public static final int kTAM_COLA=20;
	public static final int kMAX_MENSAJES=25;
	public static final int kNUM_LECTORES=3;
	public static final int kNUM_ESCRITORES=5;
	
	
	/* Método principal */
	public static void main(String[] args) throws InterruptedException {
				
		/* Inicialización de las variables necesarias en el proceso */
		Cola_v3 colaMensajes=null;
		Escritor_v3[] escritores=null;
		Lector_v3[] lectores=null;
	
		
		/* Creamos la cloa de mensajes, memoria compartida */
		colaMensajes=new Cola_v3();
		
		/* REservamos memoria para lectores y escritores */
		escritores=new Escritor_v3[kNUM_ESCRITORES];
		lectores=new Lector_v3[kNUM_LECTORES];
		
		
		
		/* Creamos y ejecutamos los hilos solicitados */
		for(int i=0; i<kNUM_ESCRITORES; i++) {
			/* Creamos y ejecutamos escritores */
			escritores[i]=new Escritor_v3("Escritor"+(i+1), colaMensajes);
			escritores[i].start();
		}
		

		for(int i=0; i<kNUM_LECTORES; i++) { 
			/* Creo y ejecuto los lectores */
			lectores[i]=new Lector_v3("Lector"+(i+1), colaMensajes);
			lectores[i].start();
		}
		
		
		/* El proceso principal espera que los escritores y los lectores hayan acabado de escribir o leer*/
		for(int i=0; i<kNUM_ESCRITORES; i++)
			escritores[i].join();
		
		for(int i=0; i<kNUM_LECTORES; i++)
			lectores[i].join();

			
		/* Fin del proceso */
		System.out.println("Ya no hay nada que leer, cerramos la Cola de mensajes");
	}
}

