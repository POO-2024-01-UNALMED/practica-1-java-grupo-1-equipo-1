package uiMain;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.Mecanico;
import gestorAplicacion.administrativo.*;

import java.util.Scanner;

public class Main_4 {
	
    private static Scanner scanner = new Scanner(System.in);
    private static boolean regresar = false;
    private static int opcion;
	
	public static void ejecutar() {


        while (!regresar) {
            System.out.println("Ejecutando Funcionalidad 4");
            // Lógica específica para Funcionalidad 4
            
            System.out.println("Seleccione una acción:");
            System.out.println("1. Accion 1");
            System.out.println("2. Accion 2");
            System.out.println("3. Accion 3");
            System.out.println("4. Accion 4");
            System.out.println("5. Accion 5");
            System.out.println("6. Regresar al menú Principal\n");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Poner los metodos a ejecutar para cada accion.
                	ejemplo();
                    break;
                case 2:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                case 3:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                case 4:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                case 5:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                case 6:
                    regresar = true;
                    System.out.println("Regresando al Menú Principal");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    // Desarrollar los metodos a partir de aqui
    public static void ejemplo() {
    	System.out.println("Ejemplo Funcionalidad 4"); 
    }
    
    
    public static void agregarVehiculo () {
    	
    	System.out.println ("Placa");
    	String placa = scanner.nextLine();
    	System.out.println ("Modelo");
    	String modelo = scanner.nextLine();
    	System.out.println ("Precio");
    	double precio = scanner.nextDouble();
    	System.out.println ("Velocidad promedio");
    	double velocidad = scanner.nextDouble();
    	System.out.println ("Tipo de vehiculo");
    	System.out.println ("Elija  una opción:");
    	int i = 1;
    	
    	for (TipoVehiculo tipo : TipoVehiculo.values()) {
    		
    		System.out.println (i + ". " + tipo);
    		i++;
    	}
    	
    	TipoVehiculo tipo = TipoVehiculo.values()[scanner.nextInt() - 1];
    	System.out.println("Transportadora");
    	System.out.println("Elija una opción:");
    	
    	for (Transportadora transportadora : Transportadora.getTransportadoras()) {
    		
    		System.out.println((Transportadora.getTransportadoras().indexOf(transportadora)+1) + ". " + transportadora.getNombre());
    	}
    	
    	Transportadora transportadora = Transportadora.getTransportadoras().get(scanner.nextInt()-1);
    	
    	Vehiculo vehiculo = new Vehiculo (placa, modelo, precio, velocidad, tipo, transportadora);
    	
    	
    	
    	
    	if (vehiculo.getPrecio() > vehiculo.getTransportadora().getDinero()) {
    		
    		vehiculo.getTransportadora().agregarVehiculo(vehiculo);
    		
    	}
    	
    	else {
    		
    		quitarVehiculo(vehiculo);
    	}
    	
    }
    
    
    public static void quitarVehiculo (Vehiculo vehiculo) {
    	
    	System.out.println("Elija Una opcion:");
    	System.out.println("1. Vender vehiculo");
    	System.out.println("2. Desechar vehiculo");
    	int opcion = scanner.nextInt();	
    	
    	switch (opcion) {
    	
    		case 1:
    			
    			vehiculo.getTransportadora().getTaller().venderVehiculo(vehiculo);
    			
    			
    		case 2:
    			
    			vehiculo.getTransportadora().removerVehiculo(vehiculo);
    	}
    	
    	
    	
    }
    
    public static Vehiculo elegirVehiculo (Transportadora transportadora) {
    	
    	System.out.println ("Escoja un vehiculo:");
    	
    	for (Vehiculo auto : transportadora.getVehiculos()) {
    		
    		System.out.println((transportadora.getVehiculos().indexOf(auto)+1) + ". " + "Placa: " + auto.getPlaca() + "; Modelo: " + auto.getModelo());
    		
    	}
    	
    	return (transportadora.getVehiculos().get(scanner.nextInt()-1));
    	
    }
    
    
    
}
