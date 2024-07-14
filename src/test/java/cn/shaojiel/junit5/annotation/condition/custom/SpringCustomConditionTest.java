package cn.shaojiel.junit5.annotation.condition.custom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;

/**
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/junit/jupiter/EnabledIf.html
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/junit/jupiter/DisabledIf.html
 */
@SpringBootTest
class SpringCustomConditionTest {

    @Test
    @EnabledIf("#{systemProperties['os.name'].toLowerCase().contains('mac')}")
    void enabled() {
        // ...
    }

    @Test
    @DisabledIf("#{systemProperties['os.name'].toLowerCase().contains('mac')}")
    void disabled() {
        // ...
    }

}
