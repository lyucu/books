package stream.test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LT2 {
    public static void main(String... args) {
        LT2 t = new LT2();
        t.test3();
    }

    public void test1() {
        String[] source = "123010203012f0d0d0skoj0a0f0j0pa80900021370731fsd0fkhja09123120".split("0");
        System.out.println(Arrays.toString(source));
        Stream<String> ss = Stream.of(source);
        Arrays.asList(source).parallelStream();
        Stream<String> l = ss.parallel().filter(word -> {
            System.out.println("==");
            return word.length() > 0;
        }).limit(1);
        System.out.println(l.count());
        ;
    }

    public void test2() {
        int bd = 1000;
        int a = 1;
        int b = 2;
        int c = 3;
        Stream<Integer> xx = Stream.iterate(bd, f -> (a * f + b) % c);
        xx.limit(3).forEach(l -> System.out.println(l));
    }

    public void test3() {
        String source = "12312fdkfsadpdjfdjp123uifdjkpsafdj";
        IntStream stera = IntStream.iterate(0, f -> f + 1).limit(source.length() - 1);
        stera.parallel().forEach(l -> System.out.println(source.charAt(l)));

    }
}
