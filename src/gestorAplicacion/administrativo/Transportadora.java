package gestorAplicacion.administrativo;
import java.util.ArrayList;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.constantes.Destino;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una transportadora, la cuál tiene su respectivo nombre, dinero, una lista de conductores, pasajeros, vehículos, viajes,
 *  un destino asiginado y una terminal, esta clase nos servirá para organizar los distitnos viajes, asociar a los conductores, hacerle su respectivo
 *  pago y monitorear a los distintos vehículos asociados a esta.
 */

public class Transportadora {
	
	// Atributos
	
	private String nombre; // Nombre de la transportadora
	private int dinero; // Dinero de la transportadora 
	private ArrayList <Conductor> conductores = new ArrayList<>(); // Lista de conductores de la transportadora
	private ArrayList <Pasajero> pasajeros = new ArrayList<>(); // Lista de pasajeros de la transportadora
	private ArrayList <Vehiculo> vehiculos = new ArrayList<>(); // Lista de vehículos asociados a la transportadora
	private ArrayList <Viaje> viajeAsignado = new ArrayList<>(); // Viajes de la transportadora
	private Destino destinoAsignado; // Destino asignado a la transportadora, lugar hacia donde esta viajará
	private Terminal terminal; // Terminal en donde opera la transportadora 
	
	//Constructor con todos los parámetros
	
	public Transportadora(String nombre, int dinero, ArrayList<Conductor> conductores, ArrayList<Pasajero> pasajeros,
			ArrayList<Vehiculo> vehiculos, ArrayList<Viaje> viajeAsignado, Destino destinoAsignado, Terminal terminal) {
		
		this.nombre = nombre;
		this.dinero = dinero;
		this.conductores = conductores;
		this.pasajeros = pasajeros;
		this.vehiculos = vehiculos;
		this.viajeAsignado = viajeAsignado;
		this.destinoAsignado = destinoAsignado;
		this.terminal = terminal;
	}

    //Constructor sin parámetros

	public Transportadora() {		
		
	}
	
	
	
	/*
	 * Metodo para contratar un conductor el cual se agregara
	 * a la lista de conductores de la transportadora.
	 * @param Conductor a contratar
	 */
	
	public void contratarConductor(Conductor conductor) {
		
		this.getConductores().add(conductor);
		
	}
	
	/* 
	 * Metodo para despedir conductor al cual se le remueve el vehiculo,
	 *  se le remueve de la lista de conductores de la transportadora y 
	 * se elimina el conductor.
	 * Para esto, primero se verifica que no tenga viajes programados y 
	 * que su vehiculo tenga almenos 2 conductores.
	 * @param Conductor a despedir*/
	
	public void despedirConductor(Conductor conductor) {
		
		if (conductor.getHorario().size() == 0) {
			if (conductor.getVehiculo().getConductores().size() >= 2 || conductor.getVehiculo() == null) {
				conductor.quitarVehiculo();
				int indiceConductor = this.getConductores().indexOf(conductor);
				this.getConductores().remove(indiceConductor);
				conductor = null;
			}
		}
		
	}
	
	public void pagarTerminal() {
		
		// Implementación pendiente
		
		
	}
	
	public Vehiculo agregarVehiculo() {
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void removerVehiculo() {
		
		// Implementación pendiente
		
		
	}
	
	public void realizarMantenimiento() {
		
		// Implementación pendiente
		
		
	}
	
	public ArrayList <Vehiculo> disponibilidadVehiculos(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public ArrayList <Factura> reporteFinanciero(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void pagarConductor() {
		
		// Implementación pendiente 
		
		
	}
	
	public boolean realizarRevision() {
		
		// Implementación pendiente
		
		return false;
	
		
	}
	
	public boolean estadoVehiculo() {
		
		// Implementación pendiente
		
		return false;
		
		
	}
	
	public void asignarVehiculo() {
		
		// Implementación pendiente
		
		
	}
	
	public void designarVehiculo() {
		
		// Implementación pendiente
		
		
	}
	
	public Factura ventaViaje() {
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Establece o modifica el nombre de la transportadora.
	 * @param nombre, el nombre de la transportadora.
	 */
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	/**
	 *Método para obtener el nombre de la transportadora.
	 * @return el nombre de la transportadora.
	 */
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	/**
	 * Establece o modifica el dinero de la transportadora.
	 * @param dinero, el dinero de la trasnportadora.
	 */
	
	public void setDinero(int dinero) {
		
		this.dinero = dinero;
		
	}
	
	/**
	 * Método para obtener el dinero de la transportadora.
	 * @return el dinero de la transportadora.
	 */
	
	public int getDinero() {
		
		return dinero;
	}
	
	/**
	 * Establece o modifica los conductores asociados a la transportadora.
	 * @param conductores, lista de conductores de la transportadora.
	 */
	
	public void setConductores(ArrayList <Conductor> conductores) {
		
		this.conductores = conductores;
		
	}
	
	/**
	 * Método para obtener la lista de los conductores de la transportadora.
	 * @return lista de conductores asociados a la transportadora.
	 */
	
	public ArrayList <Conductor> getConductores(){
		
		return conductores;
		
	}
	
	/**
	 * Establece o modifica la lista de pasajeros asociados a la transportadora.
	 * @param pasajeros, la lista de pasajeros asociados a la transportadors.
	 */
	
	public void setPasajeros(ArrayList <Pasajero> pasajeros) {
		
		this.pasajeros = pasajeros;
		
	}
	
	/**
	 * Método para obtener la lista de pasajeros asociados a la transportadora.
	 * @return la lista de pasajeros asociados a la transportadora.
	 */
	
	public ArrayList <Pasajero> getPasajeros(){
		
		return pasajeros;
		
	}
	
	/**
	 * Establece o modifica la lista de vehículos asociados a la transportadora.
	 * @param vehiculos, la lista de vehículos asociados a la transportadora.
	 */
	
	public void setVehiculos(ArrayList <Vehiculo> vehiculos) {
		
		this.vehiculos = vehiculos;
		
	}
	
	/**
	 * Método para obtener la lista de vehículos asociados a la transportadora.
	 * @return la lista de vehículos asociados a la transportadora.
	 */
	
	public ArrayList <Vehiculo> getVehiculo(){
		
		return vehiculos;
		
	}
	/**
	 * Establece o modifica la lista de los viajes asignados a la transportadora.
	 * @param viajeAsignado, lista con los viajes asignados a la transportadora.
	 */
	
	public void setViajeAsignado(ArrayList <Viaje> viajeAsignado) {
		
		this.viajeAsignado = viajeAsignado;
		
	}
	
	/**
	 * Método para obtener la lista de los viajes asignados a la transportadora.
	 * @return lista de los viajes asignados a la transportadora.
	 */
	
	public ArrayList <Viaje> getViajeAsignado(){
		
		return viajeAsignado;
		
	}
	
	/**
	 * Método para obtener el destino asignado a la transportadora.
	 * @return el destino asignado a la transportadora.
	 */
	
	
	public Destino getDestinoAsignado() {
		
		return destinoAsignado;
		
	}
	
	/**
	 * Establece o modifica la terminla asociada a la tranportadora.
	 * @param terminal, la terminal asociada a la transportadora.
	 */
	
	public void setTerminal(Terminal terminal) {
		
		this.terminal = terminal;
		
	}
	/**
	 * Método para obtener la terminal a la cuál está asociada la transportadora.
	 * @return la terminal asociada a la transportadora.
	 */
	
	public Terminal getTerminal() {
		
		return terminal;
		
	}
	
	
	
	
	
	

}
