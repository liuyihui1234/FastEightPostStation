package com.post.station.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.post.station.PostStationApplication;
import com.post.station.R;
import com.post.station.response.WaitCheckOutBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaitCheckOutAdapter extends CommonItemAdapter<WaitCheckOutBean,

        WaitCheckOutAdapter.WaitCheckOutHolderView> {

    @Override
    protected void onBindViewHolder(@NonNull WaitCheckOutHolderView holder, @Nullable WaitCheckOutBean item) {
    }

    @NonNull
    @Override
    public WaitCheckOutHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(PostStationApplication.getInstance())
                .inflate(R.layout.item_wait_checkout
                        , parent, false);
        return new WaitCheckOutHolderView(view);
    }


    public class WaitCheckOutHolderView extends RecyclerView.ViewHolder {

        public WaitCheckOutHolderView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
