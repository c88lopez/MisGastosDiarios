package ar.edu.ort.t5.grp1.misgastosdiarios;

public class Reporte {
	private Categoria categoria;
	private float importe;
	private float porcentaje;
		
	public Reporte(Categoria categoria, float importe, float porcentaje) {
		super();
		this.categoria = categoria;
		this.importe = importe;
		this.porcentaje = porcentaje;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public float getImporte() {
		return importe;
	}
	public float getPorcentaje() {
		return porcentaje;
	}
	
	
}
