package Actividad1;

/* Clase hilo que unicente necesita su nombre y una cantidad para añadirla a la cuenta */
public class Hilo extends Thread {
	
	/* Atributos */
	private int cantidad;
	private Calcular cuenta;
	
	
	/* Constructor, con el nombre, la cantidad a ingresar y la cuenta donde hacerlo */
	public Hilo(String pnom, int pcant, Calcular pcuen) {
		super(pnom);
		this.cantidad=pcant;
		this.cuenta=pcuen;
	}
	
	
	/* Unicamente realiza un ingreso,pero le pasamos a la cuenta el nombre del hilo que 
	 * ingresa para poder mostrar la informacion correcta */
	public void run() {
		cuenta.setHilo(getName());
		cuenta.nuevoValor(cantidad);
	}
}
