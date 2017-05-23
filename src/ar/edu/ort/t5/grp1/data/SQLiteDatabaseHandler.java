package ar.edu.ort.t5.grp1.data;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ar.edu.ort.t5.grp1.data.CategoriaContract.CategoriaEntry;
import ar.edu.ort.t5.grp1.data.GastoContract.GastoEntry;
 

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

	private static SQLiteDatabaseHandler instance = null;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "MisGastosDiariosDB";
	private SQLiteDatabase db;

	private SQLiteDatabaseHandler(Context context) {	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.db = this.getReadableDatabase();
	}

	public static SQLiteDatabaseHandler getInstance(Context context) {
		if (instance == null) {
			instance = new SQLiteDatabaseHandler(context);
		}
		return instance;
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(GastoEntry.SQL_CREATE);
		db.execSQL(CategoriaEntry.SQL_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// you can implement here migration process
		db.execSQL(GastoEntry.SQL_DELETE);
		db.execSQL(CategoriaEntry.SQL_DELETE);
		this.onCreate(db);
	}

}