package com.frame.id;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.frame.activity.IBaseListener;

import java.lang.reflect.Field;

/**
 * 解析注解
 *
 * @version V1.0
 * @Title: InjectUtility.java
 * @Package: com.frame.id
 * @company: byb
 * @author: ollie
 * @date 2015-1-21 下午3:42:17
 */
public class InjectUtility {

    public static void initInjectedView(View sourceActivity, Context context,
                                        IBaseListener ol) {
        initInjectedView(sourceActivity, sourceActivity, context, ol);
    }

    /**
     * 初始化ui的id
     *
     * @param @param injectedSource
     * @param @param sourceView
     * @param @param _context
     * @param @param ol
     * @return void
     * @throws
     * @Title: initInjectedView
     */
    public static void initInjectedView(Object injectedSource, View sourceView,
                                        Context _context, IBaseListener ol) {
        Class<?> clazz = injectedSource.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    ViewInject viewInject = field
                            .getAnnotation(ViewInject.class);
                    if (viewInject != null) {
                        // ViewId可以是id配置，也可以是IdStr配置
                        int viewId = viewInject.id();
                        if (viewId == 0) {
                            String idStr = viewInject.idStr();
                            if (!TextUtils.isEmpty(idStr)) {
                                try {
                                    Context context = _context;
                                    String packageName = context
                                            .getPackageName();
                                    Resources resources = context
                                            .getPackageManager()
                                            .getResourcesForApplication(
                                                    packageName);

                                    viewId = resources.getIdentifier(idStr,
                                            "id", packageName);

                                    if (viewId == 0)
                                        throw new RuntimeException(
                                                String.format(
                                                        "%s 的属性%s关联了id=%s，但是这个id是无效的",
                                                        clazz.getSimpleName(),
                                                        field.getName(), idStr));
                                } catch (Exception e) {
                                    // e.printStackTrace();
                                }
                            }
                        }
                        if (viewId != 0) {
                            try {
                                field.setAccessible(true);
                                /*
                                 * 当已经被赋值时，不在重复赋值，用于include，inflate情景下的viewinject组合
								 */
                                if (field.get(injectedSource) == null) {
                                    field.set(injectedSource,
                                            sourceView.findViewById(viewId));
                                } else {
                                    continue;
                                }
                            } catch (Exception e) {
                            }
                        }

                        String clickMethod = viewInject.click();
                        if (!TextUtils.isEmpty(clickMethod))
                            setViewClickListener(injectedSource, field,
                                    clickMethod, ol);

                        String longClickMethod = viewInject.longClick();
                        if (!TextUtils.isEmpty(longClickMethod))
                            setViewLongClickListener(injectedSource, field,
                                    longClickMethod, ol);

                        String itemClickMethod = viewInject.itemClick();
                        if (!TextUtils.isEmpty(itemClickMethod))
                            setItemClickListener(injectedSource, field,
                                    itemClickMethod, ol);

                        String itemLongClickMethod = viewInject.itemLongClick();
                        if (!TextUtils.isEmpty(itemLongClickMethod))
                            setItemLongClickListener(injectedSource, field,
                                    itemLongClickMethod, ol);

                        Select select = viewInject.select();
                        if (!TextUtils.isEmpty(select.selected()))
                            setViewSelectListener(injectedSource, field,
                                    select.selected(), select.noSelected());

                    }
                }
            }
        }
    }

    private static void setViewClickListener(Object injectedSource,
                                             Field field, String clickMethod, IBaseListener ol) {
        try {
            Object obj = field.get(injectedSource);
            if (obj instanceof View) {
                ((View) obj).setOnClickListener((OnClickListener) ol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setViewLongClickListener(Object injectedSource,
                                                 Field field, String clickMethod, IBaseListener ol) {
        try {
            Object obj = field.get(injectedSource);
            if (obj instanceof View) {
                ((View) obj).setOnLongClickListener((OnLongClickListener) ol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setItemClickListener(Object injectedSource,
                                             Field field, String itemClickMethod, IBaseListener ol) {
        try {
            Object obj = field.get(injectedSource);
            if (obj instanceof AbsListView) {
                ((AbsListView) obj).setOnItemClickListener((OnItemClickListener) ol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setItemLongClickListener(Object injectedSource,
                                                 Field field, String itemClickMethod, IBaseListener ol) {
        try {
            Object obj = field.get(injectedSource);
            if (obj instanceof AbsListView) {
                ((AbsListView) obj)
                        .setOnItemLongClickListener((OnItemLongClickListener) ol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setViewSelectListener(Object injectedSource,
                                              Field field, String select, String noSelect) {
        try {
            Object obj = field.get(injectedSource);
            if (obj instanceof View) {
                ((AbsListView) obj)
                        .setOnItemSelectedListener(new EventListener(
                                injectedSource).select(select).noSelect(
                                noSelect));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
