package com.example.deerg.papercrunch;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    public Integer i=0;
    private Context CTX;

    public static int star,progress;

    private Integer image_id[] = {R.drawable.medal,R.drawable.medal2,R.drawable.medal3,R.drawable.medal4,R.drawable.medal5,R.drawable.medal6,R.drawable.medal7,R.drawable.medal8,R.drawable.medal9,R.drawable.medal10,R.drawable.medal11,R.drawable.medal12,R.drawable.medal13,R.drawable.medal14,R.drawable.medal15};
    public ImageAdapter(Context CTX){
        this.CTX=CTX;
    }
    @Override
    public int getCount() {
        return image_id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img;
        if(convertView==null){
            img=new ImageView(CTX);
            img.setLayoutParams(new GridView.LayoutParams(160,160));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8,8,8,8);
        }
        else {
            img=(ImageView) convertView;
        }
        if(position<=star/12&&position%2==0&&star>=12) {
            img.setImageResource(image_id[position]);
            img.setVisibility(View.VISIBLE);
            return img;
        }
        else {
            if(position<=progress/200&&position%2!=0&&progress>=200){
                img.setImageResource(image_id[position]);
                img.setVisibility(View.VISIBLE);
                return img;
            }
            else {
                img.setImageResource(image_id[position]);
                img.setVisibility(View.GONE);
                return img;
            }
        }

    }

}
