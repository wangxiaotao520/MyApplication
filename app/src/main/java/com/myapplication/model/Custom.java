package com.myapplication.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:
 * created by wangxiaotao
 * 2018/6/15 0015 下午 5:33
 */
@Entity
public class Custom {

    @Id
    private Long id;
    private String name;
    private int age;
    private String num;
    @Generated(hash = 275670002)
    public Custom(Long id, String name, int age, String num) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.num = num;
    }
    @Generated(hash = 62298964)
    public Custom() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
}
