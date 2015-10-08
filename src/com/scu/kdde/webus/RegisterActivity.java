package com.scu.kdde.webus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	Button registerBtn;
	EditText username;
	EditText user;
	EditText psw;
	EditText rpsw;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        username = (EditText)findViewById(R.id.register_username_edit);
        user = (EditText)findViewById(R.id.register_user_edit);
        psw = (EditText)findViewById(R.id.register_password_edit);
        rpsw = (EditText)findViewById(R.id.register_rpassword_edit);
        
        registerBtn = (Button)findViewById(R.id.register_button);
        registerBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				if(validate()){
					showDialog("注册成功！");
				}
				else{
					showDialog("注册失败！");
				}
			}
	    });		
    }
    
    private boolean validate() {
		// TODO Auto-generated method stub
		String usernameText = username.getText().toString();
		String userText = user.getText().toString();
		String pswText = psw.getText().toString();
		String rpswText = rpsw.getText().toString();
		if(usernameText.equals("")||userText.equals("") || pswText.equals("") || rpswText.equals("")){
            showDialog("请把信息填写完整！");
            return false;
        }
        if(psw.equals(rpsw)){
            showDialog("两次输入密码不同");
            return false;
        }
        return true;
	}   
    
    private void showDialog(String msg){
        AlertDialog builder = new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("确定", null).show();  
    }
}