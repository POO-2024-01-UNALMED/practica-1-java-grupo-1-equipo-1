package gestorAplicacion.administrativo;
import java.util.ArrayList;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Persona;
import gestorAplicacion.constantes.Incentivo;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una transportadora, la cuál tiene su respectivo nombre, dinero, una lista de conductores, pasajeros, vehículos, viajes,
 *  un destino asiginado y una terminal, esta clase nos servirá para organizar los distitnos viajes, asociar a los conductores, hacerle su respectivo
 *  pago y monitorear a los distintos vehículos asociados a esta.
 */

public class Transportadora implements Incentivo {
	
	// Atributos
	
	private String nombre; // Nombre de la transportadora
	private double dinero; // Dinero de la transportadora 
	private ArrayList <Conductor> conductores = new ArrayList<>(); // Lista de conductores de la transportadora
	private ArrayList <Persona> conductoresDespedidos = new ArrayList<>(); // Lista de conductores despedidos de la transportadora 
	private ArrayList <Pasajero> pasajeros = new ArrayList<>(); // Lista de pasajeros de la transportadora
	private ArrayList <Vehiculo> vehiculos = new ArrayList<>(); // Lista de vehículos asociados a la transportadora
	private ArrayList <Viaje> viajesAsignados = new ArrayList<>(); // Viajes de la transportadora
	private Destino destinoAsignado; // Destino asignado a la transportadora, lugar hacia donde esta viajará
	private Terminal terminal; // Terminal en donde opera la transportadora
	private ArrayList <Destino> destinos = new ArrayList<>(); // ???
	private ArrayList <Viaje> viajesTerminados = new ArrayList<>(); // Viajes que ha concluido la terminal
	private Persona dueño; // Dueño de la transportadora
	private final double estrellas; // Permite calcular la tarifa de los buses
	
	//Constructor con todos los parámetros
	
	public Transportadora(String nombre, double dinero, ArrayList<Conductor> conductores, ArrayList<Pasajero> pasajeros,
			ArrayList<Vehiculo> vehiculos, ArrayList<Viaje> viajeAsignado, Destino destinoAsignado, Terminal terminal,
			ArrayList<Destino> destinos, ArrayList<Viaje> viajesTerminados, double estrellas) {
		
		this.nombre = nombre;
		this.dinero = dinero;
		this.conductores = conductores;
		this.pasajeros = pasajeros;
		this.vehiculos = vehiculos;
		this.viajesAsignados = viajeAsignado;
		this.destinoAsignado = destinoAsignado;
		this.terminal = terminal;
		this.destinos = destinos;
		this.viajesTerminados = viajesTerminados;
		this.estrellas = estrellas;
		
	}
	
	//Dejarlo mientras las Pruebas 
	public Transportadora() {
		this.estrellas = 1;		
	}

	/**
	 * Método que nos sirve para mostrar un String con la información de una factura que esté asociada 
	 * con el dueño de una transportadora.
	 * @param factura, la factura que se quiere mostarr 
	 * @return String con la información de la factura en caso de que sea encontrada, de lo contrario se retorna que el objeto no posee la factura
	 */

	public String mostrarFactura(Factura factura) {
				
				// hablar para que la transportadora tenga dueño y ese dueño sea el encargado de las facturas
			if (dueño.getFacturas().contains(factura)) {
					
					return "Numero de su factura: " + factura.getNumeroFactura() + 
							"\nTotal:" + factura.getTotal() +
							"\nTerminal: " + factura.getTerminal();
					
				}
				
				return this.dueño.getNombre() + "no tiene dicha factura";
			}
		
	
	/**
	 * Metodo para contratar un conductor el cual se agregara
	 * a la lista de conductores de la transportadora.
	 * @param Conductor a contratar
	 */
	
	public void contratarConductor(Conductor conductor) {
		
		this.getConductores().add(conductor);
		conductor.reinicioAtributos();
		
	}
	
	/**
	 * Metodo para despedir conductor al cual se le remueve el vehiculo,
	 *  se le remueve de la lista de conductores de la transportadora y 
	 * se agrega a la lista de conductores despedidos elimina el conductor.
	 * Para esto, primero se verifica que no tenga viajes programados y 
	 * que su vehiculo tenga almenos 2 conductores. Si existe algun inconveniente
	 * el metodo devolvera un valor diferente para cada caso.
	 * @param Conductor a despedir*/
	
	public String despedirConductor(Conductor conductor) {
		
		if (conductor.getHorario().size() == 0) {
			if (conductor.getVehiculo() == null) {
				conductor.quitarVehiculo();
				int indiceConductor = conductor.getTransportadora().getConductores().indexOf(conductor);
				conductor.getTransportadora().getConductores().remove(indiceConductor);
				conductor.getTransportadora().getConductores().add(conductor);
				conductor.indemnizar();
				conductor.reinicioAtributos();
				return "No problem";
				}
			if (conductor.getVehiculo().getConductores().size() >= 2 ) {
				conductor.quitarVehiculo();
				int indiceConductor = conductor.getTransportadora().getConductores().indexOf(conductor);
				conductor.getTransportadora().getConductores().remove(indiceConductor);
				conductor.getTransportadora().getConductores().add(conductor);
				conductor.indemnizar();
				conductor.reinicioAtributos();
				return "No problem";
				} else return "No es posible porque no hay mas conductores asigandos al vehiculo asociado al conductor";
			} else return "No es posible porque el conductor tiene viajes programados";
				
			}
	
	
	/**
	 * Metodo que nos permite calcular el dinero que la transportadora le debe pagar a la terminal
	 * dicha terminal cobra cierta comisión por cada viaje que realiza la transportadora
	 * por ende se contarán los viajes que realizó exitosamente la terminal 
	 * y ese valor se multiplicará por la comisión
	 */
	
	public boolean pagarTerminal() {
		
		boolean pago = false; // Nos servirá para saber si el pago fué efectuado o no
		
		double calcularValorAPagar = this.terminal.getComision()*this.getViajesTerminados().size();
		
		if (this.getDinero() > calcularValorAPagar) {
			
			this.dinero -= calcularValorAPagar;
			
			this.terminal.setDinero(calcularValorAPagar); // Se le pasa el dinero a la Terminal ya que la transportadora pagó su comisión
			
			pago = true;
			
			return pago;
		}
		
		else {
			
			 
			return false;
			
		}
			
			
			
			
		}
		
		
		
		
	
	
	/**
	 * Verifica la capacidad de la terminal y el dinero de la transportadora para poder agregar un vehiculo
	 * @param vehiculo a comprar
	 */
	
	public void agregarVehiculo(Vehiculo vehiculo) {
		
		if (vehiculo.getPrecio() <= this.dinero && this.terminal.getCapacidadVehiculos() > this.terminal.getCantidadVehiculos()) {
			
			this.vehiculos.add(vehiculo);
			this.terminal.agregarVehiculoTerminal(vehiculo);
			
		}
		
	}
	
	/**
	 * Remueve un vehiculo de la lista de vehiculos de la transportadora
	 * @param vehiculo a remover
	 */
	
	public void removerVehiculo(Vehiculo vehiculo) {
			
		vehiculos.remove(vehiculos.indexOf(vehiculo));
		this.terminal.removerVehiculoTerminal(vehiculo);
		
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
	
	/**
	 * Paga x cantidad de dinero a una persona
	 * @param persona a pagar
	 * @param cantidad de dinero a pagar
	 */
	
	public void pagarPersona(Persona persona, int cantidad) {
		
		
		this.reducirDinero(cantidad);
		persona.aumentarDinero(cantidad);
		
		
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
	
	/**
	 * Aumenta el dinero de la transportadora
	 * @param dinero a aumentar
	 */
	
	public void aumentarDinero (double dinero) {
		
		this.dinero = this.dinero + dinero;
	}
	
	/**
	 * Reduce el dinero de la terminal
	 * @param dinero a reducir
	 */
	
	public void reducirDinero (double dinero) {
		
		this.dinero = this.dinero - dinero;
	}
	
	/**
	 * Metodo para calcular el dinero de la transportadora
	 * teniendo en cuenta la cantidad de pasajeros transportados y la tarifa asociada al viaje
	 * 
	 */
	
	public void calcularDineroTransportadora() { 
		
		int cantPasajerosTransportados = 0; 
		int tarifaViajes = 0; 
		
		for (Viaje v: viajesTerminados) {
			
			if (v.getPasajeros().size()>0) { 
				
			cantPasajerosTransportados += v.getPasajeros().size(); 
			tarifaViajes += v.getTarifa(); 
			
			} 
		} 
		
		this.dinero = cantPasajerosTransportados * tarifaViajes; 
		
	}
	
	@Override
	public void descuento (double porcentaje) {
		
		
	}
	
	@Override
	public void bonificacion(double premio) {
		
	}
	
	/**
	 * Metodo para que devolvera un string con la lista de
	 * conductores activos de una determinada trasportadora
	 * @return string con la lista de conductores activos*/
	
	public String mostrarConductActivos() {
		String mensaje = "";
		for (Conductor conductor : this.conductores) {
			mensaje += "Nombre: " + conductor.getNombre()+ "  #Cedula: " + conductor.getId() + "\n";
		}
		return mensaje;
	}
	
	/**
	 * Metodo para despedir un conductor de la lista de conductores
	 * activos con su id.
	 * @param id , id del conductor a despedir*/

	public void despedirConductDesdeLista(int id) {
		for (Conductor conductor : this.conductores) {
			if (conductor.getId() == id) {
				despedirConductor(conductor);
				return;
			}
		}
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
	
	public void setDinero(double dinero) {
		
		this.dinero = dinero;
		
	}
	
	/**
	 * Método para obtener el dinero de la transportadora.
	 * @return el dinero de la transportadora.
	 */
	
	public double getDinero() {
		
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
	
	public ArrayList <Vehiculo> getVehiculos(){
		
		return vehiculos;
		
	}
	/**
	 * Establece o modifica la lista de los viajes asignados a la transportadora.
	 * @param viajeAsignado, lista con los viajes asignados a la transportadora.
	 */
	
	public void setViajesAsignados(ArrayList <Viaje> viajesAsignados) {
		
		this.viajesAsignados = viajesAsignados;
		
	}
	
	/**
	 * Método para obtener la lista de los viajes asignados a la transportadora.
	 * @return lista de los viajes asignados a la transportadora.
	 */
	
	public ArrayList <Viaje> getViajesAsignados(){
		
		return viajesAsignados;
		
	}
	
	/**
	 * Método para obtener el destino asignado a la transportadora.
	 * @return el destino asignado a la transportadora.
	 */
	
	
	public Destino getDestinoAsignado() {
		
		return destinoAsignado;
		
	}
	
	/**
	 * Establece o modifica el destino asignado a la transportadora.
	 * @param destinoAsignado.
	 */
	
	public void setDestinoAsignado(Destino destinoAsignado) {
		
		this.destinoAsignado = destinoAsignado;
		
	}
	
	/**
	 * Establece o modifica la terminal asociada a la tranportadora.
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
	
	/**
	 * Método para obtener los destinos a la cuál está asociada la transportadora.
	 * @return los destinos asociados a la transportadora.
	 */

	public ArrayList<Destino> getDestinos() {
		return destinos;
	}
	
	/**
	 * Establece o modifica los destinos asociados a la tranportadora.
	 * @param los destinos asociados a la transportadora.
	 */
	

	public void setDestinos(ArrayList<Destino> destinos) {
		this.destinos = destinos;
	}
	
	/**
	 * Establece o modifica el dueño de la transportadora.
	 * @param dueño, el dueño de la transportadora.
	 */
	
	public void setDueño(Persona dueño) {
		
		this.dueño = dueño;
		
	}
	
	/**
	 * Método para obtener el dueño de la tranportadora.
	 * @return el dueño de la transportadora.
	 */
	
	public Persona getDueño() {
		
		return dueño;
		
	}

	/**
	 * Método para obtener los conductores despedidos.
	 * @return conductores despedidos de la transportadora
	 */
	
	public ArrayList<Persona> getConductoresDespedidos() {
		
		return conductoresDespedidos;
		
	}

	/**
	 * Establece o modifica los conductores despedidos.
	 * @param conductores despedidos de la transportadora.
	 */
	
	public void setConductoresDespedidos(ArrayList<Persona> conductoresDespedidos) {
		
		this.conductoresDespedidos = conductoresDespedidos;
		
	}
	
	/**
	 * Devuelve la cantidad de estrellas de la transportadora
	 * @return Numero de estrellas
	 */

	public double getEstrellas() {
	
		return estrellas;
	}
	
	/**
	 * Devuelve la cantidad de viajes terminados de la transportadora
	 * @return Cantidad de viajes de la transportadora
	 */
	
	public ArrayList<Viaje> getViajesTerminados(){
		
		return viajesTerminados;
		
	}
	
}
