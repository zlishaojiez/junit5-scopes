package cn.shaojiel.junit5.annotation.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class PerClassTest {

    StringBuilder builder = new StringBuilder();

    @Test
    void testA() {
        builder.append("A");
        log.info("testA {}", builder);
    }

    @Test
    void testB() {
        builder.append("B");
        log.info("testB {}", builder);
    }

    @Test
    void testC() {
        builder.append("C");
        log.info("testC {}", builder);
    }
}
