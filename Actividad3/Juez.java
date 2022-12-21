package Actividad3;

/* Clase del hilo Juez, solo hay uno, asi que debería ser fácil*/
public class Juez extends Thread {
	
	
	/* Atributos de la clase, el testigo, tambien controla la cantidad de corredores que llevamos */
	private Testigo miTestigo;
	private int cantidad;
	
	
	/* Constructor, asigna valor a los atributos */
	public Juez(Testigo ptest) {
		this.miTestigo=ptest;
		this.cantidad=0;
	}
	
	
	/* proceso del hilo */
	public void run() {
		
		/* Controlamos que hayan corrido todos los corredores */
		while(cantidad<Carrera_TestigoLoco.kNUM_CORREDORES) {
			
			/* Lanza el testigo */
			miTestigo.lanzarTestigo();
			
			/* Controlamos que sumamos un nuevo lanzamiento, que coincide con los corredores que llevamos */
			cantidad++;
			System.out.println("El Juez lanza el testigo número "+cantidad);
		}
		
		/* Muestra cuando acaba el hilo */
		System.out.println("El Juez ha finalizado su labor, en total se ha lanzado el testigo "+cantidad+ " veces.");
	}

}
