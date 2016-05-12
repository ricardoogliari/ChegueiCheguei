package helloworld.teste.com.explosaocaboenergia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by ricardoogliari on 5/12/16.
 */
public class VerificaSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    if (message.startsWith("cheguei")){
                        Intent in = new Intent(context, VideoActivity.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(in);
                    }

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
        }
    }
}
