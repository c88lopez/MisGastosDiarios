package ar.edu.ort.t5.grp1.data;

import java.util.LinkedList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ar.edu.ort.t5.grp1.data.CategoriaContract.CategoriaEntry;
import ar.edu.ort.t5.grp1.misgastosdiarios.Categoria;

@SuppressLint("SimpleDateFormat")
public class CategoriaData {

	// static final DateFormat dateformatter = new
	// SimpleDateFormat("yyyyMMddHHmmss");

	private static SQLiteDatabaseHandler handler;
	private static SQLiteDatabase db;

	public CategoriaData(Context context) {
		handler = SQLiteDatabaseHandler.getInstance(context);
		db = handler.getDb();
	}

	protected void finalize() throws Throwable {
		if (db.isOpen())
			db.close();
		super.finalize();
	}

	private static Categoria get(Cursor cursor) {
		return new Categoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaEntry._ID)),
				cursor.getString(cursor.getColumnIndexOrThrow(CategoriaEntry.COLUMN_NAME_DESCRIPCION)));
	}

	public static Categoria get(int id) {
		Categoria categoria = null;
		SQLiteDatabase db = handler.getDb();
		Cursor cursor = db.query(CategoriaEntry.TABLE_NAME, // a. table
				CategoriaEntry.COLUMNS, // b. column names
				" id = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (cursor != null) {
			cursor.moveToFirst();
			categoria = get(cursor);
		}
		db.close();
		return categoria;
	}

	public List<Categoria> getList() {

		List<Categoria> categorias = new LinkedList<Categoria>();
		SQLiteDatabase db = handler.getDb();
		// Cursor cursor = db.rawQuery(query, null);

		Cursor cursor = db.query(CategoriaEntry.TABLE_NAME, // The table to
															// query
				CategoriaEntry.COLUMNS, // The columns to return
				null,
				// CategoriaEntry.COLUMN_NAME_DATE + " = ?", // The columns for
				// the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // sortOrder // The sort order
		);

		while (cursor.moveToNext()) {
			categorias.add(get(cursor));
		}
		cursor.close();
		db.close();
		return categorias;
	}

	public void add(Categoria categoria) {

		ContentValues values = new ContentValues();
		values.put(CategoriaEntry.COLUMN_NAME_DESCRIPCION, categoria.getDescripcion());
		// insert
		categoria.setId(db.insert(CategoriaEntry.TABLE_NAME, null, values));
		db.close();
	}

	public int update(Categoria categoria) {
		ContentValues values = new ContentValues();
		values.put(CategoriaEntry.COLUMN_NAME_DESCRIPCION, categoria.getDescripcion());

		int i = db.update(CategoriaEntry.TABLE_NAME, // table
				values, // column/value
				"id = ?", // selections
				new String[] { String.valueOf(categoria.getId()) });

		db.close();

		return i;
	}

	public void delete(Categoria categoria) {
		// Get reference to writable DB

		db.delete(CategoriaEntry.TABLE_NAME, "id = ?", new String[] { String.valueOf(categoria.getId()) });
		db.close();

	}

}
