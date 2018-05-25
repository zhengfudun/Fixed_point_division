package com.aslan.final_test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class choose extends AppCompatActivity {
    private Button b1,b2,b3,b4;
    String V1,V2,V3,V4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        myinit();
    }

    private void myinit() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        setTitle(type);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        if (type.equals("example"))
        {example();}
        else if (type.equals("check"))
        {check();}
        else if (type.equals("calculate"))
        {calculate();}
    }

    private void example() {
        final Excel exc=new Excel();
        final algorithm js =new algorithm();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exc.isfileExist("分析笔算","0001.xls")==0)
                {js.Fxbscf("0001.xls","0.1101","-0.1011");}
                Intent intent = new Intent(choose.this, process.class);
                intent.putExtra("name", "0001.xls");
                intent.putExtra("species", "分析笔算");
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exc.isfileExist("原码恢复余数","0002.xls")==0)
                {js.ymhfys("0002.xls","-0.1101","-0.1011");}
                Intent intent = new Intent(choose.this, process.class);
                intent.putExtra("name", "0002.xls");
                intent.putExtra("species", "原码恢复余数");
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exc.isfileExist("原码加减交替","0003.xls")==0)
                {js.ymjjjt("0003.xls","0.1101","-0.1011");}
                Intent intent = new Intent(choose.this, process.class);
                intent.putExtra("name", "0003.xls");
                intent.putExtra("species", "原码加减交替");
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exc.isfileExist("补码加减交替","0004.xls")==0)
                {js.bmjjjt("0004.xls","0.1101","0.1001");}
                Intent intent = new Intent(choose.this, process.class);
                intent.putExtra("name", "0004.xls");
                intent.putExtra("species", "补码加减交替");
                startActivity(intent);
            }
        });
    }

    private void check() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choose.this, check_input.class);
                intent.putExtra("type", "分析笔算");
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choose.this, check_input.class);
                intent.putExtra("type", "原码恢复余数");
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choose.this, check_input.class);
                intent.putExtra("type", "原码加减交替");
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choose.this, check_input.class);
                intent.putExtra("type", "补码加减交替");
                startActivity(intent);
            }
        });
    }

    private void calculate() {
        database();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(V1.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "请先通过一次此类型验算", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(choose.this, calculate.class);
                    intent.putExtra("type", "分析笔算");
                    startActivity(intent);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(V2.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "请先通过一次此类型验算", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(choose.this, calculate.class);
                    intent.putExtra("type", "原码恢复余数");
                    startActivity(intent);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(V3.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "请先通过一次此类型验算", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(choose.this, calculate.class);
                    intent.putExtra("type", "原码加减交替");
                    startActivity(intent);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(V4.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "请先通过一次此类型验算", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(choose.this, calculate.class);
                    intent.putExtra("type", "补码加减交替");
                    startActivity(intent);
                }
            }
        });
    }

    private void database(){
        DatabaseHelper dbHelper=new  DatabaseHelper(this);
        dbHelper.getWritableDatabase();
        int number=0;
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from verified", null);
        number=c.getCount();
        if(number==0)//表为空
        {
            ContentValues values=new ContentValues();
            //开始组装第一条数据
            values.put("TName", "fxbs");
            values.put("Ver", 0);
            db.insert("verified", null, values);
            values.clear();
            //开始组装第二条数据
            values.put("TName", "ymhfys");
            values.put("Ver", 0);
            db.insert("verified", null, values);
            values.clear();
            //开始组装第三条数据
            values.put("TName", "ymjjjt");
            values.put("Ver", 0);
            db.insert("verified", null, values);
            values.clear();
            //开始组装第四条数据
            values.put("TName", "bmjjjt");
            values.put("Ver", 0);
            db.insert("verified", null, values);
        }
        Cursor cursor = db.query("verified", new String[]{"Ver"}, "TName=?", new String[]{"fxbs"}, null, null, null);
        while(cursor.moveToNext()){ V1 = cursor.getString(cursor.getColumnIndex("Ver")); }
        cursor = db.query("verified", new String[]{"Ver"}, "TName=?", new String[]{"ymhfys"}, null, null, null);
        while(cursor.moveToNext()){ V2 = cursor.getString(cursor.getColumnIndex("Ver")); }
        cursor = db.query("verified", new String[]{"Ver"}, "TName=?", new String[]{"ymjjjt"}, null, null, null);
        while(cursor.moveToNext()){ V3 = cursor.getString(cursor.getColumnIndex("Ver")); }
        cursor = db.query("verified", new String[]{"Ver"}, "TName=?", new String[]{"bmjjjt"}, null, null, null);
        while(cursor.moveToNext()){ V4 = cursor.getString(cursor.getColumnIndex("Ver")); }
    }

}
