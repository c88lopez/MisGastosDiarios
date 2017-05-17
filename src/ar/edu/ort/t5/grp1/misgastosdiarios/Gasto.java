package ar.edu.ort.t5.grp1.misgastosdiarios;

import java.util.Date;

public class Gasto {
	private int id;
	private Categoria categoria;
	private float importe;
	private String detalle;
	private Date fecha;
	
	public Gasto(int id, Categoria categoria, float importe, String detalle, Date fecha) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.importe = importe;
		this.detalle = detalle;
		this.fecha = fecha;
	}
	
	
	public Gasto(Categoria categoria, float importe, String detalle, Date fecha) {
		super();
		this.categoria = categoria;
		this.importe = importe;
		this.detalle = detalle;
		this.fecha = fecha;
	}
	
	public Gasto(int id, Categoria categoria, float importe, String detalle) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.importe = importe;
		this.detalle = detalle;
		this.fecha = new Date();
	}


	public Gasto(Categoria categoria, float importe, String detalle) {
		super();
		this.categoria = categoria;
		this.importe = importe;
		this.detalle = detalle;
		this.fecha = new Date();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
