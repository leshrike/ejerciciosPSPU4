package Actividad2;


/* Calse principal de la actividad*/
public class Cola_de_mensajes {
	
	/* Declaramos las constantes necesarias */
	public static final int kTAM_COLA=20;
	public static final int kMAX_MENSAJES=25;
	public static final int kNUM_LECTORES=3;
	public static final int kNUM_ESCRITORES=5;
	
	
	/* Método principal */
	public static void main(String[] args) throws InterruptedException {
				
		/* Inicialización de las variables necesarias en el proceso */
		Cola colaMensajes=null;
		Escritor[] escritores=null;
		Lector[] lectores=null;
		int numMensajesEsc=0, restoEsc=0, total=0;
		int numMensajesLec=0, restoLec=0;
	
		
		/* Creamos la cloa de mensajes, memoria compartida */
		colaMensajes=new Cola();
		
		/* REservamos memoria para lectores y escritores */
		escritores=new Escritor[kNUM_ESCRITORES];
		lectores=new Lector[kNUM_LECTORES];
		
		/*Creamos los escritores, y le damos a cada uno de ellos trabajo equitativo, es decir dividimos
		 * los mensajes a escribir entre los escritores y dos excriben igual +/- uno */
		numMensajesEsc=kMAX_MENSAJES/kNUM_ESCRITORES;
		restoEsc=kMAX_MENSAJES%kNUM_ESCRITORES;
		
		
		/* Creamos y ejecutamos los hilos solicitados */
		for(int i=0; i<kNUM_ESCRITORES; i++) {
			
			total=numMensajesEsc;
			
			/* Si hay algun mensaje de más en la división se los añado a los primeros escritores */
			if(restoEsc>0) {
				total=numMensajesEsc+1;
				restoEsc--;
			}
			
			/* Creamos y ejecutamos escritores */
			escritores[i]=new Escritor("Escritor"+(i+1), colaMensajes, total);
			escritores[i].start();
		}
		
		/* Hacvemos lo mismo con los lectores, les repartimos equitativamente los mensajes a leer */
		numMensajesLec=kMAX_MENSAJES/kNUM_LECTORES;
		restoLec=kMAX_MENSAJES%kNUM_LECTORES;
		
		for(int i=0; i<kNUM_LECTORES; i++) { 
			total=numMensajesLec;

			if(restoLec>0) {
				total=numMensajesLec+1;
				restoLec--;
			}
			
			/* Creo y ejecuto los lectores */
			lectores[i]=new Lector("Lector"+(i+1), colaMensajes, total);
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

