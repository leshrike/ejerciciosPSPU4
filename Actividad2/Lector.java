package Actividad2;

/* Clase del Hilo lector */
public class Lector extends Thread {
	
	/* Atributos de la clase */
	private Cola colaMensajes;
	private int cantidad;
	
	/* Constructor que asigna valor a los atributos */
	public Lector(String pnom, Cola pcol, int pcant) {
		super(pnom);
		this.colaMensajes=pcol;
		this.cantidad=pcant;
	}
	
	
	/* Proceso principal del hilo Lector */
	public void run() {
		String valor="";
		
		/* Mientras tenga mensajes que leer, seguira en el bucle */
		while(cantidad>0) {
			
			/* Intenta leer y recibe un ok */
			valor=colaMensajes.getMensaje();
			
			/* Si la lectura se lleva a cabo, restamos una lectura a la cantidad acordada e informamos por pantalla */
			if (valor!="") {
				cantidad--;
				System.out.println("El "+getName()+" ha leido el mensaje: "+valor);
			}
			
			/* Espero un ratito para la proxima lectura */
			try {
				sleep((int)(Math.random()*10+10));
			}
			catch (InterruptedException e) {
				System.err.println("Error al intentar obtener un mensaje");
			}
		}
		
		/* Cuando a leido todos los mensajes asignados, acaba*/
		System.out.println("El "+getName()+ " ha finalizado de leer");
	}
}
