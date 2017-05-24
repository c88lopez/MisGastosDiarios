package ar.edu.ort.t5.grp1.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ar.edu.ort.t5.grp1.data.GastoContract.GastoEntry;
import ar.edu.ort.t5.grp1.misgastosdiarios.Categoria;
import ar.edu.ort.t5.grp1.misgastosdiarios.Gasto;

@SuppressLint("SimpleDateFormat")
public class GastoData {
	static final DateFormat dateformatter = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SQLiteDatabaseHandler handler;
	private static SQLiteDatabase db;

	public GastoData(Context context) {
		handler = SQLiteDatabaseHandler.getInstance(context);
		db = handler.getDb();
	}

	protected void finalize() throws Throwable {
		if (db.isOpen())
			db.close();
		super.finalize();
	}

	private static Gasto getGasto(Cursor cursor) throws IllegalArgumentException, ParseException {
		return new Gasto(cursor.getInt(cursor.getColumnIndexOrThrow(GastoEntry._ID)),
				CategoriaData.get(cursor.getInt((cursor.getColumnIndexOrThrow(GastoEntry.COLUMN_NAME_CATEGORIA_ID)))),
				cursor.getFloat(cursor.getColumnIndexOrThrow(GastoEntry.COLUMN_NAME_IMPORTE)),
				cursor.getString(cursor.getColumnIndexOrThrow(GastoEntry.COLUMN_NAME_DESCRIPCION)),
				dateformatter.parse(cursor.getString(cursor.getColumnIndexOrThrow(GastoEntry.COLUMN_NAME_DATE))));
	}

	public static Gasto getGasto(int id) throws IllegalArgumentException, ParseException {
		Gasto gasto = null;
		SQLiteDatabase db = handler.getDb();
		Cursor cursor = db.query(GastoEntry.TABLE_NAME, // a. table
				GastoEntry.COLUMNS, // b. column names
				" id = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (cursor != null) {
			cursor.moveToFirst();
			gasto = getGasto(cursor);
		}
		db.close();
		return gasto;
	}

	public List<Gasto> getGastos() throws IllegalArgumentException, ParseException {

		List<Gasto> gastos = new LinkedList<Gasto>();
		SQLiteDatabase db = handler.getDb();
		// Cursor cursor = db.rawQuery(query, null);

		Cursor cursor = db.query(GastoEntry.TABLE_NAME, // The table to query
				GastoEntry.COLUMNS, // The columns to return
				null,
				// GastoEntry.COLUMN_NAME_DATE + " = ?", // The columns for the
				// WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // sortOrder // The sort order
		);

		while (cursor.moveToNext()) {
			gastos.add(getGasto(cursor));
		}
		cursor.close();
		db.close();
		return gastos;
	}

	public List<Gasto> getGastos(java.util.Date desde, java.util.Date hasta)
			throws IllegalArgumentException, ParseException {

		List<Gasto> gastos = new LinkedList<Gasto>();
		SQLiteDatabase db = handler.getDb();
		// Cursor cursor = db.rawQuery(query, null);

		Cursor cursor = db.query(GastoEntry.TABLE_NAME, // The table to query
				GastoEntry.COLUMNS, // The columns to return
				null,
				// GastoEntry.COLUMN_NAME_DATE + " = ?", // The columns for the
				// WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // sortOrder // The sort order
		);

		while (cursor.moveToNext()) {
			gastos.add(getGasto(cursor));
		}
		cursor.close();
		db.close();
		return gastos;
	}

	public List<Gasto> getGastos(Categoria categoria) throws IllegalArgumentException, ParseException {

		List<Gasto> gastos = new LinkedList<Gasto>();
		SQLiteDatabase db = handler.getDb();
		// Cursor cursor = db.rawQuery(query, null);

		Cursor cursor = db.query(GastoEntry.TABLE_NAME, // The table to query
				GastoEntry.COLUMNS, // The columns to return
				null,
				// GastoEntry.COLUMN_NAME_DATE + " = ?", // The columns for the
				// WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // sortOrder // The sort order
		);

		while (cursor.moveToNext()) {
			gastos.add(getGasto(cursor));
		}
		cursor.close();
		db.close();
		return gastos;
	}

	public long add(Gasto gasto) {

		ContentValues values = new ContentValues();
		if (gasto.getId() != -1) 
			values.put(GastoEntry._ID, gasto.getId());
		values.put(GastoEntry.COLUMN_NAME_DESCRIPCION, gasto.getDetalle());
		values.put(GastoEntry.COLUMN_NAME_IMPORTE, gasto.getImporte());
		values.put(GastoEntry.COLUMN_NAME_DATE, dateformatter.format(gasto.getFecha()));
		values.put(GastoEntry.COLUMN_NAME_CATEGORIA_ID,
				gasto.getCategoria() != null ? gasto.getCategoria().getId() : null);
		// insert
		gasto.setId(db.insert(GastoEntry.TABLE_NAME, null, values));
		db.close();
		return gasto.getId();
	}

	public int update(Gasto gasto) {
		ContentValues values = new ContentValues();
		values.put(GastoEntry.COLUMN_NAME_DESCRIPCION, gasto.getDetalle());
		values.put(GastoEntry.COLUMN_NAME_IMPORTE, gasto.getImporte());
		values.put(GastoEntry.COLUMN_NAME_DATE, dateformatter.format(gasto.getFecha()));
		values.put(GastoEntry.COLUMN_NAME_CATEGORIA_ID,
				gasto.getCategoria() != null ? gasto.getCategoria().getId() : null);
		int i = db.update(GastoEntry.TABLE_NAME, // table
				values, // column/value
				"id = ?", // selections
				new String[] { String.valueOf(gasto.getId()) });

		db.close();

		return i;
	}

	public void delete(Gasto gasto) {
		// Get reference to writable DB

		db.delete(GastoEntry.TABLE_NAME, "id = ?", new String[] { String.valueOf(gasto.getId()) });
		db.close();

	}
}
