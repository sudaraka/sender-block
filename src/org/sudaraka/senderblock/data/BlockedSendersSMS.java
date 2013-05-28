package org.sudaraka.senderblock.data;

import android.content.Context;

public class BlockedSendersSMS extends BlockedSenders {
	public BlockedSendersSMS(Context context) {
		super(context);
	}

	public void add(String address) {
		super.add(address, SenderType.SMS);
	}
	
	public boolean exists(String address) {
		return super.exists(address, SenderType.SMS);
	}
	
	public void remove(String address) {
		super.remove(address, SenderType.SMS);
	}
}
