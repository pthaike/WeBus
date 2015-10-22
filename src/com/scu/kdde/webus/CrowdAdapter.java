package com.scu.kdde.webus;

import java.util.List;
import java.util.Map;

import com.scu.kdde.webus.TimeLineAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CrowdAdapter extends BaseAdapter{
	private Context context = null;
	private List<Map<String, Object>> list = null;
	private LayoutInflater inflater = null;
	
	public CrowdAdapter(Context context, List<Map<String, Object>> list) {
		super();
		System.out.println("Time= struct=======================>");
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		System.out.println("Time========================>");
		ViewHolder  viewHolder = null;
		if (convertView == null){
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.crowd_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView)convertView.findViewById(R.id.crowdtime);
			viewHolder.showtime = (ProgressBar)convertView.findViewById(R.id.crowdpro);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String titlestr = list.get(position).get("time").toString();
		viewHolder.title.setText(titlestr);
		int pro = Integer.parseInt(list.get(position).get("crowd").toString());
		System.out.println(pro);
		viewHolder.showtime.setProgress( pro);
		return convertView;
	}
	
	static class ViewHolder{
		public TextView title;
		public ProgressBar showtime;
	}
}
