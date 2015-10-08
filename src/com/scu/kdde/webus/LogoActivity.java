package com.scu.kdde.webus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class LogoActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {  
		  super.onCreate(savedInstanceState); 
		 
		  // ����androidϵͳ��״̬��  
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		   // ����Ӧ�ó���ı�����������ǰactivity�ı����� this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  
		  setContentView(R.layout.logo);  
		  ImageView imageview =(ImageView) findViewById(R.id.logoimage);  
		  imageview.setImageResource(R.drawable.logo);  
		    // ��ǳ����,�Ӱٷ�֮10���ٷ�֮��          
		 AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);  
		 aa.setDuration(2000);//���ö���ʱ��  
		 imageview.setAnimation(aa);//��image���ö���  
		 aa.setAnimationListener(new AnimationListener() {  
		    
		  public void onAnimationStart(Animation animation) {  
		     
		  }  
		    
		  public void onAnimationRepeat(Animation animation) {  
		  
		  }  
		    
		  public void onAnimationEnd(Animation animation) {  
			  Intent intent =new Intent();  
			  intent.setClass(LogoActivity.this, MainTabActivity.class);//logoչʾ�����ת����һ��Activity  
			  startActivity(intent);  
			  finish();  
		  }  
		 });  
		    
		 }  
	
}
