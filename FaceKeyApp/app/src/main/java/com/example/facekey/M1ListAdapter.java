package com.example.facekey;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class M1ListAdapter extends BaseAdapter {

    private Context context;
    private List<M1List> m1List;

    public M1ListAdapter(Context context, List<M1List> m1List){
        this.context = context;
        this.m1List = m1List;
    }

    @Override
    public int getCount() {
        return m1List.size();
    }

    @Override
    public Object getItem(int position) {
        return m1List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.m1list, null);
        TextView enter_timeText = (TextView)v.findViewById(R.id.enter_timeText);
        TextView exit_timeText = (TextView)v.findViewById(R.id.exit_timeText);
        TextView lateText = (TextView)v.findViewById(R.id.lateText);

        enter_timeText.setText(m1List.get(position).getEnter_timeText());
        exit_timeText.setText(m1List.get(position).getExit_timeText());
        lateText.setText(m1List.get(position).getLateText());

        v.setTag(m1List.get(position).getEnter_timeText());
        return v;
    }

}
