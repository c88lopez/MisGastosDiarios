package ar.edu.ort.t5.grp1.data;

import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ar.edu.ort.t5.grp1.data.CategoriaContract.CategoriaEntry;
import ar.edu.ort.t5.grp1.data.GastoContract.GastoEntry;
import ar.edu.ort.t5.grp1.misgastosdiarios.Categoria;
import ar.edu.ort.t5.grp1.misgastosdiarios.Gasto;
import ar.edu.ort.t5.grp1.misgastosdiarios.Reporte;

@SuppressLint("SimpleDateFormat")
public class CategoriaData {

	// static final DateFormat dateformatter = new
	// SimpleDateFormat("yyyyMMddHHmmss");

	private static SQLiteDatabaseHandler handler;
	// private static SQLiteDatabase db;

	public CategoriaData(Context context) {
		handler = SQLiteDatabaseHandler.getInstance(context);
		// db = handler.getDb();
	}

	/*
	 * protected void finalize() throws Throwable { if (db.isOpen()) db.close();
	 * super.finalize(); }
	 */

	private static Categoria get(Cursor cursor) {
		Categoria categoria = null;

		return categoria;

	}

	public static Categoria get(int id) {
		Categoria categoria = null;
		SQLiteDatabase db = handler.getDb();
		Cursor cursor = db.query(CategoriaEntry.TABLE_NAME, // a. table
				CategoriaEntry.COLUMNS, // b. column names
				CategoriaEntry._ID + " = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit
		
		if (cursor.moveToFirst()) {
			categoria = new Categoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaEntry._ID)),
				cursor.getString(cursor.getColumnIndexOrThrow(CategoriaEntry.COLUMN_NAME_DESCRIPCION)));
		}
		db.close();
		return categoria;
	}
	public static Categoria get(String descripcion) {
		Categoria categoria = null;
		SQLiteDatabase db = handler.getDb();
		Cursor cursor = db.query(CategoriaEntry.TABLE_NAME, // a. table
				CategoriaEntry.COLUMNS, // b. column names
				CategoriaEntry.COLUMN_NAME_DESCRIPCION + " = ?", // c. selections
				new String[] { descripcion }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit
		
		if (cursor.moveToFirst()) {
			categoria = new Categoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaEntry._ID)),
				cursor.getString(cursor.getColumnIndexOrThrow(CategoriaEntry.COLUMN_NAME_DESCRIPCION)));
		}
		db.close();
		return categoria;
	}

	public static List<Categoria> getList() {
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
			Categoria categoria = new Categoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoriaEntry.COLUMN_NAME_DESCRIPCION)));
			categorias.add(categoria);
		}

		cursor.close();
		db.close();
		return categorias;
	}

	public void insert(Categoria categoria) {
		if (get((int) categoria.getId()) == null) {
			SQLiteDatabase db = handler.getDb();
			Cursor c = db.rawQuery("SELECT * FROM categoria;", null);
			while (c.moveToNext()) {
				Log.i("APP", String.valueOf(String.valueOf(c.getInt(0)) + c.getString(1)));
			}

			ContentValues values = new ContentValues();
			if (categoria.getId() != -1) {
				values.put(CategoriaEntry._ID, categoria.getId());
			}
			values.put(CategoriaEntry.COLUMN_NAME_DESCRIPCION, categoria.getDescripcion());
			// insert
			if (get((int) categoria.getId()) == null) {
				db = handler.getDb();
				categoria.setId(db.insert(CategoriaEntry.TABLE_NAME, null, values));
			}
			db.close();
		}
	}

	public int update(Categoria categoria) {
		SQLiteDatabase db = handler.getDb();
		ContentValues values = new ContentValues();
		values.put(CategoriaEntry.COLUMN_NAME_DESCRIPCION, categoria.getDescripcion());

		int i = db.update(CategoriaEntry.TABLE_NAME, // table
				values, // column/value
				CategoriaEntry._ID + " = ?", // selections
				new String[] { String.valueOf(categoria.getId()) });

		db.close();

		return i;
	}

	public void delete(Categoria categoria) throws Exception {
		if (enUso(categoria)) {
			throw new Exception("La categoria esta en uso");
		}
		SQLiteDatabase db = handler.getDb();
		
		db.delete(CategoriaEntry.TABLE_NAME, CategoriaEntry._ID + " = ?",
				new String[] { String.valueOf(categoria.getId()) });
		db.close();

	}
	
	private boolean enUso (Categoria categoria){
		SQLiteDatabase db = handler.getDb();
		String s = categoria != null ? Long.toString(categoria.getId()) : "";
		
		String sql = "SELECT COUNT(*) FROM GASTO WHERE GASTO.CATEGORIA_ID = ?; ";
		Cursor cursor = db.rawQuery(sql, new String[] {s});
		cursor.moveToFirst();
		return cursor.getLong(0) == 0;
	}

	public List<Reporte> getReporte(int mes) throws IllegalArgumentException, ParseException {

		List<Reporte> lista = new LinkedList<Reporte>();
		SQLiteDatabase db = handler.getDb();
		String s = Integer.toString(mes);
		s = mes>9?"":"0" + s;
		
		String sql = "SELECT ROUND(SUM(IMPORTE),2) suma FROM GASTO WHERE SUBSTR(GASTO.FECHA,5,2) = ?; ";
		Cursor cursor = db.rawQuery(sql, new String[] { s});
		float suma = cursor.moveToFirst() ? cursor.getFloat(cursor.getColumnIndexOrThrow("suma")) : 0;
		
		sql = "SELECT CATEGORIA._ID, CATEGORIA.DESCRIPCION, ROUND(SUM(IMPORTE),2) importe, ROUND((SUM(IMPORTE)*100/?),2) porcentaje FROM GASTO LEFT JOIN CATEGORIA ON CATEGORIA._ID = GASTO.CATEGORIA_ID  WHERE SUBSTR(GASTO.FECHA,5,2) = ? GROUP BY CATEGORIA._ID, CATEGORIA.DESCRIPCION ORDER BY ROUND(SUM(IMPORTE),2); ";
		cursor = db.rawQuery(sql, new String[] { Float.toString(suma), s });
		
		while (cursor.moveToNext()) {
			Categoria categoria = new Categoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoriaEntry.COLUMN_NAME_DESCRIPCION)));
			lista.add(new Reporte(categoria, cursor.getFloat(cursor.getColumnIndexOrThrow("importe")),
					cursor.getFloat(cursor.getColumnIndexOrThrow("porcentaje"))));
		}
		cursor.close();
		db.close();
		return lista;
	}
	public List<Reporte> getReporte(int mes, int ano) throws IllegalArgumentException, ParseException {

		List<Reporte> lista = new LinkedList<Reporte>();
		SQLiteDatabase db = handler.getDb();
		String s = Integer.toString(mes);
		s = Integer.toString(ano) + (mes>9?"":"0") + s ;
		
		String sql = "SELECT ROUND(SUM(IMPORTE),2) suma FROM GASTO WHERE SUBSTR(GASTO.FECHA,1,6) = ?; ";
		Cursor cursor = db.rawQuery(sql, new String[] { s});
		float suma = cursor.moveToFirst() ? cursor.getFloat(cursor.getColumnIndexOrThrow("suma")) : 0;
		
		sql = "SELECT CATEGORIA._ID, CATEGORIA.DESCRIPCION, ROUND(SUM(IMPORTE),2) importe, (SUM(IMPORTE)*100/?) porcentaje FROM GASTO LEFT JOIN CATEGORIA ON CATEGORIA._ID = GASTO.CATEGORIA_ID  WHERE SUBSTR(GASTO.FECHA,1,6) = ? GROUP BY CATEGORIA._ID, CATEGORIA.DESCRIPCION ORDER BY CATEGORIA.DESCRIPCION; ";
		cursor = db.rawQuery(sql, new String[] { Float.toString(suma), s });
		
		while (cursor.moveToNext()) {
			Categoria categoria = new Categoria(cursor.getInt(cursor.getColumnIndexOrThrow(CategoriaEntry._ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(CategoriaEntry.COLUMN_NAME_DESCRIPCION)));
			lista.add(new Reporte(categoria, cursor.getFloat(cursor.getColumnIndexOrThrow("importe")),
					cursor.getFloat(cursor.getColumnIndexOrThrow("porcentaje"))));
		}
		cursor.close();
		db.close();
		return lista;
	}
}
