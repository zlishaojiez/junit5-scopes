package cn.shaojiel.junit5.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
@DisplayName
@BeforeAll
@AfterAll
@BeforeEach
@AfterEach
@Test
 */
@Slf4j
class CommonAnnotationsTest {

    @DisplayName("BeforeAll")
    @BeforeAll
    static void setUpBeforeClass() {
        log.info("Before All");
    }

    @DisplayName("AfterAll")
    @AfterAll
    static void tearDownAfterClass() {
        log.info("After All");
    }

    @DisplayName("BeforeEach")
    @BeforeEach
    void setUp() {
        log.info("Before Each");
    }

    @DisplayName("AfterEach")
    @AfterEach
    void tearDown() {
        log.info("After Each");
    }

    @DisplayName("test1")
    @Test
    void test1() {
        log.info("test1");
    }

    @DisplayName("test2")
    @Test
    void test2() {
        log.info("test2");
    }
}
