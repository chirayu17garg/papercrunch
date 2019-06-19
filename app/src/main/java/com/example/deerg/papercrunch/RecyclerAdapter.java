package com.example.deerg.papercrunch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.LevelViewHolder> {

    private Context mContext;
    private List<CardData> cardDataList;
    RecyclerAdapter(Context context,List<CardData> cardDataList){
        this.mContext =context;
        this.cardDataList = cardDataList;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mv = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cardview,viewGroup,false);
        return new LevelViewHolder(mv);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder levelViewHolder, final int i) {
        levelViewHolder.mImage.setImageResource(cardDataList.get(i).getimg());
        levelViewHolder.mlevelnum.setText(cardDataList.get(i).getlevelnum());
        levelViewHolder.mlevelnam.setText(cardDataList.get(i).getlevelname());

       levelViewHolder.carview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SubLevel.class);
                intent.putExtra("Level1",cardDataList.get(i).getlevelnum());
                intent.putExtra("Levelname",cardDataList.get(i).getlevelname());
                intent.putExtra("img",cardDataList.get(i).getimg());
                intent.putExtra("id",cardDataList.get(i).getid());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return cardDataList.size();
    }


    class LevelViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mlevelnum;
        TextView mlevelnam;
        CardView carview;
        public LevelViewHolder(@NonNull View itemView) {
            super(itemView);
            carview = itemView.findViewById(R.id.carview);
            mImage = itemView.findViewById(R.id.card1pic);
            mlevelnam = itemView.findViewById(R.id.textlvl1);
            mlevelnum = itemView.findViewById(R.id.textcard1);

        }
    }
}
