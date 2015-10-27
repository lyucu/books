package nashorn;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class Demo {

    @Test
    // 调用js代码
    public void test() throws ScriptException {
        ScriptEngineManager sm = new ScriptEngineManager();
        ScriptEngine engine = sm.getEngineByName("nashorn");
        Object result = engine.eval("'Hellow Wolred'");
        System.out.println(result);
    }

    @Test
    // 调用js脚本
    public void test2() throws ScriptException, IOException {
        ScriptEngineManager sm = new ScriptEngineManager();
        ScriptEngine engine = sm.getEngineByName("nashorn");
        Object result = engine.eval("'Hellow Wolred'");
        System.out.println(result);
        URL url = getClass().getClassLoader().getResource("jsFile.js");
        result = engine.eval(Files.newBufferedReader(Paths.get(url.getPath())));
        System.out.println(result);

        url = getClass().getClassLoader().getResource("jsFile1.js");
        result = engine.eval(Files.newBufferedReader(Paths.get(url.getPath())));
        System.out.println(result);

    }

    @Test
    public void test3() throws ScriptException {
    }
}
