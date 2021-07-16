package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class EchoTCPServerProtocol {

	public static PrintWriter toNetwork;
	public static BufferedReader fromNetwork;
	private static HashMap <Cuenta, Integer> cuentasBanco = new HashMap <Cuenta, Integer> ();
	private static int i=0;
	private static ArrayList<Transaccion> transacciones= new ArrayList<Transaccion>();
	private static DateTimeFormatter fechaHoraActual = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public static void protocol(Socket socket) throws IOException
	{
		createStreams(socket);
		mainServer.leerOpcionCliente();
		
	}

	private static void createStreams(Socket socket) throws IOException
	{
		toNetwork = new PrintWriter(socket.getOutputStream(),true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public static String abrirCuenta(Cuenta cuenta)
	{
		String mensaje="";
		
          if (!existeNombre(cuenta.getNombre()))
          {
	    	cuentasBanco.put(cuenta, i);
	    	Transaccion transaccion= new Transaccion(cuenta,LocalDate.now(),LocalTime.now(),"ABRIR_CUENTA",cuentasBanco.get(cuenta));
	        transacciones.add(transaccion);
	    	mensaje="La transaccion fue exitosa, su numero de cuenta es: "+ i;
	    	i=i+1;
	      }
          else
          {
        	  mensaje="Error en la transaccion ,el nombre ya existe en el banco";
          }
          return mensaje; 
	}

    public static boolean existeNombre(String nombre)
    {
    	Cuenta cuenta;
    	String nombreC="";
    	
    	 Iterator<Cuenta> cuentas = cuentasBanco.keySet().iterator();
		    while(cuentas.hasNext()){
		    	
		    	cuenta = cuentas.next();
			    nombreC = cuenta.getNombre();
			    
			    if(nombreC.equalsIgnoreCase(nombre))
			    return true;
		    }   
    	
    	return false;
    }
    
    public static String mostrarCuentas()
    {
    	String cadena="";
    	Cuenta cuentaC;
	    Iterator<Cuenta> cuentasC = cuentasBanco.keySet().iterator();
	    while(cuentasC.hasNext()){
	    	
	    	cuentaC = cuentasC.next();
		    cadena += " "+cuentaC.getNombre() 
		                 + "," + cuentasBanco.get(cuentaC)
		                 +","+ cuentaC.getSaldo();
		    
		    if(cuentaC.hayBolsillo())
		    {
		    	cadena +=",Bolsillo["+cuentaC.getBolsillo().getCuenta()+"-"+cuentaC.getBolsillo().getSaldo()+"]";
		    }
	    }     	
      return cadena;
    }
    

    public static String abrirBolsillo(int numeroC)
    {
    	String respuesta="";
    	Cuenta cuentaP;
    	
    	cuentaP= obtenerCuentaP(numeroC);
    	
    	if(!cuentasBanco.isEmpty())
    	{
    	  if(cuentaP!=null) {
    		
    		if(!cuentaP.hayBolsillo())
    		{
    		 Bolsillo bolsillo=new Bolsillo(0,numeroC+"b");
    		 cuentaP.setBolsillo(bolsillo);
    		 respuesta= "La transaccion fue exitosa";
	 	     Transaccion transaccion= new Transaccion(cuentaP,LocalDate.now(),LocalTime.now(),"ABRIR_BOLSILLO",cuentasBanco.get(cuentaP));
	         transacciones.add(transaccion);
    		 
    		}
    		else{
    		 respuesta="No puede crear otro bolsillo";
    		}
    	  }
    	  else{
    		respuesta="La cuenta de ahorros no existe";
    	  }
    	}
    	else{
    		respuesta="No hay cuentas de ahorro";
    	}
    	
    	return respuesta;
    }
    
    
    public static Cuenta obtenerCuentaP(int numCuenta)
    {
    	Cuenta cuentaP=null;
    	int numeroC;
    	boolean encontrado=false;
    	
    	if(!cuentasBanco.isEmpty()) {
    		
	     Iterator<Cuenta> cuentasC = cuentasBanco.keySet().iterator();
	     while(cuentasC.hasNext()){
	    	
	    	cuentaP = cuentasC.next();
	    	numeroC=cuentasBanco.get(cuentaP);
	    	if(numeroC==numCuenta)
	    	{   
	    		encontrado=true;
	    		return cuentaP;
	    	}
	      }
    	}
	    
    	if(encontrado==false)
    	{
    		cuentaP=null;
    	}
    	return cuentaP;
    }
    
    
    public static String cancelarBolsillo(String cuentaB)
    {
		String respuesta = "";
		Cuenta cuentaP;

		cuentaP = obtenerCuentaPDadoBolsillo(cuentaB);

		if (!cuentasBanco.isEmpty()) {
			if (cuentaP != null) {
				if (cuentaP.hayBolsillo()) {
					double saldo = cuentaP.getSaldo() + cuentaP.getBolsillo().getSaldo();
					cuentaP.setSaldo(saldo);
					cuentaP.getBolsillo().setSaldo(0);
					cuentaP.getBolsillo().setCuenta("");
					cuentaP.setBolsillo(null);

					Transaccion transaccion = new Transaccion(cuentaP, LocalDate.now(), LocalTime.now(), "CANCELAR_BOLSILLO",cuentasBanco.get(cuentaP));
					transacciones.add(transaccion);

					respuesta = "La transaccion fue exitosa";

				} else {
					respuesta = "No Existe la cuenta bolsillo en cuenta principal";
				}
			} else {
				respuesta = "La cuenta de ahorros no existe";
			}

		} else {
			respuesta = "No hay cuentas de ahorro";
		}
    	
		return respuesta;
    }
    
    
    public static Cuenta obtenerCuentaPDadoBolsillo(String cuentaB)
    {
    	Cuenta cuentaP=null;
    	int numeroC;
    	boolean encontrado=false;
    	
    	if(!cuentasBanco.isEmpty()) {
    		
	     Iterator<Cuenta> cuentasC = cuentasBanco.keySet().iterator();
	     while(cuentasC.hasNext()){
	    	
	    	cuentaP = cuentasC.next();
	    	numeroC=cuentasBanco.get(cuentaP);
	    	String numCuentaB= numeroC+"b";
	    	if(numCuentaB.equalsIgnoreCase(cuentaB))
	    	{   
	    		encontrado=true;
	    		return cuentaP;
	    	}
	      }
    	}
	    
    	if(encontrado==false)
    	{
    		cuentaP=null;
    	}
    	return cuentaP;
    }
  
    public static String cancelarCuentaAhorros(int cuentaA)
    {
		String respuesta = "";
		Cuenta cuentaP = obtenerCuentaP(cuentaA);

		if (!cuentasBanco.isEmpty()) {
			if (cuentaP != null) {
				if (cuentaP.getSaldo() == 0) {
					if (!cuentaP.hayBolsillo()) {
						if (cuentasBanco.containsValue(cuentaA)) {
							Transaccion transaccion = new Transaccion(cuentaP, LocalDate.now(), LocalTime.now(), "CANCELAR_CUENTA",cuentasBanco.get(cuentaP));
							transacciones.add(transaccion);

							cuentasBanco.remove(cuentaP);
							respuesta = "La Transacción fue exitosa";
						}
					} else {
						respuesta = "La cuenta está asociada a un bolsillo";
					}
				} else {
					respuesta = "El saldo no es suficiente";
				}
			} else {
				respuesta = "La cuenta de ahorros no existe";
			}
		} else {
			respuesta = "No hay cuentas de ahorro";
		}

		return respuesta;
  }
    
  public static String mostrarTransacciones()
  {
	  String cadena="";
	  Transaccion transaccion;
	  Cuenta cuenta;
	  Bolsillo bolsillo;
	  
	  if(transacciones.size()>0)
	  {
	    for(int i=0; i<transacciones.size();i++)
	    {
		   transaccion=transacciones.get(i);
		   
		   if(transaccion.getCuenta()!=null)
		   {
		       cuenta= transaccion.getCuenta();
		       
		       cadena += "Cuenta: "+transaccion.getNumCuenta()+","+cuenta.getNombre()+","+cuenta.getSaldo()+"\n";
		       
		       if(cuenta.hayBolsillo())
		       {
		    	 bolsillo= cuenta.getBolsillo();
		    	 cadena +="Bolsillo: "+bolsillo.getCuenta()+","+bolsillo.getSaldo()+"\n"; 
		       }
		      
		       cadena += transaccion.getServicio()+"," + transaccion.getDate()+ ","+transaccion.getTime()+"\n";
		   }
		         
	    }
	  }
	  return cadena;
  }
    
}
