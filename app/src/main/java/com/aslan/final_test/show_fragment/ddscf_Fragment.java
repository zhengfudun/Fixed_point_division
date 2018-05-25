package com.aslan.final_test.show_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aslan.final_test.R;

public class ddscf_Fragment extends Fragment {
    private TextView v4,v6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.ddscf, container, false);

        v4=(TextView)view.findViewById(R.id.ddscf4);
        String tv4="     移位运算又叫移位操作，对计算机来说，有很大的实用价值，例如，当计算机没有乘（除）运算线路时，可以采用移位和加法相结合，实现乘（除）运算。\n" +
                "     对于正数，由于[x]原=[x]补=[x]反=真值，故移位后出现的空位均以0添之。对于负数，由于原码、补码和反码的表示形式不同，故当机器数移位时，对其空位的添补规则也不同。下表列出了三种不同码制的机器数（整数或小数均可），分别对应正数或负数，移位后的添补规则。必须注意的是：不论是正数还是负数，移位后其符号位均不变，这是算术移位的重要特点。\n" +
                "     其规律总结如下：\n" +
                "     （1）机器数为正时，不论左移或右移，添补代码均为0。\n" +
                "     （2）由于负数的原码其数值部分与真值相同，故在移位时只要使符号位不变，其空位均添0。\n" +
                "     （3）由于负数的反码其各位除符号位外与负数的原码正好相反，故移位后所添的代码应与原码相反，即全部添1。\n" +
                "     （4）分析任意负数的补码可发现，当对其由低位向高位找到第一个“1”时，在此“1”左边的各位均与对应的反码相同，而在此“1”右边的各位（包括此“1”在内）均与对应的原码相同，即添0；右移时困空位出现在高位，则添补的代码应与反码相同，即添1。";
        v4.setText(tv4);

        v6=(TextView)view.findViewById(R.id.ddscf6);
        String tv6="     补码加法的基本公式为：\n" +
                "     整数    [A]补+[B]补=[A+B]补  (mod 2n+1)\n" +
                "     小数    [A]补+[B]补=[A+B]补  (mod 2)\n" +
                "　 　即补码表示肋两个数在进行加法运算时，可以把符号位与数位同等处理，只要结果不超出机器能表示的数值范围，运算后的结果按2n+1取模(对于整数)；或按2取模(对于小数)，就能得到本次加法的运算结果。\n" +
                "　　 对于减法因A-B=A+(-B)\n" +
                "　　 则[A-B]补=[A+(-B)]补\n" +
                "　　 由补妈加法基本公式可得：\n" +
                "　　 整数    [A-B]补=[A]补+[-B]补  (mod 2n+1)\n" +
                "　　 小数    [A-B]补=[A]补+[-B]补  (mod 2)\n" +
                "　　 因此，若机器数采用补码， 当求A-B时， 只需先求[-B]补（称[-B]补为“求补”后的减数），就可按补码加法规则进行运算。而[-B]补由[B]补连同符号位在内，每位取反，末位加1而得。\n" +
                "\n" +
                "       例：x=0.1010，y=-0.0011，用补码的加法求x+y\n" +
                "　　 解：[x]补=0.1010，[y]补=1.1101\n" +
                "　　 [x]补+[y]补=0.1010+1.1101=0.0111（按模2的意义，最左边的1丢掉）\n" +
                "　　 x+y=0.0111　\n" +
                "\n" +
                "　 　例：x=0.1001，y=-0.0011，用补码的减法求x-y\n" +
                "　 　解：[x]补=0.1001，[y]补=1.1101,[-y]补=0.0011\n" +
                "　　 [x]补-[y]补=[x]补+[-y]补=0.1001+0.0011=0.1100\n" +
                "　　 x-y=0.1100";
        v6.setText(tv6);

        return view;
    }
}
