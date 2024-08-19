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
                    //Main_1.ejecutar();
                    break;
                case 2:
                	Main_2.ejecutar();
                    break;
                case 3:
                	
                	facturacionYFinanzas();
                    break;
                    
                case 4:
                	ejecutar4();
                    break;
                case 5:
                	Main_5.ejecutar();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    //System.out.println(Tiempo.principal.); // Fin del Tiempo
                    //Serializador.serializarListas();;
                    break;
                default:
                    System.out.println("Opción no válida, Marque de 1,6");
            }
        }
        scanner.close();
        
        
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
                	
                   //verificarCompraTiquetes(); Arreglar
                    break;
                    
                case 6:
                	
                    regresar = true;
                    System.out.println("Regresando al Menú Principal");
                    break;
                    
                default:
                	
                    System.out.println("Opción no válida");
            }
            
        }while (!regresar);
        
        scanner.close();
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
    					
    					}else {System.out.println("No hay conductores para liquidar en la transportadora " + transportadora.getNombre());}
    							
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
            		String entrada = Main_Principal.readString();
            		
            		if (entrada.equalsIgnoreCase("si")) {
            			
            			int k = 1;
            			
            			 System.out.println("---------------------------------------------------");
                         System.out.printf("%-5s %-30s %-20s %-15s%n", "No.", "Conductor", "Liquidación", "Fecha de Pago");
                         System.out.println("---------------------------------------------------");
                         
            			
            			for (Conductor c: t.getConductoresDespedidos()) {
            				
            				int numConductores = t.getConductores().size();
        					
            				double dineroTransportadora = t.getDinero(); // 
            				
            				if (numConductores > 0) {
            					
            					double liquidacionConductores = dineroTransportadora / numConductores;
            					
            					System.out.printf("%-5d %-30s $%-20.2f %-15s%n", k, c.getNombre(), liquidacionConductores, t.getFechaPago());
                                System.out.println("El conductor " + c.getNombre() + " ya no pertenece a la terminal.");
               			     	k++;
            				}
            				
            				System.out.println("No hay conductores para liquidar en la transportadora " + t.getNombre());
            				 
            			}	
            			
            		}
            			
            		
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
    	                  800000);
    			
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
    
    public static void verificarCompraTiquetes(Persona p, Viaje v) {
    	
    	try {
    		
    	      System.out.println("---------------------------------------------------");
    	        System.out.println("Señor usuario, se está haciendo la respectiva validación de la compra de su ticket...");
    	        System.out.println("---------------------------------------------------");

    	        double valorApagar = v.getTarifa();
    	        
    	        if (v.getEstado() == false) { // Ya que el viaje no está en curso
    	        	
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
    	                    Factura factura = Factura.crearFacturaPasajero(valorApagar, (Pasajero)p, v.getTerminal(), v.getConductor(), v, v.getVehiculo(), v.getTransportadora());
    	                    Factura.getFacturasCreadas().add(factura);
    	                    p.getFacturas().add(factura);
    	                    Terminal.getFacturas().add(factura); // Llevar seguimiento de las facturas en la terminal
    	                    System.out.println("Señor usuario se le mostrará a continuación los deatalles de su factura");
    	                    Tablas.imprimirDetallesFactura(factura);
    	                    // Actualizar el registro de viajes del pasajero
    	                    p.getHistorial().add(v);
    	                    
    	                    
    	                    for (Viaje viaje : p.getHistorial()) {
    	                    	
    	                    	if (viaje.getId() == v.getId()) {
    	                    		
    	                    		v.getTransportadora().getPasajeros().add((Pasajero)p);
    	                   	
    	                    	}
    	             
    	                    }
    	                    // Notificar al pasajero sobre los detalles del viaje
    	                    System.out.println("---------------------------------------------------");
    	                    System.out.println("La compra del ticket ha sido exitosa.");
    	                    System.out.println("Detalles del viaje:");
    	                    System.out.println("Destino: " + v.getLlegada());
    	                    System.out.println("Fecha y hora de salida: " + v.getFecha());
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
    	}catch(InputMismatchException e) {
    		
    		System.out.println("Entrada inválida. ingrese si o no");
    		//verificarCompraTiquetes(persona p, viaje v); Arreglar
        
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
    
	
	
}
