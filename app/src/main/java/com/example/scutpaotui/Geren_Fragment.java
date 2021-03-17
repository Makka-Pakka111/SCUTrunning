package com.example.scutpaotui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Geren_Fragment extends Fragment {
    private View view;
    private TextView name;
    private Button setting_But;
    private Button history_But;
    private Setting_Fragment setting_fg;
    private History_Fragment history_fg;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ge_ren, null);
        setting_But = (Button)view.findViewById(R.id.setInfo);
        history_But = (Button)view.findViewById(R.id.history);
        name=(TextView)view.findViewById(R.id.username_value);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final MainActivity mainActivity= (MainActivity) getActivity();
        name.setText(mainActivity.getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText(mainActivity.getName());
            }
        });
        setting_But.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("ResourceType")
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack("");
                ft.replace(R.id.content, new Setting_Fragment(),null).commit();
            }
        });

        history_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack("");
                ft.replace(R.id.content, new History_Fragment(),null).commit();
            }
        });

    }



    public Fragment getSet()
    {
        return setting_fg;
    }
    public Fragment getHis()
    {
        return history_fg;
    }



}
