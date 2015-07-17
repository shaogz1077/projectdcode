package com.frame.activity;

import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * 设置监听点击的类型
 *
 * @version V1.0
 * @Title: IBaseClick.java
 * @Package: com.frame.activity
 * @company: byb
 * @author: ollie
 * @date 2015-6-30 下午3:18:37
 */
public interface IBaseClick extends OnClickListener, IBaseListener,
        OnItemClickListener, OnItemLongClickListener {

}
