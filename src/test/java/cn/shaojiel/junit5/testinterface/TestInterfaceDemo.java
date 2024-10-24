package cn.shaojiel.junit5.testinterface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestInterfaceDemo implements TestLifecycleLogger, TimeExecutionLogger, TestInterfaceDynamicTestsDemo {
    @Test
    void isEqualValue() {
        assertEquals(1, "a".length(), "is always equal");
    }

}
