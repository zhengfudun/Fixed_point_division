package com.aslan.final_test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class algorithm {

    //分析笔算除法，输入除数与被除数的二进制真值
    public void Fxbscf(String filename,String chushu,String beichushu)
    {
        Excel exc = new Excel();
        exc.creatExcel(filename,"分析笔算");
        exc.fxbspreset("分析笔算",filename,chushu,beichushu);
        //-----------------------------------输入小数点分片---------------------------------------
        String did_s,dir_s;
        did_s=beichushu;
        dir_s=chushu;

        //用于储存正负号
        String did_pm,dir_pm;
        if(Float.parseFloat(did_s)>0)
            did_pm="+";
        else did_pm="-";
        if(Float.parseFloat(dir_s)>0)
            dir_pm="+";
        else dir_pm="-";
        if (did_pm.equals(dir_pm)) did_pm="+";
        else did_pm="-";

        //小数点为界分片
        String[] did_sa=did_s.split("\\.");
        String[] dir_sa=dir_s.split("\\.");

        //用于储存小数部分
        int did_n=Integer.parseInt(did_sa[1]);
        int dir_n=Integer.parseInt(dir_sa[1]);

        //小数部分二进制
        String did_Bin=""+did_n;
        String dir_Bin=""+dir_n;

        int dir_Bn=Integer.parseInt(dir_Bin);
        int did_Bn=Integer.parseInt(did_Bin);

        //转为数组存储
        int[] did_Array = Su_to_Sa_re(did_Bin);
        int[] dir_Array = Su_to_Sa_re(dir_Bin);

        //--------------------------------算数部分-----------------------------------------------
        int[] count=new int[dir_Array.length];//用于存储运算结果
        int acount=did_Array.length;
        if(did_Bn==dir_Bn)
        {
            exc.writeTofxbs("分析笔算",filename,"","0","1");
        }
        else {
            while (acount >= 0) {
                String db = "";
                for (int j = did_Array.length - 1; j >= 0; j--) {
                    db = db + did_Array[j];
                }
                did_Bn = Integer.parseInt(db);//当前被除数
                int[] re_count=count;//存储上一次的商值
                if (did_Bn > dir_Bn)//当前被除数大于除数
                {
                    for (int k = 0; k < dir_Array.length; k++) {
                        if (did_Array[k] == dir_Array[k])
                            did_Array[k] = 0;
                        else if (did_Array[k] == 1)
                            did_Array[k] = 1;
                        else if (did_Array[k] == 0) {
                            did_Array[k] = 1;
                            int l = k + 1, m = 0;
                            if (did_Array[l] == 1)
                                did_Array[l] = 0;
                            else while (did_Array[l] == 0 && l < did_Array.length) {
                                did_Array[l] = 1;
                                l = l + 1;
                                if (did_Array[l] == 1)
                                    m = l;
                            }
                            if (m != 0)
                                did_Array[m] = 0;
                        }
                    }
                    count[dir_Array.length - acount - 1] = 1;
                    String zn="";//余数补零
                    for(int n=dir_Array.length-acount-1;n>0;n--)
                        zn=zn+"0";
                    exc.writeTofxbs("分析笔算",filename,"0.0"+zn+dir_Bn,"0."+zn+db,did_pm + 0 + "." + Sa_to_Su(re_count));
                }
                else if (did_Bn < dir_Bn && acount != 0) {
                    db = "";
                    for (int j = did_Array.length - 1; j >= 0; j--) {
                        db = db + did_Array[j];
                    }
                    did_Bn = Integer.parseInt(db);
                    did_Array = Su_to_Sa_re(did_Bn + "0");
                    acount = acount - 1;
                } else if (did_Bn == dir_Bn) {
                    count[dir_Array.length - acount - 1] = 1;
                    for (int o = did_Array.length - 1; o >= 0; o--) {
                        did_Array[o] = 0;
                    }
                    acount = -1;
                } else if (acount == 0)
                    acount = -1;
            }

            String yushu = "";
            for (int i = did_Bin.length() - 1; i > 0; i--) {
                yushu = yushu + 0;
            }
            for (int j = did_Array.length - 1; j >= 0; j--) {
                yushu = yushu + did_Array[j];
            }
            exc.writeTofxbs("分析笔算",filename,"",0 + "." + yushu,"");
        }
    }

    //原码恢复余数
    public void ymhfys(String filename,String chushu,String beichushu)//原码恢复余数预计算部分
    {
        Excel exc = new Excel();
        exc.creatExcel(filename,"原码恢复余数");
        String xa=beichushu,yb=chushu;
        String a_p,b_p;//正负号
        if(Float.parseFloat(xa)>0)
            a_p="0";
        else a_p="1";
        if(Float.parseFloat(yb)>0)
            b_p="0";
        else b_p="1";

        String[] xaraay=xa.split("\\.");//小数点分片
        String[] yaraay=yb.split("\\.");

        int xp=Integer.parseInt(xaraay[1]);//存储小数点后的
        int yp=Integer.parseInt(yaraay[1]);

        String x_tf,y_tf;//原码（true form）,无小数点的
        x_tf=a_p+xp;
        y_tf=b_p+yp;

        int[] x1=Su_to_Sa(x_tf);//x原码数组
        int[] x2;//x原码的绝对值数组
        x2=Su_to_Sa(""+0+xp);
        int[] y1=Su_to_Sa(y_tf);
        int[] y2;
        y2=Su_to_Sa(""+0+yp);
        int[] y3=yt(y2);//-y*的补码，补码（two's complement）

        //x1:x原码  x2:x原码绝对值  y1:y原码  y2:y原码绝对值  y3:-y的原码的绝对值的补码
        exc.writepreset_ym("原码恢复余数",filename,ap(x1),ap(x2),ap(y1),ap(y2),ap(y3));//输入预设值
        ymhfys_calculate(filename,x2,y2,y3,Integer.parseInt(a_p),Integer.parseInt(b_p));
    }
    public void ymhfys_calculate(String filename,int[] x2,int[] y2,int[] y3,int x0,int y0)//原码恢复余数计算部分
    {
        Excel exc = new Excel();

        int[] shang=new int[x2.length];//存储商
        int i=x2.length,i2=x2.length;
        int p=x2[0];
        String sstr="";//商的移位表示
        while(i>0)//运算次数
        {
            int jw=0;//是否有进位
            if(p==1)//余数为负时
            {
                sstr=sstr+0;
                exc.writeToExcel("原码恢复余数",filename,"  "+ap(x2),"  "+sstr,"余数为负，上商“0”");
                exc.writeToExcel("原码恢复余数",filename,"+"+ap(y2),null,"恢复余数+[y*]补___________");
                for(int j=x2.length-1;j>=0;j--)//一次运算循环，需要恢复余数
                {
                    if ((x2[j]^jw)==1)//处理当前位进位
                    {
                        x2[j]=x2[j]^jw;
                        jw=0;
                    }
                    else if(x2[j]==1 && jw==1)
                    {
                        x2[j]=x2[j]^jw;
                        jw=1;
                    }
                    if(x2[j]==1 && y2[j]==1)
                        jw=1;
                    x2[j]=x2[j]^y2[j];
                }
                exc.writeToExcel("原码恢复余数",filename,"  "+ap(x2),null,"被恢复的余数");
                if (i>0)//最后一次不要移动
                {
                    x2 = moveleft(x2);//数组向左移位
                    exc.writeToExcel("原码恢复余数",filename,"  "+ap(x2),sstr,"<--1位(左移一位)");
                }
                jw=0;
                exc.writeToExcel("原码恢复余数",filename,"+"+ap(y3),null,"+[-y*]补(减去除数)________");
                for(int j=x2.length-1;j>=0;j--)//恢复余数后
                {
                    if ((x2[j]^jw)==1)//处理当前位进位
                    {
                        x2[j]=x2[j]^jw;
                        jw=0;
                    }
                    else if(x2[j]==1 && jw==1)
                    {
                        x2[j]=x2[j]^jw;
                        jw=1;
                    }
                    if(x2[j]==1 && y3[j]==1)
                        jw=1;
                    x2[j]=x2[j]^y3[j];
                }
                p=x2[0];
            }
            else//余数为正时
            {
                if (i==i2)//第一次输入Excel
                {
                    exc.writeToExcel("原码恢复余数",filename,"  "+ap(x2),ap(shang),null);
                    exc.writeToExcel("原码恢复余数",filename,"+"+ap(y3),null,"+[-y*]补(减去除数)________");
                }
                if (i>0 && i!=i2)//第一次与最后一次不要移动
                {
                    sstr=sstr+1;
                    exc.writeToExcel("原码恢复余数",filename,"  "+ap(x2),"  "+sstr,"余数为正，上商“1”");
                    x2=moveleft(x2);//数组向左移位
                    exc.writeToExcel("原码恢复余数",filename,"  "+ap(x2),sstr,"<--1位(左移一位)");
                    exc.writeToExcel("原码恢复余数",filename,"+"+ap(y3),null,"+[-y*]补(减去除数)________");
                }
                for(int j=x2.length-1;j>=0;j--)//一次运算循环
                {
                    if ((x2[j]^jw)==1)//处理当前位进位
                    {
                        x2[j]=x2[j]^jw;
                        jw=0;
                    }
                    else if(x2[j]==1 && jw==1)
                    {
                        x2[j]=x2[j]^jw;
                        jw=1;
                    }
                    if(x2[j]==1 && y3[j]==1)
                        jw=1;
                    x2[j]=x2[j]^y3[j];
                }
                p=x2[0];
                jw=0;
            }
            if(p==1)//判断余数正负号，用来上商
            {
                shang[i2-i]=0;
            }
            else shang[i2-i]=1;
            i--;
        }
        shang[0]=x0^y0;
        //判断余数正负号，输出最后一行
        if(p==1)exc.writeToExcel("原码恢复余数",filename," "+ap(x2),sstr+"0","余数为负，上商“0”");
        else    exc.writeToExcel("原码恢复余数",filename," "+ap(x2),sstr+"1","余数为正，上商“1”");
        exc.writeToExcel("原码恢复余数",filename,null,ap(shang),null);
    }

    //原码加减交替
    public void ymjjjt(String filename,String chushu,String beichushu)//原码加减交替预计算部分
    {
        Excel exc = new Excel();
        exc.creatExcel(filename,"原码加减交替");
        String xa=beichushu,yb=chushu;
        String a_p,b_p;//正负号
        if(Float.parseFloat(xa)>0)
            a_p="0";
        else a_p="1";
        if(Float.parseFloat(yb)>0)
            b_p="0";
        else b_p="1";

        String[] xaraay=xa.split("\\.");//小数点分片
        String[] yaraay=yb.split("\\.");

        int xp=Integer.parseInt(xaraay[1]);//存储小数点后的
        int yp=Integer.parseInt(yaraay[1]);

        String x_tf,y_tf;//原码（true form）,无小数点的
        x_tf=a_p+xp;
        y_tf=b_p+yp;

        int[] x1=Su_to_Sa(x_tf);//x原码数组
        int[] x2;//x原码的绝对值数组
        x2=Su_to_Sa(""+0+xp);
        int[] y1=Su_to_Sa(y_tf);
        int[] y2;
        y2=Su_to_Sa(""+0+yp);
        int[] y3=yt(y2);//-y*的补码，补码（two's complement）

        //x1:x原码  x2:x原码绝对值  y1:y原码  y2:y原码绝对值  y3:-y的原码的绝对值的补码
        exc.writepreset_ym("原码加减交替",filename,ap(x1),ap(x2),ap(y1),ap(y2),ap(y3));//输入预设值
        ymjjjt_calculate(filename,x2,y2,y3,Integer.parseInt(a_p),Integer.parseInt(b_p));
    }
    public void ymjjjt_calculate(String filename,int[] x2,int[] y2,int[] y3,int x0,int y0)//原码加减交替计算部分
    {
        Excel exc = new Excel();

        int[] shang=new int[x2.length];//存储商
        String sstr="";//商的另一种形式，用于存储
        int i=x2.length,i2=x2.length;
        int p=x2[0];
        while(i>0)//运算次数
        {
            int jw=0;//是否有进位
            if(p==1)//余数为负时
            {
                exc.writeToExcel("原码加减交替",filename,"+"+ap(y2),null,"+[y*]补(加除数)_________");
                for(int j=x2.length-1;j>=0;j--)//一次运算循环
                {
                    if ((x2[j]^jw)==1)//处理当前位进位
                    {
                        x2[j]=x2[j]^jw;
                        jw=0;
                    }
                    else if(x2[j]==1 && jw==1)
                    {
                        x2[j]=x2[j]^jw;
                        jw=1;
                    }
                    if(x2[j]==1 && y2[j]==1)
                        jw=1;
                    x2[j]=x2[j]^y2[j];
                }
            }
            else//余数为正时
            {
                if (i==i2)//第一次输入Excel
                {
                    exc.writeToExcel("原码加减交替",filename,"  "+ap(x2),ap(shang),null);
                    exc.writeToExcel("原码加减交替",filename,"+"+ap(y3),null,"+[-y*]补(减除数)________");
                }
                if (i!=i2)//非第一次输入Excel
                {
                    exc.writeToExcel("原码加减交替",filename,"+"+ap(y3),null,"+[-y*]补(减除数)________");
                }
                for(int j=x2.length-1;j>=0;j--)//一次运算循环
                {
                    if ((x2[j]^jw)==1)//处理当前位进位
                    {
                        x2[j]=x2[j]^jw;
                        jw=0;
                    }
                    else if(x2[j]==1 && jw==1)
                    {
                        x2[j]=x2[j]^jw;
                        jw=1;
                    }
                    if(x2[j]==1 && y3[j]==1)
                        jw=1;
                    x2[j]=x2[j]^y3[j];
                }
            }
            if(x2[0]==1)//判断余数正负号，用来上商
            {
                shang[i2-i]=0;
                sstr=sstr+"0";
            }
            else
            {
                shang[i2-i]=1;
                sstr=sstr+"1";
            }
            i--;
            p=x2[0];
            if(p==1)exc.writeToExcel("原码加减交替",filename,"  "+ap(x2),"  "+sstr,"余数为负，上商“0”");
            else    exc.writeToExcel("原码加减交替",filename,"  "+ap(x2),"  "+sstr,"余数为正，上商“1”");
            if (i>0)//最后一次不要移动
            {
                x2=moveleft(x2);//数组向左移位
                exc.writeToExcel("原码加减交替",filename,"  "+ap(x2),sstr,"<--1位(左移一位)");
            }

        }
        shang[0]=x0^y0;
        exc.writeToExcel("原码加减交替",filename,null,ap(shang),null);
    }

    //补码加减交替
    public void bmjjjt(String filename,String chushu,String beichushu)//补码加减交替预计算部分
    {
        Excel exc = new Excel();
        exc.creatExcel(filename,"补码加减交替");
        String xa=beichushu,yb=chushu;
        String a_p,b_p;//正负号
        if(Float.parseFloat(xa)>0)
            a_p="0";
        else a_p="1";
        if(Float.parseFloat(yb)>0)
            b_p="0";
        else b_p="1";

        String[] xaraay=xa.split("\\.");//小数点分片
        String[] yaraay=yb.split("\\.");

        int xp=Integer.parseInt(xaraay[1]);//存储小数点后的
        int yp=Integer.parseInt(yaraay[1]);

        String x_tf,y_tf;//原码（true form）,无小数点的
        x_tf=a_p+xp;
        y_tf=b_p+yp;

        int[] x1=Su_to_Sa(x_tf);//x原码数组
        int[] x2;//x原码的绝对值数组
        x2=Su_to_Sa(""+0+xp);
        int[] x3;//x的补码
        if(x1[0]==0)
            x3=x2;
        else x3=yt(x2);

        int[] y1=Su_to_Sa(y_tf);//y原码
        int[] y2;//y原码的绝对值数组
        y2=Su_to_Sa(""+0+yp);
        int[] y3;//y的补码，补码（two's complement）
        if(y1[0]==0)
            y3=y2;
        else y3=yt(y2);
        int[] y4=yt(y3);//-y的补码

        //x1:x原码  x2:x原码绝对值  x3:x补码  y1:y原码  y2:y原码绝对值 y3:y补码 y4:-y的补码
        exc.writepreset_bm("补码加减交替",filename,beichushu,chushu,ap(x3),ap(y3),ap(y4));//输入预设值
        bmjjjt_calculate(filename,x3,y3,y4);
    }
    public void bmjjjt_calculate(String filename,int[] x3,int[] y3,int[] y4)//补码加减交替计算部分
    {
        Excel exc = new Excel();

        int[] shang=new int[x3.length];//存储商
        String sstr="";//商的另一种形式，用于存储
        int i=x3.length,i2=x3.length;
        int p=x3[0]^y3[0];
        exc.writeToExcel("补码加减交替",filename,ap(x3),ap(shang),null);
        while(i>1)//运算次数
        {
            int jw=0;//是否有进位
            if(p==0)//x补与y补同号
            {
                if (i==i2) exc.writeToExcel("补码加减交替",filename,"+"+ap(y4),null,"[x]补与[y]补同号，+[-y]补_____");
                else exc.writeToExcel("补码加减交替",filename,"+"+ap(y4),null,"+[-y]补______________________");
                for(int j=x3.length-1;j>=0;j--)//一次运算循环
                {
                    if ((x3[j]^jw)==1)//处理当前位进位
                    {
                        x3[j]=x3[j]^jw;
                        jw=0;
                    }
                    else if(x3[j]==1 && jw==1)
                    {
                        x3[j]=x3[j]^jw;
                        jw=1;
                    }
                    if(x3[j]==1 && y4[j]==1)
                        jw=1;
                    x3[j]=x3[j]^y4[j];
                }
            }
            else//R补与y补异号
            {
                if (i==i2) exc.writeToExcel("补码加减交替",filename,"+"+ap(y3),null,"[x]补与[y]补异号，+[y]补_____");
                else exc.writeToExcel("补码加减交替",filename,"+"+ap(y3),null,"+[y]补_______________________");
                for(int j=x3.length-1;j>=0;j--)//一次运算循环
                {
                    if ((x3[j]^jw)==1)//处理当前位进位
                    {
                        x3[j]=x3[j]^jw;
                        jw=0;
                    }
                    else if(x3[j]==1 && jw==1)
                    {
                        x3[j]=x3[j]^jw;
                        jw=1;
                    }
                    if(x3[j]==1 && y3[j]==1)
                        jw=1;
                    x3[j]=x3[j]^y3[j];
                }
                jw=0;
            }
            p=x3[0]^y3[0];
            if(p==1)//上商，同号上1，异号上0
            {
                shang[i2-i]=0;
                sstr=sstr+"0";
                exc.writeToExcel("补码加减交替",filename,"  "+ap(x3),"  "+sstr,"[R]补与[y]补异号，上商“0”");
            }
            else
             {
                 shang[i2-i]=1;
                 sstr=sstr+"1";
                 exc.writeToExcel("补码加减交替",filename,"  "+ap(x3),"  "+sstr,"[R]补与[y]补同号，上商“1”");
             }
            i--;
            if(i>1)
            {
                x3=moveleft(x3);//数组向左移位
                exc.writeToExcel("补码加减交替",filename,"  "+ap(x3),sstr,"<--1位（左移一位）");
            }
        }
        shang[i2-1]=1;
        x3=moveleft(x3);//数组向左移位
        exc.writeToExcel("补码加减交替",filename,"  "+ap(x3),ap(shang),"<--1位,末位恒置1");
    }

    //--------------------------------------十进制转二进制----------------------------------------
    public String Dec_to_Bin(int n)
    {
        String str = "";
        while(n!=0){
            str = n%2 + str;
            n = n/2;
        }
        return str;
    }
    //----------------------------------字符串数字转数组存储(逆置的)------------------------------
    public int[] Su_to_Sa_re(String str)
    {
        int[] Array = new int[str.length()];
        int j=str.length()-1;
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            Array[j] = Integer.parseInt(ch.toString());
            j--;
        }
        return Array;
    }
    //------------------------------------字符串数字转数组存储------------------------------------
    public int[] Su_to_Sa(String str)
    {
        int[] Array = new int[str.length()];
        int j=str.length()-1;
        for (int i = str.length()-1; i >=0; i--) {
            Character ch = str.charAt(i);
            Array[j] = Integer.parseInt(ch.toString());
            j--;
        }
        return Array;
    }
    //------------------------------------数组转字符串存储----------------------------------------
    public String Sa_to_Su(int[] array)
    {
        int a = array.length ;
        String str="";
        for (int i = 0; i < a; i++) {
            str= str + array[i];
        }
        return str;
    }
    //-------------------------------------求[-y*]补----------------------------------------------
    public int[] yt(int[] Array)
    {
        int[] array=new int[Array.length];
        for (int i=0;i<Array.length;i++)//全部异或
        {
            array[i]=Array[i];
        }
        int A=array.length-1;
        for (int i=0;i<array.length;i++)//全部异或
        {
            array[i]=array[i]^1;
        }
        if(array[A]==0)//末位加一
            array[A]=1;
        else {
            array[A]=0;
            while (array[A-1]==1 && A >= 1)//进位
            {
                array[A-1]=0;
                A--;
            }
        }
        return array;
    }
    //------------------------------------数组向左移位一次，末位补零------------------------------
    public int[] moveleft(int[] array)
    {
        int a = array.length ;
        for (int i = 0; i < a-1; i++) {
            array[i]= array[i+1];
        }
        array[a-1]=0;
        return array;
    }
    //------------------------------------获取时间-------------------------------------------------
    public String gettime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    //-------------------------------数组加小数点，改字符串----------------------------------------
    public String ap(int[] Array)//add point
    {
        String str="";
        int a= Array.length;
        for (int i = 0; i < a; i++)
        {
            if(i==0)
                str=str+Array[i]+".";
            else
                str=str+Array[i];
        }
        return str;
    }
    //----------------------------------判断是否为二进制-------------------------------------------
    public int is_Bin(String num)
    {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-1]+(\\.[0-1]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(num).toString();
        } catch (Exception e) {
            return 0;//异常 说明包含非数字。
        }
        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return 0;
        }
        return 1;
    }

    //----------------------------------判断是否为十进制-------------------------------------------
    public int is_Dec(String num)
    {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(num).toString();
        } catch (Exception e) {
            return 0;//异常 说明包含非数字。
        }
        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return 0;
        }
        return 1;
    }

    //------------------------十进制转二进制，整个的-----------------------------------------------
    public String DectoBin(String num)
    {
        String p;//正负号
        if(Float.parseFloat(num)>0)
            p="0";
        else p="-0";
        String[] a=num.split("\\.");//小数点分片
        int x=Integer.parseInt(a[1]);//存储小数点后的
        String num2="";
        num2=p+"."+Dec_to_Bin(x);
        return num2;
    }

    //------------------------判断是否为小数，是否小于1-----------------------------------------------
    public int Beforepoint(String num)
    {
        boolean a=num.contains(".");
        String[] b=num.split("\\.");//小数点分片
        int x=Integer.parseInt(b[0]);
        if(a&&x==0)
            return 1;
        else
            return 0;
    }
}
