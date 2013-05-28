package org.sudaraka.senderblock.data;

import android.content.Context;

public class MessagesSMS extends Messages {
	public MessagesSMS(Context context) {
		super(context);
	}

	public void add(String address, int received_date) {
		super.add(address, SenderType.SMS, received_date);
	}
	
	public void remove() {
		super.remove(SenderType.SMS);
	}
}
