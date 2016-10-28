package com.example.maratbe.mytest1;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by maratbe on 10/21/2016.
 */

public class GUIFragment extends Fragment
{
    private ListView messagesList;
    private String[] items;
    private ArrayAdapter<String> adapter;
    private static final String TAG = "MyActivity";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.gui_fragment,
                container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getItems();
        messagesList = (ListView) view.findViewById(R.id.mainListView);
        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, items);
        messagesList.setAdapter(adapter);
    }


    private void getItems()
    {
        items = new String[3];
        Log.d(TAG, "Printing!!!!!!!!!!!!!");
        for (int i = 0; i < MainActivity.getListOfMessages().size(); i++)
        {
            items[i] =  MainActivity.getListOfMessages().get(i).getText();
            Log.d(TAG, "["+i+"] "+ items[i]);
        }

    }
}
