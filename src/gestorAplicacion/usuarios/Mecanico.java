package gestorAplicacion.usuarios;
import gestorAplicacion.administrativo.*;
import java.util.ArrayList;

public class Mecanico extends Persona{
	private Taller taller;
	private Vehiculo vehiculoAsignado;
	private ArrayList <String> historial;
	private Boolean estado;
	
	public Mecanico(Taller taller, Vehiculo vehiculoAsignado, Boolean estado) {
		super();
		this.taller = taller;
		this.vehiculoAsignado = vehiculoAsignado;
		this.estado = estado;
		this.historial =  new ArrayList<String>();
		
	}
	
	public void repararVehiculo() {
		
	}
	
	public void calcularExperiencia() {
		
	}
	
}
