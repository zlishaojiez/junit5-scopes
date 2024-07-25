package cn.shaojiel.junit5.annotation.order.clazz;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

@TestClassOrder(ClassOrderer.Random.class)
class RandomOrderTest {

    @Nested
    class A {

        @Test
        void testA() {}
    }

    @Nested
    class B {

        @Test
        void testB() {}
    }

    @Nested
    class C {

        @Test
        void testC() {}
    }
}
