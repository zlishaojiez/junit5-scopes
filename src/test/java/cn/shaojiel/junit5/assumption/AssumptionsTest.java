package cn.shaojiel.junit5.assumption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AssumptionsTest {

    private static final String ENV = System.getenv().getOrDefault("ENV", "CI");

    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(ENV));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(ENV),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(ENV),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, 4/2);
                });
        // perform these assertions in all environments
        assertEquals(42, 6*7);
    }
}
