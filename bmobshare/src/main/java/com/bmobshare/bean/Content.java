package com.bmobshare.bean;

import cn.bmob.v3.BmobObject;

/**
 * projectdcode
 *
 * @version V1.0
 * @Package: com.bmobshare.bean
 * @company: byb
 * @author: ollie
 * @date 2015/9/18 13:58
 */
public class Content extends BmobObject {

    private String content;
    private String user;

    public Content() {
        this.setTableName("content");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
