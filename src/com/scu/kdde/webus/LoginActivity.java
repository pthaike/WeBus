package com.scu.kdde.webus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    
	private EditText userEditText;
	private EditText pwdEditText;
	private Button loginBtn;
	private Connector connector = null;
	private final String SERVERURL = "192.168.0.108";
	private final int PORT = 80;
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
					String username = userEditText.getText().toString();
					String pwd = pwdEditText.getText().toString();
					checkUser(username, pwd);
//                    if(login()){
//                        Intent intent = new Intent(LoginActivity.this,UserActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    else{
//	                   showDialog("用户名或者密码错误，请重新输入！"); 
//	             }
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
    
    private void checkUser(final String user, final String psw) {
    	boolean logininfo = false;
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Looper.prepare();
				try {
					if(connector == null){
						connector = new Connector();
					}
					boolean contrue = connector.ConnectServer(SERVERURL, PORT);
					if(!contrue||!connector.socket.isConnected()||connector.socket.isClosed()){
						Toast.makeText(LoginActivity.this, "请检查网络", Toast.LENGTH_LONG);
						Looper.loop();
						Looper.myLooper().quit();
					}else{
						connector.out.writeUTF(user);
						connector.out.writeUTF(psw);
						boolean logininfo = connector.in.readBoolean();
						//System.out.println("client_+++++++++++++++++_>"+logininfo);
						if (logininfo == true){
							Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG);
						}else{
							Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				super.run();
			}
			
		}.start();
	}
}