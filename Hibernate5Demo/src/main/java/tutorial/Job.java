package tutorial;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Test;

@Entity
@Table
public class Job {

    @Test
    public void test() {
        System.out.println(LocalDateTime.now());
    }
}
