package stream.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

public class LT3 {

    @Test
    public void test1() {
        log(() -> "123");
    }

    @Test
    public void test2() {
        withLock("12", l -> (System.out.println(l)));
    }

    @Test
    public void test7() {
        getCOmparator();
    }

    @Test
    public void test20() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(3);
        list.add(2);
        List<String> resxult = map(list, l -> l.toString());
        System.out.println(Arrays.toString(resxult.toArray()));
    }

    private static void log(Supplier<String> message) {
        System.out.println(message.get());
    }

    private static void withLock(String xxx, Consumer<String> x) {
        System.out.println(xxx + "lokc");
        x.accept("123");
        System.out.println(xxx + "unlokc");
    }

    private static Comparator<String> getCOmparator() {
        return (x, y) -> Integer.compare(x.length(), y.length());
    }

    private static <T, U> List<U> map(List<T> list, Function<T, U> function) {
        List<U> result = new ArrayList<U>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
