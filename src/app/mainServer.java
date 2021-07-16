package app;

import java.io.IOException;

import javax.swing.JOptionPane;

public class mainServer {
	
	private static EchoTCPServer es;
	public static void main(String[] args) {
		
		try {
		    es= new EchoTCPServer();
			es.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


public static void leerOpcionCliente()
{
	String message="";
	
	try {
		message = EchoTCPServerProtocol.fromNetwork.readLine();
		System.out.println("[Server] From client: "+ message);
		
		if(!message.equalsIgnoreCase("cinco"))
		{
		
		String[] ArrayDatos= message.split(",");
	
		String opcion=ArrayDatos[0];
		
		switch(opcion)
		{
		   case "ABRIR_CUENTA": 
			   					Cuenta cuenta= new Cuenta(ArrayDatos[1],0);
			                    String respuesta=EchoTCPServerProtocol.abrirCuenta(cuenta);
			                    EchoTCPServerProtocol.toNetwork.println(respuesta);
			                    
			                //    String cuentas = EchoTCPServerProtocol.mostrarCuentas();
			                //    System.out.println(cuentas);
			                    break;
			                    
		   case "ABRIR_BOLSILLO":
			   				    int numCuenta=Integer.parseInt(ArrayDatos[1]);
			   					respuesta=EchoTCPServerProtocol.abrirBolsillo(numCuenta);
			   					EchoTCPServerProtocol.toNetwork.println(respuesta);
			                 //   String cuentas2 = EchoTCPServerProtocol.mostrarCuentas();
			                 //   System.out.println(cuentas2);
			           
			   					break;
			   					
		   case "CANCELAR_BOLSILLO":
				                String cuentaB=ArrayDatos[1];
					            respuesta=EchoTCPServerProtocol.cancelarBolsillo(cuentaB);
					            EchoTCPServerProtocol.toNetwork.println(respuesta);
			                  //  String cuentas3 = EchoTCPServerProtocol.mostrarCuentas();
			                  //  System.out.println(cuentas3);

					            break;

		   case "CANCELAR_CUENTA":
				                int cuentaA = Integer.parseInt(ArrayDatos[1]);
				                respuesta = EchoTCPServerProtocol.cancelarCuentaAhorros(cuentaA);
				                EchoTCPServerProtocol.toNetwork.println(respuesta);
				              //  String cuentasA = EchoTCPServerProtocol.mostrarCuentas();
				              //  System.out.println(cuentasA);
				                break;
		}
		
		
		System.out.println("Transacciones: "+"\n"+EchoTCPServerProtocol.mostrarTransacciones());
	 }
		else
		{
			try
			{
			  es.cerrarSocket();
			}catch(Exception e)
			{
				System.out.println("socket cerrado");
			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}



