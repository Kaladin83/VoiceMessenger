package com.example.maratbe.mytest1;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.privateImeOptions;
import static android.R.attr.value;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener,DialogInterface.OnClickListener{
    private final int LIGHTPURPLE  = Color.argb(Color.TRANSPARENT,218,156,245);
    private final int LIGHTGREEN  = Color.argb(Color.TRANSPARENT,185,252,158);
    private final int LIGHTBLUE  = Color.argb(Color.TRANSPARENT,146,206,240);
    private String[] items; //= new String[]{"Shon","Kissa","Marat"};
    private static ArrayList<Message> listOfMessages = new ArrayList<Message>();
    ArrayAdapter<String> adapter;
    MainActivity general;
    AutoModeActivity auto;
    AdvancedActivity advanced;
    private final int numOfMesseges = 10;
    Intent myIntent;
    Bundle b;
    private static int number = 0;

    private void setMessages() {
        Message message = new Message("Meeting1","I'm on a meeting", "Standard", null, LIGHTGREEN);
        listOfMessages.add(message);
        message = new Message("Meeting2","Please don't bother", "Custom", null, LIGHTBLUE);
        listOfMessages.add(message);
        message = new Message("Killing","Please dont kill me!!!", "Quote", null, LIGHTPURPLE);
        listOfMessages.add(message);
        getSpinnerItems();
    }

    public static  ArrayList<Message> getListOfMessages(){
        return listOfMessages;
    }

    private void addMessageConponents() {
        LinearLayout.LayoutParams params;
        int index;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        LinearLayout messagesVLL = (LinearLayout) findViewById(R.id.messageHLL);

        for (int i = 0; i < numOfMesseges; i++) {
            index = i % 3;

            TextView rowTextView = new TextView(this);
            rowTextView.setText(i + 1 + ". ");

            Spinner dropdown = new Spinner(this);
            dropdown.setAdapter(adapter);
            dropdown.setSelection(index);
            dropdown.setOnItemSelectedListener(this);

         /*   ImageView image = new ImageView(this);
            Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.man_on_toilet);
            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 20, 20, true);
            image.setImageBitmap(bMapScaled);*/

            LinearLayout messageHLL = new LinearLayout(this);

            params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.9f);
            messageHLL.addView(rowTextView, params);

            params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.1f);
            messageHLL.addView(dropdown, params);

         /*   params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.1f);
            messageHLL.addView(image, params);*/
            if (i == numOfMesseges - 1) {
                params.setMargins(1, 1, 1, 1);
            }
            else {
                params.setMargins(1, 1, 1, 0);
            }

            messageHLL.setBackgroundColor(LIGHTPURPLE);
            messagesVLL.setBackgroundColor(LIGHTBLUE);
      //      messagesVLL.setBackgroundColor(listOfMessages.get(index).getColor());
            messagesVLL.addView(messageHLL);
        }
    }

    private void getSpinnerItems()
    {
        items = new String[3];
        for (int i = 0; i < listOfMessages.size(); i++)
        {
            items[i] = listOfMessages.get(i).getText();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        general = new MainActivity();
        setContentView(R.layout.main_activity);
        setMessages();
        addMessageConponents();
        Intent intent = getIntent();
        if (number > 0 && intent != null)
        {
            String message = intent.getExtras().getString("message");
            TextView tv = (TextView) findViewById(R.id.desplayTextView);
            tv.setText(message);
        }
        else
        {
            try
            {
                registerReceiver(new PhoneCallReceiver(),  new IntentFilter("android.intent.action.PHONE_STATE"));
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }

      /*  myIntent = new Intent(this, IncomingCallUI.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(myIntent);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.general_menu, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.autoMode:
                myIntent = new Intent(this, AutoModeActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(myIntent);
                return true;
            case R.id.advanced:
                myIntent = new Intent(this, AdvancedActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
