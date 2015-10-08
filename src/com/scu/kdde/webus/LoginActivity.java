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
        
        // ͨ��findViewById����ʵ�������
        userEditText = (EditText)findViewById(R.id.username_edit);
        // ͨ��findViewById����ʵ�������
        pwdEditText = (EditText)findViewById(R.id.password_edit);
        
        TextView txtViewRegister = (TextView)findViewById(R.id.register_link);
        txtViewRegister.setOnClickListener(new View.OnClickListener() {
        @Override
	        public void onClick(View v) {
	        // TODO Auto-generated method stub
	        /**
	         * ����ע��ҳ��RegisterActivity
	         * */
	          Intent intent =new Intent();  
	   		  intent.setClass(LoginActivity.this, RegisterActivity.class);//logoչʾ�����ת����һ��Activity  
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
	                   showDialog("�û�����������������������룡"); 
	             }
	          }
			}
	      });		
	}
    private boolean validate() {
		// TODO Auto-generated method stub
		String username = userEditText.getText().toString();
		if(username.equals("")){
            showDialog("�û������Ǳ����");
            return false;
        }
        String pwd = pwdEditText.getText().toString();
        if(pwd.equals("")){
            showDialog("�û������Ǳ����");
            return false;
        }
        return true;
	}   
    private boolean login() {
		// TODO Auto-generated method stub
		 String username = userEditText.getText().toString();
	        // �������
	        //MD5���ܺ������ݿ���жԱ�
	     String pwd = pwdEditText.getText().toString();
	        // ��õ�¼���
	     if(username.equals("shanghai") && pwd.equals("shanghai"))
	     {
	    	 return true;
	     }
	     else {
	    	 return false;
	     }
	}
    private void showDialog(String msg){
        AlertDialog builder = new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("ȷ��", null).show();  
    }
}