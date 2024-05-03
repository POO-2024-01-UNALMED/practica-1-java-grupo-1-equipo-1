package gestorAplicacion.usuarios;
import java.util.ArrayList;
import java.util.Map;

import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.administrativo.Factura;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una persona la cual tiene un id, una edad, nombre, genero, dinero, historial, experiencia y una factura esta clase 
 *  es abstracta, su implementación principalmente es para las relaciones de herencia y métodos abstractos. 
 */

public abstract class Persona {
	
	protected int id; // Id de la persona
	protected int edad; // Edad de la persona
	protected String nombre; // Nombre de la persona
	protected char genero; // Género de la persona
	protected ArrayList <Viaje> historial = new ArrayList<>(); // Historial de viajes de la persona
	protected int experiencia; // Este atributo será utilizado por las clases que heredan de persona y tendrá diferentes implementaciones en estas
	protected int dinero; // Dinero de la persona
	protected Factura factura; // Factura asociada a la persona
	protected Map<String, Integer> contrato;
	
	public Persona() {
		
		
	}
	
	/**
	 * Constructor para la clase persona
     * @param id, id de la persona.
     * @param edad, edad de la persona
     * @param nombre, nombre de la persona
     * @param genero, genero de la persona
     * @param historial, historial de viaje de la persona
     * @param experiencia, atributo que se usará en las clases hijas
     * @param dinero, dinero de la persona
     */
	
	public Persona(int id, int edad, String nombre, char genero, ArrayList<Viaje> historial,
			int experiencia, int dinero) {
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
		this.genero = genero;
		this.historial = historial;
		this.experiencia = experiencia;
		this.dinero = dinero;
	}

	/**
	 * Método para obtener el historial de viajes de la persona.
	 * @return el historial de viajes de la persona.
	 */

	public ArrayList<Viaje> verHistorialViajes() {
		
		return historial;
		
	}
	
	// Método Abstracto, tendrá diferentes implementaciones en las subclases de persona
	
	public abstract String identificarse();
		
		
	
	
	public int consultarDinero() {
		
		// Implementación pendiente
		
		return 0;
		
		
	}
	
	public Viaje tomarViaje() {
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public Viaje reservarViaje() {
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public ArrayList <Viaje> verDisponibilidad(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void elegirDestino() {
		
		// Implementación pendiente
		
		
	}
	
	// METODOS GETTERS Y SETTERS
	
	
	/**
	 * Establece o modifica el id de la persona.
	 * @param id, el id de la persona.
	 */
	
	public void setId(int id) {
		
		this.id = id;
		
	}
	
	/**
	 * Método para obtener el id de la persona.
	 * @return el id de la persona.
	 */
	
	public int getId() {
		
		return id;
		
	}
	
	/**
	 * Establece o modifica la edad de la persona.
	 * @param edad, la edad de la persona.
	 */
	
	public void setEdad(int edad) {
		
		this.edad = edad;
		
	}
	
	/**
	 * Método para obtener la edad de la persona.
	 * @return la edad de la persona.
	 */
	
	public int getEdad() {
		
		return edad;
		
	}
	
	/**
	 * Establece o modifica el nombre de la persona.
	 * @param nombre, el nombre de la persona.
	 */
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	/**
	 * Método para obtener el nombre de la persona.
	 * @return el nombre de la persona.
	 */
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	/**
	 * Establece o modifica el genero de la persona.
	 * @param genero, el género de la persona.
	 */
	
	public void setGenero(char genero) {
		
		this.genero = genero;
		
	}
	
	/**
	 * Método para obtener el genero de la persona.
	 * @return el género de la persona.
	 */
	
	public char getGenero() {
		
		return genero;
		
	}
	
	/**
	 * Establece o modifica el historial viajes de la persona.
	 * @param historial, el historia de viajes de la persona.
	 */
	
	public void setHistorial(ArrayList<Viaje> historial) {
		
		this.historial = historial;
		
	}
	
	/**
	 * Método para obtener el historial de viajes de la persona.
	 * @return el historial de viajes de la persona.
	 */
	
	public ArrayList<Viaje> getHistorial() {
		
		return historial;
		
	}
	
	
	/**
	 * Establece o modifica el dinero de la persona.
	 * @param dinero, el dinero de la persona.
	 */
	
	public void setDinero(int dinero) {
		
		this.dinero = dinero;
		
	}
	
	/**
	 * Método para obtener el dinero de la persona.
	 * @return el dinero de la persona.
	 */
	
	public int getDinero() {
		
		return dinero;
	
	}
	
	/**
	 * Establece o modifica la factura de la persona.
	 * @param factura, la factura de la persona.
	 */
	
	public void setFactura(Factura factura) {
		
		this.factura = factura;
		
	}
	
	/**
	 * Método para obtener la factura de la persona.
	 * @return la factura de la persona.
	 */
	
	public Factura getFactura() {
		
		return factura;
		
	}
	
	/**
	 * Método para obtener la experiencia de la persona.
	 * @return la experiencia de la persona.
	 */
    public int getExperiencia() {
        return experiencia;
    }

	/**
	 * Establece o modifica la experiencia de la persona.
	 * @param experiencia, la experiencia de la persona.
	 */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    /** 
     * Metodo para obtener el diccionario contrato
     * @return diccionario contrato
     * */
    
	public Map<String, Integer> getContrato() {
		return contrato;
	}
	
	/** 
	 * Metodo para establecer el contrato para conductor y mecanico
	 * @param diccionaro con los contratos*/

	public void setContrato(Map<String, Integer> contrato) {
		this.contrato = contrato;
	}
    
    
}
