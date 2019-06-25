package com.example.deerg.papercrunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

    private TextView tv2;
    private Button tv3;
    private Button tv4;
    private Button tv5;
    private TextView tv6;

    public static String ab;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1= inflater.inflate(R.layout.fragment_question, container, false);

        tv2=view1.findViewById(R.id.tv_que);
        tv3=(Button)view1.findViewById(R.id.tv_opt1);
        tv4=(Button)view1.findViewById(R.id.tv_opt2);
        tv5=(Button)view1.findViewById(R.id.tv_opt3);
        tv6=view1.findViewById(R.id.tv_queFrag);
        String msg0=getArguments().getString("msg0");
        String msg1=getArguments().getString("msg1");
        String optn1=getArguments().getString("opt1");
        String optn2=getArguments().getString("opt2");
        String optn3=getArguments().getString("opt3");
        tv2.setText(msg1);
        tv3.setText(optn1);
        tv4.setText(optn2);
        tv5.setText(optn3);
        tv6.setText(msg0);

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ab=tv3.getText().toString();
                Intent intent2=new Intent(getActivity(),Ans_Popup.class);
                startActivity(intent2);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ab=tv4.getText().toString();
                Intent intent3=new Intent(getActivity(),Ans_Popup.class);
                startActivity(intent3);
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ab=tv5.getText().toString();
                Intent intent4=new Intent(getActivity(),Ans_Popup.class);
                startActivity(intent4);
            }
        });


        return view1;
    }

}
