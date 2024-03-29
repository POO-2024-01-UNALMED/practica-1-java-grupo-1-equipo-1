package gestorAplicacion.administrativo;
import java.util.ArrayList;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.constantes.Dia;

public class Viaje {
	
	private int id;
	private int tarifa;
	private int duracion;
	public int totalViajes;
	private String hora;
	private ArrayList <Pasajero> pasajero = new ArrayList<>();
	private Vehiculo vehiculo;
	private Conductor conductor;
	private Destino finalDestino;
	private Dia dia;
	private Destino destino;
	
	
	public Viaje() {
		
	}
	
	
	public int calcularTarifa() {
		
		
	}
	
	public String validacion() {
		
		
	}
	
	public String estado() {
		
		
	}
	
	public void setId(int id) {
		
		this.id = id;
		
	}
	
	public int getId() {
		
		return id;
		
	}
	
	public void setTarifa(int tarifa) {
		
		this.tarifa = tarifa;
		
	}
	
	public int getTarifa() {
		
		return tarifa;
		
	}
	
	public void setDuracion(int duracion) {
		
		this.duracion = duracion;
		
	}
	
	public int getDuracion() {
		
		return duracion;
		
	}
	
	public void setTotalViajes(int totalViajes) {
		
		this.totalViajes = totalViajes;
		
	}
	
	public int getTotalViajes() {
		
		return totalViajes;
		
	}
	
	public void setHora(String hora) {
		
		this.hora = hora;
		
	}
	
	public String getHora() {
		
		return hora;
		
	}
	
	public void setPasajero (ArrayList <Pasajero> pasajero) {
		
		this.pasajero = pasajero;
		
	}
	
	public ArrayList <Pasajero> getPasajero(){
		
		return pasajero;
		
	}
	
	public void setVehiculo(Vehiculo vehiculo) {
		
		this.vehiculo = vehiculo;
		
	}
	
	public Vehiculo getVehiculo() {
		
		return vehiculo;
	}
	
	public void setConductor(Conductor conductor) {
		
		this.conductor = conductor;
		
	}
	
	public Conductor getConductor() {
		
		return conductor;
		
	}
	
	public Destino getFinalDestino() {
		
		return finalDestino;
		
	}
	
	public Dia getDia() {
		
		return dia;
		
	}
	
	public Destino getDestino() {
		
		return destino;
		
	}
	
	
	

}
