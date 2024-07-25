package cn.shaojiel.junit5.annotation.order.method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class DisplayNameOrderTest {

    @Test
    @DisplayName("Null")
    void nullValues() {
        // perform assertions against null values
    }

    @Test
    @DisplayName("Empty")
    void emptyValues() {
        // perform assertions against empty values
    }

    @Test
    @DisplayName("Valid")
    void validValues() {
        // perform assertions against valid values
    }
}
