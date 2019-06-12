package com.example.deerg.papercrunch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConceptFragment extends Fragment {

    private TextView tv1;

    public ConceptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_concept, container, false);

        tv1=view.findViewById(R.id.tv_frag);
        String msg=getArguments().getString("msg");
        tv1.setText(msg);

        return view;
    }

}
