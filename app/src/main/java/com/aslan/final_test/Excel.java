package com.aslan.final_test;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel
{
    private WritableWorkbook wwb;

    //-------------------------------------创建表格------------------------------------------------
    public void creatExcel(String fileName,String species)
    {
        // 首先判断该目录下的文件夹是否存在
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        if (!dir.exists()) {
            // 文件夹不存在 ， 则创建文件夹
            dir.mkdirs();
        }

        // 判断目标文件是否存在
        File file = new File(dir, fileName);
        if (!file.exists())
        {
            try {
                wwb = Workbook.createWorkbook(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //创建一个表单
        WritableSheet ws = null;
        ws = wwb.createSheet("计算详情", 0);
        if(species.equals("分析笔算"))
        {
            Label l1 = new Label(0, 0, "除数");
            Label l2 = new Label(1, 0, "被除数/余数");
            Label l3 = new Label(2, 0, "商");

            try {
                ws.addCell(l1);
                ws.addCell(l2);
                ws.addCell(l3);

                // 从内存中写入文件中
                wwb.write();
                wwb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(species.equals("原码恢复余数")||species.equals("原码加减交替"))
        {
            Label l1 = new Label(0, 0, "被除数/余数");
            Label l2 = new Label(1, 0, "商");
            Label l3 = new Label(2, 0, "说明");
            Label l4 = new Label(4, 0, "[x]原");
            Label l5 = new Label(5, 0, "[x]*");
            Label l6 = new Label(6, 0, "[y]原");
            Label l7 = new Label(7, 0, "[y]*");
            Label l8 = new Label(8, 0, "[-y*]补");

            try {
                ws.addCell(l1);
                ws.addCell(l2);
                ws.addCell(l3);
                ws.addCell(l4);
                ws.addCell(l5);
                ws.addCell(l6);
                ws.addCell(l7);
                ws.addCell(l8);

                // 从内存中写入文件中
                wwb.write();
                wwb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(species.equals("补码加减交替"))
        {
            Label l1 = new Label(0, 0, "被除数/余数");
            Label l2 = new Label(1, 0, "商");
            Label l3 = new Label(2, 0, "说明");
            Label l4 = new Label(4, 0, "x");
            Label l5 = new Label(5, 0, "y");
            Label l6 = new Label(6, 0, "[x]补");
            Label l7 = new Label(7, 0, "[y]补");
            Label l8 = new Label(8, 0, "[-y]补");


            try {
                ws.addCell(l1);
                ws.addCell(l2);
                ws.addCell(l3);
                ws.addCell(l4);
                ws.addCell(l5);
                ws.addCell(l6);
                ws.addCell(l7);
                ws.addCell(l8);

                // 从内存中写入文件中
                wwb.write();
                wwb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //---------------写入数据到excel，原码恢复余数，原码加减交替，补码加减交替---------------------
    public void writeToExcel(String species,String fileName,String dividend,String consequence,String instructions) {
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        File file = new File(dir, fileName);
        try {
            //每次插入数据,都要取原来的表,然后新建一个表,然后将原来的表的内容添加到新表上.但只要两个路径相同的话,效果相当于在原来的表添加.
            Workbook oldWwb = Workbook.getWorkbook(file);
            wwb = Workbook.createWorkbook(file, oldWwb);
            //获取指定索引的表格
            WritableSheet ws = wwb.getSheet(0);
            // 获取该表格现有的行数
            int row = ws.getRows();
            if(row==2 && ws.getCell(0,1).getContents()=="") row=1;//第一次录入与预设值同行
            Label bl1 = new Label(0, row, dividend);
            Label bl2 = new Label(1, row, consequence);
            Label bl3 = new Label(2, row, instructions);
            ws.addCell(bl1);
            ws.addCell(bl2);
            ws.addCell(bl3);
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------分析笔算录入-----------------------------------------
    public void writeTofxbs(String species,String fileName,String chushu,String beichushu,String consequence) {
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        File file = new File(dir, fileName);
        try {
            //每次插入数据,都要取原来的表,然后新建一个表,然后将原来的表的内容添加到新表上.但只要两个路径相同的话,效果相当于在原来的表添加.
            Workbook oldWwb = Workbook.getWorkbook(file);
            wwb = Workbook.createWorkbook(file, oldWwb);
            //获取指定索引的表格
            WritableSheet ws = wwb.getSheet(0);
            // 获取该表格现有的行数
            int row = ws.getRows();
            Label bl0 = new Label(0, row, chushu);
            Label bl1 = new Label(1, row, beichushu);
            Label bl2 = new Label(2, row, consequence);
            ws.addCell(bl0);
            ws.addCell(bl1);
            ws.addCell(bl2);
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------输入预运算值，例如原码，补码，绝对值（原码除法）------------------
    public void writepreset_ym(String species,String fileName,String x_yuan,String x_jue,String y_yuan,String y_jue,String fu_y_bu) {
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        File file = new File(dir, fileName);
        try {
            //每次插入数据,都要取原来的表,然后新建一个表,然后将原来的表的内容添加到新表上.但只要两个路径相同的话,效果相当于在原来的表添加.
            Workbook oldWwb = Workbook.getWorkbook(file);
            wwb = Workbook.createWorkbook(file, oldWwb);
            //获取指定索引的表格
            WritableSheet ws = wwb.getSheet(0);
            // 获取该表格现有的行数
            int row = ws.getRows();
            Label l4 = new Label(4, row, x_yuan);
            Label l5 = new Label(5, row, x_jue);
            Label l6 = new Label(6, row, y_yuan);
            Label l7 = new Label(7, row, y_jue);
            Label l8 = new Label(8, row, fu_y_bu);
            ws.addCell(l4);
            ws.addCell(l5);
            ws.addCell(l6);
            ws.addCell(l7);
            ws.addCell(l8);
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------输入预运算值，例如原码，补码，绝对值（补码加减交替）--------------------
    public void writepreset_bm(String species,String fileName,String x,String y,String x_bu,String y_bu,String fu_y_bu) {
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        File file = new File(dir, fileName);
        try {
            //每次插入数据,都要取原来的表,然后新建一个表,然后将原来的表的内容添加到新表上.但只要两个路径相同的话,效果相当于在原来的表添加.
            Workbook oldWwb = Workbook.getWorkbook(file);
            wwb = Workbook.createWorkbook(file, oldWwb);
            //获取指定索引的表格
            WritableSheet ws = wwb.getSheet(0);
            // 获取该表格现有的行数
            int row = ws.getRows();
            Label l4 = new Label(4, row, x);
            Label l5 = new Label(5, row, y);
            Label l6 = new Label(6, row, x_bu);
            Label l7 = new Label(7, row, y_bu);
            Label l8 = new Label(8, row, fu_y_bu);
            ws.addCell(l4);
            ws.addCell(l5);
            ws.addCell(l6);
            ws.addCell(l7);
            ws.addCell(l8);
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------输入预设值（分析笔算）--------------------------------------
    public void fxbspreset(String species,String fileName,String chushu,String beichushu) {
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        File file = new File(dir, fileName);
        try {
            //每次插入数据,都要取原来的表,然后新建一个表,然后将原来的表的内容添加到新表上.但只要两个路径相同的话,效果相当于在原来的表添加.
            Workbook oldWwb = Workbook.getWorkbook(file);
            wwb = Workbook.createWorkbook(file, oldWwb);
            //获取指定索引的表格
            WritableSheet ws = wwb.getSheet(0);
            // 获取该表格现有的行数
            int row = ws.getRows();
            Label l4 = new Label(0, row, chushu);
            Label l5 = new Label(1, row, beichushu);
            ws.addCell(l4);
            ws.addCell(l5);
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------获取表格行数--------------------------------------------
    public int getExcelrow(String species,String fileName) {
        int row=0;
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        File file = new File(dir, fileName);
        try {
            Workbook oldWwb = Workbook.getWorkbook(file);
            //获取指定索引的表格
            Sheet ws = oldWwb.getSheet(0);
            // 获取该表格现有的行数
            row = ws.getRows();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //-------------------------------------表格读取内容--------------------------------------------
    public String readExcel(String species,String fileName,int row,int cow) {
        String cellStr="";
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        File file = new File(dir, fileName);
        try {
            Workbook oldWwb = Workbook.getWorkbook(file);
            //获取指定索引的表格
            Sheet ws = oldWwb.getSheet(0);
            // 获取该表格现有的行数
            cellStr = ws.getRow(row)[cow].getContents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellStr;
    }

    //-------------------------------------判断文件是否存在----------------------------------------
    public int isfileExist(String species,String fileName) {
        int i=0;
        // 首先判断该目录下的文件夹是否存在
        File dir = new File(Environment.getExternalStorageDirectory() + "/定点数除法/" + species + "/");
        if (!dir.exists()) i=0;// 文件夹不存在
        else {
            // 判断目标文件是否存在
            File file = new File(dir, fileName);
            if (!file.exists()) i=0;// 文件不存在
            else i=1;
        }
        return i;
    }
}
