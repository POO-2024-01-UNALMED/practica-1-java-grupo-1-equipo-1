package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Vehiculo;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.tiempo.Tiempo;
import gestorAplicacion.usuarios.Conductor;

public class Main_2 {
	static Scanner scanner = new Scanner(System.in);
    public static void ejecutar() {
        boolean regresar = false;
        boolean valueTrans = false;

        
        System.out.println("Digite el numero de la transportadora que desea administrar");
        int n = 1;
        
        for (Transportadora transportadora: Transportadora.getTransportadoras()) {
        	System.out.println(String.valueOf(n) + ". " + transportadora.getNombre());
        	n++;
        }
        
      
        int selection = check(1,Transportadora.getTransportadoras().size());
        Transportadora transportaElegida = Transportadora.getTransportadoras().get(selection-1);
        
        System.out.println("Has seleccionado " + transportaElegida.getNombre() + "\n");
        
        while (!regresar) {
        	
            System.out.println("Seleccione una acción:");
            System.out.println("1. Despedir conductor");
            System.out.println("2. Contratar conductor");
            System.out.println("3. Modificar conductor");
            System.out.println("4. Accion 4");
            System.out.println("5. Accion 5");
            System.out.println("6. Regresar al menú Principal\n");

            int opcion = scanner.nextInt();

            switch (opcion) {
            
                case 1:
                	
                	mostrarConductoresActivos(transportaElegida);
                	System.out.println("Digite el id del conductor a despedir");
                	opcion = scanner.nextInt();
                	
                	if (transportaElegida.encontrarConductor(opcion) == null) {
    					
    					System.out.println("No se encontro el conductor con Id #" + opcion);
    					
    				} else {
    					System.out.println(transportaElegida.despedirConductor(opcion));
    				}
                    break;
                    
                case 2:
                	
                	System.out.println("Digite el id del conductor a contratar");
                	
                	if (transportaElegida.mostrarConductRegistrados().equals("")) {
                		
                		System.out.println("No hay conductores disponibles para contratar");
                		break;
                		
                	}
                	
             		System.out.println(transportaElegida.mostrarConductRegistrados());
             		opcion = scanner.nextInt();
             		System.out.println(transportaElegida.contratarConductor(opcion));
                    break;
                    
                case 3:
                	
                	mostrarConductoresActivos(transportaElegida);
                	System.out.println("Digite el id del conductor que desea modificar");
                	opcion = scanner.nextInt();
                	Conductor selectedDriver = transportaElegida.encontrarConductor(opcion);
                	System.out.println("¿Que desea modificar del conductor?");
                	System.out.println("1. Viajes programados");
                	System.out.println("2. Vehiculo");             
                	System.out.println("3. Regresar");
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
                				
                				
                				if (transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo()).equals("")){
                					System.out.println("No hay viajes disponibles que el conductor pueda tomar");
                				} else {
                					System.out.println("Digite el id del viaje que desea asignarle al conductor");
                    				System.out.println(transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo()));
                    				
                					opcion = scanner.nextInt();
                    				
                    				if (transportaElegida.encontrarViaje(opcion) == null) {
                    					System.out.println("No se encontro un viaje con el Id");
                    				} else {
                    					Viaje selectedViaje = transportaElegida.encontrarViaje(opcion);
                    					selectedDriver.vincularYDesvincular(selectedViaje.getConductor(),selectedViaje);
                    					System.out.println("Se asigno el viaje al conductor");
                    				}
                				}
                				
                				break;
                				
                			case 2:
                				
                				break;
                				
                			default:
                				
                				System.out.println("Opcion no valida");
                				break;
                				
                			}
                		} else {
                			
                			System.out.println("\nSeleccione una opcion");
                			System.out.println("1. Asignar viaje");
                			System.out.println("2. Desvincular viaje");
                			System.out.println("3. Regresar");
                			opcion = check(1,3);
                			
                			switch (opcion) {
                			
                			case 1:
                              				
                				if (transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo()).equals("")){
                					
                					System.out.println("No hay viajes disponibles que el conductor pueda tomar");
                					
                				} else {
                					
                					System.out.println("Digite el id del viaje que desea asignarle al conductor");
                    				System.out.println(transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo()));
      
                					opcion = scanner.nextInt();
                    				
                    				if (transportaElegida.encontrarViaje(opcion) == null) {
                    					
                    					System.out.println("No se encontro un viaje con el Id");
                    					
                    				} else {
                    					
                    					Viaje selectedViaje = transportaElegida.encontrarViaje(opcion);
                    					selectedDriver.vincularYDesvincular(selectedViaje.getConductor(),selectedViaje);
                    					System.out.println("Se asigno el viaje al conductor");
                    				}
                				}
                				break;
                				
                			case 2:
                				
                				System.out.println("Seleccione el viaje que quiere desvincular");
                				System.out.println(selectedDriver.mostrarViajes());
                				int selectedTrip = check(1,selectedDriver.getHorario().size());
                			
                				System.out.println("Digite el id del conductor que tomara el viaje");
                				System.out.println(transportaElegida.conductoresDisponibles(selectedDriver.getHorario().get(selectedTrip-1)));
                				opcion = scanner.nextInt();
                			
                				if (transportaElegida.encontrarConductor(opcion) == null) {
                					
                					System.out.println("No se encontro el conductor con Id #" + opcion);
                					
                				} else {
                					
                					selectedDriver.desvincularYVincular(transportaElegida.encontrarConductor(opcion), selectedDriver.getHorario().get(selectedTrip-1));
                		            System.out.print("Se asignado el viaje al conductor " + transportaElegida.encontrarConductor(opcion).getNombre() + "\n\n");
                		            
                				}
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
                        	System.out.println("1. Desvincular vehiculo asociado");
                        	System.out.println("2. Regresar");
                        	opcion = scanner.nextInt();
                        	switch (opcion) {
                        	case 1:
                        		
                        		if (selectedDriver.getHorario().isEmpty() != true) {
                        			
                        			System.out.println("No se puede desvincular el vehiculo porque el conductor tiene viajes programados");
                        			break;
                        			
                        		}
                        		
                        		System.out.println(selectedDriver.quitarVehiculo());
                        		break;
                        		
                        	case 2:
                        		
                        		break;
                        	
                        	default:
                        		
                        		System.out.println("Opcion no valida");
                        		break;
                        	}
                        	
                        	
                        	
                		} else {
                			
                			System.out.println("Seleccione una opcion");
                			System.out.println("1. Asignar un vehiculo");
                			System.out.println("2. Regresar");
                			opcion = scanner.nextInt();
                			
                			switch (opcion) {
                			case 1:

                				mostrarVehiculosDisponibles(transportaElegida,selectedDriver);
                				
                				break;
                				
                			case 2:
                				
                				break;
                				
                			default:
                				
                				System.out.println("Opcion no valida");
                				break;
                				
                			}
                		}
                		break;
                		
                	case 3:
                		
                		break;
                		
                	default:
                		
                		System.out.println("Opcion no valida.");
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
		for (Conductor conductor : transportadora.getConductores()) {
			mensaje += "Nombre: " + conductor.getNombre()+ "  #ID: " + conductor.getId() + "\n";
		}
		System.out.println(mensaje);
    }
    
    private static int check(int min, int max) {
    	boolean value = false;
    	int number = 0;
    	while (!value) {
    		int opcion = scanner.nextInt();
    		if (opcion >= min && opcion <= max) {
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
