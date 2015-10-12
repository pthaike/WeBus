package com.scu.kdde.webus;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainTabActivity extends TabActivity implements OnCheckedChangeListener{
	
	public TabHost mTabHost;
	private Intent mAIntent;
	private Intent mBIntent;
	private Intent mCIntent;
	private Intent mDIntent;
	private Intent mEIntent;
	private TextView titletext = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.maintabs);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        titletext = (TextView)findViewById(R.id.titletext);
        this.mAIntent = new Intent(this,RoutePlanDemo.class);
        this.mBIntent = new Intent(this,CrowActivity.class);
        this.mDIntent = new Intent(this,UserActivity.class);
        this.mEIntent = new Intent(this,LoginActivity.class);
        
		((RadioButton) findViewById(R.id.radio_button0))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1))
		.setOnCheckedChangeListener(this);
//        ((RadioButton) findViewById(R.id.radio_button2))
//		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button3))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button4))
		.setOnCheckedChangeListener(this);
        
        //设置tab更改监听器
        mTabHost = getTabHost();
        mTabHost.setOnTabChangedListener(new MyOnTabChangeListener());
        
        setupIntent();
    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			switch (buttonView.getId()) {
			case R.id.radio_button0:
				this.mTabHost.setCurrentTabByTag("A_TAB");
				break;
			case R.id.radio_button1:
				this.mTabHost.setCurrentTabByTag("B_TAB");
				break;
//			case R.id.radio_button2:
//				this.mTabHost.setCurrentTabByTag("C_TAB");
//				break;
			case R.id.radio_button3:
				this.mTabHost.setCurrentTabByTag("D_TAB");
				break;
			case R.id.radio_button4:
				this.mTabHost.setCurrentTabByTag("MORE_TAB");
				break;
			}
		}
	}
	
	private void setupIntent() {
		this.mTabHost = getTabHost();
		TabHost localTabHost = this.mTabHost;

		localTabHost.addTab(buildTabSpec("A_TAB", R.string.line,
				R.drawable.line, this.mAIntent));

		localTabHost.addTab(buildTabSpec("B_TAB", R.string.mapsearch,
				R.drawable.mapsearch, this.mBIntent));

//		localTabHost.addTab(buildTabSpec("C_TAB",
//				R.string.iccard, R.drawable.iccard,
//				this.mCIntent));

		localTabHost.addTab(buildTabSpec("D_TAB", R.string.user,
				R.drawable.user, this.mDIntent));

		localTabHost.addTab(buildTabSpec("MORE_TAB", R.string.other,
				R.drawable.bus, this.mEIntent));

	}
	
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
				getResources().getDrawable(resIcon)).setContent(content);
	}
	
	private class MyOnTabChangeListener implements OnTabChangeListener{

		@Override
		public void onTabChanged(String tabId) {
			// TODO Auto-generated method stub
			mTabHost = getTabHost();
			TabHost localTabHost = mTabHost;
			TabWidget tabWidget = localTabHost.getTabWidget();
			for(int i = 0; i < tabWidget.getTabCount(); i++)
			{
				View vw = tabWidget.getChildAt(i);
				if (localTabHost.getCurrentTab() == i)
				{
					switch(i){
					case 0:
						titletext.setText("路线查询");
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.user_b));
						break;
					case 1:
						titletext.setText("公交查询");
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.user_b));
						break;
					case 2:
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.iccard_b));
						break;
					case 3:
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.user_b));
						break;
					case 4:
						titletext.setText("用户信息");
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.bus_b));
						break;
					}
				}else{
					switch(i){
					case 0:
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.line));
						break;
					case 1:
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.mapsearch));
						break;
					case 2:
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.iccard));
						break;
					case 3:
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.user));
						break;
					case 4:
						vw.setBackgroundDrawable(getResources().getDrawable(R.drawable.bus));
						break;
					}
				}
			}
		}
		
	}
}