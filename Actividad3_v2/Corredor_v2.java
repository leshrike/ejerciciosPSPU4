package Actividad3_v2;

/* Clse del hilo corredor, tendremos 10 de estos funcionando */
public class Corredor_v2 extends Thread {
	
	/* Atributos de la clase, el testigo */
	private Testigo_v2 miTestigo;
	
	
	/* Constructor que asigna valor a los atributos */
	public Corredor_v2(String pnom, Testigo_v2 ptest) {
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




