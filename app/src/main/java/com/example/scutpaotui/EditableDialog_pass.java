package com.example.scutpaotui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditableDialog_pass extends Dialog {
    Activity context;
    private Button saveButton;
    private TextView textField;
    public EditText oldPassword;
    public EditText newPassword;
    private View.OnClickListener mClickListener;

    public EditableDialog_pass(Activity context) {
        super(context);
        this.context = context;
    }

    public EditableDialog_pass(Activity context, int theme, View.OnClickListener clickListener) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.dialog_pass);
        textField = findViewById(R.id.textField);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);

        // 获取窗口对象
        Window dialogWindow = this.getWindow();
        WindowManager m = context.getWindowManager();
        // 获取屏幕宽、高用
        Display d = m.getDefaultDisplay();
        // 获取对话框当前的参数值
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        // 宽度设置为屏幕的0.8
        p.width = (int) (d.getWidth() * 0.6);
        p.height = (int)(d.getHeight() * 0.35);
        dialogWindow.setAttributes(p);
        // 根据id在布局中找到控件对象
        saveButton = findViewById(R.id.btn_save_pass);
        // 为按钮绑定点击事件监听器
        saveButton.setOnClickListener(mClickListener);
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }
}
