package gestorAplicacion.administrativo;
import gestorAplicacion.usuarios.*;

public class Factura {
	
	private int numeroFactura;
	private int total;
	public int totalFactura;
	private Terminal terminal;
	private Pasajero pasajero;
	private Conductor conductor;
	private Viaje viaje;
	private Vehiculo vehiculo;
	private Transportadora transportadora;
	
	
	public Factura() {
		
		
	}
	
	public void imprimirFactura() {
		
		
	}
	
	public void modificarFactura() {
		
		
		
	}
	
	public void setNumeroFactura(int numeroFactura) {
		
		this.numeroFactura = numeroFactura;
		
	}
	
	public int getNumeroFactura() {
		
		return numeroFactura;
		
	}
	
	public void setTotal(int total) {
		
		this.total = total;
		
	}
	
	public int getTotal() {
		
		return total;
		
	}
	
	public void setTotalFactura(int totalFactura) {
		
		this.totalFactura = totalFactura;
		
	}
	
	public int getTotalFactura() {
		
		return totalFactura;
		
	}
	
	public void setTerminal(Terminal terminal) {
		
		this.terminal = terminal;
		
	}
	
	public Terminal getTerminal() {
		
		return terminal;
		
	}
	
	public void setPasajero(Pasajero pasajero) {
		
		this.pasajero = pasajero;
		
	}
	
	public Pasajero getPasajero() {
		
		return pasajero;
		
	}
	
	public void setConductor(Conductor conductor) {
		
		this.conductor = conductor;
		
	}
	
	public Conductor getConductor() {
		
		return conductor;
		
	}
	
	public void setViaje(Viaje viaje) {
		
		this.viaje = viaje;
		
	}
	
	public Viaje getViaje() {
		
		return viaje;
		
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
	
	
	
	
	
	
	
	

}
