package Actividad4;


/* Clase donde se realizan los ingresos y las retiradas de dinero, Memoria compartida*/
public class Cuenta {

	/* Atributos de la cuenta, cantidad que tiene y cantidad inicial que tenía */
	private int saldo;
	private int saldoInicial;

	
	/* Constructor de la cuenta, inicializando con las cantidades de los atributos */
	public Cuenta(int saldo) {
		this.saldoInicial=saldo;
		this.saldo=saldo;
	}
	
	
	/* Incluye dinero en la cuenta */
	public synchronized void hacerMovimiento(int cantidad){
		saldo = saldo+cantidad;
		System.out.println("Saldo actual: "+saldo);
	}
	
	
	/* Comprueba la simulación */
	public boolean esSimulacionCorrecta() {
		boolean valor=false;
		
		if (this.saldo==this.saldoInicial) 
			valor= true;
		
		return valor;
	}
	
	
	/* Devuelve el saldo actual */
	public int getSaldo(){
		return this.saldo;
	}
}




