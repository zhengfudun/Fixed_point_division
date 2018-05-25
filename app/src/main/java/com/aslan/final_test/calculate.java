package com.aslan.final_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calculate extends AppCompatActivity {
    private EditText bcs,cs;
    private Button b1,b2,b3;
    private TextView suanshi,shang,yushu;
    int q=0;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        myinit();
    }

    private void myinit() {
        final Excel exc=new Excel();
        final algorithm js=new algorithm();
        final Intent intent = getIntent();
        final String type = intent.getStringExtra("type");
        setTitle(type);
        suanshi=findViewById(R.id.ss);
        shang=findViewById(R.id.shang);
        yushu=findViewById(R.id.yushu);
        bcs=findViewById(R.id.bcs);
        cs=findViewById(R.id.cs);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(js.is_Dec(bcs.getText().toString())==0||js.is_Dec(cs.getText().toString())==0) {
                    Toast.makeText(getApplicationContext(), "输入包含非法字符", Toast.LENGTH_SHORT).show();
                    }
                else if(js.Beforepoint(bcs.getText().toString())==0||js.Beforepoint(cs.getText().toString())==0){
                    Toast.makeText(getApplicationContext(), "仅支持绝对值小于1的小数", Toast.LENGTH_SHORT).show();
                }
                else {
                    bcs.setText(js.DectoBin(bcs.getText().toString()));
                    cs.setText(js.DectoBin(cs.getText().toString()));
                    }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(js.is_Bin(bcs.getText().toString())==0||js.is_Bin(cs.getText().toString())==0) {
                    Toast.makeText(getApplicationContext(), "输入非二进制数", Toast.LENGTH_SHORT).show();
                }
                else if(js.Beforepoint(bcs.getText().toString())==0||js.Beforepoint(cs.getText().toString())==0){
                    Toast.makeText(getApplicationContext(), "仅支持绝对值小于1的小数", Toast.LENGTH_SHORT).show();
                }
                else {
                    String bcss=bcs.getText().toString();
                    String css=cs.getText().toString();
                    fileName="x:"+bcss+"÷"+"y:"+css+".xls";
                    if (type.equals("分析笔算"))
                    {if(exc.isfileExist("分析笔算",fileName)==0)
                    {js.Fxbscf(fileName,css,bcss);}}
                    else if (type.equals("原码恢复余数"))
                    {if(exc.isfileExist("原码恢复余数",fileName)==0)
                    {js.ymhfys(fileName,css,bcss);}}
                    else if (type.equals("原码加减交替"))
                    {if(exc.isfileExist("原码加减交替",fileName)==0)
                    {js.ymjjjt(fileName,css,bcss);}}
                    else if (type.equals("补码加减交替"))
                    {if(exc.isfileExist("补码加减交替",fileName)==0)
                    {js.bmjjjt(fileName,css,bcss);}}

                    if (type.equals("分析笔算"))
                    {
                        String bccss=bcs.getText().toString();
                        String ccss=cs.getText().toString();
                        fileName="x:"+bccss+"÷"+"y:"+ccss+".xls";
                        int row=exc.getExcelrow(type,fileName);
                        suanshi.setText("x/y="+bccss+"/"+ccss);
                        yushu.setText(exc.readExcel(type,fileName,row-1,1));
                        shang.setText(exc.readExcel(type,fileName,row-2,2));
                    }
                    else if (type.equals("补码加减交替"))
                    {
                        String bccss=bcs.getText().toString();
                        String ccss=cs.getText().toString();
                        fileName="x:"+bccss+"÷"+"y:"+ccss+".xls";
                        int row=exc.getExcelrow(type,fileName);
                        suanshi.setText("x/y="+bccss+"/"+ccss);
                        yushu.setText(exc.readExcel(type,fileName,row-1,0));
                        shang.setText(exc.readExcel(type,fileName,row-1,1));
                    }
                    else
                    {
                        String bccss=bcs.getText().toString();
                        String ccss=cs.getText().toString();
                        fileName="x:"+bccss+"÷"+"y:"+ccss+".xls";
                        int row=exc.getExcelrow(type,fileName);
                        suanshi.setText("x/y="+bccss+"/"+ccss);
                        yushu.setText(exc.readExcel(type,fileName,row-2,0));
                        shang.setText(exc.readExcel(type,fileName,row-1,1));
                    }
                    q=1;
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(q==0) {
                    Toast.makeText(getApplicationContext(), "请先进行一次计算", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(calculate.this, process.class);
                    intent.putExtra("name", fileName);
                    intent.putExtra("species", type);
                    startActivity(intent);
                }
            }
        });
    }
}
