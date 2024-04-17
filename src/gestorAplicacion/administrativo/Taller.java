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
	// Implementación pendiente
	
	
	

}
