package cn.shaojiel.junit5.annotation.order.clazz;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class OrderTest {

    @Nested
    @Order(1)
    class A {

        @Test
        void testA() {}
    }

    @Nested
    @Order(2)
    class B {

        @Test
        void testB() {}
    }

    @Nested
    @Order(3)
    class C {

        @Test
        void testC() {}
    }
}
