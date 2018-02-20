package com.example.first.suraksha_admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp on 10/10/17.
 */

public class ControlsFragment extends Fragment {
    @Nullable

    View myview;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.controls_layout,container,false);
        return myview;
    }
}