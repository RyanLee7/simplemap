package com.ryan.simplemap;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

public class MainActivity extends Activity {

	private static final String LOGTAG = "MainActivity";
	// 百度KEY D84B4BF973B9DD19708F94454B0FA9192209D122
	private String key = "D84B4BF973B9DD19708F94454B0FA9192209D122";
	private String content;// 用户输入的内容
	// 地图相关
	private BMapManager mBMapMan;
	private MapView mMapView;
	private MapController mMapController;
	private MKSearch mkSearch;
	private static LocationData locationData = new LocationData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 注意：请在setContentView前初始化BMapManager对象，否则会报错
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(key, null);

		setContentView(R.layout.activity_main);
		initView();
		mMapView = (MapView) findViewById(R.id.bmapView);

		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		mMapController = mMapView.getController();

		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));

		mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别
	}

	private void initView() {

	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}
}
