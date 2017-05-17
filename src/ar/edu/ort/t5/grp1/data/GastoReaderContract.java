package ar.edu.ort.t5.grp1.data;
import android.provider.BaseColumns;

public class GastoReaderContract {
	private GastoReaderContract() {
	}

	/* Inner class that defines the table contents */
	public static class GastoEntry implements BaseColumns {
		public static final String TABLE_NAME = "gasto";
		public static final String COLUMN_NAME_DETALLE = "detalle";
		public static final String COLUMN_NAME_DATE = "subtitle";
		public static final String COLUMN_NAME_IMPORTE = "importe";
		public static final String COLUMN_NAME_CATEGORIA_ID = "categoria_id";
	}

}
