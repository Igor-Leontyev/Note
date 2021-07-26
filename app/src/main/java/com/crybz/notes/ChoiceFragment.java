package com.crybz.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class ChoiceFragment extends Fragment {

    boolean isLand = false;

    public ChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if(isLand){
            showContentLand(0);
        }
        initList(view);
    }

    private void initList(View view) {
        LinearLayout LinearLayout = view.findViewById(R.id.cgoice_container);

        String[] notes = getResources().getStringArray(R.array.choice_name);

        for(int i = 0; i < notes.length; i++){
            TextView textView = new TextView(getContext());
            textView.setText(notes[i]);
            textView.setTextSize(32);

            final int finalIndex = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showContent(finalIndex);
                }



            });

            LinearLayout.addView(textView);

        }

    }
    void showContent(int index) {

        if(isLand){
            showContentLand(index);
        }else {
            showContentPort(index);
        }
    }

    void showContentLand(int index){
        ContentFragment fragment = ContentFragment.newInstance(index);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cont_cont,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    void showContentPort(int index){
        Intent intent = new Intent();
        intent.setClass(getActivity(),ContentActivitiy.class);
        intent.putExtra(ContentFragment.ARG_PARAM_INDEX,index);
        startActivity(intent);

    }
}
