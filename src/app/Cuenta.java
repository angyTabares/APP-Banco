package app;

public class Cuenta {
    private String nombre;
    private double saldo;
    private Bolsillo bolsillo;
    
	public Cuenta(String nombre,double saldo) {
		super();
		this.nombre = nombre;
		this.saldo = saldo;
		bolsillo=null;
	}
	
	public Bolsillo getBolsillo() {
		return bolsillo;
	}
	public void setBolsillo(Bolsillo bolsillo) {
		this.bolsillo = bolsillo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
    
	public boolean hayBolsillo()
	{
		if(bolsillo==null)
		{
		return false;
		}
		return true;
	}
}
