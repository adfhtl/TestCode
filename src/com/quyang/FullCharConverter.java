// FullCharConverter.java
package com.quyang;

import java.io.UnsupportedEncodingException;
/*bool isChineseChar(const char * szSrc )
        {
        int   i=0;
        int len = strlen(szSrc);
        while   (i < len)
        {
        char   ch=szSrc[i];
        if((IsDBCSLeadByteEx(936,ch))   ||   (IsDBCSLeadByteEx(950,ch)))
        {
        //中文中不允许全角的输入
        if((unsigned char)ch == 0xA3 || (unsigned char)ch == 0xA1)
        return false;
        i+=2;   //中文
        }
        else
        {
        //判断是否为非可见字符
        if(ch < 0x21 || ch > 0x7D || ch == 0x22 || ch == 0x26 || ch == 0x25 )
        return false;
        else
        i+=1;   //其他
        }
        }

        return true;
        }*/
public class FullCharConverter {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        ch < 0x21 || ch > 0x7D || ch == 0x22 || ch == 0x26 || ch == 0x25 )
        char ch =  0x25 ;
        char ch2 = 0x22 ;
        char ch3 = 0x26 ;
        char ch4 =  0x20 ;
        // 全角转半角
        String QJstr = "山东省烟台市芝罘区前进路１号附８５号内７０２号";

        System.out.println(QJstr.length());
        System.out.println((QJstr+ch+ch2+ch3+ch4));
        System.out.println((QJstr+ch+ch2+ch3+ch4).length());
        String result = toSemiangle(QJstr);
        System.out.println(result.length());
        result = toSemiangle((QJstr+ch+ch2+ch3+ch4));
        System.out.println(result.length());

        System.out.println(QJstr);
        System.out.println(result);

//        System.out.println("------------------------------------");
//
//        // 半角转全角
//        String str = "山东省烟台市芝罘区前进路1号附85号内702号";
//        System.out.println(str);
//        System.out.println(half2Fullchange(str));

    }

    public static String toSemiangle(String src) {
        char[] c = src.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < c.length; index++) {
            if((c[index] > 0x7D && c[index] < 0x7F) || c[index] < 0x21 || c[index] == 0x22 || c[index] == 0x25 || c[index] == 0x26){
            } else if (c[index] == 12288) {// 全角空格
                sb.append((char) 32);
            } else if (c[index] > 65280 && c[index] < 65375) {// 其他全角字符
                sb.append((char) (c[index] - 65248));
            } else{
                sb.append(c[index]);
            }
        }
        return sb.toString();
    }

    // 全角转半角的 转换函数
    public static final String full2HalfChange(String QJstr)
            throws UnsupportedEncodingException {

        StringBuffer outStrBuf = new StringBuffer("");
        String Tstr = "";
        byte[] b = null;

        for (int i = 0; i < QJstr.length(); i++) {

            Tstr = QJstr.substring(i, i + 1);

            // 全角空格转换成半角空格
            if (Tstr.equals("　")) {
                outStrBuf.append(" ");
                continue;
            }

            b = Tstr.getBytes("unicode"); // 得到 unicode 字节数据

            if (b[3] == -1) { // 表示全角？
                b[2] = (byte) (b[2] + 32);
                b[3] = 0;

                outStrBuf.append(new String(b, "unicode"));
            } else {
                outStrBuf.append(Tstr);
            }

        } // end for.

        return outStrBuf.toString();
    }

    //  半角转全角
    public static final String half2Fullchange(String QJstr) throws UnsupportedEncodingException {
        StringBuffer outStrBuf = new StringBuffer("");
        String Tstr = "";
        byte[] b = null;

        for (int i = 0; i < QJstr.length(); i++) {

            Tstr = QJstr.substring(i, i + 1);
            if (Tstr.equals(" ")) {//半角空格
                outStrBuf.append(Tstr);
                continue;
            }

            b = Tstr.getBytes("unicode");

            if (b[3] == 0) { // 半角?
                b[2] = (byte) (b[2] - 32);
                b[3] = -1;
                outStrBuf.append(new String(b, "unicode"));
            } else {
                outStrBuf.append(Tstr);
            }

//    if (b[3] != -1) {
//     b[2] = (byte)(b[2] - 32);
//     b[3] = -1;
//     outStrBuf.append( new String(b, "unicode") );
//    } else {
//      outStrBuf.append( Tstr );
//    }

        }

        return outStrBuf.toString();
    }
}