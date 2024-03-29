package gestorAplicacion.usuarios;
import java.util.ArrayList;
import gestorAplicacion.administrativo.Vehiculo;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Factura;


public class Conductor extends Persona {
	
	private Vehiculo vehiculo;
	private Transportadora transportadora;
	private ArrayList <Viaje> horario = new ArrayList<>();
	
	
	public Conductor() {
		
	}
	
	
	public void conducir() {
		
		
	}
	
	public String notificarLllegada() {
		
		
	}
	
	public void finalizarViaje() {
		
		
	}
	
	public ArrayList <Factura> reporteFinanciero(){
		
		
	}
	
	public void renovarContraro() {
		
		
	}
	
	public ArrayList <Viaje> consultarHorario(){
		
		
	}
	
	public void reportarProblema() {
		
		
	}
	
	public void tomarVehiculo() {
		
		
	}
	
	public void quitarVehiculo() {
		
		
	}
	
	public void setVehiculo(Vehiculo vehiculo) {
		
		this.vehiculo = vehiculo;
		
	}
	
	public Vehiculo getVehiculo() {
		
		return vehiculo;
		
	}
	
	public void setTransportadora(Transportadora transportadora) {
		
		this.transportadora = transportadora;
		
	}
	
	public Transportadora getTransportadora() {
		
		return transportadora;
		
	}
	
	public void setHorario(ArrayList <Viaje> horario) {
		
		this.horario = horario;
		
	}
	
	public ArrayList <Viaje> getHorario(){
		
		return horario;
	}
	
	
	
	
	


}
