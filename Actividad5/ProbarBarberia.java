package Actividad5;

public class ProbarBarberia {
	
    /*Constantes con el número de sillas y de clientes.*/
    public static final int NUMERO_DE_SILLAS = 2;
    public static final int NUMERO_DE_CLIENTES = 10;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Inicio del servicio.");

        /*Instancio las variables necesarias.*/
        Barberia barberia = new Barberia(NUMERO_DE_SILLAS, NUMERO_DE_CLIENTES);
        Barbero barbero = new Barbero(barberia);

        /*Array de los clientes.*/
        Cliente[] clientes = new Cliente[NUMERO_DE_CLIENTES];

        /*Inicio el barbero.*/
        barbero.start();

        
        /*Creo los clientes y los inicio, espero 1 o 2 segundos antes de crear otro.*/
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(barberia, "Cliente " + (i + 1));
            clientes[i].start();
            
            try {
                Thread.sleep((int)(Math.random()*1000+1000));
                
            } catch (InterruptedException e) {
                System.err.println("Error al crear un cliente.");
            }
        }



        /*Espero a que el barbero y los clientes terminen.*/
        for (int i = 0; i < clientes.length; i++) 
            clientes[i].join();

        barbero.join();
        
       
        
        /*Imprimo el mensaje de fin, los clientes que ha atendido, los que no ha atendido y las ganancias del barbero.*/
        System.out.println("El barbero cierra la barbería, fin del servicio.");
        System.out.println("El barbero ha atendido a " + barberia.getContadorAtendidos() + " clientes.");
        System.out.println("El barbero no ha logrado atender a " + barberia.getContadorNoAtendidos() + " clientes.");
    }
}
