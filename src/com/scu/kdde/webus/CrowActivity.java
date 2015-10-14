package com.scu.kdde.webus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * ��demo����չʾ��ν��й�����·�����������ʹ��RouteOverlay�ڵ�ͼ�ϻ��� ͬʱչʾ������·�߽ڵ㲢��������
 */
public class CrowActivity extends Activity implements
	OnGetPoiSearchResultListener, OnGetBusLineSearchResultListener {

	private ListView listView = null;
	private List<String> data = null;
	private TimeLineAdapter timeLineAdapter = null;
	private EditText cityEdit = null;
	private EditText routeEdit = null;
	private Button searchbt = null;
	private PoiSearch mSearch = null;
	private BusLineSearch mBusLineSearch = null;
	private List<String> busLineIDList = null;
	private int busLineIndex = 0;
	private BusLineResult route = null;
	private List<Map<String, Object>> list = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crowline);
		
		//��������
//		CharSequence titleLable = getResources().getString(R.string.Bus_cowd_search);
//	    setTitle(titleLable);
		cityEdit = (EditText)findViewById(R.id.city);
		routeEdit = (EditText)findViewById(R.id.searchkey);
		searchbt = (Button)findViewById(R.id.search);
		listView = (ListView) this.findViewById(R.id.lv_list);
		
		mSearch = PoiSearch.newInstance();
		mSearch.setOnGetPoiSearchResultListener(this);
		mBusLineSearch = BusLineSearch.newInstance();
		mBusLineSearch.setOnGetBusLineSearchResultListener(this);
		
		busLineIDList = new ArrayList<String>();
		
		listView.setDividerHeight(0);
//		timeLineAdapter = new TimeLineAdapter(this, getData());
//		listView.setAdapter(timeLineAdapter);
	}
	
	public void searchButtonProcess(View v) {
		busLineIDList.clear();
		busLineIndex = 0;
		EditText editCity = (EditText) findViewById(R.id.city);
		EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
		// ����poi�������ӵõ�����poi���ҵ�������·���͵�poi����ʹ�ø�poi��uid���й�����������
		mSearch.searchInCity((new PoiCitySearchOption()).city(
				editCity.getText().toString()).keyword(
				editSearchKey.getText().toString()));
	}
	
//	private List<Map<String, Object>> getData(){
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("title", "��һ������");
//		list.add(map);
//		
//		map = new HashMap<String, Object>();
//		map.put("title", "��2������");
//		list.add(map);
//		
//		map = new HashMap<String, Object>();
//		map.put("title", "��3������");
//		list.add(map);
//		
//		map = new HashMap<String, Object>();
//		map.put("title", "��4������");
//		list.add(map);
//		
//		return list;
//		
//	}

	//��ִ��searchBusLineʱ������¼�
	@Override
	public void onGetBusLineResult(BusLineResult result) {
		// TODO Auto-generated method stub
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(CrowActivity.this, "��Ǹ��δ�ҵ����",
					Toast.LENGTH_LONG).show();
			return;
		}
		route = result;
		//System.out.println("BusLineName====>"+result.getBusLineName());
//		overlay.removeFromMap();
//		overlay.setData(result);
//		overlay.addToMap();
//		overlay.zoomToSpan();
		Map<String, Object> map = null;
		list = new ArrayList<Map<String, Object>>();
		for(int i= 0; i<route.getStations().size(); i++){
			map = new HashMap<String, Object>();
			map.put("station", route.getStations().get(i).getTitle());
			map.put("title", i);
			list.add(map);
			//System.out.println("route.getStations()====>"+route.getStations().get(i).getTitle());
		}
		timeLineAdapter = new TimeLineAdapter(this, list);
		listView.setAdapter(timeLineAdapter);
		Toast.makeText(CrowActivity.this, result.getBusLineName(),
				Toast.LENGTH_SHORT).show(); //��ȡÿ������Ϣ
	}

	@Override
	public void onGetPoiDetailResult(PoiDetailResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetPoiResult(PoiResult result) {
		// TODO Auto-generated method stub
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(CrowActivity.this, "��Ǹ��δ�ҵ����",
					Toast.LENGTH_LONG).show();
			return;
		}
		// ��������poi���ҵ�����Ϊ������·��poi
		busLineIDList.clear();
		for (PoiInfo poi : result.getAllPoi()) {
			if (poi.type == PoiInfo.POITYPE.BUS_LINE
					|| poi.type == PoiInfo.POITYPE.SUBWAY_LINE) {
				busLineIDList.add(poi.uid);
				//System.out.println("poi.uid+++++++>"+poi.uid);
			}
		}
		System.out.println("onGetPoiResult++busLineIDList.size()"+busLineIDList.size());
		if(busLineIDList.size()>0){
			//����uid��ѯÿ����������
			mBusLineSearch.searchBusLine((new BusLineSearchOption()
					.city(((EditText) findViewById(R.id.city)).getText()
							.toString()).uid(busLineIDList.get(0))));
		}
		route = null;
	}
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mSearch.destroy();
		mBusLineSearch.destroy();
		super.onDestroy();
	}
}
