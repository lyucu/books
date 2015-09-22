package lamb;

import java.util.Arrays;
import java.util.Comparator;

public class Demo {
    public static void main(String args[]) {
        // () -> {System.out.println("123");}
        String[] source = { "1", "22", "333", "4" };
        Arrays.sort(source, (fisrt, second) -> Integer.compare(fisrt.length(), second.length()));

        // 泛型推到 和final 注解 final
        Comparator<String> comp = (final String first, @NotNull final String second) -> {
            System.out.println("123");
            return Integer.compare(first.length(), second.length());
        };

    }
}
