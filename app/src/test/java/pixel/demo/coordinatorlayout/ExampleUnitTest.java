package pixel.demo.coordinatorlayout;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {    // > 大于 >> 幂指数运算 >>> 忽略符号
        System.out.print("值: " + Integer.MAX_VALUE + " 运算: " + (-Integer.MAX_VALUE >> 2) + "\t" + (Integer.MAX_VALUE / 4));
    }
}