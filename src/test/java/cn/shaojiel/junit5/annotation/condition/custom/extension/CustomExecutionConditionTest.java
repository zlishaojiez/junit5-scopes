package cn.shaojiel.junit5.annotation.condition.custom.extension;

import org.junit.jupiter.api.Test;

class CustomExecutionConditionTest {

    @Test
    @Enabled
    void test1() {}

    @Test
    @Enabled(false)
    void test2() {}
}
