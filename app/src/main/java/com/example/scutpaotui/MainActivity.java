package com.example.scutpaotui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView titleTv;
    //fg
    private Xiadan_Fragment xiadanFg;
    private Jiedan_Fragment jiedanFg;
    private Geren_Fragment gerenFg;
    private Setting_Fragment setting_fragment;
    private History_Fragment history_fragment;
    //framelayout
    private FrameLayout framelayout;
    //click
    private RelativeLayout xiadanlayout;
    private RelativeLayout jiedanlayout;
    private RelativeLayout gerenlayout;
    private TextView firtext;
    private TextView sectext;
    private TextView thirtext;
    private TextView fortext;
    private ImageView xiadanimage;
    private ImageView jiedanimage;
    private ImageView gerenimage;
    private int white=0xFFFFFFFF;
    private int gray=0xFFCCCCCC;
    private int dark=0xFFCCCCCC;
    private FragmentManager fragmentManager;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        initView();
        setChoiceItem(0);
    }

    private void initView()
    {
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        View left = View.inflate(this, R.layout.title_layout, null);
        titleTv=(TextView)left.findViewById(R.id.title_text_tv);
        titleTv.setText("跑 腿");
        firtext=(TextView)findViewById(R.id.first_text);
        sectext=(TextView)findViewById(R.id.second_text);
        fortext=(TextView)findViewById(R.id.forth_text);
        xiadanimage=(ImageView)findViewById(R.id.first_image);
        jiedanimage=(ImageView)findViewById(R.id.second_image);
        gerenimage=(ImageView)findViewById(R.id.fourth_image);
        xiadanlayout=(RelativeLayout)findViewById(R.id.first_layout);
        jiedanlayout=(RelativeLayout) findViewById(R.id.second_layout);
        gerenlayout=(RelativeLayout) findViewById(R.id.fourth_layout);
        jiedanlayout.setOnClickListener(MainActivity.this);
        xiadanlayout.setOnClickListener(MainActivity.this);
        gerenlayout.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_layout:
                setChoiceItem(0);
                break;
            case R.id.second_layout:
                setChoiceItem(1);
                break;
            case R.id.fourth_layout:
                setChoiceItem(3);
                break;
            default:
                break;
        }
    }
    public String getName()
    {
        return username;
    }
    public void setName(String _name){username=_name;}
    private void setChoiceItem(int index)
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        clearChoice();
        switch (index){
            case 0:
                xiadanlayout.setBackgroundColor(gray);
                if(xiadanFg==null)
                {
                    xiadanFg = new Xiadan_Fragment();
                }
                fragmentTransaction.replace(R.id.content,xiadanFg,null);
                fragmentTransaction.addToBackStack(null);
                break;
            case 1:
                jiedanlayout.setBackgroundColor(gray);
                if(jiedanFg==null)
                {
                    jiedanFg = new Jiedan_Fragment();
                }
                fragmentTransaction.replace(R.id.content,jiedanFg,null);
                fragmentTransaction.addToBackStack(null);
                break;
            case 3:
                gerenlayout.setBackgroundColor(gray);
                if(gerenFg==null)
                {
                    gerenFg = new Geren_Fragment();
                }
                fragmentTransaction.replace(R.id.content,gerenFg,null);
                fragmentTransaction.addToBackStack(null);
                break;
        }
        fragmentTransaction.commit();
    }
    private void clearChoice()
    {
        xiadanlayout.setBackgroundColor(white);
        jiedanlayout.setBackgroundColor(white);
        gerenlayout.setBackgroundColor(white);
    }
}
