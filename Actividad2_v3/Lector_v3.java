package Actividad2_v3;

/* Clase del Hilo lector */
public class Lector_v3 extends Thread {
	
	/* Atributos de la clase */
	private Cola_v3 colaMensajes;
	private int cantidad;
	
	/* Constructor que asigna valor a los atributos */
	public Lector_v3(String pnom, Cola_v3 pcol) {
		super(pnom);
		this.colaMensajes=pcol;
		this.cantidad=0;
	}
	
	
	/* Proceso principal del hilo Lector */
	public void run() {
		String valor="";
		boolean leer=true;
		
		/* Mientras tenga mensajes que leer, seguira en el bucle */
		while(leer) {
			
			/* Intenta leer y recibe un ok */
			valor=colaMensajes.getMensaje();
			
			/* Si la lectura se lleva a cabo, restamos una lectura a la cantidad acordada e informamos por pantalla */
			if (valor!="") {
				System.out.println("El "+getName()+" ha leido el mensaje: "+valor);
				cantidad++;
			}
			
			/* Espero un ratito para la proxima lectura */
			try {
				sleep((int)(Math.random()*10+10));
			}
			catch (InterruptedException e) {
				System.err.println("Error al intentar obtener un mensaje");
			}
			
			leer=colaMensajes.quedanMensajes();
		}
		
		/* Cuando a leido todos los mensajes asignados, acaba*/
		System.out.println("El "+getName()+ " ha finalizado de leer, en total ha leido "+cantidad);
	}
}
