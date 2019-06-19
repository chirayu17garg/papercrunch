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
public class QuestionFragment extends Fragment {

    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1= inflater.inflate(R.layout.fragment_question, container, false);

        tv2=view1.findViewById(R.id.tv_que);
        tv3=view1.findViewById(R.id.tv_opt1);
        tv4=view1.findViewById(R.id.tv_opt2);
        tv5=view1.findViewById(R.id.tv_opt3);
        tv6=view1.findViewById(R.id.tv_queFrag);
        String msg1=getArguments().getString("msg1");
        String optn1=getArguments().getString("opt1");
        String optn2=getArguments().getString("opt2");
        String optn3=getArguments().getString("opt3");
        String msg0=getArguments().getString("msg0");
        tv2.setText(msg1);
        tv3.setText(optn1);
        tv4.setText(optn2);
        tv5.setText(optn3);
        tv6.setText(msg0);
        return view1;
    }

}
