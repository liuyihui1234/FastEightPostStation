package com.post.station;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.post.station.adapter.CommonItemAdapter;

import butterknife.ButterKnife;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/11/815:09
 *  作者：wpx
 *  描述：
 */public class TemplateAdapter extends CommonItemAdapter<TemplateBean,
         TemplateAdapter.TemplateHolderView> {

@Override
protected void onBindViewHolder(@NonNull TemplateHolderView holder, @Nullable TemplateBean item) {
        }

@NonNull
@Override
public TemplateHolderView onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(PostStationApplication.getInstance())
        .inflate(R.layout.item_third_party_mould
        , parent, false);
        return new TemplateHolderView(view);
        }


public class TemplateHolderView extends RecyclerView.ViewHolder {

    public TemplateHolderView(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
}
