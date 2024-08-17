package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Vehiculo;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Conductor;

public class Main_2 {
	static Scanner scanner = new Scanner(System.in);
    public static void ejecutar() {
        boolean regresar = false;
        boolean valueTrans = false;
        Transportadora transportaElegida = new Transportadora();// No borrar, solo para pruebas

        while (!regresar) {
        	
        	/*while (!valueTrans) {
    		System.out.println("Digite el numero de la transportadora que desea administrar");
            //System.out.println(terminal.mostrarTransportadoras());
    		//int selection = ckeck(1,terminal.getTransportadoras.size());
    		}*/
        	//Transportadora transportaElegida = terminal.getTransportadoras().get(selection - 1);
        	
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
                	mostrarConductoresActivos(transportaElegida);
                	System.out.println("Seleccione el conductor a despedir");
                	opcion = check(1,transportaElegida.getConductores().size());
                	System.out.println(transportaElegida.despedirConductor(opcion));
                    break;
                case 2:
                	System.out.println("Seleccione el conductor a contratar");
             		System.out.println(transportaElegida.mostrarConductRegistrados());
             		opcion = scanner.nextInt();
             		System.out.println(transportaElegida.contratarConductor(opcion));
                    break;
                case 3:
                	mostrarConductoresActivos(transportaElegida);
                	System.out.println("Seleccione el conductor que desea modificar");
                	opcion = scanner.nextInt();
                	Conductor selectedDriver = transportaElegida.encontrarConductor(opcion);
                	System.out.println("¿Que desea modificar del conductor?");
                	System.out.println("1. Viajes programados");
                	System.out.println("2. Vehiculo");
                	System.out.println("3. Estado de la licencia");
                	System.out.println("4. Regresar");
                	opcion = scanner.nextInt();
                	
                	switch (opcion) {
                	case 1:
                		if (selectedDriver.getHorario() == null) {
                			System.out.println("Seleccione una opcion");
                			System.out.println("1. Asignar viaje");
                			System.out.println("2. Regresar");
                			opcion = check(1,2);
                			switch (opcion) {
                			case 1:
                				break;
                			case 2:
                				break;
                			}
                		} else {
                			System.out.println("Seleccione una opcion");
                			System.out.println("1. Asignar viaje");
                			System.out.println("2. Desvincular viaje");
                			System.out.println("3. Regresar");
                			opcion = check(1,3);
                			switch (opcion) {
                			case 1:
                				break;
                			case 2:
                				break;
                			case 3:
                				break;
                			default:
                				break;
                			}
                			
                		}
                		break;
                	case 2:
                		boolean valorVerdad = selectedDriver.tieneVehiculo();
                		if (valorVerdad) {
                			System.out.println("Seleccione una opcion");
                        	System.out.println("1. Quitar Vehiculo asociado");
                        	System.out.println("2. Regresar");
                        	opcion = scanner.nextInt();
                        	switch (opcion) {
                        	case 1:
                        		selectedDriver.quitarVehiculo();
                        		break;
                        	case 2:
                        		break;
                        	}
                        	
                		} else {
                			System.out.println("Seleccione una opcion");
                			System.out.println("1. Asignar un vehiculo");
                			System.out.println("2. Regresar");
                			opcion = scanner.nextInt();
                			switch (opcion) {
                			case 1:
                				//transportaElegida.mostrarVehiculos();
                				mostrarVehiculosDisponibles(transportaElegida,selectedDriver);
                				break;
                			case 2:
                				break;
                			}
                		}
                		break;
                	case 3:
                		if (selectedDriver.getEstadoLicencia()) {
                			System.out.println("El conductor actualmente tiene la licencia activa");
                			System.out.println("¿Desea desactivarle la licencia?");
                			//String letras = checkSN();
                			String letras = scanner.nextLine().toUpperCase();
                			boolean value = false;
                			
                			while (!value) {
                			    letras = scanner.nextLine().toUpperCase();
                			    
                			    if (!letras.equals("S") && !letras.equals("N")) {
                			        System.out.println("Error, ingrese la letra S o N");
                			    } else {
                			        value = true;
                			    }
                			}
                			if (letras.equals("S")) {
                				if (selectedDriver.getHorario().size()==7) {
                					selectedDriver.setEstadoLicencia(false);
                					System.out.println("Se le ha desactivado la licencia exitosamente");
                				} else {
                					System.out.println("No es posible desactivar la licencia, el conductor tiene viajes prorgamados");
                				}
                			}
                		} else {
                			System.out.println("El conductor actualmente tiene la licencia desactivada");
                			System.out.println("¿Desea activarle la licencia?");
                			//String letras = checkSN();
                			String letras = scanner.nextLine().toUpperCase();
                			boolean value = false;

                			while (!value) {
                			    letras = scanner.nextLine().toUpperCase();
                			    if (!letras.equals("S") && !letras.equals("N")) {
                			        System.out.println("Error, ingrese la letra S o N");
                			    } else {
                			        value = true;
                			    }
                			}
                			if (letras.equals("S")) {
                				selectedDriver.setEstadoLicencia(true);
                				System.out.println("Se le ha activado la licencia exitosamente");
                			}
                		}
                		
                		break;
                	case 4:
                		break;
                	}
                	
                	
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
