package com.wuliwei.newbilibili.uitls;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by
 * 武立伟
 * 2017/3/28.
 * <p>
 * 作用：
 */
@Entity
public class User {
    @Id
    private String userName;
    private String passWord;



    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Generated(hash = 2134531238)
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
