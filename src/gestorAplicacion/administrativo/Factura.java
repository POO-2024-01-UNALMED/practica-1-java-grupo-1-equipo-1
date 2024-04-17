package gestorAplicacion.administrativo;
import gestorAplicacion.usuarios.*;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una factura, la cuál tiene un numero, total, el total de facturas, la terminal, pasajero, conductor, viaje, vehículo
 *  y transportadora asociada respectivamente a la factura, es decir dependiendo el objeto asociado, este tendrá su respectiva factura. 
 */

public class Factura {
	
	//Atributos 
	
	private int numeroFactura; // Número de la factura (Identificador)
	private int total; // Valor total 
	public static int totalFacturas; // Número total de obejtos factura creados
	private Terminal terminal; // Terminal asociada en la factura
	private Pasajero pasajero; // Pasajero asociado en la factura
	private Conductor conductor; // Conductor asociado en la factura
	private Viaje viaje; // Viaje asocioado a la factura 
	private Vehiculo vehiculo; // Vehículo asociado a la factura 
	private Transportadora transportadora; // Transportadora asociada a la factura
	
	
	
	public Factura() {
		
		// Implementación pendiente
		
		
	}
	
	public void imprimirFactura() {
		
		// Implementación pendiente
		
		
	}
	
	public void modificarFactura() {
		
		// Implementación pendiente
		
		
		
	}
	
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Establece o modifica el número de factura.
	 * @param numeroFactura, el número de la factura.
	 */
	public void setNumeroFactura(int numeroFactura) {
		
		this.numeroFactura = numeroFactura;
		
	}
	
	/**
	 * Método para obtener el número de la factura.
	 * @return el número de la factura.
	 */
	
	public int getNumeroFactura() {
		
		return numeroFactura;
		
	}
	
	/**
	 * Establece o modifica el total de la factura.
	 * @param total, el total de la factura.
	 */
	
	public void setTotal(int total) {
		
		this.total = total;
		
	}
	
	/**
	 * Método para obtener el total de la factura.
	 * @return total de la factura.
	 */
	
	public int getTotal() {
		
		return total;
		
	}
	
	/**
	 * Establece o modifica el número total de facturas.
	 * @param totalFacturas, el número de facturas creadas.
	 */
	
	public static void setTotalFactura(int totalFacturas) {
		
		Factura.totalFacturas = totalFacturas;
		
	}
	
	/**
	 * Método para obtener el número total de facturas creadas.
	 * @return el número total de facturas.
	 */
	
	public static int getTotalFactura() {
		
		return totalFacturas;
		
	}
	
	/**
	 * Establece o modifica la terminal asociada a la factura.
	 * @param terminal, la terminal asociada a la factura.
	 */
	
	public void setTerminal(Terminal terminal) {
		
		this.terminal = terminal;
		
	}
	
	/**
	 * Método para obtener la terminal asociada a una factura.
	 * @return la terminal asociada la la factura.
	 */
	
	public Terminal getTerminal() {
		
		return terminal;
		
	}
	
	/**
	 * Establece o modifica el pasajero asociado a una factura.
	 * @param pasajero, el pasajero asociado a la factura.
	 */
	
	public void setPasajero(Pasajero pasajero) {
		
		this.pasajero = pasajero;
		
	}
	
	/**
	 * Método para obtener el pasajero asociado a una factura.
	 * @return el pasajero asociado a la factura.
	 */
	
	public Pasajero getPasajero() {
		
		return pasajero;
		
	}
	
	/**
	 * Establece o modifica el conductor asociado a una factura.
	 * @param conductor, el conductor asociado a la factura.
	 */
	
	public void setConductor(Conductor conductor) {
		
		this.conductor = conductor;
		
	}
	
	/**
	 * Método para obtener el conductor asociado a una factura.
	 * @return el conductor asociado a una factura.
	 */
	
	public Conductor getConductor() {
		
		return conductor;
		
	}
	
	/**
	 * Establece o modifica el viaje asociado a una factura.
	 * @param viaje, el viaje asociado a la factura.
	 */
	
	public void setViaje(Viaje viaje) {
		
		this.viaje = viaje;
		
	}
	
	/**
	 * Método para obtener el viaje asociado a una factura.
	 * @return el viaje asociado a una factura.
	 */
	
	public Viaje getViaje() {
		
		return viaje;
		
	}
	
	/**
	 * Establece o modifica el vehículo asociado a una factura.
	 * @param vehiculo, el vehiculo asociado a una factura.
	 */
	
	public void setVehiculo(Vehiculo vehiculo) {
		
		this.vehiculo = vehiculo;
		
	}
	
	/**
	 * Método para obtener el vehículo asociado a una factura.
	 * @return el vehículo asociado a una factura.
	 */
	
	public Vehiculo getVehiculo() {
		
		return vehiculo;
		
	}
	
	/**
	 * Establece o modifica la transportadora asociada a una factura.
	 * @param transportadora, la transportadora asociada a una factura.
	 */
	
	public void setTransportadora(Transportadora transportadora) {
		
		this.transportadora = transportadora;
		
	}
	
	/**
	 * Método para obtener la transportadora asociada a una factura.
	 * @return la transportadora asociada a una factura.
	 */
	
	public Transportadora getTransportadora() {
		
		return transportadora;
		
	}
	
	
	
	
	
	
	
	

}
