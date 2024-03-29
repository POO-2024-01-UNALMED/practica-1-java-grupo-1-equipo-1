package gestorAplicacion.administrativo;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.usuarios.Conductor;

public class Vehiculo {
	
	private int integridad;
	private String placa;
	private String modelo;
	private int precio;
	private int velocidadPromedio;
	private TipoVehiculo tipo;
	private TipoVehiculo capacidad;
	private Conductor conductor;
	private Transportadora transportadora;
	
	
	public Vehiculo() {
		
		
	}
	
	public void reduccionIntegridad() {
		
		
	}
	
	public void asociarConductor() {
		
		
	}
	
	public void quitarConductor() {
		
		
	}
	
	public void reparacion() {
		
		
	}
	
	public boolean verificarConductor() {
			
		
	}
	
	public void setIntegridad(int integridad) {
		
		this.integridad = integridad;
		
	}
	
	public int getIntegridad() {
		
		return integridad;
		
	}
	
	public void setPlaca(String placa) {
		
		this.placa = placa;
		
	}
	
	public String getPlaca() {
		
		return placa;
		
	}
	
	public void setModelo(String modelo) {
		
		this.modelo = modelo;
		
	}
	
	public String getModelo() {
		
		return modelo;
		
	}
	
	public void setPrecio(int precio) {
		
		this.precio = precio;
		
	}
	
	public int getPrecio() {
		
		return precio;
		
	}
	
	public void setVelocidadPromedio(int velocidadPromedio) {
		
		this.velocidadPromedio = velocidadPromedio;
		
	}
	
	public int getVelocidadPromedio() {
		
		return velocidadPromedio;
		
	}
	
	public TipoVehiculo getTipo() {
		
		return tipo;
		
	}
	
	public TipoVehiculo getCapacidad() {
		
		return capacidad;
		
	}
	
	public void setConductor(Conductor conductor) {
		
		this.conductor = conductor;
		
	}
	
	public Conductor getConductor() {
		
		return conductor;
		
	}
	
	public void setTransportadora(Transportadora transportadora) {
		
		this.transportadora = transportadora;
		
	}
	
	public Transportadora getTransportadora() {
		
		return transportadora;
		
	}
	
	
	
	
	
	

}
