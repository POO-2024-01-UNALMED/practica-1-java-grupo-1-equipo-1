package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import baseDatos.Serializador;
import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.Pasajero;

public class Main_1 {
	
public static void main(String[] args) {
	    
		Serializador.objetosBase();
	    
	    String nombre = "";
	    TipoPasajero tipo = null;
	    String destino;
	    Destino destinoDeseado = null;
	    Scanner scanner = new Scanner(System.in);
	    int cantidad = 0;
	    TipoVehiculo vehiculo = null;
	    int modalidad = -1;
	    boolean regresar = false;
	    boolean regresar2 = false;
	    int contador = 0;
	    Transportadora transportadoraSeleccionada = null;
	    Viaje viajeSeleccionado = null;
	    int tipoPasajero = 0;
	    int indiceV = 0;
	    int indiceT = 0;
	    int id;
	    
	    ArrayList<Viaje> viajesDisponibles = Terminal.viajesDisponibles(); 

	    while (!regresar) {
	        System.out.println("\nEjecutando Funcionalidad 1...\n");

	        if (contador == 0) {
	            System.out.println("1. Vender viaje");
	        } else {
	            System.out.println("1. Vender otro viaje");
	        }
	        
	        contador++;

	        System.out.println("2. Regresar al menú Principal\n");

	        int opcion = scanner.nextInt();
	        scanner.nextLine();
	        
	        regresar2 = false;
	        while (!regresar2) {
	            switch (opcion) {
	                case 1:
	                    boolean tipoPasajeroValido = false;
	                    
	                    while (!tipoPasajeroValido) {
	                        System.out.println("\nIngrese el nombre del destino\n");
	                        destino = scanner.nextLine().toUpperCase();
	                        
	                        if (Terminal.esDestinoValido(destino)) {
	                            destinoDeseado = Destino.valueOf(destino);
	                            System.out.println("Destino seleccionado: " + destinoDeseado + "\n");
	                        } else {
	                            System.out.println("Destino no válido. Por favor, ingresa uno de los valores permitidos.");
	                            continue;
	                        }
	                        
	                        boolean tipoPasajeroSeleccionado = false;
	                        while (!tipoPasajeroSeleccionado) {
	                            System.out.println("\nElija el tipo de pasajero\n" +
	                                    "1. Estudiante\n" +
	                                    "2. Discapacitado\n" +
	                                    "3. Regular\n" +
	                                    "4. VIP\n");
	                            
	                                          
	                            tipoPasajero = scanner.nextInt();
	                            scanner.nextLine();
	                            
	                            if(tipoPasajero == 2) {
	                            	tipo=TipoPasajero.DISCAPACITADO;
	                            }else if(tipoPasajero == 3) {
	                            	tipo=TipoPasajero.REGULAR;
	                            }else if(tipoPasajero == 4) {
	                            	tipo=TipoPasajero.VIP;
	                            }
	                            
	                            if (tipoPasajero >= 1 && tipoPasajero <= 4) {
	                                if (tipoPasajero != 1) {
	                                    System.out.println("\nCuantos asientos desea vender");
	                                    cantidad = scanner.nextInt();
	                                    scanner.nextLine();
	                                } else {
	                                    cantidad = 1;
	                                }
	                                
	                                switch (tipoPasajero) {
	                                    case 1:
	                                        viajesDisponibles = Terminal.viajesParaEstudiantes(destinoDeseado);
	                                        break;
	                                    case 2:
	                                    case 3:
	                                        viajesDisponibles = Terminal.viajesParaRegularesYDiscapacitados(cantidad, destinoDeseado);
	                                        break;
	                                    case 4:
	                                        viajesDisponibles = Terminal.viajesParaVips(cantidad, destinoDeseado);
	                                        break;
	                                }
	                                
	                                if (viajesDisponibles.isEmpty()) {
	                                    System.out.println("No hay viajes disponibles para este tipo de pasajero. ¿Desea cambiar el tipo de pasajero? (S/N)");
	                                    String respuesta = scanner.nextLine();
	                                    if (respuesta.equalsIgnoreCase("N")) {
	                                        tipoPasajeroSeleccionado = true;
	                                        tipoPasajeroValido = true;
	                                    }
	                                } else {
	                                    tipoPasajeroSeleccionado = true;
	                                }
	                            } else {
	                                System.out.println("Tipo de pasajero no válido. Inténtalo de nuevo.");
	                            }
	                        }
	                        
	                        if (!viajesDisponibles.isEmpty()) {
	                            if (tipoPasajero != 1) {
	                                boolean modalidadValida = false;
	                                
	                                while (!modalidadValida) {
	                                	if(viajesDisponibles.isEmpty()) {viajesDisponibles = Terminal.getViajes();}
	                                    System.out.println("\nElegir viaje por...\n" +
	                                            "1. mayor velocidad\n" +
	                                            "2. salida con mayor antelación\n" +
	                                            "3. más económico\n" +
	                                            "4. transportadora\n");

	                                    if (scanner.hasNextInt()) {
	                                        modalidad = scanner.nextInt();
	                                        scanner.nextLine();
	                                        
	                                        switch (modalidad) {
	                                            case 1:
	                                                viajeSeleccionado = Terminal.masRapido(destinoDeseado, viajesDisponibles);
	                                                break;
	                                            case 2:
	                                                // Implementar lógica para seleccionar viaje con salida más pronta
	                                                break;
	                                            case 3:
	                                                viajeSeleccionado = Terminal.masEconomico(destinoDeseado, viajesDisponibles);
	                                                break;
	                                            case 4:
	                                            	if (viajesDisponibles.isEmpty()) {
	                                            	    System.out.println("No hay viajes disponibles");
	                                            	    break;
	                                            	} else {
	                                            		if(tipo == TipoPasajero.VIP) {
	                                            			System.out.println("\nElija el tipo de vehículo en el que quiere viajar\n" +
	                                            	                       "1. Bus\n" +
	                                            	                       "2. Vans\n" +
	                                            	                       "3. Taxi");

	                                            	    	indiceV = scanner.nextInt();
	                                            	    	scanner.nextLine();

	                                            	    // Asignar el tipo de vehículo basado en la selección
	                                            	    	switch (indiceV) {
	                                            	        	case 1:
	                                            	            	vehiculo = TipoVehiculo.BUS;
	                                            	            	break;
	                                            	        	case 2:
	                                            	            	vehiculo = TipoVehiculo.VANS;
	                                            	            	break;
	                                            	        	case 3:
	                                            	            	vehiculo = TipoVehiculo.TAXI;
	                                            	            	break;
	                                            	        	default:
	                                            	            	System.out.println("\nTipo de vehículo no válido.");
	                                            	            	continue;
	                                            	    	}

	                                            	    // Filtrar los viajes disponibles según el tipo de vehículo seleccionado
	                                            	    	viajesDisponibles = Terminal.viajesParaVips(cantidad, destinoDeseado, vehiculo);

	                                            	    	if (viajesDisponibles.isEmpty()) {
	                                            	        	System.out.println("No hay viajes disponibles para el tipo de vehículo seleccionado.");
	                                            	        	break;
	                                            	    	} else {
	                                            	        // Mostrar transportadoras con estrellas
	                                            	        	Tablas.transportadorasConEstrellas(viajesDisponibles);

	                                            	        	System.out.println("\nElija una transportadora por su índice\n");
	                                            	        	indiceT = scanner.nextInt();
	                                            	        	scanner.nextLine();

	                                            	        // Verificar que el índice está dentro del rango válido
	                                            	        	ArrayList<Transportadora> transportadorasDisponibles = Terminal.obtenerTransportadorasUnicas(viajesDisponibles);
	                                            	        	if (indiceT >= 0 && indiceT < transportadorasDisponibles.size()) {
	                                            	            	transportadoraSeleccionada = transportadorasDisponibles.get(indiceT);

	                                            	            // Encontrar el viaje asociado con la transportadora seleccionada
	                                            	            	boolean viajeEncontrado = false;
	                                            	            	for (Viaje viaje : viajesDisponibles) {
	                                            	                	if (viaje.getLlegada() == destinoDeseado && viaje.getVehiculo().getTransportadora().equals(transportadoraSeleccionada)) {
	                                            	                    	viajeSeleccionado = viaje;
	                                            	                    	viajeEncontrado = true;
	                                            	                    	break;
	                                            	                	}
	                                            	            	}

	                                            	            	if (!viajeEncontrado) {
	                                            	            		System.out.println("No se encontró un viaje con la transportadora seleccionada.");
	                                            	            	}
	                                            	        	} else {
	                                            	        		System.out.println("Índice no válido. Por favor, elija un índice válido.");
	                                            	        	}
	                                            	    	}
	                                            		} else if(tipo == TipoPasajero.DISCAPACITADO||tipo == TipoPasajero.REGULAR){
	                                            			System.out.println("\nElija el tipo de vehículo en el que quiere viajar\n" +
                                         	                       "1. Bus\n" +
                                         	                       "2. Vans\n" +
                                         	                       "3. Taxi\n"+
                                         	                       "4. Escalera");

                                         	    	indiceV = scanner.nextInt();
                                         	    	scanner.nextLine();

                                         	    // Asignar el tipo de vehículo basado en la selección
                                         	    	switch (indiceV) {
                                         	        	case 1:
                                         	            	vehiculo = TipoVehiculo.BUS;
                                         	            	break;
                                         	        	case 2:
                                         	            	vehiculo = TipoVehiculo.VANS;
                                         	            	break;
                                         	        	case 3:
                                         	            	vehiculo = TipoVehiculo.TAXI;
                                         	            	break;
                                         	            
                                         	        	case 4:
                                         	            	vehiculo = TipoVehiculo.ESCALERA;
                                         	            	break;
                                         	            	
                                         	        	default:
                                         	            	System.out.println("Tipo de vehículo no válido.\nElegir nuevamente");
                                         	            	continue;
                                         	    	}

                                         	    // Filtrar los viajes disponibles según el tipo de vehículo seleccionado
                                         	    	viajesDisponibles = Terminal.viajesParaRegularesYDiscapacitados(cantidad,destinoDeseado, vehiculo);

                                         	    	if (viajesDisponibles.isEmpty()) {
                                         	        	System.out.println("No hay viajes disponibles para el tipo de vehículo seleccionado.");
                                         	        	break;
                                         	    	} else {
                                         	        // Mostrar transportadoras con estrellas
                                         	        	Tablas.transportadorasConEstrellas(viajesDisponibles);

                                         	        	System.out.println("\nElija una transportadora por su índice\n");
                                         	        	indiceT = scanner.nextInt();
                                         	        	scanner.nextLine();

                                         	        // Verificar que el índice está dentro del rango válido
                                         	        	ArrayList<Transportadora> transportadorasDisponibles = Terminal.obtenerTransportadorasUnicas(viajesDisponibles);
                                         	        	if (indiceT >= 0 && indiceT < transportadorasDisponibles.size()) {
                                         	            	transportadoraSeleccionada = transportadorasDisponibles.get(indiceT);

                                         	            // Encontrar el viaje asociado con la transportadora seleccionada
                                         	            	boolean viajeEncontrado = false;
                                         	            	for (Viaje viaje : viajesDisponibles) {
                                         	                	if (viaje.getLlegada() == destinoDeseado && viaje.getVehiculo().getTransportadora().equals(transportadoraSeleccionada)) {
                                         	                    	viajeSeleccionado = viaje;
                                         	                    	viajeEncontrado = true;
                                         	                    	break;
                                         	                	}
                                         	            	}

                                         	            	if (!viajeEncontrado) {
                                         	            		System.out.println("No se encontró un viaje con la transportadora seleccionada.");
                                         	            	}
                                         	        	} else {
                                         	        		System.out.println("Índice no válido. Por favor, elija un índice válido.");
                                         	        	}
                                         	    	}
	                                            		}
	                                            	}
	                                                break;
	                                                
	                                            default:
	                                                System.out.println("Modalidad no válida.");
	                                                continue;
	                                        }
	                                        
	                                        if (viajeSeleccionado == null) {
	                                            System.out.println("No hay viajes disponibles para esta modalidad. ¿Desea cambiar la modalidad? (S/N)");
	               
	                                            String respuesta = scanner.nextLine();
	                                            if (respuesta.equalsIgnoreCase("N")) {
	                                                modalidadValida = false;
	                                                break;
	                                            }
	                                        } else {
	                                            modalidadValida = true;
	             
	                                        }
	                                    } else {
	                                        System.out.println("Modalidad no válida. Debe ingresar un número.");
	                                        scanner.next();
	                                    }
	                                }
	                            } else {
	                            	tipo = TipoPasajero.ESTUDIANTE;
	                                boolean viajeEstudianteEncontrado = false;
	                                while (!viajeEstudianteEncontrado) {
	                                    System.out.println("\nElija el tipo de vehículo en el que quiere viajar\n" +
	                                            "1. Bus\n" +
	                                            "2. Escalera\n" +
	                                            "3. Vans\n");
	                                    
	                                    indiceV = scanner.nextInt();
	                                    scanner.nextLine();
	                                    
	                                    if (indiceV == 1) {
	                                        vehiculo = TipoVehiculo.BUS;
	                                    } else if (indiceV == 2) {
	                                        vehiculo = TipoVehiculo.ESCALERA;
	                                    } else if (indiceV == 3){
	                                        vehiculo = TipoVehiculo.VANS;
	                                    }else {System.out.println("\ntipo de vehiculo no valido\n");
	                                    	continue;}
	                                    
	                                    viajesDisponibles = Terminal.viajesParaEstudiantes(destinoDeseado, vehiculo);

	                                    if (viajesDisponibles.isEmpty()) {
	                                        System.out.println("No hay viajes disponibles para este vehículo. ¿Desea elegir otro vehículo? (S/N)");
	                                        String respuesta = scanner.nextLine();
	                                        if (respuesta.equalsIgnoreCase("N")) {
	                                            System.out.println("¿Desea elegir otro destino? (S/N)");
	                                            respuesta = scanner.nextLine();
	                                            if (respuesta.equalsIgnoreCase("S")) {
	                                                System.out.println("\nIngrese el nuevo destino\n");
	                                                destino = scanner.nextLine().toUpperCase();
	                                                if (Terminal.esDestinoValido(destino)) {
	                                                    destinoDeseado = Destino.valueOf(destino);
	                                                    System.out.println("Nuevo destino seleccionado: " + destinoDeseado + "\n");
	                                                } else {
	                                                    System.out.println("Destino no válido. Volviendo al menú principal.");
	                                                    break;
	                                                }
	                                            } else {
	                                                System.out.println("No hay opciones disponibles. Volviendo al menú principal.");
	                                                break;
	                                            }
	                                        }
	                                    } else {
	                                        viajeEstudianteEncontrado = true;
	                                    }
	                                

	                                if (viajeEstudianteEncontrado) {
	                                    Tablas.transportadorasSinEstrellas(viajesDisponibles);
	                                    System.out.println("\nElija una transportadora por su índice\n");
	                                    indiceT = scanner.nextInt();
	                                    scanner.nextLine();
	                                    transportadoraSeleccionada = Terminal.obtenerTransportadorasUnicas(viajesDisponibles).get(indiceT);
	                                    for (Viaje viaje : viajesDisponibles) {
	                                        if (viaje.getLlegada() == destinoDeseado && viaje.getVehiculo().getTransportadora() == transportadoraSeleccionada) {
	                                            viajeSeleccionado = viaje;
	                                            break;
	                                        }
	                                    }
	                                }
	                            }
	                            break;
                                
	                            }
	                            
	                            if (viajeSeleccionado != null) {
	                                Tablas.tablaInformacionViaje(viajeSeleccionado);
	                                System.out.println("¿Está seguro de vender este viaje? (S/N)");
	                                String confirmar = scanner.nextLine();
	                                if (confirmar.equalsIgnoreCase("S")) {
	                                    tipoPasajeroValido = true;
	                                } else {
	                                    viajeSeleccionado = null;
	                                }
	                            }
	                        }
	                    }
	                    break;

	                case 2:
	                    regresar = true;
	                    regresar2 = true;
	                    System.out.println("Regresando al Menú Principal");
	                    break;

	                default:
	                    System.out.println("Opción no válida");
	            }
	            
	            if (!regresar2 && viajeSeleccionado != null) {
	                System.out.println("\nIngrese su nombre");
	                nombre = scanner.nextLine();
	                System.out.println("\nIngrese su número de identificación");
	                id = scanner.nextInt();
	                System.out.println("\nIngrese su edad");
	                int edad = scanner.nextInt();
	                System.out.println("\nIngrese su género");
	                char genero = scanner.next().charAt(0);
	                double valorTotal = viajeSeleccionado.getTarifa()*cantidad;
	                System.out.println("\nValor a pagar: "+ valorTotal);
	                System.out.println("\nIngrese su dinero");
	                double dinero = scanner.nextDouble();
	                scanner.nextLine();
	                if (dinero>=valorTotal) {
	                	
	                	Pasajero pasajero = Pasajero.nuevoPasajero(tipo, id, edad, nombre, genero, valorTotal);
	                	
	                	Tablas.tablaFactura(viajeSeleccionado, pasajero, cantidad, nombre);
	                	double cambio = dinero-valorTotal;
	                	System.out.println("Su Cambio:" + cambio);
		                System.out.println("Venta realizada");
		                //Pasajero.verificarVenta(viajeSeleccionado,pasajero);
		                viajeSeleccionado = null;
		                regresar2 = true;
		                
	            	}else {
	            		System.out.println("Valor entregado: " + dinero);
	            		System.out.println("Valor insuficiente, regresando...");
	            		regresar2 = false;

	            		}
	                
	            }
	        }
	    }
	    
	    scanner.close();
	}
}