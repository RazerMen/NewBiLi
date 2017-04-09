package com.wuliwei.newbilibili.view.activity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.share.OnGetShareUrlResultListener;
import com.baidu.mapapi.search.share.PoiDetailShareURLOption;
import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.mapapi.search.share.ShareUrlSearch;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.uitls.overlayutil.BikingRouteOverlay;
import com.wuliwei.newbilibili.uitls.overlayutil.DrivingRouteOverlay;
import com.wuliwei.newbilibili.uitls.overlayutil.PoiOverlay;
import com.wuliwei.newbilibili.uitls.overlayutil.WalkingRouteOverlay;
import com.wuliwei.newbilibili.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BaiDuMapActivity extends BaseActivity {

    @BindView(R.id.bmapView)
    MapView mapView;

    private BaiduMap map;
    private PoiSearch poiSearch;
    private ShareUrlSearch shareUrlSearch;
    private RoutePlanSearch routePlanSearch;
    private GeoCoder geoCoder;

    @Override
    protected void initListener() {
        //检索监听
        poiSearch.setOnGetPoiSearchResultListener(new MyOnGetPoiSearchResultListener());
        //设置分享的监听
        shareUrlSearch.setOnGetShareUrlResultListener(new MyOnGetShareUrlResultListener());
        //创建路线的回调
        routePlanSearch.setOnGetRoutePlanResultListener(new MyOnGetRoutePlanResultListener());
        //地理编码
        geoCoder.setOnGetGeoCodeResultListener(new MyOnGetGeoCodeResultListener());
    }

    @Override
    protected void initData() {
        //获取当前的地图
        map = mapView.getMap();

        //获取POI检索
        poiSearch = PoiSearch.newInstance();

        //初始化分享
        shareUrlSearch = ShareUrlSearch.newInstance();

        //创建路线计划的实例
        routePlanSearch = RoutePlanSearch.newInstance();

        //地理编码
        geoCoder = GeoCoder.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bai_du_map;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //添加图层  卫星地图
                map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.btn2:
                //添加一个几何图形
                LatLng l1 = new LatLng(39.93925, 116.357428);

                LatLng l2 = new LatLng(39.90925, 116.327428);

                LatLng l3 = new LatLng(39.88925, 116.347428);

                //将三个坐标添加到集合中
                List<LatLng> pt = new ArrayList<>();
                pt.add(l1);
                pt.add(l2);
                pt.add(l3);

                OverlayOptions options = new PolygonOptions().points(pt)
                        //2 边宽  和边的颜色  ， fillColor(填充的颜色)
                        .stroke(new Stroke(2, 0xAA00FF00)).fillColor(0xAA00FF00);

                map.addOverlay(options);
                break;
            case R.id.btn3:
                LatLng latLng = new LatLng(39.93925, 116.357428);

                poiSearch.searchNearby(new PoiNearbySearchOption().radius(10000).pageCapacity(10).pageNum(1).location(latLng).keyword("商场"));
                break;
            case R.id.btn4:
                //路线   参数1：城市的编号     参数2：具体地址
                PlanNode stNode = PlanNode.withCityCodeAndPlaceName(132, "回龙观");
                PlanNode endNode = PlanNode.withCityCodeAndPlaceName(132, "西二旗");
                routePlanSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(endNode));
                break;
            case R.id.btn5:
                //将经纬度转找成地址
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(39.946758, 116.423134)));
                break;
            case R.id.btn6:
                onCreate();
                break;
            case R.id.btn7:
                //开启定位图层
                map.setMyLocationEnabled(true);
                break;
        }
    }

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    public void onCreate() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        //注册监听函数
        initLocation();

        //开启定位
        mLocationClient.start();
    }

    /**
     * 设置LocationClient的参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");

        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);

        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);

        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);

        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);

        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);

        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);

        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);

        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);

        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /**
     * 获取定位的信息
     */
    private boolean isFirst = true;

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    //设置精确度
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100)
                    //设置纬度
                    .latitude(location.getLatitude())
                    //设置经度
                    .longitude(location.getLongitude())
                    .build();
            // 设置定位数据
            map.setMyLocationData(locData);

            if (isFirst) {
                isFirst = false; //只会在第一次更新的时候才更新
                LatLng lat = new LatLng(location.getLatitude(),
                        location.getLongitude());
                //第一个参数是坐标，第二个参数是缩放值
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(lat, 13);
                map.animateMapStatus(mapStatusUpdate);
            }
        }
    }

    class MyOnGetGeoCodeResultListener implements OnGetGeoCoderResultListener {

        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            Log.e("TAG", "onGetGeoCodeResult" + geoCodeResult.getLocation().toString());
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            Log.e("TAG", "onGetGeoCodeResult" + reverseGeoCodeResult.getAddress());
        }
    }

    class MyOnGetRoutePlanResultListener implements OnGetRoutePlanResultListener {

        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            //步行路线结果回调
            if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                return;
            }
            map.clear();
            WalkingRouteOverlay overly = new MyWalk(map);
            overly.setData(walkingRouteResult.getRouteLines().get(0));
            overly.addToMap();
            overly.zoomToSpan();
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            //换乘路线结果回调
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
            //跨城公共交通路线结果回调
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            //驾车路线结果回调
            if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                return;
            }
            map.clear();
            DrivingRouteOverlay overlay = new MyDriver(map);
            overlay.setData(drivingRouteResult.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
            //室内路线规划回调
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
            //骑行路线结果回调
            if (bikingRouteResult == null || bikingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                return;
            }
            map.clear();
            BikingRouteOverlay overlay = new MyBinking(map);
            overlay.setData(bikingRouteResult.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

    //创建步行路线覆盖物
    class MyWalk extends WalkingRouteOverlay {

        public MyWalk(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPolylineClick(Polyline polyline) {
            return super.onPolylineClick(polyline);
        }
    }

    //创建架车路线覆盖物
    class MyDriver extends DrivingRouteOverlay {

        public MyDriver(BaiduMap baiduMap) {
            super(baiduMap);
        }
    }

    //创建步行路线覆盖物
    class MyBinking extends BikingRouteOverlay {

        public MyBinking(BaiduMap baiduMap) {
            super(baiduMap);
        }
    }

    class MyOnGetShareUrlResultListener implements OnGetShareUrlResultListener {

        @Override
        public void onGetPoiDetailShareUrlResult(final ShareUrlResult shareUrlResult) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaiDuMapActivity.this, "" + shareUrlResult.getUrl(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onGetLocationShareUrlResult(ShareUrlResult shareUrlResult) {

        }

        @Override
        public void onGetRouteShareUrlResult(ShareUrlResult shareUrlResult) {

        }
    }

    class MyOnGetPoiSearchResultListener implements OnGetPoiSearchResultListener {

        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            //判断是否有结果
            if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Log.v("TAG", "没有找到相关搜索");
                return;
            }

            List<PoiInfo> list = poiResult.getAllPoi();

            for (PoiInfo info : list) {

                Log.i("TAG", "onGetPoiResult: " + info.name);
                //添加系统的标注物
                map.clear();
                MyOverly myOverly = new MyOverly(map);
                myOverly.setData(poiResult);
                myOverly.addToMap();
                myOverly.zoomToSpan(); //自动放大
                map.setOnMarkerClickListener(myOverly);
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            shareUrlSearch.requestPoiDetailShareUrl(new PoiDetailShareURLOption().poiUid(poiDetailResult.getUid()));
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    }

    class MyOverly extends PoiOverlay {
        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */
        public MyOverly(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {
            List<PoiInfo> allPoi = getPoiResult().getAllPoi();
            Toast.makeText(BaiDuMapActivity.this, "" + allPoi.get(i).name, Toast.LENGTH_SHORT).show();
            poiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUid(allPoi.get(i).uid));
            return super.onPoiClick(i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onStop();
        mapView.onResume();
    }
}
