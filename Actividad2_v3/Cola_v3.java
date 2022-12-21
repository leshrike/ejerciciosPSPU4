package Actividad2_v3;

import java.util.ArrayList;
import java.util.List;

import Actividad2.Cola_de_mensajes;

/* Cola de mensajes, memoria compartida */
public class Cola_v3 {

	/* Atributos de la cola de mensajes */
	private List<String> colaMensajes;		// Cola que almacena Strings
	private int cantMensajesEsc;			// Cantidad de mensajes insertados en la cola
	private int cantMensajesLei;			// Cantidad de mensajes sacados en la cola
	private boolean escribir;				//Se puede escribir	
	private boolean leer;					//Se puede leer
		

	/* Constgructor de la clase, se inicializan los atributos */
	public Cola_v3() {
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
		
		/* Al utilizar notify() en lugar de notityAll(), solo se despierta un hilo, nos da igual
		 * si es un escritor o un lector, lo que sabemos es que solo uno, eso significa que no
		 * necesitamos realizar ninguna comprobación extra, sabemos que hay un mensaje en la cola (seguro)
		 * y que escribir debende de si la cola esta llena o no */
       	colaMensajes.add(pmens);
       	cantMensajesEsc++;
       	
       	if(colaMensajes.size()==Cola_de_mensajes.kTAM_COLA)
       		escribir=false;

        /* Lectores pueden leer y despierto ... */
        leer=true;
        notify();        
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
		
	
        /* En caso de leer, leo el primer elemento de la cola y lo elimino */
       	valor = colaMensajes.get(0);
       	colaMensajes.remove(0);
       	cantMensajesLei++;

       	if(colaMensajes.size()==0)
       		leer=false;
        
        /* Despierto a los escritores y permito escribir */
        escribir=true;
        notify();
        return valor;
	}
}
