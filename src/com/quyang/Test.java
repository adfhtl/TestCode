package com.quyang;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            input.append("一二三四五六七八九十");
        }
        final String in = input.toString();
        {
            final String ret = dealString1(in);
            System.out.println(ret);
            System.out.println("string length = " + ret.length());
            System.out.println("byte length = " + ret.getBytes("GBK").length);
        }
        {
            final String ret = dealString2(in);
            System.out.println(ret);
            System.out.println("string length = " + ret.length());
            System.out.println("byte length = " + ret.getBytes("GBK").length);
        }
        {
            final String ret = dealString3(in);
            System.out.println(ret);
            System.out.println("string length = " + ret.length());
            System.out.println("byte length = " + ret.getBytes("GBK").length);
        }
        {
            final String ret = dealString1("a" + in);
            System.out.println(ret);
            System.out.println("string length = " + ret.length());
            System.out.println("byte length = " + ret.getBytes("GBK").length);
        }
        {
            final String ret = dealString2("a" + in);
            System.out.println(ret);
            System.out.println("string length = " + ret.length());
            System.out.println("byte length = " + ret.getBytes("GBK").length);
        }
        {
            final String ret = dealString3("a" + in);
            System.out.println(ret);
            System.out.println("string length = " + ret.length());
            System.out.println("byte length = " + ret.getBytes("GBK").length);
        }


    }

    public static String dealString1(String in) {
        final String out = in.substring(0, 120);
        return out;
    }
    public static String dealString2(String in) {
        try {
            return new String(in.getBytes("GBK"),0,120,Charset.forName("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return in;
        }
    }
    public static String dealString3(String in) {
        try {
            ByteBuffer bb = ByteBuffer.wrap(in.getBytes("GBK"), 0, 120);
            CharsetDecoder gbk = Charset.forName("GBK").newDecoder();
            gbk.onMalformedInput(CodingErrorAction.IGNORE);
            return gbk.decode(bb).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return in;
        }
    }
}
