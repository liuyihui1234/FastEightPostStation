package com.post.station.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.post.station.PostStationApplication;
import com.post.station.R;
import com.post.station.response.WaitCheckOutBean;

import butterknife.ButterKnife;

public class SmsStatisticsAdapter extends CommonItemAdapter<WaitCheckOutBean,

        SmsStatisticsAdapter.SmsStatisticsHolderView> {

    @Override
    protected void onBindViewHolder(@NonNull SmsStatisticsHolderView holder, @Nullable WaitCheckOutBean item) {
    }

    @NonNull
    @Override
    public SmsStatisticsHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(PostStationApplication.getInstance())
                .inflate(R.layout.item_sms_statistics
                        , parent, false);
        return new SmsStatisticsHolderView(view);
    }


    public class SmsStatisticsHolderView extends RecyclerView.ViewHolder {

        public SmsStatisticsHolderView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
