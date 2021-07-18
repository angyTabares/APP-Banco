package app.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import app.persistencia.LeerArchivo;

public class mainClient {

	private static EchoTCPClient ec;
	private static int i = 0;
	private static boolean salir = false;

	public static void main(String[] args) {
		ec = new EchoTCPClient();
		i++;

		try {
			ec.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void mostrarMenu() {
		Scanner sn = new Scanner(System.in);

		int opcion; // Guardaremos la opcion del usuario

		while (!salir) {
			System.out.println();
			System.out.println("Servicios del banco:");
			System.out.println("1.Abrir una cuenta");
			System.out.println("2.Crear un bolsillo");
			System.out.println("3.Cancelar bolsillo");
			System.out.println("4.Cancelar cuenta de ahorros");
			System.out.println("5.Depositar dinero en una cuenta");
			System.out.println("6.Retirar dinero en una cuenta");
			System.out.println("7.Trasladar dinero a un bolsillo");
			System.out.println("8.Consultar saldo");
			System.out.println("9.Cargar datos");
			System.out.println("10.Salir");
			// System.out.println("3. Opcion 3");
			// System.out.println("4. Salir");

			try {

				System.out.println("Escribe una de las opciones");
				opcion = sn.nextInt();
				sn.nextLine();

				switch (opcion) {
				case 1:
					try {
						System.out.println("Ingrese su nombre: ");
						String nombre = sn.nextLine();

						String fromUser = "ABRIR_CUENTA" + "," + nombre;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();
						String fromServer;
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer);
						reiniciarConexion();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case 2:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuenta = sn.nextInt();
						sn.nextLine();

						String fromUser2 = "ABRIR_BOLSILLO" + "," + numCuenta;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer2);

						reiniciarConexion();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case 3:

					try {
						System.out.println("Ingrese la cuenta bolsillo: ");
						String cuentaB = sn.nextLine();

						String fromUser2 = "CANCELAR_BOLSILLO" + "," + cuentaB;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer2);

						reiniciarConexion();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case 4:

					try {
						System.out.println("Ingrese el numero de la cuenta de ahorros: ");
						int numCuentaA = sn.nextInt();
						sn.nextLine();

						String fromUser2 = "CANCELAR_CUENTA" + "," + numCuentaA;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer2);

						reiniciarConexion();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case 5:

					try {
						System.out.println("Ingrese el numero de la cuenta de ahorros: ");
						int numCuenta = sn.nextInt();
						sn.nextLine();

						System.out.println("Ingrese el valor a depositar: ");
						double valor = sn.nextDouble();

						String fromUser2 = "DEPOSITAR" + "," + numCuenta + "," + valor;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer2);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
				case 6:

					try {
						System.out.println("Ingrese el numero de la cuenta de ahorros: ");
						int numCuentaR = sn.nextInt();
						sn.nextLine();

						System.out.println("Ingrese el valor a retirar: ");
						double valorR = sn.nextDouble();

						String fromUser2 = "RETIRAR" + "," + numCuentaR + "," + valorR;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer2);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				case 7:

					try {
						System.out.println("Ingrese el numero de la cuenta de ahorros: ");
						int numCuentaT = sn.nextInt();
						sn.nextLine();

						System.out.println("Ingrese el valor que desea trasladar al bolsillo: ");
						double valorT = sn.nextDouble();

						String fromUser2 = "TRASLADAR" + "," + numCuentaT + "," + valorT;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer2);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				case 8:

					try {
						System.out.println("Ingrese el numero de cuenta: ");
						String cuenta = sn.nextLine();

						String fromUser2 = "CONSULTAR" + "," + cuenta;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println("[Client] From server: " + fromServer2);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					break;
					
				/*case 9:

					try {

						ArrayList<String> lineas = LeerArchivo.leerArchivo("src\\app\\transacciones");

						for (String linea : lineas) {

							System.out.println(linea);
							EchoTCPClientProtocol.toNetwork.println(linea);

							System.out.println();

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							System.out.println("[Client] From server: " + fromServer2);
							reiniciarConexion();

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;*/
				case 10:

					salir = true;
					EchoTCPClientProtocol.toNetwork.println("diez");

					try {
						ec.cerrarSocket();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Solo n�meros entre 1 y 10");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un n�mero");
				sn.next();
			}
		}

	}

	public static void reiniciarConexion() {
		if (!salir) {
			try {
				ec.init();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}