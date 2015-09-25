package stream.test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class DemoStream {
    public static void main(String... args) {
        DemoStream d1 = new DemoStream();
        d1.run1();
    }

    public void run1() {
        String[] source = { "p1", "p2", "p3" };
        Stream<String> word = Stream.of(source);
        word = Stream.of("p1", "p3");
        Stream.empty();
        Arrays.stream(source);
        Stream.generate(Math::random);
        // 创建０，１，２．．．无限序列
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        // 过滤
        word.filter(w -> w.length() > 1);
        // 转换 但是每个元素会转化为一个流
        word.map(w -> w.toLowerCase());
        // 将多个流合并到一起
        // word.flatMap(w -> w.toString());

    }
}
