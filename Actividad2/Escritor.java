package Actividad2;

/* Clase del hilo escritor */
public class Escritor extends Thread {
	
	/* Atributos de la clase, la cola y la cantida que deben de escribir*/
	private Cola colaMensajes;
	private int cantidad;
	
	/* Constructor, asigna valor a los atributos */
	public Escritor(String pnom, Cola pcol, int pcant) {
		super(pnom);
		this.colaMensajes=pcol;
		this.cantidad=pcant;
	}
	
	
	/* proceso del hilo */
	public void run() {
		boolean valor=false;
		
		/* Si le quedan mensajes por escribir, continua en el bucle */
		while(cantidad>0) {
			
			/* Si ha podido escribir el mensaje, recibe un ok */
			valor=colaMensajes.putMensaje("Mensaje escrito por el "+getName());
			
			/* Si escribio en la cola, resta un mensaje a su cantidad y lo imprime por pantalla */
			if (valor) {
				cantidad--;
				System.out.println("El "+getName()+" ha añadido un mensaje a la cola.");
			}
			
			/* Espera un tiempo para volver a escribir */
			try {
				sleep((int)(Math.random()*100+100));
			}
			catch (InterruptedException e) {
				System.err.println("Error al añadir un nuevo mensaje");
			}
		}
		
		/* Muestra cuando acaba el hilo */
		System.out.println("El "+getName()+ " ha finalizado su labor");
	}

}
