package Actividad5;

public class Cliente extends Thread{

	/* Atributo con la barberia */
	private Barberia barberia;

	/* Constructor con la barbería y un nombre de cliente */
    public Cliente(Barberia barberia, String nombreCliente) {
        super(nombreCliente);
        this.barberia = barberia;
    }
    
    
    /* Devuelve el nombre del cliente */
    public String getNombre() {
        return this.getName();
    }

    
    /* Metodo principal, vamos añadiendo clientes*/
    public void run() {
        barberia.anyadirNuevoCliente(getName());
    }
}
