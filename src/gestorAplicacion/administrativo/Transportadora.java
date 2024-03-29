package gestorAplicacion.administrativo;
import java.util.ArrayList;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.constantes.Destino;

public class Transportadora {
	
	private String nombre;
	private int dinero;
	private ArrayList <Conductor> conductores = new ArrayList<>();
	private ArrayList <Pasajero> pasajeros = new ArrayList<>();
	private ArrayList <Vehiculo> vehiculos = new ArrayList<>();
	private ArrayList <Viaje> viajeAsignado = new ArrayList<>();
	private Destino destinoAsignado;
	private Terminal terminal;
	
	
	public Transportadora() {
		
		
	}
	
	public void contratarConductor() {
		
		
	}
	
	public void despedirConductor() {
		
		
	}
	
	public void pagarTerminal() {
		
		
	}
	
	public Vehiculo agregarVehiculo() {
		
		
	}
	
	public void removerVehiculo() {
		
		
	}
	
	public void realizarMantenimiento() {
		
		
	}
	
	public ArrayList <Vehiculo> disponibilidadVehiculos(){
		
		
	}
	
	public ArrayList <Factura> reporteFinanciero(){
		
		
	}
	
	public void pagarConductor() {
		
		
	}
	
	public boolean realizarRevision() {
	
		
	}
	
	public boolean estadoVehiculo() {
		
		
	}
	
	public void asignarVehiculo() {
		
		
	}
	
	public void designarVehiculo() {
		
		
	}
	
	public Factura ventaViaje() {
		
		
	}
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	public void setDinero(int dinero) {
		
		this.dinero = dinero;
		
	}
	
	public int getDinero() {
		
		return dinero;
	}
	
	public void setConductores(ArrayList <Conductor> conductores) {
		
		this.conductores = conductores;
		
	}
	
	public ArrayList <Conductor> getConductores(){
		
		return conductores;
		
	}
	
	public void setPasajeros(ArrayList <Pasajero> pasajeros) {
		
		this.pasajeros = pasajeros;
		
	}
	
	public ArrayList <Pasajero> getPasajeros(){
		
		return pasajeros;
		
	}
	
	public void setVehiculos(ArrayList <Vehiculo> vehiculos) {
		
		this.vehiculos = vehiculos;
		
	}
	
	public ArrayList <Vehiculo> getVehiculo(){
		
		return vehiculos;
		
	}
	
	public void setViajeAsignado(ArrayList <Viaje> viajeAsignado) {
		
		this.viajeAsignado = viajeAsignado;
		
	}
	
	public ArrayList <Viaje> getViajeAsignado(){
		
		return viajeAsignado;
		
	}
	
	public Destino getDestinoAsignado() {
		
		return destinoAsignado;
		
	}
	
	public void setTerminal(Terminal terminal) {
		
		this.terminal = terminal;
		
	}
	
	public Terminal getTerminal() {
		
		return terminal;
		
	}
	
	
	
	
	
	

}
