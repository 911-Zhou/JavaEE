package org.example.demo1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String filename;
        filename = scanner.next();
        System.out.println("输入：" + filename);
        File file = new File(filename);
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
//        try {
//            System.out.println(file.getCanonicalFile());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //boolean ok =  file.createNewFile();
        //boolean ok = file.delete();
    }
}
//C:\Users\A\Pictures\202012309_xiao.gif
//‪C:\Users\A\Pictures\202012309_xiao.gif
