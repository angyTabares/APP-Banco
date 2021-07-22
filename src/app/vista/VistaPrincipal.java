package app.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import app.cliente.EchoTCPClient;
import app.cliente.EchoTCPClientProtocol;
import app.persistencia.LeerArchivo;
import app.server.EchoTCPServerProtocol;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VistaPrincipal {

	private JFrame frame;

	private static EchoTCPClient ec;
	private static int i = 0;
	private static boolean salir = false;
	public static final int PORT = 3400;
	public static final String SERVER = "localhost";
	private Socket clientSideSocket;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VistaPrincipal window = new VistaPrincipal();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 283, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("App Banco");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(80, 21, 88, 17);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Crear cuenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String nombreCliente = JOptionPane.showInputDialog(null, "Ingrese su nombre");
					if (nombreCliente != null) {
						if (!nombreCliente.isEmpty()) {

							init();
							String fromUser = "ABRIR_CUENTA" + "," + nombreCliente;
							EchoTCPClientProtocol.toNetwork.println(fromUser);

							String fromServer;
							fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer);
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}
					}
				} catch (Exception x) {
					x.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(27, 54, 214, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Crear bolsillo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String numCuenta = JOptionPane.showInputDialog(null, "Ingrese el numero de su cuenta");

					if (numCuenta != null) {
						if (!numCuenta.isEmpty()) {
							init();
							int numCuentaCliente = Integer.parseInt(numCuenta);
							String fromUser2 = "ABRIR_BOLSILLO" + "," + numCuentaCliente;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer2);
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}
					}

				} catch (Exception x) {
					x.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(27, 94, 214, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Cancelar bolsillo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String numBolsilloCliente = JOptionPane.showInputDialog(null, "Ingrese la cuenta bolsillo");

					if (numBolsilloCliente != null) {
						if (!numBolsilloCliente.isEmpty()) {
							init();
							String fromUser2 = "CANCELAR_BOLSILLO" + "," + numBolsilloCliente;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer2);
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}
					}

				} catch (Exception x) {
					x.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(27, 135, 214, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Cancelar cuenta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String numCuenta = JOptionPane.showInputDialog(null, "Ingrese el numero de la cuenta de ahorros");

					if (numCuenta != null) {
						if (!numCuenta.isEmpty()) {
							init();
							int numCuentaCliente = Integer.parseInt(numCuenta);
							String fromUser2 = "CANCELAR_CUENTA" + "," + numCuentaCliente;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer2);
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}
					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(27, 176, 214, 23);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Depositar dinero en una cuenta");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String numCuenta = JOptionPane.showInputDialog(null, "Ingrese el numero de la cuenta de ahorros");
					String ValorC = JOptionPane.showInputDialog(null, "Ingrese el valor a depositar");

					if (numCuenta != null && ValorC != null) {
						if (!numCuenta.isEmpty() && !ValorC.isEmpty()) {

							init();
							int numCuentaCliente = Integer.parseInt(numCuenta);
							double valor = Double.parseDouble(ValorC);

							String fromUser2 = "DEPOSITAR" + "," + numCuentaCliente + "," + valor;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer2);

							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}
					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(27, 215, 214, 23);
		frame.getContentPane().add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Retirar dinero en una cuenta");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String numCuenta = JOptionPane.showInputDialog(null, "Ingrese el numero de la cuenta de ahorros");
					String ValorR = JOptionPane.showInputDialog(null, "Ingrese el valor a retirar");

					if (numCuenta != null && ValorR != null) {
						if (!numCuenta.isEmpty() && !ValorR.isEmpty()) {
							init();
							int numCuentaR = Integer.parseInt(numCuenta);
							double valorR = Double.parseDouble(ValorR);

							String fromUser2 = "RETIRAR" + "," + numCuentaR + "," + valorR;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer2);

							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}

					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}

		});
		btnNewButton_5.setBounds(27, 256, 214, 23);
		frame.getContentPane().add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Trasladar dinero a un bolsillo");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String numCuenta = JOptionPane.showInputDialog(null, "Ingrese el numero de la cuenta de ahorros");
					String ValorT = JOptionPane.showInputDialog(null,
							"Ingrese el valor que desea trasladar al bolsillo");

					if (numCuenta != null && ValorT != null) {
						if (!numCuenta.isEmpty() && !ValorT.isEmpty()) {
							init();
							int numCuentaT = Integer.parseInt(numCuenta);
							double valorT = Double.parseDouble(ValorT);

							String fromUser2 = "TRASLADAR" + "," + numCuentaT + "," + valorT;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							System.out.println();

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer2);

							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}

					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(27, 297, 214, 23);
		frame.getContentPane().add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("Consultar saldo");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String numCuentaCliente = JOptionPane.showInputDialog(null,
							"Ingrese el numero de la cuenta de ahorros");

					if (numCuentaCliente != null) {
						if (!numCuentaCliente.isEmpty()) {
							init();
							String fromUser2 = "CONSULTAR" + "," + numCuentaCliente;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							System.out.println();

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							JOptionPane.showMessageDialog(null, fromServer2);

							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}
					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});

		btnNewButton_7.setBounds(27, 339, 214, 23);
		frame.getContentPane().add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("Cargar datos");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String nombreArchivo = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo a enviar");

					if (nombreArchivo != null) {
						if (!nombreArchivo.isEmpty()) {
							init();
							String fromUser2 = "CARGA" + "," + nombreArchivo;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
							// JOptionPane.showMessageDialog(null, fromServer);

							init();

							String lineas = fromServer.replace('[', ' ');
							lineas = lineas.replace(']', ' ');

							String[] lista = lineas.split("-");

							for (int i = 0; i < lista.length; i++) {

								String cadena = "";
								String cadena2 = "";
								if (i == 0) {
									cadena += lista[i].replaceAll("^\\s*", "") + "\n";
								} else {
									cadena2 = lista[i].replaceFirst(",", "");
									cadena2 = cadena2.replaceAll("^\\s*", "");
									cadena += cadena2 + "\n";
								}

								String fromUser3 = cadena;
								EchoTCPClientProtocol.toNetwork.println(fromUser3);

								String fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
								JOptionPane.showMessageDialog(null, "Transaccion: " + cadena + fromServer2);
								cerrar();

								if (i < lista.length - 2) {

									init();
								}
							}
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion");
						}
					}

				} catch (Exception x) {

				}
			}
		});
		btnNewButton_8.setBounds(27, 383, 214, 23);
		frame.getContentPane().add(btnNewButton_8);

	}

	public void init() throws Exception {
		clientSideSocket = new Socket(SERVER, PORT);
		EchoTCPClientProtocol.createStreams(clientSideSocket);

	}

	public void cerrar() throws Exception {
		clientSideSocket.close();
	}

}
