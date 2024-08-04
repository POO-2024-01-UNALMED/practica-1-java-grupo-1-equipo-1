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
	private Taller taller; // Taller al cuál pertenece el mecánico
	private Vehiculo vehiculoAsignado; // Vehículo asignado al mecánico
	private ArrayList <String> historial; // Historial de vehículos reparados por el mecánico
	private Boolean estado; // Estado del mecánico
	private static ArrayList <Mecanico> mecanicos;
	private static ArrayList <Mecanico> mecanicosDisponibles;
	

	/**
	 * Constructor para la clase mecánico
     * @param taller, el taller asociado al mecánico.
     * @param vehiculoAsignado, el vehiculo asignado al mecánico.
     * @param estado, el estado de disponibilidad del mecánico.
     * 
     */
	
	public Mecanico(Taller taller, Vehiculo vehiculoAsignado, Boolean estado) {
		super();
		this.taller = taller;
		this.vehiculoAsignado = vehiculoAsignado;
		this.estado = estado;
		this.historial =  new ArrayList<String>();
		mecanicos.add(this);
	}
	
	
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
	
	// METODOS GETTERS Y SETTERS
	// Implementación pendiente
}
