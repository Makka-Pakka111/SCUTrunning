package com.example.scutpaotui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.ViewHolder>{

    private List<order> mOrderList;
    private Context context;
    private dao _remove=new dao();

    class ViewHolder extends RecyclerView.ViewHolder{
        View orderView;
        TextView orderName;
        TextView orderPrice;
        TextView orderPickUpAddress;
        TextView orderDeliveryAddress;
        TextView orderNotes;
        TextView orderTime;
        Button order_qiangdan;

        public ViewHolder(View view){
            super(view);
            orderView = view;
            orderName = (TextView)view.findViewById(R.id.item_name);
            orderName.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
            orderPrice = (TextView)view.findViewById(R.id.item_price);
            orderPickUpAddress = (TextView)view.findViewById(R.id.pickUpAddressMessage);
            orderDeliveryAddress = (TextView)view.findViewById(R.id.deliveryMessage);
            orderNotes = (TextView)view.findViewById(R.id.notesMessage);
            orderTime = (TextView)view.findViewById(R.id.item_time);
            //注册点击事件
            order_qiangdan = (Button)view.findViewById(R.id.qiangdan_button);
        }
    }


    public orderAdapter(List<order> orderList, Context context){
        this.mOrderList = orderList;this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.orderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                order order = mOrderList.get(position);
                Toast.makeText(v.getContext(), "you clicked "+ order.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.order_qiangdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            final MainActivity mainActivity=(MainActivity)context;
                            int position = holder.getAdapterPosition();
                            order _order = mOrderList.get(position);
                            final int res=_remove.removeorder(_order,mainActivity.getName());
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(res==0)
                                    {
                                        Toast.makeText(mainActivity, "不能接自己的订单", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(mainActivity, "抢单成功", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        order order = mOrderList.get(position);
        holder.orderName.setText(order.getName());
        holder.orderPrice.setText("￥"+ order.getPrice());
        holder.orderPickUpAddress.setText(order.getPickUpAddress());
        holder.orderDeliveryAddress.setText(order.getDeliveryAddress());
        holder.orderNotes.setText(order.getNotes());
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24h
        String tm=sdformat.format(order.getIssueDate());
        holder.orderTime.setText(tm);
    }

    @Override
    public int getItemCount(){
        return mOrderList.size();
    }
}
