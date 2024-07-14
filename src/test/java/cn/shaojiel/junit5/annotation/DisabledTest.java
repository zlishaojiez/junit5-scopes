package cn.shaojiel.junit5.annotation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DisabledTest {

    @Nested
    @Disabled("Disable the class")
    class DisabledClass {

        @Test
        void test1() {}

        @Test
        void test2() {}
    }

    @Nested
    /**
     * @Disabled is not @Inherited. Consequently, if you wish to disable a class whose superclass is @Disabled,
     * you must redeclare @Disabled on the subclass
     */
    class A_without_Disabled extends DisabledClass {

        @Test
        void test3() {}
    }

    @Nested
    class DisabledTests {

        @Test
        @Disabled("Disable the test3 method")
        void test4() {}

        @Test
        void test5() {}
    }
}
