package com.bmobshare.bean;

import cn.bmob.v3.BmobUser;

/**
 * @author kingofglory email: kingofglory@yeah.net blog: http:www.google.com
 * @date 2014-3-14 TODO
 */

public class User extends BmobUser {

    private String userid;
    private String signature;


    public User() {
        this.setTableName("user_info");
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


}
