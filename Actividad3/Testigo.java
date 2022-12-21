package Actividad3;

/* Clase testiggo, con métodos sincronizados, gestiona la memoria compartida */
public class Testigo {

	/* Atributos de la clase */
	private String ganador;					//Guardamos el nombre del primer hilo en correr
	private boolean testLibreJuez;			//El testigo esta en poder del juez y puede lanzarlo
	private boolean testLibreCorre;			//El testigo esta en disponible para el corredor
	
	
	/* Constructor, inicializamos atributos, el ganador a cadena vacia, indicamos que el testigo
	 * puede ser lanzado por el juez y significa que no esta disponible para los corredores */
	public Testigo() {
		this.ganador="";
		this.testLibreJuez=true;
		this.testLibreCorre=false;
	}
	
	
	/* Devuelve el ganador, primer corredor en coger el testigo*/
	public String getGanador( ) {
		return ganador;
	}
	
	
	/* Método que utiliza el juez para lanzar el testigo y puedan cogerlo los corredores*/
	public synchronized void lanzarTestigo() {
		
		/* Si el testigo esta siendo utilizado por un corredor, no esta disponible, esperamos a que lo este */
		while(!testLibreJuez){
			try {
                wait();
            } 
			catch (InterruptedException e) {
                System.err.println("Error al obtener el mensaje.");
            }
		}
		
		/* Como solo tenemos un juez, en caso de llegar a aquí, tiene asegurado poder lanzar el 
		 * testigo, Espera un tiempo para volver a lanzarlo */
		try {
			Thread.sleep((int)(Math.random()*10+100));
		}
		catch (InterruptedException e) {
			System.err.println("Error al lanzar el testigo");
		}
		
		/* Una vez lanzado el testigo, el juez ya no dispone de él, y son los corredores quienes pueden
		 * cogerlo */
		testLibreJuez=false;
		testLibreCorre=true;
		
		/* Despierto a todos los hilos que esten en espera */
		notifyAll();
	}
	
	
	/* Este método, es el de los corredores, que intentarán coger el testigo y correr durante un pequeño
	 * periodo de tiempo */
	public synchronized int cogerTestigo(String pnom) {
		int tiempo=0;
		
		/* Si el testigo no esta disponible porque lo tenga el juez u otro corredor, esperamos */
		while(!testLibreCorre){
			try {
                wait();
            } 
			catch (InterruptedException e) {
                System.err.println("Error al obtener el mensaje.");
            }
		}
		
		
		/* Al despertar a todos los corredores, el primero que entre coge el testigo, el resto deberá volver a
		 * intentarlo más adelante */
		if(testLibreCorre) {
			
			/* Corro durante un ratito */
			try {
				tiempo=(int)(Math.random()*1000+1000);
				Thread.sleep(tiempo);
			}
			catch (InterruptedException e) {
				System.err.println("Error al intentar obtener un mensaje");
			}
			
			/* Si eres el primer corredor, me quedo con tu nombre*/
			if(ganador=="")
				ganador=pnom;
			
			
			/* Le indicamos al juez que el testigo esta a su disposición y al resto de corredores que no esta disponible */
			testLibreJuez=true;
			testLibreCorre=false;
			

			/* Cuando a leido todos los mensajes asignados, acaba*/
			System.out.println("El "+pnom+ " ha corrido durante "+ tiempo+"ms, y ya ha finalizado de correr");
		}
		
		/* Despierto a todo el mundo */
		notifyAll();
		
		
		/* Si el tiempo es distinto de 0, significa que ha corrido, en otro caso, debe volver a intentarlo */
		return tiempo;
	}
	
}
