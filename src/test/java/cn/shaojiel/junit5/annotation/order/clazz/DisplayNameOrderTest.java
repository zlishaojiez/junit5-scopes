package cn.shaojiel.junit5.annotation.order.clazz;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

@TestClassOrder(ClassOrderer.DisplayName.class)
class DisplayNameOrderTest {

    @Nested
    @DisplayName("A")
    class A {

        @Test
        void testA() {}
    }

    @Nested
    @DisplayName("B")
    class B {

        @Test
        void testB() {}
    }

    @Nested
    @DisplayName("C")
    class C {

        @Test
        void testC() {}
    }
}
