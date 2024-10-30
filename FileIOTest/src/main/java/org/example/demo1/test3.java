package org.example.demo1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class test3 {
    //遍历目录所有文件
    public static void ForFile(File RootFile){
        //不是目录就返回
        if(!RootFile.isDirectory())return;

        //打印目录
        //System.out.println("文件目录为:" + RootFile.getPath());

        //打印目录下文件和文件夹
        File[] ChildrenFile = RootFile.listFiles();

        for (File file : ChildrenFile) {
            if(file.isFile()){
                //System.out.println("    文件名：" + file.getName());
            }
            else{
                ForFile(file);
            }
        }

    }
    public static void main(String[] args) {
        File root = new File("C:\\Users\\A\\Desktop");
        System.out.println(root.getName());
        System.out.println(root.getAbsoluteFile());
        ForFile(root);
    }
}
