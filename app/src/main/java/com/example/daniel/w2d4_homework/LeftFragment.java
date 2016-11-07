package com.example.daniel.w2d4_homework;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LeftFragment extends Fragment {

    private static final String TAG = "LeftFragmentTAG_";

//    TextView leftTxtV;
    Button leftButton;

    public LeftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        leftTxtV = (TextView) getView().findViewById(R.id.fl_txtView);
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_left, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        leftButton = (Button) getView().findViewById(R.id.fl_nestBtn);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nestedFragment = new NestedFragment();
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack("left");
                fragmentTransaction.add(getId(), nestedFragment, "left"); //Inspite of get id could be nestedFragmentID
                fragmentTransaction.commit();
            }
        });
    }
}
