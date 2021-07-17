package app.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Bolsillo.
 */
public class Bolsillo {

	/** The saldo. */
	private double saldo;
	
	/** The cuenta. */
	private String cuenta;
	
	/**
	 * Instantiates a new bolsillo.
	 *
	 * @param saldo the saldo
	 * @param cuenta 
	 */
	public Bolsillo(double saldo, String cuenta) {
		super();
		this.saldo = saldo;
		this.cuenta = cuenta;
	}
	
	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}
	
	/**
	 * Sets the saldo.
	 *
	 * @param saldo 
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}
	
	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta 
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	
	
}
