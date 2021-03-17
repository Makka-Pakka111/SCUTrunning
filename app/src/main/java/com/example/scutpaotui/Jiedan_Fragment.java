package com.example.scutpaotui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Jiedan_Fragment extends Fragment {

    private List<order> orderList = new ArrayList<>();
    private List<order> temp = new ArrayList<>();
    private View view;
    private String user1;
    private dao _jiedan=new dao();
    private SwipeRefreshLayout srl;
    private orderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.jie_dan, container, false);
        try {
            initRecyclerView();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                temp=_jiedan.refresh();
            }
        }, 0,5000);
        srl=(SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_refresh_layout);
        srl.setColorSchemeResources(R.color.khaki);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                try {
                    initRecyclerView();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void refresh()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        srl.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initRecyclerView() throws InterruptedException {
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        adapter = new orderAdapter(orderList,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        initOrders();
        recyclerView.setAdapter(adapter);
    }

    private void initOrders() throws InterruptedException {
        orderList.removeAll(orderList);
        for(int i=temp.size()-1;i>=0;i--)
        {
            orderList.add(temp.get(i));
        }
    }

}
