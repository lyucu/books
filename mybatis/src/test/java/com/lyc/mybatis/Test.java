package com.lyc.mybatis;

import java.io.InputStream;

public class Test {

    @org.junit.Test
    public void testLoad() {
        String resource = "org/mybatis/example/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

}
