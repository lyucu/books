package newinjava8;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

public class Demo {
    @Test
    public void test() throws IOException {
        URL url = getClass().getClassLoader().getResource("jsFile.js");

        Stream<String> stream = Files.lines(Paths.get(url.getPath()));

    }
}
