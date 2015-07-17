package com.frame.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 公用Dialog提示框方式
 *
 * @version V1.0
 * @Title: DialogTools.java
 * @author: ollie
 * @date 2015-5-28 上午11:39:50
 */
public class DialogTools extends Dialog {
    private IDialog iDialog;
    private int layoutId;
    private LayoutInflater layoutInfalter;

    public DialogTools(Context context, int theme, IDialog _iDialog,
                       int _layoutId) {
        super(context, theme);
        this.iDialog = _iDialog;
        this.layoutId = _layoutId;
        this.layoutInfalter = LayoutInflater.from(context);
        View view = layoutInfalter.inflate(layoutId, null);
        iDialog.resultDialogView(view);
        setContentView(view);
    }
}
