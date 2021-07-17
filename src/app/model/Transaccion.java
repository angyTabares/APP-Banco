package app.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// TODO: Auto-generated Javadoc
/**
 * The Class Transaccion.
 */
public class Transaccion {

	/** The cuenta. */
	private Cuenta cuenta;
	
	/** The date. */
	private LocalDate date;
	
	/** The time. */
	private LocalTime time;
	
	/** The servicio. */
	private String servicio;
	
	/** The num cuenta. */
	private int numCuenta;

	/**
	 * Instantiates a new transaccion.
	 *
	 * @param cuenta 
	 * @param date 
	 * @param time 
	 * @param servicio 
	 * @param numCuenta 
	 */
	public Transaccion(Cuenta cuenta, LocalDate date, LocalTime time, String servicio, int numCuenta) {
		super();
		this.cuenta = cuenta;
		this.date = date;
		this.time = time;
		this.servicio = servicio;
		this.numCuenta = numCuenta;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta 
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the num cuenta.
	 *
	 * @return the num cuenta
	 */
	public int getNumCuenta() {
		return numCuenta;
	}

	/**
	 * Sets the num cuenta.
	 *
	 * @param numCuenta 
	 */
	public void setNumCuenta(int numCuenta) {
		this.numCuenta = numCuenta;
	}

	/**
	 * Gets the servicio.
	 *
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * Sets the servicio.
	 *
	 * @param servicio 
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time 
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

}
