package gestorAplicacion.administrativo;
import java.util.ArrayList;

import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Persona;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una terminal, la cual tiene su respectivo nombre, dinero, una capacidad de vehiculos, tiene unas sedes específica,
 *  esta cuenta con una cantidad de vehiculos, las transportadoras asociadas, las reservas de los viajes, los viajes disponibles, destinos disponibles
 *  una ubicación y finalmente un administrador, esta clase nos servirá para administrar todo el sistema de la terminal.   
 */

public class Terminal {
	
	private String nombre; // Nombre de la terminal
	private int dinero; // Dinero de la terminal 
	private int capacidadVehiculos; // capacidad de vehículos en la terminal
	private static int cantidadSedes = 0; // cantidad de sedes de la terminal
	private int cantidadVehiculos; // cantidad de vehículos que hay en la terminal 
	private ArrayList <Transportadora> transportadoras = new ArrayList<>(); // transportadoras asociadas a la terminal 
	private ArrayList <Viaje> reservas = new ArrayList<>(); // reservas de viaje de la terminal
	private ArrayList <Viaje> viajes = new ArrayList<>(); // viajes disponibles en la terminal
	private ArrayList <Viaje> viajesEnCurso = new ArrayList<>(); // viajes en curso en la terminal
	private ArrayList <Destino> destinos = new ArrayList<>(); // destinos de la terminal 
	private Destino ubicacion; // ubuicación de la terminal 
	private Persona administrador; // Administrador de la terminal 
	
	// Constructor Clase Terminal
	public Terminal(String nombre, int dinero, int capacidadVehiculos, int cantidadSedes, int cantidadVehiculos, ArrayList <Transportadora> transportadoras, 
			        ArrayList <Viaje> reservas, ArrayList <Viaje> viajes, ArrayList <Viaje> viajesEnCurso, ArrayList <Destino> destinos, Destino ubicacion, Persona administrador) {
		
		this.nombre = nombre;
		this.dinero = dinero;
		this.capacidadVehiculos = capacidadVehiculos;
		this.cantidadVehiculos = cantidadVehiculos;
		this.transportadoras = transportadoras;
		this.reservas = reservas;
		this.viajes = viajes;
		this.viajesEnCurso = viajesEnCurso;
		this.destinos = destinos;
		this.ubicacion = ubicacion;
		this.administrador = administrador;
		Terminal.cantidadSedes++;
	}
	
	public ArrayList<Transportadora> viajesDisponiblesTransportadora(String destinoDeseado) {
		ArrayList <Transportadora> transportadorasConDestino = new ArrayList<>();
		for (Transportadora transportadora : transportadoras) {
			boolean esta = false;
			
			for (Destino destino:transportadora.getDestinos()) {
				if (destino.name().equals(destinoDeseado)) {
					esta = true;					
				}
			}
			
			if (esta) {
				transportadorasConDestino.add(transportadora);
			}
		}
		
		return transportadorasConDestino;
	}
	
	/**
	 * Este método verifica la disponibilidad de cada una de 
	 * las transportadoras dentro de la terminal para realizar 
	 * un viaje con un destino selecionado
	 * @input String, Nombre del destino deseado.
	 * @return ArrayList<Transportadora>, transporatadoras con disponibilidad.
	 */
	
	public Viaje programarViaje() {
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void cancelarViaje() {
		// Implementación pendiente
		
		
	}
	
	public ArrayList <Viaje> verificarDisponibilidad(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	/**
	 * Método para saber si hay asientosDisponibles para un viaje.
	 * @return Boolean, dependiendo la condición.
	 */
	
	public Boolean consultarCapacidad(Viaje viaje) {
		int capacidadVehiculo = viaje.getVehiculo().getTipo().getCapacidad(); // Valor de la cantidad de Asientos del Vehiculo.
		int asientosOcupados = viaje.getPasajeros().size(); // Determinara la cantidad de pasajeros que tiene el viaje actualmente.
		int disponibles = capacidadVehiculo - asientosOcupados;
		Boolean capacidad;
		if (capacidadVehiculo >= asientosOcupados) {
			capacidad = true;
			System.out.println("El número de asientos disponibles es: " + disponibles + "/" + capacidadVehiculo);
		}
		else {
			capacidad = false;
			System.out.println("No hay asientos disponibles. ");
		}
		return capacidad;
	}
	
	public void realizarReserva() {
		
		// Implementación pendiente
		
	}
	
	public void denegarReserva() {
		
		// Implementación pendiente
		
		
	}
	
	public ArrayList <Factura> obtenerFinanzas(){
		
		// Implementación pendiente 
		
		return null;
		
		
	}
	
	public String estadoViaje() {
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void asignarViaje() {
		
		// Implementación pendiente
		
		
	}
	
	public void designarViaje() {
		
		// Implementación pendiente
		
		
	}
	

			
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Establece o modifica el nombre de la terminal.
	 * @param nombre, el nombre de la terminal.
	 */
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	/**
	 * Método para obtener el nombre de la terminal.
	 * @return nombre de la terminal.
	 */
	
	public String getNombre() {
		
		return nombre;
				
	}
	
	/**
	 * Establece o modifica el dinero de la terminal.
	 * @param dinero, el dinero de la terminal.
	 */
	
	public void setDinero (int dinero) {
		
		this.dinero = dinero;
		
	}
	
	/**
	 * Método que nos sirve para obtener el dinero de la terminal.
	 * @return el dinero de la terminal.
	 */
	
	public int getDinero() {
		
		return dinero;
		
	}
	
	/**
	 * Establece o modifica la capacidad de veículos de la terminal.
	 * @param capacidadVehiculos, la capacidad de vehículos de la terminal.
	 */
	
	public void setCapacidadVehiculos(int capacidadVehiculos) {
		
		this.cantidadVehiculos = capacidadVehiculos;
			
	}
	
	/**
	 * Método que nos permite obtener la capacidad de vehículos en la terminal.
	 * @return la capacidad de vehiculos de la terminal.
	 */
	
	public int getCapacidadVehiculos() {
		
		return capacidadVehiculos;
		
	}
	
	/**
	 * Establece o modifica la cantidad de sedes de la terminal.
	 * @param cantidadSedes, la cantidad de sedes de la terminal.
	 */
	
	public void setCantidadSedes(int cantidadSedes) {
		
		Terminal.cantidadSedes = cantidadSedes;
		
	}
	
	/**
	 * Método que nos permite obtener la cantidad de sedes de la terminal.
	 * @return cantidad de sedes de la terminal.
	 */
	
	public int getCantidadSedes() {
		
		return Terminal.cantidadSedes;
		
	}
	
	/**
	 * Establece la cantidad de vehículos que hay en la terminal.
	 * @param cantidadVehiculos, la cantidad de vehículos en la terminal.
	 */
	
	public void setCantidadVehiculos(int cantidadVehiculos) {
		
		this.cantidadVehiculos = cantidadVehiculos;
		
	}
	
	/**
	 * Método que nos permite obtener la cantidad de vehículos que hay en la terminal.
	 * @return cantidad de vehículos que hay en la terminal.
	 */
	
	public int getCantidadVehiculos() {
		
		return cantidadVehiculos;
		
	}
	
	/**
	 * Establece o modifica las transportadoras asociadas a la terminal.
	 * @param trasnportadoras, lista de las transportadoras asociadas a la terminal.
	 */
	
	public void setTransportadoras(ArrayList <Transportadora> transportadoras) {
		
		this.transportadoras = transportadoras;
		
	}
	
	/**
	 * Método que nos permite obtener a las transportadoras asociadas a la terminal.
	 * @return lista de las transportadoras asociadas a la terminal.
	 */
	
	public ArrayList <Transportadora> getTransportadoras(){
		
		return transportadoras;
	}
	
	/**
	 * Establece o modifica las reservas de la terminal.
	 * @param reservas, las reservas asociadas a la terminal.
	 */
	
	
	public void setReservas(ArrayList <Viaje> reservas) {
		
		this.reservas = reservas;
		
	}
	
	/**
	 * Método que nos permite obtener las reservas asociadas a la terminal.
	 * @return lista de las reservas asociadas a la terminal.
	 */
	
	public ArrayList <Viaje> getReservas(){
		
		return reservas;
	
	}
	
	/**
	 * Establece o modifica los viajes asocaidos a la terminal.
	 * @param viajes, lista con los viajes asociados a la terminal
	 */
	
	public void setViajes(ArrayList <Viaje> viajes) {
		
		this.viajes = viajes;
		
	}
	
	/**
	 * Método para obtener la lista con los viajes asociados a la terminal.
	 * @retunr lista de viajes asociados a la terminal.
	 */
	
	public ArrayList <Viaje> getViajes(){
		
		return viajes;
	
	}
	
	/**
	 * Establece o modifica los viajes en curso asocaidos a la terminal.
	 * @param viajes, lista con los viajes en curso asociados a la terminal
	 */
	
	public void setViajesEnCurso(ArrayList <Viaje> viajesEnCurso) {
		
		this.viajesEnCurso = viajesEnCurso;
		
	}
	
	/**
	 * Método para obtener la lista con los viajes en curso asociados a la terminal.
	 * @retunr lista de viajes en curso asociados a la terminal.
	 */
	
	public ArrayList <Viaje> getViajesEnCurso(){
		
		return viajesEnCurso;
	
	}
	
	/**
	 * Establece o modifica la lista de destinos asociado a la terminal.
	 * @param destinos, lista con los destinos asociados a la terminal.
	 */
	
	public void setDestinos(ArrayList <Destino> destinos) {
		
		this.destinos = destinos;
		
	}
	
	/**
	 * Método para obtener la lista con los destinos asociados a la terminal.
	 * @return lista con los destinos asociados a la terminal
	 */
	
	public ArrayList <Destino> getDestinos(){
		
		return destinos;
	
	}
	
	/**
	 * Método para obtener la ubicación de la terminal.
	 * @return ubicación de la terminal.
	 */
	
	public Destino getUbicacion() {
		
		return ubicacion;
	}
	
	/**
	 * Establece o modifica el administrador de la terminal.
	 * @param administrador, el administrador de la terminal.
	 */
	
	public void setAdministrador(Persona administrador) {
		
		this.administrador = administrador;
		
	}
	
	/**
	 * Método para obtener el administrador de la terminal.
	 * @return el administrador de la terminal.
	 */
	
	public Persona getAdministrador() {
		
		return administrador;
		
	}
	
	
	
	
	

}
