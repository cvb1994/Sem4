package com.example.caovuongbach_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<FeedBack> list;
    private IOnChildItemClick iOnChildItemClick;

    public Adapter(Context context, List<FeedBack> list) {
        this.context = context;
        this.list = list;
    }

    public void reloadData(List<FeedBack> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void registerChildItemClick(IOnChildItemClick iOnChildItemClick){
        this.iOnChildItemClick = iOnChildItemClick;
    }

    public void unRegisterChildItemClick(){
        this.iOnChildItemClick = null;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if(rowView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item, null);

            ViewHolder holder = new ViewHolder();
            holder.tvName = rowView.findViewById(R.id.tvName);
            holder.tvMail = rowView.findViewById(R.id.tvMail);
            holder.tvContent = rowView.findViewById(R.id.tvContent);
            rowView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(list.get(i).getName());
        holder.tvMail.setText(list.get(i).getMail());
        holder.tvContent.setText(list.get(i).getContent());
        return rowView;
    }

    static class ViewHolder{
        TextView tvName, tvMail, tvContent;
    }
}
