package com.frame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基础性适配器基础类
 * </p>
 * <p>
 * 在功能模块中，如果使用的是BaseAdapter，即可继承此类
 * </p>
 * <p>
 * 继承此类及在使用上更为简单
 * </p>
 * <p>
 * 只需要实现getView方法即可
 * </p>
 * <p>
 * 注意事项
 * </p>
 * <li>参数格式的限定，必须提供Context _context,List<Map<String,String>> _list)格式参数</li>
 */
public abstract class ObjectBaseAdapter extends android.widget.BaseAdapter
        implements IAdapter {

    public final int viewKey = 0x7f040009;
    public final int moreKey = 0x7f040008;
    public Context context;
    public List<Map<String, Object>> list;
    public Map<String, Object> map;
    public LayoutInflater layoutInflater;
    public OnClickListener ol;


    public ObjectBaseAdapter(Context _context, List<Map<String, Object>> _list) {
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

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
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
