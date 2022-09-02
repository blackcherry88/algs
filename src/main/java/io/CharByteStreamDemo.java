package io;

import java.io.*;

public class CharByteStreamDemo {

    public static void main(String[] args) throws IOException {
        byte2CharStream("test.txt");
    }

    public static void char2ByteStream(String fileName) throws IOException {
        File f = new File(fileName);

        // OutputStreamWriter 是字符流通向字节流的桥梁,创建了一个字符流通向字节流的对象
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");

        String str = "我是字符流转换成字节流输出的";
        System.out.println(str);

        osw.write(str);
        osw.close();
    }

    public static void byte2CharStream(String fileName) throws IOException {
        File f = new File(fileName);

        InputStreamReader inr = new InputStreamReader(new FileInputStream(f),"UTF-8");

        char[] buf = new char[1024];

        int len = inr.read(buf);
        String str = "我是字符流转换成字节流输出的";
        String readStr = new String(buf, 0, len);
        assert(str.equals(readStr));
        System.out.println(readStr);

        inr.close();
    }

}
