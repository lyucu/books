package date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import org.junit.Test;

public class DemoDate {

    @Test
    public void test1() {
        // 计算时间差值
        Instant satrt = Instant.now();
        Instant end = Instant.now();
        Duration timeCost = Duration.between(satrt, end);
        System.out.println(timeCost.toNanos());
    }

    @Test
    public void test2() {
        // 本地日期
        LocalDate today = LocalDate.now();// 不带时区

        // 本地时间
        LocalTime time = LocalTime.now();
    }

    @Test
    public void test3() {
        // 格式化日期与解析
        TemporalAccessor tp = LocalTime.now();
        DateTimeFormatter.ISO_DATE.format(tp);
        DateTimeFormatter df = DateTimeFormatter.BASIC_ISO_DATE;
        df.withLocale(Locale.FRANCE);
        df = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
    }
}
