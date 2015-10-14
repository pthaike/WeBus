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
    String [] titles={"�ֶ��������13��������·","��7��������·�����","127·�⳷��","«��·��Ŧվ��6��������·�����ƻ�"};  
    String [] texts={"Ϊ�����ֶ���������ĳ��л��������183·��13��������·�����Ż�������Ŀǰ��·�����ƻ���ʾ����2015��10��19��ֹ����ӭ������ᱦ�������",
    		"����92·��92·B��704·��704·B��ݷׯ3·��198·��218·��ʵʩ������","127·��ȫ�߳������±�һ�����콻2���ߵĶ̲�����12����·����ɡ����һ���������",
    		"«��·��Ŧվ�ȵĹ�����·�����ƻ�����������12.4���й�ʾ��"};  
    
    String [] comtitle = {"27·","121·","91·"};
    String [] comlist = {"27·ÿ������8��ʱ������������ʱ�������ǰʮ���ӹ�������ٺܶ�",
    		"�����������������˾��ʦ����ʼ�ÿ죬�Ҳ��ˤ����ϣ���Ժ��ܿ���ƽ�ȵ㣬���������ˤ���Ͳ�������",
    		"���Ϻö����ڳ��ϳԶ�����Ū�ó��ﵽ����ʳ���ζ����ϣ���Ժ�˾��ʦ��������һ�³˿ͣ���Ҫ�ڳ���Զ���"};
    int [] resIds={R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coment);
//		CharSequence titleLable = getResources().getString(R.string.notice_com_search);
//	    setTitle(titleLable);
		TabHost th=(TabHost)findViewById(R.id.infotabhost);						
		
		th.setup();            //��ʼ��TabHost����
		
		//��ȡԤ���¼�
		eventtv = (TextView) findViewById(R.id.event);
		eventtv.setText("\t20150318�¹�\n����·����·����Լ5��\n��³��·����·��Լ100��\n����·��·��Լ00��\n����·������·��Լ150��\nբ��·����̩·��Լ20��"+
		"\n\t20150429�¹�\n����·��Ч·��Լ2��\n���·�ⰲ·��Լ1��\n����·�̳�·����Լ5��");
		
		
		//��TabHost������ǩ��Ȼ�����ã����⣯ͼ�꣯��ǩҳ����
		th.addTab(th.newTabSpec("tab1").setIndicator(getResources().getString(R.string.notice),null).setContent(R.id.tab1));
		th.addTab(th.newTabSpec("tab2").setIndicator(getResources().getString(R.string.comment),null).setContent(R.id.tab2));
		th.addTab(th.newTabSpec("tab3").setIndicator(getResources().getString(R.string.Warning),null).setContent(R.id.tab3));
		
		TextView tv0 = (TextView) th.getTabWidget().getChildAt(0).findViewById(android.R.id.title); 
		TextView tv1 = (TextView) th.getTabWidget().getChildAt(1).findViewById(android.R.id.title); 
		TextView tv2 = (TextView) th.getTabWidget().getChildAt(2).findViewById(android.R.id.title); 
        tv0.setTextSize(18);
        tv1.setTextSize(18); 
        tv2.setTextSize(18); 
		
		TabWidget tabWidget = th.getTabWidget();                                //��ȡTabHost��ͷ�� 
        
        for (int i=0; i<tabWidget.getChildCount(); i++){                         //ѭ��ÿ��tabView
            View view = tabWidget.getChildAt(i); 
            //��ȡtabView��   
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
              
            // ʹ��View�Ķ���itemView��R.layout.item����  
            View itemView = inflater.inflate(R.layout.listview_item, null);  
              
            // ͨ��findViewById()����ʵ��R.layout.item�ڸ����  
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