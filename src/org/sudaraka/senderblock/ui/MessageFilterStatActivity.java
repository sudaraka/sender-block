package org.sudaraka.senderblock.ui;

import org.sudaraka.senderblock.R;
import org.sudaraka.senderblock.data.MessagesSMS;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

public class MessageFilterStatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats);
		
		ListView list = (ListView) findViewById(R.id.stat_list);
		list.setAdapter(new SimpleCursorAdapter(
				this,
				R.layout.stat_row,
				new MessagesSMS(this).get_stat(),
				new String[] {"address", "message_count"},
				new int[] {R.id.sender, R.id.count},
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
			));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
