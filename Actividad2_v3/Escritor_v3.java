package Actividad2_v3;

/* Clase del hilo escritor */
public class Escritor_v3 extends Thread {
	
	/* Atributos de la clase, la cola y la cantida que deben de escribir*/
	private Cola_v3 colaMensajes;
	private int cantidad;
	
	/* Constructor, asigna valor a los atributos */
	public Escritor_v3(String pnom, Cola_v3 pcol) {
		super(pnom);
		this.colaMensajes=pcol;
		this.cantidad=0;
	}
	
	
	/* proceso del hilo */
	public void run() {
		boolean valor=false;
		boolean escribe=true;
		
		/* Si le quedan mensajes por escribir, continua en el bucle */
		while(escribe) {
			
			/* Si ha podido escribir el mensaje, recibe un ok */
			valor=colaMensajes.putMensaje("Mensaje escrito por el "+getName());
			
			/* Si escribio en la cola, resta un mensaje a su cantidad y lo imprime por pantalla */
			if (valor) {
				cantidad++;
				System.out.println("El "+getName()+" ha añadido un mensaje a la cola.");
			}
			
			/* Espera un tiempo para volver a escribir */
			try {
				sleep((int)(Math.random()*100+100));
			}
			catch (InterruptedException e) {
				System.err.println("Error al añadir un nuevo mensaje");
			}
			
			escribe=colaMensajes.faltanMensajes();
		}
		
		/* Muestra cuando acaba el hilo */
		System.out.println("El "+getName()+ " ha finalizado su labor, en total ha escrito "+cantidad);
	}

}
