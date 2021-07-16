package app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {

	private Cuenta cuenta;
	private LocalDate date ;
	private LocalTime time ;
	private String servicio;
	private int numCuenta;
	
	
	public Transaccion(Cuenta cuenta, LocalDate date, LocalTime time, String servicio,int numCuenta) {
		super();
		this.cuenta = cuenta;
		this.date = date;
		this.time = time;
		this.servicio = servicio;
		this.numCuenta=numCuenta;
	}


	public Cuenta getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}


	public int getNumCuenta() {
		return numCuenta;
	}


	public void setNumCuenta(int numCuenta) {
		this.numCuenta = numCuenta;
	}


	public String getServicio() {
		return servicio;
	}


	public void setServicio(String servicio) {
		this.servicio = servicio;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
	
}
