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
	ListView listView = null;
	ListView listView2 = null;
	TextView eventtv = null;
    String [] titles={"浦东酝酿调整13条公交线路","沪7条公交线路拟调整","127路拟撤销","芦恒路枢纽站等6条公交线路调整计划"};  
    String [] texts={"为完善浦东新区市民的出行环境，拟对183路等13条公交线路进行优化调整。目前线路调整计划公示，至2015年10月19日止，欢迎市民多提宝贵意见。",
    		"公交92路、92路B、704路、704路B、莘庄3路、198路、218路拟实施调整。","127路拟全线撤销，新辟一条至轨交2号线的短驳公交12××路，完成“最后一公里”的任务。",
    		"芦恒路枢纽站等的公交线路调整计划》即日起至12.4进行公示。"};  
    
    String [] comtitle = {"27路","121路","91路"};
    String [] comlist = {"27路每天早上8点时是人流量最大的时候，如果提前十分钟过来搭车会少很多",
    		"今天早上遇到的这个司机师傅开始好快，我差点摔倒，希望以后能开的平稳点，如果把老人摔倒就不得了了",
    		"早上好多人在车上吃东西，弄得车里到处是食物的味道，希望以后司机师傅能提醒一下乘客，不要在车里吃东西"};
    int [] resIds={R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coment);
//		CharSequence titleLable = getResources().getString(R.string.notice_com_search);
//	    setTitle(titleLable);
		TabHost th=(TabHost)findViewById(R.id.infotabhost);						
		
		th.setup();            //初始化TabHost容器
		
		//获取预警事件
		eventtv = (TextView) findViewById(R.id.event);
		eventtv.setText("\t20150318事故\n剑川路龙吴路西北约5米\n三鲁公路江文路北约100米\n沪闵路金都路东约00米\n宁虹路出申昆路西约150米\n闸航路出召泰路西约20米"+
		"\n\t20150429事故\n民益路新效路西约2米\n张杨北路衡安路东约1米\n浦明路商城路东北约5米");
		
		
		//在TabHost创建标签，然后设置：标题／图标／标签页布局
		th.addTab(th.newTabSpec("tab1").setIndicator(getResources().getString(R.string.notice),null).setContent(R.id.tab1));
		th.addTab(th.newTabSpec("tab2").setIndicator(getResources().getString(R.string.comment),null).setContent(R.id.tab2));
		th.addTab(th.newTabSpec("tab3").setIndicator(getResources().getString(R.string.Warning),null).setContent(R.id.tab3));
		
		TextView tv0 = (TextView) th.getTabWidget().getChildAt(0).findViewById(android.R.id.title); 
		TextView tv1 = (TextView) th.getTabWidget().getChildAt(1).findViewById(android.R.id.title); 
		TextView tv2 = (TextView) th.getTabWidget().getChildAt(2).findViewById(android.R.id.title); 
        tv0.setTextSize(18);
        tv1.setTextSize(18); 
        tv2.setTextSize(18); 
		
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
        listView2.setAdapter(new ListViewAdapter(comtitle,comlist,resIds,false));
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