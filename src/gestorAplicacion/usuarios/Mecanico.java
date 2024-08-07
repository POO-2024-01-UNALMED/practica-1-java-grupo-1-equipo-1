package gestorAplicacion.usuarios;
import gestorAplicacion.administrativo.*;
import java.util.ArrayList;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a un mecánico, el cuál nos servirá para reparar los vehículos, dicho mecánico estará asociado a un taller y tendrá un vehículo
 *  asignado, el cuál tendrá que reparar, tendrá un historial con los vehículos reparados y un estado para saber si está disponible o no para arreglar
 *  un vehículo.
 */

public class Mecanico extends Persona{
	
	// Atributos
	
	private Taller taller; // Taller al cuál pertenece el mecánico
	private Vehiculo vehiculoAsignado; // Vehículo asignado al mecánico
	private ArrayList <Vehiculo> historialReparados; // Historial de vehículos reparados por el mecánico
	private Boolean estado; // Estado del mecánico
	private static ArrayList <Mecanico> mecanicos; // ???
	private static ArrayList <Mecanico> mecanicosDisponibles; // ???
	

	/**
	 * Constructor para la clase mecánico
     * @param taller, el taller asociado al mecánico.
     * @param vehiculoAsignado, el vehiculo asignado al mecánico.
     * @param estado, el estado de disponibilidad del mecánico.
     * 
     */
	
	public Mecanico(int id, int edad, String nombre, char genero, ArrayList <Viaje> historial, int experiencia,
			double dinero, ArrayList <Factura> facturas, Taller taller, Vehiculo vehiculoAsignado, Boolean estado) {
		
		super(id, edad, nombre, genero, historial, experiencia, dinero);
		this.taller = taller;
		this.vehiculoAsignado = vehiculoAsignado;
		this.estado = estado;
		this.historialReparados =  new ArrayList<Vehiculo>();
		mecanicos.add(this);
	}
	
	@Override
	public String identificarse() {
		
		// Implementación pendiente
		
		
		
		return null;
	
	}
	
	public void repararVehiculo() {
		
		// Implementación pendiente
		
	}
	
	public void calcularExperiencia() {
		
		// Implementación pendiente
		
	}
	
	@Override
	public void descuento(double porcentaje) {
		
	}
	
	@Override
	public void bonificacion(double premio) {
		
		
	}
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Establece o modifica el taller del mecánico.
	 * @param taller, objeto de tipo Taller.
	 */
	public void setTaller(Taller taller) {
	    this.taller = taller;
	}

	/**
	 * Método que nos permite obtener el taller de un objeto de tipo mecánico.
	 * @return Objeto de tipo Taller.
	 */
	public Taller getTaller() {
	    return taller;
	}

	/**
	 * Establece o modifica el vehículo asignado del mecánico.
	 * @param vehiculoAsignado, objeto de tipo Vehiculo.
	 */
	public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
	    this.vehiculoAsignado = vehiculoAsignado;
	}

	/**
	 * Método que nos permite obtener el vehículo asignado de un objeto de tipo mecánico.
	 * @return Objeto de tipo Vehiculo.
	 */
	public Vehiculo getVehiculoAsignado() {
	    return vehiculoAsignado;
	}

	/**
	 * Establece o modifica el historial de vehículos reparados del mecánico.
	 * @param historialReparados, lista de vehículos reparados.
	 */
	public void setHistorialReparados(ArrayList<Vehiculo> historialReparados) {
	    this.historialReparados = historialReparados;
	}

	/**
	 * Método que nos permite obtener el historial de vehículos reparados de un objeto de tipo mecánico.
	 * @return Lista de vehículos reparados.
	 */
	public ArrayList<Vehiculo> getHistorialReparados() {
	    return historialReparados;
	}

	/**
	 * Establece o modifica el estado del mecánico.
	 * @param estado, valor booleano que indica el estado del mecánico.
	 */
	public void setEstado(boolean estado) {
	    this.estado = estado;
	}

	/**
	 * Método que nos permite obtener el estado de un objeto de tipo mecánico.
	 * @return Estado del mecánico.
	 */
	public boolean getEstado() {
	    return estado;
	}

	
}
