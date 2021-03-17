package com.example.scutpaotui;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    private Button login_btn;
    private TextView register_btn;
    private EditText inputname;
    private EditText inputpw;
    private dao _login =new dao();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }
    private void init()
    {
        login_btn = (Button) findViewById(R.id.login_button);
        register_btn=(TextView) findViewById(R.id.registerBut) ;
        inputname=(EditText)findViewById(R.id.name);
        inputpw=(EditText)findViewById(R.id.password) ;
        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerBut:
                Intent intent1 = new Intent(LoginActivity.this, registerActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.login_button:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            final String n = inputname.getText().toString().trim();
                            final String p = inputpw.getText().toString().trim();
                            final Info res=_login.get(n,p);
                            runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!TextUtils.isEmpty(n) && !TextUtils.isEmpty(p)){
                                            if (res != null) {
                                                Toast.makeText(LoginActivity.this, "成功登录", Toast.LENGTH_SHORT).show();
                                                Intent intent2 = new Intent();
                                                intent2.putExtra("username",n);
                                                intent2.setClass(LoginActivity.this,MainActivity.class);
                                                startActivity(intent2);
                                                finish();
                                            } else {
                                                Toast.makeText(LoginActivity.this, "用户名或者密码不正确", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            break;

        }
    }
}