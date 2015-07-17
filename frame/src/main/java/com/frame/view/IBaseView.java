package com.frame.view;

import com.frame.activity.IBaseListener;
import com.frame.adapter.IAdapter;

/**
 * 设置view中的监听和界面的显示
 *
 * @version V1.0
 * @Title: IBaseView.java
 * @Package: com.frame.view
 * @company: byb
 * @author: ollie
 * @date 2015-6-30 下午1:52:49
 */
public interface IBaseView {
    /**
     * 设置控件的监听
     *
     * @param @param ol
     * @return void
     * @throws
     * @Title: setListener
     */
    void setListener(IBaseListener ol);

    /**
     * 适配器的显示
     *
     * @param @param adapter
     * @return void
     * @throws
     * @Title: setAdapter
     */
    void setAdapter(IAdapter adapter);
}
