package org.sudaraka.senderblock.system;

import org.sudaraka.senderblock.R;
import org.sudaraka.senderblock.data.MessagesSMS;
import org.sudaraka.senderblock.ui.MessageFilterStatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;


public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent();
		PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);

		Notification notification = new NotificationCompat.Builder(context)
			.setContentTitle("Got SMS")
			.setContentText("Details not available")
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentIntent(pi)
			.build()
			;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(0, notification);

		Bundle b = intent.getExtras();
		if(null != b) {
			Object[] pdus = (Object[]) b.get("pdus");
			SmsMessage[] msg_list = new SmsMessage[pdus.length];
			MessagesSMS stat = new MessagesSMS(context);

			for(int idx = 0; idx < msg_list.length; idx++) {
				msg_list[idx] = SmsMessage.createFromPdu((byte[]) pdus[idx]);

				stat.add(msg_list[idx].getOriginatingAddress(), (int) msg_list[idx].getTimestampMillis() / 1000);
			}
		}
	}

}
