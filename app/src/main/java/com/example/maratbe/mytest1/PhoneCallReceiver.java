package com.example.maratbe.mytest1;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by maratbe on 10/20/2016.
 */

public class PhoneCallReceiver extends BroadcastReceiver {
    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static Date callStartTime;
    private static boolean isIncoming;
    private static String savedNumber;
    private Context context;
    private Intent myIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            Toast.makeText(context, "In OUTGOING_CALL.", Toast.LENGTH_LONG).show();
        }
        else{
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context, "In incoming CALL.", Toast.LENGTH_LONG).show();
            int state = 0;
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                state = TelephonyManager.CALL_STATE_IDLE;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                state = TelephonyManager.CALL_STATE_RINGING;
                Toast.makeText(context, "CALL_STATE_RINGING.", Toast.LENGTH_LONG).show();
            }

            onCallStateChanged(context, state, number);
        }
    }

   public void onCallStateChanged(Context context, int state, String number) {
        if(lastState == state){
            return;
        }
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                isIncoming = true;
                callStartTime = new Date();
                savedNumber = number;

                this.context = context;
                try {
                    myIntent = new Intent(context, MainActivity.class);
                    myIntent.putExtra("message","before calling GUI");
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    context.startActivity(myIntent);

                    myIntent = new Intent(context, IncomingCallUI.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    context.startActivity(myIntent);
                } catch (Exception e)
                {
                    e.getLocalizedMessage();
                }
                break;
            default:
                //handleOutgoing();
                //do nothing
        }
        lastState = state;
    }
}
