package ar.edu.ort.t5.grp1.data;

import java.util.LinkedList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SQLiteDatabaseHandler extends SQLiteOpenHelper {
 
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MisGastosDiariosDB";
    private static final String TABLE_NAME = "Gastos";
    private static final String KEY_ID = "id";
    private static final String KEY_DETALLE = "detalle";
    private static final String KEY_CATEGORIA_ID = "categoria_id";
    private static final String KEY_IMPORTE = "importe";
    private static final String KEY_DATE = "date";
    private static final String[] COLUMNS = { KEY_ID, KEY_DETALLE, KEY_CATEGORIA_ID, KEY_IMPORTE, KEY_DATE };
 
    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
    	private static final String SQL_CREATE_ENTRIES =
    		    "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
    		    FeedEntry._ID + " INTEGER PRIMARY KEY," +
    		    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
    		    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    		private static final String SQL_DELETE_ENTRIES =
    		    "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
 
        String CREATION_TABLE = "CREATE TABLE Gastos ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
                + "position TEXT, " + "height INTEGER )";
 
        db.execSQL(CREATION_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
 
    public void deleteOne(GastoContract gasto) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?",
                new String[] { String.valueOf(player.getId()) });
        db.close();
 
    }
 
    public Player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        if (cursor != null)
            cursor.moveToFirst();
 
        Player player = new Player();
        player.setId(Integer.parseInt(cursor.getString(0)));
        player.setName(cursor.getString(1));
        player.setPosition(cursor.getString(2));
        player.setHeight(Integer.parseInt(cursor.getString(3)));
 
        return player;
    }
 
    public List<Player> allPlayers() {
 
        List<Player> players = new LinkedList<Player>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Player player = null;
 
        if (cursor.moveToFirst()) {
            do {
                player = new Player();
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setName(cursor.getString(1));
                player.setPosition(cursor.getString(2));
                player.setHeight(Integer.parseInt(cursor.getString(3)));
                players.add(player);
            } while (cursor.moveToNext());
        }
 
        return players;
    }
 
    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DETALLE, player.getName());
        values.put(KEY_CATEGORIA_ID, player.getPosition());
        values.put(KEY_IMPORTE, player.getHeight());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }
 
    public int updatePlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DETALLE, player.getName());
        values.put(KEY_CATEGORIA_ID, player.getPosition());
        values.put(KEY_IMPORTE, player.getHeight());
 
        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(player.getId()) });
 
        db.close();
 
        return i;
    }
 
}