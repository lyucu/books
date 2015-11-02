package newinjava8;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;

import org.junit.Test;

public class Demo {
    @Test
    public void test() throws IOException {
        URL url = getClass().getClassLoader().getResource("jsFile1.js");

        Stream<String> stream = Files.lines(Paths.get(url.getPath()));
        stream.forEach(System.out::println);

        // 遍历目录
        // Files.list(dir);
        // Files.walk(start, options);
    }

    @Test
    public void test2() {
        Base64.Encoder encoder = Base64.getEncoder();
        String original = "test";
        String ecdeoed = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
        System.out.println(ecdeoed);
        // encoder.wrap();

    }

    // 1.7
    @Test
    public void test3() {
        // List<String> list = Files.readAllLines(path);
        // Files.write(path, bytes, options);

    }
}
