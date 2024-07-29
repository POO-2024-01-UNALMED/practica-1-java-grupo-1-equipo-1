package gestorAplicacion.usuarios;
import java.util.ArrayList;

import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.administrativo.Terminal;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a un pasajero, el cuál es pieza fundamental en una terminal, esta clase nos servirá para tener realizar interaciones entre 
 *  varias clases como lo son transportadora, factura, terminal y viaje, nos será de mucha utilidad ya que con esta podremos asociar pasajeros a viajes
 *  cancelar el viaje de un pasajero y rembolsar su respectivo dinero. 
 */

public class Pasajero extends Persona {
	
	private TipoPasajero tipo; // Tipo de pasajero asociado al pasajero
	
	//Constructor con todos los parámetros
	
	public Pasajero(TipoPasajero tipo, int id, int edad, String nombre, char genero, ArrayList<Viaje> historial, int experiencia,
			int dinero) {
		super(id, edad, nombre, genero, historial, experiencia, dinero);
		
		this.tipo = tipo;
	}

	
	//constructor sin parámetros
	
	public Pasajero() {
				
	}
	
	public Viaje masEconomico(String destinoDeseado, int cantidad){

		Viaje viajeMasBarato = null;

	   	//Suponiendo que "viajes" es una arrayList con los viajes que se 
		//encuentran disponibles en la terminal y que "finalDestino" de transportadora es hacia donde se dirige

		for (Viaje viaje : Terminal.getViajes()) {
	        // Verificar si el viaje tiene el destino deseado y suficientes asientos disponibles
	        if (viaje.getLlegada().name().equals(destinoDeseado) && viaje.getAsientosDisponibles() >= cantidad&&viaje.getEstado()==false) {
	            if (viajeMasBarato == null || viaje.getTarifa() < viajeMasBarato.getTarifa()) {
	                viajeMasBarato = viaje;
	                
	            }
	        }
	    }

	    return viajeMasBarato;
	}
	
	/**
	 * Este método elige el viaje con la tarifa más económica.
	 * @param destinoDeseado Nombre del destino deseado.
	 * @param cantidad Asientos solicitados.
	 * @return El viaje más barato que cumple con los criterios.
	 */

	
	public String identificarse() {
		
		// Implementación pendiente
		
		return null;
	}
	
	
	public void pagarFactura() {
		
		// Implementación pendiente
		
		
	}
	
	public void cancelarViaje() {
		
		// Implementación pendiente
		
		
	}
	
	/**
	 * Método para obtener el tipo de pasajero de la pasajero.
	 * @return el tipo de pasajero de dicho pasajero.
	 */
	
	public TipoPasajero getTipo() {
		
		return tipo;
	}

}
