package com.ailibin;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String operateType = JOptionPane.showInputDialog("请输入操作类型(输入1表示去掉@2x、2表示添加@2x)：");
        String type = "@2x";
        type = JOptionPane.showInputDialog("请输入需要的图片文件后缀的类型(类似@2x、@3x)：");
        String path = JOptionPane.showInputDialog("请输入需要更改的图片路径：");
        File file = new File(path);
        int opInt = 2;
        if (!operateType.trim().isEmpty()) {
            opInt = Integer.parseInt(operateType.trim());
        }
        if (opInt == 1) {
            deleteSuffix(file);
        } else {
            addSuffix(file, type);
        }
//        runTask();
    }

    public static void deleteSuffix(File file) throws IOException {

        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, null, "目录不存在,操作失败! ", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        File[] imageFiles = file.listFiles();
        for (File imageFile : imageFiles) {
            if (imageFile.isDirectory()) {
                deleteSuffix(imageFile);
            } else {
                String fileName = imageFile.getName();
                StringBuilder sb = new StringBuilder();
                if (fileName.contains("@2x") || fileName.contains("@3x") || fileName.contains("@4x")) {
                    String newFileName = fileName.substring(0, fileName.indexOf("@"));
                    if (fileName.endsWith(".png")) {
                        //png
                        sb.append(newFileName).append(".png");
                    } else if (fileName.endsWith(".jpg")) {
                        //jpg
                        sb.append(newFileName).append(".jpg");
                    } else {
                        //jpeg
                        sb.append(newFileName).append(".jpeg");
                    }
                    File newFile = new File(imageFile.getParent() + "/" + sb.toString());
                    imageFile.renameTo(newFile);
                } else {
                    continue;
                }
            }
        }
        JOptionPane.showMessageDialog(null, null, "操作成功! ", JOptionPane.PLAIN_MESSAGE);

    }

    //如果文件夹下同时包含了@2x或者@4x只能同时改一个
    private static void addSuffix(File file, String type) {
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, null, "目录不存在,操作失败! ", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        File[] imageFiles = file.listFiles();
        for (File imageFile : imageFiles) {
            if (imageFile.isDirectory()) {
                addSuffix(imageFile, type);
            } else {
                String fileName = imageFile.getName();
                StringBuilder sb = new StringBuilder();
                String newFileName;
                if (fileName.contains("@2x") || fileName.contains("@3x") || fileName.contains("@4x")) {
                    //如果文件已经包含了@2x、@3x、@4x从@符号截取
                    System.out.print("contains:" + fileName);
                    newFileName = fileName.substring(0, fileName.indexOf("@"));
                } else {
                    newFileName = fileName.substring(0, fileName.indexOf(".png"));
                }
                sb.append(newFileName).append(type).append(".png");
                System.out.print("newFileName:" + sb.toString());
                File newFile = new File(imageFile.getParent() + "/" + sb.toString());
                imageFile.renameTo(newFile);
            }
        }
        JOptionPane.showMessageDialog(null, null, "操作成功! ", JOptionPane.PLAIN_MESSAGE);

    }

    private static void runTask() throws IOException {
        System.out.print("Please input type:");
        Scanner s = new Scanner(System.in);//创建scanner，控制台会一直等待输入，直到敲回车结束
        Runtime r = Runtime.getRuntime();//调用脚本命令，打开所需程序
        int i = s.nextInt();//用户可自行定义i的值
        switch (i) {//指定switch语句表达式为变量i
            case 1:
                r.exec("notepad.exe");//当输入1时打开记事本
                break;//跳出该函数
            case 2:
                r.exec("mspaint.exe");//当输入2时打开画图
                break;//跳出该函数
            case 3:
                r.exec("C:\\啊哈C\\ahac.exe");//当输入3时打开啊哈c程序
                break;//跳出该函数
            case 4:
                r.exec("D:\\Program Files\\Tencent\\qqmusic\\QQMusic.exe");//当输入4时打开qq音乐程序
                break;//跳出该函数
            default:
                break;//若无常量满足表达式，则执行default后的语句
        }
    }
}
