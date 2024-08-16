package gestorAplicacion.administrativo;
import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.usuarios.Persona;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una terminal, la cual tiene su respectivo nombre, dinero, una capacidad de vehiculos, tiene unas sedes específica,
 *  esta cuenta con una cantidad de vehiculos, las transportadoras asociadas, las reservas de los viajes, los viajes disponibles, destinos disponibles
 *  una ubicación y finalmente un administrador, esta clase nos servirá para administrar todo el sistema de la terminal.   
 */

public class Terminal implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	// Atributos
	private String nombre; // Nombre de la terminal
	private double dinero; // Dinero de la terminal 
	private int capacidadVehiculos; // capacidad de vehículos en la terminal
	public static int cantidadSedes = 0; // cantidad de sedes de la terminal
	private int cantidadVehiculos; // cantidad de vehículos que hay en la terminal 
	private static ArrayList <Transportadora> transportadoras = new ArrayList<>(); // transportadoras asociadas a la terminal 
	private ArrayList <Viaje> reservas = new ArrayList<>(); // reservas de viaje de la terminal
	private static ArrayList <Viaje> viajes = new ArrayList<>(); // viajes disponibles en la terminal
	private static ArrayList <Viaje> historial = new ArrayList<>(); // viajes realizados por la terminal
	private static ArrayList <Viaje> viajesEnCurso = new ArrayList<>(); // viajes en curso en la terminal
	private ArrayList <Destino> destinos = new ArrayList<>(); // destinos de la terminal 
	private ArrayList <Vehiculo> vehiculosTerminal;
	public final double comision; // comision que cobra la terminal a las transportadoras por dejarle prestar sus servicios, está será por cada viaje que realicen las transportadoras
	private Destino ubicacion; // ubicación de la terminal 
	private Persona administrador; // Administrador de la terminal 
	
	// Constructor Clase Terminal
	public Terminal(String nombre, double dinero, int capacidadVehiculos, int cantidadSedes, int cantidadVehiculos, ArrayList <Transportadora> transportadoras,
					ArrayList <Viaje> viajes, ArrayList <Viaje> viajesEnCurso, ArrayList <Destino> destinos, double comision, Destino ubicacion, Persona administrador) {
	
		this.nombre = nombre;
		this.dinero = dinero;
		this.capacidadVehiculos = capacidadVehiculos;
		this.cantidadVehiculos = cantidadVehiculos;
		this.transportadoras = transportadoras;
		Terminal.viajes = viajes;
		this.reservas = new ArrayList<Viaje>();
		this.historial = new ArrayList<Viaje>();
		this.viajesEnCurso = viajesEnCurso;
		this.destinos = destinos;
		this.comision = comision;
		this.ubicacion = ubicacion;
		this.administrador = administrador;
		this.vehiculosTerminal = new ArrayList<> (); //Inicialización de la lista de vehiculos
		Terminal.cantidadSedes++;
	}
	
	// Constructor sobrecarga
	public Terminal(String nombre, double dinero, int capacidadVehiculos, int cantidadSedes, int cantidadVehiculos, ArrayList <Transportadora> transportadoras,
			       ArrayList <Destino> destinos, double comision, Destino ubicacion) {
		
		this.nombre = nombre;
		this.dinero = dinero;
		this.capacidadVehiculos = capacidadVehiculos;
		this.cantidadVehiculos = cantidadVehiculos;
		this.transportadoras = transportadoras;
		Terminal.viajes = new ArrayList<Viaje>();
		this.reservas = new ArrayList<Viaje>();
		this.historial = new ArrayList<Viaje>();
		this.viajesEnCurso = new ArrayList<Viaje>();
		this.destinos = destinos;
		this.comision = comision;
		this.ubicacion = ubicacion;
		this.vehiculosTerminal = new ArrayList<> (); //Inicialización de la lista de vehiculos
		//this.administrador = administrador;
		Terminal.cantidadSedes++;
}
	/**
	 * Este método verifica la disponibilidad de cada una de 
	 * las transportadoras dentro de la terminal para realizar 
	 * un viaje con un destino selecionado
	 * @input String, Nombre del destino deseado.
	 * @return ArrayList<Transportadora>, transporatadoras con disponibilidad.
	 */
	
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
	

	
	public Viaje programarViaje (Destino destino, Vehiculo vehiculo, String fecha, String hora, Destino salida) {  // Teniendo en cuenta el tipo de Vehiculo --- Habra otro que tiene en cuenta cierta capacidad y el costo del vaje
		for (Transportadora t : this.getTransportadoras()) { 		// Verfificar las transportadoras que ofrecen este destino 
			for (Destino d : t.getDestinos()) {
				if (d.equals(destino)) {
					for (Vehiculo v : t.getVehiculos()) {		    // Verfificar el tipo de vehiculo requerido
						if(vehiculo.getTipo().equals(v.getTipo()) && v.disponibilidad()) {
							Conductor conductorSeleccionado = null;  // Se pueden agregar mas condiciones para esta selección 
							for (Conductor c : v.getConductores()) { // Añadir la verificacion de la lista de horario del conductor para verificar loa disponiblidad Formato String "8:30 4/08/2024"
								String cadena = hora+fecha;
								if (c.getEstadoLicencia() ) {  // && c.getHorario().contains(cadena) Cambiar la manera de asignar el horario al conductor automaticamente esta cadena cuando se crea un viaje puede ser un metodo
									conductorSeleccionado = c;
									break;  // Selecciona el primer conductor disponible
								}
							}
							
							if (conductorSeleccionado != null) {
								Viaje nuevoViaje = new Viaje(t.getTerminal(), hora, fecha, v, conductorSeleccionado, d, null, salida); // Creacion del Viaje // --- plantear la posibilidad de eliminar el dia del constructor y que las transportadoras tengan un destino como ubicacion
								return nuevoViaje;  //El viaje no se perdera pues este ya tiene apunadores desde la clase Vehiculo, Transportadora, Conductor, Viaje. 
								
							} else {return null; } // Hay un problema con el conductor asignado --- Volver a programar automaticamente 

						} else {return null; } // No hay del mismo tipo de vehiculo
					}
					
				} else { return null; }  // No hay Transportadoras con ese destino
			}
		}
		return null; // No hay ese destino
	}
	
	public void cancelarViaje(Viaje viaje) {  // Puede devolver un String con una retroalimentacion de la operacion que se realizo
		 ArrayList <Pasajero> pasajeros = viaje.getPasajeros(); // Debemos tener en cuenta que el viaje debe ser reprogramado, reubicar sus pasajeros o la devolucion del dinero. 
		 boolean reubicados = false; // Permitira saber cual proceso fue exitoso 
		 
		 for (Viaje v : Terminal.viajes) { //Buscar en la lista de viajes disponibles un viaje que cumpla las caracteristicas para reubicar
			 if (viaje.isequals(v) == false) { // Primero debemos excluir el viaje que se va a cancelar
				 if (v.getSalida().equals(viaje.getSalida()) && v.getLlegada().equals(viaje.getLlegada()) &&  viaje.getAsientosDisponibles() <= v.getAsientosDisponibles()) { // Verificar que la salida y la llegada sean las mismas y Verificar la capacidad de los vehiculos 
					 v.setTarifa(viaje.getTarifa()); // Respeta la tarifa del viaje anterior 
					 v.getPasajeros().addAll(pasajeros); // Mover los pasajeros al nuevo viaje y elminar
					 
					 viaje.programacionAutomatica(); // Ejecutar la autoprogramación para no perder la continuidad y eliminar el viaje
					 reubicados = true;
					 break; // Terminar la operacion despues de finalizar el proceso
				 } 
			 }
		 }
		 
		 if (reubicados == false) {  // Devolución del dinero debido a que no se encontro un viaje con las condiciones requeridas 
			 for (Pasajero p : pasajeros) {
				 p.aumentarDinero((int)viaje.getTarifa()); // DEFINIR EL DINERO COMO DOUBLE O IMPLEMENTAR ALGUNA REGLA PARA DEDONDEAR
			 }
			 
		 }
		 viaje.programacionAutomatica();
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
		}
		else {
			capacidad = false;
		}
		return capacidad;
	}
	
	public void realizarReserva() {   // Se pasara a la clase pasajero, pues tiene sentido que sea el pasajero quien realiza la reserva, es decir yo como objeto psajero soy el que conozco a donde quiero ir 
		
		// Implementación pendiente
		
	}
	
	/**
	 * Deniega una reserva eliminando el viaje asociado y actualizando la lista de reservas.
	 * 
	 * @param id El identificador del viaje asociado a la reserva que se desea denegar.
	 * 
	 * El método realiza los siguientes pasos:
	 * 1. Busca el viaje con el identificador especificado en la lista de viajes de la terminal.
	 * 2. Si el viaje se encuentra, se procede a cancelar el viaje, lo que mantiene la continuidad del viaje y elimina la reserva.
	 * 3. Luego, se elimina el viaje de la lista de reservas.
	 * 
	 */
	
	public void denegarReserva(int id) { // Nota: Si el viaje no se encuentra, se debe considerar cómo informar al usuario o manejar el error adecuadamente. 
		Viaje reserva = null;
		
		for (Viaje viaje : this.getViajes()) {
			if (viaje.getId() == id) {
				reserva = viaje;
				break;
			}
		}
//		if (reserva == null) {
//			// Falta cambiar el tipo de retorno del metodo para que permita saber si la reserva no esta en la lista
//		}
		this.cancelarViaje(reserva);  // Mantiene la continuidad del viaje y ademas elimina la reserva
		this.getReservas().remove(reserva); // Lo elimina de la lista de reserva		
	}
	
	public ArrayList <Factura> obtenerFinanzas(){
		
		// Implementación pendiente 
		
		return null;
		
		
	}
	
	public String estadoViaje() { // Se paso a a la clase viaje pues yo como objeto viaje soy el que conozco mi estado 
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void asignarViaje() {
		
		// Implementación pendiente
		
		
	}
	
	public void designarViaje() {
		
		// Implementación pendiente
		
		
	}
	 
	
	/**
	 * Agrega un nuevo vehiculo a la lista de la terminal
	 * @param vehiculo a agregar
	 */

	public void agregarVehiculoTerminal (Vehiculo vehiculo) {
		
		this.vehiculosTerminal.add(vehiculo);
		this.capacidadVehiculos --;
		
	}
	/**
	 * Remueve el vehiculoeleido de la lista de vehiculos de la terminal
	 * @param vehiculo a remover
	 */
	
	public void removerVehiculoTerminal (Vehiculo vehiculo) {
		
		this.vehiculosTerminal.remove(vehiculosTerminal.indexOf(vehiculo));
	}
			
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Retorna la lista de vehiculos de la terminal
	 * @return Vehicuos de la terminal
	 */
	public ArrayList<Vehiculo> getVehiculosTerminal (){
		
		return (this.vehiculosTerminal);
	}
	
	/**
	 * Establece la lista de vehiculosTerminal
	 * @param vehiculos: Lista de vehiculos de la terminal
	 */
	
	public void setVehiculosTerminal (ArrayList<Vehiculo> vehiculos) {
		
		this.vehiculosTerminal = vehiculos;
	}
	
	
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
	
	public void setDinero (double dinero) {
		
		this.dinero = dinero;
		
	}
	
	/**
	 * Método que nos sirve para obtener el dinero de la terminal.
	 * @return el dinero de la terminal.
	 */
	
	public double getDinero() {
		
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
	
	public static void setTransportadoras(ArrayList <Transportadora> transportadoras) {
		
		Terminal.transportadoras = transportadoras;
		
	}
	
	/**
	 * Método que nos permite obtener a las transportadoras asociadas a la terminal.
	 * @return lista de las transportadoras asociadas a la terminal.
	 */
	
	public static ArrayList <Transportadora> getTransportadoras(){
		
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
	
	public static void setViajes(ArrayList <Viaje> viajes) {
		
		Terminal.viajes = viajes;
		
	}
	
	/**
	 * Método para obtener la lista con los viajes asociados a la terminal.
	 * @retunr lista de viajes asociados a la terminal.
	 */
	
	public static ArrayList <Viaje> getViajes(){
		
		return viajes;
	
	}
	
	/**
	 * Establece o modifica los viajes en curso asocaidos a la terminal.
	 * @param viajes, lista con los viajes en curso asociados a la terminal
	 */
	
	public static void setViajesEnCurso(ArrayList <Viaje> viajesEnCurso) {
		
		Terminal.viajesEnCurso = viajesEnCurso;
		
	}
	
	/**
	 * Método para obtener la lista con los viajes en curso asociados a la terminal.
	 * @retunr lista de viajes en curso asociados a la terminal.
	 */
	
	public static ArrayList <Viaje> getViajesEnCurso(){
		
		return Terminal.viajesEnCurso;
	
	}
	
	/**
	 * Establece o modifica la lista del historial de Viajes asociado a la terminal.
	 * @param destinos, lista con los destinos asociados a la terminal.
	 */
	
public static void setHistorial(ArrayList <Viaje> historial) {
		
		Terminal.historial = viajesEnCurso;
		
	}
	
	/**
	 * Método para obtener la lista con el Historial de viajes de la terminal.
	 * @retunr lista de viajes realizados por la terminal.
	 */
	
	public static ArrayList <Viaje> getHistorial(){
		
		return Terminal.historial;
	
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
	
	/**
	 * Método para obtener el valor de la comisión que le cobra la terminal a la transportadora.
	 * @return la comisión que le cobra la terminal a la transportadora.
	 */
	
	public double getComision() {
		
		return this.comision;
		
		
	}
	
}