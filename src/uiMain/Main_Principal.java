package uiMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import baseDatos.*;
import gestorAplicacion.constantes.*;
import gestorAplicacion.tiempo.Tiempo;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;

public class Main_Principal {
	
	public static Scanner sc = new Scanner(System.in);
    private static Scanner scanner = new Scanner(System.in);
    private static boolean regresar = false;
    private static int opcion;
    private static Vehiculo vehiculo;
	
	 public static int readInt() {
	    	
	    	return sc.nextInt();
	    }
	    
	    public static String readString() {
	    	
	    	return sc.nextLine();
	    
	    }
	
    static {
    	Serializador.objetosBase();
    	//Deserializador.deserializarListas();
    }
    
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("Seleccione una acción:");
            System.out.println("1. Funcionalidad 1 - Venta de Viajes");
            System.out.println("2. Funcionalidad 2 - Gestion Conductores");
            System.out.println("3. Funcionalidad 3 - Facturacion y Finanzas");
            System.out.println("4. Funcionalidad 4 - Administrar Vehiculos");
            System.out.println("5. Funcionalidad 5 - Programación de Viajes");
            System.out.println("6. Salir\n");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ventaViajes(); // Funcionalidad 1
                    break;
                case 2:
                	gestionConductores(); // Funcionalidad 2
                    break;
                case 3:
                	
                	facturacionYFinanzas(); // Funcionalidad 3
                    break;
                    
                case 4:
                	ejecutar4(); // Funcionalidad 4
                    break;
                case 5:
                	programacionViajes(); // Funcionalidad 5
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    apagarTiempo(); // Fin del Tiempo
                    //Serializador.serializarListas();;
                    break;
                default:
                    System.out.println("Opción no válida, Marque de 1,6");
            }
        }
       
        
        
    }
	
	//Funcionalidad 1
	
	public static void ventaViajes() {
		//Serializador.objetosBase();
	    
	    String nombre = "";
	    TipoPasajero tipo = null;
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

	                        ArrayList<Destino> destinos = new ArrayList<>();
	                        for (Destino destino : Destino.values()) {
	                            destinos.add(destino);
	                        }
	                        
	                        Tablas.tablaDestinos(destinos); // Llamado al formato de las tablas
	                        
	                        System.out.println("Elige el destino por su número:");
	                        int destinoSeleccionado = scanner.nextInt();
	                        
	                        if (destinoSeleccionado < 1 || destinoSeleccionado > destinos.size()) {
	                            System.out.println("Opción no válida. Por favor, elige un número del 1 al " + destinos.size());
	                            continue;
	                        }
	                        destinoDeseado = destinos.get(destinoSeleccionado);
	                        
	                        if (Terminal.viajesDestino(destinoDeseado)){
		                        System.out.println("Has elegido el destino: " + destinoDeseado.name());
		                    } else {
		                        System.out.println("No hay viajes con ese destino, ingrese otro");
		                        break;
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
	                                    boolean cantidadValida = false;
	                                    while (!cantidadValida) {
	                                        System.out.println("\nCuantos asientos desea vender");
	                                        cantidad = scanner.nextInt();
	                                        scanner.nextLine();
	                                        if (tipoPasajero == 2 || tipoPasajero == 3 || tipoPasajero == 4) {
	                                            if (cantidad >= 43) {
	                                                System.out.println("\nIngrese una cantidad inferior a 43");
	                                            } else {
	                                                cantidadValida = true;
	                                            }
	                                        } else {
	                                            cantidadValida = true;
	                                        }
	                                    }
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
	                                                viajeSeleccionado = Terminal.obtenerViajeMasProximo(viajesDisponibles);
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
	                                                            boolean transportadoraSeleccionadaValida = false;
	                                                            while (!transportadoraSeleccionadaValida) {
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

	                                                                    if (viajeEncontrado) {
	                                                                        transportadoraSeleccionadaValida = true;
	                                                                    } else {
	                                                                        System.out.println("No se encontró un viaje con la transportadora seleccionada. Por favor, elija otra.");
	                                                                    }
	                                                                } else {
	                                                                    System.out.println("Índice no válido. Por favor, elija un índice válido.");
	                                                                }
	                                                            }
	                                                        }
	                                                    } else if(tipo == TipoPasajero.DISCAPACITADO || tipo == TipoPasajero.REGULAR){
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
	                                                            boolean transportadoraSeleccionadaValida = false;
	                                                            while (!transportadoraSeleccionadaValida) {
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

	                                                                    if (viajeEncontrado) {
	                                                                        transportadoraSeleccionadaValida = true;
	                                                                    } else {
	                                                                        System.out.println("No se encontró un viaje con la transportadora seleccionada. Por favor, elija otra.");
	                                                                    }
	                                                                } else {
	                                                                    System.out.println("Índice no válido. Por favor, elija un índice válido.");
	                                                                }
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
	                                            System.out.println("No hay viajes disponibles ¿Desea cambiar la modalidad? (S/N)");
	               
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
	                                    System.out.println("\nElija el tipo de vehículo en el que quiere viajar\n" +"1. Bus\n" +
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
	                                            	destinos = new ArrayList<>();
	                                                for (Destino destino : Destino.values()) {
	                                                    destinos.add(destino);
	                                                }
	                                                
	                                                Tablas.tablaDestinos(destinos); // Llamado al formato de las tablas
	                                                
	                                                System.out.println("Elige el destino por su número:");
	                                                destinoSeleccionado = scanner.nextInt();
	                                                
	                                                if (destinoSeleccionado < 1 || destinoSeleccionado > destinos.size()) {
	                                                    System.out.println("Opción no válida. Por favor, elige un número del 1 al " + destinos.size());
	                                                    continue;
	                                                }
	                                                
	                                                destinoDeseado = destinos.get(destinoSeleccionado);  // Destino como parametro para el constructor ma adelante
	                                                System.out.println("Has elegido el destino: " + destinoDeseado.name());
	                                            } else {
	                                                System.out.println("No hay opciones disponibles. Volviendo al menú principal.");
	                                                break;
	                                            }
	                                        }
	                                    } else {
	                                        viajeEstudianteEncontrado = true;
	                                    }
	                                

	                                if (viajeEstudianteEncontrado) {
	                                    boolean transportadoraSeleccionadaValida = false;
	                                    while (!transportadoraSeleccionadaValida) {
	                                        Tablas.transportadorasSinEstrellas(viajesDisponibles);
	                                        System.out.println("\nElija una transportadora por su índice\n");
	                                        indiceT = scanner.nextInt();
	                                        scanner.nextLine();
	                                        
	                                        ArrayList<Transportadora> transportadorasDisponibles = Terminal.obtenerTransportadorasUnicas(viajesDisponibles);
	                                        if (indiceT >= 0 && indiceT < transportadorasDisponibles.size()) {
	                                            transportadoraSeleccionada = transportadorasDisponibles.get(indiceT);
	                                            for (Viaje viaje : viajesDisponibles) {
	                                                if (viaje.getLlegada() == destinoDeseado && viaje.getVehiculo().getTransportadora() == transportadoraSeleccionada) {
	                                                    viajeSeleccionado = viaje;
	                                                    transportadoraSeleccionadaValida = true;
	                                                    break;
	                                                }
	                                            }
	                                            if (!transportadoraSeleccionadaValida) {
	                                                System.out.println("No se encontró un viaje con la transportadora seleccionada. Por favor, elija otra.");
	                                            }
	                                        } else {
	                                            System.out.println("Índice no válido. Por favor, elija un índice válido.");
	                                        }
	                                    }
	                                }
	                            }
	                            break;
	                                
	                            }
	                            
	                            if (viajeSeleccionado != null) {
	                                Tablas.tablaInformacionViaje(viajeSeleccionado, tipo);
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
	                    break;
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
	                Pasajero pasajero = Pasajero.nuevoPasajero(tipo, id, edad, nombre, genero);
	                double valorTotal = viajeSeleccionado.getTarifa()*cantidad;
	                pasajero.setViaje(viajeSeleccionado);
	                pasajero.setDinero(valorTotal);
	                pasajero.descuento(cantidad);//solo se esta pasando a la transportadora el dinero de un pasaje
	                
	                System.out.println("\nValor a pagar: "+ valorTotal);
	                System.out.println("\nIngrese su dinero");
	                
	                
	                double dinero = scanner.nextDouble();
	                scanner.nextLine();
	                if (dinero>=valorTotal) {
	                	
	                	pasajero.descuento();
	                	
	                	for (int i = 0; i<cantidad;i++) {
	                		viajeSeleccionado.getPasajeros().add(pasajero);
	                	}
	                                  
	                    Tablas.tablaFactura(viajeSeleccionado, pasajero, cantidad);
	                    double cambio = dinero-valorTotal;
	                    System.out.println("Su Cambio:" + cambio);
	                    System.out.println("Venta realizada");
	                    //Terminal.verificarVentaTicket(viajeSeleccionado,pasajero);
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
	    
	  
	}

	

//Funcionalidad 2
	
	public static void gestionConductores() {
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
        	
            System.out.println("\nSeleccione una acción:");
            System.out.println("1. Despedir conductor");
            System.out.println("2. Contratar conductor");
            System.out.println("3. Modificar conductor");
            System.out.println("4. Regresar al menú Principal\n");


            int opcion = scanner.nextInt();

            switch (opcion) {
            
                case 1:
                	
                	mostrarConductoresActivos(transportaElegida);
                	System.out.println("\nDigite el id del conductor a despedir:");
                	opcion = scanner.nextInt();
                	
                	if (transportaElegida.encontrarConductor(opcion) == null) {
    					
    					System.out.println("No se encontro el conductor con Id #" + opcion);
    					
    				} else {
    					System.out.println(transportaElegida.despedirConductor(opcion));
    				}
                    break;
                    
                case 2:
                	
                	
                	
                	if (transportaElegida.mostrarConductRegistrados().equals("")) {
                		
                		System.out.println("No hay conductores disponibles para contratar.");
                		break;
                		
                	}
                	
                	System.out.println("Digite el id del conductor a contratar:");
             		System.out.println(transportaElegida.mostrarConductRegistrados());
             		opcion = scanner.nextInt();
             		System.out.println(transportaElegida.contratarConductor(opcion));
                    break;
                    
                case 3:
                	
                	mostrarConductoresActivos(transportaElegida);
                	System.out.println("Digite el id del conductor que desea modificar:");
                	opcion = scanner.nextInt();
                	Conductor selectedDriver = transportaElegida.encontrarConductor(opcion);
                	
                	if (selectedDriver == null) {
                		System.out.println("No se encontro un conductor con id #" + opcion);
                		break;
                	}
                	
                	System.out.println("\n¿Que desea modificar del conductor?");
                	System.out.println("1. Viajes");
                	System.out.println("2. Vehiculo");             
                	System.out.println("3. Regresar");
                	opcion = scanner.nextInt();
                	
                	switch (opcion) {
                	
                	case 1:
                		
                		if (selectedDriver.getHorario().size() == 0) {
                			
                			System.out.println("Seleccione una opcion:");
                			System.out.println("1. Asignar viaje");
                			System.out.println("2. Regresar");
                			opcion = check(1,2);
                			
                			switch (opcion) {
                			
                			case 1:
                				
                				
                				if (selectedDriver.getVehiculo() != null) {
                					if (transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo(),selectedDriver).equals("")){
                    					System.out.println("No hay viajes disponibles que el conductor pueda tomar");
                    				} else {
                    					System.out.println("Digite el id del viaje que desea asignarle al conductor:");
                        				System.out.println(transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo(),selectedDriver));
                        				
                    					opcion = scanner.nextInt();
                        				
                        				if (transportaElegida.encontrarViaje(opcion) == null) {
                        					System.out.println("No se encontro un viaje con el Id");
                        				} else {
                        					Viaje selectedViaje = transportaElegida.encontrarViaje(opcion);
                        					selectedDriver.vincularYDesvincular(selectedViaje.getConductor(),selectedViaje);
                        					System.out.println("Se asigno el viaje al conductor " + selectedDriver.getNombre());
                        				}
                    				}
                				} else {
                					System.out.println("No es posible asignar viaje porque el conductor no tiene vehiculo asociado");
                				}
                				
                				break;
                				
                			case 2:
                				
                				break;
                				
                			default:
                				
                				System.out.println("Opcion no valida");
                				break;
                				
                			}
                		} else {
                			
                			System.out.println("\nSeleccione una opcion:");
                			System.out.println("1. Asignar viaje");
                			System.out.println("2. Desvincular viaje");
                			System.out.println("3. Regresar");
                			opcion = check(1,3);
                			
                			switch (opcion) {
                			
                			case 1:
                              				
                				if (transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo(),selectedDriver).equals("")){
                					
                					System.out.println("No hay viajes disponibles que el conductor pueda tomar");
                					
                				} else {
                					
                					System.out.println("Digite el id del viaje que desea asignarle al conductor:");
                    				System.out.println(transportaElegida.mostrarViajesDisponibles(Tiempo.diaNombre.getValue(),selectedDriver.getVehiculo().getTipo(),selectedDriver));
      
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
                				
                				System.out.println("Digite el id del viaje que quiere desvincular:");
                				System.out.println(selectedDriver.mostrarViajes());
                				int selectedTrip = check(0,selectedDriver.getHorario().size());
                			
                				System.out.println("Digite el id del conductor que tomara el viaje:");
                				System.out.println(transportaElegida.conductoresDisponibles(selectedDriver.getHorario().get(selectedTrip)));
                				opcion = scanner.nextInt();
                			
                				if (transportaElegida.encontrarConductor(opcion) == null) {
                					
                					System.out.println("No se encontro el conductor con Id #" + opcion);
                					
                				} else {
                					
                					selectedDriver.desvincularYVincular(transportaElegida.encontrarConductor(opcion), selectedDriver.getHorario().get(selectedTrip));
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
                			
                			System.out.println("Seleccione una opcion:");
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
                			
                			System.out.println("Seleccione una opcion:");
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
                	regresar = true;
                    System.out.println("Regresando al Menú Principal");
                    break;
                    
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
	
	
//Funcionalidad 3	
public static void facturacionYFinanzas() {
    	
        Scanner scanner = new Scanner(System.in);
        boolean regresar = false;

        do {
        	
            System.out.println("Seleccione una acción:");
            System.out.println("1. Tarifas Viajes");
            System.out.println("2. transportadoras que han cancelado monto");
            System.out.println("3. Ver estadisticas generales");
            System.out.println("4. Conductores liquidados");
            System.out.println("5. Verificar compra tiquetes");
            System.out.println("6. Regresar al menú Principal\n");

            int opcion = scanner.nextInt();

            switch (opcion) {
            
                case 1:
                    
                	verTarifas();
                    break;
                    
                case 2:
                    
                	transportadorasQueHanCanceladoMonto();
                    break;
                    
                case 3:
                	
                    estadisticasGenerales();
                    break;
                    
                case 4:
                    
                	conductoresLiquidados();
                    break;
                    
                case 5:
                	
                   verificarCompraTiquetes();
                    break;
                    
                case 6:
                	
                    regresar = true;
                    System.out.println("Regresando al Menú Principal");
                    break;
                    
                default:
                	
                    System.out.println("Opción no válida");
            }
            
        }while (!regresar);
        
        
    }
    
    public static void verTarifas() {
    	
    	try {
    		
    		System.out.print("señor administrador, a continuación se le mostrarán las transportadoras disponibles de su terminal ");
        	System.out.println(" \nPara que pueda consultar las respectivas tarifas de la transportadora que desee");
        	int i = 1;
        	
        	if (!(Terminal.getTransportadoras().isEmpty())) {
        		
        		for (Transportadora transportadora: Terminal.getTransportadoras()) {
            		
            		System.out.println(i +". " + transportadora.getNombre() );
            		i++;
            		
            	}
        		
        		System.out.println("Seleccione la transportadora a la cual quiere ver dicha tarifa de viajes");
            	int numeroTransportadoraSeleccionada = Main_Principal.readInt();
            	
            	while (numeroTransportadoraSeleccionada > Terminal.getTransportadoras().size() || numeroTransportadoraSeleccionada <= 0) {
            		
            		System.out.println("Ingrese nuevamente su número, ingresó un numero incorrecto ");
            		numeroTransportadoraSeleccionada = Main_Principal.readInt();
         	
            	}
            	
            		Transportadora transportadoraSeleccionada = Terminal.getTransportadoras().get(numeroTransportadoraSeleccionada - 1); // Se le resta -1 por los índices
            		
            		if (!(transportadoraSeleccionada.getViajesAsignados().isEmpty())) {
                        
                        System.out.println("\nTarifas de viajes para " + transportadoraSeleccionada.getNombre());
                        System.out.println("------------------------------------------------------");
                        System.out.printf("%-5s %-20s %-20s %-10s%n", "No.", "Salida", "Llegada", "Tarifa");
                        System.out.println("------------------------------------------------------");
                        
                        int j = 1;
                
                		for (Viaje v: transportadoraSeleccionada.getViajesAsignados()) {
                			
                			 System.out.printf("%-5d %-20s %-20s $%-10.2f%n", j, v.getSalida().name(), v.getLlegada().name(), v.getTarifa());
                    		j++;
                    	
                    	}
                        
                	}else { System.out.println("No hay viajes asignados a esta transportadora");}
                     			
        	}else { System.out.println("No hay transportadoras disponibles");}
    		
    		
    	}catch(InputMismatchException e) {
    		
    		System.out.println("Entrada inválida. Debe ingresar un número entero.");
    		verTarifas();
        
    	}
    		
    }
    
    public static void transportadorasQueHanCanceladoMonto() {
    	
    	try {
    		
    		System.out.print("A continuación se le mostrarán las transportadoras disponibles de su terminal ");
        	System.out.println(" \nPara que pueda consultar si dichas transportadoras han cancelado monto o no");
       
        	ArrayList <Transportadora> transportadorasQueNoHanPagado = new ArrayList<>();
        	
        	if (!(Terminal.getTransportadoras().isEmpty())) {
        		
        			int i = 1;
        			
        			 System.out.println("\nTransportadoras disponibles:");
        		        System.out.println("---------------------------------------------------");
        		        System.out.printf("%-5s %-30s %-15s%n", "No.", "Nombre", "Estado de Pago");
        		        System.out.println("---------------------------------------------------");
        			
        		for (Transportadora transportadora: Terminal.getTransportadoras()) {
        			
            		transportadora.calcularDineroTransportadora(); // Se calcula el dinero que tiene la transportadora
            		transportadora.bonificacion(); // Se le hace la bonificación en caso de que tenga para que aumente su dinero
            	
            		String estadoPago = transportadora.verificarPagoTerminal() ? "Pagado" : "No Pagado";
                    System.out.printf("%-5d %-30s %-15s%n", i, transportadora.getNombre(), estadoPago);
                    i++;
            		
            		if (transportadora.verificarPagoTerminal()) {
            			
            			transportadora.descuento(); // Este método hace el respectivo pago implícitamente a la terminal
            			String fecha = Tiempo.salidaFecha; // Se le asigna la fecha de pago las transportadoras tienen que cancelar el mismo día
                		transportadora.setFechaPago(fecha);
            			Factura factura = Factura.crearFacturatransportadora(transportadora.RetornarValorAPagarTerminal(), transportadora.getTerminal()); 
            			Factura.getFacturasCreadas().add(factura);
            			transportadora.getDueño().getFacturas().add(factura); // Se le pasa la factura al dueño de la transportadora
            			Terminal.getFacturas().add(factura);
            		
            		}else {
           
            			transportadorasQueNoHanPagado.add(transportadora);	
        	}
            		
        		}
        		
        		
    			int j = 1;
    			
    			if (!transportadorasQueNoHanPagado.isEmpty()) {
    				
    				for (Transportadora transportadora: transportadorasQueNoHanPagado) {
    					
    					System.out.println(j + transportadora.getNombre());
    					j++;
    					
    					int numConductores = transportadora.getConductores().size();
    						
    					double dineroTransportadora = transportadora.getDinero(); // 
    					
    					double liquidacionConductores = 0;
    					
    					 System.out.println("---------------------------------------------------");
    	                 System.out.printf("%-30s %-15s%n", "Transportadora", transportadora.getNombre());
    	                 System.out.printf("%-30s %-15s%n", "Estado de Pago", "No Pagado");
    	                 System.out.println("---------------------------------------------------");
    	                 
    					
    					if (numConductores > 0) {
    						
    						liquidacionConductores = dineroTransportadora / numConductores; // Daremos como liquidación este valor a los conductores
    						

    			            Iterator<Conductor> iterator = transportadora.getConductores().iterator();

    			            int b = 1;
    			            
    			            while (iterator.hasNext()) {
    			            	
    			                Conductor c= iterator.next();
    			                c.aumentarDinero(liquidacionConductores);
    			                Factura factura = Factura.crearFacturaConductor(liquidacionConductores, c.getTransportadora(), c.getVehiculo()); // Modificar
    			                Factura.getFacturasCreadas().add(factura);
    			                c.getFacturas().add(factura);
    			                transportadora.getConductoresDespedidos().add(c);
    			                Terminal.getFacturas().add(factura);// Llevar seguimiento de las facturas
    			                iterator.remove(); // Elimina el conductor de la lista
    			                c.eliminarVehiculo(); // Elimina al vehiculo asociado con el conductor
    			                
    			                System.out.println("---------------------------------------------------");
    		                    System.out.printf("%-5s %-30s %-20s %-15s%n", "No.", "Conductor", "Liquidación", "Fecha de Pago");
    		                    System.out.println("---------------------------------------------------");
    		                    System.out.printf("%-5d %-30s $%-20.2f %-15s%n", b, c.getNombre(), liquidacionConductores, transportadora.getFechaPago());
    		                    b++;
    			            }
    					
    					}else {System.out.println("No hay conductores para liquidar en " + transportadora.getNombre());}
    							
    					}
    				
    			}else {System.out.println("Todas las transportadoras han cancelado monto :)");}
    					
    		}
        		
        		System.out.println("Seleccione la transportadora a la cual quiere ver datos más específicos, en caso de que esta haya pagado");
            	int selectedtransportadora = Main_Principal.readInt();
            	
            	while (selectedtransportadora > Terminal.getTransportadoras().size() || selectedtransportadora <= 0) {
            		
            		System.out.println("Ingrese nuevamente su número, ingresó un numero incorrecto ");
            		selectedtransportadora = Main_Principal.readInt();
         	
            	}
            	
            	Transportadora t = Terminal.getTransportadoras().get(selectedtransportadora -1);
            	
            	if (t.verificarPagoTerminal()) {
            		
            		System.out.println("---------------------------------------------------");
                    System.out.printf("%-30s %-15s%n", "Transportadora", t.getNombre());
                    System.out.printf("%-30s %-15s%n", "Estado de Pago", "Pagado");
                    System.out.printf("%-30s %-15.2f%n", "Monto Pagado", t.RetornarValorAPagarTerminal());
                    System.out.printf("%-30s %-15s%n", "Fecha de Pago", t.getFechaPago());
                    System.out.println("---------------------------------------------------");
            	    
            	}else {
            		
            		System.out.println("La transportadora " + t.getNombre() + " no ha cancelado el monto.");
            		System.out.println("¿Desea ver los conductores que ya no pertenecen a la terminal? ingrese si o no");
            		Scanner scanner = new Scanner(System.in);
            		String escan = scanner.nextLine();
            		
            		if (escan.equalsIgnoreCase("si")) {
            			
            			int k = 1;
           
            			for (Conductor c: t.getConductoresDespedidos()) {
            				
            				int numConductores = t.getConductores().size();
        					
            				double dineroTransportadora = t.getDinero(); // 
            				
            				if (numConductores > 0) {
            					
            					System.out.println("---------------------------------------------------");
                                System.out.printf("%-5s %-30s %-20s %-15s%n", "No.", "Conductor", "Liquidación", "Fecha de Pago");
                                System.out.println("---------------------------------------------------");
            					
            					double liquidacionConductores = dineroTransportadora / numConductores;
            					
            					System.out.printf("%-5d %-30s $%-20.2f %-15s%n", k, c.getNombre(), liquidacionConductores, t.getFechaPago());
                                System.out.println("El conductor " + c.getNombre() + " ya no pertenece a la terminal.");
               			     	k++;
            				}
            			 
            			}	
            			
            			System.out.println("No hay conductores para liquidar en la transportadora " + t.getNombre());
            			
            		}else System.out.println("Ha decidido no ver a los conductores que ya no pertenecen a la terminal");
            			
            		
            	}
            	
    	}catch(InputMismatchException e) {
    		
    		System.out.println("Entrada inválida. ingrese un número entero");
    		transportadorasQueHanCanceladoMonto();
        
    	}
        	
    	}
    	
    	public static void estadisticasGenerales() {
    	    // Inicializar variables
    	    int cantidadPasajeros = 0;
    	    int viajesRealizadosTotal = 0;
    	    double dineroTerminal = Terminal.getDinero();
    	    double dineroconPagoTransportadoras = Terminal.calcularGanancias();
    	    double ganancia = dineroTerminal + dineroconPagoTransportadoras;
    	    int numFacturasTransportadoras = 0;
    	    int numFacturasPasajeros = 0;
    	    int numFacturasConductores = 0;
    	    int numFacturasTalleres = 0;
    	    int numFacturasMecanicos = 0;

    	    // Contar el número de facturas por tipo
    	    for (Factura f : Terminal.getFacturas()) {
    	        if (f != null) {
    	            if (f.getFacturasConductores() == 1) {
    	                numFacturasConductores++;
    	            }
    	            if (f.getFacturasMecanicos() == 1) {
    	                numFacturasMecanicos++;
    	            }
    	            if (f.getFacturasPasajero() == 1) {
    	                numFacturasPasajeros++;
    	            }
    	            if (f.getFacturasTalleres() == 1) {
    	                numFacturasTalleres++;
    	            }
    	            if (f.getFacturasTransportadora() == 1) {
    	                numFacturasTransportadoras++;
    	            }
    	        }
    	    }

    	    // Imprimir estadísticas generales
    	    System.out.println("Estadísticas Generales:");
    	    System.out.println("---------------------------------------------------");
    	    System.out.printf("%-30s %-15s%n", "Ganancia Total:", "$" + String.format("%.2f", ganancia));
    	    System.out.printf("%-30s %-15d%n", "Cantidad de Pasajeros:", cantidadPasajeros);
    	    System.out.printf("%-30s %-15d%n", "Viajes Realizados Total:", viajesRealizadosTotal);
    	    System.out.printf("%-30s %-15d%n", "Facturas de Transportadora:", numFacturasTransportadoras);
    	    System.out.printf("%-30s %-15d%n", "Facturas de Pasajero:", numFacturasPasajeros);
    	    System.out.printf("%-30s %-15d%n", "Facturas de Conductor:", numFacturasConductores);
    	    System.out.printf("%-30s %-15d%n", "Facturas de Taller:", numFacturasTalleres);
    	    System.out.printf("%-30s %-15d%n", "Facturas de Mecánico:", numFacturasMecanicos);
    	    System.out.println("---------------------------------------------------");

    	    // Imprimir viajes realizados por cada transportadora
    	    System.out.println("\nViajes Realizados por Cada Transportadora:");
    	    System.out.println("---------------------------------------------------");
    	    System.out.printf("%-30s %-15s%n", "Transportadora", "Viajes Realizados");
    	    System.out.println("---------------------------------------------------");

    	    for (Transportadora t : Terminal.getTransportadoras()) {
    	        int viajesRealizados = t.getViajesTerminados().size();
    	        System.out.printf("%-30s %-15d%n", t.getNombre(), viajesRealizados);
    	    }

    	    System.out.println("---------------------------------------------------");
    	}
    
    public static void conductoresLiquidados() {
    	
    	System.out.println("Señor administrador, a continuación verá usted los conductores que han sido liquidados");
    	
    	for (Conductor c : Conductor.getConductores()) {
    		
    		int numeroPagosRealizadosTransportadora = c.getTransportadora().getNumeroDePagosRealizados();
    		int numPagosRecibidosConductor = c.getNumeropagosRecibidosConductor();
    		
    		if ((numPagosRecibidosConductor == numeroPagosRealizadosTransportadora)) {
    			
    			System.out.printf("Conductor: %s (ID: %d), ha recibido de monto: $%,.2f%n", 
    	                  c.getNombre(), 
    	                  c.getId(), 
    	                  800000.0);
    			
    			Factura f = Factura.crearFacturaConductor(800000,c.getTransportadora(), c.getVehiculo()); 
    			c.getFacturas().add(f);
    			
    		}
    		
    		else if (numPagosRecibidosConductor < numeroPagosRealizadosTransportadora) { // Ya la transportadora ha pagado y a el conductor no se le ha efectuado el pago
    			
    			if (c.getTransportadora().verificarCumplimientoHorario(c)) { // Se verifica si el conductor cumpló con sus viajes asignados
    				
    				if (c.getTransportadora().getDinero() > 800000) {
    					
    					
    					c.getTransportadora().pagarConductor(c); 
            			Factura f = Factura.crearFacturaConductor(800000,c.getTransportadora(), c.getVehiculo());
            			Factura.getFacturasCreadas().add(f);
            			c.getTransportadora().setDinero(c.getTransportadora().getDinero()-800000); // Ya que se acordó que a cada transportador se le pagará este monto
    					
    				}else {
                        // Manejo del caso cuando la transportadora no tiene suficiente dinero
                        System.out.printf("Advertencia: La transportadora %s no tiene suficiente dinero para pagar al conductor %s (ID: %d).%n", 
                                          c.getTransportadora().getNombre(),  
                                          c.getNombre(), 
                                          c.getId());
                    }
    				
    			}else {
    	                System.out.printf("El conductor %s (ID: %d) no ha cumplido con sus viajes asignados.%n", 
    	                                  c.getNombre(), 
    	                                  c.getId());
    	            }
    			}
    			
    		}
    		
    	}
    
    public static void verificarCompraTiquetes() {
    	
    	 Scanner sn = new Scanner(System.in);
         int ingreso = 0;
         Pasajero p = null;
         Viaje viaje = null;
    	 ArrayList<Pasajero> pasajeros = Terminal.getPasajerosSinViajes();
    	 ArrayList <Viaje> viajes = Terminal.getViajes();
          
      
          int contador = 1;
          
          if (!pasajeros.isEmpty()) {
        	  
        	// Imprime el encabezado de la tabla
              System.out.printf("+----------------+--------------------------+%n");
              System.out.printf("| Nº Pasajero    | Nombre                   |%n");
              System.out.printf("+----------------+--------------------------+%n");
        	  
        	  for (Pasajero pasajero : pasajeros) {
                  String nombre = pasajero.getNombre();
                  
                  System.out.printf("| %-14d | %-24s |%n", contador, nombre);
                  contador++;
              }
              
             
              System.out.printf("+----------------+--------------------------+%n");
              System.out.println("Ingrese el pasajero a elegir: ");
              
              ingreso = sn.nextInt();
              p = Terminal.getPasajerosSinViajes().get(ingreso-1);
              
              int cont = 1;
              
              if (!viajes.isEmpty()) {
            	  
            	  for (Viaje v : viajes) {
                      String llegada = v.getLlegada().name();
                      
                      System.out.printf("| %-14d | %-24s |%n", cont, llegada);
                      contador++;
                  }
                  
                 
                  System.out.printf("+----------------+--------------------------+%n");
                    
              }System.out.println("No se han encontrado viajes: ");
             
              System.out.println("Ingrese el viaje a elegir: ");
              Scanner sca = new Scanner(System.in);
              int entrada = sca.nextInt();
              
               viaje = Terminal.getViajes().get(entrada-1);
                
          }System.out.println("No se encontraron pasajeros en la terminal");
            
    	try {
    		
    	      if(viaje != null || p != null) {
    	    	  
    	    	  System.out.println("---------------------------------------------------");
      	        System.out.println("Señor usuario, se está haciendo la respectiva validación de la compra de su ticket...");
      	        System.out.println("---------------------------------------------------");

      	        double valorApagar = viaje.getTarifa();
      	        
      	        if (viaje.getEstado() == false) { // Ya que el viaje no está en curso
      	        	
      	        	 // Verificar si el pasajero tiene suficiente dinero
      	            if (p.getDinero() >= valorApagar) {
      	                System.out.println("¡Felicidades! Usted cuenta con suficiente dinero para cancelar el ticket.");
      	                System.out.println("---------------------------------------------------");

      	                // Confirmación de compra
      	                System.out.println("¿Desea proceder con la compra del ticket? (si/no)");
      	                String respuesta = Main_Principal.readString();

      	                if (respuesta.equalsIgnoreCase("si")) {
      	                	
      	                    // Simulación de proceso de pago
      	                    System.out.println("Procesando el pago...");
      	                    try {
      	                        Thread.sleep(2000); 
      	                    } catch (InterruptedException e) {
      	                        System.out.println("Error en el procesamiento del pago.");
      	                    }

      	                    // Descontar el dinero del pasajero
      	                    p.descuento();
      	                    System.out.println("---------------------------------------------------");
      	                    System.out.printf("Se ha descontado %.2f de su cuenta. Su saldo actual es: %.2f%n",((Pasajero) p).obtenerValorDescontado(), p.getDinero());
      	                    System.out.println("---------------------------------------------------");

      	                    // Generar la factura y agregarla al historial del pasajero
      	                    Factura factura = Factura.crearFacturaPasajero(valorApagar, (Pasajero)p, viaje.getTerminal(), viaje.getConductor(), viaje, viaje.getVehiculo(), viaje.getTransportadora());
      	                    Factura.getFacturasCreadas().add(factura);
      	                    p.getFacturas().add(factura);
      	                    Terminal.getFacturas().add(factura); // Llevar seguimiento de las facturas en la terminal
      	                    System.out.println("Señor usuario se le mostrará a continuación los deatalles de su factura");
      	                    Tablas.imprimirDetallesFactura(factura);
      	                    // Actualizar el registro de viajes del pasajero
      	                    p.getHistorial().add(viaje);
      	                    
      	                    
      	                    for (Viaje v : p.getHistorial()) {
      	                    	
      	                    	if (viaje.getId() == v.getId()) {
      	                    		
      	                    		v.getTransportadora().getPasajeros().add((Pasajero)p);
      	                   	
      	                    	}
      	             
      	                    }
      	                    
      	                    System.out.println("¿Por algún motivo desea usted cancelar la compra del viaje?. Ingrese si o no");
      	                    Scanner scan = new Scanner(System.in);
      	                    String cancel = scan.nextLine();
      	                    
      	                    if (cancel.equalsIgnoreCase("si")) {
      	                    	
      	                    	Main_Principal.cancelarViaje(p, viaje);
      	                    	
      	                    }
      	                    
      	                    // Notificar al pasajero sobre los detalles del viaje
      	                    System.out.println("---------------------------------------------------");
      	                    System.out.println("La compra del ticket ha sido exitosa.");
      	                    System.out.println("Detalles del viaje:");
      	                    System.out.println("Destino: " + viaje.getLlegada());
      	                    System.out.println("Fecha y hora de salida: " + viaje.getFecha());
      	                    System.out.println("---------------------------------------------------");
      	                    System.out.printf("Su saldo actual es: %.2f%n", p.getDinero());
      	                    System.out.println("---------------------------------------------------");

      	                } else {
      	                    System.out.println("La compra del ticket ha sido cancelada.");
      	                }
      	                
      	            } else {
      	                System.out.println("---------------------------------------------------");
      	                System.out.println("Lo sentimos, usted no cuenta con suficiente dinero para cancelar el ticket.");
      	                System.out.println("Su saldo actual es: " + p.getDinero());
      	                System.out.println("La tarifa del viaje es: " + valorApagar);
      	                System.out.println("---------------------------------------------------");
      	          
      	            }
      	            
      	        }
    	    	  
    	      } else {System.out.println("Error, pasajero o viaje es null.");}
    	      
    	}catch(InputMismatchException e) {
    		
    		System.out.println("Entrada inválida. ingrese si o no");
    		verificarCompraTiquetes();
        
    	}
        	
        	
        }
    
    public static void cancelarViaje(Persona p, Viaje v) {
    	
       try {
    	   
    	   int opcion;
           Scanner scan = new Scanner(System.in);

           System.out.println("¿Está seguro de esta operación? Presione 1 o 0, donde 1 confirma y 0 aborta el proceso");
           opcion = scan.nextInt();
           scan.close();  // Cierra el Scanner después de su uso

           switch (opcion) {
               case 0:
                   System.out.println("Ha abortado el proceso y no se cancelará el viaje.");
                   break;

               case 1:
                   if (v != null) {
                       // Se busca el pasajero en la lista de pasajeros del viaje
                       double montoRembolso = ((Pasajero)p).obtenerValorDescontado();

                       Iterator<Pasajero> itPasajeros = v.getPasajeros().iterator();
                       while (itPasajeros.hasNext()) {
                           Pasajero pas = itPasajeros.next();
                           if (pas.getId() == p.getId()) {
                               p.aumentarDinero(montoRembolso);  // Reembolso al pasajero
                               itPasajeros.remove();  // Se elimina de la lista de pasajeros del viaje
                               break;
                           }
                       }

                       Iterator<Factura> itFacturas = p.getFacturas().iterator();
                       while (itFacturas.hasNext()) {
                           Factura f = itFacturas.next();
                           if (f.getViaje().getId() == v.getId()) {
                               itFacturas.remove();  // Se elimina la factura del pasajero
                               Terminal.getFacturas().remove(f);  // Se elimina la factura de la terminal
                               break;
                           }
                       }

                       // Descontar dinero a la transportadora
                       Transportadora transportadora = v.getTransportadora();
                       transportadora.setDinero(transportadora.getDinero() - montoRembolso);

                       System.out.println("El viaje ha sido cancelado y el reembolso procesado correctamente.");
                   } else {
                       System.out.println("Error: El viaje no es válido o no está asociado al pasajero.");
                   }
                   break;

               default:
                   System.out.println("Opción inválida. Proceso abortado.");
                   break;
           }
           
       }catch(InputMismatchException e) {
   		
   		System.out.println("Entrada inválida. ingrese 1 o 0");
   		//cancelarViaje(Pasajero p, Viaje v) Arreglar
       
   	}
    }
	
    //Funcionalidad 4
    
	public static void ejecutar4() {
		
		int opcion1;
		int opcion2;
		int opcion3;
		int opcion4;
		int opcion5;
		regresar = false;
		
		Transportadora transportadora = elegirTransportadora();
		if (transportadora == null) {
			System.out.println("Transportadora no disponible");
			return;
		}
		
		else {
			
			if(transportadora.getTaller() == null) {
				
				System.out.println("La transportadora escogida no tiene ningun taller; Tienes que crear uno");
				cambiarTaller(transportadora);
			}


	        while (!regresar) {
	        	
	        	//try {
	        		
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
				        	        	System.out.println("4. Ver vehiculos reparandose o vendiendose");
				        	        	System.out.println("5. Vender o desechar vehiculo");
				        	        	System.out.println("6. Cancelar");
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
					            	        	System.out.println("1. Agregar vehiculo a la lista de vehiculos disponibles");
					            	        	System.out.println("2. Quitar vehiculo de la lista de vehiculos disponibles");
					            	        	System.out.println("3. Ver lista de vehiculos disponibles para viajar");
					            	        	System.out.println("4. Cancelar");
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
				        						
				        						if (vehiculo.getTransportadora().getTaller().getVehiculosEnReparacion().contains(vehiculo) || vehiculo.getTransportadora().getTaller().getVehiculosEnVenta().contains(vehiculo) || vehiculo.getIntegridad() == 0 || !vehiculo.disponibilidad()) {
				        							
				        							System.out.println("No es posible agregar el vehiculo a la lista de disponibilidad pues este no se encuentra disponible");
				        							
				        							//continue;
				        						}
				        						
				      
				        						
				        						else {
				        							
				        							if (vehiculo.getConductores().size() == 0) {
				        								
				        								System.out.println("El vehiculo no tiene un conductor asignado. No se puede agregar a la lista");
				        							}
				        							
				        							else {
				        								
				        								vehiculo.setEstado(true);
					        							System.out.println ("Vehiculo agregado a la lista de disponiblidad");
				        								
				        							}
				        							
				        							
				        						}
				        						
				        						break;
				        					
				        					case 2:
				        						
				        						vehiculo.setEstado(false);
				        						System.out.println("Vehiculo eliminado de la lista de disponiblidad");
				        						break;
		
				        					case 3:
				        						
				        						ArrayList <Vehiculo> vehiculos = vehiculosDisponibles(transportadora);
				        						
				        						System.out.println("Vehiculos disponibles:");
				        						
				        						for (int i = 0; i < vehiculos.size(); i++ ) {
				        							
				        							System.out.println((i + 1) + ". Modelo:" + vehiculos.get(i).getModelo() + "; Placa: " + vehiculos.get(i).getPlaca() );
				        						}
				        						System.out.println("Los vehiculo de la lista estan disponibles para viajar, pues: No se estan reparando o vendiendo; tienen integridad > 0; tienen un conductor asignado.");
				        						//continue;
				        						break;
				        						
				        						
				        						
				        					case 4:
				        						
				        						regresar2 = true;
				        						break;
				        						
				        					
				        					default:
				        						
				        						System.out.println("Opcion no valida");
				        						//continue;
			        					
			        					}
			        					
			        				break;	
			        					
			        				case 4:
			        					
			        					vehiculosReparandoVendiendo(transportadora);
			        					
			        					break;
			        					
			        				case 5:
			        					
		        						if (vehiculo.getTransportadora().getTaller().getVehiculosEnReparacion().contains(vehiculo) || vehiculo.getTransportadora().getTaller().getVehiculosEnVenta().contains(vehiculo)) {
		        							
		        							System.out.println("No es posible eliminar al vehiculo en este momento pues esta siendo vendido o reparado");
		        						}
		        						
		        						else {
		        							
		        							quitarVehiculo(vehiculo);
		        						}
			        					break;
			        					
			        				case 6:
			        					
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
	        	//}
	        	
	        	/**catch (Exception e) {
	        		
	        		System.out.println("Accion no valida");
	        		scanner.nextLine();
	        	}**/
	        	       	
	        }
	        
			
	       
		}
       

    }
	
	// Funcionalidad 5
	
	private static void programacionViajes() {
		
		Scanner scanner = new Scanner(System.in);

        boolean programar = true;
        while (programar) {
            // SubMenú
            System.out.println("Tipo de programación:\n");
            System.out.println("1. Programación de Viajes");
            System.out.println("2. Administración de Reservas");
            System.out.println("3. Administración de Viajes");
            System.out.println("4. Regresar al Menú Principal");

            int opcion = obtenerEntradaValida(scanner, 1, 4);

            switch (opcion) {
                case 1:
                	boolean viaje = true;
                    while (viaje) {
                        System.out.println("Has decidido programar un nuevo viaje.");
                        System.out.println("Elige un destino:\n");

                        // Seleccionar Destinos
                        ArrayList<Destino> destinos = new ArrayList<>();
                        for (Destino destino : Destino.values()) {
                            destinos.add(destino);
                        }

                        if (destinos.isEmpty()) {
                            System.out.println("No hay destinos disponibles.");
                            viaje = false; // Regresar al menú de programación
                            break;
                        }

                        Tablas.tablaDestinos(destinos); // Llamado al formato de las tablas

                        System.out.println("Elige el destino por su número:");
                        int destinoSeleccionado = obtenerEntradaValida(scanner, 1, destinos.size());

                        Destino destinoElegido = destinos.get(destinoSeleccionado);
                        System.out.println("Has elegido el destino: " + destinoElegido.name());

                        // Seleccionar Transportadora
                        ArrayList<Transportadora> transportadorasPorDestino = Terminal.transportadorasViajeDisponible(destinoElegido);

                        if (transportadorasPorDestino.isEmpty()) {
                            System.out.println("No hay transportadoras disponibles para el destino seleccionado.");
                            viaje = false; // Regresar al menú de programación
                            break;
                        }

                        System.out.println("Estas son las transportadoras asociadas al destino, " + destinoElegido.name() + ":");
                        Tablas.tablaTransportadorasporDestino(transportadorasPorDestino);
                        System.out.println("Elige una transportadora por número:");

                        int transportadoraSeleccionada = obtenerEntradaValida(scanner, 1, transportadorasPorDestino.size());

                        Transportadora transportadoraElegida = transportadorasPorDestino.get(transportadoraSeleccionada - 1);
                        System.out.println("Has elegido la transportadora: " + transportadoraElegida.getNombre());

                        // FECHA
                        System.out.println("HORA ACTUAL");
                        System.out.println(Tiempo.mostrarTiempo());
                        System.out.println("Fechas Disponibles");

                        ArrayList<String> fechasDisponibles = Terminal.fechasDisponibles();

                        if (fechasDisponibles.isEmpty()) {
                            System.out.println("No hay fechas disponibles.");
                            viaje = false; // Regresar al menú de programación
                            break;
                        }

                        Tablas.tablaFechasDisponibles(fechasDisponibles);

                        System.out.println("Elige una fecha por número:");
                        int fechaSeleccionada = obtenerEntradaValida(scanner, 1, fechasDisponibles.size());

                        String fecha = fechasDisponibles.get(fechaSeleccionada - 1); // -1 porque las listas son basadas en cero
                        System.out.println("Has seleccionado la fecha: " + fecha);

                        // HORA
                        System.out.println("HORA ACTUAL");
                        System.out.println(Tiempo.mostrarTiempo());
                        System.out.println("Horas Disponibles");

                        ArrayList<String> horasDisponibles = Terminal.horasDisponibles(fecha);

                        if (horasDisponibles.isEmpty()) {
                            System.out.println("No hay horas disponibles.");
                            viaje = false; // Regresar al menú de programación
                            break;
                        }

                        Tablas.tablaHorasDisponibles(horasDisponibles);

                        System.out.println("Elige una hora por número:");
                        int horaSeleccionada = obtenerEntradaValida(scanner, 1, horasDisponibles.size());

                        String hora = horasDisponibles.get(horaSeleccionada - 1); 
                        String horaCasteada = Tiempo.convertirHora(hora);
                        System.out.println("Has seleccionado la hora: " + hora);

                        // Selección del tipo de Vehiculo
                        System.out.println("Selecciona el tipo de Vehiculo:\n");
                        int indice = 1;
                        ArrayList<TipoVehiculo> tiposDisponibles = transportadoraElegida.tiposVehiculosDisponible(); // Obtén la lista de tipos disponibles

                        if (tiposDisponibles.isEmpty()) {
                            System.out.println("No hay tipos de vehículos disponibles.");
                            viaje = false; // Regresar al menú de programación
                            break;
                        }

                        // Mostrar las opciones de tipos de vehículos disponibles
                        for (TipoVehiculo tipo : tiposDisponibles) {
                            System.out.println(indice + " " + tipo.name());
                            indice++;
                        }

                        // Leer la selección del usuario
                        int tipoVehiculoSeleccionado = obtenerEntradaValida(scanner, 1, tiposDisponibles.size());

                        // Obtener el tipo de vehículo seleccionado
                        TipoVehiculo tipoSeleccionado = tiposDisponibles.get(tipoVehiculoSeleccionado - 1);
                        System.out.println("Has seleccionado el tipo de vehículo: " + tipoSeleccionado.name());

                        // Elegir Conductor Manualmente
                        System.out.println("¿Desea seleccionar el Conductor Manualmente?\n" +
                                "1. Sí\n" +
                                "2. No");

                        int elegirConductor = obtenerEntradaValida(scanner, 1, 2);

                        if (elegirConductor == 1) {
                            // Selección manual del conductor
                            System.out.println("Selecciona el conductor");

                            ArrayList<Conductor> conductoresDisponibles = transportadoraElegida.conductoresDisponibles(fecha, tipoSeleccionado);

                            if (conductoresDisponibles.isEmpty()) {
                                System.out.println("No hay conductores disponibles.");
                                viaje = false; // Regresar al menú de programación
                                break;
                            }

                            Tablas.tablaConductoresDisponibles(conductoresDisponibles);

                            int conductorSeleccionado = obtenerEntradaValida(scanner, 1, conductoresDisponibles.size());

                            Conductor conductorElegido = conductoresDisponibles.get(conductorSeleccionado - 1);
                            System.out.println("Has seleccionado el conductor: " + conductorElegido.getNombre() + " con " + conductorElegido.getExperiencia() + " años de experiencia.");

                            Viaje viajeProgramado = Terminal.programarViaje(destinoElegido, conductorElegido, tipoSeleccionado, fecha, horaCasteada, Destino.MEDELLIN);
                            
                            if (viajeProgramado != null) {
                                System.out.println("__________________________");
                                System.out.println("* PROGRAMACIÓN EXITOSA *");
                                System.out.println("__________________________\n");
                                System.out.println(viajeProgramado.estado());
                                System.out.println("__________________________");
                            } else {
                                System.out.println("Programacion en proceso");
                            }

                        } else if (elegirConductor == 2) {
                            Viaje viajeProgramado = Terminal.programarViaje(destinoElegido, tipoSeleccionado, fecha, horaCasteada, Destino.MEDELLIN);
                            if (viajeProgramado != null) {
                                System.out.println("__________________________");
                                System.out.println("* PROGRAMACIÓN EXITOSA *");
                                System.out.println("__________________________\n");
                                System.out.println(viajeProgramado.estado());
                                System.out.println("__________________________");
                            } else {
                                System.out.println("Programacion en proceso");
                            }
                        }

                        System.out.println("¿Deseas programar otro viaje?");
                        System.out.println("1. Sí");
                        System.out.println("2. No");
                        int respuesta = obtenerEntradaValida(scanner, 1, 2);

                        if (respuesta == 2) {
                            viaje = false; // Termina el bucle de programación de viajes
                        }
                    }
                    break;
                    
                    
                case 2:
                	
                	System.out.println("Has decidido administrar las reservas.");

                    System.out.println("1. Ver reservas actuales");
                    System.out.println("2. Regresar al menú de programación de viajes");

                    int opcionReserva = obtenerEntradaValida(scanner, 1, 2);

                    switch (opcionReserva) {
                        case 1:
                            System.out.println("Mostrando reservas actuales...");
                            System.out.println(Tiempo.mostrarTiempo());
                            System.out.println("Viajes en Reserva.");
                            ArrayList<Viaje> viajesReserva = Terminal.getReservas();

                            Tablas.tablaViajesDisponiblesId(viajesReserva);

                            System.out.println("¿Desea administrar una Reserva?. ");
                            System.out.println("1. SI");
                            System.out.println("2. NO");

                            int continuar = obtenerEntradaValida(scanner, 1, 2);

                            switch (continuar) {
                                case 1:
                                    System.out.println("Selecciona el N° de la reserva: ");

                                    int seleccion = obtenerEntradaValida(scanner, 0, viajesReserva.size());

                                    Viaje reservaAjuste = viajesReserva.get(seleccion);
                                    Tablas.viajeIndividual(reservaAjuste);

                                    System.out.println("Qué acción deseas realizar: ");

                                    System.out.println("1. Ver detalles");
                                    System.out.println("2. Cancelar");
                                    System.out.println("3. Regresar");

                                    int reserva = obtenerEntradaValida(scanner, 1, 3);

                                    switch (reserva) {
                                        case 1:
                                            System.out.println("__________________________");
                                            System.out.println(reservaAjuste.estado());
                                            System.out.println("__________________________");
                                            break;
                                        case 2:
                                            System.out.println("Selecciona el Tipo de Cancelación: ");
                                            System.out.println("1. Absoluta (Elimina el viaje y Reembolsa el Dinero.)");
                                            System.out.println("2. Relativa (Asigna el Viaje más próximo que cumpla las Características.)");
                                            System.out.println("3. Regresar");

                                            int tipo = obtenerEntradaValida(scanner, 1, 3);

                                            switch (tipo) {
                                                case 1:
                                                    String absoluta = Terminal.cancelarViajeAbsoluto(reservaAjuste);
                                                    System.out.println("__________________________");
                                                    System.out.println("Reserva Cancelada");
                                                    System.out.println(absoluta);
                                                    System.out.println("__________________________");
                                                    break;
                                                case 2:
                                                    String relativa = Terminal.denegarReserva(reservaAjuste);
                                                    System.out.println("__________________________");
                                                    System.out.println("Reserva Cancelada");
                                                    System.out.println(relativa);
                                                    System.out.println("__________________________");
                                                    break;
                                                case 3:
                                                    System.out.println("Regresando...");
                                                    break;
                                                default:
                                                    System.out.println("Opción no válida. Regresando...");
                                                    break;
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Regresando...");
                                            break;
                                        default:
                                            System.out.println("Opción no válida. Regresando...");
                                            break;
                                    }
                                    break;

                                case 2:
                                    System.out.println("Regresando...");
                                    break;

                                default:
                                    System.out.println("Opción no válida. Regresando...");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("Regresando al menú de programación de viajes");
                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, marque números enteros del 1 al 2.");
                            break;
                    }
                    
                    break;
                    
                    
                    
                case 3:
                	System.out.println("Has decidido administrar los Viajes.");

                    System.out.println("1. Viajes Disponibles");
                    System.out.println("2. Historial de Viajes");
                    System.out.println("3. Regresar");

                    int administrar = obtenerEntradaValida(scanner, 1, 3);

                    switch (administrar) {
                        case 1:
                            System.out.println(Tiempo.mostrarTiempo());
                            System.out.println("Viajes Disponibles --- Sin Salir");
                            ArrayList<Viaje> viajesDisponibles = Terminal.getViajes();

                            Tablas.tablaViajesDisponibles(viajesDisponibles);

                            System.out.println("Qué acción deseas Realizar: ");
                            System.out.println("1. Ver detalles");
                            System.out.println("2. Cancelar");
                            System.out.println("3. Regresar");

                            int modificar = obtenerEntradaValida(scanner, 1, 3);

                            switch (modificar) {
                                case 1:
                                    Tablas.tablaViajesDisponiblesId(viajesDisponibles);
                                    System.out.println("Detalles del Viaje: ");
                                    
                                    System.out.println("Selecciona el viaje a inspeccionar: ");
                                    int detalles = obtenerEntradaValida(scanner, 0, viajesDisponibles.size());

                                    Viaje viajeDetalles = viajesDisponibles.get(detalles); 
                                    Tablas.viajeIndividual(viajeDetalles);
                                    System.out.println("__________________________");
                                    System.out.println(viajeDetalles.estado());
                                    System.out.println("__________________________");
                                    System.out.println(" ");

                                    System.out.println("1. Ver lista de pasajeros.");
                                    System.out.println("2. Regresar.");

                                    int pasajeros = obtenerEntradaValida(scanner, 1, 2);

                                    switch (pasajeros) {
                                        case 1:
                                            ArrayList<Pasajero> listaPersonas = viajeDetalles.getPasajeros();
                                            int i = 0;
                                            if (!listaPersonas.isEmpty()) {
                                                System.out.println("__________________________");
                                                for (Pasajero p : listaPersonas) {
                                                    System.out.println(i + ". " + p.getNombre());
                                                    i++;
                                                }
                                                System.out.println("__________________________");
                                            } else {
                                            	System.out.println("__________________________");
                                                System.out.println("El viaje no tuvo pasajeros");
                                                System.out.println("__________________________");
                                            }
                                            break;

                                        case 2:
                                            System.out.println("Regresando...");
                                            break;
                                    }
                                    break;

                                case 2:
                                    Tablas.tablaViajesDisponiblesId(viajesDisponibles);
                                    System.out.println("Ingrese el número del Viaje a cancelar: ");

                                    int cancelar = obtenerEntradaValida(scanner, 1, viajesDisponibles.size());

                                    Viaje viajeCancelar = viajesDisponibles.get(cancelar); 

                                    System.out.println("¿Estás seguro de cancelar el viaje?");
                                    Tablas.viajeIndividual(viajeCancelar);
                                    System.out.println("1. SI");
                                    System.out.println("2. NO");

                                    int delete = obtenerEntradaValida(scanner, 1, 2);

                                    switch (delete) {
                                        case 1:
                                            System.out.println("__________________________");
                                            System.out.println("Resultado de la Operación: ");
                                            System.out.println(Terminal.cancelarViaje(viajeCancelar));
                                            System.out.println("__________________________");
                                            break;

                                        case 2:
                                            System.out.println("Regresando...");
                                            break;

                                        default:
                                            System.out.println("Opción no válida. Por favor, marque números enteros del 1 al 2.");
                                    }
                                    break;

                                case 3:
                                    System.out.println("Regresando al menú de programación de viajes");
                                    break;

                                default:
                                    System.out.println("Opción no válida. Por favor, marque números enteros del 1 al 3.");
                            }
                            break;

                        case 2:
                            System.out.println("Historial de Viajes: ");
                            System.out.println(Tiempo.mostrarTiempo());
                            ArrayList<Viaje> historialViaje = Terminal.getHistorial();
                            Tablas.tablaViajesDisponiblesId(historialViaje);
                            
                            if (!Terminal.getHistorial().isEmpty()) {
                                System.out.println("Desea interactuar con un viaje: ");
                                System.out.println("1. SI");
                                System.out.println("2: NO ");
                            } else {
                            	System.out.println("No hay viajes en el Historial");
                            	break;
                            }


                            int decision = obtenerEntradaValida(scanner, 1, 2);

                            if (decision == 2) {
                                System.out.println("Regresando...");
                                break;
                            }

                            if (decision == 1) {
                                System.out.println("Introduce el N° del viaje: ");

                                int reprogramar = obtenerEntradaValida(scanner, 0, historialViaje.size() - 1); // 0 al tamaño - 1

                                Viaje viajeReprogramar = historialViaje.get(reprogramar);
                                Tablas.viajeIndividual(viajeReprogramar);

                                boolean permanencia = true;

                                while (permanencia) {
                                    System.out.println("Qué acción deseas Realizar: ");
                                    System.out.println("1. Reprogramar el Viaje");
                                    System.out.println("2. Ver más información");
                                    System.out.println("3. Ver lista de Pasajeros");
                                    System.out.println("4. Regresar");

                                    int historial = obtenerEntradaValida(scanner, 1, 4);

                                    switch (historial) {
                                        case 1:
                                            System.out.println("Detalles: ");
                                            Tablas.viajeIndividual(viajeReprogramar);
                                            System.out.println("Se reprogramará un viaje con las condiciones del viaje seleccionado: ");

                                            boolean permanencia1 = true;

                                            while (permanencia1) {
                                                // Selección nueva Fecha
                                                System.out.println(Tiempo.mostrarTiempo());
                                                ArrayList<String> fechasDisponibles = Terminal.fechasDisponibles();

                                                System.out.println("Ingresa la Nueva fecha: ");
                                                Tablas.tablaFechasDisponibles(fechasDisponibles);

                                                System.out.println("Elige una fecha por número:");
                                                int nuevafecha = obtenerEntradaValida(scanner, 1, fechasDisponibles.size());

                                                String fechaNueva = fechasDisponibles.get(nuevafecha - 1); // -1 para ajustar al índice base cero
                                                System.out.println("Has seleccionado la fecha: " + fechaNueva);

                                                // Selección nueva Hora
                                                System.out.println(Tiempo.mostrarTiempo());
                                                System.out.println("Horas Disponibles");

                                                ArrayList<String> horasDisponibles = Terminal.horasDisponibles(fechaNueva);

                                                Tablas.tablaHorasDisponibles(horasDisponibles);

                                                System.out.println("Elige una hora por número:");
                                                int horaSeleccionada = obtenerEntradaValida(scanner, 1, horasDisponibles.size());

                                                String hora = horasDisponibles.get(horaSeleccionada - 1); // -1 para ajustar al índice base cero
                                                String horaCasteada = Tiempo.convertirHora(hora);
                                                System.out.println("Has seleccionado la hora: " + hora);

                                                Viaje a = Terminal.programarViaje(viajeReprogramar.getLlegada(), viajeReprogramar.getVehiculo().getTipo(), fechaNueva, horaCasteada, Destino.MEDELLIN);
                                                if (a != null) {
                                                    System.out.println("__________________________");
                                                    System.out.println("* PROGRAMACIÓN EXITOSA *");
                                                    System.out.println("__________________________\n");
                                                    System.out.println(a.estado());
                                                    System.out.println("__________________________");
                                                    break;
                                                } else {
                                                    System.out.println("En el momento no se cuenta con Disponibilidad.");
                                                    break;
                                                }
                                            }
                                            break;
                                        case 2:
                                        	System.out.println("__________________________");
                                            System.out.print(viajeReprogramar.estado());
                                            
                                            break;
                                        case 3:
                                        	System.out.println("Lista de Pasajeros: ");
                                        	System.out.println("__________________________");
                                            ArrayList<Pasajero> listaPersonasHistorial = viajeReprogramar.getPasajeros();
                                            int j = 0;
                                            if (listaPersonasHistorial != null) {
                                                for (Pasajero p : listaPersonasHistorial) {
                                                    System.out.println(j + ". " + p.getNombre());
                                                    j++;
                                                }
                                            } else {
                                                System.out.println("El viaje no tuvo pasajeros");
                                            }
                                            break;
                                        case 4:
                                            permanencia = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida. Por favor, elige un número del 1 al 4.");
                                    }
                                }
                            }
                    break;
                    }
                case 4:
                    System.out.println("Regresando...");
                    programar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
	
    
    // Desarrollar los metodos a partir de aqui
	public static void vehiculosReparandoVendiendo (Transportadora transportadora) {
		
		System.out.println("Vehiculos en reparacion:");
		System.out.println("");
		
		for (Vehiculo vehiculo : transportadora.getTaller().getVehiculosEnReparacion()) {
			
			System.out.println((transportadora.getTaller().getVehiculosEnReparacion().indexOf(vehiculo)+1) + ". Placa: " + vehiculo.getPlaca() + "; Modelo: " + vehiculo.getModelo() + "; Tiempo restante: " + (vehiculo.getFechaHoraReparacion() - Tiempo.getFechaHora()));
			
		}
		
		System.out.println("");
		System.out.println("Vehiculos en venta:");
		System.out.println("");
		
		for (Vehiculo vehiculo : transportadora.getTaller().getVehiculosEnVenta()) {
			
			System.out.println((transportadora.getTaller().getVehiculosEnVenta().indexOf(vehiculo)+1) + ". Placa: " + vehiculo.getPlaca() + "; Modelo: " + vehiculo.getModelo() + "; Precio de venta: " + vehiculo.getPrecio());
			
		}
		
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
				
				while (true) {
					
					System.out.println("Sexo (m,f):");
					genero = scanner.next().toUpperCase().charAt(0);
					
					if (genero == 'M' || genero == 'F') {
						
						break;
					}
				}
				
				historial = new ArrayList <Viaje> ();
				
				while (true) {
					
					System.out.println("Experiencia (0-20):");
					experiencia = scanner.nextInt();
					
					if ( experiencia <=20 && experiencia >= 0 ) {
						
						break;
					}
				}
				
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
    		Main_Principal.vehiculo = vehiculo;
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
			
			if (vehiculo.getTransportadora().getTaller().getVehiculosEnVenta().contains(vehiculo) || vehiculo.getTransportadora().getTaller().getVehiculosEnReparacion().contains(vehiculo)) {
				
				System.out.println("No se puede vender el vehiculo pues ya esta siendo vendido o reparado");
			}
			
			else {
				
				vehiculo.getTransportadora().getTaller().agregarVehiculoVenta(vehiculo);
				System.out.println("El vehiculo se agrego a la cola de ventas");
			}
			
			break;
			
			
		case 2:
			
			if (vehiculo.getTransportadora().getTaller().getVehiculosEnVenta().contains(vehiculo) || vehiculo.getTransportadora().getTaller().getVehiculosEnReparacion().contains(vehiculo)) {
				
				System.out.println("No se puede vender el vehiculo pues ya esta siendo vendido o reparado");
			}
			
			else {
				
				vehiculo.getTransportadora().removerVehiculo(vehiculo);
				System.out.println("El vehiculo se elimino correctamente");
			}
			
			
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
    			
    			if (vehiculo.getTransportadora().getTaller().getVehiculosEnVenta().contains(vehiculo) || vehiculo.getTransportadora().getTaller().getVehiculosEnReparacion().contains(vehiculo)) {
    				
    				System.out.println("No se puede vender el vehiculo pues ya esta siendo vendido o reparado");
    			}
    			
    			else {
    				
    				vehiculo.getTransportadora().getTaller().agregarVehiculoVenta(vehiculo);
    				System.out.println("El vehiculo se agrego a la cola de ventas");
    			}
    			
    			break;
    			
    			
    		case 2:
    			
    			if (vehiculo.getTransportadora().getTaller().getVehiculosEnVenta().contains(vehiculo) || vehiculo.getTransportadora().getTaller().getVehiculosEnReparacion().contains(vehiculo)) {
    				
    				System.out.println("No se puede vender el vehiculo pues ya esta siendo vendido o reparado");
    			}
    			
    			else {
    				
    				vehiculo.getTransportadora().removerVehiculo(vehiculo);
    				System.out.println("El vehiculo se elimino correctamente");
    			}
    			
    			
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
    			System.out.println((vehiculos.indexOf(car)+1) + ". " + "Placa: " + car.getPlaca() + "  Modelo: " + car.getModelo()+"   Tipo: " + car.getTipo().toString());
    		}
    		int option3 = check(1,vehiculos.size());
    		vehiculos.get(option3-1).asociarConductor(conductor);
			conductor.setVehiculo(vehiculos.get(option3-1));
			System.out.println("Se ha vinculado el vehiculo con placa "+vehiculos.get(option3-1).getPlaca() +" al conductor "+conductor.getNombre());
    	}
    }
    
    private static int obtenerEntradaValida(Scanner scanner, int min, int max) {
        int opcion = -1;
        while (opcion < min || opcion > max) {
            try {
                opcion = scanner.nextInt();
                if (opcion < min || opcion > max) {
                    System.out.println("Opción no válida. Por favor, elige un número del " + min + " al " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
        return opcion;
    }
    
	 private static void apagarTiempo() {
		 for (Tiempo i : Tiempo.principal) {
			 if (i != null) {
				 System.out.println(i.cancel());
			 }
		 }
	 }
    
}
