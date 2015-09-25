package lamb;

import java.awt.Button;
import java.util.Arrays;
import java.util.Comparator;

public class Demo {
    public void prove() {
        // () -> {System.out.println("123");}
        String[] source = { "1", "22", "333", "4" };
        Arrays.sort(source, (fisrt, second) -> Integer.compare(fisrt.length(), second.length()));

        // 泛型推到 和final 注解 final
        Comparator<String> comp = (final String first, @NotNull final String second) -> {
            System.out.println("123");
            return Integer.compare(first.length(), second.length());
        };

        Button b = new Button();
        b.addActionListener(event -> System.out.println("123"));

        // 方法传递
        b.addActionListener(System.out::println);
        Arrays.sort(source, String::compareToIgnoreCase);

        Thread t = new Thread(this::prove);
    }

    // 变量的作用于
    public void varContext(String arg1, int arg2) {
        Runnable r = () -> {
            for (int i = 0; i < arg2; i++)
                System.out.println(arg1);
        };
    }
}
