package com.frame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.List;

public abstract class BaseWXAdapter extends BaseAdapter implements IAdapter {
    public final int viewKey = 0x7f040009;
    public Context context;
    public List<Serializable> list;
    public Object map;
    public LayoutInflater layoutInflater;
    public OnClickListener ol;


    public BaseWXAdapter(Context _context, List<Serializable> _list) {
        this.context = _context;
        this.list = _list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public OnClickListener getOl() {
        return ol;
    }

    public void setOl(OnClickListener ol) {
        this.ol = ol;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

}
