package Actividad2_v2;

import java.util.ArrayList;
import java.util.List;

import Actividad2.Cola_de_mensajes;

/* Cola de mensajes, memoria compartida */
public class Cola_v2 {

	/* Atributos de la cola de mensajes */
	private List<String> colaMensajes;		// Cola que almacena Strings
	private int cantMensajesEsc;			// Cantidad de mensajes insertados en la cola
	private int cantMensajesLei;			// Cantidad de mensajes sacados en la cola
	private boolean escribir;				//Se puede escribir	
	private boolean leer;					//Se puede leer
		

	/* Constgructor de la clase, se inicializan los atributos */
	public Cola_v2() {
		colaMensajes=new ArrayList<String>();
		cantMensajesEsc=0;
		cantMensajesLei=0;
		escribir=true;
		leer=false;
	}
	
	
	/* Devuelve si la cola tiene todavía mensajes */
	public boolean quedanMensajes() {
		return (cantMensajesLei<Cola_de_mensajes.kMAX_MENSAJES);
	}
	
	
	/* Devuelve si la cola tiene todavía mensajes */
	public boolean faltanMensajes() {
		return (cantMensajesEsc<Cola_de_mensajes.kMAX_MENSAJES);
	}
	
	
	
	/* Método que hace que un escritor guarde su mensaje y en caso de no poder hacerlo
	 * hace que se espere hasta tener ocasion de hacerlo, metodo sincronizado con espera*/
	public synchronized boolean putMensaje(String pmens) {
		boolean valor=true;

		/* Si no se puede escribir porque esta llena la cola, espero */
		while(!escribir) {
			try {
                wait();
            } 
			catch (InterruptedException e) {
                System.err.println("Error al obtener el mensaje.");
            }
		}
		
		 /*Si despierto, pero no puedo incluir mi mensaje, debo volver a intentarlo,
		  * aviso que la cola esta llena y despierto a los lectores*/
        if (colaMensajes.size()==Cola_de_mensajes_v2.kTAM_COLA){
        	valor=false;
        	escribir=false;
        }
        else {
        	colaMensajes.add(pmens);
        	cantMensajesEsc++;
        }

        /* Lectores pueden leer y despierto ... */
        leer=true;
        notifyAll();        
        return valor;
	}
	
	
	
	/* Al igual que el método anterior, intento leer de la cola de mensajes, metodo sincronizado con espera*/
	public synchronized String getMensaje() {
		String valor="";
		
		/* Si no se puede leer porque esta vacía la cola, espero */
		while(!leer) {
			try {
                wait();
            } 
			catch (InterruptedException e) {
                System.err.println("Error al obtener el mensaje.");
            }
		}
		
		 /*Si no hay mensajes devuelvo nulo. Hago que no se pueda leer y despierto la escritura*/
        if (colaMensajes.size()==0){
        	valor="";
        	leer=false;
        }
        
        /* En caso de leer, leo el primer elemento de la cola y lo elimino */
        else {
        	valor = colaMensajes.get(0);
        	colaMensajes.remove(0);
        	cantMensajesLei++;
        }
        
        /* Despierto a los escritores y permito escribir */
        escribir=true;
        notifyAll();
        return valor;
	}
}
