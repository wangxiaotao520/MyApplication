package com.myapplication.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.myapplication.R;
import com.myapplication.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * created by wangxiaotao
 * 2019/8/5 0005 下午 3:29
 */
public class FragmentRecyclerview1 extends BaseFragment {

    private RecyclerView recyclerview;
    private CommonAdapter<String> mAdapter;
    private List<String> mDatas = new ArrayList<>();
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;
    private SmartRefreshLayout refreshLayout;

    @Override
    public void initView(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(false);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));


        for (int i = 0; i < 20; i++)
        {
            mDatas.add("Add:" + i);
        }

        mAdapter = new CommonAdapter<String>(mActivity, R.layout.item_contact, mDatas)
        {
            @Override
            protected void convert(ViewHolder holder, String s, int position)
            {
                holder.setText(R.id.tv_name, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };

        initHeaderAndFooter();
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener()
        {
            @Override
            public void onLoadMoreRequested()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            mDatas.add("Add:" + i);
                        }
                        mLoadMoreWrapper.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });

        recyclerview.setAdapter(mLoadMoreWrapper);
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder,  int position)
            {
                Toast.makeText(mActivity, "pos = " + position, Toast.LENGTH_SHORT).show();
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position)
            {
                return false;
            }
        });

    }

    private void initHeaderAndFooter() {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
    }

    @Override
    public void initIntentData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recyclerview1;
    }
}

