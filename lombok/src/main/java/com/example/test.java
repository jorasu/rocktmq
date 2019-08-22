package com.example;

public class test {
    public static void main(String[] args) {
        //使用@Builder注解后，可以直接通过Builder设置字段参数
        test1 t1=new test1.Test1Builder().name("wang").age("12").sex("man").build();

        System.out.println("name is"+t1.getName()+'\n'+"age is :"+t1.getAge());
    }
}
