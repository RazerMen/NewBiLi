package com.wuliwei.newbilibili.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by
 * 武立伟
 * 2017/3/28.
 * <p>
 * 作用：
 */
@Entity
public class GoodsBean implements Serializable {
    @Id
    private Long id;
    private String title;
    private String price;
    private String img;
    private int number = 1;
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1973765055)
    public GoodsBean(Long id, String title, String price, String img, int number) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.img = img;
        this.number = number;
    }
    @Generated(hash = 1806305570)
    public GoodsBean() {
    }

   
}
