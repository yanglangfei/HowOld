package com.util.ylf.howold;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.MKGeneralListener;
import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.baidu.mapapi.radar.RadarUploadInfoCallback;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class LocationActivity extends Activity implements RadarSearchListener, RadarUploadInfoCallback, OnGetPoiSearchResultListener, MKGeneralListener {
    private MapView mapView;
    private BaiduMap baiduMap;
    private RadarSearchManager manager;
    private EditText et_location;
    private int markers[] = {R.drawable.icon_marka, R.drawable.icon_markb, R.drawable.icon_markc, R.drawable.icon_markc, R.drawable.icon_markd, R.drawable.icon_marke, R.drawable.icon_markf, R.drawable.icon_markg, R.drawable.icon_markh, R.drawable.icon_marki, R.drawable.icon_markj};

    private LatLng latLng;
    private BDLocationListener locationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int type = bdLocation.getLocType();
            if (type == BDLocation.TypeNetWorkLocation) {
                String pro = bdLocation.getProvince();
                String city = bdLocation.getCity();
                String adree = bdLocation.getAddrStr();
                float direct = bdLocation.getDirection();
                String dis = bdLocation.getDistrict();
                String floor = bdLocation.getFloor();
                Log.i("111", "loc:" + pro + "   " + city + "  " + adree + "   " + direct + "   " + dis + "   " + floor);
                double lat = bdLocation.getLatitude();
                double lon = bdLocation.getLongitude();
              /*  baiduMap.setMyLocationData(new MyLocationData.Builder().latitude(lat).longitude(lon)
                        .direction(bdLocation.getDirection())
                        .accuracy(bdLocation.getRadius())
                        .build());*/
                latLng = new LatLng(lat, lon);
                MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
                baiduMap.animateMapStatus(update);

                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_center_point);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(latLng)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                baiduMap.addOverlay(option);

            }
        }
    };
    private LocationClient client;
    private PanoramaView panorama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
     //   initPanoramaView();
        setContentView(R.layout.ui_map);
        initView();
    }

    private void initPanoramaView() {
        BMapManager bMapManager=new BMapManager(getApplicationContext());
        bMapManager.init(this);
    }


    public void onSearch(View view) {
        //搜索地理位置
        String location = et_location.getText().toString();
        PoiSearch poiSearch = PoiSearch.newInstance();
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        // 设置检索中心点
        if (latLng != null) {
            option.location(latLng);
            //设置检索的关键字---如超市,酒店,药店等
            option.keyword(location);
            // 检索半径，单位是米
            option.radius(5000);
            //检索页数
            option.pageNum(10);
            poiSearch.searchNearby(option);
            poiSearch.setOnGetPoiSearchResultListener(this);
        }
    }

    private void initView() {
      //  panorama=(PanoramaView)findViewById(R.id.panorama);
        //panorama.setPanorama("0100220000130817164838355J5");
        et_location = (EditText) findViewById(R.id.et_location);
        mapView = (MapView) findViewById(R.id.mapView);
        baiduMap = mapView.getMap();
        //设置地图类型
        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //是否开启交通地图
        baiduMap.setTrafficEnabled(true);
        baiduMap.setMyLocationEnabled(true);
        //是否开启热力图
        baiduMap.setBaiduHeatMapEnabled(false);
        //设置地图Logo在地图右下角
        mapView.setLogoPosition(LogoPosition.logoPostionRightBottom);
        //设置比例尺等级
        baiduMap.setMaxAndMinZoomLevel(1, 2);
        //设置打开室内地图
        baiduMap.setIndoorEnable(true);
        //监听在室内还是室外
        baiduMap.setOnBaseIndoorMapListener(new BaiduMap.OnBaseIndoorMapListener() {
            @Override
            public void onBaseIndoorMapMode(boolean b, MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
                if (b) {
                    //获取室内平面信息
                    ArrayList<String> floors = mapBaseIndoorMapInfo.getFloors();
                    String id = mapBaseIndoorMapInfo.getID();
                    if (floors != null && floors.size() > 0) {
                        for (int i = 0; i < floors.size(); i++) {
                            Log.i("111", ":" + floors.get(i) + "\n");
                        }
                    } else {
                        Log.i("111", "no floors");
                    }


                } else {

                }
            }
        });

        //初始化周边雷达
        manager = RadarSearchManager.getInstance();
        //设置周边雷达监听
        manager.addNearbyInfoListener(this);
        manager.setUserID("");
        //上传当前信息
        RadarUploadInfo radarNearbyInfo = new RadarUploadInfo();
        radarNearbyInfo.comments = "当前信息";
        manager.uploadInfoRequest(radarNearbyInfo);
        //开启自动上传信息
        manager.startUploadAuto(this, 500);

        //构造请求参数，其中centerPt是自己的位置坐标
        LatLng latLng = new LatLng(120, 35);
        RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(latLng).pageNum(1).radius(2000);
        //发起查询请求
        manager.nearbyInfoRequest(option);


        client = new LocationClient(this);

        LocationClientOption option1 = new LocationClientOption();
        option1.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option1.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option1.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option1.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option1.setOpenGps(true);//可选，默认false,设置是否使用gps
        option1.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option1.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option1.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option1.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option1.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option1.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        client.setLocOption(option1);
        client.registerLocationListener(locationListener);
        client.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        panorama.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        panorama.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除定位监听
        client.unRegisterLocationListener(locationListener);
        //销毁周边雷达信息
        manager.removeNearbyInfoListener(this);
        manager.clearUserInfo();
        manager.destroy();
        //销毁Map对象
        mapView.onDestroy();
    }

    @Override
    public void onGetNearbyInfoList(RadarNearbyResult radarNearbyResult, RadarSearchError radarSearchError) {
        //获取到周边信息
        if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
            Toast.makeText(LocationActivity.this, "周边检索成功", Toast.LENGTH_SHORT).show();
            List<RadarNearbyInfo> infoArray = radarNearbyResult.infoList;
            for (int i = 0; i < infoArray.size(); i++) {
                RadarNearbyInfo info = infoArray.get(i);
                Log.i("111", "info.comments:" + info.comments);
            }
        } else {
            Log.e("111", "周边检索失败:" + radarSearchError.name());
            Toast.makeText(LocationActivity.this, "周边检索失败:" + radarSearchError.name(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onGetUploadState(RadarSearchError radarSearchError) {
        //监听上传信息
        if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
            Toast.makeText(LocationActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LocationActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onGetClearInfoState(RadarSearchError radarSearchError) {
        //清除用户信息    清除后将不被其他用户检索到

    }

    @Override
    public RadarUploadInfo onUploadInfoCallback() {
        //自动上传信息回调
        RadarUploadInfo uploadInfo = new RadarUploadInfo();
        uploadInfo.comments = "auto upload";
        return uploadInfo;
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        //检索信息结果
        List<PoiInfo> pois = poiResult.getAllPoi();
        if (pois != null) {
            int count;
            if(pois.size()>10){
                count=10;
            }else {
                count=pois.size();
            }
            baiduMap.clear();
            for (int i = 0; i < count; i++) {
                LatLng lng = pois.get(i).location;
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(markers[i]);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions op = new MarkerOptions()
                        .position(lng)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                baiduMap.addOverlay(op);
            }
        }

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        //检索详细信息


    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void onGetPermissionState(int i) {

    }
}
