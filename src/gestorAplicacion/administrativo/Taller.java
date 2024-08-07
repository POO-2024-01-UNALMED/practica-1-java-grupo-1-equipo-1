package gestorAplicacion.administrativo;
import java.util.ArrayList;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Mecanico;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a un taller, el cuál cuenta con vehiculos en reparación, vehículos en venta, cuenta con una ubicación específica, una factura,
 *  una lista de mecánicos, los cuales son los encargados de trabajar en el taller, tiene un nombre y una capacidad de vehiculos, nos servirá para 
 *  reparar los vehículos y para venderlos.
 */

public class Taller {
	
	// Atributos 
	
	private ArrayList <Vehiculo> vehiculosEnReparacion; // Lista de vehículos a reparar
	private ArrayList <Vehiculo> vehiculosEnVenta; // Lista de vehículos
	private Transportadora transportadora; // Transportadora asociada al taller 
	private Destino ubicacion; // Ubicación del taller
	private Factura factura; // objeto tipo factura que genera el taller 
	private ArrayList <Mecanico> mecanicos; // Mecánicos que trabajan en el taller
	private String nombre; // nombre del taller 
	private int capacidad; // capacidad de vehículos del taller 
	
	public void agregarVehiculoReparacion () {
		
		// Implementación pendiente
		
	}
	
	public void agregarVehiculoVenta () {
		
		// Implementación pendiente
		
	}
	
	public int generarCotizacion () {
		
		// Implementación pendiente
		
		return 0;
		
	}
	
	public void aplicarGastos () {
		
		// Implementación pendiente
		
	}
	
	public void regresarVehiculo () {
		
		// Implementación pendiente
		
	}
	
	public void venderVehiculo () {
		
		// Implementación pendiente
		
	}
	
	public int calcularIntegridad () {
		
		// Implementación pendiente
		
		return 0;
		
	}
	
	public int calcularValor () {
		
		// Implementación pendiente
		
		return 0;
		
	}
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Establece o modifica el nombre del taller.
	 * @param nombre, el nombre que se le va a asignar al taller.
	 */
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	/**
	 * Método que nos permite obtener el nombre de un objeto de tipo taller.
	 * @return el nombre del taller.
	 */
	
	public String getNombre() {
		
		return nombre;
	}
	
	/**
	 * Establece o modifica los vehículos en reparación del taller.
	 * @param vehiCulosEnReparacion, los vehiculos en reparacion del taller.
	 */
	
	public void setVehiculosEnReparacion(ArrayList<Vehiculo> vehiculosEnReparacion) {
		
		this.vehiculosEnReparacion = vehiculosEnReparacion;
		
	}
	
	/**
	 * Método que nos permite obtener los vehículos en reparación de un objeto de tipo taller.
	 * @return Lista de vehículos en reparación.
	 */
	public ArrayList<Vehiculo> getVehiculosEnReparacion() {
		
	    return vehiculosEnReparacion;
	}

	/**
	 * Establece o modifica los vehículos en venta del taller.
	 * @param vehiculosEnVenta, lista de vehículos en venta.
	 */
	public void setVehiculosEnVenta(ArrayList<Vehiculo> vehiculosEnVenta) {
		
	    this.vehiculosEnVenta = vehiculosEnVenta;
	}

	/**
	 * Método que nos permite obtener los vehículos en venta de un objeto de tipo taller.
	 * @return Lista de vehículos en venta.
	 */
	public ArrayList<Vehiculo> getVehiculosEnVenta() {
		
	    return vehiculosEnVenta;
	}

	/**
	 * Establece o modifica la transportadora del taller.
	 * @param transportadora, objeto de tipo Transportadora.
	 */
	public void setTransportadora(Transportadora transportadora) {
		
	    this.transportadora = transportadora;
	}

	/**
	 * Método que nos permite obtener la transportadora de un objeto de tipo taller.
	 * @return Objeto de tipo Transportadora.
	 */
	public Transportadora getTransportadora() {
		
	    return transportadora;
	}

	/**
	 * Establece o modifica la ubicación del taller.
	 * @param ubicacion, objeto de tipo Destino.
	 */
	public void setUbicacion(Destino ubicacion) {
		
	    this.ubicacion = ubicacion;
	}

	/**
	 * Método que nos permite obtener la ubicación de un objeto de tipo taller.
	 * @return Objeto de tipo Destino.
	 */
	public Destino getUbicacion() {
		
	    return ubicacion;
	}

	/**
	 * Establece o modifica la factura del taller.
	 * @param factura, objeto de tipo Factura.
	 */
	public void setFactura(Factura factura) {
		
	    this.factura = factura;
	}

	/**
	 * Método que nos permite obtener la factura de un objeto de tipo taller.
	 * @return Objeto de tipo Factura.
	 */
	public Factura getFactura() {
		
	    return factura;
	}

	/**
	 * Establece o modifica los mecánicos del taller.
	 * @param mecanicos, lista de mecánicos.
	 */
	public void setMecanicos(ArrayList<Mecanico> mecanicos) {
		
	    this.mecanicos = mecanicos;
	}

	/**
	 * Método que nos permite obtener los mecánicos de un objeto de tipo taller.
	 * @return Lista de mecánicos.
	 */
	public ArrayList<Mecanico> getMecanicos() {
		
	    return mecanicos;
	}

	/**
	 * Establece o modifica la capacidad del taller.
	 * @param capacidad, cantidad de vehículos que puede albergar el taller.
	 */
	public void setCapacidad(int capacidad) {
		
	    this.capacidad = capacidad;
	}

	/**
	 * Método que nos permite obtener la capacidad de un objeto de tipo taller.
	 * @return Capacidad del taller.
	 */
	public int getCapacidad() {
		
	    return capacidad;
	}

	
	
	
	
	
	
	

}
