package com.ytpdw.fun.login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.ytodw.R;

public class LoginActivity extends Activity {
    //    static {
//        System.loadLibrary("hello");
//    }
//
//    private TextView textView;
//
//    public native String getStringName();
    ProgressBar indeterminateProgressLarge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        textView = (TextView) findViewById(R.id.textView);
//        textView.setText(getStringName());
//        indeterminateProgressLarge = (ProgressBar) findViewById(R.id.indeterminate_progress_large_library);
//        indeterminateProgressLarge.setIndeterminateDrawable(
//                new IndeterminateProgressDrawable(this));
    }
}
