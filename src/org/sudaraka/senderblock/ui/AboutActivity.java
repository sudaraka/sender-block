package org.sudaraka.senderblock.ui;

import java.io.InputStream;

import org.sudaraka.senderblock.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class AboutActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		TabHost tabs = (TabHost)findViewById(R.id.about_tabhost);
		tabs.setup();
		
		TabSpec tab_copyright = tabs.newTabSpec("Copyright");
		tab_copyright.setIndicator("Copyright");
		tab_copyright.setContent(R.id.copyright);
		tabs.addTab(tab_copyright);
		
		TabSpec tab_license = tabs.newTabSpec("License");
		tab_license.setIndicator("License");
		tab_license.setContent(R.id.license);
		tabs.addTab(tab_license);
		
		TextView gpl_text = (TextView)findViewById(R.id.gpl_text);
		
		try {
			InputStream is = getResources().openRawResource(R.raw.gpl);
			byte[] b = new byte[is.available()];
			is.read(b);
			
			gpl_text.setText(new String(b));
		}
		catch (Exception e) {}
		
		gpl_text.setMovementMethod(new ScrollingMovementMethod());
	}
}
