package com.example.bysj.fragment;


import android.os.Bundle;


import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ListView;

import com.example.bysj.R;



public class DiscussFragment extends Fragment  {
    private View rootview;
    private ListView Lv = null;


    public static DiscussFragment newInstance(String param1, String param2) {
        DiscussFragment fragment = new DiscussFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_discuss, container, false);
        }

return rootview;
    }

}