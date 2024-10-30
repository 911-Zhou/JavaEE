package org.example.demo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class test1 {
    private static Scanner scanner = new Scanner(System.in);

    private static void scan(File root, String key){
        if(!root.isDirectory()){
            return;
        }

        File[] files = root.listFiles();
        if(files.length==0)return;

        for (File file : files){
              if(file.isDirectory()){
                  scan(file,key);
              }
              else{
                  if(file.getName().contains(key)){
                      System.out.println(file.getName()+"是否要删除(Y/N):");
                      if(scanner.next().equals("Y")){
                          System.out.println(file.delete());
                      }
                  }
              }
        }
    }
    public static void main(String[] args) {
        System.out.println("请输入查找目录：");
        String filepath = scanner.next();
        String key;
        File file = new File(filepath);

        if(!file.isDirectory()){
            System.out.println("目录不存在");
        }
        else{
            System.out.println("请输入文件关键字:");
            key  = scanner.next();
            scan(file,key);
        }
    }
}
