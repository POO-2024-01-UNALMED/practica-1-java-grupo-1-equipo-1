package gestorAplicacion.usuarios;
import java.util.ArrayList;
import gestorAplicacion.administrativo.Viaje;

public class Persona {
	
	private int id;
	private int edad;
	private String nombre;
	private char genero;
	private ArrayList <Viaje> historial = new ArrayList<>();
	private boolean estadoLicencia;
	private int experienciaConduciendo;
	private int dinero;
	
	
	public Persona() {
		
	}
	
	public ArrayList<Viaje> verHistorialViajes() {
		
		return historial;
		
	}
	
	public String identificarse() {
		
		
	}
	
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
		
		this.experienciaConduciendo = experienciaConduciendo;
		
	}
	
	public int getExperienciaConduciendo() {
		
		return experienciaConduciendo;
	
	}
	
	public void setDinero(int dinero) {
		
		this.dinero = dinero;
		
	}
	
	public int getDinero() {
		
		return dinero;
	
	}
}
