package cn.shaojiel.junit5.annotation.condition;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;

import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

class ConditionalOSTest {

    @Nested
    class ConditionalSystem {

        @Test
        @EnabledOnOs(MAC)
        void onlyOnMacOs() {
            // ...
        }

        @TestOnMac
        void testOnMac() {
            // ...
        }

        @Test
        @EnabledOnOs({LINUX, MAC})
        void onLinuxOrMac() {
            // ...
        }

        @Test
        @DisabledOnOs(WINDOWS)
        void notOnWindows() {
            // ...
        }
    }

    @Nested
    class ConditionalArchitecture {

        @Test
        @EnabledOnOs(architectures = "aarch64")
        void onAarch64() {
            // ...
        }

        @Test
        @DisabledOnOs(architectures = "x86_64")
        void notOnX86_64() {
            // ...
        }

        @Test
        @EnabledOnOs(value = MAC, architectures = "aarch64")
        void onNewMacs() {
            // ...
        }

        @Test
        @DisabledOnOs(value = MAC, architectures = "aarch64")
        void notOnNewMacs() {
            // ...
        }

    }

}
