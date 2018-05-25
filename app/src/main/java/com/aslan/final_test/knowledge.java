package com.aslan.final_test;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.aslan.final_test.show_fragment.*;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class knowledge extends AppCompatActivity {

    private static final String[] strs = new String[] {
        "原码", "补码", "定点数与浮点数", "定点数除法","分析笔算法", "原码恢复余数法","原码加减交替法","补码加减交替法"
    };

    //三个fragment
    private ym_Fragment f1;
    private bm_Fragment f2;
    private ddsorfds_Fragment f3;
    private ddscf_Fragment f4;
    private fxbs_Fragment f5;
    private ymhfys_Fragment f6;
    private ymjjjt_Fragment f7;
    private bmjjjt_Fragment f8;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        //侧滑菜单
        slidingm();
        myinit();
    }

    private void myinit() {
        //第一次初始化首页默认显示第一个fragment
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(f1 == null){
            f1 = new ym_Fragment();
            transaction.add(R.id.fram1, f1);
        }
        setTitle("原码");
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);
        //提交事务
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){ transaction.hide(f1); }
        if(f2 != null){ transaction.hide(f2); }
        if(f3 != null){ transaction.hide(f3); }
        if(f4 != null){ transaction.hide(f4); }
        if(f5 != null){ transaction.hide(f5); }
        if(f6 != null){ transaction.hide(f6); }
        if(f7 != null){ transaction.hide(f7); }
        if(f8 != null){ transaction.hide(f8); }
    }

    private void slidingm() {
        //隐藏标题栏
        // getSupportActionBar().hide();
        // configure the SlidingMenu
        //创建对象
        SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置滑动模式
        slidingMenu.setMode(SlidingMenu.LEFT);
        //SlidingMenu划出时主页面显示的剩余宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置滑动的区域
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //使SlidingMenu附加在Activity上
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置布局文件
        slidingMenu.setMenu(getLeftMenu() );
    }

    public View getLeftMenu() {
        //从主布局文件绑定的Activity调用另一个布局文件必须调用LayoutInflater
        LayoutInflater inflater = getLayoutInflater();
        //得到menu的View
        @SuppressLint("ResourceType") View v = inflater.inflate(R.menu.menu1, null);
        ListView listview = (ListView) v.findViewById(R.id.left_menu_listview);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();/*在数组中存放数据*/
        int l=strs.length;
        for(int i=0;i<l;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", strs[i]);
            listItem.add(map);
        }
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,listItem,
                R.layout.item,new String[] {"ItemTitle"},new int[] {R.id.titlem});
        listview.setAdapter(mSimpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                hideFragment(transaction);
                if(position==0)
                {
                    setTitle("原码");
                    if(f1 == null)
                     {
                        f1 = new ym_Fragment();
                        transaction.add(R.id.fram1,f1);
                     }
                    transaction.show(f1);
                }
                else if (position==1)
                {
                    setTitle("补码");
                    if(f2 == null)
                    {
                        f2 = new bm_Fragment();
                        transaction.add(R.id.fram1,f2);
                    }
                    transaction.show(f2);
                }
                else if (position==2)
                {
                    setTitle("定点数与浮点数");
                    if(f3 == null)
                    {
                        f3 = new ddsorfds_Fragment();
                        transaction.add(R.id.fram1,f3);
                    }
                    transaction.show(f3);
                }
                else if (position==3)
                {
                    setTitle("定点数除法");
                    if(f4 == null)
                    {
                        f4 = new ddscf_Fragment();
                        transaction.add(R.id.fram1,f4);
                    }
                    transaction.show(f4);
                }
                else if (position==4)
                {
                    setTitle("分析笔算法");
                    if(f5 == null)
                    {
                        f5 = new fxbs_Fragment();
                        transaction.add(R.id.fram1,f5);
                    }
                    transaction.show(f5);
                }
                else if (position==5)
                {
                    setTitle("原码恢复余数法");
                    if(f6 == null)
                    {
                        f6 = new ymhfys_Fragment();
                        transaction.add(R.id.fram1,f6);
                    }
                    transaction.show(f6);
                }
                else if (position==6)
                {
                    setTitle("原码加减交替法");
                    if(f7 == null)
                    {
                        f7 = new ymjjjt_Fragment();
                        transaction.add(R.id.fram1,f7);
                    }
                    transaction.show(f7);
                }
                else if (position==7)
                {
                    setTitle("补码加减交替法");
                    if(f8 == null)
                    {
                        f8 = new bmjjjt_Fragment();
                        transaction.add(R.id.fram1,f8);
                    }
                    transaction.show(f8);
                }
                transaction.commit();
            }
        });
        return v;
    }
}
