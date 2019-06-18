package com.example.deerg.papercrunch;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class aExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;

    public aExpandableListAdapter(Context context,List<String> listDataHeader,HashMap<String,List<String>> listHashMap){
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;

    }
    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headTitle =(String)getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ashwinlist_group,null);
        }
        TextView lbListHead = (TextView)convertView.findViewById(R.id.lbListHead);
        //lbListHead.setTypeface(null, Typeface.BOLD);
        lbListHead.setText(headTitle);
        ImageView lvlis = (ImageView) convertView.findViewById(R.id.lvListImg);
        ProgressBar pbar = (ProgressBar)convertView.findViewById(R.id.progBar);
        switch (groupPosition){
            case 0 : lvlis.setImageResource(R.drawable.ic_content_paste_black_24dp);pbar.setProgress(66);
            break;
            case 1 : lvlis.setImageResource(R.drawable.ic_date_range_black_24dp);break;
            case 2 : lvlis.setImageResource(R.drawable.ic_developer_mode_black_24dp);break;
            case 3 : lvlis.setImageResource(R.drawable.ic_input_black_24dp);break;
            case 4 : lvlis.setImageResource(R.drawable.logic);break;
            case 5 : lvlis.setImageResource(R.drawable.ic_swap_horiz_black_24dp);break;
            case 6 : lvlis.setImageResource(R.drawable.ic_loop_black_24dp);break;
            case 7 : lvlis.setImageResource(R.drawable.ic_functions_black_24dp);break;
            case 8 : lvlis.setImageResource(R.drawable.ic_view_array_black_24dp);break;

            default: lvlis.setImageResource(R.drawable.logo);
        }



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ashwinlist_item,null);
        }
        TextView txChild = (TextView)convertView.findViewById(R.id.lvlistitem);
        txChild.setText(childText);
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
