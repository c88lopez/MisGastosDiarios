package ar.edu.ort.t5.grp1.misgastosdiarios;

public class Categoria {
	private long id = -1;
	private String descripcion;
	
	public Categoria(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Categoria(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.descripcion;
	}
	
	

}
