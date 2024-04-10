package gestorAplicacion.administrativo;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.Conductor;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math; 


public class Vehiculo {
	
	private int integridad;
	private String placa;
	private String modelo;
	private int precio;
	private int velocidadPromedio;
	private TipoVehiculo tipo;
	private TipoVehiculo capacidad;
	private ArrayList <Conductor> conductores;
	private Transportadora transportadora;
	
	
	public Vehiculo() {
		
		
	}
	
	public Vehiculo (String placa, String modelo, int precio, int velocidadPromedio, TipoVehiculo tipo, TipoVehiculo capacidad, Transportadora transportadora ) {
		
		this.integridad = 100;
		this.placa = placa; this.modelo = modelo; this.precio = precio; this.velocidadPromedio = velocidadPromedio; this.tipo = tipo; this.capacidad = capacidad; this.transportadora = transportadora;
		
	}
	
	public void reduccionIntegridad(int duracionViaje) {
		
		this.integridad = Math.round(this.integridad - (duracionViaje * new Random().nextFloat(2) / 10));
		
		
	}
	
	public void asociarConductor(Conductor conductor) {
		
		this.conductores.add(conductor);
		
		
	}
	
	public void quitarConductor(String nombre) {
		
		for (Conductor i : conductores) {
			
			if (i.getNombre().equals(nombre)) {
				
				conductores.remove(i);
				
				System.out.println(nombre + " ha sido eliminado de los conductores asociados");
				
				return;
				
			}
			
		}	
				
		System.out.println("Ningun conductor con ese nombre esta asociado al vehiculo");
			
		
		
		
		
	}
	
	public void reparacion() {
		
		this.integridad = 100;
		
		
	}
	
	public boolean verificarConductor() {
		
		if (conductores.size() == 0) {
			
			return (false);
		}
		
		return (true);
			
		
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
	
	public void setConductores(ArrayList<Conductor> conductores) {
		
		this.conductores = conductores;
		
	}
	
	public ArrayList<Conductor> getConductores() {
		
		return conductores;
		
	}
	
	public void setTransportadora(Transportadora transportadora) {
		
		this.transportadora = transportadora;
		
	}
	
	public Transportadora getTransportadora() {
		
		return transportadora;
		
	}
	//Testeos
	/*public static void main (String[] Args) {
		
		Vehiculo v = new Vehiculo();
		
		v.setIntegridad(100);
		
		v.reduccionIntegridad(70);
		
		System.out.println(v.getIntegridad());
		
	}*/
	
	
	
	
	

}
