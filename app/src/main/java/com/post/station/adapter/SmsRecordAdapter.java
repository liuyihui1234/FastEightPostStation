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

public class SmsRecordAdapter extends CommonItemAdapter<WaitCheckOutBean,

        SmsRecordAdapter.SmsRecordOutHolderView> {

    @Override
    protected void onBindViewHolder(@NonNull SmsRecordOutHolderView holder, @Nullable WaitCheckOutBean item) {
    }

    @NonNull
    @Override
    public SmsRecordOutHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(PostStationApplication.getInstance())
                .inflate(R.layout.item_sms_record
                        , parent, false);
        return new SmsRecordOutHolderView(view);
    }


    public class SmsRecordOutHolderView extends RecyclerView.ViewHolder {

        public SmsRecordOutHolderView(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
