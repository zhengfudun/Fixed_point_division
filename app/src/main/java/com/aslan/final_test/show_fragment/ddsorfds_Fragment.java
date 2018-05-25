package com.aslan.final_test.show_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aslan.final_test.R;

public class ddsorfds_Fragment extends Fragment {
    private TextView v4,v9,v11;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.ddsorfds,container,false);

        v4=(TextView)view.findViewById(R.id.ddfd4);
        String tv4="     指整个机器字长的全部二进制位均表示数值位，相当于数的绝对值。\n" +
                "     若机器字长为n+1位，则数值表示为：\n" +
                "X=X0X1X2...Xn 其中Xi={0,1},0<=i<=n \n" +
                "     即X0*2^n + X1*2^(n-1） + X2*2^(n-2） + ... + Xn-1*2 + Xn\n" +
                "     数值范围是 0≤X≤2^(n+1） - 1\n" +
                "     例如：1111表示15。";
        v4.setText(tv4);

        v9=(TextView)view.findViewById(R.id.ddfd9);
        String tv9="     小数点位固定在最后一位之后称为定点整数。\n" +
                "     若机器字长为n+1位，数值表示为：\n" +
                "X=X0X1X2...Xn，其中Xi={0,1},0≤i≤n \n" +
                "     即（-1）^X0 * (X1*2^(n-1） + X2*2^(n-2） + ... + Xn-1*2 + Xn)\n" +
                "     数值范围是 -（2^n-1）≤X≤2^n-1\n" +
                "     例如：1111表示-7。";
        v9.setText(tv9);

        v11=(TextView)view.findViewById(R.id.ddfd11);
        String tv11="     小数点固定在最高位之后称为定点小数。\n" +
                "     若机器字长为n+1位，数值表示为：\n" +
                "X=X0.X1X2...Xn，其中Xi={0,1},0≤i≤n （这里X0不表示数字，只表示符号，若X0=0，则代表X=0.X1X2...Xn，X0=1，则代表-0.X1X2...Xn)。\n" +
                "     即X=X0.X1X2...Xn代表的小数为 (-1)^X0 * ((X1*2^(-1）） + X2*2^(-2） + ... + Xn-1*2^(-n+1） + Xn*2^(-n))\n" +
                "     数值范围是 -（1-2^(-n））≤X≤1-2^(-n)\n" +
                "     例如：1111表示-0.875";
        v11.setText(tv11);

        return view;
    }
}
