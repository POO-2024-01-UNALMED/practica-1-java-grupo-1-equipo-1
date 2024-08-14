package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Vehiculo;
import gestorAplicacion.usuarios.Conductor;

public class Main_2 {
	static Scanner scanner = new Scanner(System.in);
    public static void ejecutar() {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("Ejecutando Funcionalidad 2");
            // Lógica específica para Funcionalidad 2
            
            System.out.println("Seleccione una acción:");
            System.out.println("1. Accion 1");
            System.out.println("2. Accion 2");
            System.out.println("3. Accion 3");
            System.out.println("4. Accion 4");
            System.out.println("5. Accion 5");
            System.out.println("6. Regresar al menú Principal\n");

            int opcion = scanner.nextInt();

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
    	System.out.println("Ejemplo Funcionalidad 2"); 
    }
    
    
    public static void mostrarConductoresActivos(Transportadora transportadora) {
    	//System.out.println(transportadora.mostrarConductActivos());
    	String mensaje = "";
    	int number = 1;
		for (Conductor conductor : transportadora.getConductores()) {
			mensaje += String.valueOf(number) + "Nombre: " + conductor.getNombre()+ "  #Cedula: " + conductor.getId() + "\n";
			number++;
		}
		System.out.println(mensaje);
    }
    
    private static int check(int min, int max) {
    	boolean value = false;
    	int number = 0;
    	while (!value) {
    		int opcion = scanner.nextInt();
    		if (opcion >= min || opcion <= max) {
    			value = true;
    			number = opcion;
    		} else {
    			System.out.print("Error, ingrese un numero entre " + min + " y " + max);
    		}
    	}
    	return number;
    }
    
    private static void mostrarVehiculosDisponibles(Transportadora transportadora, Conductor conductor) {
    	System.out.println ("Escoja un vehiculo:");
    	ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    	for (Vehiculo auto : transportadora.getVehiculos()) {
    		if (auto.getConductores().size() <= 2) {
    			vehiculos.add(auto);
    			}
    		}
    	if (vehiculos.size() == 0) {
    		System.out.println("No hay vehiculos disponibles");
    	} else {
    		for (Vehiculo car : vehiculos) {
    			System.out.println((vehiculos.indexOf(car)+1) + ". " + "Placa: " + car.getPlaca() + "; Modelo: " + car.getModelo());
    		}
    		int option3 = check(1,vehiculos.size());
    		vehiculos.get(option3).asociarConductor(conductor);
			conductor.setVehiculo(vehiculos.get(option3));
    	}
    } 
}
