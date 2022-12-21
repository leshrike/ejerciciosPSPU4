package Actividad3_v2;

/* Clase del hilo Juez, solo hay uno, asi que debería ser fácil*/
public class Juez_v2 extends Thread {
	
	
	/* Atributos de la clase, el testigo, tambien controla la cantidad de corredores que llevamos */
	private Testigo_v2 miTestigo;
	
	
	/* Constructor, asigna valor a los atributos */
	public Juez_v2(Testigo_v2 ptest) {
		this.miTestigo=ptest;
	}
	

	
	/* proceso del hilo */
	public void run() {
		int tiempo=0;
		
		tiempo=(int)(Math.random()*10+100);
		
		System.out.println("El juez tiene el testigo y espera "+tiempo +"ms");
		try {
			Thread.sleep(tiempo);
		}
		catch (InterruptedException e) {
			System.err.println("Error al lanzar el testigo");
		}
		
		System.out.println("El juez lanza el testigo al aire. ¿Quíen lo atrapará?");
		
		/* Lanza el testigo */
		miTestigo.lanzarTestigo(tiempo);
	}

}
