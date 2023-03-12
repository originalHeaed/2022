package org.example.mutilThreadPool;

import java.util.concurrent.*;

public class CreateThreadPool {
    public static void main(String[] args) {
        /* 创建指定长度的线程池 */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new SynchronousQueue<>());
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy()); // 线程池的线程拒绝策略
        threadPoolExecutor.allowCoreThreadTimeOut(true); // 允许核心线程被回收
        /* 将任务加入到线程池中 */
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("111,start" + Thread.currentThread());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println("111,end" + Thread.currentThread());
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("222,start" + Thread.currentThread());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println("222,end" + Thread.currentThread());
            }
        });
        /* 关闭线程池 */
        threadPoolExecutor.shutdown();
    }
}
