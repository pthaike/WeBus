package com.scu.kdde.webus;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserInfoActivity extends Activity{

	private Button changebt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo);
		CharSequence titleLable = getResources().getString(R.string.user_info);
	    setTitle(titleLable);
	    
	    changebt = (Button) findViewById(R.id.user_change);
	    changebt.setOnClickListener(new ChangeOnclickListener());
	}

	private class ChangeOnclickListener implements android.view.View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent =new Intent();  
	   		intent.setClass(UserInfoActivity.this, LoginActivity.class);
	   		startActivity(intent);  
		}
		
	}
}
