package com.example.deerg.papercrunch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> lisheader;
    private HashMap<String, List<String>> listchild;
    ExpandableListView expandList;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData, ExpandableListView mView) {
        this.mContext = context;
        lisheader = listDataHeader;
        listchild = listChildData;
        expandList = mView;
    }

    @Override
    public int getGroupCount() {
        int i = lisheader.size();
        return i;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        int i = 0;
        if (groupPosition == 0 || groupPosition==1) {
            i = this.listchild.get(this.lisheader.get(groupPosition)).size();
        }
        return i;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return this.lisheader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this.listchild.get(this.lisheader.get(groupPosition)).get(childPosition);
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
        String header = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        if (groupPosition < 10) {
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.listheader);
            lblListHeader.setText(header);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String child = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        TextView txtchild = (TextView) convertView.findViewById(R.id.submenu);
        txtchild.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
