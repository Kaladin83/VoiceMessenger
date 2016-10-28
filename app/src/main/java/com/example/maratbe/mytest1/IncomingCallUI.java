package com.example.maratbe.mytest1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by maratbe on 10/20/2016.
 */

public class IncomingCallUI extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
  //      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
   //             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        setContentView(R.layout.incoming_call_ui);
    }

    public IncomingCallUI (){
        // Empty constructor required for DialogFragment
    }
}

