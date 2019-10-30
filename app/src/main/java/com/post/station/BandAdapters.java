package com.post.station;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.post.station.adapter.BandAdapter;
import com.post.station.response.BandBean;

import java.util.ArrayList;
import java.util.List;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/10/2913:47
 *  作者：wpx
 *  描述：
 */public class BandAdapters extends BaseAdapter {
    private Context mContext;
    private int poins;

    private List<BandBean> mInfo = new ArrayList<>();

    public BandAdapters(Context context, List<BandBean> mInfo) {
        this.mContext = context;
        this.mInfo = mInfo;

    }

    int a = -1;

    public void setSelectFlag(int flag) {
        if (flag == a) {
            return;
        }
        a = flag;
    }

    public void setonclick(int poin) {

        poins = poin;

    }


    public int getCount() {
        return mInfo.size();
    }

    public Object getItem(int i) {
        return mInfo.get(i);
    }


    public long getItemId(int i) {
        return 0;
    }


    /**
     * 情况数据，并将数据添加到开头
     *
     * @param
     */


    public View getView(final int position,View convertView,ViewGroup parent) {
        BandAdapters.DiyHolder diyHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_brand, null);
            diyHolder = new BandAdapters.DiyHolder();
            diyHolder.iv_express = convertView.findViewById(R.id.iv_express);
            diyHolder.tv_name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(diyHolder);

        } else {
            diyHolder = (BandAdapters.DiyHolder) convertView.getTag();
        }
        BandBean bean = mInfo.get(position);
        diyHolder.tv_name.setText(bean.getName());
//        diyHolder.iv_express.setText(bean.getImg());



      /*  if (poins == position) {
            if (diyHolder.ll_brand.getBackground().equals(R.mipmap.right_ic)){
                diyHolder.ll_brand.setBackgroundColor(mContext.getResources().getColor(R.color.white));

            }else {
                diyHolder.ll_brand.setBackgroundResource(R.mipmap.right_ic);
            }

        }*/

        return convertView;
    }


    class DiyHolder {

        //用户名
        TextView tv_name;
        ImageView iv_express;

    }


}
