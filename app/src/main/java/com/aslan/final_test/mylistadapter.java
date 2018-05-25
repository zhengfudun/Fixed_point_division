package com.aslan.final_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class mylistadapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<Data> mData;

    public mylistadapter() {}

    public mylistadapter(LinkedList<Data> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder();
            holder.content1 = convertView.findViewById(R.id.l1);
            holder.content2 = convertView.findViewById(R.id.l2);
            holder.content3 = convertView.findViewById(R.id.l3);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.content1.setText(mData.get(position).getContent1());
        holder.content2.setText(mData.get(position).getContent2());
        holder.content3.setText(mData.get(position).getContent3());
        return convertView;
    }

    public void add(Data data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView content1,content2,content3;
    }


}
