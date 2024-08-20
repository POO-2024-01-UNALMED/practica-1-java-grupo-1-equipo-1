package gestorAplicacion.usuarios;
import java.util.ArrayList;
import gestorAplicacion.administrativo.Vehiculo;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Incentivo;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Factura;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a un conductor, el cual tiene una licencia, un vehículo asociado, una transportadora asociada y una lista con su horario
 *  de vijes, esta clase servirá para hacer interacciones como asociar un conductor a un viaje, remover un conductor de un viaje, asociarle un
 *  vehículo, quitarle un vehículo y ver su disponibilidad para realizar un viaje. 
 */

public class Conductor extends Persona {
	
	//Atributos
	
	private static final long serialVersionUID = 1L;
	private boolean estadoLicencia; // Estado de la licencia del conductor
	private Vehiculo vehiculo; // Vehículo asociado al conductor
	private Transportadora transportadora; // Transportadora asociada al conductor 
	private ArrayList <Viaje> horario = new ArrayList<>(); // Horario de viajes del conductor
	private static ArrayList <Conductor> conductores = new ArrayList<>();
	private int numeroDePagosRecibidos;
	
	public Conductor() { //Se agrego para facilitar las pruebas
	}

	/**
	 * Constructor con parametros de Conductor.
	 * @param estadoLicencia, el estado de licencia asociado al conductor.
     * @param vehiculo, el vehiculo asociado al conductor.
     * @param transportadora, la transportadora asociada al conductor.
     * @param horario, la lista con el horario de viajes asociados al conductor
     * 
     */
	
	/*public Conductor(boolean estadoLicencia, Vehiculo vehiculo, Transportadora transportadora, ArrayList<Viaje> horario) {
		
		super();
		this.estadoLicencia = estadoLicencia;
		this.vehiculo = vehiculo;
		this.transportadora = transportadora;
		this.horario = horario;
		
	}*/
	
	
	/**
	 * Constructor con parametros de Persona y Conducto
	 * @param id del conductor.
	 * @param edad del conductor.
	 * @param nombre del conductor.
	 * @param genero del conductor.
	 * @param historial del conductor.
	 * @param experiencia del conductor.
	 * @param dinero del conductor.
	 * @param estadoLicencia del conductor
	 * @param vehiculo del conductor
	 * @param transportadora del conductor.
	 * @param horario del conductor.
	 */

	public Conductor(int id, int edad, String nombre, char genero, ArrayList<Viaje> historial, int experiencia,
			double dinero, boolean estadoLicencia, Vehiculo vehiculo, Transportadora transportadora, ArrayList<Viaje> horario, 
			ArrayList<Factura> facturas, int diasRestantesContr, int diasTrabajados) {
		super(id, edad, nombre, genero, historial, experiencia, dinero, facturas, diasRestantesContr, diasTrabajados);
		this.estadoLicencia = estadoLicencia;
		this.vehiculo = vehiculo;
		this.transportadora = transportadora;
		this.horario = horario;
		this.numeroDePagosRecibidos = this.getFacturas().size();
		Conductor.conductores.add(this);
	}
	
	

	public void conducir() {
		
		// Implementación pendiente
		
		
	}
	
	public String notificarLllegada() {
		
		// Implementación pendiente (falta el tiempo)
		
		return null;
		
	}
	
	public void finalizarViaje() {
		
		// Implementación pendiente
		
		
	}
	
	public ArrayList <Factura> reporteFinanciero(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	/** 
	 * Metodo para renovar el contrato del conductor en el 
	 * cual se modifica el diccionario contrato en la clave conductor 
	 * @return void*/
	
	public void renovarContraro() {
		
		//Implementación pendiente
		
	}
	
	public ArrayList <Viaje> consultarHorario(){
		
		// Implementación pendiente. Nota: es similar a un get
		
		return null;
		
		
	}
	
	public void reportarProblema() {
		
		// Implementación pendiente
		
		
	}
	
	/**
	 * Metodo que le da un bono de bienvenida al conductor contratado
	 * @param transportadora a la que se le descontara el dinero*/
	
	public void bonoBienvenida(Transportadora t) {
		this.dinero += 10000.00;
		t.setDinero(t.getDinero() - 10000.00);
	}
	
	
	/** 
	 * Metodo para asociar un conductor al vehiculo que
	 * va de parametro si el vehiculo tiene menos de 5 conductores.
	 * @param Vehiculo, vehiculo al que se quiere asociar un conductor*/
	
	
	public void tomarVehiculo(Vehiculo vehiculo) {
		
		//Se le pone momentaneamente un limite de 3 conductores por vehiculo
		if (vehiculo.getConductores().size() < 3) {
			vehiculo.asociarConductor(this);
			this.vehiculo=vehiculo;
		}
		
	}
	
	/** 
	 * Metodo para quitar el vehiculo si no tiene viajes programados
	 * @return void*/
	
	public String quitarVehiculo() {
		
		if (this.horario.size() == 0) {
			
			if (vehiculo.getConductores().size() >= 2) {
				this.vehiculo.quitarConductor(this.id);
				this.vehiculo=null;
				return "Se ha desvinculado el vehiculo a " + this.nombre;
			} else {
				return "No se ha podido desvincular el vehiculo a " + this.nombre;
			}
		} else {
			return "No se ha podido desvincular el vehiculo a " + this.nombre;
		}
		
		
	}
	
	/**
	 * Metodo para desvincular el vehiculo al conductor y desvincular
	 * al conductor el vehiculo*/
	
	public void eliminarVehiculo() {
		
		this.vehiculo.quitarConductor(this.id);
		this.vehiculo = null;
	}
	
	@Override
	public String identificarse() {
	
		return "Soy un conductor, mi nombre es " + this.getNombre() + " mi estado de licencia es " + this.getEstadoLicencia() + " y estoy asociado a la transportadora " + this.getTransportadora().getNombre();
	}
	
	
	/**
	 * Método que nos sirve para descontarle al conductor cierta cantidad de dinero por el valorAseguradora que le cobra la transportadora
	 
	 */
	
	@Override
	public void descuento() {
		
		double valorAseguradora = 20000; 
		
		this.dinero -= valorAseguradora;
		this.getTransportadora().setDinero(this.getTransportadora().getDinero() + valorAseguradora);
		
	}
	
	/**
	 * Método que nos sirve para calcular la bonificación que le hace la transportadora a un conductor
	 * teninedo en cuenta el número de viajes que este haya realizado para dicha transportadora
	 
	 */
	
	@Override
	public void bonificacion() {
		
		int numeroDeViajesRealizados = 0;
		double dineroTransportadora = this.getTransportadora().getDinero();
		
		for (Viaje v: this.getTransportadora().getViajesTerminados()) {
			
			if (this.id == v.getConductor().getId()) {
				
				
				numeroDeViajesRealizados += 1;
				
			}
			
			if (numeroDeViajesRealizados < 10) {
				
				this.dinero += Incentivo.incentivoBase;
				this.getTransportadora().setDinero(dineroTransportadora - Incentivo.incentivoBase);
				
			}
			
			else if(numeroDeViajesRealizados > 10) {
				
				
				this.dinero += Incentivo.incentivoBase *2;
				this.getTransportadora().setDinero(dineroTransportadora - (Incentivo.incentivoBase*2));
			}
			
			
				
			
		}
		
		
	}
	
	/**
	 * Metodo para saber si el conductor tiene un vehiculo
	 * @return boolean*/
	
	public boolean tieneVehiculo() {
		if (vehiculo == null) {
			return false;
		} else {
			return true;
		}
			
	}
	
	/**
	 * Metodo para renovar el contrato del conductor
	 * @param dias , dias que se le sumaran a los dias restantes de contrato*/
	
	@Override
	protected void renovarContrato(int dias) {
		this.diasRestantesContr += dias;
	}

	/**
	 * Metodo para indemnizar el conductor al despedirlo*/
	
	public void indemnizar() {
		this.dinero += (this.diasRestantesContr * 0.5); //El dinero que se le devolvera todavia no esta definido
	}
	
	/**
	 * Metodo para agregarle un dia trabajado al conductor*/
	
	public void sumarUnDiaTrabajado() {
		this.diasTrabajados++;
	}
	
	/**
	 * Metodo para reiniciar algunos atributos del conductor, 
	 * este se usara cuando se despida o contrate un conductor
	 * para que no haya errores en un futuro*/
	
	public void reinicioAtributos() {
		this.horario = new ArrayList<>();
		this.diasRestantesContr = 0;
		this.diasTrabajados = 0;
		this.historial = new ArrayList<>();
	}
	
	/**
	 * Metodo que devuelve un string con la lista de los viajes que tiene
	 * asignados el conductor
	 * @return string con la lista de viajes*/
	
	public String mostrarViajes() {
		String mensaje = "";
		for (Viaje viaje : getHorario()) {
			mensaje += viaje.detallesViaje();

		}
		return mensaje;
	}
	
	/**
	 * Metodo para desvincular un viaje al conductor que invoco el metodo
	 * y vincular el viaje al conductor que va de parametro
	 * @param conductor, conductor al que se le vinculara el viaje
	 * @param viaje, el viaje que se le vinculara al conductor de parametr
	 * */
	
	public void desvincularYVincular(Conductor c,Viaje v) {
		
		c.getHorario().add(v);
		v.setConductor(c);
		v.setVehiculo(c.getVehiculo());
		this.getHorario().remove(v);
		
		
	}
	
	/**
	 * Metodo para vincular un viaje al conductor que invoco el metodo
	 * y desvincular el viaje al conductor que va de parametro
	 * @param conductor, conductor al que se le desvinculara el viaje
	 * @param viaje, el viaje que se le desvinculara al conductor de parametro
	 * */
	
public void vincularYDesvincular(Conductor c,Viaje v) {
		
		this.getHorario().add(v);
		v.setConductor(this);
		v.setVehiculo(this.getVehiculo());
		c.getHorario().remove(v);
		
		
	}

/**
 * Metodo para saber  un viaje al conductor que invoco el metodo
 * y desvincular el viaje al conductor que va de parametro
 * @param conductor, conductor al que se le desvinculara el viaje
 * @param viaje, el viaje que se le desvinculara al conductor de parametro
 * */

public boolean disponibleFecha(String fecha) {
	boolean disponible = true;
	if (this.getHorario()!=null) {
		for (Viaje viaje : this.getHorario()){
			if (viaje.getFecha().equals(fecha)) {
				disponible = false;
				return disponible;
			}
		}
	}
	return disponible;
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
	
	public static ArrayList <Conductor> getConductores(){
		
		
		return Conductor.conductores;
	}
	
	public int getNumeropagosRecibidosConductor() {
		
		
		return this.numeroDePagosRecibidos;
		
	}
	
	
	
	
	
	
	


}
