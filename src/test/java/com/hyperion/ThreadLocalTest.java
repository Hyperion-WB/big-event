package com.hyperion;

public class ThreadLocalTest {
    public  void testThreadLocalSetAndGet(){
        //提供一个线程安全的线程局部变量
        ThreadLocal tl = new ThreadLocal();
        //开启两个线程
        new Thread(()->{
            tl.set("hello");
            System.out.println(tl.get());
        }, "线程1").start();

    }
}
