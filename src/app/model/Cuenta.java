package app.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Cuenta.
 */
public class Cuenta {
	
	/** The nombre. */
	private String nombre;
	
	/** The saldo. */
	private double saldo;
	
	/** The bolsillo. */
	private Bolsillo bolsillo;

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param nombre 
	 * @param saldo 
	 */
	public Cuenta(String nombre, double saldo) {
		super();
		this.nombre = nombre;
		this.saldo = saldo;
		bolsillo = null;
	}

	/**
	 * Gets the bolsillo.
	 *
	 * @return the bolsillo
	 */
	public Bolsillo getBolsillo() {
		return bolsillo;
	}

	/**
	 * Sets the bolsillo.
	 *
	 * @param bolsillo 
	 */
	public void setBolsillo(Bolsillo bolsillo) {
		this.bolsillo = bolsillo;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Hay bolsillo.
	 *
	 * @return true, if successful
	 */
	public boolean hayBolsillo() {
		if (bolsillo == null) {
			return false;
		}
		return true;
	}
}
