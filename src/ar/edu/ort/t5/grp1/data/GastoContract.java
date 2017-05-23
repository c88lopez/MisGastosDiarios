package ar.edu.ort.t5.grp1.data;
import android.provider.BaseColumns;

public class GastoContract {
	private GastoContract() {
	}

	/* Inner class that defines the table contents */
	public static class GastoEntry implements BaseColumns {
		protected static final String TABLE_NAME = "gasto";
		protected static final String COLUMN_NAME_DESCRIPCION = "detalle";
		protected static final String COLUMN_NAME_DATE = "fecha";
		protected static final String COLUMN_NAME_IMPORTE = "importe";
		protected static final String COLUMN_NAME_CATEGORIA_ID = "categoria_id";
		
		protected static final String SQL_CREATE = "CREATE TABLE " + GastoEntry.TABLE_NAME + " (" + GastoEntry._ID
				+ " INTEGER PRIMARY KEY," + GastoEntry.COLUMN_NAME_DESCRIPCION + " TEXT," + GastoEntry.COLUMN_NAME_DATE
				+ " TEXT," + GastoEntry.COLUMN_NAME_IMPORTE + " FLOAT," + GastoEntry.COLUMN_NAME_CATEGORIA_ID
				+ " INTEGER" + ")";
		protected static final String SQL_DELETE = "DROP TABLE IF EXISTS " + GastoEntry.TABLE_NAME;
		
		protected static final String[] COLUMNS = { _ID, COLUMN_NAME_DESCRIPCION, COLUMN_NAME_DATE, COLUMN_NAME_IMPORTE, COLUMN_NAME_CATEGORIA_ID };
	}

}
