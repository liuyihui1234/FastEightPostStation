package com.post.station;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.post.station.response.BandBean;

import java.util.ArrayList;
import java.util.List;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/11/2516:53
 *  作者：wpx
 *  描述：
 */public class BrandManagementAdapter extends BaseAdapter {
    private Context mContext;
    private int poins;

    private List<BandBean> mInfo = new ArrayList<>();

    public BrandManagementAdapter(Context context, List<BandBean> mInfo) {
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
        BrandManagementAdapter.DiyHolder diyHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_brand_management, null);
            diyHolder = new BrandManagementAdapter.DiyHolder();


            diyHolder.tv_name = convertView.findViewById(R.id.tv_name);
            diyHolder.iv_express = convertView.findViewById(R.id.iv_express);
            convertView.setTag(diyHolder);

        } else {
            diyHolder = (BrandManagementAdapter.DiyHolder) convertView.getTag();
        }
        BandBean bean = mInfo.get(position);
        // 写活的setText diyHolder.tv_time.setText(bean.getTime());
        //写死的数据setTag

        diyHolder.tv_name.setText(bean.getName());
        //error错误显示
        Glide.with(mContext).load("http://pic18.nipic.com/20120103/8993051_170340691334_2.jpg").error(R.drawable.sto).into(diyHolder.iv_express);
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


        TextView tv_name;
       ImageView iv_express;


    }

}

