package ar.edu.ort.t5.grp1.misgastosdiarios;

public class Comunicador {
	private static Object objeto = null;

	public static void setObjeto(Object newObjeto) {
		objeto = newObjeto;
	}

	public static Object getObjeto() {
		return objeto;
	}
}
