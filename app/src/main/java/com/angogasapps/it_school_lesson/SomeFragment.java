package com.angogasapps.it_school_lesson;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SomeFragment extends Fragment {
    TextView textView;
    String string;

    public SomeFragment(String string) {
        this.string = string;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_some, container, false);
        return view;
    }

    @Override
    public void onStart() {
        textView = getView().findViewById(R.id.textView);
        textView.setText(string);
        super.onStart();
    }
}