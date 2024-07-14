package cn.shaojiel.junit5.assertion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertThrowsTest {

    @Test
    void exceptionTesting() {
        String errorMessage = "Oops!";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException(errorMessage);
        });
        assertEquals(errorMessage, exception.getMessage());
    }
}
