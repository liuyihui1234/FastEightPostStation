package com.post.station.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.post.station.R;
import com.post.station.response.BandBean;

import java.util.ArrayList;
import java.util.List;

public class BandAdapter extends BaseAdapter {
    private Context mContext;
    private int poins;

    private List<BandBean> mInfo = new ArrayList<>();

    public BandAdapter(Context context, List<BandBean> mInfo) {
        this.mContext = context;
        this.mInfo = mInfo;

    }

    public void setonclick(int poin) {
        poins = poin;

    }

    @Override
    public int getCount() {
        return mInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return mInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    /**
     * 情况数据，并将数据添加到开头
     *
     * @param
     */

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DiyHolder diyHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_brand, null);
            diyHolder = new DiyHolder();

            diyHolder.name = convertView.findViewById(R.id.tv_name);
            diyHolder.ll_brand = convertView.findViewById(R.id.ll_brand);
            convertView.setTag(diyHolder);

        }else {
            diyHolder = (DiyHolder)convertView.getTag();
        }
        BandBean bean = mInfo.get(position);
        diyHolder.name.setText(bean.getName());
        if (poins == position) {
            diyHolder.ll_brand.setBackgroundColor(mContext.getResources().getColor(R.color.green_base));
        }
        return convertView;
    }


    private class DiyHolder {

        //用户名
        TextView name;
        LinearLayout ll_brand;

    }

}