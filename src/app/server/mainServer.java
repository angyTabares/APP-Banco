package app.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.cliente.EchoTCPClient;
import app.cliente.EchoTCPClientProtocol;
import app.model.Cuenta;
import app.persistencia.*;
import app.cliente.*;

public class mainServer {

	private static EchoTCPServer es;

	public static void main(String[] args) {

		try {
			es = new EchoTCPServer();
			es.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void leerOpcionCliente() {
		String message = "";

		try {
			message = EchoTCPServerProtocol.fromNetwork.readLine();
			System.out.println("[Server] From client: " + message);

			if (!message.equalsIgnoreCase("diez")) {

				String[] ArrayDatos = message.split(",");

				String opcion = ArrayDatos[0];
				System.out.println(opcion);
				switch (opcion) {
				case "ABRIR_CUENTA":

					Cuenta cuenta = new Cuenta(ArrayDatos[1], 0);
					String respuesta = EchoTCPServerProtocol.abrirCuenta(cuenta);
					EchoTCPServerProtocol.toNetwork.println(respuesta);

					// String cuentas = EchoTCPServerProtocol.mostrarCuentas();
					// System.out.println(cuentas);
					break;

				case "ABRIR_BOLSILLO":
					int numCuenta = Integer.parseInt(ArrayDatos[1]);
					respuesta = EchoTCPServerProtocol.abrirBolsillo(numCuenta);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					// String cuentas2 = EchoTCPServerProtocol.mostrarCuentas();
					// System.out.println(cuentas2);

					break;

				case "CANCELAR_BOLSILLO":
					String cuentaB = ArrayDatos[1];
					respuesta = EchoTCPServerProtocol.cancelarBolsillo(cuentaB);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					// String cuentas3 = EchoTCPServerProtocol.mostrarCuentas();
					// System.out.println(cuentas3);

					break;

				case "CANCELAR_CUENTA":
					int cuentaA = Integer.parseInt(ArrayDatos[1]);
					respuesta = EchoTCPServerProtocol.cancelarCuentaAhorros(cuentaA);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					// String cuentasA = EchoTCPServerProtocol.mostrarCuentas();
					// System.out.println(cuentasA);
					break;

				case "DEPOSITAR":
					int numeroCuenta = Integer.parseInt(ArrayDatos[1]);
					double valor = Double.parseDouble(ArrayDatos[2]);
					String respuesta5 = EchoTCPServerProtocol.depositarDinero(numeroCuenta, valor);
					EchoTCPServerProtocol.toNetwork.println(respuesta5);
					break;

				case "RETIRAR":
					int numeroCuentaR = Integer.parseInt(ArrayDatos[1]);
					double valorR = Double.parseDouble(ArrayDatos[2]);
					String respuesta6 = EchoTCPServerProtocol.retirarDinero(numeroCuentaR, valorR);
					EchoTCPServerProtocol.toNetwork.println(respuesta6);
					break;

				case "TRASLADAR":
					int numeroCuentaT = Integer.parseInt(ArrayDatos[1]);
					double valorT = Double.parseDouble(ArrayDatos[2]);
					String respuesta7 = EchoTCPServerProtocol.trasladarDineroBolsillo(numeroCuentaT, valorT);
					EchoTCPServerProtocol.toNetwork.println(respuesta7);
					break;

				case "CONSULTAR":

					String cuentaC = ArrayDatos[1];
					String respuesta8 = EchoTCPServerProtocol.consultarSaldo(cuentaC);
					EchoTCPServerProtocol.toNetwork.println(respuesta8);
					break;

				case "CARGA":

					try {

						String nombreArchivo = ArrayDatos[1];
						ArrayList<String> lineas = LeerArchivo.leerArchivo(nombreArchivo);
						EchoTCPServerProtocol.toNetwork.println(lineas);
					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				}

				System.out.println("Transacciones: " + "\n" + EchoTCPServerProtocol.mostrarTransacciones());
			} else {
				try {
					es.cerrarSocket();
				} catch (Exception e) {
					System.out.println("socket cerrado");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
