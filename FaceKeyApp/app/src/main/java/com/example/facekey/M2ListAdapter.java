package com.example.facekey;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class M2ListAdapter extends BaseAdapter {

    private Context context;
    private List<M2List> m2List;

    public M2ListAdapter(Context context, List<M2List> m2List){
        this.context = context;
        this.m2List = m2List;
    }

    @Override
    public int getCount() {
        return m2List.size();
    }

    @Override
    public Object getItem(int position) {
        return m2List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.m2list, null);
        TextView enter_timeText = (TextView)v.findViewById(R.id.enter_timeText);
        TextView exit_timeText = (TextView)v.findViewById(R.id.exit_timeText);
        TextView lateText = (TextView)v.findViewById(R.id.lateText);

        enter_timeText.setText(m2List.get(position).getEnter_timeText());
        exit_timeText.setText(m2List.get(position).getExit_timeText());
        lateText.setText(m2List.get(position).getLateText());

        v.setTag(m2List.get(position).getEnter_timeText());
        return v;
    }

}
