package ar.edu.ort.t5.grp1.data;

import android.provider.BaseColumns;

public class CategoriaReaderContract {
	  private CategoriaReaderContract() {}

	    /* Inner class that defines the table contents */
		public static class CategoriaEntry implements BaseColumns {
		    public static final String TABLE_NAME = "categoria";
		    public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
		}
}
