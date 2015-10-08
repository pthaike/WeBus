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
		 
		  // 隐藏android系统的状态栏  
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		   // 隐藏应用程序的标题栏，即当前activity的标题栏 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  
		  setContentView(R.layout.logo);  
		  ImageView imageview =(ImageView) findViewById(R.id.logoimage);  
		  imageview.setImageResource(R.drawable.logo);  
		    // 从浅到深,从百分之10到百分之百          
		 AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);  
		 aa.setDuration(2000);//设置动画时间  
		 imageview.setAnimation(aa);//给image设置动画  
		 aa.setAnimationListener(new AnimationListener() {  
		    
		  public void onAnimationStart(Animation animation) {  
		     
		  }  
		    
		  public void onAnimationRepeat(Animation animation) {  
		  
		  }  
		    
		  public void onAnimationEnd(Animation animation) {  
			  Intent intent =new Intent();  
			  intent.setClass(LogoActivity.this, MainTabActivity.class);//logo展示完毕跳转至另一个Activity  
			  startActivity(intent);  
			  finish();  
		  }  
		 });  
		    
		 }  
	
}
