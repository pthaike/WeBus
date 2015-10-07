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
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;
	private PoiSearch mPoiSearch = null;
	private BusLineSearch mBusLineSearch = null;
	private BusLineResult route = null;
	private List<String> busLineIDList = null;
	private RoutePlanSearch mroutePlanSearch = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext()); 
		setContentView(R.layout.activity_main);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		
		
		//����·������
//		mroutePlanSearch = RoutePlanSearch.newInstance();
//		OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
//			
//			@Override
//			public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("+++++++++++++++++>walk");
//			}
//			
//			@Override
//			public void onGetTransitRouteResult(TransitRouteResult result) {
//				// TODO Auto-generated method stub
//				System.out.println("==================================================================================================================================>");
//				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR)
//					System.out.println("onGetWalkingRouteResult δ�ҵ����");
//				if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR){
//					//�������յ��ַ�����壬ͨ�����½ӿڽ����ѯ
//					//arg0.getSuggestAddrInfo();
//					System.out.println("========================================================================================================================>��������");
//					return;
//				}
//				if (result.error == SearchResult.ERRORNO.NO_ERROR){
//					System.out.println("+++++++++++++++++>result.error == SearchResult.ERRORNO.NO_ERROR");
//					TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaiduMap);  
//					mBaiduMap.setOnMarkerClickListener(overlay);  
//		            overlay.setData(result.getRouteLines().get(0));  
//		            overlay.addToMap();  
//		            overlay.zoomToSpan();  
//		            System.out.println("=====>"+result);
//				}
//			}
//			
//			@Override
//			public void onGetDrivingRouteResult(DrivingRouteResult arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("+++++++++++++++++>drive");
//			}
//		};
//		//���ü�����
//		mroutePlanSearch.setOnGetRoutePlanResultListener(listener);
//		//׼���������յ�
//		PlanNode stNode = PlanNode.withCityNameAndPlaceName("����", "����");
//		PlanNode enNode = PlanNode.withCityNameAndPlaceName("����", "����");
//		System.out.println("==================================================================================================================================>"+stNode);
//		//���м���
//		boolean flag = mroutePlanSearch.transitSearch(new TransitRoutePlanOption().from(stNode).city("����").to(enNode));
//		
//		System.out.println("+++++++++++++++++>"+flag);
//		//����
//		mroutePlanSearch.destroy();
		
	}

	private class MyTransitRouteOverlay extends TransitRouteOverlay{

		public MyTransitRouteOverlay(BaiduMap baidumap) {
			super(baidumap);
			// TODO Auto-generated constructor stub
		}
		
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
