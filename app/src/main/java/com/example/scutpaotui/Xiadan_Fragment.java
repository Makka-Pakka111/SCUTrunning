package com.example.scutpaotui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public class Xiadan_Fragment extends Fragment {
    private EditText quhuoAddress;
    private EditText shouhuoAddress;
    private EditText mingcheng;
    private EditText money;
    private EditText note;
    private Button button;
    private String username1;
    private dao _addorder=new dao();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xia_dan,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void init()
    {
        shouhuoAddress=(EditText)getActivity().findViewById(R.id.Edit01);
        mingcheng=(EditText)getActivity().findViewById(R.id.Edit02);
        quhuoAddress=(EditText)getActivity().findViewById(R.id.Edit03);
        money=(EditText)getActivity().findViewById(R.id.Edit04);
        note=(EditText)getActivity().findViewById(R.id.Edit05);
        button=(Button)getActivity().findViewById(R.id.add);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                    click(view);
            }
        });
        MainActivity mainActivity=(MainActivity)getActivity();
        username1=mainActivity.getName();
    }
    public void click(View v)
    {
        new Thread()
        {
            @Override
            public void run() {
                try{
                    final MainActivity mainActivity=(MainActivity)getActivity();
                    String quhuo=quhuoAddress.getText().toString().trim();
                    String shouhuo=shouhuoAddress.getText().toString().trim();
                    String huowu=mingcheng.getText().toString().trim();
                    double reward=Double.parseDouble(money.getText().toString());
                    String nt=note.getText().toString().trim();
                    switch (check(shouhuo,huowu,quhuo))
                    {
                        case 1:
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(getActivity(),"收获地址不能为空",Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case 2:
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(),"货物名称不能为空",Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case 3:
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(),"拿货地址不能为空",Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case 0:
                            final order o=new order();
                            o.setName(huowu);
                            o.setDeliveryAddress(shouhuo);
                            o.setPickUpAddress(quhuo);
                            o.setNotes(nt);
                            _addorder.addorder(o,username1);
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(),"下单成功",Toast.LENGTH_SHORT).show();
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

    }

    public int check(String a,String b,String c)
    {
        int res=0;
        if(a.isEmpty())
        {
            res=1;
            return res;
        }
        if(b.isEmpty())
        {
            res=2;
            return res;
        }
        if(c.isEmpty())
        {
            res=3;
            return res;
        }
        return res;
    }

}
