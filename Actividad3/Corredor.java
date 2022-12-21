package Actividad3;

/* Clse del hilo corredor, tendremos 10 de estos funcionando */
public class Corredor extends Thread {
	
	/* Atributos de la clase, el testigo */
	private Testigo miTestigo;
	
	
	/* Constructor que asigna valor a los atributos */
	public Corredor(String pnom, Testigo ptest) {
		super(pnom);
		this.miTestigo=ptest;
	}
	
	
	/* Proceso principal del hilo Lector */
	public void run() {
		int tiempo=0;
		
		/* Comprueba que el hilo corra, en ese caso el tiempo será superior a 0, en otro caso 
		 * vuelve a intentar coger el testigo */
		while(tiempo==0) {
			
			/* Intenta coger el testigo */
			tiempo=miTestigo.cogerTestigo(getName());
		}
	}
	
}




