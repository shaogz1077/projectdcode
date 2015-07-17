package com.frame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;

import java.util.List;
import java.util.Map;


public abstract class LogicBaseAdapter extends android.widget.BaseAdapter
        implements IAdapter {

    public final int viewKey = 0x7f040009;
    public final int moreKey = 0x7f040008;
    public Context context;
    public List<Map<String, String>> list;
    public Map<String, String> map;
    public LayoutInflater layoutInflater;
    public OnClickListener ol;


    public LogicBaseAdapter(Context _context, List<Map<String, String>> _list) {
        this.context = _context;
        this.list = _list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public OnClickListener getOl() {
        return ol;
    }

    public void setOl(OnClickListener ol) {
        this.ol = ol;
    }

}
