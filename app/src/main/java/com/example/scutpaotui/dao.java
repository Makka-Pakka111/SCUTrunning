package com.example.scutpaotui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class dao {
    private static final String URL="jdbc:mysql://rm-wz95p9mgm2shbcux1pm.mysql.rds.aliyuncs.com:3306/myandroid";
    private static final String USER="lxf";
    private static final String PASSWORD="20001006Lxf";
    //登录，查找
    public Info get(String n, String p) {
        Info u=null;
        //获取连接
        Connection conn=null;
        try{
            System.out.println("加载");
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn==null)
            {
                System.out.println("null");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接");
        //sql
        String sql="select * from user where name="+"'"+n+"'"+" and password="+"'"+p+"'";
        try{
            Statement st;
            st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next())
            {
                String tn=rs.getString("name");
                String tp=rs.getString("password");
                u=new Info(tn,tp);
            }
            rs.close();
            st.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }
    //注册,增加
    public int add(Info a)
    {
        int res=0;
        //获取连接
        Connection conn=null;
        try{
            System.out.println("加载");
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn==null)
            {
                System.out.println("null");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接");
        //判断是否重复
        String ck=a.getName();
        String checksql="select name from user where name="+"'"+ck+"'";
        //sql
        String sql="INSERT INTO user(name, password) values(?,?)";
        try{
            Statement cktp;
            cktp=conn.createStatement();
            ResultSet rs=cktp.executeQuery(checksql);
            if(!rs.next())
            {
                PreparedStatement st;
                //预编译
                st=conn.prepareStatement(sql);
                //传参
                st.setString(1,a.getName());
                st.setString(2,a.getPass());
                st.execute();
                st.close();
                conn.close();
                res=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    //下单
    public void addorder(order o,String user)
    {
        System.out.println("add");
        Connection conn=null;
        try{
            System.out.println("加载");
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn==null)
            {
                System.out.println("null");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接");

        //sql
        String sql="insert into info(username1, date, quhuo, huowu, shouhuo, reward, note) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st;
            st=conn.prepareStatement(sql);
            st.setString(1,user);
            st.setTimestamp(2,o.getsqlIssueDate());
            st.setString(3,o.getPickUpAddress());
            st.setString(4,o.getName());
            st.setString(5,o.getDeliveryAddress());
            st.setDouble(6,o.getPrice());
            st.setString(7,o.getNotes());
            st.execute();
            st.close();
            conn.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public List<order> refresh()
    {
        Connection conn=null;
        try{
            System.out.println("加载");
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn==null)
            {
                System.out.println("null");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接");
        List<order> _order = new ArrayList<>();
        String sql="select * from info where isclick=0";
        try{
            Statement st;
            st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next())
            {
                String username=rs.getString(1);
                Timestamp date=rs.getTimestamp(2);
                String quhuo=rs.getString(3);
                String huowu=rs.getString(4);
                String shouhuo=rs.getString(5);
                double reward=rs.getDouble(6);
                String note=rs.getString(7);
                order temp=new order();
                temp.setName(huowu);
                temp.setNotes(note);
                temp.setPickUpAddress(quhuo);
                temp.setDeliveryAddress(shouhuo);
                temp.setIssueDate(date);
                temp.setPrice(reward);
                _order.add(temp);
            }
            rs.close();
            st.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return _order;
    }
    //接单
    public int removeorder(order o,String s)
    {
        Connection conn=null;
        int n=-1;
        try{
            System.out.println("加载");
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn==null)
            {
                System.out.println("null");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接");

        //sql
        String Sql="select username1 from info where quhuo="+"'"+o.getPickUpAddress()+"'"+" and huowu="+"'"+o.getName()+"'"+" and shouhuo="+"'"+o.getDeliveryAddress()+"'"+" and reward="+o.getPrice();
        try{
            Statement st;
            st=conn.createStatement();
            ResultSet rs=st.executeQuery(Sql);
            if(rs.next())
            {
                //接自己的单
                if(s.equals(rs.getString("username1"))){
                    n=0;
                }
                System.out.println(s.equals(rs.getString("username1")));
            }
            rs.close();
            st.close();
            if(n==0)
            {
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(n!=0)
        {
            String sql="update info set isclick=1 where quhuo="+"'"+o.getPickUpAddress()+"'"+" and huowu="+"'"+o.getName()+"'"+" and shouhuo="+"'"+o.getDeliveryAddress()+"'"+" and reward="+o.getPrice();
            try{
                Statement st;
                st=conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println(n);
        return n;
    }
    //修改用户名
    public void updatename(String now,String pre)
    {
        Connection conn=null;
        try{
            System.out.println("加载");
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn==null)
            {
                System.out.println("null");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接");
        String sql1="update user set name="+"'"+now+"'"+" where name="+"'"+pre+"'";
        try{
            Statement st;
            st=conn.createStatement();
            st.executeUpdate(sql1);
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("aaa");
        String sql2="update info set username1="+"'"+now+"'"+" where username1="+"'"+pre+"'";
        try{
            Statement st;
            st=conn.createStatement();
            System.out.println("xxx");
            st.executeUpdate(sql2);
            st.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //修改密码
    public int updatepw(String now,String pre,String name)
    {
        int res=0;
        Connection conn=null;
        try{
            System.out.println("加载");
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            if(conn==null)
            {
                System.out.println("null");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接");
        String sql1="select * from user where name="+"'"+name+"'"+" and password="+"'"+pre+"'";
        try{
            Statement st;
            st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql1);
            if(rs.next())
            {
                res=1;
            }
            rs.close();
            st.close();
            if(res==0)
            {
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(res==1)
        {
            String sql2="update user set password="+"'"+now+"'"+" where name="+"'"+name+"'";
            try{
                Statement st;
                st=conn.createStatement();
                st.executeUpdate(sql2);
                st.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;
    }

}
