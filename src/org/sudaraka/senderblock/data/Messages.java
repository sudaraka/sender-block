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
	
	public void finalize() {
		db.close();
	}
	
	protected void add(String address, int type, int received_date) {
		ContentValues val = new ContentValues();
		
		val.put("address", address);
		val.put("type", type);
		val.put("received_date", received_date);
		
		db.insert(TABLE, null, val);
	}
	
	protected void remove(int type) {
		db.delete(TABLE, " type = " + type, null);
	}
	
	public Cursor get_stat() {
		String sql ="select m._id, m.address, count(m._id) as message_count, (bs._id is not null) as blocked " +
				"from " + TABLE + " as m " +
				"left join " + MessageFilterDataHelper.TABLE_BLOCKED_SENDERS + " as bs on " +
				"	bs.address = m.address " +  
				"group by m.address " +
				"order by blocked desc, message_count desc"
				;
		
		return db.rawQuery(sql, null);
	}
}
