package com.aslan.final_test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button b1,b2,b3,b4,b5,data0,data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myinit();
    }

    private void myinit() {
        b1=findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, knowledge.class);
                startActivity(intent);
            }
        });
        b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, choose.class);
                intent.putExtra("type", "example");
                startActivity(intent);
            }
        });
        b3=findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, choose.class);
                intent.putExtra("type", "check");
                startActivity(intent);
            }
        });
        b4=findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, choose.class);
                intent.putExtra("type", "calculate");
                startActivity(intent);
            }
        });
        b5=findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);
            }
        });
        data0=findViewById(R.id.button);
        data0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper=new  DatabaseHelper(MainActivity.this);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("Ver", 0);
                db.update("verified", values, "TName=?", new String[] { "fxbs" });
                db.update("verified", values, "TName=?", new String[] { "ymhfys" });
                db.update("verified", values, "TName=?", new String[] { "ymjjjt" });
                db.update("verified", values, "TName=?", new String[] { "bmjjjt" });
            }
        });
        data1=findViewById(R.id.button2);
        data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper=new  DatabaseHelper(MainActivity.this);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("Ver", 1);
                db.update("verified", values, "TName=?", new String[] { "fxbs" });
                db.update("verified", values, "TName=?", new String[] { "ymhfys" });
                db.update("verified", values, "TName=?", new String[] { "ymjjjt" });
                db.update("verified", values, "TName=?", new String[] { "bmjjjt" });
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                String path=uri.getPath().toString();
                String paths[]=path.split("/");
                int ii=paths.length;
                Intent intent = new Intent(MainActivity.this, process.class);
                intent.putExtra("name", paths[ii-1]);
                intent.putExtra("species", paths[ii-2]);
                startActivity(intent);
            }
        }
    }

}
