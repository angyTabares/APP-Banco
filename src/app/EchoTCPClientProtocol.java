package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class EchoTCPClientProtocol {
	
//	private static final Scanner SCANNER= new Scanner(System.in);
	
	public static PrintWriter toNetwork;
	public static BufferedReader fromNetwork;
	
	public static void protocol(Socket socket) throws IOException
	{
		createStreams(socket);
		mainClient.mostrarMenu();		
	}

	public static void createStreams(Socket socket) throws IOException
	{
		toNetwork = new PrintWriter(socket.getOutputStream(),true);
		fromNetwork= new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	

}
