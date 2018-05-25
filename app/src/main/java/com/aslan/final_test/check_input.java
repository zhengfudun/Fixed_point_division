package com.aslan.final_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class check_input extends AppCompatActivity {
    private EditText bcs,cs;
    private Button b1,b2;
    String fileName;
    int q=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_input);
        myinit();
    }

    private void myinit() {
        final Excel exc=new Excel();
        final algorithm js=new algorithm();
        Intent intent = getIntent();
        final String type = intent.getStringExtra("type");
        setTitle(type);
        bcs=findViewById(R.id.bcs);
        cs=findViewById(R.id.cs);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
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
                    q=1;
                }

                if(q==0) {
                    Toast.makeText(getApplicationContext(), "请先输入计算的除数与被除数", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(check_input.this, check.class);
                    intent.putExtra("name", fileName);
                    intent.putExtra("species", type);
                    startActivity(intent);
                }
            }
        });
    }
}
