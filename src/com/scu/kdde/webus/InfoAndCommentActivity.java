package com.scu.kdde.webus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class InfoAndCommentActivity extends Activity {
	ListView listView;
	ListView listView2;
    String [] titles={"标题1","标题2","标题3","标题4","标题1","标题2","标题3","标题4"};  
    String [] texts={"文本内容Asdjfkjalsjdklksjdkfljaskdjfklasjlfskdfjkals","文本内容B","文本内容C","文本内容D","文本内容B","文本内容C","文本内容D","weben"};  
    int [] resIds={R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coment);
//		CharSequence titleLable = getResources().getString(R.string.notice_com_search);
//	    setTitle(titleLable);
		TabHost th=(TabHost)findViewById(R.id.infotabhost);						
		
		th.setup();            //初始化TabHost容器
		
		//在TabHost创建标签，然后设置：标题／图标／标签页布局
		//th.addTab(th.newTabSpec("tab1").setIndicator("公告",getResources().getDrawable(R.drawable.checkbox_on)).setContent(R.id.tab1));
		th.addTab(th.newTabSpec("tab1").setIndicator(getResources().getString(R.string.notice),null).setContent(R.id.tab1));
		th.addTab(th.newTabSpec("tab2").setIndicator(getResources().getString(R.string.comment),null).setContent(R.id.tab2));
		
		TextView tv0 = (TextView) th.getTabWidget().getChildAt(0).findViewById(android.R.id.title); 
		TextView tv1 = (TextView) th.getTabWidget().getChildAt(1).findViewById(android.R.id.title); 
        tv0.setTextSize(20); 
        tv1.setTextSize(20); 
		
		TabWidget tabWidget = th.getTabWidget();                                //获取TabHost的头部 
        
        for (int i=0; i<tabWidget.getChildCount(); i++){                         //循环每个tabView
            View view = tabWidget.getChildAt(i); 
            //获取tabView项   
            view.setBackgroundResource(R.drawable.menu_selector);      
            //view.setBackgroundColor("#E3E3E3");
         }

        
		listView=(ListView)this.findViewById(R.id.MyListView); 
		listView2=(ListView)this.findViewById(R.id.PListView);
        listView.setAdapter(new ListViewAdapter(titles,texts,resIds,true));
        listView2.setAdapter(new ListViewAdapter(titles,texts,resIds,false));
	}
	class ListViewAdapter extends BaseAdapter{  
        View [] itemViews;  
          
        public ListViewAdapter(String [] itemTitles, String [] itemTexts,  
                int [] itemImageRes,boolean flag){  
            itemViews = new View[itemTitles.length];  
            

	        for (int i=0; i<itemViews.length; ++i){  
	            itemViews[i] = makeItemView(itemTitles[i], itemTexts[i],   
	                    itemImageRes[i],flag);  
	        }  
    
        }

        public int getCount()   {  
            return itemViews.length;  
        }  
          
        public View getItem(int position)   {  
            return itemViews[position];  
        }  
          
        public long getItemId(int position) {  
            return position;  
        }  
          
        private View makeItemView(String strTitle, String strText, int resId,boolean flag) {  
            LayoutInflater inflater = (LayoutInflater)InfoAndCommentActivity.this  
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
              
            // 使用View的对象itemView与R.layout.item关联  
            View itemView = inflater.inflate(R.layout.listview_item, null);  
              
            // 通过findViewById()方法实例R.layout.item内各组件  
            TextView title = (TextView)itemView.findViewById(R.id.itemTitle);  
            title.setText(strTitle);  
            TextView text = (TextView)itemView.findViewById(R.id.itemText);  
            text.setText(strText);
            if(flag){
            	ImageView image = (ImageView)itemView.findViewById(R.id.itemImage);  
            	image.setImageResource(resId);  
            }
            return itemView;  
        }  
          
        public View getView(int position, View convertView, ViewGroup parent) {  
            if (convertView == null)  
                return itemViews[position];  
            return convertView;  
        }  
	}
}