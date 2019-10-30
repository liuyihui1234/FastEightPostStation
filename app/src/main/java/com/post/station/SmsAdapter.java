package com.post.station;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/10/3014:15
 *  作者：wpx
 *  描述：
 */public class SmsAdapter extends BaseAdapter {
    private Context mContext;
    private int poins;
    private List<String> list = new ArrayList<>();

    private List<BandSms> mInfo = new ArrayList<>();

    public SmsAdapter(Context context, List<BandSms> mInfo) {
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
    public View getView(final int position,View convertView,ViewGroup parent) {
        SmsAdapter.DiyHolder diyHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sms_recharge, null);
            diyHolder = new SmsAdapter.DiyHolder();

            diyHolder.tv_number = convertView.findViewById(R.id.tv_number);
            diyHolder.tv_price = convertView.findViewById(R.id.tv_price);
            diyHolder.tv_univalent = convertView.findViewById(R.id.tv_univalent);
            convertView.setTag(diyHolder);

        } else {
            diyHolder = (SmsAdapter.DiyHolder) convertView.getTag();
        }
        BandSms bean = mInfo.get(position);
        // 写活的setText diyHolder.tv_time.setText(bean.getTime());
        //写死的数据setTag
        diyHolder.tv_price.setText(bean.getPrice());
        diyHolder.tv_univalent.setText(bean.getUnivalent());
        diyHolder.tv_number.setText(bean.getNumber());
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

        TextView tv_number;
        TextView tv_price;
        TextView tv_univalent;


    }


}
