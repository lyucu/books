package lamb.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LT1 {
    public static void main(String... args) {
        LT1 t = new LT1();
        // t.test1();
        // t.test2();
        // t.test3();
        // t.test4();
        t.test8();
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

    public void test2() {
        File file = new File("/var");
        String[] result = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (file.isDirectory())
                    return true;
                return false;
            }
        });
        System.out.println(Arrays.toString(result));
        result = file.list((x, y) -> {
            if (x.isDirectory())
                return true;
            return false;
        });
        System.out.println(Arrays.toString(result));
    }

    private String s3 = "l";

    public void test3() {
        String s1 = "g";
        String s2 = "c";
        File file = new File("/var");
        String[] result = file.list((x, y) -> {
            if (x.isDirectory())
                if (y.contains(s1) || y.contains(s2) || y.contains(this.s3))
                    return true;
            return false;
        });
        System.out.println(Arrays.toString(result));
    }

    public void test4() {
        File file = new File("/var");
        String[] result = file.list((x, y) -> {
            if (x.isDirectory())
                return true;
            return false;
        });
        Arrays.sort(result, String::compareTo);
        System.out.println(Arrays.toString(result));
    }

    // 都获取了想要的值
    public void test8() {
        String[] names = { "p1", "p2", "p3" };
        List<Runnable> runners = new ArrayList();
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }
        for (Runnable r : runners) {
            new Thread(r).run();
        }
        System.out.println("success");
    }
}
