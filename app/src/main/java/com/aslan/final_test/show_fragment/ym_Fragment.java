package com.aslan.final_test.show_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aslan.final_test.R;

public class ym_Fragment extends Fragment {

    private TextView v6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.ym,container,false);

        v6 =(TextView)view.findViewById(R.id.ymtv6);
        String tv6="        原码不能直接参加运算，可能会出错。例如数学上，1+(-1)=0，\n" +
                "        而在二进制中00000001+10000001=10000010，换算成十进制为-2，出错了。\n" +
                "        所以原码的符号位不能直接参与运算，必须和其他位分开，这就增加了硬件的开销和复杂性，具体定义还分小数和整数：\n" +
                "        ①小数原码的定义\n" +
                "        [X] =X( 0≤X <1 )\n" +
                "        1－ X (－1 < X ≤ 0)\n" +
                "        例如： X=+0.1011 , [X]原= 0.1011\n" +
                "        X=－0.1011 [X]原= 1.1011\n" +
                "        ②整数原码的定义\n" +
                "        [X]原 =X (0≤X <2(n-1))\n" +
                "        2(n-1)－X (－ 2(n-1) < X ≤ 0)\n" +
                "        x为正整数时，[X]原=x；\n" +
                "        x为负整数时，[X]原=2的n次方-x；\n" +
                "        x为负小数时，[X]原=1-x；\n" +
                "        计算机中所有的数均用0，1编码表示，数字的正负号也不例外，如果一个机器数字长是n位的话，约定最左边一位用作符号位，其余n-1位用于表示数值。\n" +
                "        在符号位上用“0”表示正数；用“1”表示负数。数值位表示真值的绝对值。凡不足n-1位的，小数在最低位右边加零；整数则在最高位左边加零以补足n-1位。这种计算机的编码形式叫做原码。\n" +
                "        记作X=[X]原。例如在字长n=8的机器内：\n" +
                "        小数： [+0.1011]原=0.1011000\n" +
                "        [-0.1011]原=1.1011000\n" +
                "        整数： [+1011]原=00001011\n" +
                "        [-1011]原=10001011\n" +
                "        代码中的小数点“.”是在书写时为了清晰起见加上去的，在机器中并不出现。";
        v6.setText(tv6);

        return view;
    }
}