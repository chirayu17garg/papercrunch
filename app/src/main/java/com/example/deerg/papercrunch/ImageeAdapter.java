package com.example.deerg.papercrunch;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageeAdapter extends BaseAdapter {
    public Integer i=0;
    private Context CTX2;
    public static Integer image_id2[] = {R.drawable.avatar,R.drawable.avatar1,R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar4,R.drawable.avatar5,R.drawable.avatar6,R.drawable.avatar7,R.drawable.avatar8,R.drawable.avatar9,R.drawable.avatar10};
    public ImageeAdapter(Context CTX2) {
        this.CTX2 = CTX2;
    }
    @Override
    public int getCount(){
        return image_id2.length;
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
            img=new ImageView(CTX2);
            img.setLayoutParams(new GridView.LayoutParams(250,250));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(8,8,8,8);
        }
        else {
            img=(ImageView) convertView;
        }

        img.setImageResource(image_id2[position]);
        return img;

    }
}



