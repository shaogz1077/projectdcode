package com.frame.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * 全局公用View通知Toast
 *
 * @version V1.0
 * @Title: CommonToast.java
 * @company: byb
 * @author: ollie
 * @date 2015-1-29 下午2:01:50
 */
public class CommonToast {
    public static int type = 0;
    public static int yOffset = 0;

    private static Toast toast;

    /**
     * @param @param msg
     * @param @param context
     * @return void
     * @throws
     * @Title: showMessage
     */
    public static void showMessage(CharSequence msg, Context context,
                                   int layoutId) {
        if (context != null) {
            if (type == 0) {
                if (toast == null) {
                    if (type == 0) {
                        toast = Toast
                                .makeText(context, msg, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 50);
                        // 定制的view
                        View view = View.inflate(context, layoutId, null);
                        toast.setView(view);
                    }
                }
                if (toast != null) {
                    toast.show();
                }
            }
        }
    }

}
