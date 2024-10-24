package cn.shaojiel.junit5.testinterface;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit Jupiter allows @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, @TestTemplate,
 *
 * @BeforeEach, and @AfterEach to be declared on interface default methods. @BeforeAll and @AfterAll
 * can either be declared on static methods in a test interface or on interface default methods if the
 * test interface or test class is annotated with @TestInstance(Lifecycle.PER_CLASS)
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestLifecycleLogger {
    Logger log = LoggerFactory.getLogger(TestLifecycleLogger.class.getName());

    @BeforeAll
    default void beforeAllTests() {
        log.info("Before all tests");
    }

    @AfterAll
    default void afterAllTests() {
        log.info("After all tests");
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        log.info("About to execute [{}]", testInfo.getDisplayName());
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        log.info("Finished executing [{}]", testInfo.getDisplayName());
    }
}