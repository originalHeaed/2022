import org.junit.Test;

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
}
