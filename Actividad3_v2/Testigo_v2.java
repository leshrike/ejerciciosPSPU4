package Actividad3_v2;

import java.util.ArrayList;
import java.util.List;

/* Clase testiggo, con métodos sincronizados, gestiona la memoria compartida */
public class Testigo_v2 {

	/* Atributos de la clase */
	private List<String> ganador;			//Guardamos el orden en que se coge el testigo
	private boolean testLibreCorre;			//El testigo esta en disponible para el corredor
	private int tiempoTotal;
	
	
	/* Constructor, inicializamos atributos, el ganador a cadena vacia, indicamos que el testigo
	 * puede ser lanzado por el juez y significa que no esta disponible para los corredores */
	public Testigo_v2() {
		this.ganador=new ArrayList<String>();
		this.testLibreCorre=false;
		this.tiempoTotal=0;
	}
	
	
	/* Devuelve el ganador, primer corredor en coger el testigo*/
	public List<String> getGanador( ) {
		return ganador;
	}
	
	
	/* Método que devuelve el tiempo total de la carrera, sin el tiempo de espera del juez*/
	public int getTiempo() {
		return tiempoTotal;
	}
	
	
	/* Método que utiliza el juez para lanzar el testigo y puedan cogerlo los corredores*/
	public synchronized void lanzarTestigo(int tiempo) {
		
		/* Inicializamos el tiempo con la espera del juez*/
		tiempoTotal=tiempo;
		
		/* Una vez lanzado el testigo, el juez ya no dispone de él, y son los corredores quienes pueden
		 * cogerlo */
		testLibreCorre=true;
		
		/* Despierto a todos los hilos que esten en espera */
		notifyAll();
	}
	
	
	/* Este método, es el de los corredores, que intentarán coger el testigo y correr durante un pequeño
	 * periodo de tiempo */
	public synchronized int cogerTestigo(String pnom) {
		int tiempo=0;
		
		/* Si el testigo no esta disponible porque lo tenga otro corredor, esperamos */
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
				tiempoTotal+=tiempo;
				Thread.sleep(tiempo);
			}
			catch (InterruptedException e) {
				System.err.println("Error al intentar obtener un mensaje");
			}
			
			/* Guardamos el nombre dle corredor que cogio el testigo*/
			ganador.add(pnom);
			
			
			/* Le indicamos al juez que el testigo esta a su disposición y al resto de corredores que no esta disponible */
			testLibreCorre=true;
			

			/* Cuando a leido todos los mensajes asignados, acaba*/
			System.out.println(pnom+ " ha corrido durante "+ tiempo+"ms, y lanza el testigo al aire");
		}
		else
			testLibreCorre=false;
		
		/* Despierto a todo el mundo */
		notifyAll();
		
		
		/* Si el tiempo es distinto de 0, significa que ha corrido, en otro caso, debe volver a intentarlo */
		return tiempo;
	}
	
}
