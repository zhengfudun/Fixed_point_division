package com.aslan.final_test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class process extends AppCompatActivity {
    private ListView lv_show;
    private mylistadapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;
    private TextView x1,x2,x3,y1,y2,y3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("name");
        String species = intent.getStringExtra("species");
        setTitle(species);
        sethead(filename,species);
        myinit(filename,species);
    }

    private void sethead(String filename,String species) {
        x1=findViewById(R.id.x1);
        x2=findViewById(R.id.x2);
        x3=findViewById(R.id.x3);
        y1=findViewById(R.id.y1);
        y2=findViewById(R.id.y2);
        y3=findViewById(R.id.y3);

        Excel exc=new Excel();
        if(species.equals("分析笔算"))
        {
            x1.setText("被除数x："+exc.readExcel(species,filename,1,1));
            y1.setText("除数y："+exc.readExcel(species,filename,1,0));
        }
        else if(species.equals("原码恢复余数")||species.equals("原码加减交替"))
        {
            x1.setText("[x]原："+exc.readExcel(species,filename,1,4));
            x2.setText("[x]*："+exc.readExcel(species,filename,1,5));
            y1.setText("[y]原："+exc.readExcel(species,filename,1,6));
            y2.setText("[y]*："+exc.readExcel(species,filename,1,7));
            y3.setText("[-y*]补："+exc.readExcel(species,filename,1,8));
        }
        else if(species.equals("补码加减交替"))
        {
            x1.setText("x："+exc.readExcel(species,filename,1,4));
            x2.setText("[x]补："+exc.readExcel(species,filename,1,6));
            y1.setText("y："+exc.readExcel(species,filename,1,5));
            y2.setText("[y]补："+exc.readExcel(species,filename,1,7));
            y3.setText("[-y]补："+exc.readExcel(species,filename,1,8));
        }
    }

    private void myinit(String filename,String species) {
        lv_show =findViewById(R.id.lv_show);
        mContext = process.this;
        mData = new LinkedList<Data>();
        mAdapter = new mylistadapter((LinkedList<Data>) mData,mContext);
        lv_show.setAdapter(mAdapter);

        Excel exc=new Excel();
        int row=exc.getExcelrow(species,filename);
        for (int i=0;i<row;i++)
        {
                   String chushu=exc.readExcel(species,filename,i,0);
                   String yushu=exc.readExcel(species,filename,i,1);
                   String shang=exc.readExcel(species,filename,i,2);
                   mAdapter.add(new Data(chushu,yushu,shang));
        }
    }
}


