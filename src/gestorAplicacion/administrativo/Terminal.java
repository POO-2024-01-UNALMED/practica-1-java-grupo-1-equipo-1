package gestorAplicacion.administrativo;
import java.util.ArrayList;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Persona;

public class Terminal {
	
	private String nombre;
	private int dinero;
	private int capacidadVehiculos;
	private int cantidadSedes;
	private int cantidadVehiculos;
	private ArrayList <Transportadora> transportadoras = new ArrayList<>();
	private ArrayList <Viaje> reservas = new ArrayList<>();
	private ArrayList <Viaje> viajes = new ArrayList<>();
	private ArrayList <Destino> destinos = new ArrayList<>();
	private Destino ubicacion;
	private Persona administrador;
	
	
	public Terminal() {
	
	}
	
	
	public Viaje programarViaje() {
		
		
	}
	
	public void cancelarViaje() {
		
		
	}
	
	public ArrayList <Viaje> verificarDisponibilidad(){
		
		
	}
	
	public Boolean consultarCapacidad() {
		
		
	}
	
	public void realizarReserva() {
		
		
	}
	
	public void denegarReserva() {
		
		
	}
	
	public ArrayList <Factura> obtenerFinanzas(){
		
		
	}
	
	public String estadoViaje() {
		
		
	}
	
	public void asignarViaje() {
		
		
	}
	
	public void designarViaje() {
		
		
	}
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String getNombre() {
		
		return nombre;
				
	}
	
	public void setDinero (int dinero) {
		
		this.dinero = dinero;
		
	}
	
	public int getDinero() {
		
		return dinero;
		
	}
	
	public void setCapacidadVehiculos(int capacidadVehiculos) {
		
		this.cantidadVehiculos = capacidadVehiculos;
			
	}
	
	public int getCapacidadVehiculos() {
		
		return capacidadVehiculos;
		
	}
	
	public void setCantidadSedes(int cantidadSedes) {
		
		this.cantidadSedes = cantidadSedes;
		
	}
	
	public int getCantidadSedes() {
		
		return cantidadSedes;
		
	}
	
	public void setCantidadVehiculos(int cantidadVehiculos) {
		
		this.cantidadVehiculos = cantidadVehiculos;
		
	}
	
	public int getCantidadVehiculos() {
		
		return cantidadVehiculos;
		
	}
	
	public void setTransportadoras(ArrayList <Transportadora> transportadoras) {
		
		this.transportadoras = transportadoras;
		
	}
	
	public ArrayList <Transportadora> obtenerTransportadoras(){
		
		return transportadoras;
	}
	
	
	public void setReservas(ArrayList <Viaje> reservas) {
		
		this.reservas = reservas;
		
	}
	
	public ArrayList <Viaje> getReservas(){
		
		return reservas;
	
	}
	
	public void setViajes(ArrayList <Viaje> viajes) {
		
		this.viajes = viajes;
		
	}
	
	public ArrayList <Viaje> getViajes(){
		
		return viajes;
	
	}
	
	public void setDestinos(ArrayList <Destino> destinos) {
		
		this.destinos = destinos;
		
	}
	
	public ArrayList <Destino> getDestinos(){
		
		return destinos;
	
	}
	
	public Destino getUbicacion() {
		
		return ubicacion;
	}
	
	public void setAdministrador(Persona administrador) {
		
		this.administrador = administrador;
		
	}
	
	public Persona getAdministrador() {
		
		return administrador;
		
	}
	
	
	
	
	

}
