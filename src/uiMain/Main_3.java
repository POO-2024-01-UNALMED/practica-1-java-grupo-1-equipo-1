package uiMain;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Iterator;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;
import gestorAplicacion.constantes.*;

public class Main_3 {
	
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
                    
                	Main_3.verTarifas();
                    break;
                    
                case 2:
                    
                	Main_3.trasnportadorasQueHanCaneladoMonto();
                    break;
                    
                case 3:
                	
                    Main_3.estadisticasGenerales();
                    break;
                    
                case 4:
                    
                	Main_3.conductoresLiquidados();
                    break;
                    
                case 5:
                	
                   // Main_3.verificarCompraTiquetes();
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
        		
        		for (Transportadora t: Terminal.getTransportadoras()) {
            		
            		System.out.println(i +". " + t.getNombre() );
            		i++;
            		
            	}
        		
        		System.out.println("Seleccione la transportadora a la cual quiere ver dicha tarifa de viajes");
            	int scanner = Main_Principal.readInt();
            	
            	while (scanner > Terminal.getTransportadoras().size() || scanner <= 0) {
            		
            		System.out.println("Ingrese nuevamente su número, ingresó un numero incorrecto ");
                	scanner = Main_Principal.readInt();
         	
            	}
            	
            		Transportadora transportadoraSeleccionada = Terminal.getTransportadoras().get(scanner - 1);
            		
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
          
    		
    	}
    	
    	

    	
    }
    
    public static void trasnportadorasQueHanCaneladoMonto() {
    	
    	System.out.print("A continuación se le mostrarán las transportadoras disponibles de su terminal ");
    	System.out.println(" \nPara que pueda consultar si dichas transportadoras han cancelado monto o no");
   
    	ArrayList <Transportadora> transportadorasQueNoHanPagado = new ArrayList<>();
    	
    	if (!(Terminal.getTransportadoras().isEmpty())) {
    		
    			int i = 1;
    			
    			 System.out.println("\nTransportadoras disponibles:");
    		        System.out.println("---------------------------------------------------");
    		        System.out.printf("%-5s %-30s %-15s%n", "No.", "Nombre", "Estado de Pago");
    		        System.out.println("---------------------------------------------------");
    			
    		for (Transportadora t: Terminal.getTransportadoras()) {
    			
        		t.calcularDineroTransportadora(); // Se calcula el dinero que tiene la transportadora
        		t.bonificacion(); // Se le hace la bonificación en caso de que tenga para que aumente su dinero
        		String fecha = Tiempo.salidaFecha; // Se le asigna la fecha de pago las transportadoras tienen que cancelar el mismo día
        		t.setFechaPago(fecha);
        		
        		String estadoPago = t.verificarPagoTerminal() ? "Pagado" : "No Pagado";
                System.out.printf("%-5d %-30s %-15s%n", i, t.getNombre(), estadoPago);
                i++;
        		
        		if (t.verificarPagoTerminal()) {
        			
        			t.descuento(); // Este método hace el respectivo pago implícitamente a la terminal
        			Factura factura = Factura.crearFacturatransportadora(t.RetornarValorAPagarTerminal(), t.getTerminal()); 
        			Factura.getFacturasCreadas().add(factura);
        			t.getDueño().getFacturas().add(factura); // Se le pasa la factura al dueño de la transportadora
        			Terminal.getFacturas().add(factura);
        			
        		}else {
        			
        			transportadorasQueNoHanPagado.add(t);
        			
        			int j = 1;
        			
        			for (Transportadora trans: transportadorasQueNoHanPagado) {
        				
        				System.out.println(j + t.getNombre());
        				j++;
        				
        				int numConductores = trans.getConductores().size();
        					
        				double dineroTransportadora = trans.getDinero(); // 
        				
        				double liquidacionConductores = dineroTransportadora / numConductores; // Daremos como liquidación este valor a los conductores
        				
        				
        				 System.out.println("---------------------------------------------------");
                         System.out.printf("%-30s %-15s%n", "Transportadora", trans.getNombre());
                         System.out.printf("%-30s %-15s%n", "Estado de Pago", "No Pagado");
                         System.out.println("---------------------------------------------------");
                         

        	            Iterator<Conductor> iterator = trans.getConductores().iterator();
        	            
        	            int b = 1;
        	            
        	            while (iterator.hasNext()) {
        	            	
        	                Conductor c= iterator.next();
        	                c.aumentarDinero(liquidacionConductores);
        	                Factura factura = Factura.crearFacturaConductor(liquidacionConductores, c.getTransportadora(), c.getVehiculo()); // Modificar
        	                Factura.getFacturasCreadas().add(factura);
        	                c.getFacturas().add(factura);
        	                Terminal.getFacturas().add(factura);// Llevar seguimiento de las facturas
        	                iterator.remove(); // Elimina el conductor de la lista
        	                c.eliminarVehiculo(); // Elimina al vehiculo asociado con el conductor
        	                
        	                System.out.println("---------------------------------------------------");
                            System.out.printf("%-5s %-30s %-20s %-15s%n", "No.", "Conductor", "Liquidación", "Fecha de Pago");
                            System.out.println("---------------------------------------------------");
                            System.out.printf("%-5d %-30s $%-20.2f %-15s%n", b, c.getNombre(), liquidacionConductores, trans.getFechaPago());
                            b++;
        	            }
        			
        			}
        			
        		}
        		
    	}	
    		
    		System.out.println("Seleccione la transportadora a la cual quiere ver datos más específicos, en caso de que esta haya pagado");
        	int sc = Main_Principal.readInt();
        	
        	while (sc > Terminal.getTransportadoras().size() || sc <= 0) {
        		
        		System.out.println("Ingrese nuevamente su número, ingresó un numero incorrecto ");
            	sc = Main_Principal.readInt();
     	
        	}
        	
        	Transportadora t = Terminal.getTransportadoras().get(sc-1);
        	
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
                     
        			
        			for (Conductor c: t.getConductores()) {
        				
        				int numConductores = t.getConductores().size();
    					
        				double dineroTransportadora = t.getDinero(); // 
        				
        				double liquidacionConductores = dineroTransportadora / numConductores;

        				 System.out.printf("%-5d %-30s $%-20.2f %-15s%n", k, c.getNombre(), liquidacionConductores, t.getFechaPago());
                         System.out.println("El conductor " + c.getNombre() + " ya no pertenece a la terminal.");
        			     k++;
        			}	
        			
        		}
        			
        		
        	}
        	
    		
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
    			
    			Factura f = Factura.crearFacturaConductor(800000,c.getTransportadora(), c.getVehiculo()); // Modificar
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
    				
    			}
    			
    		}
    		
    	}
    	
    	
    	
    	
    }
    
    public static void verificarCompraTiquetes(Persona p, Viaje v) {
    	
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
        	
        	
        }
    
    public static void cancelarViaje(Pasajero p, Viaje v) {
    	
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
                    double montoRembolso = p.obtenerValorDescontado();

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
    }
      
}
    


