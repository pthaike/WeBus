package com.scu.kdde.webus;

import android.app.Activity;
import android.os.Bundle;

public class UserInfoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo);
		CharSequence titleLable = getResources().getString(R.string.user_info);
	    setTitle(titleLable);
	}

}
