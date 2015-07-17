package com.frame.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;

import com.frame.activity.IBaseListener;
import com.frame.adapter.IAdapter;

/**
 * 公共的view
 *
 * @version V1.0
 * @Title: BaseView.java
 * @Package: com.frame.view
 * @company: byb
 * @author: ollie
 * @date 2015-1-21 下午4:19:25
 */
public class BaseView extends FrameLayout implements IBaseView {
    public LayoutInflater layoutInflater;
    public View view;
    public Context context;
    public OnClickListener ol;//控件点击监听
    public OnItemClickListener oil;//列表点击监听
    public OnItemLongClickListener oill;//列表长按监听

    public BaseView(Context _context, int layoutId) {
        super(_context);
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view = (View) inflate(_context, layoutId, null);
        addView(view, lp);
        this.context = _context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void setListener(IBaseListener _ol) {
        ol = (OnClickListener) _ol;
        oil = (OnItemClickListener) _ol;
        oill = (OnItemLongClickListener) _ol;

    }

    @Override
    public void setAdapter(IAdapter adapter) {

    }

}
