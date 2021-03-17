package com.example.scutpaotui;

import androidx.annotation.NonNull;

public class Info {
    //private int id;
    private String name;
    private String pass;
    public Info(String name, String pass)
    {
        //this.id=id;
        this.name=name;
        this.pass=pass;
    }

    public String getName()
    {
        return name;
    }
    public String getPass(){
        return pass;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public void setPass(String pass)
    {
        this.pass=pass;
    }

    @NonNull
    @Override
    public String toString() {
        return "Info{"+"name="+name+", pass="+pass+"}";
    }
}
