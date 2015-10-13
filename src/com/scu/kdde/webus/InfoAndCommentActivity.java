package com.scu.kdde.webus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
    String [] titles={"����1","����2","����3","����4","����1","����2","����3","����4"};  
    String [] texts={"�ı�����Asdjfkjalsjdklksjdkfljaskdjfklasjlfskdfjkals","�ı�����B","�ı�����C","�ı�����D","�ı�����B","�ı�����C","�ı�����D","weben"};  
    int [] resIds={R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on,R.drawable.checkbox_on};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coment);
		
		TabHost th=(TabHost)findViewById(R.id.infotabhost);						
		
		th.setup();            //��ʼ��TabHost����
		
		//��TabHost������ǩ��Ȼ�����ã����⣯ͼ�꣯��ǩҳ����
		//th.addTab(th.newTabSpec("tab1").setIndicator("����",getResources().getDrawable(R.drawable.checkbox_on)).setContent(R.id.tab1));
		th.addTab(th.newTabSpec("tab1").setIndicator("����",null).setContent(R.id.tab1));
		th.addTab(th.newTabSpec("tab2").setIndicator("����",null).setContent(R.id.tab2));
		
		
		TabWidget tabWidget = th.getTabWidget();                                //��ȡTabHost��ͷ�� 
        
        for (int i=0; i<tabWidget.getChildCount(); i++){                         //ѭ��ÿ��tabView
            View view = tabWidget.getChildAt(i);                                 //��ȡtabView��   
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