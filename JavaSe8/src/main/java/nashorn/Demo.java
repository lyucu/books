package nashorn;

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
    public void test2() throws ScriptException {
        ScriptEngineManager sm = new ScriptEngineManager();
        ScriptEngine engine = sm.getEngineByName("nashorn");
        Object result = engine.eval("'Hellow Wolred'");
        System.out.println(result);
    }
}
