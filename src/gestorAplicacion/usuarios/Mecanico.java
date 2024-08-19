package gestorAplicacion.usuarios;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.constantes.Incentivo;

import java.util.ArrayList;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a un mecánico, el cuál nos servirá para reparar los vehículos, dicho mecánico estará asociado a un taller y tendrá un vehículo
 *  asignado, el cuál tendrá que reparar, tendrá un historial con los vehículos reparados y un estado para saber si está disponible o no para arreglar
 *  un vehículo.
 */

public class Mecanico extends Persona{
	
	// Atributos
	
	private static final long serialVersionUID = 1L;
	private Taller taller; // Taller al cuál pertenece el mecánico
	private ArrayList <Vehiculo> historialReparados; // Historial de vehículos reparados por el mecánico
	private Boolean estado; // Estado del mecánico
	private ArrayList <Vehiculo> vehiculosReparando; // Vehiculos en la cola de reparacion de dicho mecanico
	private static ArrayList <Mecanico> mecanicos = new ArrayList<>(); // Lista de todos los mecanicos

	
	// Constructor por defecto
	public Mecanico () {
		
		this.experiencia = 1;
	}

	/**
	 * Constructor para la clase mecánico
     * @param taller, el taller asociado al mecánico.
     * @param vehiculoAsignado, el vehiculo asignado al mecánico.
     * @param estado, el estado de disponibilidad del mecánico.
     * 
     */
	
	public Mecanico(int id, int edad, String nombre, char genero, ArrayList <Viaje> historial, int experiencia,
			double dinero, ArrayList <Factura> facturas, Taller taller, int diasRestantesContr, int diasTrabajados) {
		
		super(id, edad, nombre, genero, historial, experiencia, dinero, facturas, diasRestantesContr, diasTrabajados);
		this.taller = taller;
		this.estado = true;
		this.historialReparados = new ArrayList<Vehiculo>();
		this.vehiculosReparando = new ArrayList <Vehiculo> ();
		taller.agregarMecanico(this);
		mecanicos.add(this);
		this.experiencia = 1;
	}
	
	public Mecanico(int id, int edad, String nombre, char genero, ArrayList <Viaje> historial, int experiencia,
			double dinero, ArrayList <Factura> facturas, Taller taller, Vehiculo vehiculoAsignado, boolean estado, int diasRestantesContr, int diasTrabajados) {
		
		super(id, edad, nombre, genero, historial, experiencia, dinero, facturas, diasRestantesContr, diasTrabajados);
		this.taller = taller;
		this.estado = true;
		this.historialReparados = new ArrayList<Vehiculo>();
		this.vehiculosReparando = new ArrayList <Vehiculo> ();
		
		if (taller != null) {
			
			taller.agregarMecanico(this);
		}
		
		mecanicos.add(this);
		this.experiencia = 1;
	}

	
	
	@Override
	public String identificarse() {
		
		// Implementación pendiente
		
		
		
		return null;
	
	}
	
	public void agregarVehiculoCola (Vehiculo vehiculo) {
		
		this.vehiculosReparando.add(vehiculo);
	}
	
	
	public void repararVehiculo(Vehiculo vehiculo) {
		
		vehiculo.reparacion();
		this.vehiculosReparando.remove(vehiculo);
		this.taller.removerVehiculoReparacion(vehiculo);
		this.historialReparados.add(vehiculo);
		this.calcularExperiencia();
		
	}
	
	public void calcularExperiencia() {
		
		if (this.experiencia < 50 && this.historialReparados.size() % 10 == 0) {}
		
		this.experiencia = this.experiencia / 5;
		
	}
	
	@Override
	public void renovarContrato (int dias) {
		
	}
	
	/**
	 * Método que nos sirve para calcular la bonificación que le hace la transportadora al conductor
	 * teninedo en cuenta su cantidad de vehículos reparados
	 
	 */
	
	@Override
	public void descuento() {
		
		double dineroTransportadora = this.getTaller().getTransportadora().getDinero();
		
		if (this.getHistorialReparados().size()> 10) {
			
			this.dinero += Incentivo.incentivoBase;
			
			this.getTaller().getTransportadora().setDinero(dineroTransportadora - Incentivo.incentivoBase);
			
		}	
	}
	
	/**
	 * Método que nos sirve para calcular la bonificación que le hace la transportadora al conductor
	 * teninedo en cuenta su gran experiencia
	 
	 */
	
	@Override
	public void bonificacion() {
		
		double dineroTrans = this.getTaller().getTransportadora().getDinero();
		
		if (this.experiencia >= 5) {
			
			this.dinero += Incentivo.incentivoBase;
			
			this.getTaller().getTransportadora().setDinero(dineroTrans - Incentivo.incentivoBase);
		}
		
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

	/**
	 * @return mecanicos
	 */
	public static ArrayList <Mecanico> getMecanicos() {
		
		return mecanicos;
	}

	/**
	 * @param define la lista de mecanicos
	 */
	public static void setMecanicos(ArrayList <Mecanico> mecanicos) {
		
		Mecanico.mecanicos = mecanicos;
	}

	/**
	 * @return vehiculosReparando
	 */
	public ArrayList <Vehiculo> getVehiculosReparando() {
		
		return vehiculosReparando;
	}

	/**
	 * @param Define la lista de vehiculos a reparar por el mecanico
	 */
	public void setVehiculosReparando(ArrayList <Vehiculo> vehiculosReparando) {
		
		this.vehiculosReparando = vehiculosReparando;
	}
	
	public static void agregarMecanico (Mecanico mecanico ) {
		
		mecanicos.add(mecanico);
	} 
	
	public static void removerMecanico (Mecanico mecanico) {
		
		mecanicos.remove(mecanico);
	}

	
}
