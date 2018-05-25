package com.aslan.final_test.show_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aslan.final_test.R;

public class bm_Fragment extends Fragment {

    private TextView v7,v9,v11,v13;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.bm,container,false);

        v7=(TextView)view.findViewById(R.id.bmtv7);
        String tv7="     正整数的补码是其二进制表示，与原码相同。\n" +
                "    【例1】+9的补码是00001001。\n" +
                "    （备注：这个+9的补码是用8位2进制来表示的，补码表示方式很多，还有16位二进制补码表示形式，以及32位二进制补码表示形式,64位进制补码表示形式等。每一种补码表示形式都只能表示有限的数字。）";
        v7.setText(tv7);

        v9=(TextView)view.findViewById(R.id.bmtv9);
        String tv9="     求负整数的补码，将其对应正数二进制表示所有位取反（包括符号位，0变1，1变0）后加1。\n" +
                "     同一个数字在不同的补码表示形式中是不同的。比如-15的补码，在8位二进制中是11110001，然而在16位二进制补码表示中，就是1111111111110001。以下都使用8位2进制来表示。\n" +
                "    【例2】求-5的补码。\n" +
                "    -5对应正数5（00000101）→所有位取反（11111010）→加1(11111011)\n" +
                "    所以-5的补码是11111011。\n" +
                "    【例3】数0的补码表示是唯一的。\n" +
                "    [+0]补=[+0]反=[+0]原=00000000\n" +
                "    [ -0]补=11111111+1=00000000";
        v9.setText(tv9);

        v11=(TextView)view.findViewById(R.id.bmtv11);
        String tv11="     已知一个数的补码，求原码的操作其实就是对该补码再求补码：\n" +
                "     ⑴如果补码的符号位为“0”，表示是一个正数，其原码就是补码。\n" +
                "     ⑵如果补码的符号位为“1”，表示是一个负数，那么求给定的这个补码的补码就是要求的原码。\n" +
                "     【例4】已知一个补码为11111001，则原码是10000111（-7）。\n" +
                "     因为符号位为“1”，表示是一个负数，所以该位不变，仍为“1”。\n" +
                "     其余七位1111001取反后为0000110；\n" +
                "     再加1，所以是10000111。";
        v11.setText(tv11);

        v13=(TextView)view.findViewById(R.id.bmtv13);
        String tv13="     【例5】-65的补码是10111111\n" +
                "     若直接将10111111转换成十进制，发现结果并不是-65，而是191。\n" +
                "     事实上，在计算机内，如果是一个二进制数，其最左边的位是1，则我们可以判定它为负数，并且是用补码表示。\n" +
                "     若要得到一个负二进制补码的数值，只要对补码全部取反并加1，就可得到其数值。\n" +
                "     如：二进制值：10111111（-65的补码）\n" +
                "     各位取反：01000000\n" +
                "     加1：01000001（+65）";
        v13.setText(tv13);


        return view;
    }
}
