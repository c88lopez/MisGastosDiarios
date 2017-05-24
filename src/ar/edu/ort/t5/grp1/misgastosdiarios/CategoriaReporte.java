package ar.edu.ort.t5.grp1.misgastosdiarios;

public class CategoriaReporte {
	private Categoria categoria;
	private float importe;
	private float porcentaje;
	
	public CategoriaReporte(Categoria categoria, float importe, float porcentaje) {
		super();
		this.categoria = categoria;
		this.importe = importe;
		this.porcentaje = porcentaje;
	}

	public float getImporte() {
		return importe;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public String getDescripcion() {
		return categoria.getDescripcion();
	}

	
	

}
