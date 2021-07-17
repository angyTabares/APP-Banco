package app.server;

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
import app.model.Bolsillo;
import app.model.Cuenta;
import app.model.Transaccion;

/**
 * The Class EchoTCPServerProtocol.
 */
public class EchoTCPServerProtocol {

	/** The to network. */
	public static PrintWriter toNetwork;

	/** The from network. */
	public static BufferedReader fromNetwork;

	/** The cuentas banco. */
	private static HashMap<Cuenta, Integer> cuentasBanco = new HashMap<Cuenta, Integer>();

	/** The i. */
	private static int i = 0;

	/** The transacciones. */
	private static ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();

	/** The fecha hora actual. */
	private static DateTimeFormatter fechaHoraActual = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	/**
	 * Protocol.
	 *
	 * @param socket the socket
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void protocol(Socket socket) throws IOException {
		createStreams(socket);
		mainServer.leerOpcionCliente();

	}

	/**
	 * Creates the streams.
	 *
	 * @param socket the socket
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void createStreams(Socket socket) throws IOException {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * Abrir cuenta.
	 *
	 * @param cuenta
	 * @return mensaje
	 */
	public static String abrirCuenta(Cuenta cuenta) {
		String mensaje = "";

		if (!existeNombre(cuenta.getNombre())) {
			cuentasBanco.put(cuenta, i);
			Transaccion transaccion = new Transaccion(cuenta, LocalDate.now(), LocalTime.now(), "ABRIR_CUENTA",
					cuentasBanco.get(cuenta));
			transacciones.add(transaccion);
			mensaje = "La transaccion fue exitosa, su numero de cuenta es: " + i;
			i = i + 1;
		} else {
			mensaje = "Error en la transaccion ,el nombre ya existe en el banco";
		}
		return mensaje;
	}

	/**
	 * Existe nombre.
	 *
	 * @param nombre
	 * @return true, if successful
	 */
	public static boolean existeNombre(String nombre) {
		Cuenta cuenta;
		String nombreC = "";

		Iterator<Cuenta> cuentas = cuentasBanco.keySet().iterator();
		while (cuentas.hasNext()) {

			cuenta = cuentas.next();
			nombreC = cuenta.getNombre();

			if (nombreC.equalsIgnoreCase(nombre))
				return true;
		}

		return false;
	}

	/**
	 * Mostrar cuentas.
	 *
	 * @return the string
	 */
	public static String mostrarCuentas() {
		String cadena = "";
		Cuenta cuentaC;
		Iterator<Cuenta> cuentasC = cuentasBanco.keySet().iterator();
		while (cuentasC.hasNext()) {

			cuentaC = cuentasC.next();
			cadena += " " + cuentaC.getNombre() + "," + cuentasBanco.get(cuentaC) + "," + cuentaC.getSaldo();

			if (cuentaC.hayBolsillo()) {
				cadena += ",Bolsillo[" + cuentaC.getBolsillo().getCuenta() + "-" + cuentaC.getBolsillo().getSaldo()
						+ "]";
			}
		}
		return cadena;
	}

	/**
	 * Abrir bolsillo.
	 *
	 * @param numeroC
	 * @return respuesta
	 */
	public static String abrirBolsillo(int numeroC) {
		String respuesta = "";
		Cuenta cuentaP;

		cuentaP = obtenerCuentaP(numeroC);

		if (!cuentasBanco.isEmpty()) {
			if (cuentaP != null) {

				if (!cuentaP.hayBolsillo()) {
					Bolsillo bolsillo = new Bolsillo(0, numeroC + "b");
					cuentaP.setBolsillo(bolsillo);
					respuesta = "La transaccion fue exitosa";
					Transaccion transaccion = new Transaccion(cuentaP, LocalDate.now(), LocalTime.now(),
							"ABRIR_BOLSILLO", cuentasBanco.get(cuentaP));
					transacciones.add(transaccion);

				} else {
					respuesta = "No puede crear otro bolsillo";
				}
			} else {
				respuesta = "La cuenta de ahorros no existe";
			}
		} else {
			respuesta = "No hay cuentas de ahorro";
		}

		return respuesta;
	}

	/**
	 * Obtener cuenta P.
	 *
	 * @param numCuenta
	 * @return cuentaP
	 */
	public static Cuenta obtenerCuentaP(int numCuenta) {
		Cuenta cuentaP = null;
		int numeroC;
		boolean encontrado = false;

		if (!cuentasBanco.isEmpty()) {

			Iterator<Cuenta> cuentasC = cuentasBanco.keySet().iterator();
			while (cuentasC.hasNext()) {

				cuentaP = cuentasC.next();
				numeroC = cuentasBanco.get(cuentaP);
				if (numeroC == numCuenta) {
					encontrado = true;
					return cuentaP;
				}
			}
		}

		if (encontrado == false) {
			cuentaP = null;
		}
		return cuentaP;
	}

	/**
	 * Cancelar bolsillo.
	 *
	 * @param cuentaB
	 * @return cuentaP
	 */
	public static String cancelarBolsillo(String cuentaB) {
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

					Transaccion transaccion = new Transaccion(cuentaP, LocalDate.now(), LocalTime.now(),
							"CANCELAR_BOLSILLO", cuentasBanco.get(cuentaP));
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

	/**
	 * Obtener cuenta P dado bolsillo.
	 *
	 * @param cuentaB
	 * @return cuentaP
	 */
	public static Cuenta obtenerCuentaPDadoBolsillo(String cuentaB) {
		Cuenta cuentaP = null;
		int numeroC;
		boolean encontrado = false;

		if (!cuentasBanco.isEmpty()) {

			Iterator<Cuenta> cuentasC = cuentasBanco.keySet().iterator();
			while (cuentasC.hasNext()) {

				cuentaP = cuentasC.next();
				numeroC = cuentasBanco.get(cuentaP);
				String numCuentaB = numeroC + "b";
				if (numCuentaB.equalsIgnoreCase(cuentaB)) {
					encontrado = true;
					return cuentaP;
				}
			}
		}

		if (encontrado == false) {
			cuentaP = null;
		}
		return cuentaP;
	}

	/**
	 * Cancelar cuenta ahorros.
	 *
	 * @param cuentaA
	 * @return respuesta
	 */
	public static String cancelarCuentaAhorros(int cuentaA) {
		String respuesta = "";
		Cuenta cuentaP = obtenerCuentaP(cuentaA);

		if (!cuentasBanco.isEmpty()) {
			if (cuentaP != null) {
				if (cuentaP.getSaldo() == 0) {
					if (!cuentaP.hayBolsillo()) {
						if (cuentasBanco.containsValue(cuentaA)) {
							Transaccion transaccion = new Transaccion(cuentaP, LocalDate.now(), LocalTime.now(),
									"CANCELAR_CUENTA", cuentasBanco.get(cuentaP));
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

	/**
	 * Mostrar transacciones.
	 * 
	 * @return cadena
	 */
	public static String mostrarTransacciones() {
		String cadena = "";
		Transaccion transaccion;
		Cuenta cuenta;
		Bolsillo bolsillo;

		if (transacciones.size() > 0) {
			for (int i = 0; i < transacciones.size(); i++) {
				transaccion = transacciones.get(i);

				if (transaccion.getCuenta() != null) {
					cuenta = transaccion.getCuenta();

					cadena += "Cuenta: " + transaccion.getNumCuenta() + "," + cuenta.getNombre() + ","
							+ cuenta.getSaldo() + "\n";

					if (cuenta.hayBolsillo()) {
						bolsillo = cuenta.getBolsillo();
						cadena += "Bolsillo: " + bolsillo.getCuenta() + "," + bolsillo.getSaldo() + "\n";
					}

					cadena += transaccion.getServicio() + "," + transaccion.getDate() + "," + transaccion.getTime()
							+ "\n";
				}

			}
		}
		return cadena;
	}

	/**
	 * Depositar dinero. Deposita dinero en una cuenta ingresada
	 * 
	 * @param numCuenta
	 * @param valor
	 * @return cadena
	 */
	public static String depositarDinero(int numCuenta, double valor) {

		String cadena = "";
		Cuenta cuenta = null;

		cuenta = obtenerCuentaP(numCuenta);

		if (cuenta != null) {

			double saldo = cuenta.getSaldo() + valor;
			cuenta.setSaldo(saldo);

			cadena = "Transaccion existosa";
		} else {

			cadena = "Error en la transaccion, la cuenta no existe";
		}

		return cadena;
	}

	/**
	 * Retirar dinero. Retira el dinero de una cuenta ingresada
	 * 
	 * @param numCuenta
	 * @param valor
	 * @return cadena
	 */
	public static String retirarDinero(int numCuenta, double valor) {

		String cadena = "";
		Cuenta cuenta = null;

		cuenta = obtenerCuentaP(numCuenta);

		if (cuenta != null) {

			double saldo = cuenta.getSaldo();
			if (saldo > valor) {

				double nuevoSaldo = saldo - valor;
				cuenta.setSaldo(nuevoSaldo);
				cadena = " Retiro existoso ";

			} else {
				cadena = "No se realizo el retiro, saldo insuficiente ";
			}
		} else {
			cadena = "Error, la cuenta no existe";
		}

		return cadena;
	}

	/**
	 * Trasladar dinero bolsillo. Traslada el dinero de una cuenta existente al
	 * bolsillo de dicha cuenta
	 * 
	 * @param numCuenta
	 * @param valor
	 * @return cadena
	 */
	public static String trasladarDineroBolsillo(int numCuenta, double valor) {

		String cadena = "";
		Cuenta cuenta = null;

		cuenta = obtenerCuentaP(numCuenta);

		if (cuenta != null) {
			if (cuenta.getBolsillo() != null) {

				double saldo = cuenta.getSaldo();

				if (saldo > valor) {

					double nuevoSaldo = saldo - valor;
					cuenta.setSaldo(nuevoSaldo);
					cuenta.getBolsillo().setSaldo(valor);
					cadena = "Traslado existoso\n Saldo cuenta:" + nuevoSaldo + "Saldo bolsillo:" + valor;

				} else {
					cadena = "Salfo insuficiente ";
				}
			} else {
				cadena = "La cuenta no tiene creado un bolsillo";
			}

		} else {
			cadena = "Error, La cuenta no existe";
		}

		return cadena;
	}

	/**
	 * Consultar saldo C. Consulta un saldo a partir de un numero de cuenta
	 * 
	 * @param numCuenta
	 * @return cadena
	 */
	public static String consultarSaldoC(int numCuenta) {

		String cadena = "";
		Cuenta cuenta = obtenerCuentaP(numCuenta);

		if (cuenta != null) {
			double saldoCuenta = cuenta.getSaldo();
			double saldoBolsillo = cuenta.getBolsillo().getSaldo();

			cadena = "El saldo de la cuenta es de: " + saldoCuenta + "  El saldo del bolsillo es de: S" + saldoBolsillo;
		}

		return cadena;
	}

	/**
	 * Consultar saldo B. Consulta un saldo a partir del numero de bolsillo
	 * 
	 * @param bolsillo
	 * @return cadena
	 */
	public static String consultarSaldoB(String bolsillo) {

		String cadena = "";
		Cuenta cuenta = obtenerCuentaPDadoBolsillo(bolsillo);

		if (cuenta != null) {
			double saldoCuenta = cuenta.getSaldo();
			double saldoBolsillo = cuenta.getBolsillo().getSaldo();

			cadena = "El saldo de la cuenta es de: " + saldoCuenta + " El saldo del bolsillo es de: " + saldoBolsillo;
		}

		return cadena;
	}

	public static String consultarSaldo(String cuenta) {

		String cadena = "";

		if (cuenta.contains("b")) {
			cadena = consultarSaldoB(cuenta);
		} else {

			cadena = consultarSaldoC(Integer.parseInt(cuenta));
		}

		return cadena;
	}
}