package gestorAplicacion.usuarios;
import java.util.ArrayList;
import gestorAplicacion.administrativo.Viaje;

public abstract class Persona {
	
	protected int id;
	protected int edad;
	protected String nombre;
	protected char genero;
	protected ArrayList <Viaje> historial = new ArrayList<>();
	protected boolean estadoLicencia;
	protected int experiencia;
	protected int dinero;
	
	
	public Persona() {
		
	}
	
	public Persona(int id, int edad, String nombre, char genero, ArrayList<Viaje> historial, boolean estadoLicencia,
			int experiencia, int dinero) {
		super();
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
		this.genero = genero;
		this.historial = historial;
		this.estadoLicencia = estadoLicencia;
		this.experiencia = experiencia;
		this.dinero = dinero;
	}



	public ArrayList<Viaje> verHistorialViajes() {
		
		return historial;
		
	}
	
	public abstract String identificarse();
		
		
	
	
	public int consultarDinero() {
		
		
	}
	
	public Viaje tomarViaje() {
		
		
	}
	
	public Viaje reservarViaje() {
		
		
	}
	
	public ArrayList <Viaje> verDisponibilidad(){
		
		
	}
	
	public void elegirDestino() {
		
		
	}
	
	
	public void setId(int id) {
		
		this.id = id;
		
	}
	
	public int getId() {
		
		return id;
		
	}
	
	public void setEdad(int edad) {
		
		this.edad = edad;
		
	}
	
	public int getEdad() {
		
		return edad;
		
	}
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	public void setGenero(char genero) {
		
		this.genero = genero;
		
	}
	
	public char getGenero() {
		
		return genero;
		
	}
	
	public void setHistorial(ArrayList<Viaje> historial) {
		
		this.historial = historial;
		
	}
	
	public ArrayList<Viaje> getHistorial() {
		
		return historial;
		
	}
	
	public void setEstadoLicencia(boolean estadoLicencia) {
		
		this.estadoLicencia = estadoLicencia;
		
	}
	
	public boolean getEstadoLicencia() {
		
		return estadoLicencia;
		
	}
	
	public void setExperienciaConduciendo(int experienciaConduciendo) {
		
		this.experiencia = experienciaConduciendo;
		
	}
	
	public int getExperienciaConduciendo() {
		
		return experiencia;
	
	}
	
	public void setDinero(int dinero) {
		
		this.dinero = dinero;
		
	}
	
	public int getDinero() {
		
		return dinero;
	
	}
}
