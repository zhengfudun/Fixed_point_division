package com.aslan.final_test.show_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aslan.final_test.R;

public class fxbs_Fragment extends Fragment {
    private TextView v4,v8;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fxbs,container,false);

        v4=(TextView)view.findViewById(R.id.fxbs4);
        String tv4="     其特点可归纳如下：\n" +
                "　　 ①每次上商都是由心算来比较余数(被除数)和除数的大小，确定商为1还是0。\n" +
                "　 　②每做一次减法，总是保持余数不动，低位补0，再减去右移后的除数。\n" +
                "　 　③商符单独处理。如果将上述规则完全照搬到计算机内，实现起来有一定困难，主要问题是：\n" +
                "　 　a．机器不能“心算”上商，必须通过比较被除数(或余数)和除数绝对值的大小来确定商值，即|x|-|y|，若差为正(够减)上商1,差为负(不够减)上商0。\n" +
                "　 　b．按照每次减法总是保持余数不动低位补0，再减去右移后的除数这一规则，则要求加法器的位数必须为除数的两倍。仔细分析发现，右移除数可以用左移余数的办法代替，其运算结果是一样的，但对线路结构更有利。不过此刻所得到的余数不是真正的余数，只有将它乘上2-n才是真正的余数。\n" +
                "　　 c．笔算求商时是从高位向低位逐位求的，而要求机器把每位商直接写到寄存器的不同位也是不可取的。计算机可将每一位商直接写到寄存器的最低位，并把原来的部分商左移一位。\n" +
                "　　 综上所述便可得原码除法运算规则。";
        v4.setText(tv4);

        v8=(TextView)view.findViewById(R.id.fxbs8);
        String tv8="    式中  为x的绝对值，记作x*\n" +
                "　　 为y的绝对值，记作y*\n" +
                "　　 即商符由两数符号位“异或”运算求得，商值由两数绝对值相除(x*/y*)求得。\n" +
                "　　 小数定点除法对被除数和除数有一定的约束，即必须满足下列条件：\n" +
                "　　 　0＜|被除数|≤|除数|\n" +
                "　　 实现除法运算时，还应避免除数为0或被除数为0。前者结果为无限大，不能用机器的有限位数表示；后者结果总是0，这个除法操作等于白做，浪费了机器时间。至于商的位数一般与操作数的位数相同。";
        v8.setText(tv8);

        return view;
    }
}
