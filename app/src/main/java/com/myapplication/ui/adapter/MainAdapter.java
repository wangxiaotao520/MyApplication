package com.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myapplication.R;

import java.util.List;

/**
 * Description: 主页Adapter
 * created by wangxiaotao
 * 2018/11/3 0003 下午 5:31
 */
public class MainAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mDatas ;

    public MainAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }



    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.layout_main_item,null);
            viewHolder.tv_name=convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(mDatas.get(position));
        return convertView;
    }

    static class ViewHolder{
        TextView tv_name;
    }
}
