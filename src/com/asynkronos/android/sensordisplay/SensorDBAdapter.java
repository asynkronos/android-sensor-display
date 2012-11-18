package com.asynkronos.android.sensordisplay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author Michael Bruno
 *
 */
public class SensorDBAdapter {
	
	public static final String DB_NAME = "sensorDash.db";
	public static final String DB_TABLE ="sensor_values";
	public static final int DB_VERSION = 2;
	
	public static final String KEY_ID = "sensorId";
	//public static final String KEY_NAME = 
	
	public static final String DB_CREATE = "create table " +
	DB_TABLE + " (sensorId INTEGER PRIMARY KEY, val0 REAL, val1 REAL, val2 REAL, val3 REAL)";
	
	private SQLiteDatabase db;
	@SuppressWarnings("unused")
	private final Context context;
	private DatabaseHelper dbHelper;
	
	public SensorDBAdapter(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
	}
	
	public void open() throws SQLException {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			db = dbHelper.getReadableDatabase();
		}
	}
	
	public void close() {
		db.close();
	}

	
	//public boolean removeEntry(long _rowIndex) {
	//	return db.delete(DB_TABLE, KEY_ID + "=" + _rowIndex, null) > 0;
	//}
	
	//public Cursor getAllEntries () {
	//	return db.query(DB_TABLE, new String[] {KEY_ID, KEY_NAME},
	//	null, null, null, null, null);
	//}
		
	
	public long insertEntry(DatabaseEntry entry) {
		// Create a new row of values to insert.
		ContentValues values = new ContentValues();
		
		values.put(KEY_ID, entry.getSensorId());
		values.put("val0", entry.getVal0());
		values.put("val1", entry.getVal1());
		values.put("val2", entry.getVal2());
		values.put("val3", entry.getVal3());
		return db.insert(DB_TABLE, null, values);
	}
		
	public boolean removeEntry(long _rowIndex) {
		return db.delete(DB_TABLE, KEY_ID + "=" + _rowIndex, null) > 0;
	}
		
	public boolean updateEntry(long sensorId, DatabaseEntry entry) {
		ContentValues values = new ContentValues();
		values.put("val0", entry.getVal0());
		values.put("val1", entry.getVal1());
		values.put("val2", entry.getVal2());
		values.put("val3", entry.getVal3());
		return db.update(DB_TABLE, values, KEY_ID + "=" + sensorId, null) > 0;
	}
	
	public DatabaseEntry getEntry(long sensorId) throws SQLException {
		
		//Cursor cursor = db.query(true, DB_TABLE,new String[] {KEY_ID, "val0","val1",
		//		"val2","val3"},
		Cursor cursor = db.rawQuery("Select sensorId, val0, val1, val2, val3 from sensor_values where sensorId=" +sensorId,
				null);
		
		DatabaseEntry result = null;
		
		if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
			//throw new SQLException("No to do item found for row: " + sensorId);

		}else{
			result = new DatabaseEntry(new Integer(cursor.getInt(0)),
				new Float(cursor.getFloat(1)),
				new Float(cursor.getFloat(2)),
				new Float(cursor.getFloat(3)),
				new Float(cursor.getFloat(4)));
		}
		cursor.close();
		return result;
	}
	
	public DatabaseEntry dump() throws SQLException {
		
		//Cursor cursor = db.query(true, DB_TABLE,new String[] {KEY_ID, "val0","val1",
		//		"val2","val3"},
		Cursor cursor = db.rawQuery("Select * from sensor_values",null);
		
		DatabaseEntry result = null;
		boolean more =true;
		cursor.moveToFirst();
		
		while(more){
			
			Log.e("result:",
			cursor.getInt(0)+ " " +
			cursor.getFloat(1)+ " " +
			cursor.getFloat(2)+ " " +
			cursor.getFloat(3)+ " " +
			cursor.getFloat(4));
			if(cursor.isLast()){
				more = false;
			}else{
				cursor.moveToNext();
			}
		}
		
		if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
			//throw new SQLException("No to do item found for row: " + sensorId);

		}else{
			result = new DatabaseEntry(new Integer(cursor.getInt(0)),
				new Float(cursor.getFloat(1)),
				new Float(cursor.getFloat(2)),
				new Float(cursor.getFloat(3)),
				new Float(cursor.getFloat(4)));
		}
		cursor.close();
		return result;
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		public DatabaseHelper(Context context, String name,
		CursorFactory factory, int version) {
		super(context, name, factory, version);
		}
		// Called when no database exists in disk and the helper class needs
		// to create a new one.
		
		@Override
		public void onCreate(SQLiteDatabase _db) {
		 _db.execSQL(DB_CREATE);
		}
		// Called when there is a database version mismatch meaning that the version
		// of the database on disk needs to be upgraded to the current version.
		
		@Override
		public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
		// Log the version upgrade.
		//Log.w("TaskDBAdapter", "Upgrading from version " +
		//_oldVersion + " to " +
		//_newVersion + ", which will destroy all old data");
		// Upgrade the existing database to conform to the new version. Multiple
		// previous versions can be handled by comparing _oldVersion and _newVersion
		// values.
		// The simplest case is to drop the old table and create a new one.
		_db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
		// Create a new one.
		onCreate(_db);
		}
	}
}
