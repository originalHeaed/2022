import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.lang.reflect.Method;

public class MyTest {

    @Test
    public void test1() {
        int i = 0;
        while (true) {
            label1: {
                if (i != 5) {
                    System.out.println("当前 i 值为：" + i);
                    break label1;
                } else {
                    System.out.println("i 值为：" + i + " 循环结束");
                    return;
                }
            }
            i++;
        }
    }

    @Test
    public void test2() throws InterruptedException {
        /* 启动子线程 */
        Thread thread = new Thread(new TestClass());
        thread.start();
        /* 使用父线程进行中断测试 */
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().toString() + "正在尝试中断子线程：" + thread.toString());
        thread.interrupt(); // 中断子线程
    }

    class TestClass implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("程序继续进行中...");
                } catch (Exception e) {
                    System.out.println("当前线程：" + Thread.currentThread().toString() + " 被中断");
                }
            }
        }
    }

    @Test
    public void test() {
        Class clazz = TestReflect.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }
    }
    class TestReflect0 {
        public void method0() {};
    }


    class TestReflect extends TestReflect0{
        public void method1() {};
        private void method2() {};

        public final void method3() {};
    }
}
