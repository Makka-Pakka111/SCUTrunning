package com.example.scutpaotui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class Setting_Fragment extends Fragment {
    private Button setN;
    private Button setP;
    private TextView titleField1;
    private EditText titleField2;
    private View view;
    private TextView name;
    private EditableDialog dialog;
    private dao _change=new dao();
    private EditableDialog_pass dialogPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_setting, null);
        setN = (Button)view.findViewById(R.id.setName);
        setP = (Button)view.findViewById(R.id.setPass);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new Thread(){
                    @Override
                    public void run() {
                        try {
                            final MainActivity mainActivity=(MainActivity)getActivity();
                            String newname=dialog.editTextField.getText().toString().trim();
                            String prevnamw=mainActivity.getName();
                            _change.updatename(newname,prevnamw);
                            mainActivity.setName(newname);
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mainActivity,"修改用户名成功",Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();*/
                showEditDialog(setN);
            }
        });
        setP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialogPass(setP);
            }
        });
    }

    public void showEditDialog(View view) {
        dialog = new EditableDialog(getActivity(),R.layout.dialog, onClickListener);
        dialog.show();
    }
    public void showEditDialogPass(View view){
        dialogPass = new EditableDialog_pass(getActivity(),R.layout.dialog_pass,onClickListener);
        dialogPass.show();
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_save:
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                final MainActivity mainActivity=(MainActivity)getActivity();
                                String newname=dialog.editTextField.getText().toString().trim();
                                String prevnamw=mainActivity.getName();
                                _change.updatename(newname,prevnamw);
                                mainActivity.setName(newname);
                                mainActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mainActivity,"修改用户名成功",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    dialog.dismiss();
                    break;
                case R.id.btn_save_pass:
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                final MainActivity mainActivity=(MainActivity)getActivity();
                                String name=mainActivity.getName();
                                String newpass=dialogPass.newPassword.getText().toString().trim();
                                String prevpass=dialogPass.oldPassword.getText().toString().trim();
                                final int res=_change.updatepw(newpass,prevpass,name);
                                mainActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(res==0)
                                        {
                                            Toast.makeText(mainActivity,"密码错误",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(mainActivity,"修改密码成功",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    dialogPass.dismiss();
                default:
                    break;
            }
        }
    };
}
