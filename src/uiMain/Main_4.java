package uiMain;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;
import java.util.ArrayList;

import java.util.Scanner;

public class Main_4 {
	
    private static Scanner scanner = new Scanner(System.in);
    private static boolean regresar = false;
    private static int opcion;
    private static Vehiculo vehiculo;
	
	public static void ejecutar() {
		
		int opcion1;
		int opcion2;
		int opcion3;
		int opcion4;
		int opcion5;
		
		Transportadora transportadora = elegirTransportadora();
		if (transportadora == null) {
			return;
		}
		
		else {
			
			if(transportadora.getTaller() == null) {
				
				System.out.println("La transportadora escogida no tiene ningun taller; Tienes que crear uno");
				cambiarTaller(transportadora);
			}


	        while (!regresar) {
	        	
	        	try {
	        		
	        		while (true) {
	        	
			        	try {
		        			System.out.println("Opciones de administracion:");
				        	System.out.println("1. Agregar un nuevo vehiculo");
				        	System.out.println("2. Administrar vehiculos");
				        	System.out.println("3. Administrar mecanicos");
				        	System.out.println("4. Cambiar de taller");
				        	System.out.println("5. Ir atras");
				        	
				        	opcion1 = scanner.nextInt();
				        	break;
			        	}
			        	
			        	catch (Exception e) {
			        		System.out.println("Valor ingresado incorrecto");
			        		scanner.nextInt();
			        	}
	        		}
		        	
		        	switch (opcion1) {
		        	
		        		case 1:
		        			
		        			if (transportadora.getTerminal().getCapacidadVehiculos() > transportadora.getTerminal().getCantidadVehiculos() ) {
		        				
		        				if (!agregarVehiculo(transportadora)) {
		        					
		        					System.out.println("No se pudo agregar el vehiculo");
		        					
		        					
		        				}
		        				
		        				if (!verificarIntegridad(vehiculo)) {
		        					
		        					while (true) {
		        						
		        						try {
				        		    		System.out.println ("¿Desea reparar el vehiculo?");
				        		    		System.out.println ("1. Si");
				        		    		System.out.println ("2. No");
				        		    		
				        		    		if (scanner.nextInt() == 1) {
				        		    			
				        		    			repararVehiculo(vehiculo);
				        		    		}
				        		    		break;
		        						}
		        						
		        						catch (Exception e) {
		        							
		        							System.out.println("Valor ingresado incorrecto");
		        							scanner.next();
		        						}
		        					}
		        				}	
		        			}
		        			
		        			else {
		        				
		        				System.out.println ("La terminal no tiene suficiente capacidad, elimine un vehiculo");
		        				quitarVehiculo(transportadora);
		        			}
		        		break;
		        			
		        		
		        		case 2:
		        			
		        			vehiculo = elegirVehiculo(transportadora);
		        			boolean regresar2 = false;
		        			while (!regresar2) {
		        				
		        				while (true) {
			        				
		        					try {
			        					System.out.println("Que accion desea realizar con el vehiculo");
				        	        	System.out.println("1. Verificar integridad");
				        	        	System.out.println("2. Reparar vehiculo");
				        	        	System.out.println("3. Administrar disponibilidad");
				        	        	System.out.println("4. Cancelar");
				        				opcion2 = scanner.nextInt();
				        				break;
		        					}
		        					
		        					catch (Exception e) {
		        						
		        						System.out.println("Valor ingresado incorrecto");
	        							scanner.next();
		        						
		        					}
		        				}
		        				
		        				switch (opcion2) {
		        				
			        				case 1:
			        					
			        					verificarIntegridad(vehiculo);
			        					break;
			        					
			        				case 2:
			        					
			        					repararVehiculo (vehiculo);
			        					break;
			        					
			        				case 3:
			        					while (true) {
			        						
			        						try {
					        					System.out.println("Que accion desea realizar");
					            	        	System.out.println("1. Eliminar vehiculo de la transportadora");
					            	        	System.out.println("2. Agregar vehiculo a la lista de vehiculos disponibles");
					            	        	System.out.println("3. Quitar vehiculo de la lista de vehiculos disponibles");
					            	        	System.out.println("4. Ver lista de vehiculos disponibles para viajar");
					            	        	System.out.println("5. Cancelar");
					        					opcion3 = scanner.nextInt();
					        					break;
			        						}
			        						
			        						catch (Exception e) {
			        							
				        						System.out.println("Valor ingresado incorrecto");
			        							scanner.next();
			        							
			        						}
			        					}
			        					
			        					switch (opcion3) {
			        					
				        					case 1:
				        						
				        						quitarVehiculo(vehiculo);
				        						break;
				        						
				        					case 2:
				        						
				        						if (vehiculo.getTransportadora().getTaller().getVehiculosEnReparacion().contains(vehiculo) || vehiculo.getTransportadora().getTaller().getVehiculosEnVenta().contains(vehiculo) || vehiculo.getIntegridad() == 0 || !vehiculo.disponibilidad()) {
				        							
				        							System.out.println("No es posible agregar el vehiculo a la lista de disponibilidad pues este no se encuentra disponible");
				        							
				        							//continue;
				        						}
				        						
				      
				        						
				        						else {
				        							
				        							vehiculo.setEstado(true);
				        							System.out.println ("Vehiculo agregado a la lista de disponiblidad");
				        							
				        							//continue;
				        							
				        							
				        						}
				        						
				        						break;
				        					
				        					case 3:
				        						
				        						vehiculo.setEstado(false);
				        						System.out.println("Vehiculo eliminado de la lista de disponiblidad");
				        						break;
		
				        					case 4:
				        						
				        						ArrayList <Vehiculo> vehiculos = vehiculosDisponibles(transportadora);
				        						
				        						System.out.println("Vehiculos disponibles:");
				        						
				        						for (int i = 0; i < vehiculos.size(); i++ ) {
				        							
				        							System.out.println((i + 1) + ". Modelo:" + vehiculos.get(i).getModelo() + "; Placa: " + vehiculos.get(i).getPlaca() );
				        						}
				        						System.out.println("Los vehiculo de la lista estan disponibles para viajar, pues: No se estan reparando o vendiendo; tienen integridad > 0; tienen un conductor asignado.");
				        						//continue;
				        						break;
				        						
				        						
				        						
				        					case 5:
				        						
				        						regresar2 = true;
				        						break;
				        						
				        					
				        					default:
				        						
				        						System.out.println("Opcion no valida");
				        						//continue;
			        					
			        					}
			        					
			        				break;	
			        					
			        				case 4:
			        					
			        					regresar2 = true;
			        					break;
			        					
			        				default:
			        					
			        					System.out.println("Opcion no valida");
			        					//continue;
			        				
		        				}
		        				
		        		
		        			}
		        			
		        			break;
		        			
		        			
		        			
		        		case 3:
		        			boolean regresar3 = false;
		        			
		        			while (!regresar3) {
			        			
		        				while (true) {
		        					
		        					try {
				        				System.out.println("Que accion desea realizar con los mecanicos");
					        			System.out.println("1. Agregar mecanico");
					        			System.out.println("2. Eliminaar mecanico");
					        			System.out.println("3. Mostrar mecanicos");
					        			System.out.println("4. Ir atras");
					        			opcion4 = scanner.nextInt();
					        			break;
		        					}
		        					
		        					catch (Exception e) {
		        						
		        						System.out.println("Valor ingresado incorrecto");
	        							scanner.next();
	        							
		        					}
		        				}
		        				
			        			switch (opcion4) {
			        			
			        			case 1:
			        				
			        				agregarMecanico(transportadora);
			        				break;
			
			        			case 2:
			        				
			        				if (quitarMecanico(transportadora)) {
			        					
			        					System.out.println("El mecanico se retiro correctamente");
			        				}
			        				
			        				else {
			        					
			        					System.out.println("El mecanico no se pudo eliminar");
			        				}
			        				break;
			        				
			        			case 3:
			        				
			        				System.out.println("Mecanicos de " + transportadora.getNombre() + ":");
			        				
			        				for (Mecanico mecanico : transportadora.getTaller().getMecanicos()) {
			        					
			        					System.out.println((transportadora.getTaller().getMecanicos().indexOf(mecanico)+1) + ". " + "Nombre: " + mecanico.getNombre() + "; ID: " + mecanico.getId());
			        					
			        				}
			        				break;
			        			
			        			case 4:
			        				
			        				regresar3 = true;
			        				break;
			        				
			        			default:
			        				
			        				System.out.println("Opcion no valida");
			        				
			        			}
			        		}
		        			break;
		
		        		case 4:
		        			
		        			while (true) {
		        				
		        				try {
				        			System.out.println("Si cambia de taller perdera el taller anterior y los mecanicos de este");
				        			System.out.println("¿Desea hacerlo?");
				        			System.out.println("1. Si");
				        			System.out.println("2. No");
				        			
				        			opcion5 = scanner.nextInt();
				        			break;
		        				}
		        				
		        				catch (Exception e) {
		        					
		        					System.out.println("Valor ingresado incorrecto");
        							scanner.next();
		        				}
		        			}
		        			
		        			switch (opcion5) {
		        			
		        				case 1:
		        					
		        					if (transportadora.getTaller().getVehiculosEnReparacion().size() > 0 || transportadora.getTaller().getVehiculosEnVenta().size() > 0) {
		        						
		        						System.out.println("No es posible cambiar el taller pues quedan tareas pendientes en esta");
		        					}
		        					
		        					else {
		        						
		        						cambiarTaller(transportadora);
		        					}
		        					break;
		        					
		        				case 2:
		        					
		        					
		        					break;
		        				
		        				default:
		        					
		        					System.out.println("Opcion no valida");
		        					break;
		        			}
		        			break;
		        			
		        		case 5:
		        			
		        			regresar = true;
		        			break;
		        			
		        		default:
		        			
		        			System.out.println("Opcion no valida");
		        	}
	        	}
	        	
	        	catch (Exception e) {
	        		
	        		System.out.println("Accion no valida");
	        		scanner.nextLine();
	        	}
	        	       	
	        }
	        
			
	       
		}
       
            /**System.out.println("Ejecutando Funcionalidad 4");
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
        }**/
    }
    
    // Desarrollar los metodos a partir de aqui
    public static void ejemplo() {
    	System.out.println("Ejemplo Funcionalidad 4"); 
    }


    public static void cambiarTaller (Transportadora transportadora) {
		String nombre;
		int capacidad;
    	while (true) {
			
			try {
		    	System.out.println("Ingrese los siguientes datos del nuevo taller:");
				System.out.println("Nombre:");
				scanner.nextLine();
				nombre =scanner.nextLine();
				System.out.println("Capacidad:");
				capacidad = scanner.nextInt();
				break;
			}
			
			catch (Exception e) {
				
				System.out.println("Valor ingresado incorrecto");
				scanner.next();
				
			}
		}
		
		new Taller (transportadora, transportadora.getDestinoAsignado(), nombre, capacidad);
		System.out.println("Taller cambido correctamente");
    	
    }
    
    public static Transportadora elegirTransportadora () {
    	Transportadora a = null;
    	if (Transportadora.getTransportadoras() != null) {
    		
    		System.out.println("Elija una transportadora:");
    		
    		if (Transportadora.getTransportadoras().size() > 0) {
    			
    			for (Transportadora transportadora : Transportadora.getTransportadoras()) {
    				
    				System.out.println((Transportadora.getTransportadoras().indexOf(transportadora)+1) + ". " + transportadora.getNombre());
    				
    			}
    			while (true) {
    				try {
    					
    					return (Transportadora.getTransportadoras().get(scanner.nextInt()-1));
    			
    				}
    				
    				catch (Exception e) {
    					
    					System.out.println("Valor ingresado incorrecto");
    					scanner.next();
    					
    				}
    			}
    		}
    		
    		else {
    			
    			System.out.println("Actualmente no hay ninguna transportadora. Agregue una antes de continuar");
    			
    			
    		}
    	}
    	return a;
    }
    
    public static void agregarMecanico (Transportadora transportadora) {
    	int id;
    	int edad;
    	String nombre;
    	char genero;
    	ArrayList <Viaje> historial;
    	int experiencia;
    	int dinero;
    	ArrayList<Factura> factura;
    	Taller taller;
    	int resContrato;
    	int diasTrabajados;
    	
    	while (true) {
    		
    		try {
				System.out.println("Id:");
				id = scanner.nextInt();
				System.out.println("Edad:");
				edad = scanner.nextInt();
				System.out.println("Nombre");
				scanner.nextLine();
				nombre = scanner.nextLine();
				System.out.println("Sexo (m,f):");
				genero = scanner.next().charAt(0);
				historial = new ArrayList <Viaje> ();
				System.out.println("Experiencia (0-20):");
				experiencia = scanner.nextInt();
				dinero = 2000;
				factura = new ArrayList <Factura> ();
				taller = transportadora.getTaller();
				System.out.println("Dias restantes del contrato");
				resContrato = scanner.nextInt();
				diasTrabajados = 0;
				
				break;
    		}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    		}
		
    	}
		//transportadora.getTaller().agregarMecanico(new Mecanico (id, edad, nombre, genero, historial, experiencia, dinero, factura, taller, resContrato, diasTrabajados));
		new Mecanico (id, edad, nombre, genero, historial, experiencia, dinero, factura, taller, resContrato, diasTrabajados);
    }
    
    
    public static boolean quitarMecanico (Transportadora transportadora) {
    	
    	int num;
    	
    	System.out.println("Escoja el mecanico que desea eliminar:");
    	
    	for (Mecanico mecanico : transportadora.getTaller().getMecanicos()) {
    		
    		System.out.println((transportadora.getTaller().getMecanicos().indexOf(mecanico)+1) + ". " + "Nombre: " + mecanico.getNombre() + "; ID: " + mecanico.getId());
    		
    	}
    	while (true) {
    		
    		try {
    		num = scanner.nextInt() - 1;
    		break;
    	
    		}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    			
    		}
    	}
    	
    	if (transportadora.getTaller().getMecanicos().get(num).getEstado() == true) {
    		
    		transportadora.getTaller().removerMecanico(transportadora.getTaller().getMecanicos().get(num));
    		
    		return true;
    	}
    	
    	else {
    		
    		System.out.println("El mecanico no se puede remover pues tiene tareas pendientes");
    		
    		return false;
    	}
    }
    
    
    public static boolean agregarVehiculo (Transportadora trans) {
    	String placa;
    	String modelo;
    	double precio;
    	double velocidad;
    	TipoVehiculo tipo;
    	
    	while (true) {
    		
    		try {
		    	scanner.nextLine();
		    	System.out.println ("Placa");
		    	placa = scanner.nextLine();
		    	System.out.println ("Modelo");
		    	modelo = scanner.nextLine();
		    	System.out.println ("Precio");
		    	precio = scanner.nextDouble();
		    	System.out.println ("Velocidad promedio");
		    	velocidad = scanner.nextDouble();
		    	System.out.println ("Tipo de vehiculo");
		    	System.out.println ("Elija  una opción:");
		    	break;
	    	}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    			
    		}
    	}
    	
    	int i = 1;
    	
    	for (TipoVehiculo tipos : TipoVehiculo.values()) {
    		
    		System.out.println (i + ". " + tipos);
    		i++;
    	}
    	while (true) {
    		
    		try {
    			tipo = TipoVehiculo.values()[scanner.nextInt() - 1];
    			break;
    	
    		}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    		}
    	}
    	
    	Transportadora transportadora = trans;
    	
    	Vehiculo vehiculo = new Vehiculo (placa, modelo, precio, velocidad, tipo, transportadora);
    	
    	
    	
    	
    	if (vehiculo.getPrecio() <= vehiculo.getTransportadora().getDinero()) {
    		
    		vehiculo.getTransportadora().agregarVehiculo(vehiculo);
    		Main_4.vehiculo = vehiculo;
    		return true;
    		
    	}
    	
    	else {
    		
    		System.out.println("No tiene suficiente dinero para comprar el vehiculo");
    		return false;
    	}
    	
    }
    
    
    public static void quitarVehiculo (Transportadora transportadora) {
    	
    	int num;
    	int opcion;
    	
    	System.out.println("Escoja el vehiculo que desea eliminar:");
    	
    	for (Vehiculo auto : transportadora.getVehiculos()) {
    		
    		System.out.println((transportadora.getVehiculos().indexOf(auto)+1) + ". " + "Placa: " + auto.getPlaca() + "; Modelo: " + auto.getModelo());
    		
    	}
    	while (true) {
    		try {
    		num = scanner.nextInt() - 1;
    		break;
    	
    		}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    		}
    	}
    	while (true) {
    		
    		
    		try {
		    	System.out.println("Escoja Una opcion:");
		    	System.out.println("1. Vender vehiculo");
		    	System.out.println("2. Desechar vehiculo");
		    	opcion = scanner.nextInt();
		    	break;
    		}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    		}
    	}
    	
    	switch (opcion) {
    	
    		case 1:
    			
    			transportadora.getTaller().venderVehiculo(transportadora.getVehiculos().get(num));
    			break;
    			
    			
    		case 2:
    			
    			transportadora.removerVehiculo(transportadora.getVehiculos().get(num));
    			break;
    		
    		default:
    			
    			System.out.println("Opcion no valida");
    	}
    	
    	
    	
    }
    
    public static void quitarVehiculo (Vehiculo vehiculo) {
    	
    	int opcion;
    	
    	while (true) {
    		
    		try {
		    	System.out.println("Escoja Una opcion:");
		    	System.out.println("1. Vender vehiculo");
		    	System.out.println("2. Desechar vehiculo");
		    	opcion = scanner.nextInt();
		    	break;
    		}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    			
    		}
    	
    	}
    	
    	switch (opcion) {
    	
    		case 1:
    			
    			vehiculo.getTransportadora().getTaller().venderVehiculo(vehiculo);
    			break;
    			
    			
    		case 2:
    			
    			vehiculo.getTransportadora().removerVehiculo(vehiculo);
    			break;
    			
    		default:
    			
    			System.out.println("Opcion no valida");
    	}
    	
    }
    
    
    public static Vehiculo elegirVehiculo (Transportadora transportadora) {
    	
    	System.out.println ("Escoja un vehiculo:");
    	
    	for (Vehiculo auto : transportadora.getVehiculos()) {
    		
    		System.out.println((transportadora.getVehiculos().indexOf(auto)+1) + ". " + "Placa: " + auto.getPlaca() + "; Modelo: " + auto.getModelo());
    		
    	}
    	while (true) {
    		
    		try {
    		
    			return (transportadora.getVehiculos().get(scanner.nextInt()-1));
    	
    		}
    		
    		catch (Exception e) {
    			
    			System.out.println("Valor ingresado incorrecto");
				scanner.next();
    		}
    	}
    	
    }
    
    
    /**public static Transportadora elegirTransportadora () {
    	
    	System.out.println ("Escoja una transportadora");
    	
    	for (Transportadora transportadora : Transportadora.getTransportadoras()) {
    		
    		System.out.println((Transportadora.getTransportadoras().indexOf(transportadora)+1) + ". " + transportadora.getNombre());
    		
    	}
    	
    	return (Transportadora.getTransportadoras().get(scanner.nextInt()-1));
    }**/
    
    
    public static boolean verificarIntegridad (Vehiculo vehiculo) {
    	
    	System.out.println ("La integridad del vehiculo es: " + vehiculo.getIntegridad());
    	
    	if (vehiculo.getIntegridad() != 100) {
    		
    		return false;
    		

    	}
    	
    	return true;
    }
    
    public static void repararVehiculo (Vehiculo vehiculo) {
    	
    	int opcion;
    	
    	if (vehiculo.getTransportadora().getTaller().getMecanicos().size() > 0) {
    		
	    	ArrayList <String> cotizacion = vehiculo.getTransportadora().getTaller().generarCotizacion(vehiculo);
	    	long precio = Long.valueOf(cotizacion.get(0)).longValue();
	    	int tiempo = Integer.parseInt(cotizacion.get(1));
	    	
	    	
	    	while (true) {
		    	try {
		    		System.out.println("El precio de la reparacion sera: " + precio);
			    	System.out.println("La reparacion se tardara " + (int) (tiempo/3600) + "horas; " + (int) ((tiempo % 3600) / 60) + "minutos");
			    	System.out.println("¿realizar reparacion?");
			    	System.out.println("1. Si");
			    	System.out.println("2. No");
			    	opcion = scanner.nextInt();
			    	break;
		    	}
		    	
		    	catch (Exception e) {
		    		
		    		System.out.println("Valor ingresado incorrecto");
					scanner.next();
		    	}
	    	}
			
	    	
	    	switch (opcion) {
				
				case 1:
					if (!vehiculo.isReparando()) {
						
						if (vehiculo.getTransportadora().getDinero() >= precio) {
						
							vehiculo.getTransportadora().getTaller().agregarVehiculoReparacion(vehiculo);
							
							vehiculo.getTransportadora().getTaller().aplicarGastos(vehiculo);
							
							System.out.println("Gastos aplicados");
							System.out.println("La transportadora: " + vehiculo.getTransportadora().getNombre() +" ahora tiene: " + vehiculo.getTransportadora().getDinero() + "$");
							System.out.println("El vehiculo fue agregado a la cola de reparaciones");
							
						}
						
						else {
							
							System.out.println("La transportadora no cuenta con dinero suficiente");
						}
					
					}
				
				
					else {
						
						System.out.println("No se puede reparar el vehiculo pues ya esta siendo reparado o vendido");
					}
					break;
					
				case 2:
					
					break;
					
				default:
					
					System.out.println("Opcion no valida");
			}
    	}
    	
    	else {
    		System.out.println("El taller no cuenta con mecanicos para realizar la reparacion, agregue nuevos mecanicos");
    	}
		
    }
    public static ArrayList <Vehiculo> vehiculosDisponibles (Transportadora transportadora) {
    	
    	ArrayList <Vehiculo> vehiculos = new ArrayList <Vehiculo> ();
    	
    	for (Vehiculo auto : transportadora.getVehiculos()) {
    		
    		if (auto.disponibilidad()) {
    			
    			vehiculos.add(auto);
    		}
    	}
    	
    	return (vehiculos);
    }
    
   /** public static void main (String [] args) {
    	Persona admin = new Pasajero (TipoPasajero.VIP);
    	Terminal terminal = new Terminal("terminal norte", 100000, 10, 0, 1, new ArrayList <Transportadora> (), new ArrayList <Viaje> (), new ArrayList <Viaje> (), new ArrayList <Destino> (), 10, Destino.MEDELLIN, admin);
    	Transportadora transportadora1 = new Transportadora ("Cootratan", 1000, new ArrayList<Conductor> (),new ArrayList<Pasajero> (), new ArrayList<Vehiculo> (), new ArrayList<Viaje> (), Destino.MEDELLIN, terminal, new ArrayList <Destino> (), new ArrayList<Viaje> (), admin, 3);
    	//Taller taller = new Taller (transportadora1, Destino.MEDELLIN, "Taller autos", 4);
    	//Mecanico mecanico1 = new Mecanico (123, 40, "Pedro", 'M', new ArrayList <Viaje> (), 20, 100, new ArrayList <Factura> (), taller, 100, 20);
    	
    	System.out.println(terminal.getCapacidadVehiculos());
    	ejecutar ();
    }**/
    
}

