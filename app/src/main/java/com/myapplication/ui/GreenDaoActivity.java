package com.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.db.StudentSql;
import com.myapplication.model.Student;
import com.myapplication.utils.LogUtils;

public class GreenDaoActivity extends BaseActivity implements View.OnClickListener{

    private Button mAddButton;
    public Button mDeleteButton,mUpdateButton,mQueryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        mAddButton = (Button) findViewById(R.id.addbutton);
        mDeleteButton = (Button) findViewById(R.id.deletebutton);
        mUpdateButton = (Button) findViewById(R.id.uodatebutton);
        mQueryButton = (Button) findViewById(R.id.queryButton);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mAddButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
        mUpdateButton.setOnClickListener(this);
        mQueryButton.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_green_dao;
    }


    @Override
    protected void initIntentData() {
        //useless
    }

    @Override
    protected int getFragmentCotainerId() {
        return 0;
    }

    @Override
    protected void initFragment() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbutton:
                Student student = new Student();
                student.setId((long) 10);
                student.setName("jamy");
                student.setAge(110+0);
                boolean b = StudentSql.getInstance().insertObject(student);
               Log.e("[student]",b+"");
               LogUtils.v("[student]"+b);
                break;
            case R.id.deletebutton:
                StudentSql.getInstance().clear();

                break;
            case R.id.queryButton:
                Student student1 = StudentSql.getInstance().hasLoginUser();
                if (student1!=null){
                    LogUtils.w("[student]"+student1.getName());
                }else {
                    LogUtils.w("[student]"+student1);
                }
                break;
            case R.id.uodatebutton:
                Student student2 = new Student();
                student2.setId((long) 10);
                student2.setName("tom");
                StudentSql.getInstance().updateObject(student2);
                LogUtils.w("[student]"+student2.getName());
                break;
        }
    }
}
