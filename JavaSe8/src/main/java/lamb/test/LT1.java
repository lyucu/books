package lamb.test;

import java.util.Arrays;
import java.util.Comparator;

public class LT1 {
    public static void main(String... args) {
        LT1 t = new LT1();
        t.test1();
    }

    // 同一个线程
    public void test1() {
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName());
        Integer[] source = { 1, 2, 5, 2, 3 };
        Comparator<Integer> c = (num1, num2) -> {
            System.out.println(Thread.currentThread().getId());
            System.out.println(Thread.currentThread().getName());
            return Integer.compare(num1, num2);
        };
        Arrays.sort(source, c);
    }
}
