package com.myapplication.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;

/**
 * Description:
 * created by wangxiaotao
 * 2019/8/5 0005 上午 10:14
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
        recyclerViewHolder.tv_name.setText("张三");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {


        TextView tv_name;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);

        }
    }
}
