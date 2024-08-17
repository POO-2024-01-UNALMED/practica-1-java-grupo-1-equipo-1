package uiMain;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;
import gestorAplicacion.constantes.*;

public class Main_3 {
	
	public static Scanner sc = new Scanner(System.in);
	
    public static int readInt() {
    	
    	return sc.nextInt();
    }
    
    public static String readString() {
    	
    	return sc.nextLine();
    
    }
    
    public static void ejecutar() {
    	
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
                	
                    Main_3.verificarCompraTiquetes();
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
    	
    	System.out.print("señor administrador, a continuación se le mostrarán las transportadoras disponibles de su terminal ");
    	System.out.println(" \nPara que pueda consultar las respectivas tarifas de la transportadora que desee");
    	int i = 1;
    	
    	if (!(Terminal.getTransportadoras().isEmpty())) {
    		
    		for (Transportadora t: Terminal.getTransportadoras()) {
        		
        		System.out.println(i +". " + t.getNombre() );
        		i++;
        		
        	}
    		
    		System.out.println("Seleccione la transportadora a la cual quiere ver dicha tarifa de viajes");
        	int scanner = Main_3.readInt();
        	
        	while (scanner > Terminal.getTransportadoras().size() || scanner <= 0) {
        		
        		System.out.println("Ingrese nuevamente su número, ingresó un numero incorrecto ");
            	scanner = Main_3.readInt();
     	
        	}
        		
        		Transportadora TransportadoraSeleccionada = Terminal.getTransportadoras().get(scanner - 1);
        		
        		int j = 1;
            	
            	if (!(TransportadoraSeleccionada.getViajesAsignados().isEmpty())) {
            		
            		for (Viaje v: TransportadoraSeleccionada.getViajesAsignados()) {
            			
                		String formato = "Viaje " + j + v.getSalida().name() + " - " + v.getLlegada().name() + " - Tarifa: $" + String.format("%.2f", v.getTarifa());
                		System.out.println(formato);
                		j++;
                	
                	}
            		
            	}else { System.out.println("Algo está fallando");}
            			
    	}else { System.out.println("Algo está fallando");}
    	
    }
    
    public static void trasnportadorasQueHanCaneladoMonto() {
    	
    	System.out.print("señor administrador, a continuación se le mostrarán las transportadoras disponibles de su terminal ");
    	System.out.println(" \nPara que pueda consultar si dichas transportadoras han cancelado monto o no");
   
    	ArrayList <Transportadora> transportadorasQueNoHanPagado = new ArrayList<>();
    	
    	if (!(Terminal.getTransportadoras().isEmpty())) {
    		
    			int i = 1;
    			
    		for (Transportadora t: Terminal.getTransportadoras()) {
        		
        		t.calcularDineroTransportadora(); // Se calcula el dinero que tiene la transportadora
        		t.bonificacion(); // Se le hace la bonificación en caso de que tenga para que aumente su dinero
        		String fecha = Tiempo.salidaFecha; // Se le asigna la fecha de pago
        		t.setFechaPago(fecha);
        		System.out.println(i + t.getNombre());
        		i++;
        		
        		if (t.verificarPagoTerminal()) {
        			
        			t.descuento(); // Este método hace el respectivo pago implícitamente a la terminal
        			Factura factura = new Factura(t.RetornarValorAPagarTerminal(), t.getTerminal());
        			Terminal.getFacturas().add(factura);
        			
        		}else {
        			
        			transportadorasQueNoHanPagado.add(t);
        			
        			for (Transportadora trans: transportadorasQueNoHanPagado) {
        				
        				System.out.println(i + t.getNombre());
        				i++;
        				
        				int numConductores = trans.getConductores().size();
        					
        				double dineroTransportadora = trans.getDinero(); // 
        				
        				double liquidacionConductores = dineroTransportadora / numConductores; // Daremos como liquidación este valor a los conductores
        				

        	            Iterator<Conductor> iterator = trans.getConductores().iterator();
        	            
        	            while (iterator.hasNext()) {
        	            	
        	                Conductor c= iterator.next();
        	                c.aumentarDinero(liquidacionConductores);
        	                Factura factura =new Factura(liquidacionConductores, c.getTransportadora(), c.getVehiculo());
        	                c.getFacturas().add(factura);
        	                iterator.remove(); // Elimina el conductor de la lista
        	                c.eliminarVehiculo(); // Elimina al vehiculo asociado con el conductor
        	            }
        			
        			}
        			
        		}
        		
    	}	
    		
    		System.out.println("Seleccione la transportadora a la cual quiere ver si ha cancelado monto");
        	int sc = Main_3.readInt();
        	
        	while (sc > Terminal.getTransportadoras().size() || sc <= 0) {
        		
        		System.out.println("Ingrese nuevamente su número, ingresó un numero incorrecto ");
            	sc = Main_3.readInt();
     	
        	}
        	
        	Transportadora t = Terminal.getTransportadoras().get(i-1);
        	
        	if (t.verificarPagoTerminal()) {
        		
        		System.out.println("La transportadora " + t.getNombre() + " ha cancelado el monto.");
        	    System.out.println("El monto fue de " + t.RetornarValorAPagarTerminal() + " pesos.");
        	    System.out.println("El pago se realizó en la fecha: " + t.getFechaPago());
        	    
        	}else {
        		
        		System.out.println("La transportadora " + t.getNombre() + " no ha cancelado el monto.");
        		System.out.println("¿Desea ver los conductores que ya no pertenecen a la terminal? ingrese si o no");
        		String entrada = Main_3.readString();
        		
        		if (entrada.equalsIgnoreCase("si")) {
        			
        			for (Conductor c: t.getConductores()) {
        				
        				int numConductores = t.getConductores().size();
    					
        				double dineroTransportadora = t.getDinero(); // 
        				
        				double liquidacionConductores = dineroTransportadora / numConductores;

        			        System.out.println("El conductor " + c.getNombre() + " ya no pertenece a la terminal.");
        			        System.out.println("Su liquidación fue de " + liquidacionConductores + " pesos.");
        			        System.out.println("El pago se efectuó el " + t.getFechaPago() + ".");
        			        System.out.println(); 
        			        
        			}	
        			
        		}
        			
        		
        	}
        	
    		
    	}
    	
    }
    
    public static void estadisticasGenerales() {
    	
    	
    	
    	
    }
    
    public static void conductoresLiquidados() {
    	
    	System.out.println("Señor administrador, a continuación verá usted los conductores que han sido liquidados");
    	
    	for (Conductor c : Conductor.getConductores()) {
    		
    		if (c.getDinero() == 0 && c.getTransportadora().getNumeroDePagosRealizados() > 0) { // Ya la transportadora ha pagado y el conductor no ha recibido dinero
    			
    			c.getTransportadora().pagarConductor(c);
  	
    			
    		}
    		
    	}
    	
    	
    	
    	
    }
    
    public static void verificarCompraTiquetes() {
    	
    	
    }
    
}

