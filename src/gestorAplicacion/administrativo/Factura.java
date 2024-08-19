package gestorAplicacion.administrativo;
import gestorAplicacion.usuarios.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una factura, la cuál tiene un numero, total, el total de facturas, la terminal, pasajero, conductor, viaje, vehículo
 *  y transportadora asociada respectivamente a la factura, esta es útil ya que nos ayudará a llevar un registro de los diferentes pasajeros que 
 *  hayan comprado un ticket para un viaje, también nos servirá para llevar el registro de pago a los conductores, mecánicos y las transportadoras
 *  que hayan cancelado su respectivo monto a la terminal.
 */

	public class Factura implements Serializable {
	
	//Atributos 
	
	private static final long serialVersionUID = 1L;
	private int numeroFactura; // Número de la factura (Identificador)
	private double total; // Valor total de la factura
	public static int totalFacturas; // Número total de obejtos factura creados
	private Terminal terminal; // Terminal asociada en la factura
	private Pasajero pasajero; // Pasajero asociado en la factura
	private Conductor conductor; // Conductor asociado en la factura
	private Viaje viaje; // Viaje asocioado a la factura 
	private Vehiculo vehiculo; // Vehículo asociado a la factura 
	private Transportadora transportadora; // Transportadora asociada a la factura
	private Taller taller; // Taller asociado a la factura
	private String fecha; // Fecha en la que adquirió la factura
	public String trayecto; // Trayecto asociado a un pasajero, muestra el lugar de salida y el lugar de llegada del pasajero
	private static ArrayList <Factura> facturasCreadas = new ArrayList<>();
	private int facturasTransportadora = 0;
	private int facturasPasajeros = 0;
	private int facturasConductores = 0;
	private int facturaTalleres = 0;
	private int facturaMecanico = 0;
	
	/**
	 * Constructor para la clase factura, este objeto estará asociado con un pasajero
     * @param total, el total de la factura.
     * @param pasajero, el pasajero asociado a la factura.
     * @param terminal, la terminal donde se generó dicha factura.
     * @param conductor, el conductor asociado al viaje en la factura.
     * @param viaje, el viaje al cual pertenece la factura.
     * @param vehiculo, el vehículo asociado a la factura.
     * @param transportadora, la transportadora que genera la factura.
     */
	
	public Factura(double total, Pasajero pasajero, Terminal terminal, Conductor conductor, Viaje viaje, Vehiculo vehiculo, Transportadora transportadora) {
		
		numeroFactura = (int)(Math.random()*10000);
		this.total = total;
		this.pasajero = pasajero;
		this.terminal = terminal;
		this.conductor = conductor;
		this.viaje = viaje;
		this.vehiculo = vehiculo;
		this.transportadora = transportadora;
		this.trayecto = viaje.getSalida().name() + " - " +viaje.getLlegada().name();
		pasajero.getFacturas().add(this);
		totalFacturas++;
		Factura.facturasCreadas.add(this);
		facturasPasajeros ++;
	}
	
	
	/**
	 * Constructor para la clase factura, este objeto estará asociado con el dueño de una transportadora
     * @param total, el valor total de la factura.
     * @param terminal, la terminal a la cual está asociada la transportadora.
     */
	
	public Factura(double total, Terminal terminal) {
		
		numeroFactura = (int)(Math.random()*10000);
		this.total = total;
		this.terminal = terminal;
		totalFacturas++;
		Factura.facturasCreadas.add(this);
		facturasTransportadora++;
		
	}

	/**
	 * Constructor para la clase factura, este objeto estará asociado con un conductor de una transportadora en específico
     * @param total, el valor total de la factura.
     * @param transportadora, la transportadora de la cual el conductor hace parte.
     * @param vehiculo, el vehículo que ha conducido el conductor
     */
	
	
	public Factura(double total, Transportadora transportadora, Vehiculo vehiculo) {
		
		numeroFactura = (int)(Math.random()*10000);
		this.total = total;
		this.transportadora = transportadora;
		this.vehiculo = vehiculo;
		totalFacturas++;
		Factura.facturasCreadas.add(this);
		facturasConductores++;
		
	}
	
	/**
	 * Constructor para la clase factura, este objeto estará asociado con un mecánico que hace parte de un taller
     * @param total, el valor total de la factura.
     * @param transportadora, la transportadora en la cual el taller en el que trabaja el mecánico hace parte.
     * @param taller, el taller asociado con el mecánico
     */
	 
	public Factura(double total, Transportadora transportadora, Taller taller) {
		
		numeroFactura = (int)(Math.random()*10000);
		this.total = total;
		this.transportadora = transportadora;
		this.taller = taller;
		totalFacturas++;
		Factura.facturasCreadas.add(this);
		this.facturaMecanico++;
			
	}
	
	public static Factura crearFacturaPasajero(double total, Pasajero pasajero, Terminal terminal, Conductor conductor, Viaje viaje, Vehiculo vehiculo, Transportadora transportadora) {
		
		return new Factura(total, pasajero, terminal, conductor, viaje, vehiculo, transportadora);
		
	}
	
	public static Factura crearFacturaConductor(double total, Transportadora transportadora, Vehiculo vehiculo) {
		
		return new Factura(total, transportadora, vehiculo);
		
	}
	
	public static Factura crearFacturatransportadora(double total, Terminal terminal) {
		
		return new Factura(total, terminal);
		
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
	
	public void setTotal(double total) {
		
		this.total = total;
		
	}
	
	/**
	 * Método para obtener el total de la factura.
	 * @return total de la factura.
	 */
	
	public double getTotal() {
		
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
	
	
	/**
	 * Establece o modifica el taller asociado a una factura.
	 * @param taller, el taller asociado a una factura.
	 */
	
	public void setTaller(Taller taller) {
		
		this.taller = taller;
		
	}
	
	/**
	 * Método para obtener el taller asociado a una factura.
	 * @return el taller asociado a una factura.
	 */
	
	public Taller getTaller() {
		
		return taller;
	}
	
	/**
	 * Establece o modifica la fecha asociada a una factura.
	 * @param fecha, la fecha asociada a una factura.
	 */
	
	public void setFecha(String fecha) {
		
		this.fecha = fecha;
		
	}
	
	/**
	 * Método para obtener la fecha asociada a una factura.
	 * @return fecha asociada a dicha factura.
	 */
	
	public String getFecha() {
		
		return fecha;
		
	}
	
	public static ArrayList <Factura> getFacturasCreadas() {
	
		return Factura.facturasCreadas;
		
	}
	
	public static void setFacturasCreadas(ArrayList<Factura> facturasCreadas) {
		
		Factura.facturasCreadas = facturasCreadas;
		
	}
	
	public int getFacturasTransportadora() {
		
		return this.facturasTransportadora;
	}
	
	public int getFacturasPasajero() {
		
		return this.facturasPasajeros;
	}
	
	public int getFacturasConductores() {
		
		return this.facturasConductores;
	}
	
	public int getFacturasTalleres() {
		
		return this.facturaTalleres;
	}
	
	public int getFacturasMecanicos() {
		
		return this.facturaMecanico;
	}
	
	
	
}
