package gestorAplicacion.usuarios;
import java.util.ArrayList;
import gestorAplicacion.administrativo.Vehiculo;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Factura;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a un conductor, el cual tiene una licencia, un vehículo asociado, una transportadora asociada y una lista con su horario
 *  de vijes, esta clase servirá para hacer interacciones como asociar un conductor a un viaje, remover un conductor de un viaje, asociarle un
 *  vehículo, quitarle un vehículo y ver su disponibilidad para realizar un viaje. 
 */

public class Conductor extends Persona {
	
	private boolean estadoLicencia; // Estado de la licencia del conductor
	private Vehiculo vehiculo; // Vehículo asociado al conductor
	private Transportadora transportadora; // Transportadora asociada al conductor 
	private ArrayList <Viaje> horario = new ArrayList<>(); // Horario de viajes del conductor
	
	/*Constructor*/
	
	/**
     * @param vehiculo, el vehiculo asociado al conductor.
     * @param transportadora, la transportadora asociada al conductor.
     * @param horario, la lista con el horario de viajes asociados al conductor
     * 
     */
	public Conductor(Vehiculo vehiculo, Transportadora transportadora, ArrayList<Viaje> horario) {
		super();
		this.vehiculo = vehiculo;
		this.transportadora = transportadora;
		this.horario = horario;
	}

	public void conducir() {
		
		// Implementación pendiente
		
		
	}
	
	public String notificarLllegada() {
		
		// Implementación pendiente
		
		return null;
		
	}
	
	public void finalizarViaje() {
		
		// Implementación pendiente
		
		
	}
	
	public ArrayList <Factura> reporteFinanciero(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void renovarContraro() {
		
		// Implementación pendiente
		
		
	}
	
	public ArrayList <Viaje> consultarHorario(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void reportarProblema() {
		
		// Implementación pendiente
		
		
	}
	
	public void tomarVehiculo() {
		
		// Implementación pendiente
		
		
	}
	
	public void quitarVehiculo() {
		
		// Implementación pendiente
		
		
	}
	
	public String identificarse() {
		
		// Implementación pendiente
		
		return null;
	}
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Establece o modifica el estado de la licencia del conductor.
	 * @param estadoLicencia, el estado de la licencia del conductor.
	 */
	
	public void setEstadoLicencia(boolean estadoLicencia) {
		
		this.estadoLicencia = estadoLicencia;
		
	}
	
	/**
	 * Método que nos permite obtener el estado de la licencia del conductor.
	 * @return el estado de la licencia del conductor.
	 */
	
	public boolean getEstadoLicencia() {
		
		return estadoLicencia;
		
	}
	
	/**
	 * Establece o modifica el vehículo asociado al conductor.
	 * @param vehiculo, vehículo que se le asociará al conductor.
	 */
	
	public void setVehiculo(Vehiculo vehiculo) {
		
		this.vehiculo = vehiculo;
		
	}
	
	/**
	 * Método que nos permite obtener el vehículo asociado al conductor.
	 * @return vehículo asociado al conductor.
	 */
	
	public Vehiculo getVehiculo() {
		
		return vehiculo;
		
	}
	
	/**
	 * Establece o modifica la transportadora a la cuál está vinculado el conductor.
	 * @param transportadora, la trasnportadora vinculada con el conductor.
	 */
	
	public void setTransportadora(Transportadora transportadora) {
		
		this.transportadora = transportadora;
		
	}
	
	/**
	 * Método para obtener la transportadora a la cuál el conductor está asociado.
	 * @return transportadora asociada al conductor.
	 */
	
	public Transportadora getTransportadora() {
		
		return transportadora;
		
	}
	
	/**
	 * Establece o modifica el listado con el horario asociado al conductor.
	 * @param horario, listado con el horario asociado al conductor.
	 */
	
	public void setHorario(ArrayList <Viaje> horario) {
		
		this.horario = horario;
		
	}
	
	/**
	 * Método para obtener el listado con el horario del conductor.
	 * @return El listado del horario asociado al conductor.
	 */
	
	public ArrayList <Viaje> getHorario(){
		
		return horario;
	}
	
	
	
	
	


}
