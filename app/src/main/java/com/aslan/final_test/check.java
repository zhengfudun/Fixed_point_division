package com.aslan.final_test;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class check extends AppCompatActivity {
    private ListView lv_show;
    private mylistadapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;
    private TextView x1,x2,x3,y1,y2,y3;
    private Button b1;
    private EditText et;
    int i;//记录当前行数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

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

    private void myinit(final String filename, final String species) {
        lv_show =findViewById(R.id.lv_show);
        mContext = check.this;
        mData = new LinkedList<Data>();
        mAdapter = new mylistadapter((LinkedList<Data>) mData,mContext);
        lv_show.setAdapter(mAdapter);
        final Excel exc=new Excel();
        final algorithm js=new algorithm();
        final int row=exc.getExcelrow(species,filename);

        //加载头两行，包括说明和初值
        for (i=0;i<2;i++)
        {
            String chushu=exc.readExcel(species,filename,i,0);
            String yushu=exc.readExcel(species,filename,i,1);
            String shang=exc.readExcel(species,filename,i,2);
            mAdapter.add(new Data(chushu,yushu,shang));
        }
        b1=findViewById(R.id.b1);
        et=findViewById(R.id.et);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (i<row-1)
              {
                if(species.equals("分析笔算"))
                {
                    String chushu=exc.readExcel(species,filename,i,0);
                    String yushu=exc.readExcel(species,filename,i,1);
                    String shang=exc.readExcel(species,filename,i,2);
                    String shuru=et.getText().toString();
                    float y=Float.parseFloat(yushu);
                    float s=Float.parseFloat(shuru);
                    if (s==y)
                    {
                        Toast.makeText(getApplicationContext(), "当前步骤计算正确", Toast.LENGTH_SHORT).show();
                        mAdapter.add(new Data(chushu,yushu,shang));
                        i++;
                    }
                    else Toast.makeText(getApplicationContext(), "当前步骤计算错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
                else if(species.equals("原码加减交替")||species.equals("原码恢复余数"))
                {
                    String chushu=exc.readExcel(species,filename,i,0);
                    String yushu=exc.readExcel(species,filename,i,1);
                    String shang=exc.readExcel(species,filename,i,2);
                    String shuru=et.getText().toString();
                    float y=Float.parseFloat(chushu);
                    float s=Float.parseFloat(shuru);
                    if (s==y)
                    {
                        Toast.makeText(getApplicationContext(), "当前步骤计算正确", Toast.LENGTH_SHORT).show();
                        mAdapter.add(new Data(chushu,yushu,shang));
                        i++;
                        if(i==row-1){et.setText("验算完，请再按一次确认");}
                    }
                    else Toast.makeText(getApplicationContext(), "当前步骤计算错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
                else if(species.equals("补码加减交替"))
                {
                    String chushu=exc.readExcel(species,filename,i,0);
                    String yushu=exc.readExcel(species,filename,i,1);
                    String shang=exc.readExcel(species,filename,i,2);
                    String shuru=et.getText().toString();
                    float y=Float.parseFloat(chushu);
                    float s=Float.parseFloat(shuru);
                    if (s==y)
                    {
                        Toast.makeText(getApplicationContext(), "当前步骤计算正确", Toast.LENGTH_SHORT).show();
                        mAdapter.add(new Data(chushu,yushu,shang));
                        i++;
                        if(i==row-1){et.setText("验算完，请再按一次确认");}
                    }
                    else Toast.makeText(getApplicationContext(), "当前步骤计算错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
              }
              else {
                  if(i==row-1){
                      String chushu=exc.readExcel(species,filename,i,0);
                      String yushu=exc.readExcel(species,filename,i,1);
                      String shang=exc.readExcel(species,filename,i,2);
                      mAdapter.add(new Data(chushu,yushu,shang));
                      i++;
                  }
                  Toast.makeText(getApplicationContext(), "计算结束", Toast.LENGTH_SHORT).show();
                  DatabaseHelper dbHelper=new  DatabaseHelper(check.this);
                  SQLiteDatabase db=dbHelper.getWritableDatabase();
                  ContentValues values = new ContentValues();
                  if(species.equals("分析笔算"))
                  {
                      values.put("Ver", 1);
                      db.update("verified", values, "TName=?", new String[] { "fxbs" });
                  }
                  else if(species.equals("原码恢复余数"))
                  {
                      values.put("Ver", 1);
                      db.update("verified", values, "TName=?", new String[] { "ymhfys" });
                  }
                  else if(species.equals("原码加减交替"))
                  {
                      values.put("Ver", 1);
                      db.update("verified", values, "TName=?", new String[] { "ymjjjt" });
                  }
                  else if(species.equals("补码加减交替"))
                  {
                      values.put("Ver", 1);
                      db.update("verified", values, "TName=?", new String[] { "bmjjjt" });
                  }
              }
            }
        });
    }


}
