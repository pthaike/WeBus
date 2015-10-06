package com.scu.kdde.webus;

import java.util.List;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;
	private PoiSearch mSearch = null;
	private BusLineSearch mBusLineSearch = null;
	private BusLineResult route = null;
	private List<String> busLineIDList = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext()); 
		setContentView(R.layout.activity_main);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		
		//公交路线搜索
		mSearch = PoiSearch.newInstance();
		OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
			
			@Override
			public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onGetTransitRouteResult(TransitRouteResult arg0) {
				// TODO Auto-generated method stub
				if (arg0 == null || arg0.error != SearchResult.ERRORNO.NO_ERROR)
					System.out.println("onGetWalkingRouteResult 未找到结果");
				if (arg0.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR){
					//起点或者终点地址有歧义，通过以下接口建议查询
					arg0.getSuggestAddrInfo();
					return;
				}
				if (arg0.error == SearchResult.ERRORNO.NO_ERROR){
					TransitRouteOverlay overlay = new TransitRouteOverlay(mBaiduMap);  
					mBaiduMap.setOnMarkerClickListener(overlay);  
		            overlay.setData(arg0.getRouteLines().get(0));  
		            overlay.addToMap();  
		            overlay.zoomToSpan();  
				}
			}
			
			@Override
			public void onGetDrivingRouteResult(DrivingRouteResult arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMapView.onDestroy();
	}
	
}
