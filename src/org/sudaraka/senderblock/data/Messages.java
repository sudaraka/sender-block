package org.sudaraka.senderblock.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Messages {
	private static final String TABLE = MessageFilterDataHelper.TABLE_MESSAGES;
	
	private SQLiteDatabase db;
	
	public Messages(Context context) {
		db = (new MessageFilterDataHelper(context)).getWritableDatabase();
	}
	
	protected void add(String address, int type, int received_date) {
		ContentValues val = new ContentValues();
		
		val.put("address", address);
		val.put("type", type);
		val.put("received_date", received_date);
		
		db.insert(TABLE, null, val);
	}
	
	protected void remove(int type) {
		db.delete(TABLE, " where type = " + type, null);
	}
	
	public Cursor get_stat() {
		String sql ="select _id, address, count(_id) as message_count " +
				"from " + TABLE + " " +
				"group by address";
		
		return db.rawQuery(sql, null);
	}
}
