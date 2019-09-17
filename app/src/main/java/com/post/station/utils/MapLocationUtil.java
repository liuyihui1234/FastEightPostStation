package com.post.station.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.post.station.PostStationApplication;
import com.post.station.R;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by zw on 2019/8/16.
 * Discription :
 */
public class MapLocationUtil {

    private Context context;
    protected LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Device_Sensors; // GPS定位
    protected LocationClient locationClient = null;
    public static final int MAPLOCAL_SUCCESS = 11;
    public static final int MAPLOCAL_FAILURE = 22;
    public static final int MAPLOCAL_SEARCH_OK = 33;
    public static final int MAPLOCAL_SEARCH_FAILURE = 44;

    private LocationClientOption option;
    private static MapLocationUtil single;
    private Bitmap bitmap;

    // 静态工厂方法
    public synchronized static MapLocationUtil getInstance() {
        if (single == null) {
            single = new MapLocationUtil(PostStationApplication.getInstance());
        }
        return single;
    }

    public MapLocationUtil(Context context) {
        this.context = context;
        locationClient = new LocationClient(context.getApplicationContext());
        initLocationOption(context);
    }

    // 重新定位
    public void restartLocation() {
        if (locationClient != null) {
            if (locationClient.isStarted()) {
                locationClient.stop();
            }
            if (option != null)
                if (isInternetConnect(context)) {
                    //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
                    option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
                } else {
                    option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
                }
            locationClient.setLocOption(option);
            locationClient.start();
        }
    }

    private void initLocationOption(Context context) {
        option = new LocationClientOption();
        if (isInternetConnect(context)) {
            //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        } else {
            option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        }
        option.setCoorType("bd09ll");//返回百度经纬度坐标——ll是英文字母l
        option.setNeedDeviceDirect(true);//需要手机的方向信息
        option.setScanSpan(2000);//设置定位次数，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setTimeOut(9 * 1000);//10秒定位不到就返回
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(true);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        locationClient.setLocOption(option);
    }

    private boolean isHightAccuracy = true;

    public void setHightAccuracy(boolean hightAccuracy) {
        isHightAccuracy = hightAccuracy;
    }

    private boolean isInternetConnect(Context context) {
        return isHightAccuracy;
    }

    public void getLocalPoint(final BaiduMap baiduMap, final boolean isNeedShowLocation,
                              Action1<LatLng> lngAction1) {//获取当前位置
        if (locationClient == null) {
            locationClient = new LocationClient(context.getApplicationContext());
            initLocationOption(context);
        }
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                Log.e("", "定位成功:" + location.getLatitude() + ":" + location.getLongitude() + "\t" + location.getAddrStr());
                lngAction1.call(latLng);
                if (isNeedShowLocation) {
                    setLocationMark(baiduMap, latLng);//设置定位图标
                }
                getAroundPoi(new LatLng(location.getLatitude(), location.getLongitude()));
            }

        });
        locationClient.start();
    }

    //设置定位点
    public void setLocationMark(BaiduMap baiduMap, LatLng latLng) {
        if (baiduMap == null) return;
        baiduMap.clear();//清除之前的marker
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker_location_red);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        Marker overlay = (Marker) baiduMap.addOverlay(option);
        //设置显示中心点
        MapStatus mapStatus = new MapStatus.Builder(baiduMap.getMapStatus())
                .target(latLng)//设置中心点
                .zoom(17f)//缩放比例
                .overlook(-10f)//俯视角度
                .build();
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        baiduMap.setMapStatus(statusUpdate);//设置地图状态改变
    }

    /**
     * 设置定位点(不清除之前的点)
     *
     * @param baiduMap
     * @param latLng
     * @param zoom     设置地图放大比例
     * @param address
     */
    public void setLocationMark(BaiduMap baiduMap, LatLng latLng, float zoom, boolean isRed, String address, Action1<BaiduMap> action1) {
        if (baiduMap == null) return;
        BitmapDescriptor bitmapDescriptor = null;
        //构建Marker图标
        //构建MarkerOption，用于在地图上添加Marker
        if (isRed) {
            bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker_location_red);
            //设置显示中心点
            MapStatus mapStatus = new MapStatus.Builder(baiduMap.getMapStatus())
                    .target(latLng)//设置中心点
                    .zoom(zoom)//缩放比例
                    .overlook(-10f)//俯视角度
                    .build();
            MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
            baiduMap.setMapStatus(statusUpdate);//设置地图状态改变
        } else
            bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker_location_grey);

//        OverlayOptions option = new MarkerOptions()
//                .position(latLng)
//                .icon(bitmap);
//        //在地图上添加Marker，并显示
//        Marker overlay = (Marker) baiduMap.addOverlay(option);
        //文字覆盖物位置坐标
//        if (action1 != null) action1.call(baiduMap);

        drawBitMap(bitmapDescriptor, address);
        OverlayOptions option = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(bitmap));
        if (baiduMap != null) {
            baiduMap.addOverlay(option);
        }
        gc();
    }

    private void drawBitMap(BitmapDescriptor mIconMarker, String str) {
        float scale = PostStationApplication.getInstance().getResources().getDisplayMetrics().density;
        Log.e("scale", "=" + scale);
        int width = mIconMarker.getBitmap().getWidth(), height = mIconMarker.getBitmap().getHeight();//marker的获取宽高
        //建立一个空的Bitmap
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        bitmap = scaleWithWH(bitmap, width * scale, height * scale);//缩放
        //画笔进行添加文字（强烈推荐启舰的自定义控件三部曲http://blog.csdn.net/harvic880925/article/details/50995268）
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤
        paint.setColor(Color.RED);
        paint.setTextSize(17 * scale);

        Rect bounds = new Rect();
        paint.getTextBounds(str, 0, str.length(), bounds);//获取文字的范围
        //文字在mMarker中展示的位置
        float paddingLeft = (bitmap.getWidth() - bounds.width()) / 2;//在中间
        float paddingTop = (bitmap.getHeight() / scale);//在顶部

        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(str, paddingLeft, paddingTop, paint);

        //合并两个bitmap为一个
        canvas.drawBitmap(mIconMarker.getBitmap(), paddingLeft, paddingTop, null);//marker的位置
    }

    private Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        } else {
            // 记录src的宽高
            int width = src.getWidth();
            int height = src.getHeight();
            // 创建一个matrix容器
            Matrix matrix = new Matrix();
            // 计算缩放比例
            float scaleWidth = (float) (w / width);
            float scaleHeight = (float) (h / height);
            // 开始缩放
            matrix.postScale(scaleWidth, scaleHeight);
            // 创建缩放后的图片
            return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }

    //设置定位点_图标为百度系统自带

    /**
     * @param baiduMap
     * @param latLng
     * @param zoom     地图放大比例
     */
    public void setLocation(BaiduMap baiduMap, LatLng latLng, float zoom) {
        baiduMap.clear();
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(0)
                .direction(0)// 此处设置开发者获取到的方向信息，顺时针0-360
                .latitude(latLng.latitude)
                .longitude(latLng.longitude).build();
        // 设置定位数据
        baiduMap.setMyLocationData(locData);
        MapStatus mapStatus = new MapStatus.Builder(baiduMap.getMapStatus())
                .target(latLng)//设置中心点
                .zoom(zoom)//缩放比例
                .overlook(-10f)//俯视角度
                .build();
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        baiduMap.setMapStatus(statusUpdate);//设置地图状态改变
    }

    public void getAroundPoi(LatLng latLng) {
        // 创建GeoCoder实例对象
        final GeoCoder geoCoder = GeoCoder.newInstance();
        // 设置查询结果监听者
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                    return;
                }
                final List<PoiInfo> poiInfos = result.getPoiList();
                if (poiInfos != null && poiInfos.size() > 0) {
//                    Message message = new Message();
//                    message.what = MAPLOCAL_SEARCH_OK;
//                    message.obj = poiInfos;
//                    handler.sendMessage(message);
                    geoCoder.destroy();
                    stopLocation();
                } else {
//                    Message message = new Message();
//                    message.what = MAPLOCAL_SEARCH_FAILURE;
//                    message.obj = poiInfos;
//                    handler.sendMessage(message);
                }
            }
        });
        //发起地理编码检索
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

    //关闭定位
    public void stopLocation() {
        if (locationClient != null && locationClient.isStarted()) {
            locationClient.stop();
        }
        locationClient = null;
    }

    //是否开启GPS
    public boolean isOpenGPS(Activity activity) {
        LocationManager locManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 设置GPS弹框
     *
     * @param activity
     * @param GPS_REQUEST_CODE
     */
    public void showSetGPSDialog(final Activity activity, final int GPS_REQUEST_CODE) {
        new AlertDialog.Builder(activity)
                .setTitle("定位提示")
                .setMessage("GPS未开启，GPS开启后定位更精确，是否设置GPS？")
                // 拒绝, 退出应用
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.setting,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //跳转GPS设置界面
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                activity.startActivityForResult(intent, GPS_REQUEST_CODE);
                            }
                        })

                .setCancelable(false)
                .show();
    }

    private void gc() {
        if (bitmap != null) {
            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
    }
}