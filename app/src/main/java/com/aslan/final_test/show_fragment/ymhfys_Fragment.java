package com.aslan.final_test.show_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aslan.final_test.R;

public class ymhfys_Fragment extends Fragment {
    private TextView v2,v3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.ymhfys,container,false);

        v2=(TextView)view.findViewById(R.id.hfys2);
        String tv2="     恢复余数法的特点是：当余数为负时，需加上除数，将其恢复成原来的余数。\n" +
                "     由上所述，商值的确定是通过比较被除数和除数的绝对值大小，即x*-y*实现的， 而计算机内只设加法器， 故需将x*-y*操作变为[x*]补+[-y*]补的操作。\n" +
                "\n" +
                "     例：已知：x=-0.1011,y=-0.1101,求：[x÷y]原\n" +
                "     解：由x*=0.1011，[x]原=1.1011\n" +
                "       y*=0.1101，[-y]补=1.0011,[y]原=1.1101\n" +
                "\n" +
                "     商值的求解过程如下：";
        v2.setText(tv2);

        v3=(TextView)view.findViewById(R.id.hfys3);
        String tv3="     故商值为0.1101\n" +
                "     商的符号位为 X0^Y0=1^1=0\n" +
                "     所以 [x÷y]原=0.1101\n" +
                "     由此可见，共上商5次，第一次上的商在商的整数位上，这对小数除法而言，可用它作溢出判断。即当该位为“1”时，表示此除法为溢出，不能进行，应由程序进行处理；当该位为“0”时，说明除法合法，可以进行。\n" +
                "　　 在恢复余数法中，每当余数为负时，都需恢复余数，这变延长了机器除法的时间，操作也很不规则，对线路结构不利。加减交替法可克服这些缺点。";
        v3.setText(tv3);

        return view;
    }
}
