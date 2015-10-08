package com.scu.kdde.webus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    
	EditText userEditText;
	EditText pwdEditText;
	Button loginBtn;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        // 通过findViewById方法实例化组件
        userEditText = (EditText)findViewById(R.id.username_edit);
        // 通过findViewById方法实例化组件
        pwdEditText = (EditText)findViewById(R.id.password_edit);
        
        TextView txtViewRegister = (TextView)findViewById(R.id.register_link);
        txtViewRegister.setOnClickListener(new View.OnClickListener() {
        @Override
	        public void onClick(View v) {
	        // TODO Auto-generated method stub
	        /**
	         * 跳到注册页面RegisterActivity
	         * */
	          Intent intent =new Intent();  
	   		  intent.setClass(LoginActivity.this, RegisterActivity.class);//logo展示完毕跳转至另一个Activity  
	   		  startActivity(intent);  
	        }
        });
          
        loginBtn = (Button)findViewById(R.id.signin_button);
        loginBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				if(validate()){
                    if(login()){
                        Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
	                   showDialog("用户名或者密码错误，请重新输入！"); 
	             }
	          }
			}
	      });		
	}
    private boolean validate() {
		// TODO Auto-generated method stub
		String username = userEditText.getText().toString();
		if(username.equals("")){
            showDialog("用户名称是必填项！");
            return false;
        }
        String pwd = pwdEditText.getText().toString();
        if(pwd.equals("")){
            showDialog("用户密码是必填项！");
            return false;
        }
        return true;
	}   
    private boolean login() {
		// TODO Auto-generated method stub
		 String username = userEditText.getText().toString();
	        // 获得密码
	        //MD5加密后与数据库进行对比
	     String pwd = pwdEditText.getText().toString();
	        // 获得登录结果
	     if(username.equals("shanghai") && pwd.equals("shanghai"))
	     {
	    	 return true;
	     }
	     else {
	    	 return false;
	     }
	}
    private void showDialog(String msg){
        AlertDialog builder = new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("确定", null).show();  
    }
}