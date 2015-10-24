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
 * 此demo用来展示如何进行公交线路详情检索，并使用RouteOverlay在地图上绘制 同时展示如何浏览路线节点并弹出泡泡
 */
public class CrowActivity extends Activity implements
	OnGetPoiSearchResultListener, OnGetBusLineSearchResultListener {

	private ListView listView = null;
	private ListView corwdlistview = null;
	private List<String> data = null;
	private TimeLineAdapter timeLineAdapter = null;
	private CrowdAdapter crowdAdapter = null;
	private EditText cityEdit = null;
	private EditText routeEdit = null;
	private Button searchbt = null;
	private PoiSearch mSearch = null;
	private BusLineSearch mBusLineSearch = null;
	private List<String> busLineIDList = null;
	private int busLineIndex = 0;
	private BusLineResult route = null;
	private List<Map<String, Object>> list = null;
	private List<Map<String, Object>> crowlist = null;
	private int[] crowdpro = {34, 93, 100, 53, 32, 34, 39, 39, 39, 55, 55, 76, 60, 34, 27, 27, 20, 0, 6};
	private int[] crowdstation = {30, 45, 48, 60, 43, 34, 39, 39, 39, 55, 55, 76, 60, 34, 27, 27, 20, 0, 6};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crowline);
		
		cityEdit = (EditText)findViewById(R.id.city);
		routeEdit = (EditText)findViewById(R.id.searchkey);
		searchbt = (Button)findViewById(R.id.search);
		listView = (ListView) this.findViewById(R.id.lv_list);
		corwdlistview = (ListView) findViewById(R.id.crow_list);
		
		mSearch = PoiSearch.newInstance();
		mSearch.setOnGetPoiSearchResultListener(this);
		mBusLineSearch = BusLineSearch.newInstance();
		mBusLineSearch.setOnGetBusLineSearchResultListener(this);
		
		busLineIDList = new ArrayList<String>();
		//getCrowdTime();
		listView.setDividerHeight(0);
		corwdlistview.setDividerHeight(0);
	}
	
	private void getCrowdTime(){
		Map<String, Object> map = null;
		
		for(int i = 6; i<24; i++){
			map = new HashMap<String, Object>();
			//map.put("crowdtime", i+"~"+(i+1));
			//map.put("crowdpro", crowdpro[i-6]+"");
			map.put("time", i+":00~"+(i+1)+":00");
			map.put("crowd", crowdpro[i-6]+"");
			System.out.println(crowdpro[i-6]);
			crowlist.add(map);
		}
	}
	
	public void searchButtonProcess(View v) {
		busLineIDList.clear();
		busLineIndex = 0;
		EditText editCity = (EditText) findViewById(R.id.city);
		EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
		// 发起poi检索，从得到所有poi中找到公交线路类型的poi，再使用该poi的uid进行公交详情搜索
		mSearch.searchInCity((new PoiCitySearchOption()).city(
				editCity.getText().toString()).keyword(
				editSearchKey.getText().toString()));
	}
	
	//当执行searchBusLine时会出发事件
	@Override
	public void onGetBusLineResult(BusLineResult result) {
		// TODO Auto-generated method stub
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(CrowActivity.this, "抱歉，未找到结果",
					Toast.LENGTH_LONG).show();
			return;
		}
		route = result;

		Map<String, Object> map = null;
		list = new ArrayList<Map<String, Object>>();
		for(int i= 0; i<route.getStations().size(); i++){
			map = new HashMap<String, Object>();
			map.put("station", route.getStations().get(i).getTitle());
			map.put("crowd", crowdstation[i]);
			list.add(map);
			//System.out.println("route.getStations()====>"+route.getStations().get(i).getTitle());
		}
		timeLineAdapter = new TimeLineAdapter(this, list);
		listView.setAdapter(timeLineAdapter);
		crowlist = new ArrayList<Map<String, Object>>();
		getCrowdTime();
		//crowdAdapter = new CrowdAdapter(this, crowdlist);
		crowdAdapter = new CrowdAdapter(this, crowlist);
		corwdlistview.setAdapter(crowdAdapter);
		Toast.makeText(CrowActivity.this, result.getBusLineName(),
				Toast.LENGTH_SHORT).show(); //获取每个点信息
	}

	@Override
	public void onGetPoiDetailResult(PoiDetailResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onGetPoiResult(PoiResult result) {
		// TODO Auto-generated method stub
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(CrowActivity.this, "抱歉，未找到结果",
					Toast.LENGTH_LONG).show();
			return;
		}
		// 遍历所有poi，找到类型为公交线路的poi
		busLineIDList.clear();
		for (PoiInfo poi : result.getAllPoi()) {
			if (poi.type == PoiInfo.POITYPE.BUS_LINE
					|| poi.type == PoiInfo.POITYPE.SUBWAY_LINE) {
				busLineIDList.add(poi.uid);
			}
		}
		//System.out.println("onGetPoiResult++busLineIDList.size()"+busLineIDList.size());
		if(busLineIDList.size()>0){
			//根据uid查询每条公交详情
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
