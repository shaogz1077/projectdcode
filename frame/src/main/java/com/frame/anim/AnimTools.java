package com.frame.anim;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * projectdcode
 *
 * @version V1.0
 * @Package: com.frame.anim
 * @company: byb
 * @author: ollie
 * @date 2015/7/30 14:39
 */
public class AnimTools {

    /**
     * @param view 要加动画的view
     * @param type 动画的类型
     * @param x    动画的x
     * @param y
     * @return
     */
    public ObjectAnimator getObjectAimator(View view, String type, float x, float y) {
        ObjectAnimator object = ObjectAnimator.ofFloat(view, type, x, y);
        return object;
    }


}
