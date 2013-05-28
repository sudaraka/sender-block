package org.sudaraka.senderblock.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessageFilterDataHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "message_filter_data";
	private static final int DB_VER = 1;
	
	public static final String TABLE_BLOCKED_SENDERS = "blocked_enders"; 
	public static final String TABLE_MESSAGES = "messages"; 
	
	public MessageFilterDataHelper(Context context) {
		super(context, DB_NAME, null, DB_VER);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "";
		
		sql = "create table if not exists " + TABLE_BLOCKED_SENDERS + " (" +
				"_id integer primary key autoincrement"+
				", address text" +
				", type integer" +
				")"
				;
		db.execSQL(sql);
		
		sql = "create table if not exists " + TABLE_MESSAGES + " (" +
				"_id integer primary key autoincrement"+
				", address text" +
				", type integer" +
				", received_date integer" +
				")"
				;
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int old_ver, int new_ver) {
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int old_ver, int new_ver) {
	}

}
