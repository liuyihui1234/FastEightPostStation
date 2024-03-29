package com.post.station.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.post.station.CapitalDetailActivity;
import com.post.station.R;
import com.post.station.response.BandBean;
import com.post.station.response.BandBeans;
import com.post.station.response.PenaltyBean;

import java.util.ArrayList;
import java.util.List;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station.adapter
 *  创建时间：2019/10/2310:40
 *  作者：wpx
 *  描述：
 */public class BandsAdapter extends BaseAdapter {
    private Context mContext;
    private int poins;
    private List<String> list = new ArrayList<>();

    private List<BandBeans> mInfo = new ArrayList<>();

    public BandsAdapter(Context context, List<BandBeans> mInfo) {
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
        BandsAdapter.DiyHolder diyHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_postexpress, null);
            diyHolder = new BandsAdapter.DiyHolder();

            diyHolder.tv_content = convertView.findViewById(R.id.tv_content);
            diyHolder.tv_notice = convertView.findViewById(R.id.tv_notice);
            diyHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(diyHolder);

        } else {
            diyHolder = (BandsAdapter.DiyHolder) convertView.getTag();
        }
        BandBeans bean = mInfo.get(position);
        // 写活的setText diyHolder.tv_time.setText(bean.getTime());
        //写死的数据setTag
        diyHolder.tv_time.setTag(bean.getTime());
        diyHolder.tv_notice.setTag(bean.getNotice());
        diyHolder.tv_content.setTag(bean.getContent());
      /*  if (poins == position) {
            if (diyHolder.ll_brand.getBackground().equals(R.mipmap.right_ic)){
                diyHolder.ll_brand.setBackgroundColor(mContext.getResources().getColor(R.color.white));

            }else {
                diyHolder.ll_brand.setBackgroundResource(R.mipmap.right_ic);
            }

        }*/


//        if (a != -1 && position == a) {
//            if (list.size()==0){
//                diyHolder.ll_brand.setBackgroundResource(R.mipmap.right_ic);
//                list.add(""+a);
//            }else {
//                for (int n=0;n<list.size();n++){
//                    if (list.get(n) .equals("" + a) ) {
//                        Log.e("listview", list.get(n)+"getView: "+a );
//                        diyHolder.ll_brand.setBackgroundColor(mContext.getResources().getColor(R.color.white));
//                        list.remove(""+a);
//                    }else {
//                        Log.e("listview", "getView: " );
//                        diyHolder.ll_brand.setBackgroundResource(R.mipmap.right_ic);
//                        list.add(""+a);
//                    }
//                }
//
//            }
//        }

        return convertView;
    }


    private class DiyHolder {

        TextView tv_notice;
        TextView tv_content;
        TextView tv_time;


    }

}