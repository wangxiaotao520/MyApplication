package com.myapplication.db;

import android.content.Context;

import com.myapplication.BaseApplication;
import com.myapplication.model.Student;

import java.util.List;

/**
 * Description:
 * created by wangxiaotao
 * 2018/6/19 0019 上午 9:17
 */
public class StudentSql  extends  BaseDao<Student> {

    private volatile  static StudentSql studentSql;//多线程访问
    private StudentSql(Context context) {
        super(context);
    }
    /**
     * 使用单例模式获得操作数据库的对象
     * @return
     */
    public  static StudentSql getInstance(){
        StudentSql instance = null;
        if (studentSql==null){
            synchronized (StudentSql.class){
                if (instance==null){
                    instance = new StudentSql(BaseApplication.getContext());
                    studentSql = instance;
                }
            }
        }

        return studentSql;
    }

    public   void clear() {
        deleteAll(Student.class);
    }

    public Student hasLoginUser(){
        Student student=null;
        List<Student> students = QueryAll(Student.class);
        if (students.size()>0){
            student=students.get(0);
        }
        return student;
    }
}
