package org.sudaraka.senderblock.ui;

import org.sudaraka.senderblock.R;
import org.sudaraka.senderblock.data.BlockedSendersSMS;
import org.sudaraka.senderblock.data.MessagesSMS;

import android.os.Bundle;
import android.app.ListActivity;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ImageView;

public class MessageFilterStatActivity extends ListActivity {
	Cursor sender_list = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sender_list = new MessagesSMS(this).get_stat(); 
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
			this,
			R.layout.stat_row,
			sender_list,
			new String[] {"address", "message_count", "blocked"},
			new int[] {R.id.sender, R.id.count, R.id.bop},
			CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
		);
		
		adapter.setViewBinder(new ViewBinder() {
			
			@Override
			public boolean setViewValue(View v, Cursor c, int col) {
				if(v.getId() == R.id.bop) {
					ImageView img = (ImageView) v;
					
					if(1 == c.getInt(col)) {
						img.setImageResource(R.drawable.blocked);
					}
					else {
						img.setImageResource(R.drawable.unblocked);
					}
					
					return true;
				}
					
				return false;
			}
		});
		
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, final View v, int pos, long id) {
		if(1 == sender_list.getInt(3)) { // Unblock
			new BlockedSendersSMS(getApplicationContext()).remove(sender_list.getString(1));
		}
		else { // Block
			new BlockedSendersSMS(getApplicationContext()).add(sender_list.getString(1));
		}
		
		sender_list.close();
		sender_list = new MessagesSMS(this).get_stat(); 
		((SimpleCursorAdapter)l.getAdapter()).swapCursor(sender_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
