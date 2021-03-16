package com.littlebuddha.recruit.test.thread;

/**
 * 线程测试类一，继承Thread
 * @author ck
 * @date 2021/2/22 10:32
 */
public class TestThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("重写run方法："+i);
        }
    }

    public static void main(String[] args) {
        TestThread1 myThread = new TestThread1();
        myThread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("主方法："+i);
        }
    }
}
