package ar.edu.ort.t5.grp1.data;

import android.provider.BaseColumns;
import ar.edu.ort.t5.grp1.data.GastoContract.GastoEntry;

public class CategoriaContract {
	private CategoriaContract() {
	}

	/* Inner class that defines the table contents */
	public static class CategoriaEntry implements BaseColumns {
		protected static final String TABLE_NAME = "categoria";
		protected static final String COLUMN_NAME_DESCRIPCION = "descripcion";
		protected static final String SQL_CREATE = "CREATE TABLE " + CategoriaEntry.TABLE_NAME + " ( " + GastoEntry._ID
				+ " INTEGER PRIMARY KEY," + CategoriaEntry.COLUMN_NAME_DESCRIPCION + " TEXT " + " )";
		protected static final String SQL_DELETE = "DROP TABLE IF EXISTS " + CategoriaEntry.TABLE_NAME;
		
		protected static final String[] COLUMNS = { _ID, COLUMN_NAME_DESCRIPCION };
	}
}
