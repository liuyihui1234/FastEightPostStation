package com.post.station;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.post.station.adapter.BandsAdapter;
import com.post.station.response.BandBeans;

import java.util.ArrayList;
import java.util.List;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/11/614:42
 *  作者：wpx
 *  描述：
 */public class StorageRecordAdapter extends BaseAdapter {
    private Context mContext;
    private int poins;
    private List<String> list = new ArrayList<>();

    private List<StorageRecordBean> mInfo = new ArrayList<>();

    public StorageRecordAdapter(Context context, List<StorageRecordBean> mInfo) {
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
        StorageRecordAdapter.DiyHolder diyHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_storage_record, null);
            diyHolder = new StorageRecordAdapter.DiyHolder();

            diyHolder.tv_consignee = convertView.findViewById(R.id.tv_consignee);
            diyHolder.tv_from_city = convertView.findViewById(R.id.tv_from_city);
            diyHolder.tv_name = convertView.findViewById(R.id.tv_name);
            diyHolder.tv_sending_center = convertView.findViewById(R.id.tv_sending_center);
            diyHolder.tv_to_city = convertView.findViewById(R.id.tv_to_city);
            diyHolder.tv_tracking_number = convertView.findViewById(R.id.tv_tracking_number);
            diyHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(diyHolder);

        } else {
            diyHolder = (StorageRecordAdapter.DiyHolder) convertView.getTag();
        }
        StorageRecordBean bean = mInfo.get(position);
        // 写活的setText diyHolder.tv_time.setText(bean.getTime());
        //写死的数据setTag
        diyHolder.tv_time.setTag(bean.getTime());
        diyHolder.tv_name.setTag(bean.getName());
        diyHolder.tv_tracking_number.setTag(bean.getNumber());
        diyHolder.tv_to_city.setTag(bean.getTocity());
        diyHolder.tv_sending_center.setTag(bean.getSendingcenter());
        diyHolder.tv_from_city.setTag(bean.getFromcity());
        diyHolder.tv_consignee.setTag(bean.getConsignee());
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

        TextView tv_tracking_number;
        TextView tv_from_city;
        TextView tv_to_city;
        TextView tv_sending_center;
        TextView tv_name;
        TextView tv_consignee;
        TextView tv_time;

    }

}
