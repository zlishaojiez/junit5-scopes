package cn.shaojiel.junit5.annotation;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven-filter-tags
 */
class TagTest {

    @Nested
    @Tag("fast")
    class TagClass {

        @Test
        void test1() {}

        @Test
        void test2() {}
    }

    @Nested
    class TagTests {

        @Test
        @Tag("fast")
        void test3() {}

        @Test
        @Tag("integration")
        void test4() {}
    }
}
