package Actividad1;


/* Clase de la cuenta donde los hilos ingresarán el dinero, es memoria a la que acceden todos
 * los hilos, así que hay que controlar su acceso, realizando una sincronización del metodo principal */
public class Calcular {
	
	/* Atributos de la clase, un contador total, uno de promedio, cantidad de hilos que ingresan y 
	 * el nombre del hilo que esta ingresando */
	private int totalSumaValor;
	private float promedio;
	private int ingresos;
	private String hilo;
	
	/* Constructor de la cuenta, solo inicializa atributos */
	public Calcular(int pcant) {
		this.totalSumaValor=pcant;
		this.promedio=0;
		this.ingresos=0;
		this.hilo="";
	}
	
	
	/* Devuelve el valor actual de la cuenta */
	public int getTotalSuma() {
		return totalSumaValor;
	}
	
	
	/* Devuelve el valor promedio actual */
	public float getPromedio() {
		return promedio;
	}
	
	
	/* Asigna nombre de un hilo al ingreso */
	public void setHilo(String pnom) {
		hilo=pnom;
	}
	
	
	/* Método que cambia el valor de nuestra memoria, debe ser sincronizada, recibe una cantida y 
	 * la suma al total que ya tenía la cuenta. Al sincronizar todo el método estamos realizando Exclusión Mutua */
	public synchronized void nuevoValor(int pcant) {
		
		/* No son pasos necesarios, pero hacemos que se modifiquen algunas posiciones de memoria
		 * para así ver que si no se sincroniza, habra problemas de acceso */
		int nuevoTotal=totalSumaValor+pcant;
		
		/* Esperamos un poco después de añadir la cantidad */
		try {
			Thread.sleep(10);
		}
		catch (InterruptedException e) {
			System.err.println("Error al añadir nueva cantidad");
		}

		/* Actualizamos los valores de la cuenta */
		totalSumaValor=nuevoTotal;
		ingresos++;
		promedio=(float)nuevoTotal/(float)ingresos;

		/* Mostramos la información por pantalla */
		System.out.println("Ha ingresado" + pcant+" el hilo "+ hilo + " ahora tenemos:"+totalSumaValor+" Euros");
		System.out.println("\t\tEl promedio actual es: "+promedio);
	}
}





