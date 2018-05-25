package com.aslan.final_test.show_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aslan.final_test.R;

public class ymjjjt_Fragment extends Fragment {
    private TextView v2,v3,v5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.ymjjjt,container,false);

        v2=(TextView)view.findViewById(R.id.ymjjjt2);
        String tv2="     加减交替法又称不恢复余数法，可以认为它是恢复余数法的一种改进算法。\n" +
                "     分析原码恢复余数法得知：\n" +
                "     当余数Ri>0时，可上商“1”，再对Ri左移一位后减除数，即2Ri-y*。\n" +
                "     当余数Ri>0时，可上商“0”，然后再做Ri+y*，即完成恢复余数的运算，再做2(Ri+y*)-y*，也即2Ri+y*。\n" +
                "     可见，原码恢复余数法可归纳为：\n" +
                "     当余数Ri>0时，商上“1”，做2Ri-y*的运算；\n" +
                "     当余数Ri<0时，商上“0”，做2Ri+y*的运算。\n" +
                "     这里已看不出余数的恢复问题了，而只是做加y*或减y*，因此，一般把它叫做加减交替法或不恢复余数法。\n" +
                "\n" +
                "　　 例：已知：x=-0.1011,y=-0.1101,求：[x÷ y]原\n" +
                "　　 解：[x]原=1.1011, x*=0.1011\n" +
                "　　 　　[y]原=0.1101, y*=0.1101, [-y*]补=1.0011\n" +
                "\n" +
                "　　 商值的求解过程如下表所示：";
        v2.setText(tv2);

        v3=(TextView)view.findViewById(R.id.ymjjjt3);
        String tv3="      商的符号位为 X0^Y0=1^0=1\n" +
                "      所以 [x÷ y]原=1.1101\n" +
                "      分析此例可见，n位小数的除法共上商n+1次，第一次商用来判断是否溢出。倘若比例因子选择恰当，除数结果不溢出，则第一次商肯定是0。如果省去这位商，只需上商n次即可，此时除法运算一开始应将被除数左移一位减去除数，然后再根据余数上商。";
        v3.setText(tv3);

        v5=(TextView)view.findViewById(R.id.ymjjjt5);
        String tv5="      对于整数除法，要求满足以下条件：\n" +
                "      0<|除数|≤|被除数|\n" +
                "      因为这样才能得到整数商。通常在做整数除法前，先要对这个条件进行判断，若不满足上述条件，机器发出出错信号，程序要重新设定比例因子。\n" +
                "      上述讨论的小数除法完全适用于整数除法，只是整数除法的被除数位数可以是除数的两倍，且要求被除数的高M位要比除数(n位)小，否则即为溢出。如果被除数和除数的位数都是单字长，则要在被除数前面加上一个字的0，从而扩展成双倍字长再进行运算。";
        v5.setText(tv5);
        return view;
    }
}
