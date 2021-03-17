package com.example.scutpaotui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity implements OnClickListener {
    private EditText yonghuming;
    private EditText mima;
    private EditText querenmima;
    private Button zhuce;
    private TextView qudenglu;
    private dao _register=new dao();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
    }
    public void init()
    {
        yonghuming=(EditText)findViewById(R.id.register_name);
        mima=(EditText)findViewById(R.id.editText);
        querenmima=(EditText)findViewById(R.id.editText2);
        qudenglu=(TextView)findViewById(R.id.register_login);
        zhuce=(Button)findViewById(R.id.register_confirm);
        zhuce.setOnClickListener(this);
        qudenglu.setOnClickListener(this);
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.register_confirm:
                new Thread()
                {
                    @Override
                    public void run() {
                        try{
                            switch (check())
                            {
                                case 0:
                                    String yhm=yonghuming.getText().toString().trim();
                                    String mm=mima.getText().toString().trim();
                                    Info temp=new Info(yhm,mm);
                                    final int res=_register.add(temp);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(res==1)
                                            {
                                                Toast.makeText(registerActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                            }else
                                            {
                                                Toast.makeText(registerActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    break;
                                case 1:
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(registerActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    break;
                                case 2:
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(registerActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    break;
                                case 3:
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(registerActivity.this,"请确认密码",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    break;
                                case 4:
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(registerActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    break;
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.register_login:
                Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    public int check() {
        int isok = 0;
        String n = yonghuming.getText().toString().trim();
        String m = mima.getText().toString().trim();
        String q = querenmima.getText().toString().trim();
        if(n.isEmpty())
        {
            isok=1;
            return isok;
        }
        if(m.isEmpty())
        {
            isok=2;
            return isok;
        }
        if(q.isEmpty())
        {
            isok=3;
            return isok;
        }
        if(!m.equals(q))
        {
            isok=4;
            return isok;
        }
        return isok;
    }


}
