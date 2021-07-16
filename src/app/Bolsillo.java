package app;

public class Bolsillo {

	private double saldo;
	private String cuenta;
	
	public Bolsillo(double saldo, String cuenta) {
		super();
		this.saldo = saldo;
		this.cuenta = cuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	
	
}
