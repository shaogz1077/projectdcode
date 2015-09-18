package com.bmobshare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.bmobshare.bean.Content;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "32c2380ba818c531e2f0729e1a583fd4");
        Content content = new Content();
        content.setContent("djdhhdhhd");
        content.setUser("ddddd");
        content.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
//        User user = new User();
//        user.setUsername("n");
//        user.setPassword("pass");
//        user.setEmail("shaogz1077@126.com");
////        user.setSex("n");
//        user.signUp(this, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//
//            }
//        });

    }

}
