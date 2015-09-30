package stream.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoStream {
    public static void main(String... args) {
        DemoStream d1 = new DemoStream();
        // d1.run1();
        d1.run4();
    }

    public void run1() {
        String[] source = { "p1", "p2", "p3" };
        Stream<String> word = Stream.of(source);
        Stream<String> word2 = Stream.of(source);
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

        word.limit(100);
        word.skip(1);
        Stream.concat(word, word2);

        // peek返回与原始流相同的流 但是每次取用时都会调用peak的函数
        word.peek(e -> System.out.println("Fecth " + e));

        // 去重
        word.distinct();

        // 排序 返回一个新流
        word.sorted(Comparator.comparing(String::length).reversed());

    }

    public void run2() {
        String[] source = { "p1", "p2", "p3" };
        Stream<String> word = Stream.of(source);
        // 获取最大值
        Optional<String> largest = word.max(String::compareToIgnoreCase);
        print(largest);
        word = Stream.of(source);
        // 获取第一个值
        largest = word.filter(s -> s.startsWith("p")).findFirst();
        print(largest);
        // 获取任意匹配值 对并行有利
        largest = word.filter(s -> s.startsWith("p")).findAny();
        // 查看任意匹配
        word.anyMatch(s -> s.startsWith("p"));
    }

    public void run3() throws Exception {
        List<String> result = new ArrayList();
        String[] source = { "p1", "p2", "p3" };
        Stream<String> word = Stream.of(source);
        Optional<String> largest = word.max(String::compareToIgnoreCase);
        largest.ifPresent(result::add);
        // 返回操作结构
        Optional<Boolean> temp = largest.map(result::add);
        // 为空时设置默认值或抛出异常
        largest.orElse("123");
        largest.orElseGet(() -> System.getProperty("123"));
        largest.orElseThrow(Exception::new);

        // 创建Optional
        Optional.of(1);
        Optional.empty();
        // /f方法返回T T有g方法
        // s.f().flatMpa(T::g);

        // s.flat(MyMath::squareRoot);
    }

    public void run4() {
        // 聚合操作
        Integer[] source = { 1, 2, 3 };
        Stream<Integer> word = Stream.of(source);
        Optional<Integer> result = word.reduce(Integer::sum);
        print(result);

        // 收集结果
        word = Stream.of(source);
        HashSet<Integer> rSet = word.collect(HashSet::new, HashSet::add, HashSet::addAll);
        word = Stream.of(source);
        rSet = (HashSet<Integer>) word.collect(Collectors.toSet());

        word = Stream.of(source);
        word.forEach(System.out::println);

        // 收集结果到ｍａｐ
        word = Stream.of(source);
        Map<Integer, Integer> map = word.collect(Collectors.toMap(Integer::intValue, Function.identity()));

        word.collect(Collectors.toCollection(TreeSet::new));
    }

    public void print(Optional source) {
        if (source.isPresent()) {
            System.out.println(source.get());
        }
    }
}
