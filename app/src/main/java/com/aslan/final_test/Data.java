package com.aslan.final_test;
//用于列表显示,
public class Data {
    private String content1,content2,content3;

    public Data() {}

    public Data(String content1,String content2,String content3) {
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }
}
