package com.scu.kdde.webus;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeLineAdapter extends BaseAdapter{

	private Context context = null;
	private List<Map<String, Object>> list = null;
	private LayoutInflater inflater = null;
	
	public TimeLineAdapter(Context context, List<Map<String, Object>> list) {
		super();
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
		ViewHolder  viewHolder = null;
		if (convertView == null){
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.timelist, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView)convertView.findViewById(R.id.title);
			viewHolder.showtime = (TextView)convertView.findViewById(R.id.show_time);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String titlestr = list.get(position).get("title").toString();
		viewHolder.title.setText(titlestr);
		viewHolder.showtime.setText(list.get(position).get("station").toString());
		return convertView;
	}
	
	static class ViewHolder{
		public TextView year;
		public TextView month;
		public TextView title;
		public TextView showtime;
	}
}
