package org.sudaraka.senderblock.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BlockedSenders {
	private static final String TABLE = MessageFilterDataHelper.TABLE_BLOCKED_SENDERS;
	
	private SQLiteDatabase db;
	
	public BlockedSenders(Context context) {
		db = (new MessageFilterDataHelper(context)).getWritableDatabase();
	}
	
	protected void add(String address, int type) {
		if(exists(address, type)) return;
		
		ContentValues val = new ContentValues();
		
		val.put("address", address);
		val.put("type", type);
		
		db.insert(TABLE, null, val);
	}
	
	protected boolean exists(String address, int type) {
		Cursor cursor = db.rawQuery("select 0 from " + TABLE + 
				" where address ='" + address + "' and type = " + type , null);
		
		return 0 < cursor.getCount();
	}
	
	protected void remove(String address, int type) {
		db.delete(TABLE, " address ='" + address + "' and type = " + type, null);
	}
}
