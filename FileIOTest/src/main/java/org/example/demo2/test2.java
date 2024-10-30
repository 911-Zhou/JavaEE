package org.example.demo2;

import java.io.*;
import java.util.Scanner;

//复制指定文件
public class test2 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("请输入文件路径:");
        String FilePath = scanner.next();
        System.out.println("输入："+FilePath);
        File file = new File(FilePath);
        System.out.println(file.isFile());
        if(!file.isFile()){
            System.out.println("输入路径不存在");
            return;
        }

//        System.out.print("请输入复制目的路径：");
//        String SourcePath = scanner.next();
//        File SourceFile = new File(SourcePath);
//        if(new File(SourceFile.getParent()).isDirectory()){
//            System.out.println("路径不存在");
//        }
//
//        try (FileInputStream fileInputStream = new FileInputStream(FilePath);
//             FileOutputStream fileOutputStream = new FileOutputStream(SourceFile)) {
//            while(true){
//                byte[] bytes = new byte[1024];
//                int n = fileInputStream.read(bytes);
//                System.out.println(n);
//                if(n==-1)return;
//                fileOutputStream.write(bytes,0,n);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

    }
}
