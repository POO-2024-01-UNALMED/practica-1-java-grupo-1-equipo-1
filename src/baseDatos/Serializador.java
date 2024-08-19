package baseDatos;

import gestorAplicacion.administrativo.*;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.Dia;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializador {
    
	private static void serializar(ArrayList< ?extends Object > listas, String nombre) {
		
		File archivo = new File("");
		
		try {
			File path = new File(archivo.getAbsolutePath()+"/src/baseDatos/temp/" + nombre + ".txt");
			
			FileOutputStream f = new FileOutputStream(path);
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(listas);
			
			o.close();
			f.close();
		}
		catch(FileNotFoundException e){
			System.out.println("No se encuentra el archivo");
		}
		catch(IOException e){
			System.out.println("Error inicialización");
		}
	}
	
	public static void serializarListas(){
		Serializador.serializar(Transportadora.getTransportadoras(), "transportadora");// OBJETOS DE TRANSPORTADORA
		Serializador.serializar(Terminal.getListaTerminales(), "terminal");           // OBJETO TERMINAL
		Serializador.serializar(Terminal.getHistorial(), "historialViajes");         // VIAJES TERMINADOS - HISTORIAL
	    Serializador.serializar(Terminal.getViajesEnCurso(), "viajesEnCurso");      // VIAJES SIN TERMINAR - EN CURSO
	    Serializador.serializar(Terminal.getViajes(), "viajesDisponibles");        // VIAJES SIN SALIR - DISPONIBLES
	    Serializador.serializar(Tiempo.principal, "tiempoObjetos");               // OBJETOS TIEMPO - PERMITE GUARDAR EL PROGRESO DEL TIEMPO
	    Serializador.serializar(Persona.getSerializarPersonas(), "personas");    // OBJETOS TIPO PERSONA
	    Serializador.serializar(Factura.getFacturasCreadas(), "facturas");      // OBJETOS TIPO FACTURA
	    Serializador.serializar(Taller.getListaTalleres(), "talleres");        // OBJETOS TIPO TALLER
	    Serializador.serializar(Vehiculo.getListaVehiculos(), "vehiculos");   // OBJETOS TIPO VEHICULO
	}
		
	
    // Métodos de Guardar y Cargar Estado Estático - - - - Utlizado para actualizar los atributos de clase de Tiempo, debido a que implementa la Clase Timer que no es Serializable. 
    public static void guardarEstado() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\baseDatos\\temp\\estado_tiempo.txt"))) {
            writer.write("minutos=" + Tiempo.minutos);
            writer.newLine();
            writer.write("horas=" + Tiempo.horas);
            writer.newLine();
            writer.write("dias=" + Tiempo.dias);
            writer.newLine();
            writer.write("semana=" + Tiempo.semana);
            writer.newLine();
            writer.write("meses=" + Tiempo.meses);
            writer.newLine();
            writer.write("año=" + Tiempo.año);
            writer.newLine();
            writer.write("salidaFecha=" + Tiempo.salidaFecha);
            writer.newLine();
            writer.write("salidaHora=" + Tiempo.salidaHora);
        } catch (IOException e) {
        	System.out.println("Error inicialización");
        }
    }
    
	public static void objetosBase() {
		//new Tiempo();
		
		Terminal terminal = new Terminal("Terminal del norte", 99999999, 500, 1, 20, null,  null, 0, Destino.MEDELLIN); //(String nombre, double dinero, int capacidadVehiculos, int cantidadSedes, int cantidadVehiculos, ArrayList <Transportadora> transportadoras, ArrayList <Destino> destinos, double comision, Destino ubicacion)

		ArrayList<Conductor> conductoresRegistrados1 = new ArrayList<>();
		ArrayList<Conductor> conductoresRegistrados2 = new ArrayList<>();
		ArrayList<Conductor> conductoresRegistrados3 = new ArrayList<>();
		ArrayList<Conductor> conductoresRegistrados4 = new ArrayList<>();
		ArrayList<Conductor> conductoresRegistrados5 = new ArrayList<>();
		ArrayList<Conductor> conductoresRegistrados6 = new ArrayList<>();
		

		Transportadora transportadoraRapida = new Transportadora("Transportadora Rapida", 196000.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, terminal, null, new ArrayList<>(), new ArrayList<>(), null, 4.5);
		Transportadora transportadoraEficiente = new Transportadora("Transportadora Eficiente", 189000.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, terminal, null, new ArrayList<>(), new ArrayList<>(), null, 4.0);
		Transportadora transportadoraExpress = new Transportadora("Transportadora Express", 223000.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, terminal, null, new ArrayList<>(), new ArrayList<>(), null, 3.5);
		Transportadora transportadoraSegura = new Transportadora("Transportadora Segura", 204000.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, terminal, null, new ArrayList<>(), new ArrayList<>(), null, 5.0);
		Transportadora transportadoraGlobal = new Transportadora("Transportadora Global", 305000.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, terminal, null, new ArrayList<>(), new ArrayList<>(), null, 2.5);
		Transportadora transportadoraLocal = new Transportadora("Transportadora Local", 250000.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, terminal, null, new ArrayList<>(), new ArrayList<>(), null, 4.7);
				                       
		

		
		// Creación de un ArrayList y adición de las instancias de Transportadora
	    ArrayList<Transportadora> transportadoras = new ArrayList<>();
		transportadoras.add(transportadoraRapida);
		transportadoras.add(transportadoraEficiente);
		transportadoras.add(transportadoraExpress);
		transportadoras.add(transportadoraSegura);
		transportadoras.add(transportadoraGlobal);
		transportadoras.add(transportadoraLocal);

		terminal.setTransportadoras(transportadoras);

		ArrayList<Destino> destinos1 = new ArrayList<>();
	    destinos1.add(Destino.ANGELOPOLIS);
	    destinos1.add(Destino.BARRANQUILLA);
	    destinos1.add(Destino.CALI);
	    destinos1.add(Destino.BARBOSA);
	    transportadoraExpress.setDestinos(destinos1);

	    ArrayList<Destino> destinos2 = new ArrayList<>();
	    destinos2.add(Destino.GUARNE);
	    destinos2.add(Destino.MARINILLA);
	    destinos2.add(Destino.RIONEGRO);
	    destinos2.add(Destino.LAPINTADA);
	    transportadoraSegura.setDestinos(destinos2);

	    
	    ArrayList<Destino> destinos3 = new ArrayList<>();
	    destinos3.add(Destino.COOPACABANA);
	    destinos3.add(Destino.BELLO);
	    destinos3.add(Destino.GIRARDOTA);
	    destinos3.add(Destino.GUATAPE);
	    transportadoraGlobal.setDestinos(destinos3);
	    
	    
	    ArrayList<Destino> destinos4 = new ArrayList<>();
	    destinos4.add(Destino.ANGELOPOLIS);
	    destinos4.add(Destino.BARRANQUILLA);
	    destinos4.add(Destino.CALI);
	    destinos4.add(Destino.BARBOSA);
	    transportadoraLocal.setDestinos(destinos4);
	    
	    //Talleres 
	    
		Taller tallerRapido = new Taller(transportadoraRapida, transportadoraRapida.getDestinoAsignado(), "Taller Rápido", 10);
		Taller tallerEficiente = new Taller(transportadoraEficiente, transportadoraEficiente.getDestinoAsignado(), "Taller Eficiente", 10);
		Taller tallerExpress = new Taller(transportadoraExpress, transportadoraExpress.getDestinoAsignado(), "Taller Express", 10);
		Taller tallerSegura = new Taller(transportadoraSegura, transportadoraSegura.getDestinoAsignado(), "Taller Seguro", 10);
		Taller tallerGlobal = new Taller(transportadoraGlobal, transportadoraGlobal.getDestinoAsignado(), "Taller Global", 10);
		//Taller tallerLocal = new Taller(transportadoraLocal, transportadoraLocal.getDestinoAsignado(), "Taller Local", 10);
	    
	    //Vehiculos
	    Vehiculo vehiculo1 = new Vehiculo("ABC123", "ModeloA", 12500.00, 120.0, TipoVehiculo.BUS, transportadoraRapida);
        Vehiculo vehiculo2 = new Vehiculo("DEF456", "ModeloB", 13500.00, 130.0, TipoVehiculo.ESCALERA, transportadoraRapida);
        Vehiculo vehiculo3 = new Vehiculo("GHI789", "ModeloC", 14500.00, 125.0, TipoVehiculo.VANS, transportadoraRapida);
        Vehiculo vehiculo4 = new Vehiculo("JKL012", "ModeloD", 15500.00, 140.0, TipoVehiculo.TAXI, transportadoraRapida);
        Vehiculo vehiculo5 = new Vehiculo("MNO345", "ModeloE", 16500.00, 135.0, TipoVehiculo.BUS, transportadoraRapida);
        Vehiculo vehiculo11 = new Vehiculo("EFG123", "ModeloK", 22500.00, 140.0, TipoVehiculo.ESCALERA, transportadoraEficiente);
        Vehiculo vehiculo12 = new Vehiculo("HIJ456", "ModeloL", 23500.00, 145.0, TipoVehiculo.VANS, transportadoraEficiente);
        Vehiculo vehiculo13 = new Vehiculo("KLM789", "ModeloM", 24500.00, 120.0, TipoVehiculo.TAXI, transportadoraEficiente);
        Vehiculo vehiculo14 = new Vehiculo("NOP012", "ModeloN", 25500.00, 125.0, TipoVehiculo.BUS, transportadoraEficiente);
        Vehiculo vehiculo15 = new Vehiculo("QRS345", "ModeloO", 26500.00, 130.0, TipoVehiculo.ESCALERA, transportadoraEficiente);
        Vehiculo vehiculo21 = new Vehiculo("IJK123", "ModeloU", 32500.00, 135.0, TipoVehiculo.BUS, transportadoraExpress);
        Vehiculo vehiculo22 = new Vehiculo("LMN456", "ModeloV", 33500.00, 140.0, TipoVehiculo.ESCALERA, transportadoraExpress);
        Vehiculo vehiculo23 = new Vehiculo("OPQ789", "ModeloW", 34500.00, 145.0, TipoVehiculo.VANS, transportadoraExpress);
        Vehiculo vehiculo24 = new Vehiculo("RST012", "ModeloX", 25000.00, 120.0, TipoVehiculo.TAXI, transportadoraExpress);
        Vehiculo vehiculo25 = new Vehiculo("UVW345", "ModeloY", 26000.00, 125.0, TipoVehiculo.BUS, transportadoraExpress);
        Vehiculo vehiculo31 = new Vehiculo("MNO123", "ModeloEE", 16000.00, 125.0, TipoVehiculo.ESCALERA, transportadoraSegura);
        Vehiculo vehiculo32 = new Vehiculo("PQR456", "ModeloFF", 17000.00, 130.0, TipoVehiculo.VANS, transportadoraSegura);
        Vehiculo vehiculo33 = new Vehiculo("STU789", "ModeloGG", 18000.00, 135.0, TipoVehiculo.TAXI, transportadoraSegura);
        Vehiculo vehiculo34 = new Vehiculo("VWX012", "ModeloHH", 19000.00, 140.0, TipoVehiculo.BUS, transportadoraSegura);
        Vehiculo vehiculo35 = new Vehiculo("YZA345", "ModeloII", 20000.00, 145.0, TipoVehiculo.ESCALERA, transportadoraSegura);
        Vehiculo vehiculo41 = new Vehiculo("QRS123", "ModeloOO", 26000.00, 145.0, TipoVehiculo.VANS, transportadoraGlobal);
        Vehiculo vehiculo42 = new Vehiculo("TUV456", "ModeloPP", 27000.00, 120.0, TipoVehiculo.TAXI, transportadoraGlobal);
        Vehiculo vehiculo43 = new Vehiculo("WXY789", "ModeloQQ", 28000.00, 125.0, TipoVehiculo.BUS, transportadoraGlobal);
        Vehiculo vehiculo44 = new Vehiculo("ZAB012", "ModeloRR", 29000.00, 130.0, TipoVehiculo.ESCALERA, transportadoraGlobal);
        Vehiculo vehiculo45 = new Vehiculo("CDE345", "ModeloSS", 30000.00, 135.0, TipoVehiculo.VANS, transportadoraGlobal);
        Vehiculo vehiculo51 = new Vehiculo("ABC234", "ModeloYY", 11500.00, 110.0, TipoVehiculo.TAXI, transportadoraLocal);
        Vehiculo vehiculo52 = new Vehiculo("DEF567", "ModeloZZ", 12500.00, 120.0, TipoVehiculo.BUS, transportadoraLocal);
        Vehiculo vehiculo53 = new Vehiculo("GHI890", "ModeloAAA", 13500.00, 130.0, TipoVehiculo.ESCALERA, transportadoraLocal);
        Vehiculo vehiculo54 = new Vehiculo("JKL123", "ModeloBBB", 14500.00, 140.0, TipoVehiculo.VANS, transportadoraLocal);
        Vehiculo vehiculo55 = new Vehiculo("MNO456", "ModeloCCC", 15500.00, 125.0, TipoVehiculo.TAXI, transportadoraLocal);



				
	    
				ArrayList<Conductor> conductores1 = new ArrayList<>();
				ArrayList<Conductor> conductores2 = new ArrayList<>();
				ArrayList<Conductor> conductores3 = new ArrayList<>();
				ArrayList<Conductor> conductores4 = new ArrayList<>();
				ArrayList<Conductor> conductores5 = new ArrayList<>();
				ArrayList<Conductor> conductores6 = new ArrayList<>();

				ArrayList<Vehiculo> vehiculos1 = new ArrayList<>();
				ArrayList<Vehiculo> vehiculos2 = new ArrayList<>();
				ArrayList<Vehiculo> vehiculos3 = new ArrayList<>();
				ArrayList<Vehiculo> vehiculos4 = new ArrayList<>();
				ArrayList<Vehiculo> vehiculos5 = new ArrayList<>();
				ArrayList<Vehiculo> vehiculos6 = new ArrayList<>();


				ArrayList<Mecanico> mecanicos1 = new ArrayList<>();
				ArrayList<Mecanico> mecanicos2 = new ArrayList<>();
				ArrayList<Mecanico> mecanicos3 = new ArrayList<>();
				ArrayList<Mecanico> mecanicos4 = new ArrayList<>();
				ArrayList<Mecanico> mecanicos5 = new ArrayList<>();
				ArrayList<Mecanico> mecanicos6 = new ArrayList<>();



				Conductor conductor1 = new Conductor(1, 30, "Carlos Gómez", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 200);
				Conductor conductor2 = new Conductor(2, 28, "José Martínez", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 190);
				Conductor conductor3 = new Conductor(3, 35, "Luis Rodríguez", 'M', new ArrayList<>(), 7, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 9, 210);
				Conductor conductor4 = new Conductor(4, 32, "Miguel Sánchez", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 8, 220);
				Conductor conductor5 = new Conductor(5, 29, "Juan Pérez", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 180);
				Conductor conductor6 = new Conductor(6, 31, "Pedro Fernández", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 200);
				Conductor conductor7 = new Conductor(7, 27, "Antonio López", 'M', new ArrayList<>(), 3, 1300.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 170);
				Conductor conductor8 = new Conductor(8, 34, "Francisco García", 'M', new ArrayList<>(), 6, 1575.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 9, 215);
				Conductor conductor9 = new Conductor(9, 33, "Jorge Hernández", 'M', new ArrayList<>(), 7, 1620.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 7, 230);
				Conductor conductor10 = new Conductor(10, 29, "Alberto Ruiz", 'M', new ArrayList<>(), 4, 1460.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 190);
				Conductor conductor11 = new Conductor(11, 28, "Fernando Castro", 'M', new ArrayList<>(), 5, 1490.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 200);
				Conductor conductor12 = new Conductor(12, 36, "Raúl Ramírez", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 9, 210);
				Conductor conductor13 = new Conductor(13, 31, "Manuel Torres", 'M', new ArrayList<>(), 6, 1580.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 8, 220);
				Conductor conductor14 = new Conductor(14, 30, "Héctor Gil", 'M', new ArrayList<>(), 5, 1520.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 200);
				Conductor conductor15 = new Conductor(15, 29, "Vicente Díaz", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 180);
				Conductor conductor16 = new Conductor(16, 32, "Ángel Ramos", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor conductor17 = new Conductor(17, 30, "Javier Navarro", 'M', new ArrayList<>(), 5, 1480.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 9, 220);
				Conductor conductor18 = new Conductor(18, 34, "Mario Ortiz", 'M', new ArrayList<>(), 6, 1570.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 8, 215);
				Conductor conductor19 = new Conductor(19, 29, "Andrés Flores", 'M', new ArrayList<>(), 4, 1420.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 200);
				Conductor conductor20 = new Conductor(20, 35, "Daniel Maldonado", 'M', new ArrayList<>(), 7, 1610.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 230);				                  

				conductores1.add(conductor1);
				conductores1.add(conductor2);
				conductores1.add(conductor3);
				conductores1.add(conductor4);
				conductores1.add(conductor5);
				conductores1.add(conductor6);
				conductores1.add(conductor7);
				conductores1.add(conductor8);
				conductores1.add(conductor9);
				conductores1.add(conductor10);
				
				conductoresRegistrados1.add(conductor11);
				conductoresRegistrados1.add(conductor12);
				conductoresRegistrados1.add(conductor13);
				conductoresRegistrados1.add(conductor14);
				conductoresRegistrados1.add(conductor15);
				conductoresRegistrados1.add(conductor16);
				conductoresRegistrados1.add(conductor17);
				conductoresRegistrados1.add(conductor18);
				conductoresRegistrados1.add(conductor19);
				conductoresRegistrados1.add(conductor20);
				
				
				

				conductor1.setTransportadora(transportadoraRapida);
				conductor2.setTransportadora(transportadoraRapida);
				conductor3.setTransportadora(transportadoraRapida);
				conductor4.setTransportadora(transportadoraRapida);
				conductor5.setTransportadora(transportadoraRapida);
				conductor6.setTransportadora(transportadoraRapida);
				conductor7.setTransportadora(transportadoraRapida);
				conductor8.setTransportadora(transportadoraRapida);
				conductor9.setTransportadora(transportadoraRapida);
				conductor10.setTransportadora(transportadoraRapida);
				conductor11.setTransportadora(transportadoraRapida);
				conductor12.setTransportadora(transportadoraRapida);
				conductor13.setTransportadora(transportadoraRapida);
				conductor14.setTransportadora(transportadoraRapida);
				conductor15.setTransportadora(transportadoraRapida);
				conductor16.setTransportadora(transportadoraRapida);
				conductor17.setTransportadora(transportadoraRapida);
				conductor18.setTransportadora(transportadoraRapida);
				conductor19.setTransportadora(transportadoraRapida);
				conductor20.setTransportadora(transportadoraRapida);




				Conductor conductor21 = new Conductor(21, 36, "Rubén Vargas", 'M', new ArrayList<>(), 8, 1700.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 230);
				Conductor conductor22 = new Conductor(22, 30, "Adrián Muñoz", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 200);
				Conductor conductor23 = new Conductor(23, 29, "Santiago Reyes", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 190);
				Conductor conductor24 = new Conductor(24, 37, "Sebastián Castillo", 'M', new ArrayList<>(), 9, 1750.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 240);
				Conductor conductor25 = new Conductor(25, 32, "Martín Rojas", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor conductor26 = new Conductor(26, 31, "Iván Morales", 'M', new ArrayList<>(), 5, 1520.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 220);
				Conductor conductor27 = new Conductor(27, 30, "Tomás Herrera", 'M', new ArrayList<>(), 4, 1490.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 180);
				Conductor conductor28 = new Conductor(28, 38, "Enrique Romero", 'M', new ArrayList<>(), 10, 1800.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 250);
				Conductor conductor29 = new Conductor(29, 33, "Eduardo Morales", 'M', new ArrayList<>(), 6, 1570.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 220);
				Conductor conductor30 = new Conductor(30, 31, "Gabriel Paredes", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor conductor31 = new Conductor(31, 28, "Emilio Duarte", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 190);
				Conductor conductor32 = new Conductor(32, 35, "Felipe Soto", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 230);
				Conductor conductor33 = new Conductor(33, 29, "Gonzalo Montalvo", 'M', new ArrayList<>(), 5, 1480.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 200);
				Conductor conductor34 = new Conductor(34, 36, "Hugo Escobar", 'M', new ArrayList<>(), 8, 1720.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 240);
				Conductor conductor35 = new Conductor(35, 32, "Diego Olivares", 'M', new ArrayList<>(), 6, 1580.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 220);
				Conductor conductor36 = new Conductor(36, 34, "Iván Castañeda", 'M', new ArrayList<>(), 7, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 230);
				Conductor conductor37 = new Conductor(37, 30, "Joaquín Salinas", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 200);
				Conductor conductor38 = new Conductor(38, 37, "Ismael Peña", 'M', new ArrayList<>(), 9, 1750.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 240);
				Conductor conductor39 = new Conductor(39, 33, "Rafael Marín", 'M', new ArrayList<>(), 6, 1570.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 220);
				Conductor conductor40 = new Conductor(40, 31, "Alejandro Campos", 'M', new ArrayList<>(), 5, 1490.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 210);		       

				// Agregar los nuevos conductores al ArrayList
				conductores2.add(conductor21);
				conductores2.add(conductor22);
				conductores2.add(conductor23);
				conductores2.add(conductor24);
				conductores2.add(conductor25);
				conductores2.add(conductor26);
				conductores2.add(conductor27);
				conductores2.add(conductor28);
				conductores2.add(conductor29);
				conductores2.add(conductor30);
				
				conductoresRegistrados2.add(conductor21);
				conductoresRegistrados2.add(conductor22);
				conductoresRegistrados2.add(conductor23);
				conductoresRegistrados2.add(conductor24);
				conductoresRegistrados2.add(conductor25);
				conductoresRegistrados2.add(conductor26);
				conductoresRegistrados2.add(conductor27);
				conductoresRegistrados2.add(conductor28);
				conductoresRegistrados2.add(conductor29);
				conductoresRegistrados2.add(conductor30);
				


				conductor21.setTransportadora(transportadoraEficiente);
				conductor22.setTransportadora(transportadoraEficiente);
				conductor23.setTransportadora(transportadoraEficiente);
				conductor24.setTransportadora(transportadoraEficiente);
				conductor25.setTransportadora(transportadoraEficiente);
				conductor26.setTransportadora(transportadoraEficiente);
				conductor27.setTransportadora(transportadoraEficiente);
				conductor28.setTransportadora(transportadoraEficiente);
				conductor29.setTransportadora(transportadoraEficiente);
				conductor30.setTransportadora(transportadoraEficiente);
				conductor31.setTransportadora(transportadoraEficiente);
				conductor32.setTransportadora(transportadoraEficiente);
				conductor33.setTransportadora(transportadoraEficiente);
				conductor34.setTransportadora(transportadoraEficiente);
				conductor35.setTransportadora(transportadoraEficiente);
				conductor36.setTransportadora(transportadoraEficiente);
				conductor37.setTransportadora(transportadoraEficiente);
				conductor38.setTransportadora(transportadoraEficiente);
				conductor39.setTransportadora(transportadoraEficiente);
				conductor40.setTransportadora(transportadoraEficiente);


				Conductor conductor41 = new Conductor(41, 29, "Álvaro Soto", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 185);
				Conductor conductor42 = new Conductor(42, 31, "Carlos Guzmán", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 195);
				Conductor conductor43 = new Conductor(43, 34, "Luis Ortega", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor conductor44 = new Conductor(44, 28, "Marcelo Fernández", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 190);
				Conductor conductor45 = new Conductor(45, 33, "Antonio Vargas", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 220);
				Conductor conductor46 = new Conductor(46, 30, "Ricardo Ruiz", 'M', new ArrayList<>(), 5, 1480.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor conductor47 = new Conductor(47, 32, "Roberto Pérez", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 225);
				Conductor conductor48 = new Conductor(48, 27, "Javier Romero", 'M', new ArrayList<>(), 4, 1350.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 185);
				Conductor conductor49 = new Conductor(49, 36, "Felipe Calderón", 'M', new ArrayList<>(), 8, 1700.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 240);
				Conductor conductor50 = new Conductor(50, 31, "Julio Martínez", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 215);
				Conductor conductor51 = new Conductor(51, 29, "Héctor Villalobos", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 200);
				Conductor conductor52 = new Conductor(52, 34, "Luis González", 'M', new ArrayList<>(), 7, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 225);
				Conductor conductor53 = new Conductor(53, 30, "Samuel Morales", 'M', new ArrayList<>(), 5, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 210);
				Conductor conductor54 = new Conductor(54, 37, "Victor Gómez", 'M', new ArrayList<>(), 9, 1750.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 240);
				Conductor conductor55 = new Conductor(55, 33, "Óscar Sandoval", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 225);
				Conductor conductor56 = new Conductor(56, 32, "Mauricio Ramírez", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 220);
				Conductor conductor57 = new Conductor(57, 35, "Fernando Vega", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 230);
				Conductor conductor58 = new Conductor(58, 31, "Emmanuel Ruiz", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 200);
				Conductor conductor59 = new Conductor(59, 28, "Jorge Silva", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 190);
				Conductor conductor60 = new Conductor(60, 30, "Esteban Cruz", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 215);

				conductores3.add(conductor41);
				conductores3.add(conductor42);
				conductores3.add(conductor43);
				conductores3.add(conductor44);
				conductores3.add(conductor45);
				conductores3.add(conductor46);
				conductores3.add(conductor47);
				conductores3.add(conductor48);
				conductores3.add(conductor49);
				conductores3.add(conductor50);
			

				conductoresRegistrados3.add(conductor51);
				conductoresRegistrados3.add(conductor52);
				conductoresRegistrados3.add(conductor53);
				conductoresRegistrados3.add(conductor54);
				conductoresRegistrados3.add(conductor55);
				conductoresRegistrados3.add(conductor56);
				conductoresRegistrados3.add(conductor57);
				conductoresRegistrados3.add(conductor58);
				conductoresRegistrados3.add(conductor59);
				conductoresRegistrados3.add(conductor60);

				
				conductor41.setTransportadora(transportadoraExpress);
				conductor42.setTransportadora(transportadoraExpress);
				conductor43.setTransportadora(transportadoraExpress);
				conductor44.setTransportadora(transportadoraExpress);
				conductor45.setTransportadora(transportadoraExpress);
				conductor46.setTransportadora(transportadoraExpress);
				conductor47.setTransportadora(transportadoraExpress);
				conductor48.setTransportadora(transportadoraExpress);
				conductor49.setTransportadora(transportadoraExpress);
				conductor50.setTransportadora(transportadoraExpress);
				conductor51.setTransportadora(transportadoraExpress);
				conductor52.setTransportadora(transportadoraExpress);
				conductor53.setTransportadora(transportadoraExpress);
				conductor54.setTransportadora(transportadoraExpress);
				conductor55.setTransportadora(transportadoraExpress);
				conductor56.setTransportadora(transportadoraExpress);
				conductor57.setTransportadora(transportadoraExpress);
				conductor58.setTransportadora(transportadoraExpress);
				conductor59.setTransportadora(transportadoraExpress);
				conductor60.setTransportadora(transportadoraExpress);


				Conductor conductor61 = new Conductor(61, 29, "Mauricio Álvarez", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 190);
				Conductor conductor62 = new Conductor(62, 32, "Gabriel Herrera", 'M', new ArrayList<>(), 6, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 205);
				Conductor  conductor63 = new Conductor(63, 30, "Eduardo Rivas", 'M', new ArrayList<>(), 5, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 220);
				Conductor  conductor64 = new Conductor(64, 34, "Ricardo Beltrán", 'M', new ArrayList<>(), 7, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 225);
				Conductor  conductor65 = new Conductor(65, 28, "Alejandro Guzmán", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 200);
				Conductor  conductor66 = new Conductor(66, 33, "Óscar Gutiérrez", 'M', new ArrayList<>(), 6, 1570.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 215);
				Conductor  conductor67 = new Conductor(67, 31, "Santiago Mendoza", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor  conductor68 = new Conductor(68, 35, "Héctor Núñez", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 230);
				Conductor  conductor69 = new Conductor(69, 30, "Manuel Vargas", 'M', new ArrayList<>(), 5, 1520.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 205);
				Conductor  conductor70 = new Conductor(70, 37, "Jorge Moreno", 'M', new ArrayList<>(), 9, 1750.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 240);
				Conductor  conductor71 = new Conductor(71, 31, "Luis Silva", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 220);
				Conductor  conductor72 = new Conductor(72, 29, "Carlos Martínez", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 200);
				Conductor  conductor73 = new Conductor(73, 34, "Felipe Díaz", 'M', new ArrayList<>(), 7, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 225);
				Conductor  conductor74 = new Conductor(74, 30, "Javier López", 'M', new ArrayList<>(), 5, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 210);
				Conductor  conductor75 = new Conductor(75, 36, "Mario Castro", 'M', new ArrayList<>(), 8, 1700.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 240);
				Conductor  conductor76 = new Conductor(76, 32, "Fernando Soto", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 220);
				Conductor  conductor77 = new Conductor(77, 33, "Adrián Díaz", 'M', new ArrayList<>(), 6, 1570.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 225);
				Conductor  conductor78 = new Conductor(78, 28, "Ramiro Aguirre", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 190);
				Conductor  conductor79 = new Conductor(79, 35, "Mauricio Araya", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 230);
				Conductor  conductor80 = new Conductor(80, 31, "Luis Hernández", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 205);

				conductores4.add(conductor61);
				conductores4.add(conductor62);
				conductores4.add(conductor63);
				conductores4.add(conductor64);
				conductores4.add(conductor65);
				conductores4.add(conductor66);
				conductores4.add(conductor67);
				conductores4.add(conductor68);
				conductores4.add(conductor69);
				conductores4.add(conductor70);
				
				conductoresRegistrados4.add(conductor71);
				conductoresRegistrados4.add(conductor72);
				conductoresRegistrados4.add(conductor73);
				conductoresRegistrados4.add(conductor74);
				conductoresRegistrados4.add(conductor75);
				conductoresRegistrados4.add(conductor76);
				conductoresRegistrados4.add(conductor77);
				conductoresRegistrados4.add(conductor78);
				conductoresRegistrados4.add(conductor79);
				conductoresRegistrados4.add(conductor80);


				conductor61.setTransportadora(transportadoraSegura);
				conductor62.setTransportadora(transportadoraSegura);
				conductor63.setTransportadora(transportadoraSegura);
				conductor64.setTransportadora(transportadoraSegura);
				conductor65.setTransportadora(transportadoraSegura);
				conductor66.setTransportadora(transportadoraSegura);
				conductor67.setTransportadora(transportadoraSegura);
				conductor68.setTransportadora(transportadoraSegura);
				conductor69.setTransportadora(transportadoraSegura);
				conductor70.setTransportadora(transportadoraSegura);
				conductor71.setTransportadora(transportadoraSegura);
				conductor72.setTransportadora(transportadoraSegura);
				conductor73.setTransportadora(transportadoraSegura);
				conductor74.setTransportadora(transportadoraSegura);
				conductor75.setTransportadora(transportadoraSegura);
				conductor76.setTransportadora(transportadoraSegura);
				conductor77.setTransportadora(transportadoraSegura);
				conductor78.setTransportadora(transportadoraSegura);
				conductor79.setTransportadora(transportadoraSegura);
				conductor80.setTransportadora(transportadoraSegura);


				Conductor conductor81 = new Conductor(81, 30, "Jorge Sandoval", 'M', new ArrayList<>(), 5, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor conductor82 = new Conductor(82, 32, "Sergio Montoya", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 220);
				Conductor  conductor83 = new Conductor(83, 31, "Ricardo Morales", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 215);
				Conductor  conductor84 = new Conductor(84, 29, "Mauricio Valenzuela", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 200);
				Conductor  conductor85 = new Conductor(85, 35, "Héctor Mejía", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 230);
				Conductor  conductor86 = new Conductor(86, 30, "Fernando Arrieta", 'M', new ArrayList<>(), 5, 1520.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 210);
				Conductor  conductor87 = new Conductor(87, 37, "Mario Cordero", 'M', new ArrayList<>(), 9, 1750.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 240);
				Conductor  conductor88 = new Conductor(88, 33, "Javier Hernández", 'M', new ArrayList<>(), 6, 1570.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 225);
				Conductor  conductor89 = new Conductor(89, 32, "Luis Palacios", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 220);
				Conductor  conductor90 = new Conductor(90, 34, "Ángel Peña", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 230);
				Conductor  conductor91 = new Conductor(91, 31, "Carlos Ramírez", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor  conductor92 = new Conductor(92, 29, "Andrés Soto", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 190);
				Conductor  conductor93 = new Conductor(93, 33, "Jorge Morales", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 215);
				Conductor  conductor94 = new Conductor(94, 30, "Oscar Hernández", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 205);
				Conductor  conductor95 = new Conductor(95, 36, "Luis García", 'M', new ArrayList<>(), 8, 1700.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 240);
				Conductor  conductor96 = new Conductor(96, 32, "Samuel Lozano", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 220);
				Conductor  conductor97 = new Conductor(97, 31, "Ricardo Nieto", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 210);
				Conductor  conductor98 = new Conductor(98, 28, "Felipe Castaño", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 195);
				Conductor  conductor99 = new Conductor(99, 35, "Manuel Vargas", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 230);
				Conductor  conductor100 = new Conductor(100, 33, "Eduardo Peña", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 215);

				conductores5.add(conductor81);
				conductores5.add(conductor82);
				conductores5.add(conductor83);
				conductores5.add(conductor84);
				conductores5.add(conductor85);
				conductores5.add(conductor86);
				conductores5.add(conductor87);
				conductores5.add(conductor88);
				conductores5.add(conductor89);
				conductores5.add(conductor90);
				
				conductoresRegistrados5.add(conductor91);
				conductoresRegistrados5.add(conductor92);
				conductoresRegistrados5.add(conductor93);
				conductoresRegistrados5.add(conductor94);
				conductoresRegistrados5.add(conductor95);
				conductoresRegistrados5.add(conductor96);
				conductoresRegistrados5.add(conductor97);
				conductoresRegistrados5.add(conductor98);
				conductoresRegistrados5.add(conductor99);
				conductoresRegistrados5.add(conductor100);

				conductor81.setTransportadora(transportadoraGlobal);
				conductor82.setTransportadora(transportadoraGlobal);
				conductor83.setTransportadora(transportadoraGlobal);
				conductor84.setTransportadora(transportadoraGlobal);
				conductor85.setTransportadora(transportadoraGlobal);
				conductor86.setTransportadora(transportadoraGlobal);
				conductor87.setTransportadora(transportadoraGlobal);
				conductor88.setTransportadora(transportadoraGlobal);
				conductor89.setTransportadora(transportadoraGlobal);
				conductor90.setTransportadora(transportadoraGlobal);
				conductor91.setTransportadora(transportadoraGlobal);
				conductor92.setTransportadora(transportadoraGlobal);
				conductor93.setTransportadora(transportadoraGlobal);
				conductor94.setTransportadora(transportadoraGlobal);
				conductor95.setTransportadora(transportadoraGlobal);
				conductor96.setTransportadora(transportadoraGlobal);
				conductor97.setTransportadora(transportadoraGlobal);
				conductor98.setTransportadora(transportadoraGlobal);
				conductor99.setTransportadora(transportadoraGlobal);
				conductor100.setTransportadora(transportadoraGlobal);


				Conductor conductor101 = new Conductor(101, 30, "Héctor Jiménez", 'M', new ArrayList<>(), 5, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 210);
				Conductor  conductor102 = new Conductor(102, 32, "Santiago Díaz", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 225);
				Conductor  conductor103 = new Conductor(103, 31, "Gabriel Andrade", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 215);
				Conductor  conductor104 = new Conductor(104, 29, "Alejandro Rodríguez", 'M', new ArrayList<>(), 4, 1450.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 200);
				Conductor  conductor105 = new Conductor(105, 35, "Mauricio Ortega", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 230);
				Conductor  conductor106 = new Conductor(106, 30, "Ricardo Vargas", 'M', new ArrayList<>(), 5, 1520.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 210);
				Conductor  conductor107 = new Conductor(107, 37, "Javier Sánchez", 'M', new ArrayList<>(), 9, 1750.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 240);
				Conductor  conductor108 = new Conductor(108, 33, "Luis Carrillo", 'M', new ArrayList<>(), 6, 1570.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 220);
				Conductor  conductor109 = new Conductor(109, 32, "Carlos Martínez", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 225);
				Conductor  conductor110 = new Conductor(110, 34, "Óscar Morales", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 230);
				Conductor  conductor111 = new Conductor(111, 31, "Mario Ruiz", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 215);
				Conductor  conductor112 = new Conductor(112, 29, "Ramiro Guzmán", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 190);
				Conductor  conductor113 = new Conductor(113, 33, "Fernando Pérez", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 220);
				Conductor  conductor114 = new Conductor(114, 30, "Álvaro Díaz", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 14, 205);
				Conductor  conductor115 = new Conductor(115, 36, "Jorge Calderón", 'M', new ArrayList<>(), 8, 1700.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 240);
				Conductor  conductor116 = new Conductor(116, 32, "Héctor Mendoza", 'M', new ArrayList<>(), 6, 1600.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 11, 225);
				Conductor  conductor117 = new Conductor(117, 31, "Esteban Cordero", 'M', new ArrayList<>(), 5, 1500.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 13, 210);
				Conductor  conductor118 = new Conductor(118, 28, "Ricardo Silva", 'M', new ArrayList<>(), 4, 1400.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 15, 195);
				Conductor  conductor119 = new Conductor(119, 35, "José Martínez", 'M', new ArrayList<>(), 7, 1650.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 10, 230);
				Conductor  conductor120 = new Conductor(120, 33, "Óscar Guerrero", 'M', new ArrayList<>(), 6, 1550.0, true, null, null, new ArrayList<>(), new ArrayList<>(), 12, 215);

				conductores6.add(conductor101);
				conductores6.add(conductor102);
				conductores6.add(conductor103);
				conductores6.add(conductor104);
				conductores6.add(conductor105);
				conductores6.add(conductor106);
				conductores6.add(conductor107);
				conductores6.add(conductor108);
				conductores6.add(conductor109);
				conductores6.add(conductor110);
				
				conductoresRegistrados6.add(conductor111);
				conductoresRegistrados6.add(conductor112);
				conductoresRegistrados6.add(conductor113);
				conductoresRegistrados6.add(conductor114);
				conductoresRegistrados6.add(conductor115);
				conductoresRegistrados6.add(conductor116);
				conductoresRegistrados6.add(conductor117);
				conductoresRegistrados6.add(conductor118);
				conductoresRegistrados6.add(conductor119);
				conductoresRegistrados6.add(conductor120);


				conductor101.setTransportadora(transportadoraLocal);
				conductor102.setTransportadora(transportadoraLocal);
				conductor103.setTransportadora(transportadoraLocal);
				conductor104.setTransportadora(transportadoraLocal);
				conductor105.setTransportadora(transportadoraLocal);
				conductor106.setTransportadora(transportadoraLocal);
				conductor107.setTransportadora(transportadoraLocal);
				conductor108.setTransportadora(transportadoraLocal);
				conductor109.setTransportadora(transportadoraLocal);
				conductor110.setTransportadora(transportadoraLocal);
				conductor111.setTransportadora(transportadoraLocal);
				conductor112.setTransportadora(transportadoraLocal);
				conductor113.setTransportadora(transportadoraLocal);
				conductor114.setTransportadora(transportadoraLocal);
				conductor115.setTransportadora(transportadoraLocal);
				conductor116.setTransportadora(transportadoraLocal);
				conductor117.setTransportadora(transportadoraLocal);
				conductor118.setTransportadora(transportadoraLocal);
				conductor119.setTransportadora(transportadoraLocal);
				conductor120.setTransportadora(transportadoraLocal);


				transportadoraRapida.setConductores(conductores1);
				transportadoraEficiente.setConductores(conductores2);
				transportadoraExpress.setConductores(conductores3);
				transportadoraSegura.setConductores(conductores4);
				transportadoraGlobal.setConductores(conductores5);
				transportadoraLocal.setConductores(conductores6);
				




				

				conductor1.tomarVehiculo(vehiculo1);
				conductor2.tomarVehiculo(vehiculo1);
				conductor3.tomarVehiculo(vehiculo2);
				conductor4.tomarVehiculo(vehiculo2);
				conductor5.tomarVehiculo(vehiculo3);
				conductor6.tomarVehiculo(vehiculo3);
				conductor7.tomarVehiculo(vehiculo4);
				conductor8.tomarVehiculo(vehiculo4);
				conductor9.tomarVehiculo(vehiculo5);
				conductor10.tomarVehiculo(vehiculo5);
				conductor21.tomarVehiculo(vehiculo11);
				conductor22.tomarVehiculo(vehiculo11);
				conductor23.tomarVehiculo(vehiculo12);
				conductor24.tomarVehiculo(vehiculo12);
				conductor25.tomarVehiculo(vehiculo13);
				conductor26.tomarVehiculo(vehiculo13);
				conductor27.tomarVehiculo(vehiculo14);
				conductor28.tomarVehiculo(vehiculo14);
				conductor29.tomarVehiculo(vehiculo15);
				conductor30.tomarVehiculo(vehiculo15);
				conductor41.tomarVehiculo(vehiculo21);
				conductor42.tomarVehiculo(vehiculo21);
				conductor43.tomarVehiculo(vehiculo22);
				conductor44.tomarVehiculo(vehiculo22);
				conductor45.tomarVehiculo(vehiculo23);
				conductor46.tomarVehiculo(vehiculo23);
				conductor47.tomarVehiculo(vehiculo24);
				conductor48.tomarVehiculo(vehiculo24);
				conductor49.tomarVehiculo(vehiculo25);
				conductor50.tomarVehiculo(vehiculo25);
				conductor61.tomarVehiculo(vehiculo31);
				conductor62.tomarVehiculo(vehiculo31);
				conductor63.tomarVehiculo(vehiculo32);
				conductor64.tomarVehiculo(vehiculo32);
				conductor65.tomarVehiculo(vehiculo33);
				conductor66.tomarVehiculo(vehiculo33);
				conductor67.tomarVehiculo(vehiculo34);
				conductor68.tomarVehiculo(vehiculo34);
				conductor69.tomarVehiculo(vehiculo35);
				conductor70.tomarVehiculo(vehiculo35);
				conductor81.tomarVehiculo(vehiculo41);
				conductor82.tomarVehiculo(vehiculo41);
				conductor83.tomarVehiculo(vehiculo42);
				conductor84.tomarVehiculo(vehiculo42);
				conductor85.tomarVehiculo(vehiculo43);
				conductor86.tomarVehiculo(vehiculo43);
				conductor87.tomarVehiculo(vehiculo44);
				conductor88.tomarVehiculo(vehiculo44);
				conductor89.tomarVehiculo(vehiculo45);
				conductor90.tomarVehiculo(vehiculo45);
				conductor101.tomarVehiculo(vehiculo51);
				conductor102.tomarVehiculo(vehiculo51);
				conductor103.tomarVehiculo(vehiculo52);
				conductor104.tomarVehiculo(vehiculo52);
				conductor105.tomarVehiculo(vehiculo53);
				conductor106.tomarVehiculo(vehiculo53);
				conductor107.tomarVehiculo(vehiculo54);
				conductor108.tomarVehiculo(vehiculo54);
				conductor109.tomarVehiculo(vehiculo55);
				conductor110.tomarVehiculo(vehiculo55);



				vehiculos1.add(vehiculo1);
				vehiculos1.add(vehiculo2);
				vehiculos1.add(vehiculo3);
				vehiculos1.add(vehiculo4);
				vehiculos1.add(vehiculo5);
				vehiculos2.add(vehiculo11);
				vehiculos2.add(vehiculo12);
				vehiculos2.add(vehiculo13);
				vehiculos2.add(vehiculo14);
				vehiculos2.add(vehiculo15);
				vehiculos3.add(vehiculo21);
				vehiculos3.add(vehiculo22);
				vehiculos3.add(vehiculo23);
				vehiculos3.add(vehiculo24);
				vehiculos3.add(vehiculo25);
				vehiculos4.add(vehiculo31);
				vehiculos4.add(vehiculo32);
				vehiculos4.add(vehiculo33);
				vehiculos4.add(vehiculo34);
				vehiculos4.add(vehiculo35);
				vehiculos5.add(vehiculo41);
				vehiculos5.add(vehiculo42);
				vehiculos5.add(vehiculo43);
				vehiculos5.add(vehiculo44);
				vehiculos5.add(vehiculo45);
				vehiculos6.add(vehiculo51);
				vehiculos6.add(vehiculo52);
				vehiculos6.add(vehiculo53);
				vehiculos6.add(vehiculo54);
				vehiculos6.add(vehiculo55);


				transportadoraRapida.setVehiculos(vehiculos1);
				transportadoraEficiente.setVehiculos(vehiculos2);
				transportadoraExpress.setVehiculos(vehiculos3);
				transportadoraSegura.setVehiculos(vehiculos4);
				transportadoraGlobal.setVehiculos(vehiculos5);
				 transportadoraLocal.setVehiculos(vehiculos6);




				 Mecanico mecanico121 = new Mecanico(121, 34, "Alejandro Vargas", 'M', new ArrayList<>(), 5, 1600.0, new ArrayList<>(), tallerRapido, null, true, 15, 200);
				 Mecanico mecanico122 = new Mecanico(122, 28, "Sergio Morales", 'M', new ArrayList<>(), 4, 1450.0, new ArrayList<>(), tallerEficiente, null, true, 12, 180);
				 Mecanico mecanico123 = new Mecanico(123, 37, "Ricardo López", 'M', new ArrayList<>(), 7, 1750.0, new ArrayList<>(), tallerExpress, null, true, 20, 220);
				 Mecanico mecanico124 = new Mecanico(124, 31, "Carlos Martínez", 'M', new ArrayList<>(), 6, 1650.0, new ArrayList<>(), tallerSegura, null, true, 14, 210);
				 Mecanico mecanico125 = new Mecanico(125, 40, "Javier Ramírez", 'M', new ArrayList<>(), 8, 1800.0, new ArrayList<>(), tallerGlobal, null, true, 18, 230);
				 Mecanico mecanico126 = new Mecanico(126, 29, "Fernando Pérez", 'M', new ArrayList<>(), 5, 1550.0, new ArrayList<>(), tallerRapido, null, true, 13, 200);
				 Mecanico mecanico127 = new Mecanico(127, 33, "Héctor Jiménez", 'M', new ArrayList<>(), 6, 1600.0, new ArrayList<>(), tallerEficiente, null, true, 17, 220);
				 Mecanico mecanico128 = new Mecanico(128, 35, "Luis Rodríguez", 'M', new ArrayList<>(), 7, 1700.0, new ArrayList<>(), tallerExpress, null, true, 16, 230);
				 Mecanico mecanico129 = new Mecanico(129, 30, "Manuel García", 'M', new ArrayList<>(), 5, 1500.0, new ArrayList<>(), tallerSegura, null, true, 15, 200);
				 Mecanico mecanico130 = new Mecanico(130, 38, "Tomás Fernández", 'M', new ArrayList<>(), 7, 1750.0, new ArrayList<>(), tallerGlobal, null, true, 19, 220);
				 Mecanico mecanico131 = new Mecanico(131, 32, "Óscar López", 'M', new ArrayList<>(), 6, 1600.0, new ArrayList<>(), tallerRapido, null, true, 14, 210);
				 Mecanico mecanico132 = new Mecanico(132, 31, "David Martínez", 'M', new ArrayList<>(), 5, 1550.0, new ArrayList<>(), tallerEficiente, null, true, 13, 200);
				 Mecanico mecanico133 = new Mecanico(133, 36, "Gabriel Silva", 'M', new ArrayList<>(), 8, 1800.0, new ArrayList<>(), tallerExpress, null, true, 20, 230);
				 Mecanico mecanico134 = new Mecanico(134, 29, "Ricardo Vargas", 'M', new ArrayList<>(), 5, 1500.0, new ArrayList<>(), tallerSegura, null, true, 12, 180);
				 Mecanico mecanico135 = new Mecanico(135, 34, "Adrián Rodríguez", 'M', new ArrayList<>(), 7, 1650.0, new ArrayList<>(), tallerGlobal, null, true, 18, 220);
				 Mecanico mecanico136 = new Mecanico(136, 40, "Héctor Hernández", 'M', new ArrayList<>(), 9, 1750.0, new ArrayList<>(), tallerRapido, null, true, 22, 240);
				 Mecanico mecanico137 = new Mecanico(137, 33, "Eduardo Díaz", 'M', new ArrayList<>(), 6, 1600.0, new ArrayList<>(), tallerEficiente, null, true, 15, 210);
				 Mecanico mecanico138 = new Mecanico(138, 32, "Carlos Moreno", 'M', new ArrayList<>(), 6, 1550.0, new ArrayList<>(), tallerExpress, null, true, 14, 200);
				 Mecanico mecanico139 = new Mecanico(139, 28, "José Pérez", 'M', new ArrayList<>(), 4, 1450.0, new ArrayList<>(), tallerSegura, null, true, 12, 180);
				 Mecanico mecanico140 = new Mecanico(140, 35, "Jorge Moreno", 'M', new ArrayList<>(), 7, 1700.0, new ArrayList<>(), tallerGlobal, null, true, 16, 220);
				 Mecanico mecanico141 = new Mecanico(141, 30, "Luis Martínez", 'M', new ArrayList<>(), 5, 1500.0, new ArrayList<>(), tallerRapido, null, true, 13, 200);
				 Mecanico mecanico142 = new Mecanico(142, 31, "Miguel Gómez", 'M', new ArrayList<>(), 6, 1600.0, new ArrayList<>(), tallerEficiente, null, true, 14, 210);
				 Mecanico mecanico143 = new Mecanico(143, 29, "Andrés Morales", 'M', new ArrayList<>(), 5, 1550.0, new ArrayList<>(), tallerExpress, null, true, 13, 200);
				 Mecanico mecanico144 = new Mecanico(144, 38, "Roberto Fernández", 'M', new ArrayList<>(), 8, 1750.0, new ArrayList<>(), tallerSegura, null, true, 17, 220);
				 Mecanico mecanico145 = new Mecanico(145, 32, "Víctor Vargas", 'M', new ArrayList<>(), 6, 1600.0, new ArrayList<>(), tallerGlobal, null, true, 15, 210);
				 Mecanico mecanico146 = new Mecanico(146, 34, "Fernando Gómez", 'M', new ArrayList<>(), 7, 1650.0, new ArrayList<>(), tallerRapido, null, true, 16, 220);
				 Mecanico mecanico147 = new Mecanico(147, 30, "Héctor Rivas", 'M', new ArrayList<>(), 5, 1500.0, new ArrayList<>(), tallerEficiente, null, true, 12, 200);


				mecanicos1.add(mecanico121);
				mecanicos1.add(mecanico122);
				mecanicos1.add(mecanico123);
				mecanicos2.add(mecanico124);
				mecanicos2.add(mecanico125);
				mecanicos2.add(mecanico126);
				mecanicos3.add(mecanico127);
				mecanicos3.add(mecanico128);
				mecanicos3.add(mecanico129);
				mecanicos4.add(mecanico130);
				mecanicos4.add(mecanico131);
				mecanicos4.add(mecanico132);
				mecanicos5.add(mecanico133);
				mecanicos5.add(mecanico134);
				mecanicos5.add(mecanico135);
				mecanicos6.add(mecanico136);
				mecanicos6.add(mecanico137);
				
				//Viajes
				ArrayList<Viaje> lista = new ArrayList<>();

				lista.add(new Viaje(terminal, "8:0", "2/1/2024", vehiculo1, conductor1, Destino.ANGELOPOLIS, Destino.MEDELLIN));
				lista.add(new Viaje(terminal, "10:0", "2/1/2024", vehiculo2, conductor2, Destino.BARRANQUILLA, Destino.MEDELLIN));
				lista.add(new Viaje(terminal, "12:0", "3/1/2024", vehiculo3, conductor3, Destino.CALI, Destino.MEDELLIN));
				lista.add(new Viaje(terminal, "14:0", "14/1/2024", vehiculo4, conductor4, Destino.BARBOSA, Destino.MEDELLIN));

				lista.add(new Viaje(terminal, "8:30", "4/2/2024", vehiculo11, conductor21, Destino.GUARNE, Destino.MEDELLIN));
				lista.add(new Viaje(terminal, "10:30", "5/2/2024", vehiculo12, conductor22, Destino.MARINILLA, Destino.MEDELLIN));
				lista.add(new Viaje(terminal, "12:30", "7/1/2024", vehiculo14, conductor24, Destino.RIONEGRO, Destino.MEDELLIN));
				lista.add(new Viaje(terminal, "14:30", "18/1/2024", conductor26.getVehiculo(), conductor26, Destino.LAPINTADA, Destino.MEDELLIN));

				lista.add(new Viaje(terminal, "9:0", "1/3/2024", vehiculo21, conductor41, Destino.COOPACABANA, Destino.MEDELLIN));

				lista.add(new Viaje(terminal, "11:0", "1/4/2024", vehiculo31, conductor62, Destino.ANGELOPOLIS, Destino.MEDELLIN));

				lista.add(new Viaje(terminal, "13:0", "1/9/2024", vehiculo41, conductor81, Destino.ANGELOPOLIS, Destino.MEDELLIN));
					
				Terminal.setViajes(lista);
				
				transportadoraRapida.setconductoresRegistrados(conductoresRegistrados1);
				transportadoraEficiente.setconductoresRegistrados(conductoresRegistrados2);
				transportadoraExpress.setconductoresRegistrados(conductoresRegistrados3);
				transportadoraSegura.setconductoresRegistrados(conductoresRegistrados4);
				transportadoraGlobal.setconductoresRegistrados(conductoresRegistrados5);
				transportadoraLocal.setconductoresRegistrados(conductoresRegistrados6);
				
				
				//pasajeros tipo VIP(80)
				new Pasajero(TipoPasajero.VIP, 100001, 34, "Luis Martinez", 'M');
				new Pasajero(TipoPasajero.VIP, 100002, 45, "Ana Gomez", 'F');
				new Pasajero(TipoPasajero.VIP, 100003, 29, "Carlos Perez", 'M');
				new Pasajero(TipoPasajero.VIP, 100004, 53, "Maria Lopez", 'F');
				new Pasajero(TipoPasajero.VIP, 100005, 38, "Juan Fernandez", 'M');
				new Pasajero(TipoPasajero.VIP, 100006, 42, "Laura Sanchez", 'F');
				new Pasajero(TipoPasajero.VIP, 100007, 26, "Andres Ramirez", 'M');
				new Pasajero(TipoPasajero.VIP, 100008, 49, "Carmen Castro", 'F');
				new Pasajero(TipoPasajero.VIP, 100009, 31, "Miguel Torres", 'M');
				new Pasajero(TipoPasajero.VIP, 100010, 39, "Lucia Ortiz", 'F');
				new Pasajero(TipoPasajero.VIP, 100011, 27, "Javier Morales", 'M');
				new Pasajero(TipoPasajero.VIP, 100012, 51, "Claudia Jimenez", 'F');
				new Pasajero(TipoPasajero.VIP, 100013, 33, "Rafael Herrera", 'M');
				new Pasajero(TipoPasajero.VIP, 100014, 44, "Elena Vargas", 'F');
				new Pasajero(TipoPasajero.VIP, 100015, 30, "Oscar Mendoza", 'M');
				new Pasajero(TipoPasajero.VIP, 100016, 47, "Paula Dominguez", 'F');
				new Pasajero(TipoPasajero.VIP, 100017, 28, "Fernando Reyes", 'M');
				new Pasajero(TipoPasajero.VIP, 100018, 53, "Patricia Rojas", 'F');
				new Pasajero(TipoPasajero.VIP, 100019, 40, "David Gil", 'M');
				new Pasajero(TipoPasajero.VIP, 100020, 36, "Isabel Soto", 'F');
				new Pasajero(TipoPasajero.VIP, 100021, 25, "Adrian Campos", 'M');
				new Pasajero(TipoPasajero.VIP, 100022, 48, "Marta Paredes", 'F');
				new Pasajero(TipoPasajero.VIP, 100023, 32, "Sergio Montero", 'M');
				new Pasajero(TipoPasajero.VIP, 100024, 39, "Cristina Leon", 'F');
				new Pasajero(TipoPasajero.VIP, 100025, 35, "Pablo Marin", 'M');
				new Pasajero(TipoPasajero.VIP, 100026, 41, "Susana Peña", 'F');
				new Pasajero(TipoPasajero.VIP, 100027, 29, "Guillermo Silva", 'M');
				new Pasajero(TipoPasajero.VIP, 100028, 50, "Rosa Acosta", 'F');
				new Pasajero(TipoPasajero.VIP, 100029, 37, "Ivan Flores", 'M');
				new Pasajero(TipoPasajero.VIP, 100030, 46, "Lourdes Nunez", 'F');
				new Pasajero(TipoPasajero.VIP, 100031, 34, "Diego Serrano", 'M');
				new Pasajero(TipoPasajero.VIP, 100032, 43, "Alicia Miranda", 'F');
				new Pasajero(TipoPasajero.VIP, 100033, 30, "Victor Cruz", 'M');
				new Pasajero(TipoPasajero.VIP, 100034, 52, "Raquel Gallardo", 'F');
				new Pasajero(TipoPasajero.VIP, 100035, 27, "Hector Bautista", 'M');
				new Pasajero(TipoPasajero.VIP, 100036, 49, "Angela Castillo", 'F');
				new Pasajero(TipoPasajero.VIP, 100037, 38, "Gonzalo Guerrero", 'M');
				new Pasajero(TipoPasajero.VIP, 100038, 40, "Veronica Barrios", 'F');
				new Pasajero(TipoPasajero.VIP, 100039, 36, "Eduardo Santana", 'M');
				new Pasajero(TipoPasajero.VIP, 100040, 45, "Natalia Navarro", 'F');
				new Pasajero(TipoPasajero.VIP, 100041, 28, "Ignacio Cabrera", 'M');
				new Pasajero(TipoPasajero.VIP, 100042, 50, "Noelia Guzman", 'F');
				new Pasajero(TipoPasajero.VIP, 100043, 31, "Francisco Luna", 'M');
				new Pasajero(TipoPasajero.VIP, 100044, 53, "Beatriz Vega", 'F');
				new Pasajero(TipoPasajero.VIP, 100045, 33, "Gabriel Roman", 'M');
				new Pasajero(TipoPasajero.VIP, 100046, 41, "Rocio Cabrera", 'F');
				new Pasajero(TipoPasajero.VIP, 100047, 29, "Sebastian Figueroa", 'M');
				new Pasajero(TipoPasajero.VIP, 100048, 48, "Lorena Muñoz", 'F');
				new Pasajero(TipoPasajero.VIP, 100049, 35, "Manuel Iglesias", 'M');
				new Pasajero(TipoPasajero.VIP, 100050, 44, "Pilar Lozano", 'F');
				new Pasajero(TipoPasajero.VIP, 100051, 26, "Jaime Esteban", 'M');
				new Pasajero(TipoPasajero.VIP, 100052, 47, "Gloria Espinosa", 'F');
				new Pasajero(TipoPasajero.VIP, 100053, 32, "Martin Ruiz", 'M');
				new Pasajero(TipoPasajero.VIP, 100054, 39, "Mercedes Franco", 'F');
				new Pasajero(TipoPasajero.VIP, 100055, 31, "Santiago Valdez", 'M');
				new Pasajero(TipoPasajero.VIP, 100056, 43, "Teresa Ponce", 'F');
				new Pasajero(TipoPasajero.VIP, 100057, 29, "Joaquin Benitez", 'M');
				new Pasajero(TipoPasajero.VIP, 100058, 51, "Elisa Romero", 'F');
				new Pasajero(TipoPasajero.VIP, 100059, 37, "Alberto Santos", 'M');
				new Pasajero(TipoPasajero.VIP, 100060, 46, "Miriam Quintero", 'F');
				new Pasajero(TipoPasajero.VIP, 100061, 34, "Antonio Villalobos", 'M');
				new Pasajero(TipoPasajero.VIP, 100062, 40, "Adela Soto", 'F');
				new Pasajero(TipoPasajero.VIP, 100063, 33, "Jesus Maldonado", 'M');
				new Pasajero(TipoPasajero.VIP, 100064, 38, "Marta Cordova", 'F');
				new Pasajero(TipoPasajero.VIP, 100065, 27, "Raul Ortiz", 'M');
				new Pasajero(TipoPasajero.VIP, 100066, 50, "Dolores Morales", 'F');
				new Pasajero(TipoPasajero.VIP, 100067, 31, "Alfonso Vallejo", 'M');
				new Pasajero(TipoPasajero.VIP, 100068, 42, "Fatima Mendez", 'F');
				new Pasajero(TipoPasajero.VIP, 100069, 29, "Felipe Salazar", 'M');
				new Pasajero(TipoPasajero.VIP, 100070, 47, "Cecilia Montoya", 'F');
				new Pasajero(TipoPasajero.VIP, 100071, 35, "Mario Suarez", 'M');
				new Pasajero(TipoPasajero.VIP, 100072, 44, "Esperanza Zamora", 'F');
				new Pasajero(TipoPasajero.VIP, 100073, 28, "Hugo Prieto", 'M');
				new Pasajero(TipoPasajero.VIP, 100074, 39, "Rosa Aguilar", 'F');
				new Pasajero(TipoPasajero.VIP, 100075, 37, "Jose Moreno", 'M');
				new Pasajero(TipoPasajero.VIP, 100076, 45, "Carmen Navarro", 'F');
				new Pasajero(TipoPasajero.VIP, 100077, 34, "Enrique Cardenas", 'M');
				new Pasajero(TipoPasajero.VIP, 100078, 41, "Lidia Miranda", 'F');
				new Pasajero(TipoPasajero.VIP, 100079, 30, "Ricardo Medina", 'M');
				new Pasajero(TipoPasajero.VIP, 100080, 53, "Nuria Benavides", 'F');
				
				// Estudiante

				new Pasajero(TipoPasajero.ESTUDIANTE, 200005, 22, "Laura Rodríguez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200006, 23, "Javier Fernández", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200007, 24, "Isabel Fernández", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200008, 25, "Pedro Gómez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200009, 26, "Sofía Martínez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200010, 27, "Alejandro Torres", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200011, 28, "Elena Moreno", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200012, 29, "Francisco García", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200013, 30, "Valeria Jiménez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200014, 31, "Óscar Romero", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200015, 32, "Marta Ruiz", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200016, 33, "David Morales", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200017, 34, "Catalina López", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200018, 35, "Andrés Pérez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200019, 36, "Diana Sánchez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200020, 37, "Jorge Ramírez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200021, 38, "Natalia Castro", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200022, 39, "Fernando Cordero", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200023, 40, "Camila Vargas", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200024, 41, "Héctor González", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200025, 42, "Sara Morales", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200026, 43, "Miguel Ángel", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200027, 44, "Lina Moreno", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200028, 45, "Julian Arias", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200029, 46, "Valentina Díaz", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200030, 47, "Daniela Gómez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200031, 48, "Felipe Soto", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200032, 49, "Mariana León", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200033, 50, "Santiago López", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200034, 51, "Paula Jaramillo", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200035, 52, "Alejandra Ruiz", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200036, 53, "Lucas Vargas", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200037, 54, "Gabriela Mendoza", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200038, 55, "Tomás Álvarez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200039, 56, "Laura Cortés", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200040, 57, "Emmanuel Ortega", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200041, 58, "Mónica Gutiérrez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200042, 59, "Mateo Pérez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200043, 60, "Andrea Romero", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200044, 61, "Sebastián López", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200045, 62, "Juliana Castaño", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200046, 63, "Julián Castro", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200047, 64, "Carolina Gómez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200048, 65, "Juan Sebastián", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200049, 66, "Sofía Martínez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200050, 67, "Nicolás Sánchez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200051, 68, "Claudia López", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200052, 69, "Ricardo Hernández", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200053, 70, "Isabela Hernández", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200054, 71, "Javier Ramírez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200055, 72, "Mariana López", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200056, 73, "Felipe Romero", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200057, 74, "Gabriela Martínez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200058, 75, "Esteban Morales", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200059, 76, "Isabella Ruiz", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200060, 77, "Mateo Gómez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200061, 78, "Valentina Vargas", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200062, 79, "Juliana González", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200063, 80, "David Cordero", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200064, 81, "Laura García", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200065, 82, "Santiago Pérez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200066, 83, "Catalina Rivera", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200067, 84, "Felipe Sánchez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200068, 85, "Mónica Fernández", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200070, 87, "Isabela Ramírez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200071, 88, "Gabriel Torres", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200072, 89, "Daniela Castillo", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200073, 90, "Tomás Gómez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200074, 91, "Natalia Torres", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200075, 92, "Julián Morales", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200076, 93, "María Camila", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200077, 94, "Héctor Ramírez", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200078, 95, "Carla López", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200079, 96, "Santiago Morales", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200080, 97, "Valeria Pérez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200081, 98, "Mateo Ruiz", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200082, 99, "Alejandra Martínez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200083, 100, "Juan Pablo", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200084, 101, "Sofía Jiménez", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200085, 102, "Andrés Moreno", 'M');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200086, 103, "Mariana Guerrero", 'F');
				new Pasajero(TipoPasajero.ESTUDIANTE, 200087, 104, "Felipe Vargas", 'M');


				// Pasajero regular

				new Pasajero(TipoPasajero.REGULAR, 300301, 25, "Luis Fernández", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300302, 26, "Valeria Gómez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300303, 27, "Alejandro Pérez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300304, 28, "Catalina Ruiz", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300305, 29, "Ricardo Martínez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300306, 30, "Laura Ramírez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300307, 31, "Jorge López", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300308, 32, "Gabriela Torres", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300309, 33, "Mateo Gómez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300310, 34, "Isabella Morales", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300311, 35, "David Sánchez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300312, 36, "Sofía Fernández", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300313, 37, "Óscar Martínez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300314, 38, "Natalia Pérez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300315, 39, "Juan Sebastián", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300316, 40, "Claudia Gómez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300317, 41, "Felipe Ramírez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300318, 42, "Carolina López", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300319, 43, "Santiago Díaz", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300320, 44, "María José", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300321, 45, "Andrés Martínez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300322, 46, "Gabriela Ramírez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300323, 47, "Juliana González", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300324, 48, "Daniela López", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300325, 49, "Luis Sánchez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300326, 50, "Valeria Martínez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300327, 51, "Óscar Pérez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300328, 52, "Isabella Ramírez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300329, 53, "David Ramírez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300330, 54, "Sofía González", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300331, 55, "Mateo Fernández", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300332, 56, "Laura Gómez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300333, 57, "Jorge Ramírez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300334, 58, "Gabriela López", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300335, 59, "Felipe González", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300336, 60, "Natalia Sánchez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300101, 22, "Juan Gómez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300102, 23, "Sara Martínez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300103, 24, "Andrés López", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300104, 25, "Paola Ramírez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300105, 26, "Felipe Mendoza", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300106, 27, "Ana Torres", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300107, 28, "Santiago González", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300108, 29, "Natalia Álvarez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300109, 30, "David Romero", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300110, 31, "Laura Sánchez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300111, 32, "Carlos Pérez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300112, 33, "Mónica Vargas", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300113, 34, "Javier Martínez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300114, 35, "Camila Fernández", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300115, 36, "Ricardo Ortega", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300116, 37, "Valeria Díaz", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300117, 38, "Mateo Mendoza", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300118, 39, "Gabriela López", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300119, 40, "Juan Pablo", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300120, 41, "Isabella Gómez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300121, 42, "Sergio Romero", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300122, 43, "Diana Torres", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300123, 44, "Alejandro Ruiz", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300124, 45, "Juliana Sánchez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300125, 46, "Luis Ramírez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300126, 47, "Catalina Pérez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300127, 48, "Óscar González", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300128, 49, "Sofía Martínez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300129, 50, "Jorge López", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300130, 51, "Laura Fernández", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300131, 52, "Nicolás Vargas", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300132, 53, "Ana María", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300133, 54, "Héctor Pérez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300134, 55, "Margarita López", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300135, 56, "Tomás Hernández", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300136, 57, "Sandra Gómez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300137, 58, "Felipe Gómez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300138, 59, "Claudia Ramírez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300139, 60, "Emilio Martínez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300140, 61, "Laura Álvarez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300141, 62, "Eduardo González", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300142, 63, "Natalia Pérez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300143, 64, "Ricardo Fernández", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300144, 65, "Gabriela Martínez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300145, 66, "Juan Manuel", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300146, 67, "Sofía Vargas", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300147, 68, "Mateo Fernández", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300148, 69, "Ana Sánchez", 'F');
				new Pasajero(TipoPasajero.REGULAR, 300149, 70, "Felipe Martínez", 'M');
				new Pasajero(TipoPasajero.REGULAR, 300150, 71, "Natalia Gómez", 'F');


				// Pasajeros discapacitados 

				new Pasajero(TipoPasajero.DISCAPACITADO, 400218, 47, "Laura Fernández", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400219, 48, "Óscar Pérez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400220, 49, "Sofía Gómez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400221, 50, "Santiago Ramírez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400222, 51, "María José", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400223, 52, "Felipe Ruiz", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400224, 53, "Diana Morales", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400225, 54, "Mateo Martínez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400226, 55, "Ana Gómez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400227, 56, "Javier González", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400228, 57, "Juliana Ramírez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400229, 58, "Sergio López", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400230, 59, "Carolina Sánchez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400231, 60, "Mateo Rodríguez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400232, 61, "Gabriela Vargas", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400233, 62, "Óscar González", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400234, 63, "Sofía Ramírez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400235, 64, "Sebastián Martínez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400236, 65, "Isabella Morales", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400237, 66, "Ricardo Gómez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400238, 67, "María Fernanda", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400239, 68, "Andrés Díaz", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400240, 69, "Claudia López", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400241, 70, "David Pérez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400242, 71, "Natalia Gómez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400243, 72, "Jorge Ramírez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400244, 73, "Sofía Pérez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400245, 74, "Mateo González", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400246, 75, "Valeria Rodríguez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400247, 76, "Alejandro Ramírez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400248, 77, "Catalina Fernández", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400249, 78, "Carlos Gómez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400250, 79, "Isabella Pérez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400251, 80, "Julián Martínez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400252, 81, "Gabriela Ramírez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400253, 82, "Mateo López", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400254, 83, "Laura Gómez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400255, 84, "Felipe Díaz", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400256, 85, "Diana Sánchez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400257, 86, "Sebastián López", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400258, 87, "Claudia Morales", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400259, 88, "Ricardo Ramírez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400260, 89, "María Paula", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400261, 90, "Andrés González", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400262, 91, "Isabella Gómez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400263, 92, "Sergio Rodríguez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400264, 93, "Gabriela Martínez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400265, 94, "Juan Pablo", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400266, 95, "Valeria Díaz", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400267, 96, "Daniela Gómez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400268, 97, "Santiago López", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400269, 98, "María José", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400270, 99, "Óscar Rodríguez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400271, 100, "Laura Ramírez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400272, 101, "Javier Pérez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400273, 102, "Catalina López", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400274, 103, "Mateo Gómez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400275, 104, "Natalia Ramírez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400276, 105, "Luis Pérez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400277, 106, "Juliana González", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400278, 107, "David Rodríguez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400279, 108, "Gabriela Pérez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400280, 109, "Santiago Martínez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400281, 110, "Isabella Martínez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400282, 111, "Juan Sebastián", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400283, 112, "Carolina Ramírez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400284, 113, "Felipe Gómez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400285, 114, "Laura Fernández", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400286, 115, "Ricardo Gómez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400287, 116, "Mónica Martínez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400288, 117, "Mateo Pérez", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400289, 118, "Sofía Ramírez", 'F');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400290, 119, "Óscar González", 'M');
				new Pasajero(TipoPasajero.DISCAPACITADO, 400291, 120, "Gabriela Ramírez", 'F');
		
	}
	
}

        