package org.example;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        /* 启动子线程 */
        Thread thread = new Thread(new TestClass());
        thread.start();
        /* 使用父线程进行中断测试 */
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().toString() + "正在尝试中断子线程：" + thread.toString());
        thread.interrupt(); // 中断子线程
    }

    static class TestClass implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("程序继续进行中...");
                } catch (Exception e) {
                    System.out.println("当前线程：" + Thread.currentThread().toString() + " 被中断");
                    break;
                }
            }
        }
    }

}
